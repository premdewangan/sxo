package com.e2e.stepdef;

import java.io.IOException;

import com.e2e.config.Configuration;
import com.e2e.pages.BasePage;
import com.e2e.pages.ChatbotPage;
import com.e2e.pages.GoodForTrainPage;
import com.e2e.utils.ScenarioContext;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GoodForTrainServiceSteps extends BasePage {

	GoodForTrainPage goodfortrain = new GoodForTrainPage();
	ChatbotPage chatbot = new ChatbotPage();
   
	@Given("User launches GoodForTrain url {string}")
	public void user_launches_GoodForTrain_url(String tenant) throws IOException, InterruptedException {
		page = browser.newPage();
		String url = null;
		String env = System.getProperty("envName", Configuration.ENVIRONMENT);
		
		// Save information of current tenant under execution
         scenarioContext.setContext("Tenant_Name", tenant);

		// Save information of Dynamic Test Data source file to be used
         scenarioContext.setContext("Dynamic_TestData_FileName", "desktop");

		switch (tenant) {
		case "LH": {
			switch (env) {
			case "PREPROD": {
				url = Configuration.LH_GOODFORTRAIN_PREPROD;
				break;
			}
			case "INT": {
				url = Configuration.LH_GOODFORTRAIN_INT;
				break;
			}
			}
			break;
		}
		}
		
		log.info("url is: " + url);
		
		if (url != null) {
			try {
				// Navigate to the determined URL
				page.navigate(url);
				// Handle pop-ups that appear
				goodfortrain.clicks("acceptPopupbtn");
			} catch (Exception e) {
				// Log an error if navigation fails
				System.err.println("Error navigating to the GoodForTrain URL: " + e.getMessage());
			}
		} else {
			// Log a message if the URL is null
			System.err.println("URL is null. Cannot navigate to a null URL.");
		}
	}
	
	@When("User navigates to GoodForTrain page with PNR and lastname {string},{string} and {string}")
	public void user_navigates_to_GoodForTrain_page_with_pnr_and_lastname_and(String firstname
			, String lastname,
			String tenant) throws IOException, InterruptedException {
		goodfortrain.retrievePNRDetailsFromGoodForTrain(firstname, lastname);
	}
	
	@And("User Selects Flight and Adds Email for the QR Code {string}")
	public void user_Selects_Flight_And_Adds_Email_For_GoodForTrain(String email) throws IOException, InterruptedException{
		goodfortrain.selectFlightsGoodForTrain(email);
		goodfortrain.addEmailGoodForTrain(email);
	}
	
	@Then("User verify the Voucher code and QR Code")
	public void user_Verifies_Voucher_And_QR() throws IOException, InterruptedException{
		goodfortrain.verifyVoucherAndQR();
	}	
}
