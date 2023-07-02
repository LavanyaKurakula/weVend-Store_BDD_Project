package StepDefs;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.Helper;

public class HomePageStepDef extends BaseClass {
	int addToCart_ItemsList;
	int buyNow_ItemsList;
	Helper helper;
	TestContext testContext;
	
	public HomePageStepDef(TestContext context) throws IOException {
		testContext = context;
		
		driver = testContext.getWebDriverManagerSetup().getDriver();
		homePg = testContext.getPageObjectManager().getHomePageObj();
		checkoutPg = testContext.getPageObjectManager().getReviewAndPaymentsPageObj();
		
		helper = new Helper(driver);
	}

	@Given("Open the url {string}")
	public void Open_the_url(String expected_url) throws InterruptedException, ClassNotFoundException {
		
		test=extent.createTest("Test1");
		test.log(Status.INFO, "Launching the App");
		driver.get(expected_url);
		System.out.println("Launching the App");

		//---Asserting URL---//
		helper.waitForUrlToLoad(expected_url);
		String current_url = driver.getCurrentUrl();
		System.out.println("user is redirected to this url: " + current_url);
		assertEquals(current_url, expected_url);
		System.out.println("expected url " + expected_url + "matches with the current url " + current_url);
		test.log(Status.PASS, "Application launched sucessfullly ");
	}

	@When("User navigate to the BuyNow button under any product and click on it {string}")
	public void user_navigate_to_the_buy_now_button_under_any_product_and_click_on_it(String product1){
		//extent.createTest("MyFirstTest").log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!");
		homePg.clickOnBuyNow(product1);
		System.out.println("Clicked on BuyNow button for product " + product1);
	    test.log(Status.PASS, "Clicked on BuyNow button for product" + product1);
	}

	@When("User should be redirected to {string}")
	public void user_should_be_redirected_to(String expected_url) {
		//extent.createTest("MyFirstTest").log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!");
		//----Asserting URL---//
		helper.waitForUrlToLoad(expected_url);
		String current_url = driver.getCurrentUrl();
		System.out.println("user is redirected to this url: " + current_url);
		assertEquals(current_url, expected_url);
		System.out.println("expected url " + expected_url + "matches with the current url " + current_url);
		test.log(Status.PASS, "expected url matches with the current url");
		
	}

	@Then("Payment method should be present on the page as {string}")
	public void payment_method_should_be_present_on_the_page_as(String wevendtext) {
		//extent.createTest("MyFirstTest").log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!");
		//----Asserting wevendPay Text----//
		String result2 = checkoutPg.wevendPayTextIsDisplayed();
		Assert.assertEquals(result2, wevendtext);
		System.out.println("wevend pay text is displayed under payment Method");
		test.log(Status.PASS, "wevend pay text is displayed under payment Method");
		//extent.flush();
	}

	@When("User navigate to the AddToCart button under any product and click on it [{string}, {string}, {string}]")
	public void user_navigate_to_the_add_to_cart_button_under_any_product_and_click_on_it(String product1,String product2, String product3) throws InterruptedException {
		      test= extent.createTest("Add to cart");
		    List<String> products = Arrays.asList(product1, product2, product3);
		     for (String product : products) {
			homePg.clickOnAddToCart(product);
			// ---- Assert success msg after adding item to cart----//
			String successmsg = homePg.get_AddToCart_Successmsg();
			String sub_successmsg = "You added";
			boolean result = successmsg.contains(sub_successmsg);
			Assert.assertTrue(result);
			System.out.println(product + "successfully added to the cart");
			test.log(Status.PASS, product + "successfully added to the cart");
		     }
		addToCart_ItemsList = products.size();
	}

	@Then("^Product should be added to the cart$")
	public void product_should_be_added_to_the_cart() {
		//----Assert that the cart item count----//
		int afterCount = homePg.getCartItemCount();

		Assert.assertEquals(afterCount, addToCart_ItemsList);
		System.out.println("No. of Items in the cart are: " + afterCount);
		test.log(Status.PASS, "Number displayed on cart matches with items added to the cart");
		//extent.flush();
	}

	public static void main(String[] args) {
		test=extent.createTest("Test1");
	}

}
