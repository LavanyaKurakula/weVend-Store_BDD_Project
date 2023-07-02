package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.Utility;

public class OrderSuccessPage {
	Utility utility_obj;

	public OrderSuccessPage(WebDriver driver) {
		// this.driver=driver;
		PageFactory.initElements(driver, this);
		utility_obj = new Utility(driver);
	}

	@FindBy(xpath = "//span[contains(text(),'Thank you')]")
	WebElement order_successText;

	@FindBy(xpath = "//div[@class='checkout-success']/p/span")
	WebElement order_id;

	public String orderSuccessTextIsDisplayed() {
		utility_obj.waitForVisibilityOfElement(order_successText);
		if (order_successText.isDisplayed()) {
			return order_successText.getText();
		} else
			return null;

	}
	
	public String orderIdIsDisplayed() {
		utility_obj.waitForVisibilityOfElement(order_id);
		if (order_id.isDisplayed()) {
			return order_id.getText();
		} else
			return null;

	}
	
}
