package com.RMGYantra.test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import baseUtils.BaseApiClass;
import baseUtils.IEndPoint;
import baseUtils.Project;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;
public class AddProjectInTextFormat extends BaseApiClass{
	@Test
	public void addprojectInTextFormat() {
		Project project = new Project("Nithesh", "09/12/2020", "TMRO", "TY_02", "Completed", 10);
		
		given()
			.contentType(ContentType.TEXT)
			.body(project,ObjectMapperType.JACKSON_1)
		.when()
			.post(IEndPoint.addProject)
		.then()
			.assertThat().statusCode(500)
		.and()
			.contentType(ContentType.JSON)
		.and()
			.time(Matchers.lessThanOrEqualTo(300L),TimeUnit.MILLISECONDS)
		.log().all();
		
	}

}
