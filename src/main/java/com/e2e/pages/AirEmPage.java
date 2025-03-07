package com.e2e.pages;

import java.io.IOException;
import java.util.HashMap;

import com.e2e.utils.DateManipulator;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.PlaywrightException;

import static java.util.Collections.fill;


public class AirEmPage extends BasePage {

	private HashMap<String, String> locatorMap = new HashMap<>();
	private HashMap<String, String> infoMap = new HashMap<>();

	// Constants
	final String locCabinClass1 = "//div[contains(text(),'";
	final String locCabinClass2 = "')]";

	final String locArrDate1 = "//td[contains(@aria-label,'";
	final String locArrDate2 = "')]";

	final String locAirlineName1 = "li:has-text('";
	final String locAirlineName2 = "'), maui-option[optionvalue='";

	final String oneWayTrip = "OW";
	final String roundTrip = "RT";
	final String multiCity = "MC";

	public AirEmPage() {
		// Locators
		locatorMap.put("AcceptorClose","//maui-link-button[@variant='close' and @aria-label='Close']");

		locatorMap.put("LoginFlagCloseHost", "maui-link-button.d-inline-flex.mb-0.text-right");
		locatorMap.put("innerLoginFlagCloseHost", "maui-icon");
		locatorMap.put("loginflagColse", "div.icon.small");
		locatorMap.put("loginflagClose", "//div[@class='modal-header']//div[2]");

		locatorMap.put("homePageImage",
				"//img[contains(@alt,'back to homepage')] | //a[contains(@aria-label,'go to home page')]");
		locatorMap.put("cookiesAcceptAllButton", "//button[@id='cm-acceptAll']");
		locatorMap.put("popUpCloseCrossButtonType1", "//button[@aria-label='Close']");
//		locatorMap.put("popUpCloseCrossButtonType2", "//i[@class='icon lh lh-close']");
		locatorMap.put("hotelTab", "//a[contains(@id,'hotel') and contains(@id,'toggle')]");
		locatorMap.put("flightsTab",
				"//a[contains(@id, 'flight') and substring(@id, string-length(@id) - string-length('-toggle') +1) = '-toggle' and not(contains(@id,'Hotel')) ]");
		locatorMap.put("flightAndHotelTab", "//a[contains(@id,'flightsHotelLasminuteCom')]");
		locatorMap.put("countryFromTxt", "(//input[contains(@id,\"flightQuery.flightSegments[0].originCode\")])[1]");
		locatorMap.put("tripTypeDropdown", "(//span[@class=\"dropdown-content mb-0\"])[1]");
//		locatorMap.put("tripTypeOW", "//li[div[text()='One-way']]");
		locatorMap.put("tripTypeOW", "(//li[@class='sel-item'])[1]");
//		locatorMap.put("tripTypeOW", "#dcep-a7d55076f-ee6b-469f-a9d1-262c1d986d20-flm-flight-tripType-item-2");
//*[@class="d-inline-flex align-items-center btn-sm mb-0 travel-details-opener"]
		locatorMap.put("travel_Details", "//*[@class=\"d-inline-flex align-items-center btn-sm mb-0 travel-details-opener\"]");
		locatorMap.put("Travel_class", "//*[@class=\"d-inline-flex align-items-center btn-sm mb-0 travel-details-opener\"]");
		locatorMap.put("travel_Detail", "//*[@class=\"d-inline-flex align-items-center btn-sm mb-0 travel-details-opener\"]");


		locatorMap.put("countryToTextField",
				"(//input[contains(@id,\"flightQuery.flightSegments[0].destinationCode\")])[1]");
		locatorMap.put("dateNextArrow",
				"(//*[@class=\"icon-pager-next icon\"])");
		locatorMap.put("calenderContinue", "//maui-button[@type='primary' and @aria-label='Continue'][.//span[text()='Continue']]");
		locatorMap.put("bookingClassAndTravllerType",
				"//button[contains(@class,'btn-link btn-sm mb-0 travel-details-opener')]");
		locatorMap.put("cabinClassDropDown", "//button[contains(@id,'modalForm.flightQuery.cabin')]/span");
		locatorMap.put("adultPaxPlusBtn", "//button[@class='plus controlbutton' and .//maui-icon[@use='icon-plus']]");
//		locatorMap.put("adultPaxPlusBtn","#stepper-unique[data-valid=""][data-optional=""][theme="lh"][mode="event"]");
		locatorMap.put("childPaxPlusBtn",
				"(//button[@class=\"btn-link mb-0 btn-plus border-left-0 stepper-border-gray\"])[1]");
		locatorMap.put("infantPaxPlusBtn",
				"(//button[@class=\"btn-link mb-0 btn-plus border-left-0 stepper-border-gray\"])[2]");
		locatorMap.put("cabinClassContinueBtn",
				"//div[@class='modal-footer']//button[@class='btn btn-primary']//span[contains(text(),'Continue') or contains(text(),'Weiter') or contains(text(),'Save')]");
		locatorMap.put("searchFlightsButton", "//*[@class=\"d-block\"]");
		locatorMap.put("clickOnMyProfile", "//*[contains(text(),'My profile')]");

		locatorMap.put("myBookingExpandButton", "//a[contains(@aria-controls,'Bookings-section')]");
		locatorMap.put("bookingCode", "//maui-input[@id='dcep-a1150d9ad-dea0-462c-847e-8cf787bcee14-mybookings-bookingQuery.bookingCode']");
		locatorMap.put("bookingLastName",
				"(//maui-input[@placeholder='Last name'])[1]");
		locatorMap.put("ContinueButton",
				"//maui-button[@type='primary' and @behavior='button']");
		locatorMap.put("ClosePopup","//maui-link-button[@variant='close' and @aria-label='Close']");
		locatorMap.put("flightStatusExpandButton", "//a[contains(@aria-controls,'flightStatus-section')]");
		locatorMap.put("flightStatusAirlineDropDown",
				"button[id*=\"flightstatus-flightStatusByFlightNumberRequest.airline\"],button#select");
		locatorMap.put("flightStatusAirlineNumber",
				"//*[contains(@id,\"flightStatusByFlightNumberRequest.flightNumber\")]");
		locatorMap.put("flightStatusSearchButton",
				"//*[contains(@id,\"flightStatus-section\")]//button[@type=\"submit\"]");

		locatorMap.put("showAllBookingsLink", "//a[contains(text(),'Show all bookings')]");

		locatorMap.put("errorNotificationDisplayed", "maui-notification[type='error']");
		locatorMap.put("shadowHost", "#undefined");

		locatorMap.put("departureReturnDateField", "//input[@placeholder='Departure - return' or @placeholder='Departure']");

		locatorMap.put("checkinExpandButton", "//a[contains(@aria-controls,'checkIn-section')]");
		locatorMap.put("checkinBookingCode",
				"//*[contains(@id,'checkIn-section')]//input[@placeholder='Booking code or ticket no.']");
		locatorMap.put("checkinFirstName", "//*[contains(@id,'checkIn-section')]//input[@placeholder='First name']");
		locatorMap.put("checkinLastName", "//*[contains(@id,'checkIn-section')]//input[@placeholder='Last name']");
		locatorMap.put("checkinSubmitButton",
				"//*[contains(@id,'checkIn-section')]/div/div/form//button[@type='submit']");

		locatorMap.put("footerSearchBarInput", "//div[@class='footer-area container-brand']//input[@name='searchbar']");
		locatorMap.put("faqBaggage", "//a[text()='How do I add extra baggage to my flight booking?']");
		locatorMap.put("faqLounge", "//a[text()='Where can I find information about the lounges?']");
		locatorMap.put("lastMinuteSitePageHeader", "//strong[text()='Your entire holiday in one click']");
		locatorMap.put("denyAllButton", "//button[text()='Deny all']");

		locatorMap.put("headerBaggage", "//h1[contains(text(),'ggage')]");
		locatorMap.put("headerLounge", "//h1[contains(text(),'ounge')]");

		// Info messages for all locators
		infoMap.put("AcceptorClose", "Accept or close Cookies Button");
		infoMap.put("cookiesAcceptAllButton", "Accept All Cookies Button");
		infoMap.put("popUpCloseCrossButtonType1", "Cross Close Button");
		infoMap.put("popUpCloseCrossButtonType2", "Alternate Cross Close Button");
		infoMap.put("hotelTab", "Hotel Tab in Airem Page");
		infoMap.put("flightsTab", "Flights Tab in Airem Page");
		infoMap.put("flightAndHotelTab", "Flights and Hotel Tab in Airem Page");
		infoMap.put("countryFromTxt", "Text Box to Select From Country");
		infoMap.put("tripTypeDropdown", "Drop Down to Select the Trip Type");
		infoMap.put("tripTypeOW", "Drop Down Selection for One Way Trip");
		infoMap.put("countryToTextField", "Text Box to Select To Country");
		infoMap.put("dateNextArrow", "Next Arrow Button in Date Selection Calendar");
		infoMap.put("calenderContinue", "Continue Button in Calendar after Date Selection");
		infoMap.put("bookingClassAndTravllerType", "Button to Select Traveller Type");
		infoMap.put("cabinClassDropDown", "Drop Down to Select Cabin Type");
		infoMap.put("adultPaxPlusBtn", "Button to Add 1 Pax to Adult Count");
		infoMap.put("childPaxPlusBtn", "Button to Add 1 Pax to Child Count");
		infoMap.put("infantPaxPlusBtn", "Button to Add 1 Pax to Infant Count");
		infoMap.put("cabinClassContinueBtn", "Button to Continue after Cabin Selection");
		infoMap.put("searchFlightsButton", "Button to Search Flight Availability");
		infoMap.put("clickOnMyProfile", "Button to Click on My Profile");

		infoMap.put("myBookingExpandButton", "Button to Expand My Bookings Section");
		infoMap.put("bookingCode", "Input box to enter Booking Code in My Booking Section");
		infoMap.put("bookingLastName", "Input box to enter Last Name in My Booking Section");
		infoMap.put("findBookingsButton", "Button to search Booking Code details in My Booking Section");

		infoMap.put("flightStatusExpandButton", "Button to Expand Flight Status Search Section");
		infoMap.put("flightStatusAirlineDropDown", "Drop Down to enter Flight Code in Flight Status Section");
		infoMap.put("flightStatusAirlineNumber", "Input box to enter Airline Number in Flight Status Section");
		infoMap.put("flightStatusSearchButton",
				"Button to search Flight Status details in Flight Status Search Section");
		infoMap.put("showAllBookingsLink", "Link to Show All Bookings in My Booking section");
		infoMap.put("errorNotificationDisplayed", "Notification showing error message");
		infoMap.put("shadowHost", "Shadow Host for Shadow Root displayed for error message");

		infoMap.put("departureReturnDateField", "Date field used to enter the Daparture and Return Date");

		infoMap.put("checkinExpandButton", "Button to Expand Check In Section from Homepage");
		infoMap.put("checkinBookingCode", "Input box to enter Booking Code in Check In Section from Homepage");
		infoMap.put("checkinFirstName", "Input box to enter First Name in Check In Section from Homepage");
		infoMap.put("checkinLastName", "Input box to enter Last Name in Check In Section from Homepage");
		infoMap.put("checkinSubmitButton", "Button to search Booking Code details in Check In Section from Homepage");

		infoMap.put("footerSearchBarInput", "Search bar on the footer of the page to search for a content");
		infoMap.put("faqBaggage", "Link to a frequently asked question related to Baggage");
		infoMap.put("faqLounge", "Link to a frequently asked question related to Lounge");
		infoMap.put("lastMinuteSitePageHeader", "Heading displayed in home page of Lastminute.com");
		infoMap.put("denyAllButton", "Button to Deny All");

		infoMap.put("headerBaggage", "Header of the page that displayed the Baggage Details");
		infoMap.put("headerLounge", "Header of the page that displayed the Lounge Details");

		infoMap.put("loginflagClose", "login popup flag is Closed");
		infoMap.put("", "Header of the page that displayed the Baggage Details");

	}

