package testRunner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(

		features = "src/test/resources/Features", 
		glue = "StepDefs", 
		dryRun = false, 
		monochrome=true,
		//tags = "@demo1",
		plugin = {
				"pretty", "html:target/cucumber-reports/reports_1.html",
			//	"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }
		}

)

public class Run1 extends AbstractTestNGCucumberTests {

}