package baseUtils;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import static io.restassured.RestAssured.*;

import java.io.File;

public class BaseApiClass {
	public static ExtentHtmlReporter htmlReport;
	public static ExtentReports reports;
	public static ExtentTest test;
	@BeforeSuite
	public void configBS() throws Throwable {
		htmlReport=new ExtentHtmlReporter(new File("./ExtentReport.html"));
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setDocumentTitle("RMG Yantra");
		htmlReport.config().setReportName("Api test script");
		reports=new ExtentReports();
		reports.attachReporter(htmlReport);
		reports.setSystemInfo("OS", "Windows 10");
		reports.setSystemInfo("ENV", "Testing");
		reports.setSystemInfo("ReporterName", "NitheshHS");
		DatabaseLib.getConnection();
		baseURI="http://localhost:8084";
		
		
	}
	@BeforeMethod
	public void setUP() {
		
	}
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getMethod().getMethodName()+" is passed");
		}else if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getMethod().getMethodName()+" is Failed");
		}else if(result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, result.getMethod().getMethodName()+" is skipped");
		}
		
	}
	@AfterSuite
	public void configAS() throws Throwable {
		reports.flush();
		DatabaseLib.clodeDBConnection();
	}

}
