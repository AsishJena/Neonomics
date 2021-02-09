package ApiUtilities;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import static io.restassured.RestAssured.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import PageObjects.Secrets;

public class ApiUtils {

	WebDriver driver;

	public String extractAccessToken(Secrets secrets) {

		/*
		 * client = "96d434cc-397b-4a70-a390-31d36eca8c44" secret =
		 * "466b203a-53ba-4630-b16c-e9743edbfe07"
		 */

		RestAssured.baseURI = "https://sandbox.neonomics.io";

		Response response = RestAssured.given()
				.config(RestAssured.config().encoderConfig(
						EncoderConfig.encoderConfig().encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
				.contentType("application/x-www-form-urlencoded; charset=UTF-8")
				.formParam("grant_type", "client_credentials").formParam("client_id", secrets.getClientId())
				.formParam("client_secret", secrets.getClientSecret())
				.post("/auth/realms/sandbox/protocol/openid-connect/token").then().extract().response();

		Assert.assertEquals(response.statusCode(), 200);
		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);
		String accessToken = js.getString("access_token");

		return accessToken;

//	
	}

	public String getBankId(String accessToken) {
		String auth = "Bearer " + accessToken;
		RestAssured.baseURI = "https://sandbox.neonomics.io";
		String bankId = "";

		Header header1 = new Header("Authorization", auth);
		Header header2 = new Header("Accept", "application/json");
		Header header3 = new Header("x-device-id", "example_id_for_quickstar");

		Headers headers = new Headers(header1, header2, header3);
		Response response = given().headers(headers).get("/ics/v3/banks").then().extract().response();
		System.out.println("Response Status" + response.statusCode());

		Assert.assertEquals(response.statusCode(), 200);
		List<BankIdResponse> bankList = Arrays.asList(response.getBody().as(BankIdResponse[].class));

		System.out.println("Size" + bankList.size());
		for (int i = 0; i < bankList.size(); i++) {
			String retrievedBankName = bankList.get(i).getBankDisplayName();
			if (retrievedBankName.equalsIgnoreCase("Justo Bank")) {
				bankId = bankList.get(i).getId();
				break;
			}
		}
		System.out.println(bankList.get(0).getBankDisplayName());

		return bankId;
	}

	public String getSessionId(String bankId, String accessToken) {

		String auth = "Bearer " + accessToken;
		RestAssured.baseURI = "https://sandbox.neonomics.io";

		Header header1 = new Header("Authorization", auth);
		Header header2 = new Header("Content-Type", "application/json");
		Header header3 = new Header("x-device-id", "example_id_for_quickstar");

		Headers headers = new Headers(header1, header2, header3);
		Response response = given().headers(headers).body("{\"bankId\": \"anVzdG9iYW5rLnYxSlVTVE5PS0s=\"}")
				.post("ics/v3/session").then().extract().response();
		System.out.println("Response Status" + response.statusCode());

		Assert.assertEquals(response.statusCode(), 201);

		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);
		String sessionId = js.getString("sessionId");

		return sessionId;

	}

	public List<String> getAccounts(String sessionId, String accessToken) {
		List<String> ibanList = null;
		String auth = "Bearer " + accessToken;
		RestAssured.baseURI = "https://sandbox.neonomics.io";

		Header header1 = new Header("Authorization", auth);
		Header header2 = new Header("Content-Type", "application/json");
		Header header3 = new Header("x-device-id", "example_id_for_quickstar");
		Header header4 = new Header("x-psu-ip-address", "109.74.179.3");
		Header header5 = new Header("x-session-id", sessionId);

		Headers headers = new Headers(header1, header2, header3, header4, header5);
		Response response = given().headers(headers).body("{\"bankId\": \"anVzdG9iYW5rLnYxSlVTVE5PS0s=\"}")
				.get("ics/v3/accounts").then().extract().response();

		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);
		String errorCode = js.getString("errorCode");
		if (errorCode.equals("1426")) {
			System.out.println("Call Consent API");
			return null;

		} else {
			List<AccountResponse> accountList = Arrays.asList(response.getBody().as(AccountResponse[].class));

			System.out.println("Size" + accountList.size());
			ibanList = accountList.stream().map(AccountResponse::getIban).collect(Collectors.toList());

			System.out.println(ibanList);
		}

		return ibanList;

	}

	public String getConsent(String sessionId, String accessToken) {
		String auth = "Bearer " + accessToken;
		RestAssured.baseURI = "https://sandbox.neonomics.io";

		Header header1 = new Header("Authorization", auth);
		Header header2 = new Header("Content-Type", "application/json");
		Header header3 = new Header("x-device-id", "example_id_for_quickstar");
		Header header4 = new Header("x-psu-ip-address", "109.74.179.3");

		String path = "ics/v3/consent/" + sessionId + "?scope=accounts";

		Headers headers = new Headers(header1, header2, header3, header4);
		Response response = given().headers(headers).get(path).then().extract().response();

		ConsentResponse consentResponse = response.getBody().as(ConsentResponse.class);
		Links[] links = consentResponse.getLinks();
		String href = null;
		for (Links links2 : links) {
			href = links2.getHref();
		}

		System.out.println("Href from consent Url is : " + href);

		return href;

	}

	public String noAccessToken() {

		String responses = "asish";

		Response response = RestAssured.given()
				.config(RestAssured.config().encoderConfig(
						EncoderConfig.encoderConfig().encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
				.contentType("application/x-www-form-urlencoded; charset=UTF-8")
				.formParam("grant_type", "client_credentials").formParam("client_id", "xyz")
				.formParam("client_secret", "466b203a-53ba-4630-b16c-e9743edbfe07")
				.post("/auth/realms/sandbox/protocol/openid-connect/token").then().extract().response();

		Assert.assertEquals(response.statusCode(), 200);
		return responses;

	}

	public String validateAccessToken() {

		/*
		 * client = "96d434cc-397b-4a70-a390-31d36eca8c44" secret =
		 * "466b203a-53ba-4630-b16c-e9743edbfe07"
		 */

		RestAssured.baseURI = "https://sandbox.neonomics.io";
		/*
		 * given().header("Content-Type", "application/x-www-form-urlencoded")
		 * .body("client_id", "fmngmfd")
		 */

		Response response = RestAssured.given()
				.config(RestAssured.config().encoderConfig(
						EncoderConfig.encoderConfig().encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
				.contentType("application/x-www-form-urlencoded; charset=UTF-8")
				.formParam("grant_type", "client_credentials").formParam("client_id", "xyz")
				.formParam("client_secret", "466b203a-53ba-4630-b16c-e9743edbfe07")
				.post("/auth/realms/sandbox/protocol/openid-connect/token").then().extract().response();

		Assert.assertEquals(response.statusCode(), 510);
		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);
		String message = js.getString("message");

		return message;

		//
	}

}
