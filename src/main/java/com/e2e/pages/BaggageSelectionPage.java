package com.e2e.pages;

import com.microsoft.playwright.Locator;

import java.io.IOException;
import java.util.HashMap;

public class BaggageSelectionPage extends BasePage {

	private HashMap<String, String> locatorMap = new HashMap<>();
	private HashMap<String, String> infoMap = new HashMap<>();

	// Constants
	final String oneWayTrip = "OW";
	final String roundTrip = "RT";
	final String multiCity = "MC";

	public BaggageSelectionPage()
	{
		// Locators
		locatorMap.put("baggage_AddBaggage_btn", "//*[@class='default-button-text']");
		locatorMap.put("baggageOutboundAdditionalCheckedBaggage",
				"//li[@class='category-selection-travel-unit ng-star-inserted'][1]//button[@aria-label='Add'][1]");
		locatorMap.put("baggage_TotalPrice_obj", "//*[@class='footer-wrapper']//span[contains(@class,'price-amount')]");
		locatorMap.put("baggage_ConfirmBag_btn", "//*[text()='Confirm your bags']");
		locatorMap.put("baggage_Confirmation",
				"(//span[contains(text(),'Total price baggage:')or contains(text(),'Total to be paid:')])[1]");
		locatorMap.put("baggageAdditionalBaggageButton", "//button[@aria-label='Add']");

		locatorMap.put("baggageOutboundAdditionalCheckedBagBtn",
				"//li[@class='category-selection-travel-unit ng-star-inserted'][1]//div[text()='Additional checked bags']/parent::*/parent::*/parent::*//button[@aria-label='Add']");
		locatorMap.put("baggageOutboundAdditionalHeavyBagBtn",
				"//li[@class='category-selection-travel-unit ng-star-inserted'][1]//div[text()='Heavy additional bag']/parent::*/parent::*/parent::*//button[@aria-label='Add']");
		locatorMap.put("baggageOutboundAdditionalOversizedBagBtn",
				"//li[@class='category-selection-travel-unit ng-star-inserted'][1]//div[text()='Oversized additional bag']/parent::*/parent::*/parent::*//button[@aria-label='Add']");
		locatorMap.put("baggageOutboundAdditionalHeavyOversizedBagBtn",
				"//li[@class='category-selection-travel-unit ng-star-inserted'][1]//div[text()='Heavy and oversized additional bag']/parent::*/parent::*/parent::*//button[@aria-label='Add']");

		locatorMap.put("baggageInboundAdditionalCheckedBagBtn",
				"//li[@class='category-selection-travel-unit ng-star-inserted'][2]//div[text()='Additional checked bags']/parent::*/parent::*/parent::*//button[@aria-label='Add']");
		locatorMap.put("baggageInboundAdditionalHeavyBagBtn",
				"//li[@class='category-selection-travel-unit ng-star-inserted'][2]//div[text()='Heavy additional bag']/parent::*/parent::*/parent::*//button[@aria-label='Add']");
		locatorMap.put("baggageInboundAdditionalOversizedBagBtn",
				"//li[@class='category-selection-travel-unit ng-star-inserted'][2]//div[text()='Oversized additional bag']/parent::*/parent::*/parent::*//button[@aria-label='Add']");
		locatorMap.put("baggageInboundAdditionalHeavyOversizedBagBtn",
				"//li[@class='category-selection-travel-unit ng-star-inserted'][2]//div[text()='Heavy and oversized additional bag']/parent::*/parent::*/parent::*//button[@aria-label='Add']");


		// Info messages for all locators
		infoMap.put("baggage_AddBaggage_btn", "Button to Add Baggage Count");
		infoMap.put("baggageOutboundAdditionalCheckedBaggage", "Button to increase bag count by 1");
		infoMap.put("baggage_TotalPrice_obj", "Label to display total price for extra bags");
		infoMap.put("baggage_ConfirmBag_btn", "Button to Confirm your Bag Count");
		infoMap.put("baggage_Confirmation", "Total Baggage and Price Confirmation Message");
		infoMap.put("baggageAdditionalBaggageButton", "Button to Add Baggage count by 1");
		infoMap.put("baggageOutboundAdditionalHeavyBagBtn", "Button to increase Additional Heavy bag count by 1 Outbound Flight");
		infoMap.put("baggageOutboundAdditionalCheckedBagBtn", "Button to increase Additional Checked bag count by 1 Outbound Flight");
		infoMap.put("baggageOutboundAdditionalOversizedBagBtn", "Button to increase Additional Oversized bag count by 1 Outbound Flight");
		infoMap.put("baggageOutboundAdditionalHeavyOversizedBagBtn", "Button to increase Additional Heavy & Oversized bag count by 1 Outbound Flight");
		infoMap.put("baggageInboundAdditionalHeavyBagBtn", "Button to increase Additional Heavy bag count by 1 Inbound Flight");
		infoMap.put("baggageInboundAdditionalCheckedBagBtn", "Button to increase Additional Checked bag count by 1 Inbound Flight");
		infoMap.put("baggageInboundAdditionalOversizedBagBtn", "Button to increase Additional Oversized bag count by 1 Inbound Flight");
		infoMap.put("baggageInboundAdditionalHeavyOversizedBagBtn", "Button to increase Additional Heavy & Oversized bag count by 1 Inbound Flight");

	}

	public void activateExtraBaggage() throws InterruptedException, IOException
	{
		if (isTheElementVisible("baggage_AddBaggage_btn", elementVisibilityMaxTimeout))
		{
			clickOnTheElement("baggage_AddBaggage_btn");
		}
		waitForElementToBeVisible("baggage_ConfirmBag_btn", elementVisibilityMaxTimeout);
	}

