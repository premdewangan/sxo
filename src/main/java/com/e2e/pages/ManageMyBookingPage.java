package com.e2e.pages;

import com.e2e.utils.DateManipulator;
import com.microsoft.playwright.Locator;

import java.io.IOException;
import java.util.HashMap;

public class ManageMyBookingPage extends BasePage
{

	private HashMap<String, String> locatorMap = new HashMap<>();
	private HashMap<String, String> infoMap = new HashMap<>();

	public ManageMyBookingPage()
	{
		// Locators
		locatorMap.put("acceptPopupbtn", "//button[@id='cm-acceptAll']");
		locatorMap.put("referenceNumberSer", "//input[@placeholder=\"Your booking reference\"]");
		locatorMap.put("referenceLastnameSer", "//input[@placeholder=\"Your last name\"]");
		locatorMap.put("mmbSubmitBtn", "//button[@type=\"submit\"]");

		locatorMap.put("upgradePage", "//*[contains(text(),'Select your upgrade') or contains(text(),'Upgrade now')]");
		locatorMap.put("upgradeButton", "//span[text()='Start upgrade' or text()='Upgrade now']");
		locatorMap.put("upgradeContinueButton", "//button[contains(text(),'Continue')]");
		locatorMap.put("paymentContinuetoPaymentButton", "//span[text()='Continue to payment']");

		locatorMap.put("freeRebookRefundBtn", "//span[contains(text(),'Free rebooking/refund')]");
		locatorMap.put("acceptOfferBtn", "//span[contains(text(),'Accept offer')]");
		locatorMap.put("checkInContinueBtn", "//span[contains(text(),'Continue')]");
		locatorMap.put("flightsAreConfirmedText", "//h2[contains(text(),'Your flight is confirmed')]");

		locatorMap.put("cancel/refundFlight", "//*[contains(text(),'Cancel/Refund your flights')]");
		locatorMap.put("refundPageVal", "//h1//*[contains(text(),'Cancellation and Refund')]");
		locatorMap.put("getMyRefundBtn", "//*[contains(text(),'Get my refund')]");
		locatorMap.put("refundVal", "//*[contains(text(),'Your cancellation and refund are confirmed.')]");

		locatorMap.put("checkChangeFlightButton", "//span[text()='Change flights']");
		locatorMap.put("rebookingSearchButton", "//span[contains(text(),'Search')]");

		locatorMap.put("addRailAndFlyButton", "(//span[contains(text(),'Add Rail&Fly')])[2]");
		locatorMap.put("railAndFlyHeader", "//div[contains(text(),'Rail&Fly Ticket')]");
		locatorMap.put("firstClassSelectionRadioButton", "//span[contains(text(),'1st Class')]");
		locatorMap.put("outBoundCheckBox", "//*[@id=\"mat-checkbox-2\"]");
		locatorMap.put("confirmRailAndFlyTicketBtn", "//span[contains(text(),'Confirm Rail&Fly Ticket')]");
		locatorMap.put("confirmationOfRailAndFly", "//span[contains(text(),'Rail&Fly Voucher')]");
		locatorMap.put("upgradePanelExpansionObj", "//i[contains(@aria-label,'Expand fare list')]");
		locatorMap.put("chooseSpecialMealBtn", "//span[contains(text(),'Choose special meal')]");
		locatorMap.put("confirmYourMealChoice", "//span[contains(text(),'Confirm your choice')]");
		locatorMap.put("activateOutboundMealChoiceDropdown", "(//span[contains(text(),'Please select an option.')])[1]");
		locatorMap.put("activateInboundMealChoiceDropdown", "//span[@class='mat-mdc-select-placeholder mat-mdc-select-min-line ng-tns-c1771602899-14 ng-star-inserted']");
		locatorMap.put("addPetInCabinBtn", "//span[contains(text(),'Add your pet')]");
		locatorMap.put("confirmYourPetChoice", "//span[contains(text(),'Confirm your choice')]");
		locatorMap.put("activateOutboundPetChoiceDropdown", "(//span[contains(text(),'Please select')])[1]");
		locatorMap.put("activateInboundPetChoiceDropdown", "(//span[text()='Please select'])[2]");
		locatorMap.put("airportHealthDocChkBox", "//input[@id='mat-mdc-checkbox-1-input']");
		locatorMap.put("petTransportDeclarationForm", "//input[@id='mat-mdc-checkbox-2-input']");

		// Info messages for all locators
		infoMap.put("acceptPopupbtn", "Button to Accept All");
		infoMap.put("referenceNumberSer", "Input TextBox to enter Booking Reference Number or PNR");
		infoMap.put("referenceLastnameSer", "Input TextBox to enter Last Name");
		infoMap.put("mmbSubmitBtn", "Button to Continue retrieval of Passenger Details");

		infoMap.put("upgradePage", "Page displaying Upgradation message");
		infoMap.put("upgradeButton", "Button to Start Upgrade");
		infoMap.put("upgradeContinueButton", "Button to Continue Upgrade of cabin");
		infoMap.put("paymentContinuetoPaymentButton", "Button to Continue Payment");

		infoMap.put("freeRebookRefundBtn", "Button to do Free Booking/Refund");
		infoMap.put("acceptOfferBtn", "Button to Accept Offer");
		infoMap.put("checkInContinueBtn", "Button to Continue Check-In");
		infoMap.put("flightsAreConfirmedText", "Message to display confirmation of Flight Booking");

		infoMap.put("cancel/refundFlight", "Button to do Cancel/Refund");
		infoMap.put("refundPageVal", "Message confirmation for Cancel and Refund");
		infoMap.put("getMyRefundBtn", "Button to Get Refund");
		infoMap.put("refundVal", "Message to display confirmation of Refund");

		infoMap.put("checkChangeFlightButton", "Button to Change Flights");
		infoMap.put("rebookingSearchButton", "Button to Search for Rebooking");

		infoMap.put("addRailAndFlyButton", "Button to Change Flights");
		infoMap.put("railAndFlyHeader", "Button to Search for Rebooking");
		infoMap.put("firstClassSelectionRadioButton", "Button to Change Flights");
		infoMap.put("outBoundCheckBox", "Button to Search for Rebooking");
		infoMap.put("confirmRailAndFlyTicketBtn", "Button to Change Flights");
		infoMap.put("confirmationOfRailAndFly", "Button to Search for Rebooking");
		infoMap.put("upgradePanelExpansionObj", "Panels displaying Upgrade options");
		infoMap.put("chooseSpecialMealBtn", "Choose Special Meal Button");
		infoMap.put("confirmYourMealChoice", "Confirm Your Meal Choice Button");
		infoMap.put("activateOutboundMealChoiceDropdown", "Confirm Your Outbound Flight Meal Choice Dropdown");
		infoMap.put("activateInboundMealChoiceDropdown", "Confirm Your Inbound Flight Meal Choice Dropdown");
		infoMap.put("addPetInCabinBtn", "Pet In Cabin Button");
		infoMap.put("confirmYourPetChoice", "Confirm Your Pet Choice");
		infoMap.put("activateOutboundPetChoiceDropdown", "Activate Outbound Flight Pet Choice Dropdown");
		infoMap.put("activateInboundPetChoiceDropdown", "Activate Inbound Flight Pet Choice Dropdown");
		infoMap.put("airportHealthDocChkBox", "Pet Travel Health Obligation Checkbox");
		infoMap.put("petTransportDeclarationForm", "Pet Travel Form");


	}


