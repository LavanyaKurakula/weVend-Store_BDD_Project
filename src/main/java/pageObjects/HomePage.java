package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.Utility;

public class HomePage {
	Utility utility_obj;

	public HomePage(WebDriver driver) {
		utility_obj = new Utility(driver);
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[@class='actions-primary']/form[@data-role='tocart-form']")
	private List<WebElement> productNames;

	@FindBy(xpath = "//div[@data-block='minicart']/a/descendant::span[@class='counter-number']")
	private WebElement cartItemsCount;

	@FindBy(xpath = "//button[@title='Buy Now']")
	private List<WebElement> buyNowButtons;

	@FindBy(xpath = "//span[text()='Add to Cart']")
	private List<WebElement> addToCartButtons;

	@FindBy(xpath = "//a[text()='shopping cart']/parent::div")
	private WebElement addToCart_Successmsg;

	// form[@data-product-sku='Triple Shot Coffee']/child::button[@title='Add to
	// Cart']

	public void clickOnAddToCart(String product) throws InterruptedException {
		for (int i = 0; i < productNames.size(); i++) {
			WebElement productName = productNames.get(i);
			String productNameText = productName.getAttribute("data-product-sku");
			if (product.equalsIgnoreCase(productNameText)) {
				WebElement itemName = addToCartButtons.get(i);
				utility_obj.waitForElementToBeClickable(itemName);
				itemName.click();
			}
		}
	}

	public void clickOnBuyNow(String itemneeded) {
		for (int i = 0; i < productNames.size(); i++) {
			WebElement productName = productNames.get(i);
			String productNameText = productName.getAttribute("data-product-sku");
			if (itemneeded.equalsIgnoreCase(productNameText)) {
				WebElement itemName = buyNowButtons.get(i);
				utility_obj.waitForElementToBeClickable(itemName);
				itemName.click();
			}
		}
	}

	public String get_AddToCart_Successmsg() {
		utility_obj.waitForVisibilityOfElement(addToCart_Successmsg);
		return addToCart_Successmsg.getText();
	}

	// Get the text value of the cart element
	// Parse the text value to an integer
	public int getCartItemCount() {
		utility_obj.waitForVisibilityOfElement(cartItemsCount);
		int itemCount;
		String cartText = cartItemsCount.getText();
		if (!cartText.isEmpty()) {
			itemCount = Integer.parseInt(cartText);
		} else {
			return 0;
			// Handle the case where the input string is empty
		}
		return itemCount;
	}
}
