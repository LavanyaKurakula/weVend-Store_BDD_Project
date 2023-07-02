package Context;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import managers.PageObjectManager;
import managers.WebDriverManagerSetup;

public class TestContext {

	private WebDriverManagerSetup webDriverManagerSetup;
	private PageObjectManager pageObjectManager;

	public TestContext() throws IOException {
		webDriverManagerSetup = new WebDriverManagerSetup();
		pageObjectManager = new PageObjectManager(webDriverManagerSetup.getDriver());
	}

	public WebDriverManagerSetup getWebDriverManagerSetup() {
		return webDriverManagerSetup;
	}

	public PageObjectManager getPageObjectManager() {
		return pageObjectManager;
	}


	 
}