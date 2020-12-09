package com.RMGYantra.test;

import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseUtils.BaseApiClass;
import baseUtils.DatabaseLib;
import baseUtils.IEndPoint;
import baseUtils.Project;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class AddprojectWithDuplicateId extends BaseApiClass{
	@Test
	public void addProjectWithDuplicateIDTest() throws Throwable {
		test=reports.createTest("addProjectWithDuplicateIDTest");
	Project	project=new Project("Deepak", "9/12/2020", "DeepakProj3", "TY_PROJ_604", "completed", 10);
	
	Response res=given()
		.contentType(ContentType.JSON)
		.body(project)
	.when()
		.post(IEndPoint.addProject);
	
	res.then()
		.assertThat().statusCode(502)
	.and()
		.assertThat().contentType(ContentType.JSON)
	.and()
		.assertThat().time(Matchers.lessThanOrEqualTo(300L),TimeUnit.MILLISECONDS)
		.log().all();
	
	String projectName=res.jsonPath().get("projectName");
	String actualData=DatabaseLib.executeQuery("select * from project",5 , "completed");
	Assert.assertEquals("Project is not created", actualData);
	
	}

}
