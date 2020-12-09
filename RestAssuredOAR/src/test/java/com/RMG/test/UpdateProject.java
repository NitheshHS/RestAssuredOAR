package com.RMG.test;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class UpdateProject {
	@Test
	public void updateProjectTest() {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("createdBy", "Nithesh H S");
		map.put("createdOn", "12/08/2020");
		map.put("projectId","TY_01");
		map.put("projectName", "Google");
		map.put("status", "On Going");
		map.put("teamSize",5);
		
		given()
			.contentType(ContentType.JSON)
			.baseUri("http://localhost")
			.port(8084)
			.body(map)
		.when()
			.put("/projects/TY_PROJ_403")
		.then()
			.assertThat().statusCode(200)
		.and()
			.log().all();
		
		
		
	}

}
