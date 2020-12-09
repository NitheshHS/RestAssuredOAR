package com.RMGYantra.test;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import baseUtils.BaseApiClass;
import baseUtils.DatabaseLib;
import baseUtils.IEndPoint;
import baseUtils.Project;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;
public class AddprojectWithOnGoingStatus extends BaseApiClass{
	@Test
	public void addProjectWithOnGoingStatusTest() throws Throwable {
		
		Project project = new Project("Deepak", "09/12/2020", "DeepakProj5", "Ty_02", "On Going", 10);
		
		Response res=given()
		.contentType(ContentType.JSON)
		.body(project)
		.when()
		.post(IEndPoint.addProject);
		res.then()
		.assertThat().statusCode(201)
		.and()
		.contentType(ContentType.JSON)
		.log().all();
		String status=res.jsonPath().get("projectName");
		String actualStatus=DatabaseLib.executeQuery("select * from project;", 4, status);
	
		Assert.assertEquals(status, actualStatus);
	}

}
