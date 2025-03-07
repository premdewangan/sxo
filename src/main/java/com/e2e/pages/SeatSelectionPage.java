package com.e2e.pages;

import java.io.IOException;
import java.util.HashMap;

import com.microsoft.playwright.*;

public class SeatSelectionPage extends BasePage {

	private HashMap<String, String> locatorMap = new HashMap<>();
	private HashMap<String, String> infoMap = new HashMap<>();

	public SeatSelectionPage() {
		// Locators
		locatorMap.put("seatSelectSeatObj",
				"//*[text()='Select your seats' or text()='Select seat' or text()='Select your Seats']");
		locatorMap.put("rebookingModifyDropDownLocator", "//*[@id='ST1SEAT']/div[2]/i");
		locatorMap.put("rebookingModifyButtonLocator",
				"//ul[@class='category-recap-trav el-units']//span[contains(text(),'Modify')]");
		locatorMap.put("seatConfirmAndContinueButton", "//*[contains(text(),'Confirm') or contains(text(),'Confirm and Continue')]\n");
		locatorMap.put("seatMapLegend", "//*[text()='Seatmap Legend' or text()='Seat map legend']");

		locatorMap.put("seatChargeableSeatButton", "//*[contains(@aria-label,'Seat available., Chargeable.')]");
		locatorMap.put("seatMapObj", "//div[@class='seatmap' or @class='seatmaps-wrapper ng-star-inserted']");
		locatorMap.put("seatSeatNumber", "//div[contains(@class,'seat-number') or contains(@class,'seat-tag')]");
		locatorMap.put("emergencySeatConsentCheckbox", "//input[@type='checkbox']");
		locatorMap.put("seatAcknowledgeObj",
				"//span[text()='Select seat' or text()='Acknowledge and select seat' or text()=' Select seat ']");
		locatorMap.put("nextSeatButton", "//button[contains(@class,'next-btn') or @id='btnContinue']");
		locatorMap.put("outboundSeatConfirmation", "//*[@class=\"selected-seat ng-star-inserted\"]");

		locatorMap.put("classicSeatButton",
				"//*[contains(@aria-label,'Classic Seat, Seat available') or contains(@aria-label,'Classic Seat, 0.00, Available seat')]");
		locatorMap.put("continueSeatButton", "//*[contains(text(),'Continue')]");

		// Info messages for all locators
		infoMap.put("seatSelectSeatObj", "Button to Start Selecting your Seat");
		infoMap.put("rebookingModifyDropDownLocator", "Drop Down to Modify Seats");
		infoMap.put("rebookingModifyButtonLocator", "Button to Modify Seats");
		infoMap.put("seatConfirmAndContinueButton", "Button to Confirm and Continue seat selection");
		infoMap.put("seatMapLegend", "Seat Map Legend Heading");

		infoMap.put("seatChargeableSeatButton", "Button to select Chargeable Seat");
		infoMap.put("seatMapObj", "Seat Map of the flight displayed");
		infoMap.put("seatSeatNumber", "Seat number of the Flight Seat");
		infoMap.put("emergencySeatConsentCheckbox", "Consent Checkbox in case of selecting an Emergency Seat");
		infoMap.put("seatAcknowledgeObj", "Button to Acknowledge Seat Selection");
		infoMap.put("nextSeatButton", "Button to Confirm and Continue seat selection");
		infoMap.put("outboundSeatConfirmation", "Message to Confirm Outbound Seat Selected ");

		infoMap.put("classicSeatButton", "Button to Select Classic Seats Available ");
		infoMap.put("continueSeatButton", "Button to Continue after seat selection ");

	}

	public void continueSeatSelection() throws InterruptedException, IOException {
		try {
			if (isTheElementVisible("seatSelectSeatObj", elementVisibilityMaxTimeout)) {
				takeScreenshot();
				clickOnTheElement("seatSelectSeatObj");
			} else {
				waitAndClickOnTheElement("rebookingModifyDropDownLocator", elementVisibilityMaxTimeout);
				waitAndClickOnTheElement("rebookingModifyButtonLocator", elementVisibilityMaxTimeout);
			}
			takeExtraScreenshot();
			if (isTheElementVisible("seatConfirmAndContinueButton", elementVisibilityMaxTimeout)
					&& (!isTheElementVisible("seatMapLegend", elementVisibilityMaxTimeout))) {
				clickOnTheElement("seatConfirmAndContinueButton");
			}
		} catch (Exception e) {
			logAssert_Fail("Failed to Select Seat Button");
		}

	}

