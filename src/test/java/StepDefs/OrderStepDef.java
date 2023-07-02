package StepDefs;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Context.TestContext;
import configfile.ConfigFileReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import utility.Utility;

public class OrderStepDef extends BaseClass {
	Utility utility_obj;
	TestContext testContext;
	ExtentTest test3;
	String payMethod;
	String orderId;
	boolean proceedButtonStatus;
	
	public OrderStepDef(TestContext context) throws IOException {
		testContext = context;
		driver = testContext.getWebDriverManagerSetup().getDriver();
		homePg = testContext.getPageObjectManager().getHomePageObj();
		checkoutPg = testContext.getPageObjectManager().getReviewAndPaymentsPageObj();
		paySelectPg = testContext.getPageObjectManager().getPayMethodSelectionPageObj();
		paymentGatewayPg = testContext.getPageObjectManager().getPaymentGatewayPage();
          orderSuccessPg=  testContext.getPageObjectManager().getOrderSuccessPage();
		utility_obj = new Utility(driver);
		configfile = new ConfigFileReader();
		
	}
//	@Given("Open url")
//	public void Open_the_url() {
//		test3=extent.createTest("Place an Order Test");
//		try {
//		driver.get(configfile.getPropertyByKey("AppUrl"));
//		//---Asserting URL---//
//		utility_obj.waitForUrlToLoad(configfile.getPropertyByKey("AppUrl"));
//		} catch (TimeoutException e) {
//			e.printStackTrace();
//			test3.log(Status.FAIL, e.getMessage());
//		}
//		String current_url = driver.getCurrentUrl();
//		if(current_url.equals(configfile.getPropertyByKey("AppUrl"))) {
//			test3.log(Status.INFO, "Application is launched sucessfully ");
//		}
//		else {
//			test3.log(Status.FAIL, "Application is not launched sucessfully ");
//		}
//		
//		assertEquals(current_url, configfile.getPropertyByKey("AppUrl"));
//		
//	}	

	@When("User navigates to the BuyNow button under any product and click on it {string}")
	public void user_navigate_to_the_buy_now_button_under_any_product_and_click_on_it(String product1){
		test=extent.createTest("Order Test");
		String current_url = driver.getCurrentUrl();
		if(current_url.equals(configfile.getPropertyByKey("AppUrl"))) {
			test.log(Status.INFO, "Application launched sucessfully ");
		}
		else {
			test.log(Status.FAIL, "Application not launched sucessfully ");
		}
		homePg.clickOnBuyNow(product1);
		System.out.println("Clicked on BuyNow button for product " + product1);
	    test.log(Status.INFO, "Clicked on BuyNow button for product : " + product1);
	}
	