	public void addOutboundExtraBaggage(String bagType, String passenger) throws InterruptedException, IOException
	{
		try
		{
			int adult = Character.getNumericValue(passenger.split(",")[0].charAt(0));
			int child = Character.getNumericValue(passenger.split(",")[1].charAt(0));
			int infant = Character.getNumericValue(passenger.split(",")[2].charAt(0));

			int totalPassenger = adult + child + infant ;

			if (bagType.equalsIgnoreCase("checked")) {
				log.info("Inside checked baggage addition");
				addExtraCheckedBaggage(totalPassenger);
			} else if (bagType.equalsIgnoreCase("heavy")) {
				log.info("Inside heavy baggage addition");
				addExtraHeavyBaggage(totalPassenger);
			} else if (bagType.equalsIgnoreCase("oversized")) {
				log.info("Inside oversized baggage addition");
				addExtraOversizedBaggage(totalPassenger);
			} else if (bagType.equalsIgnoreCase("heavy oversized")) {
				log.info("Inside heavy oversized baggage addition");
				addExtraHeavyOversizedBaggage(totalPassenger);
			}
		}
		catch (Exception e)
		{
			logAssert_Fail("Failed to Select Extra Baggage in Outbound Flight");
		}
	}

	public void addExtraCheckedBaggage(int totalPassenger) throws InterruptedException, IOException
	{
		try
		{
			int requiredAdditionalBag;
			Locator additionalBaggage = page.locator(locatorMap.get("baggageOutboundAdditionalCheckedBagBtn"));
			requiredAdditionalBag = totalPassenger;

			for (int BagCntr = 0; BagCntr < requiredAdditionalBag; BagCntr++) {
				additionalBaggage.nth(BagCntr).click();
			}
		} catch (Exception e) {
			logAssert_Fail("Failed to add Additional Checked Baggage");
		}
	}

	public void addExtraHeavyBaggage(int totalPassenger) throws InterruptedException, IOException
	{
		try {
			int requiredAdditionalBag;
			Locator additionalBaggage = page.locator(locatorMap.get("baggageOutboundAdditionalHeavyBagBtn"));
			requiredAdditionalBag = totalPassenger;

			for (int BagCntr = 0; BagCntr < requiredAdditionalBag; BagCntr++) {
				additionalBaggage.nth(BagCntr).click();
			}
		} catch (Exception e) {
			logAssert_Fail("Failed to add Heavy Additional Baggage");
		}
	}

	public void addExtraOversizedBaggage(int totalPassenger) throws InterruptedException, IOException
	{
		try {
			int requiredAdditionalBag;
			Locator additionalBaggage = page.locator(locatorMap.get("baggageOutboundAdditionalOversizedBagBtn"));
			requiredAdditionalBag = totalPassenger;

			for (int BagCntr = 0; BagCntr < requiredAdditionalBag; BagCntr++) {
				additionalBaggage.nth(BagCntr).click();
			}
		} catch (Exception e) {
			logAssert_Fail("Failed to add Oversized Additional Baggage");
		}
	}

	public void addExtraHeavyOversizedBaggage(int totalPassenger) throws InterruptedException, IOException
	{
		try {
			int requiredAdditionalBag;
			Locator additionalBaggage = page.locator(locatorMap.get("baggageOutboundAdditionalHeavyOversizedBagBtn"));
			requiredAdditionalBag = totalPassenger;

			for (int BagCntr = 0; BagCntr < requiredAdditionalBag; BagCntr++) {
				additionalBaggage.nth(BagCntr).click();
			}
		} catch (Exception e) {
			logAssert_Fail("Failed to add Heavy & Oversized Additional Baggage");
		}
	}


	public void addAdditionalCheckedBagForRoundTrip(String executionMode, String tripType, String passenger)
			throws InterruptedException, IOException
	{

		try {
			int adult = Character.getNumericValue(passenger.split(",")[0].charAt(0));
			int child = Character.getNumericValue(passenger.split(",")[1].charAt(0));
			int infant = Character.getNumericValue(passenger.split(",")[2].charAt(0));
			int totalPassenger = adult + child + infant, requiredAdditionalBag;

			waitAndClickOnTheElement("baggage_AddBaggage_btn", elementVisibilityMaxTimeout);
			isTheElementVisible("baggageAdditionalBaggageButton", elementVisibilityMaxTimeout);
			Locator additionalBaggage = page.locator(locatorMap.get("baggageOutboundAdditionalCheckedBaggage"));
			if (tripType.equalsIgnoreCase(oneWayTrip)) {
				requiredAdditionalBag = totalPassenger;
			} else {
				requiredAdditionalBag = totalPassenger * 2;
			}
			for (int BagCntr = 0; BagCntr < requiredAdditionalBag; BagCntr++) {
				additionalBaggage.nth(BagCntr).click();
			}
			takeExtraScreenshot();
		} catch (Exception e) {
			logAssert_Fail("Failed to add Additional Checked Baggage for Round Trip");
		}

	}

	public void confirmBaggageAddition() throws InterruptedException, IOException
	{
		try {
			waitAndClickOnTheElement("baggage_ConfirmBag_btn", elementVisibilityMaxTimeout);
			waitForElementToBeVisible("baggage_Confirmation", elementVisibilityMaxTimeout);
		} catch (Exception e) {
			logAssert_Fail("Failed to confirm Baggage Addition");
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
