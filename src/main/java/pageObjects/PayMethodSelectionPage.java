package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.Utility;

public class PayMethodSelectionPage {

	Utility utility_obj;
	WebDriver driver;

	public PayMethodSelectionPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		utility_obj = new Utility(driver);
	}

	@FindBy(xpath = "//b[text()='Card Pay']")
	WebElement cardPayOption;
	
	@FindBy(xpath = "//a[@href='/cardpay']//b[text()='Card Pay']")
	WebElement cardPayButn;

	public void clickOnCardPayButn() {
		utility_obj.waitForVisibilityOfElement(cardPayButn);
		cardPayButn.click();
	}
	
	public String getCardPayAsPaymentOption() {
		utility_obj.waitForVisibilityOfElement(cardPayOption);
		return cardPayOption.getText();
		
	}
}
