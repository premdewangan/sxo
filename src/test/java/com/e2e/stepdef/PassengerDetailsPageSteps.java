package com.e2e.stepdef;

import java.io.IOException;

import com.e2e.config.Configuration;
import com.e2e.pages.FlightSelectionPage;
import com.e2e.pages.BasePage;
import com.e2e.pages.PassengerDetailsPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PassengerDetailsPageSteps extends BasePage {

	FlightSelectionPage flightSelection = new FlightSelectionPage();
	PassengerDetailsPage passengerDetails = new PassengerDetailsPage();

	@When("User enters passenger details as logged in customer {string},{string},{string},{string},{string},{string},{string},{string} and {string}")
	public void enterPassengerDetailsLoginProfile(String Passenger, String title, String firstname, String lastname,
			String dateofbirth, String gender, String emailid, String countrycode, String phoneno)
			throws InterruptedException, IOException {

		passengerDetails.clickConfirmPassengerDetailsButton();

	}

	@When("User enters passenger details {string},{string},{string},{string},{string},{string},{string} and {string}")
	public void userEntersPassengerDetailsWithoutLogin(String title, String firstname, String lastname,
			String dateofbirth, String gender, String emailid, String countrycode, String phoneno)
			throws InterruptedException, IOException {

		passengerDetails.enterPassengerDetails(title, firstname, lastname, dateofbirth, gender, emailid, countrycode,
				phoneno);
		passengerDetails.clickConfirmPassengerDetailsButton();

	}

}
