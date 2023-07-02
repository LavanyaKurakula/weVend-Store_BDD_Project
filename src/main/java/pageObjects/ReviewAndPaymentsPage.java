package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.Utility;

public class ReviewAndPaymentsPage {
	Utility utility_obj;

	public ReviewAndPaymentsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		utility_obj = new Utility(driver);
	}

	@FindBy(xpath = "//button/span[text()='Proceed']")
	private WebElement proceedButton;

	@FindBy(xpath = "//span[text()='weVend Gateway']")
	private WebElement wevendPayText;

	@FindBy(xpath = "//div[@data-block='minicart']/descendant::span[@class='counter-number']")
	private WebElement cartItemsCount;

	public void getCartItemsCount() {
		utility_obj.waitForVisibilityOfElement(cartItemsCount);
		cartItemsCount.getText();
	}

	public void clickOnProceed() {
		utility_obj.waitForElementToBeClickable(proceedButton);
		proceedButton.click();
	}

	public boolean ProceedIsEnabled() {
		utility_obj.waitForElementToBeClickable(proceedButton);
		return proceedButton.isEnabled();

	}

	public String wevendPayTextIsDisplayed() {
		utility_obj.waitForVisibilityOfElement(wevendPayText);
		if (wevendPayText.isDisplayed()) {
			return wevendPayText.getText();
		} else
			return null;

	}
}
