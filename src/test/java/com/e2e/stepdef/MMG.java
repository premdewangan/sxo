package com.e2e.stepdef;

import com.e2e.config.Configuration;
import com.e2e.pages.AirEmPage;
import com.e2e.pages.BasePage;
import com.e2e.pages.MMGPage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.WaitForLoadStateOptions;
import com.microsoft.playwright.options.LoadState;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import static com.e2e.pages.MMGPage.email;
import static com.e2e.utils.Base64EncryptionUtil.readDecryptedData;
import static com.e2e.utils.TwoFA.authentication;
import static com.e2e.utils.TwoFA.smsCode;

public class MMG extends BasePage {
	MMGPage mmgPage = new MMGPage();


	@Given("User launches the MMG Application {string}")
	public void user_launches_the_mmg_application(String mamurl) {
		page = context.newPage();

		// Save information of current tenant under execution
		scenarioContext.setContext("Tenant_Name", mamurl);

		// Save information of Static Test Data source file to be used
		scenarioContext.setContext("Static_TestData_FileName", "mmg");

		String url;
		String env = System.getProperty("envName", Configuration.ENVIRONMENT);
		if ("MMG".equals(mamurl) && "PREPROD".equalsIgnoreCase(env)) {
			url = Configuration.MMG_PREPROD;
			log.info("url is: " + url);
			try {
				page.navigate(url);
				mmgPage.handle_Privacy_Settings_Page();
				takeExtraScreenshot();
			} catch (Exception e) {
				// Log an error if navigation fails
				Assert.fail("Error navigating to the MMG URL: " + e.getMessage());
			}
		} else {
			// Log a message if the URL is null
			Assert.fail("URL is null. Cannot navigate to a null URL.");
		}
	}

	@When("User register with new emailid and password  {string},{string}  and personal details {string},{string},{string},{string} and {string}")
	public void user_register_with_new_emailid_and_password_and_personal_details_and(String newEmailAddress,
																					 String password, String firstName, String surname, String dateofBirth, String postalAddress,
																					 String telephoneNumber) throws IOException, InterruptedException {

		mmgPage.clickMMGRegistrationLink();
		mmgPage.generateRandomEmail();
		mmgPage.enterUserCredentialsForMMG(newEmailAddress, password);
		mmgPage.clickToProceedEnrollment();
		mmgPage.enterEnrollmentDetailsInMMG(firstName, surname, dateofBirth, postalAddress, telephoneNumber);

	}

	@When("User login using OneID FFP SEN service card number and pin {string} and {string}")
	public void user_login_using_one_id_ffp_sen_service_card_number_and_pin_and(String SCN,
																				String Password) throws InterruptedException {
		String mmg_Login_SCN_Value = readDecryptedData(SCN);
		String mmg_Login_PIN_Value = readDecryptedData(Password);
		mmgPage.verifySCN();
		mmgPage.verifyAndEnterText("ffbLoginSCN", mmg_Login_SCN_Value);
		log.info("SCN entered successfully");
		mmgPage.clicks("mmgNextButton");
		mmgPage.verifyPin();
		mmgPage.verifyAndEnterText("scnPin", mmg_Login_PIN_Value);
		log.info("Password entered successfully");
		mmgPage.clicks("emailLoginSubmitButton");
		log.info("login button clicked successfully");
		mmgPage.verifyMmgAccountButtonVisibility();
		log.info("Login done successfully");
	}

	@When("User subscribe for Lufthansa Group Airlines under newsletter permission")
	public void userSubscribeForLufthansaGroupAirlinesUnderNewsletterPermission() throws InterruptedException {
		mmgPage.clicks("mmgAccountButton");
		mmgPage.clicks("mmgProfileButton");
		mmgPage.clicks("mmgEditButtonOffersAndCommunication");
		mmgPage.checkBoxHandling(("mmgPersonalisedCommunicationCheckBox"));
		mmgPage.clicks("saveChangesButton");
	}