	public void addPetInOneWayFlight(String outboundPetName) throws InterruptedException
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

	public void addPetInRoundTripFlight(String outboundPetName, String inboundPetName) throws InterruptedException
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

	public void addSpecialMealOneWayTrip(String outboundSpecialMeal) throws InterruptedException
	{
		try
		{
			waitAndClickOnTheElement("chooseSpecialMealBtn", elementVisibilityMaxTimeout);
			waitAndCheckForElementToBeEnabled(locatorMap.get("confirmYourMealChoice"));
			waitAndClickOnTheElement("activateOutboundMealChoiceDropdown", elementVisibilityMaxTimeout);
			//String mealLocator = "(//span[contains(text()," + outboundSpecialMeal + ")])[1]";
			String mealLocator = "//span[contains(text(),\"" + outboundSpecialMeal + "\")]";
			waitAndCheckForElementToBeEnabled(mealLocator);
			waitAndClick(mealLocator);
			waitAndClickOnTheElement("confirmYourMealChoice", elementVisibilityMaxTimeout);

		} catch (Exception e)
		{
			logAssert_Fail("Fails to select the Outbound Flight Meal");
		}

	}

	public void addSpecialMealRoundTrip(String outboundSpecialMeal, String inboundSpecialMeal) throws InterruptedException
	{
		try
		{
			//Add Special Meal in Outbound Flight
			waitAndClickOnTheElement("chooseSpecialMealBtn", elementVisibilityMaxTimeout);
			waitAndCheckForElementToBeEnabled(locatorMap.get("confirmYourMealChoice"));
			waitAndClickOnTheElement("activateOutboundMealChoiceDropdown", elementVisibilityMaxTimeout);
			String outboundMealLocator = "//span[contains(text(),\"" + outboundSpecialMeal + "\")]";
			waitAndCheckForElementToBeEnabled(outboundMealLocator);
			waitAndClick(outboundMealLocator);

			//Add Special Meal in Inbound Flight
			waitAndClickOnTheElement("activateInboundMealChoiceDropdown", elementVisibilityMaxTimeout);
			String inboundMealLocator = "//span[normalize-space()=\"" + inboundSpecialMeal + "\"]";
			waitAndCheckForElementToBeEnabled(inboundMealLocator);
			waitAndClick(inboundMealLocator);
			waitAndClickOnTheElement("confirmYourMealChoice", elementVisibilityMaxTimeout);

		} catch (Exception e)
		{
			logAssert_Fail("Fails to select the special meals in Round Trip");
		}

	}

