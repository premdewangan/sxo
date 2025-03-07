package com.e2e.stepdef;

import java.io.IOException;

import com.e2e.config.Configuration;
import com.e2e.pages.AirEmPage;
import com.e2e.pages.BasePage;
import com.e2e.pages.LoginPage;
import com.e2e.pages.PassengerDetailsPage;
import com.e2e.utils.ScenarioContext;
import static com.e2e.utils.Base64EncryptionUtil.*;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginPageSteps extends BasePage {

	LoginPage loginPage = new LoginPage();
	AirEmPage airEmforLogin = new AirEmPage();
	AirEmPage airEm = new AirEmPage();
	PassengerDetailsPage passengerDetails = new PassengerDetailsPage();

	@When("User logs in with Servicecard by Airem {string} and {string}")
	public void user_login_with_servicecard_byAirem(String scn, String scpin) throws IOException, InterruptedException {
		loginPage.loginWithServiceCardforAirem(scn, scpin);

	}

	@When("User does late login {string} and {string}")
	public void user_late_login_and(String userid, String password) throws IOException, InterruptedException {
		loginPage.initiateLateLogin();
		page.waitForTimeout(5000);
		airEm.clicksAiremPagePopUps();
		airEmforLogin.temporaryByPassFixforPrivacyIssue();
		loginPage.loginWithEmailID(userid, password);
		passengerDetails.clickConfirmPassengerDetailsButton();


	}

	@When("User log in with {string} and {string}")
	public void loginWithEmailID(String userid, String password) throws InterruptedException, IOException {
		loginPage.loginWithEmailID(userid, password);
	}
	@And("User verifies the name displayed in login page as {string} and {string}")
	public void verifyNameDisplayedOnLoginPage(String firstName, String lastName)
			throws InterruptedException, IOException {
		loginPage.verifyNameDisplayedOnLogin(firstName, lastName);
	}
	@When("User completes the enrollment via YOP Mail")
	public void completeEnrollmentByMail() throws InterruptedException, IOException {
		String mailURL = Configuration.YOP_MAIL;
		loginPage.naviagteToYOPMail(mailURL);
		loginPage.handleYOPPopUps();
		loginPage.searchYOPMailInbox();
		String activationLink = loginPage.extractYOPMailContent();
		loginPage.activateYOPMailLink(activationLink);
	}

	@And("User verfies enrollment successful message")
	public void verifyEnrollmentSuccessMsg() throws InterruptedException, IOException {
		loginPage.verifyEnrollmentSuccessfulMessage();
	}

	@Then("User logs in with newly enrolled credentials")
	public void loginWithNewEnrolledCredentials() throws InterruptedException, IOException {
		// loginPage.handleLoginPopups();

		String userid = scenarioContext.getContext("emailAddressGenerated");
		String password = scenarioContext.getContext("passwordGenerated");

		userid = encryptionData(userid);
		password = encryptionData(password);

		loginPage.loginWithEmailID(userid, password);
	}

//	@When("User verifies the name displayed in login page as {string} and {string}")
//	public void vverifyNameDisplayedOnLoginPage(String firstName, String lastName)
//			throws InterruptedException, IOException {
//		loginPage.verifyNameDisplayedOnLogin(firstName, lastName);
//	}

	@When("User verifies the intials displayed in avatar on login page as {string}")
	public void verifyNameDisplayedInitialsLoginPage(String initials) throws InterruptedException, IOException {
		loginPage.verifyInitialsDisplayedOnLogin(initials);
	}

	@When("User click logout")
	public void UserlogOut()throws InterruptedException, IOException  {
		loginPage.UserlogOut();
	}
}