	public void selectOutboundSeat(String seatType, String passengerCount) throws InterruptedException, IOException {
		try {
			if (seatType.equalsIgnoreCase("chargeable")) {
				log.info("Inside chargeable seat selection");
				selectChargeableSeat(passengerCount);
			} else if (seatType.equalsIgnoreCase("Extralegroom")) {
				// Needs to be Developed - Harish
				// extraLegroomSeat(seatType, passenger);
			} else if (seatType.equalsIgnoreCase("FreeSeat")) {
				log.info("Inside Free seat selection");
				selectClassicSeat(passengerCount);
			}
			takeExtraScreenshot();
		} catch (Exception e) {
			logAssert_Fail("Failed to Select Outbound Seat");
		}
	}

	public void selectInboundSeat(String seatType2, String passengerCount) throws InterruptedException, IOException {
		try {
			// chargeable seat
			if (seatType2.equalsIgnoreCase("chargeable")) {
				selectChargeableSeat(passengerCount);
				takeExtraScreenshot();
			} else if (seatType2.equalsIgnoreCase("Extralegroom")) {
				// Needs to be Developed - Harish
				// extraLegroomSeat(seatType, passenger);
				takeExtraScreenshot();
			} else if (seatType2.equalsIgnoreCase("FreeSeat")) {
				log.info("Inside Free seat selection");
				selectClassicSeat(passengerCount);
				takeExtraScreenshot();
			}

		} catch (Exception e) {
			logAssert_Fail("Failed to Select InBound Seat");
		}
	}

	public void activateSeatMapLegend() throws InterruptedException, IOException {
		if (isTheElementVisible("seatSelectSeatObj", elementVisibilityMaxTimeout)) {
			clickOnTheElement("seatSelectSeatObj");
		}
		waitForElementToBeVisible("seatMapLegend", elementVisibilityMaxTimeout);
	}

	/**
	 * This method selects chargeable seats for the passengers and confirms the
	 * selection.
	 * 
	 * @param passengerCountInput The count of adult, child, and infant passengers
	 *                            in the format "Adult,Child,Infant"
	 * @throws IOException          If an input or output exception occurs
	 * @throws InterruptedException If the thread is interrupted while waiting for
	 *                              the seat selection
	 */

	public void selectChargeableSeat(String passengerCountInput) throws IOException, InterruptedException {
		int chargeableSeatCount, passengerCount, seatCounter;
		boolean ifContinueSeatSelection = true;
		String seatNumber;

		// Checking the count of Passengers in Chargeable Category
		int adult = Character.getNumericValue(passengerCountInput.split(",")[0].charAt(0));
		int child = Character.getNumericValue(passengerCountInput.split(",")[1].charAt(0));
		int infant = Character.getNumericValue(passengerCountInput.split(",")[2].charAt(0));

		int TotalPassenger = adult + child + infant;
		log.info("Total Passenger Count is " + TotalPassenger);

		waitForElementToBeVisible("seatMapObj", elementVisibilityMaxTimeout);

		while (ifContinueSeatSelection == true) {

			// Checking the count of seat available in Chargeable Category
			Locator chargeableSeat = page.locator(locatorMap.get("seatChargeableSeatButton"));
			chargeableSeatCount = getAvailableSeatCount(chargeableSeat);

			passengerCount = 0;
			seatCounter = 1;

			while (passengerCount < TotalPassenger) {

				// Checking the availability of seat in Chargeable Category
				log.info("passengerCount :" + passengerCount);

				// Checking the availability of Seat Map and Legend in Classic Category
				checkSeatMapandLegendVisibility();

				// Selecting Passenger Seat(s)
				selectPassengerSeat(infant, passengerCount, seatCounter, chargeableSeatCount, chargeableSeat);

				// Selecting Seat Number
				seatNumber = getConfirmedChargeableSeatNumber();
				log.info("Seat number Selected is :" + seatNumber);

				passengerCount = passengerCount + 1;
				seatCounter = seatCounter + 1;
			}
			if (passengerCount >= TotalPassenger) {
				ifContinueSeatSelection = false;
			}

			if (isTheElementVisible("outboundSeatConfirmation", elementVisibilityMinTimeout)) {
				log.info("Outbound Seat has been selected");
			} else {
				log.info("Inbound Seat has been selected");
			}
		}

	}

