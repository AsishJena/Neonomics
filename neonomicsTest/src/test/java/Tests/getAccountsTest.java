package Tests;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import ApiUtilities.ApiUtils;
import PageObjects.Login;
import PageObjects.NewApplication;
import PageObjects.Secrets;
import WebUtilities.ReadProperties;
import WebUtilities.WebUtils;

public class getAccountsTest {
	WebDriver driver;
	Secrets secrets;
	ReadProperties props = new ReadProperties();
	String username = props.getCredentials().getProperty("username");
	String password = props.getCredentials().getProperty("password");
	

	@Test(priority = 1)
	public void executeWeb() {
 
		driver = WebUtils.driverSetUp();

		Login login = new Login(driver);
		login.goToUrl("https://developer.neonomics.io/login");
		login.login(username, password);
		System.out.println("Login to developer portal is successful");

		login.clickOnNewApplication();
		System.out.println("Click on New Application");

		NewApplication newApplication = new NewApplication(driver);
		newApplication.completeStep1("TestApp", "TestDescription");
		newApplication.completeStep2();
		System.out.println("New Application successfully created");

		secrets = new Secrets();
		secrets.setClientId(newApplication.getCLientId());
		secrets.setClientSecret(newApplication.getClientSecret());
		System.out.println("Secrets object is : " + secrets);

		driver.quit();

	}

	@Test(priority = 2)
	public void executeAPI() {
		ApiUtils utils = new ApiUtils();
		String accessTokens = utils.extractAccessToken(secrets);
		System.out.println("Access Token is successfully generated");
		System.out.println("accessToken: " + accessTokens);

		String bankId = utils.getBankId(accessTokens);

		System.out.println("BankId ");
		System.out.println("Bank Id " + bankId);
		System.out.println("Bank Id is successfully extracted");

		/*
		 * getSessionId
		 */

		String sessionId = utils.getSessionId(bankId, accessTokens);
		System.out.println("Session Id is successfully generated : " + sessionId);

		/*
		 * getConsent and getAccounts
		 */
		List<String> ibanList = utils.getAccounts(sessionId, accessTokens);
		if (ibanList != null) {
			System.out.println("Do nothing. Accounts is already fetched." + ibanList);
		} else {
			String consentUrl = utils.getConsent(sessionId, accessTokens);
			driver = WebUtils.driverSetUp();
			driver.get(consentUrl);

			Login login = new Login(driver);
			login.goToUrl(consentUrl);
			login.loginConsent("sean.dave112233@gmail.com", "Seandave123");
			System.out.println("Login Successful. fetching accounts");
			
			
			driver.quit();
			ibanList = utils.getAccounts(sessionId, accessTokens);

		}
		System.out.println("accounts list : " + ibanList);
		assertEquals(1, ibanList.size());

	}

	@Test(priority = 3)
	public void executeNegativeTests() {

		ApiUtils utils = new ApiUtils();
		String incorrectClientId = utils.validateAccessToken();
		System.out.println("Message:  " + incorrectClientId);

	}

}
