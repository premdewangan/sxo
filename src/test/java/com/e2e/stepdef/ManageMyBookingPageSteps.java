package com.e2e.stepdef;

import com.e2e.pages.BasePage;
import com.e2e.pages.LoginPage;
import com.e2e.pages.ManageMyBookingPage;
import com.e2e.utils.EnvironmentSelector;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;

public class ManageMyBookingPageSteps extends BasePage {

	ManageMyBookingPage manageMyBooking = new ManageMyBookingPage();
	LoginPage loginPage = new LoginPage();

	@Given("User launches Manage My Booking {string}")
	public void user_launches_url(String tenant) throws IOException, InterruptedException {
		page = browser.newPage();
		String url = null;

		// Save information of current tenant under execution
		scenarioContext.setContext("Tenant_Name", tenant);

		// Save information of Static Test Data source file to be used
		scenarioContext.setContext("Static_TestData_FileName", "desktop");

		// Save information of Dynamic Test Data source file to be used
		scenarioContext.setContext("Dynamic_TestData_FileName", "desktop");

		// Get MMB URL from Configuration based on Tenant
		tenant = scenarioContext.getContext("Tenant_Name");
		url = EnvironmentSelector.envSelectorforMMBApplication(tenant);
		log.info("The MMB Application URL is " + url);

		log.info("url is: " + url);
		if (url != null) {
			try {
				// Navigate to the determined URL
				page.navigate(url);
				// Handle pop-ups that appear
				takeExtraScreenshot();
			} catch (Exception e) {
				// Log an error if navigation fails
				System.err.println("Error navigating to the Manage My Booking URL: " + e.getMessage());
				logAssert_Fail("Error navigating to the Manage My Booking URL");
			}
		} else {
			// Log a message if the URL is null
			System.err.println("URL is null. Cannot navigate to a null URL.");
			logAssert_Fail("URL is null. Cannot navigate to a null URL.");
		}
	}

	@When("User navigates to MMB page with PNR and lastname {string},{string} and {string}")
	public void user_navigates_to_mmb_page_with_pnr_and_lastname_and(String PNR, String lastname, String tenant)
			throws IOException, InterruptedException
	{
		loginPage.handleLoginPopups();
		manageMyBooking.retrievePNRDetailsFromMMB(PNR, lastname, tenant);
	}

	@When("User upgrades the Inbound Flight to {string}")
	public void user_upgrades_the_InBoundflight(String upgradeCabin) throws IOException, InterruptedException
	{
		manageMyBooking.upgradeInboundFlight(upgradeCabin);
	}

	@When("User upgrades the outbound flight to {string}")
	public void userUpgradesTheOutboundFlightTo(String upgradeCabin) throws IOException, InterruptedException
	{
		manageMyBooking.upgradeOutboundFlight(upgradeCabin);
		log.info("User has upgraded the flight to upper cabin class");
	}

	@When("User upgrades the Outbound Flight {string} and {string}")
	public void user_upgrades_the_OutBoundflight(String executionmode, String upgradeCabin)
			throws IOException, InterruptedException
	{
		// Code development in Progress
		logAssert_Fail("The implementation of step definition is in progress !");
	}

	@When("User Rebooks the flights from the proposed Flights")
	public void user_rebook_the_flights_from_the_proposed_flights() throws IOException, InterruptedException
	{
		manageMyBooking.rebookFromProposedFlights();
	}

	@Then("User verifies the rebooking is successful")
	public void verify_the_rebooking_is_successful() throws IOException, InterruptedException
	{
		manageMyBooking.verifyRebookingSuccessful();
	}

	@When("User performs refund {string}")
	public void refundFlight(String executionmode) throws IOException, InterruptedException
	{
		manageMyBooking.performRefundFlight(executionmode);
	}

	@Then("User validates refund {string}")
	public void validateRefund(String executionmode) throws IOException, InterruptedException
	{
		manageMyBooking.performFlightRefundValidation(executionmode);
	}

	@When("User rebooks {string},{string} and {string}")
	public void user_rebook_and(String Rebookorigin, String Rebookdestination, String RebookDate)
			throws IOException, InterruptedException
	{
		manageMyBooking.performRebooking(Rebookorigin, Rebookdestination, RebookDate);
	}

	@And("User adds Rail and Fly Widget")
	public void addRailAndFlyWidget() throws IOException, InterruptedException
	{
		manageMyBooking.addRailandFlyWidgetforUser();
	}

	@When("User adds the special meal in one way trip flight {string}")
	public void userAddsTheSpecialMealInOneWayTripFlight(String outboundSpecialMeal) throws InterruptedException
	{
		manageMyBooking.addSpecialMealOneWayTrip(outboundSpecialMeal);
		log.info("User has added the special meal for one way trip flight");
	}

	@When("User adds pet in cabin in one way trip flight {string}")
	public void userAddsPetInCabinInOneWayTripFlight(String outboundPetName) throws InterruptedException
	{
		manageMyBooking.addPetInOneWayFlight(outboundPetName);
		log.info("User added the pet in Cabin for one way trip flight");
	}

	@When("User adds the special meals in round trip {string},{string}")
	public void userAddsTheSpecialMealsInRoundTrip(String outboundSpecialMeal, String inboundSpecialMeal) throws InterruptedException
	{
		manageMyBooking.addSpecialMealRoundTrip(outboundSpecialMeal,inboundSpecialMeal);
		log.info("User has added the special meals for round trip flight");
	}

	@When("User adds the pet in cabin in round trip flight {string},{string}")
	public void userAddsThePetInCabinInRoundTripFlight(String outboundPetName, String inboundPetName) throws InterruptedException
	{
		manageMyBooking.addPetInRoundTripFlight(outboundPetName,inboundPetName);
		log.info("User added the pet in Cabin for round trip flight");
	}
	@Then("User clicks add Pet Button")
	public void addPetInCartOneWayConfirm() throws InterruptedException, IOException {

		manageMyBooking.addPetInCartOneWayConfirm("outboundPetName");
	}
}