	public void retrievePNRDetailsFromMMB(String PNR, String lastname, String tenant)
			throws InterruptedException, IOException
	{
		try
		{

			String bookingCode_value = readDynamicExternalData(PNR);
			String lastName_value = readDynamicExternalData(lastname);

			enterTextOnTheElement("referenceNumberSer", bookingCode_value);
			enterTextOnTheElement("referenceLastnameSer", lastName_value);
			enterTextOnTheElement("referenceNumberSer", PNR);
			enterTextOnTheElement("referenceLastnameSer", lastname);
			clickOnTheElement("mmbSubmitBtn");
		}
		catch (Exception e)
		{
			logAssert_Fail("Failed to Retrieve PNR Details through Manage My Booking URL");
		}

	}

	public void upgradeInboundFlight(String upgradeCabin) throws InterruptedException, IOException
	{
		try {

			int panelCount;
			waitForElementToBeVisible("upgradeButton", elementVisibilityMaxTimeout);

			log.info("Going to Upgrade !!! ");

			if (page.locator(locatorMap.get("upgradeButton")).isVisible()) {
				clickUpgradeButton();

				Locator multiplePanelsObj = page.locator(locatorMap.get("upgradePanelExpansionObj"));
				panelCount = multiplePanelsObj.count();
				boolean isSelectionFound = false;

				for (int panel = 0; panel <= panelCount - 1; panel++) {
					log.debug("entering upgrade loop");
					waitAndClickInElementArray(locatorMap.get("upgradePanelExpansionObj"), panel,
							elementVisibilityMaxTimeout);
					String upgradeCabinObject = "//*[contains(@class,'price-card-container')]//div[contains(text(),'"
							+ upgradeCabin + "')]";
					log.info(upgradeCabinObject);
					waitAndClickInElementArray(upgradeCabinObject, panel, elementVisibilityMaxTimeout);
					takeExtraScreenshot();

					isSelectionFound = clickContinueToPaymentButtonIfVisible();
					isSelectionFound = clickContinueToUpgradeButtonIfVisible();
					if (isSelectionFound) {
						break;
					}

				}

			}

		}

		catch (Exception e) {
			logAssert_Fail("Failed to Upgrade the Flight");
		}

	}

