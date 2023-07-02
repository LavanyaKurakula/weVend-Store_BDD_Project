package StepDefs;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
//import org.apache.logging.log4j.Level;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
//import org.testng.log4testng.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import utility.Utility;

public class Hooks extends BaseClass {

	TestContext testContext;
	public Hooks(TestContext context) {
		testContext = context;
	}
	@BeforeAll
	public static void before_all() {
	//	String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		extent = new ExtentReports();
		 spark = new ExtentSparkReporter("./ExtentReports/WevendStoreExtentReport.html");
		 spark.config().setDocumentTitle("weVend Shop");
		 spark.config().setReportName("weVend Smoke Test");
		 extent.attachReporter(spark);
		
		 
	}
	@Before 
	public void setUp() throws IOException {
		driver = testContext.getWebDriverManagerSetup().getDriver();
		driver.manage().window().maximize();
	}
	
	@After(order=1)
	public void end(Scenario sc) throws IOException {
		if (sc.isFailed() == true) {

			TakesScreenshot scrShot = (TakesScreenshot) driver;
			byte[] data = scrShot.getScreenshotAs(OutputType.BYTES);
			sc.attach(data, "image/png", "failedscreenshot");

//				String FilePath="D:\\Test1\\BDDcucumberProject\\Screenshot\\failedscrshot.png";
//				File srcFile= scrShot.getScreenshotAs(OutputType.FILE);
//				File destFile= new File(FilePath);
//				FileUtils.copyFile(srcFile, destFile);
			
		}
	}

	@After(order = 2)	
	public void quitDriver() {
		
		driver.close();
	}
	
	@AfterAll	
	public static void quitExtent() {
		extent.flush();
}
	
}