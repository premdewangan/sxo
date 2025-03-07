package com.e2e.stepdef;

import java.io.IOException;
import com.e2e.pages.BasePage;
import com.e2e.pages.AdditionalServicesPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AdditionalServicesSteps extends BasePage {

	AdditionalServicesPage additionalServices = new AdditionalServicesPage();

	@Then("User verifies Airport Parking Widget")
	public void verify_airport_parking_widget() throws InterruptedException, IOException {
		additionalServices.scrollWidget();
		additionalServices.activateAirportParkingWidget();
		additionalServices.handleAirportParkingWidget();
		additionalServices.verifyAirportParkingWidget();
		additionalServices.closePopUp();
	}

	@Then("User validates Lounge teaser should be visible to the user")
	public void validate_lounge_teaser() throws InterruptedException, IOException {
		additionalServices.scrollWidget();
		additionalServices.activateLoungeTeaserWidget();
		additionalServices.handleLoungeTeaserWidget();
		additionalServices.verifyLoungeTeaser();
		additionalServices.closePopUp();
	}

	@When("User verifies insurance teaser and add insurance")
	public void addAndVerifyInsuranceStep() throws InterruptedException, IOException {
		additionalServices.addInsurance();
		additionalServices.verifyInsurance();
	}
}