	public void upgradeOutboundFlight(String upgradeCabin) throws InterruptedException, IOException
	{
		try {

			int panelCount;
			waitForElementToBeVisible("upgradeButton", elementVisibilityMaxTimeout);

			log.info("Going to Upgrade !!! ");
			if (page.locator(locatorMap.get("upgradeButton")).isVisible()) {
				clickUpgradeButton();

				Locator multiplePanelsObj = page.locator(locatorMap.get("upgradePanelExpansionObj"));
				panelCount = multiplePanelsObj.count();
				boolean isSelectionFound = false;

				for (int panel = 0; panel <= panelCount - 1; panel++) {
					log.debug("entering upgrade loop");
					waitAndClickInElementArray(locatorMap.get("upgradePanelExpansionObj"), panel,
							elementVisibilityMaxTimeout);
					String upgradeCabinObject = "//*[contains(@class,'price-card-container')]//div[contains(text(),'"
							+ upgradeCabin + "')]";
					log.info(upgradeCabinObject);
					waitAndClickInElementArray(upgradeCabinObject, panel, elementVisibilityMaxTimeout);
					takeExtraScreenshot();

					isSelectionFound = clickContinueToPaymentButtonIfVisible();
					isSelectionFound = clickContinueToUpgradeButtonIfVisible();
					if (isSelectionFound) {
						break;
					}

				}

			}

		}

		catch (Exception e) {
			logAssert_Fail("Failed to Upgrade the Flight");
		}

	}

	public boolean clickContinueToPaymentButtonIfVisible() throws InterruptedException, IOException
	{
		if (isTheElementVisible("paymentContinuetoPaymentButton", elementVisibilityMaxTimeout)) {
			clickOnTheElement("paymentContinuetoPaymentButton");
			return true;
		} else {
			return false;
		}
	}

	public boolean clickContinueToUpgradeButtonIfVisible() throws InterruptedException, IOException {
		if (isTheElementVisible("upgradeContinueButton", elementVisibilityMaxTimeout))
		{
			clickOnTheElement("upgradeContinueButton");
			return true;
		} else {
			return false;
		}

	}

	public void clickUpgradeButton() throws InterruptedException, IOException
	{
		try {
			waitAndClickOnTheElement("upgradeButton", elementVisibilityMaxTimeout);
		} catch (Exception e) {
			logAssert_Fail("Failed to Click Upgrade Button");

		}

	}

	public void rebookFromProposedFlights() throws InterruptedException, IOException
	{
		try {
			clickOnTheElement("checkInContinueBtn");
			waitAndClickOnTheElement("freeRebookRefundBtn", elementVisibilityMaxTimeout);
			waitAndClickOnTheElement("acceptOfferBtn", elementVisibilityMaxTimeout);
			waitAndClickOnTheElement("checkInContinueBtn", elementVisibilityMaxTimeout);
			takeExtraScreenshot();

		} catch (Exception e) {
			logAssert_Fail("Failed to Rebook from Proposed Flights");

		}

	}

	public void verifyRebookingSuccessful() throws InterruptedException, IOException
	{
		try {
			boolean status;
			waitForElementToBeVisible("flightsAreConfirmedText", elementVisibilityMaxTimeout);
			status = page.locator(locatorMap.get("flightsAreConfirmedText")).isVisible();
			if (status == false) {
				logAssert_Fail("Flight confirmation failed for Rebboking");
			}
			takeExtraScreenshot();
		} catch (Exception e) {
			logAssert_Fail("Failed to Verify if Rebooking was Successful");

		}

	}