	public void ClickAcceptorClose() throws InterruptedException, IOException {
		waitAndClickOnTheElement("AcceptorClose", elementVisibilityMaxTimeout);
		page.click("AcceptorClose");
		log.info("AcceptorClose");

	}


	public void clicksAiremPagePopUps() throws InterruptedException {
		long totalTimeout = (elementVisibilityMinTimeout * elementVisibilityMaximumRetry) * 1000;
		long startTime = System.currentTimeMillis();
		int individualTimeout = elementVisibilityPollIntervalTimeout;
		page.waitForTimeout(5000);
		// Define the elements for cookies and pop-up close buttons
		waitForElementToBeVisible("cookiesAcceptAllButton", elementVisibilityMaxTimeout);
		String[] elementsToCheck = {"cookiesAcceptAllButton", "popUpCloseCrossButtonType1"};

		// Loop until all pop-ups are handled or total timeout is reached
		while (System.currentTimeMillis() - startTime < totalTimeout) {
			for (String element : elementsToCheck) {
				if (isTheElementVisible(element, individualTimeout)) {
					try {
						clickOnTheElement(element); // Click the element
						log.info("Clicked on: " + infoMap.get(locatorMap.get(element)) + ". Locator: " + locatorMap.get(element));
						return; // Exit as soon as any pop-up is clicked
					} catch (Exception e) {
						log.info("Failed to select: " + infoMap.get(locatorMap.get(element)) + ". Locator: " + locatorMap.get(element));
					}
				}
			}
			HardWait(1); // Small wait to ensure background DOM load
		}
	}

//	public void clicksAiremPagePopUps() throws InterruptedException {
//
//		long totalTimeout = (elementVisibilityMinTimeout * elementVisibilityMaximumRetry) * 500; // Total timeout for
//																									// all pop-ups in
//																									// milliseconds
//		long startTime = System.currentTimeMillis();
//		int individualTimeout = elementVisibilityPollIntervalTimeout; // Timeout for each individual pop-up check
//
//		boolean cookiesClicked = false;
//		boolean type1Clicked = false;
//		boolean type2Clicked = false;
//
//		while (System.currentTimeMillis() - startTime < totalTimeout) {
//			if (!cookiesClicked && isTheElementVisible("cookiesAcceptAllButton", elementVisibilityMinTimeout)) {
//				try {
//					clickOnTheElement("cookiesAcceptAllButton");
//					cookiesClicked = true;
//				} catch (Exception e) {
//					log.info("Fails to select " + infoMap.get(locatorMap.get("cookiesAcceptAllButton"))
//							+ ". Locator is: " + locatorMap.get("cookiesAcceptAllButton"));
//				}
//			}
//
//			if (!type1Clicked && !type2Clicked
//					&& isTheElementVisible("popUpCloseCrossButtonType1", individualTimeout)) {
//				try {
//					clickOnTheElement("popUpCloseCrossButtonType1");
//					type1Clicked = true;
//				} catch (Exception e) {
//					log.info("Fails to select " + infoMap.get(locatorMap.get("popUpCloseCrossButtonType1"))
//							+ ". Locator is: " + locatorMap.get("popUpCloseCrossButtonType1"));
//				}
//			}
//
//			if (!type1Clicked && !type2Clicked
//					&& isTheElementVisible("popUpCloseCrossButtonType2", individualTimeout)) {
//				try {
//					clickOnTheElement("popUpCloseCrossButtonType2");
//					type2Clicked = true;
//				} catch (Exception e) {
//					log.info("Fails to select " + infoMap.get(locatorMap.get("popUpCloseCrossButtonType2"))
//							+ ". Locator is: " + locatorMap.get("popUpCloseCrossButtonType2"));
//				}
//			}
//
//			if (cookiesClicked && (type1Clicked || type2Clicked))
//				break;
//			if (type1Clicked || type2Clicked)
//				break;
//
//			HardWait(1); // Small wait to ensure background DOM load
//		}
//	}

