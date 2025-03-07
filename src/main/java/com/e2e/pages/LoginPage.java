package com.e2e.pages;

import static com.e2e.utils.Base64EncryptionUtil.decryption;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;

import com.microsoft.playwright.ElementHandle;
import com.e2e.utils.Base64EncryptionUtil;

public class LoginPage extends BasePage {

	private HashMap<String, String> locatorMap = new HashMap<>();
	private HashMap<String, String> infoMap = new HashMap<>();

	public LoginPage() {
		// Locators
		locatorMap.put("loginButton_link", "//a[contains(@class,'login-portlet-link btn-link btn-meta-nav-link')]");
		locatorMap.put("login_With_Scn_Text",
				"//*[contains(text(),'Please enter your service card number or User ID')]");
		locatorMap.put("loginScnInput", "//*[@id=\"id-loginStepOne-textfield\"]");
		locatorMap.put("loginNextButton", "//button[contains(@class,'travelid-login__continueButton')]");
		locatorMap.put("scnPin", "//input[@id='id-loginStepTwoPassword-textfield']");
		locatorMap.put("loginPerformButton", "//button[contains(@class,'travelid-login__loginButton')]");
		locatorMap.put("profileButton", "//*[@class='d-none d-lg-block float-left']");
		locatorMap.put("logoutButton", "//maui-link-button[.//span[text()='Logout']]");
		locatorMap.put("loginButton", "//span[text()=\"Log in\"]");

		locatorMap.put("lateLoginBtn", "//a[contains(@class,'late-login')]");
		locatorMap.put("loginAndRegisterAzureButton_lnk", "//span[text()='Login and Register Azure']");
		locatorMap.put("scnLoginAvtar", "//span[@id=\"lh-loginModule-name\"]");
		locatorMap.put("loginButton_lnk_General", "//span[contains(text(),'Login')]");

		locatorMap.put("acceptPopupbtn", "//button[@id='cm-acceptAll']");
		locatorMap.put("lateLoginClickEmailLoginBtn", "//span[contains(text(),'Log in with email address')]");
		locatorMap.put("emailID_input", "//*[@id=\"id-loginStepOne-textfield\"]");
		locatorMap.put("nextAfterEmailButton",
				"(//button[contains(@class,'travelid-button')]//span[contains(text(),'Next')])[1]");
		locatorMap.put("emailPassword", "//input[@id='id-loginStepTwoPassword-textfield']");
		locatorMap.put("loginButtonAfterPassword", "//span[contains(text(),'Log')]");

		locatorMap.put("yopMailConsentButton", "//button[@aria-label='Consent']");
		locatorMap.put("yopMailAcceptCookies", "//*[contains(text(),'Accept Recommended Cookies & Continue')]");
		locatorMap.put("yopEmail", "//*[@id='login']");
		locatorMap.put("yopMailSubmit", "//button[contains(@title,'Check Inbox')]");
		locatorMap.put("mailUrl", "//td[contains(@style,'important')]/span");
		locatorMap.put("mailActivationTxt",
				"//p[contains(text(),'Your Travel ID has now been activated and is ready to use.')]");
		locatorMap.put("loginSuccess",
				"//p[contains(text(),'Your Travel ID has now been activated and is ready to use.')]");
		locatorMap.put("loginDisplayName",
				"//*[@class='d-none d-lg-block float-left']");

		locatorMap.put("loginDisplayInitials", "(//*[contains(text(),'AS')])[1]");


		// Info messages for all locators
		infoMap.put("loginButton_link", "Link Button to Perform Login");
		infoMap.put("login_With_Scn_Text", "Text Displayed asking user to enter Login Credentials");
		infoMap.put("loginScnInput", "Username / Email / SCN Number Input Text Field");
		infoMap.put("loginNextButton", "Next Button to enter Password");
		infoMap.put("scnPin", "Input Text Field for Service Card PIN Number");
		infoMap.put("loginPerformButton", "Button to Perform Login Action");

		infoMap.put("lateLoginBtn", "Button to Perform Late Login");
		infoMap.put("loginAndRegisterAzureButton_lnk", "Button to Perform Login and Register Azure");
		infoMap.put("scnLoginAvtar", "Login avatar button");
		infoMap.put("loginButton_lnk_General", "Link to Login as User");

		infoMap.put("acceptPopupbtn", "Button to Accept All Cookies");
		infoMap.put("lateLoginClickEmailLoginBtn", "Button to select Email for Late Login");
		infoMap.put("emailID_input", "Username / Email / SCN Number Input Text Field");
		infoMap.put("nextAfterEmailButton", "Next Button to enter Password");
		infoMap.put("emailPassword", "Input Text Field for Password");
		infoMap.put("loginButtonAfterPassword", "Button to Perform Login Action");

		infoMap.put("yopMailConsentButton", "Button to give consent for YOP Mail");
		infoMap.put("yopMailAcceptCookies", "Button to accept cookies for YOP Mail");
		infoMap.put("yopEmail", "YOP Mail entered input box");
		infoMap.put("yopMailSubmit", "Submit button for YOP Mail");
		infoMap.put("mailUrl", "YOP Mail URL");
		infoMap.put("mailActivationTxt", "Activation Link text for YOP Mail");
		infoMap.put("loginSuccess", "Login is Success");
		infoMap.put("loginDisplayName", "Name Displayed of the User after Login");
		infoMap.put("loginDisplayInitials", "Initials Displayed of the User after Login");

	}