	public void performRefundFlight(String executionmode) throws InterruptedException, IOException
	{
		try {
			waitAndClickOnTheElement("cancel/refundFlight", elementVisibilityMaxTimeout);
			waitForElementToBeVisible("refundPageVal", elementVisibilityMaxTimeout);
			waitAndClickOnTheElement("getMyRefundBtn", elementVisibilityMaxTimeout);
			waitForElementToBeVisible("refundVal", elementVisibilityMaxTimeout);
		} catch (Exception e) {
			logAssert_Fail("Failed to Refund Flight");

		}

	}

	public void performFlightRefundValidation(String executionmode) throws InterruptedException, IOException
	{
		try {
			if (page.isVisible(locatorMap.get("cancel/refundFlight"))) {
				logAssert_Pass("Flight Refund Page is Validated");
			} else {
				logAssert_Fail("Failed to Get Text displayed on Element " + infoMap.get("cancel/refundFlight")
						+ ". Locator is: " + locatorMap.get("cancel/refundFlight"));
			}
		} catch (Exception e) {
			logAssert_Fail("Failed to Validate Flight Refund");

		}

	}

	public void performRebooking(String rebookOrigin, String rebookDestination, String rebookDate)
			throws InterruptedException, IOException

	{
		try {

			// Get Dates dynamically generated
			rebookDate = getFlightDate(rebookDate);

			waitAndClickOnTheElement("checkChangeFlightButton", elementVisibilityMaxTimeout);
			waitForElementToBeVisible("rebookingSearchButton", elementVisibilityMaxTimeout);

			// Click the Checkbox
			String rebookCheckBoxlocator = "//div[text()='" + rebookOrigin + " to " + rebookDestination
					+ "']/parent::*/parent::*/parent::*/parent::*/parent::*/parent::*//input[@type='checkbox']";

			String rebookDatelocator = "//div[text()='" + rebookOrigin + " to " + rebookDestination
					+ "']/parent::*/parent::*/parent::*/parent::*/parent::*/parent::*//input[contains(@id,'departureDate')]";

			waitAndCheckForElementVisibility(rebookCheckBoxlocator, elementVisibilityMaxTimeout);
			waitAndClick(rebookCheckBoxlocator);
			waitAndCheckForElementVisibility(rebookDatelocator, elementVisibilityMaxTimeout);
			waitAndClick(rebookDatelocator);

			page.locator(rebookDatelocator).fill(rebookDate);
			clickOnTheElement("rebookingSearchButton");

		} catch (Exception e) {
			logAssert_Fail("Failed to Rebook Flight");

		}

	}

	public void addRailandFlyWidgetforUser() throws InterruptedException, IOException
	{
		try {
			waitAndClickOnTheElement("addRailAndFlyButton", elementVisibilityMaxTimeout);
			waitForElementToBeVisible("railAndFlyHeader", elementVisibilityMaxTimeout);
			waitAndClickOnTheElement("firstClassSelectionRadioButton", elementVisibilityMaxTimeout);

			clickOnTheElement("outBoundCheckBox");
			clickOnTheElement("confirmRailAndFlyTicketBtn");
			waitForElementToBeVisible("confirmationOfRailAndFly", elementVisibilityMaxTimeout);

		} catch (Exception e) {
			logAssert_Fail("Failed to add Rail and Fly Widget for User");

		}

	}

	public String getFlightDate(String dateKey) throws InterruptedException, IOException
	{

		String dateOutput = dateKey;
		String requiredDateFormat = "dd/MM/yyyy";

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

	public void addPetInCartOneWayConfirm(String outboundPetName) throws InterruptedException
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

	@Override
	public HashMap<String, String> getLocatorMap() {
		return locatorMap;
	}

	@Override
	public HashMap<String, String> getInfoMap() {
		return infoMap;
	}

}
