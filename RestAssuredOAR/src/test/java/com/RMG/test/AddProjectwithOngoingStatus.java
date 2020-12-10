package com.RMG.test;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import baseUtils.BaseApiClass;
import baseUtils.DatabaseLib;
import baseUtils.Project;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import junit.framework.Assert;

public class AddProjectwithOngoingStatus extends BaseApiClass
{

	@Test
	public void addProjectWitONGoingStatusTest() throws Throwable
	{
		test=reports.createTest("addProjectWithOnGoingStatusTest");
		Project p=new Project("Nirosha", "10/12/2020", "Airtel", "TY_00012", "ongoing", 6);
		
		Response response = given()
		.baseUri("http://localhost:8084")
		.contentType(ContentType.JSON)
		.body(p)
		.when()
		.post("/addProject");
		response
		.then()
		.assertThat()
		.statusCode(201).log().all()
		.and()
		.contentType(ContentType.JSON);
		/*.and()
		.time(lessThanOrEqualTo(300L));*/
		String status=response.jsonPath().get("projectName");
		String actualStatus=DatabaseLib.executeQuery("select * from project;",4, status);
		System.out.println(status);
		System.out.println(actualStatus);
		
		Assert.assertEquals(status, actualStatus);
	}
}
