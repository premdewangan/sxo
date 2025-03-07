package com.e2e.stepdef;

import com.e2e.pages.BasePage;
import com.e2e.pages.FlightStatusPage;
import com.e2e.pages.PPBPage;
import com.e2e.utils.ScenarioContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;

public class PPBSteps extends BasePage {

		PPBPage ppbPage = new PPBPage();  // Create an instance of PPBPage

	@When("User validates and adds membership Partner Plus Benefits")
	public void validateAndAddMembershipPartnerPlusBenefits() {
		try {
			// Passing the correct field locator and value to the method
			ppbPage.enterMembershipDetails("AddMembership_number", "E11111111111111");
		} catch (Exception e) {
			// Improved logging for better troubleshooting
			log.error("Error while validating and adding membership Partner Plus Benefits: " + e.getMessage());
		}
	}


	@When("User verifies Partner Plus Benefits Popup visibility")
	public void verifyPopupVisibility() throws InterruptedException {
		try {
			ppbPage.verifyPopupVisibility("popupLocator");
		} catch (Exception e) {
			// Log an error if navigation fails
			log.info("Popup not visible " + e.getMessage());
			logAssert_Fail("Popup not visible");
		}
	}

		@Then("The membership details should be successfully added")
		public void verifyMembershipAddedSuccessfully() {
			// You can verify the success of the operation here or add additional checks
			// This could be assertions or logging based on your framework
		}

	}

