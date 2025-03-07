package com.e2e.pages;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class PassengerDetailsPage extends BasePage {

	private HashMap<String, String> locatorMap = new HashMap<>();
	private HashMap<String, String> infoMap = new HashMap<>();

	// Constants
	final String locTitle1 = "//span[@aria-label='";
	final String locTitle2 = "']";

	public PassengerDetailsPage() {
		// Locators
		locatorMap.put("passengerDetailsConfimButton", "//button[contains(@class,'nextBtn')]");

		locatorMap.put("bookWithoutLoginCheckbox", "//*[@id='mat-checkbox-1']");
		locatorMap.put("passengerDetailsPleaseSelectOption", "//*[contains(text(),'Please select -')]");
		locatorMap.put("passengerTitle",
				"(//span[contains(@class,'mat-option-text')]//span[contains(text(),'Mr')])[1]");
		locatorMap.put("passengerFirstName", "//input[@formcontrolname='firstName']");
		locatorMap.put("passengerLastName", "//input[@formcontrolname='lastName']");
		locatorMap.put("passengerEmail", "//input[@placeholder='Your email address']");
		locatorMap.put("passengerCountryCode", "//input[@placeholder='Your country calling code']");
		locatorMap.put("passengerPhoneNumber", "//input[@placeholder='Your mobile phone']");
		locatorMap.put("passengerDateofBirth", "//*[contains(@id,\"PersonalInfodob\")]");
		locatorMap.put("getTravelID","//input[@id='mat-mdc-checkbox-1-input']");
		locatorMap.put("sugarPot","//input[@class='mdc-checkbox__native-control']");
		locatorMap.put("ProminentLoginButton","//[@class='mdc-button__label']");


		// Info messages for all locators
		infoMap.put("passengerDetailsConfimButton", "Button to Confirm passenger Details");

		infoMap.put("bookWithoutLoginCheckbox", "Checkbox to Book without Login");
		infoMap.put("passengerDetailsPleaseSelectOption", "Passenger Selection Option Dropdown");
		infoMap.put("passengerTitle", "Title of the Passenger");
		infoMap.put("passengerFirstName", "First Name of the Passenger");
		infoMap.put("passengerLastName", "Last Name of the Passenger");
		infoMap.put("passengerEmail", "Email of the Passenger");
		infoMap.put("passengerCountryCode", "Country Code of the Passenger");
		infoMap.put("passengerPhoneNumber", "Phone number of the Passenger");
		infoMap.put("passengerDateofBirth", "Date of Birth of the Passenger");
		infoMap.put("getTravelID", "Ticked getTravelID");
		infoMap.put("sugarPot", "Ticked sugarPot");
		infoMap.put("ProminentLoginButton", "Clicks Prominent Login Button");


	}

	public void clickConfirmPassengerDetailsButton() throws InterruptedException, IOException {
		try {
			waitAndClickOnTheElement("passengerDetailsConfimButton", elementVisibilityMaxTimeout);
		} catch (Exception e) {
			logAssert_Fail("Failed to confirm passenger details as logged in user");
		}

	}

	public void enterPassengerDetails(String title, String firstname, String lastname, String dateofbirth,
			String gender, String emailid, String countrycode, String phoneno)
			throws InterruptedException, IOException {
		try {

			waitAndClickOnTheElement("passengerDetailsPleaseSelectOption", elementVisibilityMaxTimeout);
			String titleLocator = locTitle1 + title + locTitle2;
			waitAndClick(titleLocator);

			enterTextOnTheElement("passengerFirstName", firstname);
			enterTextOnTheElement("passengerLastName", lastname);
			enterTextOnTheElement("passengerEmail", emailid);
			enterTextOnTheElement("passengerCountryCode", countrycode);
			// Hard waited added as at time it takes time for drop down to appear
			HardWait(1);
			enterTextOnTheElement("passengerPhoneNumber", phoneno);
//			clickOnTheElement("getTravelID");
//			clickOnTheElement("sugarPot");
			takeExtraScreenshot();
			// Enter additional details for US and other related markets

			if (isTheElementVisible("passengerDateofBirth", elementVisibilityMinTimeout)) {
				enterTextOnTheElement("passengerDateofBirth", dateofbirth);
				String genderLocator = "//span[contains(text(),\"" + gender + "\")]";
				waitAndClick(genderLocator);
			}

			takeExtraScreenshot();

		} catch (Exception e) {
			logAssert_Fail("Failed to enter passenger details as non logged in user");
		}
	}
	public void clickProminentLoginButton() throws InterruptedException, IOException {
		try {
			waitAndClickOnTheElement("ProminentLoginButton", elementVisibilityMaxTimeout);
		} catch (Exception e) {
			logAssert_Fail("Failed to confirm passenger details as logged in user");
		}

	}


//	public void enterPassengerDetailsForNonLoggedInUser(String jsonKeyDetails, String objectKey) {
//		String pax = commonActions.getTestData("Passenger_Count");
//		try {
//			int adult = Character.getNumericValue(pax.split(",")[0].charAt(0));
//			int child = Character.getNumericValue(pax.split(",")[1].charAt(0));
//			int infant = Character.getNumericValue(pax.split(",")[2].charAt(0));
//			int totalPax = adult + child + infant;
//			int totalPaxCounter = 0;
//
//			FirstName=commonActions.generateRandomString(5);
//			LastName=commonActions.generateRandomString(5);
//			for (int i = 0; i < adult; i++) {
//
//				if (!(page.locator(commonActions.getLocatorStr("userProfileOnPassengerDetailsPage")).isVisible())) {
//					commonActions.clickAndThenSelectValFromDropDownList("Title_dropdown", "Mr.");
//					if(i==0){
//						enterText("firstName", FirstName);
//						enterText("lastName", LastName);
//						Email_address = commonActions.generateRandomEmail();
//						enterText("emailAddress", Email_address);
//						enterTextOnTheElement("countryCode", "Germany");
//						enterText("phoneNumber", "9876543210");
//					} else {
//						commonActions.enterText("firstName", "Test"+commonActions.generateRandomString(3));
//						commonActions.enterText("lastName", "User"+commonActions.generateRandomString(5));
//					}
//				}
//
//				totalPaxCounter += 1;
//				if (i == (adult - 1) && totalPaxCounter == totalPax) {
//					commonActions.click(objectKey);
//				} else {
//					commonActions.click("Next_passenger");
//				}
//			}
//			for (int i = 0; i < child; i++) {
//
//				enterText("firstName", "Test"+commonActions.generateRandomString(5));
//			enterText("lastName", "User"+page.generateRandomString(5));
//				randonDOB("child");
//				commonActions.click("child_DOB_calender_icon");
//				String selectChildYear = String.format(commonActions.getLocatorStr("commonCalenderXpath") + childyear + " ']");
//				this.commonActions.page.locator(selectChildYear).click();
//
//				String selectChildMonth = String.format(commonActions.getLocatorStr("commonCalenderXpath") + monthDOB + " ']");
//				this.commonActions.page.locator(selectChildMonth).click();
//
//				String selectChildDay = String.format(commonActions.getLocatorStr("commonCalenderXpath") + dayDOB + " ']");
//				this.commonActions.page.locator(selectChildDay).click();
//				totalPaxCounter += 1;
//
//				if (i == (child - 1) && totalPaxCounter == totalPax) {
//					commonActions.click(objectKey);
//				} else {
//					commonActions.click("Next_passenger");
//				}
//			}
//
//			for (int i = 0; i < infant; i++) {
//				commonActions.enterText("firstName", "Test"+commonActions.generateRandomString(5));
//				commonActions.enterText("lastName", "User"+commonActions.generateRandomString(5));
//				randonDOB("infant");
//				commonActions.click("child_DOB_calender_icon");
//				String selectChildYear = String.format(commonActions.getLocatorStr("commonCalenderXpath") + infantyear + " ']");
//				this.commonActions.page.locator(selectChildYear).click();
//
//				String selectChildMonth = String.format(commonActions.getLocatorStr("commonCalenderXpath") + monthDOB + " ']");
//				this.commonActions.page.locator(selectChildMonth).click();
//
//				String selectChildDay = String.format(commonActions.getLocatorStr("commonCalenderXpath") + dayDOB + " ']");
//				this.commonActions.page.locator(selectChildDay).click();
//
//				totalPaxCounter += 1;
//				if (i == (infant - 1) && totalPaxCounter == totalPax) {
//					commonActions.click(objectKey);
//				} else {
//					page.click("Next_passenger");
//				}
//			}
//			commonActions.waitForTimeOutAndPageLoad(commonActions.wait5Sec);
//			page.click("Continue_to_payment");
//		} catch (Exception e) {
//			String errMsg = String.format("Passenger : enter Data failed");
//			log.info(errMsg + " : " + e);
//
//		}
//	}
//	 To Generate random Date of Birth
//	public String randonDOB(String pax) {
//		final String pattern = "dd/MM/yyyy";
//		String DOB = "";
//		String dateInString = new SimpleDateFormat(pattern).format(new Date());
//		String currentYear = dateInString.substring(dateInString.length() - 4);
//		int currentInfantYear = Integer.parseInt(currentYear) - 1;
//		int adultMinYear = Integer.parseInt(currentYear) - 18;
//		int childMinYear = Integer.parseInt(currentYear) - 10;
//		int childMaxYear = Integer.parseInt(currentYear) - 4;
//		int infantMinYear = Integer.parseInt(currentYear) - 1;
//
//		int day = 1 + (int) Math.round(Math.random() * (27));
//		int month = 1 + (int) Math.round(Math.random() * (11));
//
//		// Use SimpleDateFormat to format the month as "MMM" and convert to uppercase
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.MONTH, month - 1);  // Calendar months are 0-based, so subtract 1
//		String monthDOB = new SimpleDateFormat("MMM").format(calendar.getTime()).toUpperCase();
//
//		// Generate day with leading zero if needed
//		String dayDOB = String.valueOf(day);
//
//		// Generate years based on the pax category
//		adultyear = 1947 + (int) Math.round(Math.random() * (adultMinYear - 1947));
//		childyear = childMinYear + (int) Math.round(Math.random() * (childMaxYear - childMinYear));
//		infantyear = infantMinYear + (int) Math.round(Math.random() * (currentInfantYear - infantMinYear));
//
//		// Set the correct DOB based on pax type
//		if (pax.equals("adult")) {
//			DOB = dayDOB + "/" + monthDOB + "/" + String.valueOf(adultyear);
//		} else if (pax.equals("child")) {
//			DOB = dayDOB + "/" + monthDOB + "/" + String.valueOf(childyear);
//		} else if (pax.equals("infant")) {
//			DOB = dayDOB + "/" + monthDOB + "/" + String.valueOf(infantyear);
//		}
//
//		System.out.println(DOB);
//		return DOB;
//	}
	@Override
	public HashMap<String, String> getLocatorMap() {
		return locatorMap;
	}

	@Override
	public HashMap<String, String> getInfoMap() {
		return infoMap;
	}
}
