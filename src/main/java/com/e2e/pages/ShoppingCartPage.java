package com.e2e.pages;

import java.io.IOException;
import java.util.HashMap;

import com.e2e.utils.DateManipulator;

public class ShoppingCartPage extends BasePage {

	private HashMap<String, String> locatorMap = new HashMap<>();
	private HashMap<String, String> infoMap = new HashMap<>();

	public ShoppingCartPage()
	{
		// Locators
		locatorMap.put("itineraryPageHeader", "//h2[text()='Your Itinerary']");
		locatorMap.put("flySustainablyHeader", "//div[text()='Fly more sustainably']");
		locatorMap.put("co2PackageList",
				"//mat-card[contains(@class,'mat-mdc-card mdc-card co2-neutralisation-card-container')]//span[contains(text(),'Select package')]");
		locatorMap.put("addPetInCabinBtn", "//div[normalize-space()='Pet in Cabin']");
		locatorMap.put("confirmYourPetChoice", "//span[contains(text(),'Confirm your choice')]");
		locatorMap.put("activateOutboundPetChoiceDropdown", "(//span[contains(text(),'Please select')])[1]");
		locatorMap.put("activateInboundPetChoiceDropdown", "(//span[text()='Please select'])[2]");
		locatorMap.put("airportHealthDocChkBox", "(//input[@class='mdc-checkbox__native-control'])[1]");
		locatorMap.put("petTransportDeclarationForm", "(//input[@type='checkbox'])[2]");
		locatorMap.put("PAXexpand", "//*[@class=\"expand-icon refx-icon-chevron-down ng-star-inserted\"]");

		// Info messages for all locators
		infoMap.put("itineraryPageHeader", "Header displayed for Itinerary Page");
		infoMap.put("flySustainablyHeader", "Header displayed after selecting CO2 Package");
		infoMap.put("co2PackageList", "List of CO2 Packages available");
		infoMap.put("addPetInCabinBtn", "Pet In Cabin Button");
		infoMap.put("confirmYourPetChoice", "Confirm Your Pet Choice");
		infoMap.put("activateOutboundPetChoiceDropdown", "Activate Outbound Flight Pet Choice Dropdown");
		infoMap.put("activateInboundPetChoiceDropdown", "Activate Inbound Flight Pet Choice Dropdown");
		infoMap.put("airportHealthDocChkBox", "Pet Travel Health Obligation Checkbox");
		infoMap.put("petTransportDeclarationForm", "Pet Travel Form");

	}

	public void ClickPAXexpand() throws InterruptedException {
		try {
			waitForElementToBeVisible("PAXexpand", elementVisibilityMaxTimeout);
            waitAndClickOnTheElement("PAXexpand",1);
			takeScreenshot();
		} catch (Exception e) {
			logAssert_Fail("Failed to Click on Expand");
		}
	}


	public void selectCO2Packagae(String packageNumber) throws InterruptedException {
		try {
			waitForElementToBeVisible("itineraryPageHeader", elementVisibilityMaxTimeout);

			int packageNo = Integer.parseInt(packageNumber);
			String dynamicLoc = getLocatorMap().get("co2PackageList");
			waitAndClickInElementArray(dynamicLoc, packageNo, elementVisibilityMaxTimeout);
			waitForElementToBeVisible("flySustainablyHeader", elementVisibilityMaxTimeout);
		} catch (Exception e) {
			logAssert_Fail("Failed to Select CO2 Package");
		}
	}

	public void addPetInCartOneWayFlight(String outboundPetName) throws InterruptedException
	{
		try
		{
			waitAndClickOnTheElement("addPetInCabinBtn", elementVisibilityMaxTimeout);
			waitAndCheckForElementToBeEnabled(locatorMap.get("confirmYourPetChoice"));
			waitAndClickOnTheElement("activateOutboundPetChoiceDropdown", elementVisibilityMaxTimeout);
			String petLocator = "//span[contains(text(),\"" + outboundPetName + "\")]";
			waitAndCheckForElementToBeEnabled(petLocator);
			waitAndClick(petLocator);
			waitAndClickOnTheElement("airportHealthDocChkBox", elementVisibilityMaxTimeout);
			waitAndClickOnTheElement("petTransportDeclarationForm", elementVisibilityMaxTimeout);
			waitAndClickOnTheElement("confirmYourPetChoice", elementVisibilityMaxTimeout);
		}
		catch (Exception e)
		{
			logAssert_Fail("Failed to Select Pet in Outbound Flight Booking");
		}
	}

	public void addPetInCartRoundTripFlight(String outboundPetName, String inboundPetName) throws InterruptedException
	{
		try
		{
			//Add Pet in Outbound Flight
			waitAndClickOnTheElement("addPetInCabinBtn", elementVisibilityMaxTimeout);
			waitAndCheckForElementToBeEnabled(locatorMap.get("confirmYourPetChoice"));
			waitAndClickOnTheElement("activateOutboundPetChoiceDropdown", elementVisibilityMaxTimeout);
			String OutboundPetLocator = "//span[contains(text(),\"" + outboundPetName + "\")]";
			waitAndCheckForElementToBeEnabled(OutboundPetLocator);
			waitAndClick(OutboundPetLocator);

			//Add Pet in Inbound Flight
			waitAndClickOnTheElement("activateInboundPetChoiceDropdown", elementVisibilityMaxTimeout);
			String inboundPetLocator = "//span[normalize-space()=\"" + inboundPetName + "\"]";
			waitAndCheckForElementToBeEnabled(inboundPetLocator);
			waitAndClick(inboundPetLocator);
			waitAndClickOnTheElement("airportHealthDocChkBox", elementVisibilityMaxTimeout);
			waitAndClickOnTheElement("petTransportDeclarationForm", elementVisibilityMaxTimeout);
			waitAndClickOnTheElement("confirmYourPetChoice", elementVisibilityMaxTimeout);
		}
		catch (Exception e)
		{
			logAssert_Fail("Failed to Select Pet in Round Trip Flight");
		}

	}


	@Override
	public HashMap<String, String> getLocatorMap() {
		return locatorMap;
	}

	@Override
	public HashMap<String, String> getInfoMap() {
		return infoMap;
	}

}
