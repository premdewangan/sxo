package com.e2e.stepdef;

import java.io.IOException;

import com.e2e.config.Configuration;
import com.e2e.pages.BasePage;
import com.e2e.pages.AirEmPage;
import com.e2e.pages.LoginPage;
import com.e2e.utils.ScenarioContext;
import com.e2e.utils.EnvironmentSelector;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AirEmPageSteps extends BasePage {

	AirEmPage airEm = new AirEmPage();
	LoginPage loginPage = new LoginPage();

	@When("User searches for {string},{string},{string},{string},{string},{string} and {string}")
	public void user_Search_For_Flight(String triptype, String origin, String destination, String cabin,
			String OutboundDate, String InboundDate, String Passenger) throws IOException, InterruptedException {

//		airEm.clicksAiremPagePopUps();

		airEm.searchForFlights(triptype, origin, destination, OutboundDate, InboundDate, cabin, Passenger);
	}

	@Given("User launches {string}")
	public void user_launches_url_(String tenant) throws IOException, InterruptedException {
		page = browser.newPage();

		// Save information of current tenant under execution
		scenarioContext.setContext("Tenant_Name", tenant);

		// Save information of Static Test Data source file to be used
		scenarioContext.setContext("Static_TestData_FileName", "desktop");

		final String url = EnvironmentSelector.envSelectorForBaseApplication(tenant);

		// Save information about the environment currently being executed on
		String currentEnv = System.getProperty("envName", Configuration.ENVIRONMENT);
		scenarioContext.setContext("Current_Env", currentEnv);

		log.info("url is: " + url);
		if (url != null) {
			try {
				// Navigate to the determined URL
				page.navigate(url);

			} catch (Exception e) {
				// Log an error if navigation fails
				log.info("Error navigating to the AirEM URL: " + e.getMessage());
				logAssert_Fail("Error navigating to the AirEM URL");
			}
		} else {
			// Log a message if the URL is null
			log.info("URL is null. Cannot navigate to a null URL.");
			logAssert_Fail("URL is null. Cannot navigate to a null URL.");
		}
// Find the Agree button and click it
//		page.click("//button[text()='Agree']");

// Wait for 5 seconds
		page.waitForTimeout(5000);
		airEm.clicksAiremPagePopUps();
	}

	@When("User selects the My profile window")
	public void selectUserProfile() throws InterruptedException, IOException {
		loginPage.initiateLateLogin();
		airEm.selectMyProfile();
	}

	@Then("User retrieves the generated PNR details through my booking option")
	public void user_retrievesPNR_myBooking() throws IOException, InterruptedException {
		airEm.retrieveGeneratedPNRMyBooking();
	}

	@Then("User adds the Booking Details through my booking option")
	public void user_addsBookingDetails_myBooking() throws IOException, InterruptedException {
		airEm.addDetailsInMyBookingList();
		airEm.showAllBookingListCreated();
	}

	@When("User navigates to flight status page")
	public void user_Navigate_To_FlightStatus() throws InterruptedException, IOException {
		airEm.navigateToFlightStatusSearch();
	}

	@When("User selects the {string} and enter {string}")
	public void user_Select_FlightNumber(String airline, String flightNumber) throws InterruptedException, IOException {
		airEm.enterFlightStatusSearchDetails(airline, flightNumber);
	}

	@And("User clicks on search button for flight status details")
	public void user_Searches_FlightNumber() throws InterruptedException, IOException {
		airEm.clickSearchFlightStatus();
	}

	@Then("User retrieves the generated PNR details and intiate CheckIn from Home Page")
	public void user_checkin_fromHomePage() throws IOException, InterruptedException {
		airEm.performCheckInFromHomePage();
	}

	@When("User searches for {string} in Home Page")
	public void user_searchIn_fromHomePage(String searchOption) throws IOException, InterruptedException {
		airEm.searchInFooterSearchBar(searchOption);
	}

	@Then("User verifies baggage page is displayed after home page search")
	public void user_verifysearch_fromHomePage_Baggage() throws IOException, InterruptedException {
		airEm.verifyBaggagePageHeader();
	}

	@Then("User verifies lounge page is displayed after home page search")
	public void user_verifysearch_fromHomePage_Lounge() throws IOException, InterruptedException {
		airEm.verifyLoungePageHeader();
	}

	@Then("User navigated to Flights and Hotel tab")
	public void user_verify_FlightandHotelTab() throws IOException, InterruptedException {
		airEm.verifyFlightandHotelTab();
	}

	@When("User clicks {string} tab and {string} link inside Home Page")
	public void user_clicksTabandLink_fromHomePage(String searchOptionTab, String searchOptionLink)
			throws IOException, InterruptedException {
		airEm.clickHomePageHeaderMenu(searchOptionTab);
		airEm.clickLinkText(searchOptionLink);
	}

	@Then("User verifies link with the text {string} is present in page")
	public void user_verify_LinkText(String expectedLinkText) throws IOException, InterruptedException {
		airEm.verifyLinkText(expectedLinkText);
	}

	@Then("User clicks {string} tab on the footer link inside Home Page")
	public void user_click_FooterMenu_HomePage(String searchTabName) throws IOException, InterruptedException {
		airEm.clickHomePageFooterMenu(searchTabName);
	}

	@Then("User clicks {string} tab on the header link inside Home Page")
	public void user_click_HeaderMenu_HomePage(String searchTabName) throws IOException, InterruptedException {
		airEm.clickHomePageHeaderMenu(searchTabName);
	}
	@When("User navigates to Home Page")
	public void user_Navigates_ToHomePage() throws IOException, InterruptedException {
		airEm.clickToHomePage();
		airEm.closeLoginFlag();
//		airEm.clicksAiremPagePopUps();
	}

	@When("User enters {string} details and {string}")
	public void userEntersDetails(String PNR, String Lastname) throws IOException, InterruptedException {
		airEm.enterPNRandlastname(PNR,Lastname);
	}
}