	public void searchForFlights(String tripType, String origin, String destination, String outboundDate,
			String inboundDate, String cabin, String passenger) throws InterruptedException, IOException {

		// Get Dates dynamically generated
		outboundDate = getFlightDate(outboundDate);
		inboundDate = getFlightDate(inboundDate);

		activateCitySelectionDropDownFlightSearch(origin);
		selectTripType(tripType);
		selectOriginCity(origin);
		selectDestinationCity(destination);
		depDateSelection(outboundDate);

		if (!tripType.equalsIgnoreCase(oneWayTrip)) {
			arrDateSelection(inboundDate);
		}
		clickOnTheElement("calenderContinue");
//		cabinSelection(cabin);
//		travellerCountSelection(passenger);
		selectTravelerAndClass(cabin,passenger);
		clickOnTheElement("searchFlightsButton");
//		takeExtraScreenshot();

	}


	public void selectDestinationCity(String destination) throws InterruptedException, IOException {
		enterTextOnTheElement("countryToTextField", destination);
		selectSugeestedRoute();
	}

	public void selectOriginCity(String origin) throws InterruptedException, IOException {
		waitAndClickOnTheElement("countryFromTxt", elementVisibilityMaxTimeout);
//		enterTextOnTheElement("countryFromTxt", origin);
//		selectSugeestedRoute();
		enterTextOnTheElement("countryFromTxt", origin);
		log.info(origin);
		selectSugeestedRoute();
	}

