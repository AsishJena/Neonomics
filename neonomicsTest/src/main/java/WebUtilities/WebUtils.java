package WebUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebUtils {

	public static WebDriver driver;

	public static WebDriver driverSetUp() {

		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
		driver = new ChromeDriver();
		return driver;

	}

	public static WebDriver getDriver() {   
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		WebUtils.driver = driver;
	}
}