	public void loginWithServiceCardforAirem(String scn, String scpin) throws InterruptedException, IOException {
		try {

			String decodedScn = Base64EncryptionUtil.readDecryptedData(scn);
			String decodedPin = Base64EncryptionUtil.readDecryptedData(scpin);

			waitAndClickOnTheElement("loginButton_link", elementVisibilityMaxTimeout);
			waitAndClickOnTheElement("login_With_Scn_Text", elementVisibilityMaxTimeout);

			enterTextOnTheElement("loginScnInput", decodedScn);
			clickOnTheElement("loginNextButton");
			enterTextOnTheElement("scnPin", decodedPin);

			takeExtraScreenshot();

			clickOnTheElement("loginPerformButton");
			log.info("Login with Service Card is done through Airem");
		} catch (Exception e) {
			logAssert_Fail("Failed to Login with Service Card through Airem");
		}
	}

	public void initiateLateLogin() throws InterruptedException, IOException {
		try {

			waitForElementToBeVisible("lateLoginBtn", elementVisibilityMaxTimeout);
			waitAndCheckForElementToBeEnabled(locatorMap.get("lateLoginBtn"));

			if (page.isVisible(locatorMap.get("lateLoginBtn"))) {
				clickOnTheElement("lateLoginBtn");
			} else if (page.isVisible(locatorMap.get("loginAndRegisterAzureButton_lnk"))) {
				clickOnTheElement("loginAndRegisterAzureButton_lnk");
			} else if (page.isVisible(locatorMap.get("scnLoginAvtar"))) {
				clickOnTheElement("scnLoginAvtar");
			} else {
				clickOnTheElement("loginButton_lnk_General");
			}

		} catch (Exception e) {
			logAssert_Fail("Failed to to do Late Login");
		}

	}

	public void loginWithEmailID(String userid, String password) throws InterruptedException, IOException {
		try {

			if (isTheElementVisible("loginButton_link", elementVisibilityMinTimeout)) {
				waitAndClickOnTheElement("loginButton_link", elementVisibilityMaxTimeout);
			}

			String decodedUserId;
			String decodedPassword;
			try {
				decodedUserId = Base64EncryptionUtil.readDecryptedData(userid);
				decodedPassword = Base64EncryptionUtil.readDecryptedData(password);
			} catch (Exception e) {
				// When UserID and Password are generated at runtime during enrollment
				decodedUserId = decryption(userid);
				decodedPassword = decryption(password);
			}

			waitForElementToBeVisible("emailID_input", elementVisibilityMaxTimeout);
			enterTextOnTheElement("emailID_input", decodedUserId);
			clickOnTheElement("nextAfterEmailButton");
			log.info("Clicked next button");
			enterTextOnTheElement("emailPassword", decodedPassword);
			log.info("Entered password");
			takeExtraScreenshot();
			clickOnTheElement("loginButtonAfterPassword");
//			waitForElementToBeVisible("loginButtonAfterPassword", elementVisibilityMinTimeout);
			page.waitForTimeout(10000);
			log.info("User logged in");

		} catch (Exception e) {
			logAssert_Fail("Failed to Login with Email ID");
		}
     page.waitForTimeout(10);
	}
	public void Userlogin() throws InterruptedException, IOException {
		try {

			waitForElementToBeVisible("loginButton", elementVisibilityMaxTimeout);
			Thread.sleep(5000);
			takeScreenshot();
			clickOnTheElement("loginButton");
			log.info("Clicked on Login button");
			takeExtraScreenshot();

		} catch (Exception e) {
			logAssert_Fail("Failed to Logout");
		}

	}

	public void UserlogOut() throws InterruptedException, IOException {
		try {

			if (isTheElementVisible("profileButton", elementVisibilityMinTimeout)) {
				waitAndClickOnTheElement("profileButton", elementVisibilityMaxTimeout);
			}

			waitForElementToBeVisible("logoutButton", elementVisibilityMaxTimeout);
			Thread.sleep(5000);
			takeScreenshot();
			clickOnTheElement("logoutButton");
			log.info("Clicked Logout button");
			takeExtraScreenshot();

		} catch (Exception e) {
			logAssert_Fail("Failed to Logout");
		}

	}
	public void verifyNameDisplayedOnLogin(String firstName, String lastName) throws InterruptedException, IOException {
		page.waitForTimeout(15000);
		waitForElementToBeVisible("loginDisplayName", elementVisibilityMaxTimeout);
		String expectedNameToDisplay = firstName + " " + lastName;
		assertTextOnTheElement("loginDisplayName", expectedNameToDisplay);
	}

