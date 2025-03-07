package com.e2e.stepdef;

import com.e2e.pages.AKCIPage;
import com.e2e.pages.BasePage;
import com.e2e.pages.PPBPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AKCISteps extends BasePage {

	AKCIPage akciPage = new AKCIPage();  // Create an instance of PPBPage

	@When("User validates and adds AKCI")
	public void validateAndAddakci() {
		try {
			// Passing the correct field locator and value to the method
			akciPage.addAKCI();
		} catch (Exception e) {
			// Improved logging for better troubleshooting
			log.error("Error while validating and adding membership Partner Plus Benefits: " + e.getMessage());
		}
	}
}

//	@When("User verifies Partner Plus Benefits Popup visibility")
//	public void verifyPopupVisibility() throws InterruptedException {
//		try {
//			ppbPage.verifyPopupVisibility("popupLocator");
//		} catch (Exception e) {
//			// Log an error if navigation fails
//			log.info("Popup not visible " + e.getMessage());
//			logAssert_Fail("Popup not visible");
//		}
//	}
//
//		@Then("The membership details should be successfully added")
//		public void verifyMembershipAddedSuccessfully() {
//			// You can verify the success of the operation here or add additional checks
//			// This could be assertions or logging based on your framework
//		}
//
//	}
//
