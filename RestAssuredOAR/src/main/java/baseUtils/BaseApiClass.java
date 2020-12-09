package baseUtils;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.*;

public class BaseApiClass {
	
	@BeforeSuite
	public void configBS() throws Throwable {
		DatabaseLib.getConnection();
		baseURI="http://localhost:8084";
	}
	@BeforeMethod
	public void setUP() {
		
	}
	@AfterMethod
	public void tearDown() {
		
	}
	@AfterSuite
	public void configAS() throws Throwable {
		DatabaseLib.clodeDBConnection();
	}

}
