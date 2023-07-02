package managers;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import configfile.ConfigFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverManagerSetup {
	private WebDriver driver;

	public WebDriver getDriver() throws IOException {

		ConfigFileReader configfile = new ConfigFileReader();

		if (configfile.getPropertyByKey("browser").equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			if (driver == null) {
				driver = new ChromeDriver();
			}
			return driver;
		} else if (configfile.getPropertyByKey("browser").equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if (driver == null)
			{
				driver = new FirefoxDriver();
			}
			return driver;
		}
		return null;

	}
}