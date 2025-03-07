package com.e2e.pages;

import java.io.IOException;
import java.util.HashMap;

public class FlightSelectionPage extends BasePage {

	private HashMap<String, String> locatorMap = new HashMap<>();
	private HashMap<String, String> infoMap = new HashMap<>();

	// Dynamic Locators
	String fareLocator1 = "//div[text()='";
	String fareLocator2 = "']//following::button[1]";
	String operatedFlightLocator1 = "//div[contains(text(),'0 stops')]//ancestor::div[contains(@class,'flight-card-layout')]//span[contains(text(),'";
	String operatedFlightLocator2 = "')]//ancestor::div[contains(@class,'flight-card-layout-left-section-container')]//following-sibling::div[contains(@class,'basic-flight-card-layout-right-section-container')]//i[contains(@aria-label,'fare list')]";

	// Constant Values

	final String classOption1 = "Economy";
	final String classOption2 = "Business";

	public FlightSelectionPage() {
		// Locators
		locatorMap.put("enterPassengerDetailsButton", "//span[contains(text(),'Enter passenger details')]");
		locatorMap.put("economyClassSelection", "//button [contains(@data-fare-family-group,'eco')][1]");
		locatorMap.put("businessClassSelection", "//button [contains(@data-fare-family-group,'business')][1]");
		locatorMap.put("ecoPremiumClassSelection", "//button [contains(@data-fare-family-group,'ecoPremium')][1]");
		locatorMap.put("departurePageTitle", "//*[@class='refx-title title']");
		locatorMap.put("returnPageTitle", "//*[@class='refx-title title']");

		locatorMap.put("homePageImage",
				"//img[contains(@alt,'back to homepage')] | //a[contains(@aria-label,'go to home page')]");

		locatorMap.put("rebookFlightCard", "//button[contains(@class,'flight-card-button')]");
		locatorMap.put("departureFlightHeader", "//div[contains(text(),'Please select your departure')]");
		locatorMap.put("returnFlightHeader", "//div[contains(text(),'Please select your return')]");

		// Info messages for all locators
		infoMap.put("enterPassengerDetailsButton", "Button to Proceed and Enter Passenger Details");
		infoMap.put("economyClassSelection", "Button to Select Economy Class");
		infoMap.put("businessClassSelection", "Button to Select Business Class");
		infoMap.put("ecoPremiumClassSelection", "Button to Select Eco Premium Class");
		infoMap.put("departurePageTitle", "Page Title in the page to select departure flight");
		infoMap.put("homePageImage", "Image Button to navigate to Home Page");

		infoMap.put("rebookFlightCard", "Flight Cards available for Rebooking");

		infoMap.put("departureFlightHeader", "Title header for selecting Departure Flight");
		infoMap.put("returnFlightHeader", "Title header for selecting Return Flight");

	}

	public void selectOutboundFlight(String outboundFlightOperatedby, String outboundFareType)
			throws InterruptedException, IOException {
		String operatedFlightZeroStop = null;
		try {
			waitForElementToBeVisible("departurePageTitle", pageVisibilityMaxTimeout);
			log.debug("Inside Flight Availability Page");

			// Class Selection
			if (outboundFareType.contains(classOption1)) {
				// Economy Class
				operatedFlightZeroStop = locatorMap.get("economyClassSelection");
			} else {
				if (outboundFareType.contains(classOption2)) {
					// Business Class
					operatedFlightZeroStop = locatorMap.get("businessClassSelection");
				} else {
					// Premium Economy
					operatedFlightZeroStop = locatorMap.get("ecoPremiumClassSelection");
				}
			}

			if (!isTheElementVisible("departureFlightHeader", elementVisibilityMinTimeout)) {
				// Re-booking Logic to select Flight
				operatedFlightZeroStop = locatorMap.get("rebookFlightCard");
			}

			for (int counter = 0; counter < 10; counter++) {
				boolean flightAvailableFlag = waitAndClickInElementArray(operatedFlightZeroStop, counter,
						elementVisibilityMaxTimeout);
				if (flightAvailableFlag) {
					log.info("Outbound Flight is found for the mentioned dates");
					break;
				} else {
					log.info("Outbound Flight not found for the mentioned dates");
				}
			}
		} catch (Exception e) {
			logAssert_Fail("Failed to Select Outbound Flight");
		}
	}

	public void fareSelection(String outboundFareType) throws InterruptedException, IOException {
		try {
			String fareSelection = fareLocator1 + outboundFareType + fareLocator2;
			waitAndClick(fareSelection);
		} catch (Exception e) {
			logAssert_Fail("Failed to Select Flight Fare");
		}
	}

	public void selectInboundFlight(String inboundFlightOperatedby, String inboundFareType)
			throws InterruptedException, IOException {
		String operatedFlightZeroStop = null;
		try {
			waitForElementToBeVisible("returnPageTitle", pageVisibilityMaxTimeout);
			log.debug("Inside Flight Availability Page");

			// Class Selection
			if (inboundFareType.contains(classOption1)) {
				// Economy Class
				operatedFlightZeroStop = locatorMap.get("economyClassSelection");
			} else {
				if (inboundFareType.contains(classOption2)) {
					// Business Class
					operatedFlightZeroStop = locatorMap.get("businessClassSelection");
				} else {
					// Premium Economy
					operatedFlightZeroStop = locatorMap.get("ecoPremiumClassSelection");
				}
			}

			if (!isTheElementVisible("returnFlightHeader", elementVisibilityMinTimeout)) {
				// Re-booking Logic to select Flight
				operatedFlightZeroStop = locatorMap.get("rebookFlightCard");
			}

			for (int counter = 0; counter < 10; counter++) {
				boolean flightAvailableFlag = waitAndClickInElementArray(operatedFlightZeroStop, counter,
						elementVisibilityMaxTimeout);
				if (flightAvailableFlag) {
					log.info("Inbound Flight is found for the mentioned dates");
					break;
				} else {
					log.info("Inbound Flight not found for the mentioned dates");
				}
			}

		} catch (Exception e) {
			logAssert_Fail("Failed to Select Inbound Flight");
		}
	}




	public void initiatePassengerDetailsRecording() throws InterruptedException, IOException {
		clickOnTheElement("enterPassengerDetailsButton");
	}

	public void clickToHomePage() throws InterruptedException, IOException {
		clickOnTheElement("homePageImage");
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
