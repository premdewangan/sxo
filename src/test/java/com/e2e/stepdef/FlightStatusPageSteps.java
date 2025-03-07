package com.e2e.stepdef;

import java.io.IOException;

import com.e2e.pages.BasePage;
import com.e2e.pages.FlightStatusPage;
import com.e2e.utils.ScenarioContext;

import cucumber.api.java.en.When;

public class FlightStatusPageSteps extends BasePage {

	FlightStatusPage flightStatus = new FlightStatusPage();
	ScenarioContext scenarioContext = ScenarioContext.Singleton();

	@When("User verifies the {string},{string},{string},{string} and {string} in results page")
	public void user_Verify_FlightStatusPage(String airline, String flightNumber, String date, String origin,
			String destination) throws InterruptedException, IOException {

		flightStatus.verifyFlightNumber(airline, flightNumber);
		flightStatus.verifyDate(date);
		flightStatus.verifyOrigin(origin);
		flightStatus.verifyDestination(destination);
		takeExtraScreenshot();

	}
	@When("User clicks enter passenger button")
	public void user_clicks_enter_passenger_button() throws InterruptedException, IOException {
//		flightStatus.verifyFlightNumber(airline, flightNumber);
//		flightStatus.verifyDate(OutboundDate);
//		flightStatus.verifyOrigin(origin);
//		flightStatus.verifyDestination(destination);
		flightStatus.enterPassengerDetailsButton();
//		flightStatus.takeScreenshot();
//		flightStatus.takeExtraScreenshot();
	}

	@When("User verifies destination Image on the page")
	public void verifyDestinationImage() throws InterruptedException {
    flightStatus.verifyDestinationImage();

	}
	@When("User clicks share link icon")
	public void shareLink() throws InterruptedException {
//		flightStatus.cross();
		flightStatus.shareLink();
		flightStatus.copy();
//		flightStatus.cross();
	}

	@When("User enable TTT on the page")
	public void user_enable_TTT_on_the_page() throws InterruptedException {
		flightStatus.enableTTT();

	}
}
