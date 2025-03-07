package com.e2e.pages;

import java.io.IOException;
import java.util.HashMap;

import com.e2e.utils.DateManipulator;
import com.microsoft.playwright.Locator;

public class FlightStatusPage extends BasePage {

	private HashMap<String, String> locatorMap = new HashMap<>();
	private HashMap<String, String> infoMap = new HashMap<>();

	// Constants
	final String locFlightNumber1 = "//maui-flight-number[@carrier='";
	final String locFlightNumber2 = "' and @number='";

	public FlightStatusPage() {
		// Locators
		locatorMap.put("flightNumber", "//span[@class='flight-number']");
		locatorMap.put("flightDate", "//*[contains(@class,'flightdate')]");
		locatorMap.put("origin", "//span[@class='refx-body-1 bound-departure-airport-code']");
		locatorMap.put("destination", "//span[@class='refx-body-1 bound-arrival-airport-code']");
		locatorMap.put("destinationImage", "//picture[@class='image-background-title']");
        locatorMap.put("ttt", "//*[@class='mdc-switch__handle']");
		locatorMap.put("share", "//button[@class='share ng-star-inserted']");
		locatorMap.put("copy", "//button[@class='copy-to-clipboard']");
		locatorMap.put("cross", "//button[@class='refx-icon-cross']");

        locatorMap.put("shadowHostFlightStatusTab", "//*[@id=\"maui-tab-control\"]//div");
		locatorMap.put("airportTab", "//*[@id=\"maui-tab-control-tab-m13jyo0m2osjry1a1iy-2\"]");
		locatorMap.put("enterPassengerDetailsButton", "//span[contains(text(),'Enter passenger details')]");
		// Info messages for all locators
		infoMap.put("flightNumber", "Flight Number Displayed");
		infoMap.put("flightDate", "Flight Date Displayed");
		infoMap.put("origin", "Origin Airport Code Displayed");
		infoMap.put("destination", "Destination Airport Code Displayed");
		infoMap.put("destinationImage", "Destination Image Displayed");
		infoMap.put("enterPassengerDetailsButton", "Button to Proceed and Enter Passenger Details");
		infoMap.put("shadowHostFlightStatusTab", "Shadow Host for selecting Flight Status Tab Options");
		infoMap.put("airportTab", "Tab to select Flight status through Airport");

	}

	public void verifyFlightNumber(String airline, String flightNumber) throws InterruptedException {
		try {
			String dynamicShadowLocatorFlightNumber = locFlightNumber1 + airline + locFlightNumber2 + flightNumber
					+ "']";
			Boolean visibilityStatus = waitAndCheckForElementVisibility(dynamicShadowLocatorFlightNumber,
					elementVisibilityMaxTimeout);
			if (!visibilityStatus) {
				logAssert_Fail("The flight number is not displayed in Flight Search for Airline " + airline
						+ " and Flight " + flightNumber);
			}
		} catch (InterruptedException e) {
			logAssert_Fail("The Flight Status Search was not done due to error message " + e.getMessage());
		}
	}

	public void verifyDate(String date) throws InterruptedException, IOException {

		String dateFormatDisplayed = "dd/MM/yyyy";
		String tenantName = scenarioContext.getContext("Tenant_Name");
		log.info("The current tenant is " + tenantName);
		if (tenantName.equalsIgnoreCase("LX")) {
			dateFormatDisplayed = "dd.MM.yyyy";
		}
		date = getFlightDate(date, dateFormatDisplayed);
		assertTextOnTheElement("flightDate", date);
	}

	public void verifyOrigin(String origin) throws InterruptedException {
		assertTextOnTheElement("origin", origin);
	}

	public void verifyDestination(String destination) throws InterruptedException {
		assertTextOnTheElement("destination", destination);
	}

	public void verifyDestinationImage() throws InterruptedException {
		page.waitForTimeout(10000);
		takeScreenshot();
		Locator element = page.locator("destinationImage");

		// Check if the element is visible on the page
		if (element.isVisible()) {
			System.out.println("Element is present and visible.");
		} else {
			System.out.println("Element is not visible.");
		}
//		waitForElementToBeVisible("destinationImage", elementVisibilityMaxTimeout);
	}
    public void enableTTT() throws InterruptedException {
		Thread.sleep(25000);
//		waitForElementToBeVisible("ttt", elementVisibilityMaxTimeout);
//        waitAndCheckForElementToBeEnabled(locatorMap.get("ttt"));
		waitAndClickOnTheElement("ttt",5000);
    }
	public void shareLink() throws InterruptedException {
		Thread.sleep(10000);
		takeScreenshot();
		Thread.sleep(10000);
		waitForElementToBeVisible("share", elementVisibilityMaxTimeout);
		takeScreenshot();
		waitAndClickOnTheElement("share",5000);
//		Thread.sleep(15000);
		takeScreenshot();
	}
	public void copy() throws InterruptedException {
		waitForElementToBeVisible("copy", elementVisibilityMaxTimeout);
//		clickAndSwitchToNewTab("copy");
		waitAndClickOnTheElement("copy",5000);
		takeScreenshot();
//		switchToNewWindow("copy");
	}
	public void cross() throws InterruptedException {
		waitForElementToBeVisible("cross", elementVisibilityMaxTimeout);
		waitAndClickOnTheElement("cross",5000);
	}

	public void enterPassengerDetailsButton() throws InterruptedException {
		Thread.sleep(5000);
		waitForElementToBeVisible("enterPassengerDetailsButton",elementVisibilityMaxTimeout);
		takeScreenshot();
		Thread.sleep(5000);
		waitAndClickOnTheElement("enterPassengerDetailsButton",5000);

	}

	public String getFlightDate(String dateKey, String dateFormatDisplayed) throws InterruptedException, IOException {

		String dateOutput = dateKey;
		String requiredDateFormat = dateFormatDisplayed;

		// To return Current Date
		if (dateKey.equalsIgnoreCase("CurrentDate")) {
			dateOutput = DateManipulator.convertDateToString(DateManipulator.getCurrentDate(requiredDateFormat),
					requiredDateFormat);
		}

		// To return days added to Current Date
		if (dateKey.contains("+")) {
			String numberOfDaysToAddString = dateKey.split("\\+")[1];
			int numberOfDays = Integer.parseInt(numberOfDaysToAddString);
			String currentDate = DateManipulator.convertDateToString(DateManipulator.getCurrentDate(requiredDateFormat),
					requiredDateFormat);
			dateOutput = DateManipulator.addDaystoDate(currentDate, requiredDateFormat, numberOfDays);
		}

		log.info("The output date is :" + dateOutput);
		return dateOutput;

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
