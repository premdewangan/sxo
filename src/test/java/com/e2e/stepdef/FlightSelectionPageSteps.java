package com.e2e.stepdef;

import java.io.IOException;

import com.e2e.config.Configuration;
import com.e2e.pages.AirEmPage;
import com.e2e.pages.BasePage;
import com.e2e.pages.FlightSelectionPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FlightSelectionPageSteps extends BasePage {

	FlightSelectionPage flightSelection = new FlightSelectionPage();
	AirEmPage airEm = new AirEmPage();

	@When("User selects outbound flight {string} and fare type {string}")
	public void user_Select_Outbound_Flight(String outboundFlightOperatedby, String outboundFareType)
			throws IOException, InterruptedException {
		flightSelection.selectOutboundFlight(outboundFlightOperatedby, outboundFareType);
		flightSelection.fareSelection(outboundFareType);
	}

	@When("User selects inbound flight {string} and fare type {string}")
	public void user_Select_Inbound_Flight(String inboundFlightOperatedby, String inboundFareType)
			throws IOException, InterruptedException {
		flightSelection.selectInboundFlight(inboundFlightOperatedby, inboundFareType);
		flightSelection.fareSelection(inboundFareType);
//		flightSelection.initiatePassengerDetailsRecording();
	}



}
