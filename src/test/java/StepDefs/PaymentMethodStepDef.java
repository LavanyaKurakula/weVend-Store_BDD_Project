package StepDefs;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Context.TestContext;
import configfile.ConfigFileReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.Utility;

public class PaymentMethodStepDef extends BaseClass {
	int addToCart_ItemsList;
	int buyNow_ItemsList;
	Utility utility_obj;
	TestContext testContext;
	//ExtentTest test1;
	String payMethod;
	
	public PaymentMethodStepDef(TestContext context) throws IOException {
		testContext = context;
		
		driver = testContext.getWebDriverManagerSetup().getDriver();
		homePg = testContext.getPageObjectManager().getHomePageObj();
		checkoutPg = testContext.getPageObjectManager().getReviewAndPaymentsPageObj();
		paySelectPg = testContext.getPageObjectManager().getPayMethodSelectionPageObj();
		paymentGatewayPg = testContext.getPageObjectManager().getPaymentGatewayPage();
		
		utility_obj = new Utility(driver);
		configfile = new ConfigFileReader();
		
	}

//	@Given("Launch the App")
//	public void Launch_the_App() throws InterruptedException, ClassNotFoundException {
//		
//		test1=extent.createTest("Payment Method Test");
//		try {
//		driver.get(configfile.getPropertyByKey("AppUrl"));
//		//---Asserting URL---//
//		utility_obj.waitForUrlToLoad(configfile.getPropertyByKey("AppUrl"));
//		} catch (TimeoutException e) {
//			e.printStackTrace();
//			test1.log(Status.FAIL, e.getMessage());
//		}catch (NoSuchElementException e) {
//			e.printStackTrace();
//			test1.log(Status.FAIL, e.getMessage());
//		}
//		String current_url = driver.getCurrentUrl();
//		if(current_url.equals(configfile.getPropertyByKey("AppUrl"))) {
//			test1.log(Status.INFO, "Application is launched sucessfully ");
//		}
//		else {
//			test1.log(Status.FAIL, "Application is not launched sucessfully ");
//		}
//		assertEquals(current_url, configfile.getPropertyByKey("AppUrl"));
//		
//	}

	@When("User navigate to the BuyNow button under any product and click on it {string}")
	public void user_navigate_to_the_buy_now_button_under_any_product_and_click_on_it(String product1){
		test=extent.createTest("Payment Method Test");
		String current_url = driver.getCurrentUrl();
		if(current_url.equals(configfile.getPropertyByKey("AppUrl"))) {
			test.log(Status.INFO, "Application launched sucessfully ");
		}
		else {
			test.log(Status.FAIL, "Application not launched sucessfully ");
		}
		try {
		homePg.clickOnBuyNow(product1);
		} catch (TimeoutException e) {
			e.printStackTrace();
			test.log(Status.FAIL, e.getMessage());
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(Status.FAIL, e.getMessage());
		}
	    test.log(Status.INFO, "Clicked on BuyNow button for product : " + product1);
	}

	@When("User should be navigated to Checkout page")
	public void user_should_be_redirected_to_Checkout_page() {
		//----Asserting URL---//
		try {
		utility_obj.waitForTitle(configfile.getPropertyByKey("CheckoutPageTitle"));
	} catch (TimeoutException e) {
		e.printStackTrace();
		test.log(Status.FAIL, e.getMessage());
	}
		String current_title = driver.getTitle();
		if(current_title.equals(configfile.getPropertyByKey("CheckoutPageTitle"))) {
			test.log(Status.INFO, "User is navigated to Checkout page ");
		}
		else {
			test.log(Status.FAIL, "User is not navigated to Checkout page "+ "");
		}
		assertEquals(current_title, configfile.getPropertyByKey("CheckoutPageTitle"));
	}
	
	@Then("Payment method should be present on the page as {string}")
	public void payment_method_should_be_present_on_the_page_as(String wevendtext) throws InterruptedException {
		try {
		//----Asserting wevendPay Text----//
		 payMethod = checkoutPg.wevendPayTextIsDisplayed();
		//	payMethod = "other gateway";
		} catch (TimeoutException e) {
			e.printStackTrace();
			test.log(Status.FAIL, e.getMessage());
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(Status.FAIL, e.getMessage());
		}  
		if(payMethod.equals(wevendtext)) {
			test.log(Status.PASS, "weVend Gateway is reflecting under Payment Methods : " + payMethod );
		}
		else {
			test.log(Status.FAIL, "Invalid Gateway is reflecting under Payment Methods : " + payMethod);
		}
		Assert.assertEquals(payMethod, wevendtext);

}
}