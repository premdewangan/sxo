package com.e2e.stepdef;

import com.e2e.pages.BaggageSelectionPage;
import com.e2e.pages.BasePage;
import cucumber.api.java.en.When;

import java.io.IOException;

public class BaggageSelectionPageSteps extends BasePage {

	BaggageSelectionPage baggageSelection = new BaggageSelectionPage();

	@When("User adds additional baggage {string},{string} and {string}")
	public void addAdditionalBaggage(String executionmode, String tripType, String Passenger)
			throws IOException, InterruptedException
	{
		baggageSelection.addAdditionalCheckedBagForRoundTrip(executionmode, tripType, Passenger);
		baggageSelection.confirmBaggageAddition();
		log.info("User added the additional checked bag in round trip flights");
	}

	@When("User adds the outbound extra baggage in cart {string} and {string}")
	public void userAddsTheOutboundExtraBaggageInCartAnd(String bagType, String Passenger) throws IOException, InterruptedException
	{
		baggageSelection.activateExtraBaggage();
		baggageSelection.addOutboundExtraBaggage(bagType, Passenger);
		baggageSelection.confirmBaggageAddition();
		log.info("User added the extra baggage in cart for outbound flight booking");
	}

	@When("User adds the outbound extra baggage in manage booking {string} and {string}")
	public void userAddsTheOutboundExtraBaggageInManageBookingAnd(String bagType, String Passenger) throws IOException, InterruptedException
	{
		baggageSelection.activateExtraBaggage();
		baggageSelection.addOutboundExtraBaggage(bagType, Passenger);
		baggageSelection.confirmBaggageAddition();
		log.info("User added the extra baggage in manage booking for outbound flight");
	}
}
