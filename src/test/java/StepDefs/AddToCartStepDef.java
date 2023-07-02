package StepDefs;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import Context.TestContext;
import configfile.ConfigFileReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.Utility;

public class AddToCartStepDef extends BaseClass {
	int addToCart_ItemsList;
	int buyNow_ItemsList;
	Utility utility_obj;
	TestContext testContext;
	String successmsg;
	int numberDisplayedOnCart;
	
	public AddToCartStepDef(TestContext context) throws IOException {
		testContext = context;
		
		driver = testContext.getWebDriverManagerSetup().getDriver();
		homePg = testContext.getPageObjectManager().getHomePageObj();
		checkoutPg = testContext.getPageObjectManager().getReviewAndPaymentsPageObj();
		
		utility_obj = new Utility(driver);
		 configfile = new ConfigFileReader();
	}

//	@Given("Launch the url")
//	public void Open_the_url() throws InterruptedException, ClassNotFoundException {
//		
//		test2=extent.createTest("Add to Cart Test");
//		//test2.log(Status.INFO, "Launching the App");
//		try {
//		driver.get(configfile.getPropertyByKey("AppUrl"));
//		//---Asserting URL---//
//		utility_obj.waitForUrlToLoad(configfile.getPropertyByKey("AppUrl"));
//		} catch (TimeoutException e) {
//			e.printStackTrace();
//			test2.log(Status.FAIL, e.getMessage());
//		}
//		String current_url = driver.getCurrentUrl();
//		if(current_url.equals(configfile.getPropertyByKey("AppUrl"))) {
//			test2.log(Status.INFO, "Application launched sucessfully ");
//		}
//		else {
//			test2.log(Status.FAIL, "Application not launched sucessfully ");
//		}
//		assertEquals(current_url, configfile.getPropertyByKey("AppUrl"));
//	}

	@When("User navigate to the AddToCart button under any product and click on it [{string}, {string}, {string}]")
	public void user_navigate_to_the_add_to_cart_button_under_any_product_and_click_on_it(String product1,String product2, String product3) throws InterruptedException {
		test=extent.createTest("Add to Cart Test");
		String current_url = driver.getCurrentUrl();
		if(current_url.equals(configfile.getPropertyByKey("AppUrl"))) {
			test.log(Status.INFO, "Application launched sucessfully ");
		}
		else {
			test.log(Status.FAIL, "Application not launched sucessfully ");
		}
		List<String> products = Arrays.asList(product1, product2, product3);
		   
				for (String product : products) {
					try {
				homePg.clickOnAddToCart(product);
				// ---- Assert success msg after adding item to cart----//
				 successmsg = homePg.get_AddToCart_Successmsg();
					} catch (TimeoutException e) {
						e.printStackTrace();
						test.log(Status.FAIL, e.getMessage());
					}catch (NoSuchElementException e) {
						e.printStackTrace();
						test.log(Status.FAIL, e.getMessage());
					}
				String sub_successmsg = "You added";
				boolean result = successmsg.contains(sub_successmsg);
				if(result) {
					test.log(Status.PASS, product + " is successfully added to the cart");
				}
				else {
					test.log(Status.FAIL, product + " is not added to the cart");
				}
				Assert.assertTrue(result);
				 }
				 addToCart_ItemsList = products.size();
			
	}

	@Then("^Product should be added to the cart$")
	public void product_should_be_added_to_the_cart() {
		try {
		//----Assert that the cart item count----//
			numberDisplayedOnCart = homePg.getCartItemCount();
		} catch (TimeoutException e) {
			e.printStackTrace();
			test.log(Status.FAIL, e.getMessage());
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(Status.FAIL, e.getMessage());
		}
		if(numberDisplayedOnCart==addToCart_ItemsList) {
			test.log(Status.INFO, "Total Number of items displayed on the cart is : " + numberDisplayedOnCart);
		}else {
			test.log(Status.FAIL, "Total Number of items displayed on cart is not matching with items added to the cart: "+ numberDisplayedOnCart);
		}
		Assert.assertEquals(numberDisplayedOnCart, addToCart_ItemsList);
	//addToCart_ItemsList

}
}