	public void verifyInitialsDisplayedOnLogin(String initials) throws InterruptedException, IOException {
		waitForElementToBeVisible("loginDisplayName", elementVisibilityMaxTimeout);
		assertTextOnTheElement("loginDisplayInitials", initials);
	}

	public void handleLoginPopups() throws InterruptedException, IOException {
		waitAndClickOnTheElement("acceptPopupbtn", elementVisibilityMaxTimeout);
	}

	public void naviagteToYOPMail(String url) throws IOException, InterruptedException {
		try {
			page.navigate(url);
		} catch (Exception e) {
			logAssert_Fail("Failed to Navigate to YOP Mail URL");
		}

	}

	public void handleYOPPopUps() throws IOException, InterruptedException {
		page.bringToFront();
		if (isTheElementVisible("yopMailConsentButton", elementVisibilityMinTimeout)) {
			clickOnTheElement("yopMailConsentButton");
			log.info("YOP Mail consent button clicked");
		}
		if (page.isVisible(locatorMap.get("yopMailAcceptCookies"))) {
			clickOnTheElement("yopMailAcceptCookies");
		}
		if (isTheElementVisible("yopMailConsentButton", elementVisibilityMinTimeout)) {
			clickOnTheElement("yopMailConsentButton");
			log.info("YOP Mail consent button clicked");
		}
	}

	public void searchYOPMailInbox() throws IOException, InterruptedException {
		try {
			HardWait(5);
			clickOnTheElement("yopEmail");
			String emailAddressGenerated = scenarioContext.getContext("emailAddressGenerated");
			enterTextOnTheElement("yopEmail", emailAddressGenerated);
			String HTMLContent = page.content();
			log.info("The HTML Content is :   " + HTMLContent);
			clickOnTheElement("yopMailSubmit");
			HardWait(5);
			String pageContent = page.content();
			log.info("Current Page Content :" + pageContent);
			takeExtraScreenshot();

			// To handle consent pop up during Pipeline execution
			if (isTheElementVisible("yopMailConsentButton", elementVisibilityMinTimeout)) {
				clickOnTheElement("yopMailConsentButton");
				log.info("YOP Mail consent button clicked");
			}
			log.info("checkpoint 8");
		} catch (Exception e) {
			logAssert_Fail("Failed to search mail in YOP Mail Inbox");
		}

	}

	public String extractYOPMailContent() throws IOException, InterruptedException {
		String mailLinkExtracted = null;
		// Constants
		final String startIndexPattern = "https:";
		final String endIndexPattern = "=0P";
		try {
			ElementHandle url;
			url = page.frame("ifmail").querySelector(locatorMap.get("mailUrl"));
			String mailText = url.textContent();
			log.info(mailText);
			int startIndex = mailText.indexOf(startIndexPattern);
			log.info(startIndex);
			int endIndex = mailText.lastIndexOf(endIndexPattern);
			log.info(endIndex);
			mailLinkExtracted = mailText.substring(startIndex, endIndex + 2);
			log.info(mailLinkExtracted + " is the link");

		} catch (Exception e) {
			logAssert_Fail("Failed to extract YOP Mail Content");
		}
		return mailLinkExtracted;
	}

	public void activateYOPMailLink(String mailLink) throws IOException, InterruptedException {
		try {
			page.navigate(mailLink);
			waitForElementToBeVisible("mailActivationTxt", elementVisibilityMaxTimeout);
			page.locator(locatorMap.get("mailActivationTxt")).isVisible();
			takeExtraScreenshot();
			log.info("Activation URL is Captured in console and launched");
		} catch (Exception e) {
			logAssert_Fail("Failed to activate YOP Mail Activation link");
		}
	}

	public void verifyEnrollmentSuccessfulMessage() throws IOException, InterruptedException {
		try {
			assertTextOnTheElement("mailActivationTxt", "Your Travel ID has now been activated and is ready to use.");
		} catch (Exception e) {
			logAssert_Fail("Failed to display Enrollment Successful message");
		}
	}
	public void verifyLoginSuccessfulMessage() throws IOException, InterruptedException {
		try {
			assertTextOnTheElement("mailActivationTxt", "Your Travel ID has now been activated and is ready to use.");
		} catch (Exception e) {
			logAssert_Fail("Failed to display Login Successful message");
		}
	}

	public void pressDeleteKey() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DELETE);
		robot.keyRelease(KeyEvent.VK_DELETE);
	}

	@Override
	public HashMap<String, String> getLocatorMap() {
		return locatorMap;
	}

	@Override
	public HashMap<String, String> getInfoMap() {
		return infoMap;
	}

}
