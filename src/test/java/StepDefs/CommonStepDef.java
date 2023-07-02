package StepDefs;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Context.TestContext;
import configfile.ConfigFileReader;
import io.cucumber.java.en.Given;
import utility.Utility;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonStepDef extends BaseClass {
	
	Utility utility_obj;
	TestContext testContext;
	//ExtentTest test2;
	public CommonStepDef(TestContext context) throws IOException {
		testContext = context;
		driver = testContext.getWebDriverManagerSetup().getDriver();
		homePg = testContext.getPageObjectManager().getHomePageObj();
		checkoutPg = testContext.getPageObjectManager().getReviewAndPaymentsPageObj();
		
		utility_obj = new Utility(driver);
		 configfile = new ConfigFileReader();
	}

	
	@Given("User is on HomePage")
	public void Open_the_url() throws InterruptedException, ClassNotFoundException {
		
		//test=extent.createTest("Launch the app Test");
		//test.log(Status.INFO, "Launching the App");
		try {
		driver.get(configfile.getPropertyByKey("AppUrl"));
		//---Asserting URL---//
		utility_obj.waitForUrlToLoad(configfile.getPropertyByKey("AppUrl"));
		} catch (TimeoutException e) {
			e.printStackTrace();
			test.log(Status.FAIL, e.getMessage());
			
		}
		String current_url = driver.getCurrentUrl();
		if(current_url.equals(configfile.getPropertyByKey("AppUrl"))) {
		//	test.log(Status.INFO, "Application launched sucessfully ");
		}
		else {
	//		test.log(Status.FAIL, "Application not launched sucessfully ");
		}
		assertEquals(current_url, configfile.getPropertyByKey("AppUrl"));
	}
}