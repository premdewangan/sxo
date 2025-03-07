package com.e2e.stepdef;

import java.io.IOException;

import com.e2e.config.Configuration;
import com.e2e.pages.BasePage;
import com.e2e.pages.SeatSelectionPage;
import com.e2e.utils.ScenarioContext;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SeatSelectionPageSteps extends BasePage {

	SeatSelectionPage seatSelection = new SeatSelectionPage();

	@When("User clicks on select seat button")
	public void clickSelectSeat() throws InterruptedException, IOException {
		seatSelection.continueSeatSelection();
	}

	@When("User selects the outbound seat {string} and {string}")
	public void selectOutboundSeat(String seatType, String Passenger) throws InterruptedException, IOException {
		seatSelection.activateSeatMapLegend();
		seatSelection.selectOutboundSeat(seatType, Passenger);
	}

	@When("User selects the inbound seat {string} and {string}")
	public void selectInboundSeat(String seatType2, String Passenger) throws InterruptedException, IOException {
		seatSelection.activateSeatMapLegend();
		seatSelection.selectInboundSeat(seatType2, Passenger);
	}

}