	@When("User should be redirected to Checkout page")
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
			test.log(Status.FAIL, "User is not navigated to Checkout page ");
		}
		assertEquals(current_title, configfile.getPropertyByKey("CheckoutPageTitle"));
	}
	
	@When("User clicks on proceed button")
	public void user_clicks_on_proceed_button() {
		
		//----Asserting wevendPay Text----//
		try {
				 payMethod = checkoutPg.wevendPayTextIsDisplayed();
		} catch (TimeoutException e) {
			e.printStackTrace();
			test.log(Status.FAIL, e.getMessage());
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(Status.FAIL, e.getMessage());
		}
				
		if(payMethod.equals(configfile.getPropertyByKey("paymentmethod"))) {
						test.log(Status.PASS, "weVend Gateway is reflecting under Payment Methods : " + payMethod );
					}
					else {
						test.log(Status.FAIL, "Invalid Gateway is reflecting under Payment Methods : " + payMethod);
					}
				    Assert.assertEquals(payMethod, configfile.getPropertyByKey("paymentmethod"));
				
			try {
		// Asserting Proceed button
		 proceedButtonStatus = checkoutPg.ProceedIsEnabled();
			} catch (TimeoutException e) {
				e.printStackTrace();
				test.log(Status.FAIL, e.getMessage());
			}catch (NoSuchElementException e) {
				e.printStackTrace();
				test.log(Status.FAIL, e.getMessage());
			}
		Assert.assertEquals(proceedButtonStatus, true);
		//test.log(Status.PASS, "Proceed button is enabled");
		checkoutPg.clickOnProceed();
		
		// Asserting title
			try {
		utility_obj.waitForTitle(configfile.getPropertyByKey("PaymentMethodPageTitle"));
	} catch (TimeoutException e) {
			e.printStackTrace();
			test.log(Status.FAIL, e.getMessage());
		}
		String title = driver.getTitle();
		Assert.assertEquals(title,configfile.getPropertyByKey("PaymentMethodPageTitle"));
	}

	@When("User selects card payment method and navigates to Payment Gateway page")
	public void user_selects_card_payment_method_and_navigates_to_Payment_Gateway_page() throws InterruptedException {
	               try { 	    
		
				String cardPay=paySelectPg.getCardPayAsPaymentOption();
				test.log(Status.INFO, "Payment method is selected as : "+ cardPay);
				paySelectPg.clickOnCardPayButn();
	               } catch (TimeoutException e) {
		   				e.printStackTrace();
		   				test.log(Status.FAIL, e.getMessage());
		   			}catch (NoSuchElementException e) {
		   				e.printStackTrace();
		   				test.log(Status.FAIL, e.getMessage());
		   			}
		// Asserting url
	               try {
		utility_obj.waitForUrlToLoad(configfile.getPropertyByKey("CardPayUrl"));
	} catch (TimeoutException e) {
			e.printStackTrace();
			test.log(Status.FAIL, e.getMessage());
		}
		String current_url = driver.getCurrentUrl();
		assertEquals(current_url, configfile.getPropertyByKey("CardPayUrl"));
	               

	}
	@When("User enters card details")
	public void user_enters_card_details() {
		try {
		paymentGatewayPg.enter_CardNum(configfile.getPropertyByKey("CardNo"));
		paymentGatewayPg.enter_CardExpiryDate(configfile.getPropertyByKey("CardExpiry"));
		paymentGatewayPg.enter_CardCvv(configfile.getPropertyByKey("CardCvv"));
		} catch (TimeoutException e) {
			e.printStackTrace();
			test.log(Status.FAIL, e.getMessage());
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(Status.FAIL, e.getMessage());
		}
		
	}
	
	@When("User clicks on Pay button")
	public void user_clicks_on_pay_button() {
		try {
		paymentGatewayPg.clickOnPayButn();
		} catch (TimeoutException e) {
			e.printStackTrace();
			test.log(Status.FAIL, e.getMessage());
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(Status.FAIL, e.getMessage());
		}
		test.log(Status.INFO, "Clicked on Pay button");
		
	}

	@Then("Order should be placed and user will be navigated to Success page")
	public void order_should_be_placed_and_user_will_be_navigated_to_Success_page() {
		
		//*********************************************Temporary code************************************************//
		utility_obj.waitForTitle("React App");
		String current_title = driver.getTitle();
		if(current_title.equals("React App")) {
			test.log(Status.INFO, "User is on status page ");
		}
		else {
			test.log(Status.FAIL, "User is not redirected to status page");
		}
		driver.findElement(By.xpath("//button[text()='Get Receipt']")).click();
		
		//*********************************************Temporary code************************************************//
		
		try {
		utility_obj.waitForUrlToLoad(configfile.getPropertyByKey("SuccessPageUrl"));
		}catch (TimeoutException e) {
			String msg=e.getMessage();
			test.log(Status.FAIL, msg);
		}
		String current_url = driver.getCurrentUrl();
		if(current_url.equals(configfile.getPropertyByKey("SuccessPageUrl"))) {
			test.log(Status.INFO, "Payment is processed successfully ");
		}
		else {
			test.log(Status.FAIL, "Payment is not processed successfully");
		}
		assertEquals(current_url, configfile.getPropertyByKey("SuccessPageUrl"));
		try {
	      orderSuccessPg.orderSuccessTextIsDisplayed();
	      
	      orderId=orderSuccessPg.orderIdIsDisplayed();
		} catch (TimeoutException e) {
			e.printStackTrace();
			test.log(Status.FAIL, e.getMessage());
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(Status.FAIL, e.getMessage());
		}
	      if(orderId!=null) {
	    	  test.log(Status.PASS, "Order is Placed and Order ID # : " +orderId );
	    	  Assert.assertTrue(true);
	      }
	      else {
	    	  test.log(Status.FAIL, "Order ID # is not generated ");
	             Assert.assertTrue(false);
	             }
		
	}
}