	public void activateCitySelectionDropDownFlightSearch(String origin) throws InterruptedException, IOException {
		/*
		 * Workaround for activate city selection. The issue is that no values will be
		 * visible in drop-down at certain times and we need to change the focus on tabs
		 * for the drop down options to appear. Same workaround implemented by CSI team
		 * for same issue
		 */
		try {
			waitAndClickOnTheElement("hotelTab", elementVisibilityMinTimeout);
			waitAndClickOnTheElement("flightsTab", elementVisibilityMinTimeout);

			page.keyboard().press("Delete");
			log.info("The origin city displayed is" + origin);
		} catch (Exception e) {
			logAssert_Fail("Failed to Activate City Dropdowns");
		}

	}

	public void selectTripType(String tripType) throws InterruptedException, IOException {
		try {
			waitForElementToBeVisible("tripTypeDropdown", elementVisibilityMaxTimeout);
			// Default Trip Type is Round Trip will be selected if input is not One Way
			if (tripType.equalsIgnoreCase(oneWayTrip)) {
				clickOnTheElement("tripTypeDropdown");
				clickOnTheElement("tripTypeOW");
			}
		} catch (Exception e) {
			logAssert_Fail("Failed to Select Trip Type");
		}

	}

	public void selectSugeestedRoute() throws InterruptedException {
		// Need to wait for the drop down to be displayed completely for KeyPress to
		// occur
		HardWait(3);
		final String pressArrowDownKey = "ArrowDown";
		final String pressEnterKey = "Enter";
		final String pressTabKey = "Tab";
		page.keyboard().press(pressArrowDownKey);
		page.keyboard().press(pressEnterKey);
		page.keyboard().press(pressTabKey);

	}

