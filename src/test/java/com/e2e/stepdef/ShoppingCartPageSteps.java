package com.e2e.stepdef;

import java.io.IOException;

import com.e2e.config.Configuration;
import com.e2e.pages.BasePage;
import com.e2e.pages.ShoppingCartPage;
import com.e2e.pages.AirEmPage;
import com.e2e.pages.LoginPage;
import com.e2e.utils.ScenarioContext;
import com.e2e.utils.EnvironmentSelector;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ShoppingCartPageSteps extends BasePage {

	ShoppingCartPage shoppingCart = new ShoppingCartPage();

	@Then("User clicks PAX")
	public void Click_PAX() throws InterruptedException, IOException
	{
		shoppingCart.ClickPAXexpand();
	}

	@Then("User adds CO2 package {string}")
	public void user_AddsCO2Package(String packageNumber) throws InterruptedException, IOException
	{
		shoppingCart.selectCO2Packagae(packageNumber);
	}

	@When("User adds the pet in cabin in cart {string},{string}")
	public void userAddsThePetInCabinInCart(String outboundPetName, String inboundPetName) throws InterruptedException
	{
		shoppingCart.addPetInCartRoundTripFlight(outboundPetName, inboundPetName);
		log.info("User added the pet in Cabin Shopping Cart for round trip flight");

	}

	@When("User adds pet in cabin in cart one way trip flight {string}")
	public void userAddsPetInCabinInCartOneWayTripFlight(String outboundPetName) throws InterruptedException
	{
		shoppingCart.addPetInCartOneWayFlight(outboundPetName);
		log.info("User added the pet in Cabin Shopping Cart for one way trip flight");
	}
}
