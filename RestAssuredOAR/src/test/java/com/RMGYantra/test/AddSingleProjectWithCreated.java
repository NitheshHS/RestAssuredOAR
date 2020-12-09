package com.RMGYantra.test;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseUtils.BaseApiClass;
import baseUtils.DatabaseLib;
import baseUtils.IEndPoint;
import baseUtils.Project;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;
/**
 * 
 * @author Nitheesha
 *
 */
public class AddSingleProjectWithCreated extends BaseApiClass {
	
	@Test
	public void addSingleProjectWithCreatedTest() throws Throwable {
		Project project=new Project("Deepak", "9/12/2020", "DeepakProj2", "created", "TY_01", 10);
		
		Response res=given()
			.contentType(ContentType.JSON)
			.body(project)
		.when()
			.post(IEndPoint.addProject);
		res.then()
			.assertThat().statusCode(201)
		.and()
			.assertThat().contentType(ContentType.JSON)
		.assertThat().time(Matchers.lessThanOrEqualTo(3000L),TimeUnit.MILLISECONDS)
		.log().all();
		
		String actual=res.jsonPath().get("projectName");
		String msg=res.jsonPath().getString("msg");
		
		String expected=DatabaseLib.executeQuery("select * from project;", 4, "DeepakProj2");
	    Assert.assertEquals(expected, actual);
	    Assert.assertEquals(msg, "Successfully Added");
	    
	    
	}

}