	public void depDateSelection(String outboundDate) throws InterruptedException {
		String depDateLocator = "//td[contains(@aria-label,'" + outboundDate + "')]";
		page.waitForTimeout(10000);
		// Additional check to ensure Calendar Screen is displayed
		if (!isTheElementVisible("dateNextArrow", elementVisibilityMinTimeout)) {
			log.info("Clicking and Displaying the Calendar view");
//			waitAndClickOnTheElement("ClosePopup",1);
			getByPlaceholder("departureReturnDateField").click();

//			clickOnTheElement("departureReturnDateField");
		}

		for (int monthCounter = 0; monthCounter < 12; monthCounter++) {
			try {
				if (waitAndCheckForElementVisibility(depDateLocator, elementVisibilityMinTimeout)) {
					waitAndClick(depDateLocator);
					break;
				}
			} catch (Exception e) {
				log.info("Departure Date not visible in current month displayed ! Need to scroll to Next Month ");
			}
			try {
				clickOnTheElement("dateNextArrow");
			} catch (Exception e) {
				logAssert_Fail("Failed to Select Departure Date");
			}
		}
	}

	public void arrDateSelection(String inboundDate) throws InterruptedException, IOException {

		String arrDateLocator = locArrDate1 + inboundDate + locArrDate2;
		for (int monthCounter = 0; monthCounter < 12; monthCounter++) {
			try {
				if (waitAndCheckForElementVisibility(arrDateLocator, elementVisibilityMinTimeout)) {
					waitAndClick(arrDateLocator);
					break;
				}
			} catch (Exception e) {
				log.info("Arrival Date not visible in current month displayed ! Need to scroll to Next Month ");
			}
			try {
				clickOnTheElement("dateNextArrow");
			} catch (Exception e) {
				clickOnTheElement("dateNextArrowAlt");
				logAssert_Fail("Failed to Select Arrival Date");

			}
		}
	}
////////////////Shadow root////////////open////////
private void selectTravelerAndClass(String cabin,String Passenger) throws InterruptedException {
	page.waitForTimeout(1000);
	waitAndClickOnTheElement("travel_Details",1);
	page.getByTitle("Travel class").click();
	performGetByRole("dropdown",cabin).click();

	int adult = Character.getNumericValue(Passenger.split(",")[0].charAt(0));
	int child = Character.getNumericValue(Passenger.split(",")[1].charAt(0));
	int infant = Character.getNumericValue(Passenger.split(",")[2].charAt(0));

	if (adult != 0) {

		for (int i = 0; i < adult - 1; i++) {
			page.waitForTimeout(1500);
			page.waitForLoadState();
			getElementByTagAndText("div", "Adults").locator("maui-stepper").locator("button").nth(1).click();
		}
	}
	if (child != 0) {

		for (int i = 0; i < child; i++) {
			page.waitForTimeout(1500);
			page.waitForLoadState();
			getElementByTagAndText("div", "Children").locator("maui-stepper").locator("button").nth(4).click();
		}
	}
	if (infant != 0) {

		for (int i = 0; i < infant; i++) {
			page.waitForTimeout(1500);
			page.waitForLoadState();
			getElementByTagAndText("div", "Infant").locator("maui-stepper").locator("button").nth(7).click();
		}

	}
	page.waitForTimeout(5000);
	waitAndClickOnTheElement("ContinueButton",5);
//	performGetByRole("button","Continue").click();
}

	public void closeLoginFlag() throws InterruptedException {
		try {
			page.waitForLoadState();
			page.waitForTimeout(1000);
			Locator shadowHost = page.locator("maui-link-button.d-inline-flex.mb-0.text-right");
			Locator shadowHost2 = shadowHost.locator("maui-icon");
			Locator shadowElement = shadowHost2.locator("div.icon.small");

//			 Now you can interact with the shadow element
			if (shadowElement.isVisible()) {
				shadowElement.click();
				log.info("Login Modal is present on home page.");

			} else {
				log.info("Login Modal is not present on home page.");
			}

		} catch (Exception e) {
			logAssert_Fail("No Login Flag appeared : ");

		}
	}

////////////////Shadow////////end//////
public void CloseflagLoginn() throws IOException, InterruptedException {
page.waitForTimeout(5000);
//	String tenantName = scenarioContext.getContext("Tenant_Name");
//	if (tenantName.equalsIgnoreCase("LH"))
	{
		if (isTheElementVisible("loginflagClose", elementVisibilityMinTimeout)) {
			try {
				clickOnTheElement("loginflagClose");
				log.info("Clicked on login flag Close");
			} catch (Exception e) {
				log.info("Fails to select " + infoMap.get(locatorMap.get("cookiesAcceptAllButton"))
						+ ". Locator is: " + locatorMap.get("cookiesAcceptAllButton"));
			}
		}
	}
}
public void cabinSelection(String cabin) throws InterruptedException, IOException {
		waitAndClickOnTheElement("bookingClassAndTravllerType", elementVisibilityMaxTimeout);
		// Default selection is Economy and the other option available is Business
		if (!cabin.equalsIgnoreCase("Economy")) {
			clickOnTheElement("cabinClassDropDown");
			String locatorCabinClass = locCabinClass1 + cabin + locCabinClass2;
			waitAndClick(locatorCabinClass);
		}

	}