	@When("edit the profile {string},and {string}")
	public void editTheProfileAnd(String areacode, String Phoneno) throws IOException, InterruptedException, AWTException {

		mmgPage.clicks("backToProfileButton");
		mmgPage.clicks("personalInfoEdit");
		mmgPage.clearText("addressTextBox");
		mmgPage.generateRandomAddress();
		log.info("Address is added");
		page.keyboard().press("Tab");
		mmgPage.clicks("addressSave");
		mmgPage.clicks("requestCodeButton");
		mmgPage.clicks("enterVerificationCodeButton");
		authentication();
		mmgPage.verifyAndEnterText("enterVerificationCodeButton", smsCode);
		log.info("OTP is entered");
		mmgPage.clicks("confirmButton");
		mmgPage.clicks("profileEmail");
		mmgPage.getNextEmail("profileEmail");
		mmgPage.clearText("profileEmail");
		mmgPage.verifyAndEnterText("profileEmail", email);
		log.info("Email id is entered");
		page.keyboard().press("Tab");
		mmgPage.clicks("emailSaveChanges");
		mmgPage.enterTelephoneNumber(areacode, Phoneno);
		log.info("Area code and Phone number is added");
		mmgPage.clicks("telephoneNumberSaveButton");
	}

	@When("user logout from MAM portal")
	public void userlogoutfromMAMportal() throws InterruptedException {

		mmgPage.clicks("accountButton");
		log.info("Account Button is clicked");
		mmgPage.clicks("logoutButton");
		log.info("Log out successful");

	}

	@And("User verifies Account is displayed in MAM homepage")
	public void User_verifies_Account_is_displayed_in_MAM_homepage() throws InterruptedException {
		mmgPage.verifyMmgAccountButtonVisibility();
		log.info("Account verified successfully");
		mmgPage.verifyMmgLoggedinButtonVisibility();
		log.info("Loggedin button verified successfully");
	}


	@And("User launches {string} in new tab in the same browser to verify if the User is logged in automatically and user name is present at the top right corner of home page for all tenants")
	public void User_launches_in_new_tab_in_the_same_browser_to_verify_if_the_User_is_logged_in_automatically_and_user_name_is_present_at_the_top_right_corner_of_home_page_for_all_tenants(String url) throws InterruptedException {


		// Save information of current tenant under execution
		scenarioContext.setContext("Tenant_Name", url);

		// Save information of Static Test Data source file to be used
		scenarioContext.setContext("Static_TestData_FileName", "desktop");
		String env = System.getProperty("envName", Configuration.ENVIRONMENT);
		if ("LH_DESKTOP_PREPROD".equals(url) && "PREPROD".equalsIgnoreCase(env)) {
			try {
				String[] urls = {
						Configuration.LH_DESKTOP_PREPROD,
						Configuration.OS_DESKTOP_PREPROD,
						Configuration.LX_DESKTOP_PREPROD,
						Configuration.SN_DESKTOP_PREPROD
				};
				for (String AiremURL : urls) {
					page = context.newPage();
					page.navigate(AiremURL);
					page.waitForLoadState();
					mmgPage.getPageTitle();
					takeScreenshot();
					log.info(AiremURL + "url Launched");
					AirEmPage airEmPage = new AirEmPage();
					airEmPage.clicksAiremPagePopUps();
					page.reload();
					log.info(AiremURL + "url Reloaded");
					airEmPage.clicksAiremPagePopUps();
					mmgPage.verifyAiremLoggedinButtonVisibility();
					takeScreenshot();
				}


			} catch (Exception e) {
				// Log an error if navigation fails
				Assert.fail("Error navigating to the Airem URL: " + e.getMessage());
			}
		} else {
			// Log a message if the URL is null
			Assert.fail("URL is null. Cannot navigate to a null URL.");
		}
	}

	@Then("Close the tab one by one for all tenants")
	public void Close_the_tab_one_by_one_for_all_tenants() throws InterruptedException {
		while (context.pages().size() >= 2) {
			List<Page> pages = context.pages();
			Page tabToClose = pages.get(pages.size() - 1);
			tabToClose.bringToFront();
			tabToClose.waitForLoadState(LoadState.DOMCONTENTLOADED);
			String tabToCloseTitle = tabToClose.title();
			log.info(tabToCloseTitle + " Tenant Closed Now");
			tabToClose.close();
		}
	}

	@Then("User booking is successful")
	public void User_booking_is_successful() throws InterruptedException {
		page.waitForTimeout(50000);
		mmgPage.getPageTitle();
	}
}
