package managers;

import org.openqa.selenium.WebDriver;
import pageObjects.*;

public class PageObjectManager {

	private WebDriver driver;

	private HomePage homePageObj;

	private ReviewAndPaymentsPage reviewAndPaymentsPageObj;

	private PayMethodSelectionPage payMethodSelectionPageObj;

	private PaymentGatewayPage paymentGatewayPageObj;
	private OrderSuccessPage orderSuccessPageObj;

	public PageObjectManager(WebDriver driver) {

		this.driver = driver;

	}

	public HomePage getHomePageObj() {

		return (homePageObj == null) ? homePageObj = new HomePage(driver) : homePageObj;

	}

	public PayMethodSelectionPage getPayMethodSelectionPageObj() {

		return (payMethodSelectionPageObj == null) ? payMethodSelectionPageObj = new PayMethodSelectionPage(driver)
				: payMethodSelectionPageObj;

	}

	public ReviewAndPaymentsPage getReviewAndPaymentsPageObj() {

		return (reviewAndPaymentsPageObj == null) ? reviewAndPaymentsPageObj = new ReviewAndPaymentsPage(driver)
				: reviewAndPaymentsPageObj;

	}

	public PaymentGatewayPage getPaymentGatewayPage() {

		return (paymentGatewayPageObj == null) ? paymentGatewayPageObj = new PaymentGatewayPage(driver)
				: paymentGatewayPageObj;

	}
	
	public OrderSuccessPage getOrderSuccessPage() {

		return (orderSuccessPageObj == null) ? orderSuccessPageObj = new OrderSuccessPage(driver)
				: orderSuccessPageObj;

	}
}