	public void travellerCountSelection(String passenger) throws InterruptedException, IOException {
		int adult = Character.getNumericValue(passenger.split(",")[0].charAt(0));
		int child = Character.getNumericValue(passenger.split(",")[1].charAt(0));
		int infant = Character.getNumericValue(passenger.split(",")[2].charAt(0));

		if (adult != 0) {
			for (int counter = 0; counter < adult - 1; counter++) {
				clickOnTheElement("adultPaxPlusBtn");
			}
		}
		if (child != 0) {
			for (int counter = 0; counter < child; counter++) {
				clickOnTheElement("childPaxPlusBtn");
			}
		}

		if (infant != 0) {
			for (int counter = 0; counter < infant; counter++) {
				clickOnTheElement("infantPaxPlusBtn");
			}
		}

		waitAndClickOnTheElement("cabinClassContinueBtn", elementVisibilityMaxTimeout);
	}



	public void selectMyProfile() throws InterruptedException, IOException {
		try {
			waitAndClickOnTheElement("clickOnMyProfile", elementVisibilityMaxTimeout);
			log.info("Clicked on my profile");
		} catch (Exception e) {
			logAssert_Fail("Failed to Select User Profile");

		}
	}

	public void retrieveGeneratedPNRMyBooking() throws InterruptedException, IOException {
		String pnrRetrieved = scenarioContext.getContext("PNR_Retrieved");
		String firstNameRetrieved = scenarioContext.getContext("FirstName_Retrieved");
		String lastNameRetrieved = scenarioContext.getContext("LastName_Retrieved");

		if (pnrRetrieved == null || firstNameRetrieved == null || lastNameRetrieved == null) {
			log.error("One or more required context values are missing: PNR, First Name, or Last Name.");
			throw new IllegalArgumentException("Required context values are missing.");
		}

		log.info("PNR Retrieved is : " + pnrRetrieved);
		log.info("First Name Retrieved is : " + firstNameRetrieved);
		log.info("Last Name Retrieved is : " + lastNameRetrieved);
		page.waitForTimeout(10);
//        clickOnTheElement("ClosePopup");
		scrollElementToVisibility("myBookingExpandButton");
		waitForElementToBeVisible("myBookingExpandButton", elementVisibilityMaxTimeout);
		clickOnTheElement("myBookingExpandButton");
//		waitForElementToBeVisible("bookingCode", elementVisibilityMaxTimeout);
		getByPlaceholder("Booking code").last().type(pnrRetrieved);
		getByPlaceholder("Last name").last().type(lastNameRetrieved);
//		enterTextOnTheElement("bookingCode", pnrRetrieved);
//		enterTextOnTheElement("bookingLastName", lastNameRetrieved);
//		getElementByTagAndText("span","Find Bookings").click();
		performGetByRole("button","Find Bookings").click();
//		clickOnTheElement("findBookingsButton");
	}

	public void addDetailsInMyBookingList() throws InterruptedException, IOException {
		String pnrRetrieved = scenarioContext.getContext("PNR_Retrieved");
		String firstNameRetrieved = scenarioContext.getContext("FirstName_Retrieved");
		String lastNameRetrieved = scenarioContext.getContext("LastName_Retrieved");

		log.info("PNR Retrieved is : " + pnrRetrieved);
		log.info("First Name Retrieved is : " + firstNameRetrieved);
		log.info("Last Name Retrieved is : " + lastNameRetrieved);

		waitAndClickOnTheElement("myBookingExpandButton", elementVisibilityMaxTimeout);
		waitAndClickOnTheElement("showAllBookingsLink", elementVisibilityMinTimeout);
	}

