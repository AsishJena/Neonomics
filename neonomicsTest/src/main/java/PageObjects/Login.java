package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {

	public WebDriver driver;

	public Login(WebDriver driver) {
		this.driver = driver;
	}

	public void goToUrl(String url) {
		driver.get(url);
	}

	public void clickOnOrganisation() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(
				By.xpath("/html/body/app-root/app-app-layout-component/div/app-dashboard/div/div[1]/div[2]/ul/li[2]"))
				.click();
	}

	public void login(String userName, String password) {
		driver.findElement(By.xpath("/html/body/app-root/app-login/div/div/div[1]/div/form/div[1]/input"))
				.sendKeys(userName);
		driver.findElement(By.xpath("/html/body/app-root/app-login/div/div/div[1]/div/form/div[2]/input"))
				.sendKeys(password);
		driver.findElement(By.id("login-button")).click();

	}

	public void clickOnNewApplication() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("new-application-button")).click();

	}

	public void loginConsent(String userName, String password) {

		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("kc-login")).click();
		//driver.findElement(By.id("kc-login")).click();
		
		if(!driver.findElements(By.id("kc-login")).isEmpty()) {

			driver.findElement(By.id("kc-login")).click();
		} 
		

	}

}
