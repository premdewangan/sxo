package com.e2e.stepdef;

import java.io.IOException;

import com.e2e.config.Configuration;
import com.e2e.pages.BasePage;
import com.e2e.pages.OnlineCheckinPage;
import com.e2e.utils.EnvironmentSelector;
import com.microsoft.playwright.Locator;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OnlineCheckinPageSteps extends BasePage {

	OnlineCheckinPage onlineCheckin = new OnlineCheckinPage();

	@When("User launches {string} and retrives the pnr in servicing flow {string}")
	public void user_launches_and_retrives_the_pnr_in_servicing_flow(String Serviceurl, String tenant)
			throws InterruptedException, IOException {

		// Get ONCE URL from Configuration based on Tenant
		tenant = scenarioContext.getContext("Tenant_Name");
		Serviceurl = EnvironmentSelector.envSelectorforOnceApplication(tenant);
		log.info("The ONCE Application URL is " + Serviceurl);
		onlineCheckin.enterPNRDetails(Serviceurl, tenant);
	}

	@When("User performs CheckIn with {string},{string},{string},{string}")
	public void user_checksin_withPNR(String email, String countryCode, String phoneNumber, String issueMode)
			throws InterruptedException, IOException {
		onlineCheckin.acceptIfBaggagePickUpNeeded();
		onlineCheckin.clickToConfirmPassengerDetails();
		onlineCheckin.enterContactDetails(email, countryCode, phoneNumber);
		onlineCheckin.acceptIfEmergencySeatSelected();
	}

	@When("User continues CheckIn with {string},{string},{string},{string}")
	public void user_continue_checksin_withPNR(String email, String countryCode, String phoneNumber, String issueMode)
			throws InterruptedException, IOException {
		onlineCheckin.continueWithNoDangerousItems();
		onlineCheckin.acceptHomeBaggageTagIfRequired();
		onlineCheckin.issueBoardingPass(issueMode);
	}

	@When("User performs CheckIn with {string},{string},{string},{string},{string},{string},{string} for Intercontinental Route to {string}")
	public void user_checksin_withPNR_Intercontinental(String email, String countryCode, String phoneNumber,
			String issueMode, String addressCity, String addressStreet, String addressZipCode, String country)
			throws InterruptedException, IOException {
		onlineCheckin.acceptIfBaggagePickUpNeeded();
		onlineCheckin.enterNationality(country);
		onlineCheckin.clickToConfirmPassengerDetails();
		onlineCheckin.enterDocumentDetails("documentExpiryDate", "documentNumber");
		onlineCheckin.enterAddressDetails(addressCity, addressStreet, addressZipCode, country);
		onlineCheckin.enterContactDetails(email, countryCode, phoneNumber);

		onlineCheckin.continueEntryRegulationsForCheckInProcess();
		onlineCheckin.continueDocumentCheckforCheckIn();

	}

	@When("User verifies check-in is successfull with boarding pass in {string}")
	public void user_verify_successful_checkin(String issueMode) throws InterruptedException, IOException {
		onlineCheckin.verifyBoardingPassIssueMessage(issueMode);
	}

	@When("User performs upgrade to {string} for the route {string} to {string}")
	public void user_performUpgrade(String upgradeOption, String origin, String destination)
			throws InterruptedException, IOException {
		onlineCheckin.startUpgradeProcess(upgradeOption);
	}

	@When("User verifies upgrade to {string} for the route {string} to {string}")
	public void user_verifyUpgrade(String upgradeOption, String origin, String destination)
			throws InterruptedException, IOException {
		onlineCheckin.verifyUpgradationProcess(origin, destination, upgradeOption);
		onlineCheckin.continueCheckinAfterUpgradeVerification();
	}

	@Then("User clicks to Change Seat during Checkin Process")
	public void user_selectsSeatAdditionalServices() throws InterruptedException, IOException {
		onlineCheckin.changeSeatButton();
	}

	@Then("User skips additional services during CheckIn Process")
	public void user_skipsAdditionalServices() throws InterruptedException, IOException {
		onlineCheckin.skipAdditionalServices();
	}

	@When("User continues CheckIn process for Intercontinental Route in {string}")
	public void user_continueCheckinProcess(String issueMode) throws InterruptedException, IOException {
		onlineCheckin.continueWithNoDangerousItems();
		onlineCheckin.acceptHomeBaggageTagIfRequired();
		onlineCheckin.issueBoardingPass(issueMode);
	}

}
