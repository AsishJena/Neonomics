package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewApplication {

	public WebDriver driver;

	public NewApplication(WebDriver driver) {
		this.driver = driver;
	}

	public void completeStep1(String appName, String appDescription) {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("eAppName")).sendKeys(appName);
		driver.findElement(By.id("eAppDesc")).sendKeys(appDescription);
		driver.findElement(By.id("next-button")).click();

	}

	public void completeStep2() {

		driver.findElement(By.xpath("//*[@id=\"aisp-section\"]/div/label/span")).click();
		driver.findElement(By.xpath("//*[@id=\"pisp-section\"]/div/label/span")).click();
		driver.findElement(By.id("genId")).click();
	}

	public String getCLientId() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		String clientId = driver.findElement(By.id("eClientId")).getAttribute("value");
		System.out.println("clientId" + clientId);
		return clientId;
	}

	public String getClientSecret() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String clientSecret = driver.findElement(By.id("eAppSecretId")).getAttribute("value");
		System.out.println("clientSecret" + clientSecret);
		return clientSecret;

	}
}