	/**
	 * This method selects classic seats for the passengers and confirms the
	 * selection.
	 * 
	 * @param passengerCountInput The count of adult, child, and infant passengers
	 *                            in the format "Adult,Child,Infant"
	 * @throws IOException          If an input or output exception occurs
	 * @throws InterruptedException If the thread is interrupted while waiting for
	 *                              the seat selection
	 */
	public void selectClassicSeat(String passengerCountInput) throws IOException, InterruptedException {
		int classicSeatCount, passengerCount, seatCounter;
		boolean ifContinueSeatSelection = true;
		String seatNumber;

		// Checking the count of Passengers in Classic Category
		int adult = Character.getNumericValue(passengerCountInput.split(",")[0].charAt(0));
		int child = Character.getNumericValue(passengerCountInput.split(",")[1].charAt(0));
		int infant = Character.getNumericValue(passengerCountInput.split(",")[2].charAt(0));

		int TotalPassenger = adult + child + infant;
		log.info(TotalPassenger + "is the Total Number of Passengers available");
		log.info("Infant Count :" + infant);

		waitForElementToBeVisible("seatMapObj", elementVisibilityMaxTimeout);

		while (ifContinueSeatSelection == true) {

			// Checking the count of seat available in Classic Category
			Locator classicSeat = page.locator(locatorMap.get("classicSeatButton"));
			classicSeatCount = getAvailableSeatCount(classicSeat);

			passengerCount = 0;
			seatCounter = 1;

			log.info("passengerCount :" + passengerCount);
			while (passengerCount < TotalPassenger) {

				// Checking the availability of Seat Map and Legend in Classic Category
				checkSeatMapandLegendVisibility();

				// Selecting Passenger Seat(s)
				selectPassengerSeat(infant, passengerCount, seatCounter, classicSeatCount, classicSeat);

				// Selecting Seat Number
				seatNumber = getConfirmedClassicSeatNumber();
				log.info("Seat number Selected is :" + seatNumber);

				passengerCount++;
				if (passengerCount >= TotalPassenger) {
					ifContinueSeatSelection = false;
					log.debug("Breaking from Loop");
					break;
				}
			}

			clickOnTheElement("nextSeatButton");
			log.info("Clicked on confirm seat button");
		}

		if (isTheElementVisible("outboundSeatConfirmation", elementVisibilityMinTimeout)) {
			log.info("Outbound Seat has been selected");
		} else {
			log.info("Inbound Seat has been selected");
		}

	}

	public void checkSeatMapandLegendVisibility() throws IOException, InterruptedException {
		boolean ifseatMapVisible = isTheElementVisible("seatMapObj", elementVisibilityMinTimeout);
		if (ifseatMapVisible == true) {
			log.info("Seatmap is displayed");
		} else {
			logAssert_Fail("Seatmap is not displayed");
		}
		boolean ifSeatMapLegendVisible = isTheElementVisible("seatMapLegend", elementVisibilityMinTimeout);
		if (ifSeatMapLegendVisible == true) {
			log.info("Seatmap legend is displayed");
		} else {
			logAssert_Fail("Seatmap legend is not displayed");
		}
	}

	public String getConfirmedClassicSeatNumber() throws IOException, InterruptedException {
		// Selecting and Confirming Seat Numbers selected
		boolean seatnumber_status = isTheElementVisible("seatSeatNumber", elementVisibilityMinTimeout);
		if (seatnumber_status == true) {
			log.info("Seat Visibility status is :" + seatnumber_status);
		} else {
			logAssert_Fail("Seat Visibility is not displayed");
		}
		String SeatnumberEachpax = getTextOnTheElement("seatSeatNumber");
		String seatNumber = SeatnumberEachpax.split(" ")[1];
		log.info("********Seat Number********" + seatNumber);

		if (isTheElementVisible("emergencySeatConsentCheckbox", elementVisibilityMinTimeout)) {
			clickOnTheElement("emergencySeatConsentCheckbox");
		}
		clickOnTheElement("seatAcknowledgeObj");
		return seatNumber;
	}

	public String getConfirmedChargeableSeatNumber() throws IOException, InterruptedException {
		// Selecting and Confirming Seat Number
		waitForElementToBeVisible("seatSeatNumber", elementVisibilityMaxTimeout);
		String SeatnumberEachpax = getTextOnTheElement("seatSeatNumber");
		String seatNumber = SeatnumberEachpax.split(" ")[1];
		log.info("********Seat Number********" + seatNumber);

		if (isTheElementVisible("emergencySeatConsentCheckbox", elementVisibilityMinTimeout)) {
			clickOnTheElement("emergencySeatConsentCheckbox");
		}
		clickOnTheElement("seatAcknowledgeObj");
		waitAndClickOnTheElement("nextSeatButton", elementVisibilityMaxTimeout);
		log.info("Clicked on confirm seat");
		return seatNumber;
	}

	public int getAvailableSeatCount(Locator seatTypeLocator) {
		int availableSeatCount = 0;
		availableSeatCount = seatTypeLocator.count();
		log.info("available seats:" + availableSeatCount);
		return availableSeatCount;
	}

	public void selectPassengerSeat(int infant, int passengerCount, int seatCounter, int seatCount,
			Locator seatLocator) {
		log.info("Infant Count :" + infant);
		if (infant > 1) {
			log.info("passengerCount :" + passengerCount);
			if (passengerCount == 1) {
				seatLocator.nth((seatCount - infant)).click();
				log.info("Seat number selected :" + (seatCount - infant));
				infant--;
			} else {
				seatLocator.nth(seatCounter).click();
			}
		} else {
			seatLocator.nth(seatCounter).click();
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
