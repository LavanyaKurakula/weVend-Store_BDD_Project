package utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Utility {
	WebDriver driver;

	public Utility(WebDriver driver) {
		this.driver = driver;
	}

	private By getByFromWebElement(WebElement element) {
		String elementAsString = element.toString();
		String locatorString = elementAsString.substring(elementAsString.indexOf("-> ") + 3);
		return By.xpath(locatorString);
	}

	public void waitForVisibilityOfElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForPresenceOfElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		By locator = getByFromWebElement(element);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

//	public String timeStamp() {
//		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//		return dateName;
//		
//	}
	public void waitForUrlToLoad(String url) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.urlToBe(url));
	}
	
	public void waitForTitle(String title) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.titleContains(title));
	}

	
}
