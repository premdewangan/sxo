package com.e2e.stepdef;

import java.io.IOException;

import com.e2e.config.Configuration;
import com.e2e.pages.FifteenBelowPage;
import com.e2e.pages.BasePage;

import com.e2e.utils.Base64EncryptionUtil;
import com.e2e.utils.ScenarioContext;
import static com.e2e.utils.Base64EncryptionUtil.*;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FifteenBelowPageSteps extends BasePage {

	FifteenBelowPage fifteenBelowPage = new FifteenBelowPage();

	@When("User completes the enrollment via FifteenBelow")
	public void completeEnrollmentByFifteenBelow() throws InterruptedException, IOException {
		String mailURL = Configuration.FIFTEEN_BELOW;
		fifteenBelowPage.naviagteToFifteenBelowMail(mailURL);

		// Save information of Static Test Data source file to be used
		scenarioContext.setContext("Static_TestData_FileName", "desktop");

		fifteenBelowPage.loginToFifteenBelow();
		fifteenBelowPage.selectTenant("LX-TST");
		fifteenBelowPage.selectMenu("Transactional History");

		String emailAddressGenerated = scenarioContext.getContext("emailAddressGenerated");
		fifteenBelowPage.searchEmailInbox(emailAddressGenerated);

		fifteenBelowPage.viewEmailContent();
		fifteenBelowPage.activateEmailID();

	}

}