	public void showAllBookingListCreated() throws InterruptedException, IOException {

		// Locate the shadow host
		Locator shadowHost = page.locator(locatorMap.get("shadowHost"));
		waitForElementToBeVisible("shadowHost", elementVisibilityMaxTimeout);

		// Access the shadow root and query the shadow DOM
		ElementHandle shadowRoot = (ElementHandle) shadowHost.evaluateHandle("host => host.shadowRoot");

		ElementHandle shadowElement = (ElementHandle) shadowRoot
				.evaluateHandle("root => root.querySelector('maui-notification[type=\"error\"]')");

		// Check if the error notification is visible
		if (shadowElement != null && Boolean.TRUE.equals(shadowElement.isVisible())) {
			logAssert_Fail("Error message displayed while opening all booking list of user");
		} else {
			log.info("No error message was displayed");
		}

	}
	public void clickToHomePage() throws InterruptedException, IOException {
		clickOnTheElement("homePageImage");
	}
	public void navigateToFlightStatusSearch() throws InterruptedException, IOException {
		waitAndClickOnTheElement("flightStatusExpandButton", elementVisibilityMaxTimeout);
	}

	public void enterFlightStatusSearchDetails(String airline, String flightNumber)
			throws InterruptedException, IOException {
		waitAndClickOnTheElement("flightStatusAirlineDropDown", elementVisibilityMaxTimeout);

		String airlineNameLocator = "li:has-text('" + airline + "'), maui-option[optionvalue='" + airline
				+ "'][id*='select-']";

		waitAndCheckForElementVisibility(airlineNameLocator, elementVisibilityMaxTimeout);
		waitAndClick(airlineNameLocator);
		enterTextOnTheElement("flightStatusAirlineNumber", flightNumber);
	}

	public void clickSearchFlightStatus() throws InterruptedException, IOException {
		waitAndClickOnTheElement("flightStatusSearchButton", elementVisibilityMaxTimeout);
	}

