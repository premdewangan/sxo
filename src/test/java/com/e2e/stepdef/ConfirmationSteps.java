package com.e2e.stepdef;

import com.e2e.pages.BasePage;
import com.e2e.pages.ConfirmationPage;
import com.e2e.pages.ShoppingCartPage;
import cucumber.api.java.en.Then;

import java.io.IOException;

public class ConfirmationSteps extends BasePage {

	ConfirmationPage confirmationSteps = new ConfirmationPage();

	@Then("User clicks print icon")
	public void user_clicksPrintIcon() throws InterruptedException, IOException {

		confirmationSteps.clicksPrintIcon();
	}
	@Then("User clicks Continue Booking Button")
	public void user_click_ContinueBookingButton() throws InterruptedException, IOException {

		confirmationSteps.ContinueBookingButton();
	}

}