	public String getFlightDate(String dateKey) throws InterruptedException, IOException {

		String dateOutput = dateKey;
		String requiredDateFormat = "dd MMMM yyyy";

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

	public void performCheckInFromHomePage() throws InterruptedException, IOException {
		String pnrRetrieved = scenarioContext.getContext("PNR_Retrieved");
		String firstNameRetrieved = scenarioContext.getContext("FirstName_Retrieved");
		String lastNameRetrieved = scenarioContext.getContext("LastName_Retrieved");

		if (pnrRetrieved == null || firstNameRetrieved == null || lastNameRetrieved == null) {
			log.error("One or more required context values are missing: PNR, First Name, or Last Name.");
			throw new IllegalArgumentException("Required context values are missing.");
		}

		log.info("PNR Retrieved is : " + pnrRetrieved);
		log.info("First Name Retrieved is : " + firstNameRetrieved);
		log.info("Last Name Retrieved is : " + lastNameRetrieved);

		waitForElementToBeVisible("checkinExpandButton", elementVisibilityMaxTimeout);
		clickOnTheElement("checkinExpandButton");
		waitForElementToBeVisible("checkinBookingCode", elementVisibilityMaxTimeout);
		enterTextOnTheElement("checkinBookingCode", pnrRetrieved);
		enterTextOnTheElement("checkinFirstName", firstNameRetrieved);
		enterTextOnTheElement("checkinLastName", lastNameRetrieved);
		clickOnTheElement("checkinSubmitButton");
	}
	public void enterPNRandlastname(String PNR, String Lastname) throws InterruptedException, IOException {
		scrollElementToVisibility("myBookingExpandButton");
		waitForElementToBeVisible("myBookingExpandButton", elementVisibilityMaxTimeout);
		clickOnTheElement("myBookingExpandButton");
		getByPlaceholder("Booking code").last().type(PNR);
		getByPlaceholder("Last name").last().type(Lastname);
		performGetByRole("button", "Find Bookings").click();
	}
//		getByTestID("dcep-a1150d9ad-dea0-462c-847e-8cf787bcee14-mybookings-bookingQuery.bookingCode").click();
//		getElementByTagAndText("maui-label","Booking code").last().type(PNR);
//		getByLabel("Booking code").type(PNR);
		// Locate the shadow host (`maui-input`)
//        page.locator("maui-input");

// Access the shadow root and interact with elements inside
//		page.locator("shadowRoot >> maui-input#dcep-a73794729-ba22-4d21-94f3-869f23f22d99-mybookings-bookingQuery.bookingCode");
//		// Fill the input field
////		page.fill("ABC123",PNR);
//		enterText("ABC123",PNR);

// Optionally, access the label
//page.locator("shadowRoot >> maui-label[for='dcep-a73794729-ba22-4d21-94f3-869f23f22d99-mybookings-bookingQuery.bookingCode-input']");
//		enterText("ABC123",PNR);
//waitAndClickOnTheElement("BookingCode",elementVisibilityMinTimeout);
//		enterTextOnTheElement("BookingCode", PNR);
//		enterTextOnTheElement("bookingLastName",Lastname);


//	public void enterIDandlastname(String PNR, String Lastname) throws InterruptedException {
//
//		try {
//			scrollElementToVisibility("myBookingExpandButton");
//			waitForElementToBeVisible("myBookingExpandButton", elementVisibilityMaxTimeout);
//			clickOnTheElement("myBookingExpandButton");
//
//			Locator shadowHost = page.locator("maui-input");
//
//			Locator shadowHost2 = shadowHost.locator("maui-label");
//
//			Locator shadowElement = shadowHost2.locator(getByText("Booking code"));
////			Locator shadowElement = shadowHost2.locator("input.control.validation-input");
//
//			// Now you can interact with the shadow element
//
//			if (shadowElement.isVisible()) {
//
//				shadowElement.click();
//				shadowElement.click();
////				clickONTheElement("shadowElement");
////				enterTextOnTheElement("shadowElement",PNR);
//
//				enterText("trty",PNR);
//				page.waitForTimeout(5000);
//				log.info("Entered Booking ID");
//			} else {
//
//				log.info("Not Entered Booking ID");
//
//			}
//
//		} catch (Exception e) {
//			logAssert_Fail("No Booking ID ");
//
//		}
//
//	}

	public void searchInFooterSearchBar(String searchOption) throws InterruptedException, IOException {
		waitAndClickOnTheElement("footerSearchBarInput", elementVisibilityMaxTimeout);
		enterTextOnTheElement("footerSearchBarInput", searchOption);
		page.keyboard().press("Enter");
	}

	public void verifyLoungeFaq() throws InterruptedException, IOException {
		waitForElementToBeVisible("faqLounge", elementVisibilityMaxTimeout);
	}

	public void verifyBaggageFaq() throws InterruptedException, IOException {
		waitForElementToBeVisible("faqBaggage", elementVisibilityMaxTimeout);
	}

	public void verifyBaggagePageHeader() throws InterruptedException, IOException {
		waitForElementToBeVisible("headerBaggage", elementVisibilityMaxTimeout);
	}

	public void verifyLoungePageHeader() throws InterruptedException, IOException {
		waitForElementToBeVisible("headerLounge", elementVisibilityMaxTimeout);
	}

	public void verifyFlightandHotelTab() throws InterruptedException, IOException {
		waitAndClickOnTheElement("flightAndHotelTab", elementVisibilityMaxTimeout);
		waitAndClickOnTheElement("denyAllButton", elementVisibilityMaxTimeout);
		waitAndClickOnTheElement("lastMinuteSitePageHeader", elementVisibilityMaxTimeout);
	}

	public void verifyLinkText(String expectedLinkText) throws InterruptedException {
		String linkLocator = "//a[text()='+expectedLinkText+']";
		log.info("The link locator is :" + linkLocator);
		boolean linkVisibilityStatus = waitAndCheckForElementVisibility(linkLocator, elementVisibilityMaxTimeout);
		if (!linkVisibilityStatus) {
			logAssert_Fail("The expected link with text " + expectedLinkText + " was not found");
		}

	}

	public void clickHomePageFooterMenu(String searchTabName) throws InterruptedException {
		String linkLocator = "//footer[@role='contentinfo']//*[contains(text(),'" + searchTabName + "')] [1]";
		log.info("The link locator is :" + linkLocator);
		waitAndCheckForElementVisibility(linkLocator, elementVisibilityMinTimeout);
		waitAndClick(linkLocator);
	}

	public void clickHomePageHeaderMenu(String searchTabName) throws InterruptedException {
		String linkLocator = "//*[@id=\"header\"]//span[text()='" + searchTabName + "']";
		log.info("The link locator is :" + linkLocator);
		waitAndCheckForElementVisibility(linkLocator, elementVisibilityMinTimeout);
		waitAndClick(linkLocator);
	}

	public void clickLinkText(String expectedLinkText) throws InterruptedException {
		String linkLocator = "//*[contains(text(),'" + expectedLinkText + "')]";
		log.info("The link locator is :" + linkLocator);
		waitAndCheckForElementVisibility(linkLocator, elementVisibilityMinTimeout);
		waitAndClick(linkLocator);
	}

	// * This function is TEMPORARY FIX to ByPASS Defect Number CART-132958*//
	// MUST BE DELETED once Defect is FIXED *//

	public void temporaryByPassFixforPrivacyIssue() throws IOException, InterruptedException {

		String tenantName = scenarioContext.getContext("Tenant_Name");
		if (tenantName.equalsIgnoreCase("LH")) {
			if (isTheElementVisible("cookiesAcceptAllButton", elementVisibilityMinTimeout)) {
				try {
					clickOnTheElement("cookiesAcceptAllButton");
				} catch (Exception e) {
					log.info("Fails to select " + infoMap.get(locatorMap.get("cookiesAcceptAllButton"))
							+ ". Locator is: " + locatorMap.get("cookiesAcceptAllButton"));
				}
			}
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
