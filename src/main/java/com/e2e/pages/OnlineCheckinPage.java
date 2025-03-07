package com.e2e.pages;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import com.e2e.utils.Base64EncryptionUtil;

public class OnlineCheckinPage extends BasePage {

	private HashMap<String, String> locatorMap = new HashMap<>();
	private HashMap<String, String> infoMap = new HashMap<>();

	// Constants
	final String dynamicLocBoardingPass1 = "//span[contains(text(),'";
	final String dynamicLocBoardingPass2 = "')] ";

	final String dynamicLocUpgradeClass1 = "//input[contains(@aria-label,'";
	final String dynamicLocUpgradeClass2 = "')]/..";

	public OnlineCheckinPage() {
		// Locators
		locatorMap.put("baggagePickUpConfirmButton", "//*[@id='btnConfirm']");
		locatorMap.put("acceptPopupbtn", "//button[@id='cm-acceptAll']");
		locatorMap.put("bookingReferenceNumber", "//input[@id='fileKey']");
		locatorMap.put("firstName", "//input[@id='firstName']");
		locatorMap.put("lastName", "//input[@id='lastName']");
		locatorMap.put("continueButton", "//span[contains(text(),'Continue')]");
		locatorMap.put("continueButtonRegulatory", "//*[@id='btnRegulatory']");
		locatorMap.put("emailTextBox", "//*[@id='email']");
		locatorMap.put("countryCode", " //*[@id='phoneCountryCode']");
		locatorMap.put("phoneNumber", "//*[@id='phoneNumber']");
		locatorMap.put("consentCheckbox", "//mat-icon[@data-mat-icon-name='once-checkbox']");
		locatorMap.put("continueButtonContactDetails", " //*[@id='btnContinue']");
		locatorMap.put("continueAndSkipAdditionalServicesButton", "//*[@id='btnSkip']");
		locatorMap.put("continueWithNoDangerousItemsButton", "//*[@id='btnConfirm']");
		locatorMap.put("boardingPassOptionsDropDown", "//*[@id='boardingPassOptions']");
		locatorMap.put("requestBoardingPassButton", "//*[@id='btnContinue']");
		locatorMap.put("successMsgforBoardingPassIssued",
				"//h4[contains(text(),'We successfully issued your boarding pass(es)')]");
		locatorMap.put("successMsgforBoardingPassIssuedBySMS",
				"//p[contains(text(),'We sent your boarding pass(es) by text message')]");
		locatorMap.put("successMsgforBoardingPassIssuedByEmail",
				"//p[contains(text(),'We sent your boarding pass(es) by e-mail')]");

		locatorMap.put("emergencySeatConsentCheckbox", "//mat-icon[@data-mat-icon-name='once-checkbox']");
		locatorMap.put("emergencySeatConfirmButton", "//*[@id=\"btnConfirm\"]");
		locatorMap.put("boardingPassDownloadLink", "//a[contains(text(),\"Download documents\")]");

		locatorMap.put("nationalityTextBox", "//*[contains(@id,'nationality')]");
		locatorMap.put("expiryDateTextBox", "//*[@id='expiryDate']");
		locatorMap.put("documentNumberTextBox", "//*[@id='documentNumber']");

		locatorMap.put("residenceCountry", "//*[@id='countryOfResidence']");
		locatorMap.put("addressCity", "//*[contains(@id,'destinationAddress') and contains(@id,'City')]");
		locatorMap.put("addressStreet", "//*[contains(@id,'destinationAddress') and contains(@id,'Street')]");
		locatorMap.put("addressZipCode", "//*[contains(@id,'destinationAddress') and contains(@id,'ZipCode')]");

		locatorMap.put("continueButton", "//span[contains(text(),'Continue')]");

		locatorMap.put("startUpgradeButton", "//span[contains(text(),'Upgrade')]");
		locatorMap.put("buyNowButton", "//span[contains(text(),'Buy now')or contains(text(),'Confirm')]");

		locatorMap.put("upgradeCompletedMessage",
				"//h4[text()='Your upgrade has been completed' or text()='Your payment has been processed']");
		locatorMap.put("originCodeDisplayed", "//span[@class='originCode']");
		locatorMap.put("destinationCodeDisplayed", "//span[@class='destinationCode']");
		locatorMap.put("upgradeClassDisplayed", "//span[@class='pax-description']");

		locatorMap.put("firstCityOptionDisplayed", "//*[contains(@id,\"mat-option\")][1]");

		locatorMap.put("homeBaggageTagHeaderDisplayed", "//h1[text()='Print home-printed baggage tag']");
		locatorMap.put("performUpgradeButton", "//span[contains(text(),'Perform upgrade')]");

		locatorMap.put("changeSeatButton", "//*[@id='btnChangeSeat']");

		locatorMap.put("continueEntryRegulations", "//span[contains(text(),'Continue') or contains(text(),'Confirm')]");

		// Info messages for all locators
		infoMap.put("baggagePickUpConfirmButton", "Button to Confirm Baggage PickUp");
		infoMap.put("acceptPopupbtn", "Button to Accept All");
		infoMap.put("bookingReferenceNumber", "Input TextBox to enter Booking Reference Number or PNR");
		infoMap.put("firstName", "Input TextBox to enter First Name");
		infoMap.put("lastName", "Input TextBox to enter Last Name");
		infoMap.put("continueButton", "Button to Continue retrieval of Passenger Details");
		infoMap.put("continueButtonRegulatory", "Button to Continue after entering Passenger details");
		infoMap.put("emailTextBox", "Text Box to enter Email Address");
		infoMap.put("countryCode", "Text Box to enter Country Code");
		infoMap.put("phoneNumber", "Text Box to enter Phone Number");
		infoMap.put("consentCheckbox", "Checkbox to provide user consent");
		infoMap.put("continueButtonContactDetails", "Button to Continue after entering contact details");
		infoMap.put("continueAndSkipAdditionalServicesButton", "Button to Continue after Skipping additional services");
		infoMap.put("continueWithNoDangerousItemsButton",
				"Button to Continue after agreeing no Dangerous or Prohibitted items onboard");

		infoMap.put("boardingPassOptionsDropDown", "Drop Down to select options to issue Boarding Pass");
		infoMap.put("requestBoardingPassButton", "Button to Request Boarding Pass");
		infoMap.put("successMsgforBoardingPassIssued",
				"Message Displayed when Boarding Pass(es) are Successfully Issued");
		infoMap.put("successMsgforBoardingPassIssuedBySMS",
				"Message Displayed when Boarding Pass(es) are Successfully Issued by SMS");
		infoMap.put("successMsgforBoardingPassIssuedByEmail",
				"Message Displayed when Boarding Pass(es) are Successfully Issued by Email");

		infoMap.put("emergencySeatConsentCheckbox", "Checkbox to accept Emergency Seat");
		infoMap.put("emergencySeatConfirmButton", "Button to confirm Emergency Seat");
		infoMap.put("boardingPassDownloadLink", "Download link for Boarding Pass for Print Issue Mode");

		infoMap.put("residenceCountry", "Text box to enter Country of Residence");
		infoMap.put("nationalityTextBox", "Text box to enter Nationality");
		infoMap.put("expiryDateTextBox", "Text box to enter expiry date of document");
		infoMap.put("documentNumberTextBox", "Text box to enter document number");

		infoMap.put("addressCity", "Text box to enter City of Address");
		infoMap.put("addressStreet", "Text box to enter Street of Address");
		infoMap.put("addressZipCode", "Text box to enter Zip Code of Address");

		infoMap.put("continueButton", "Button to Continue Process");
		infoMap.put("startUpgradeButton", "Button to Start Upgrade Process");
		infoMap.put("buyNowButton", "Button to Buy Now");

		infoMap.put("upgradeCompletedMessage", "Header Message displaying Upgradation Successful");
		infoMap.put("originCodeDisplayed", "Origin Code Displayed");
		infoMap.put("destinationCodeDisplayed", "Destination Code Displayed");
		infoMap.put("upgradeClassDisplayed", "Class displayed after upgradation");

		infoMap.put("firstCityOptionDisplayed", "The first option of city displayed when searching for city");
		infoMap.put("homeBaggageTagHeaderDisplayed", "The header displayed in page to select Home Baggage Tags");
		infoMap.put("performUpgradeButton", "Button to continue and perform Upgrade on pop-up");

		infoMap.put("changeSeatButton", "Button to Change Seat during Checkin Process");
		infoMap.put("continueEntryRegulations", "Button to Continur after Entry Regulations Page");

	}

	public void enterPNRDetails(String serviceUrl, String tenant) throws InterruptedException, IOException {
		try {

			page.navigate(serviceUrl);
			page.waitForLoadState();
			log.info("Navigated to Service Url for retrieval flow");

			if (page.isVisible(locatorMap.get("acceptPopupbtn"))) {
				page.locator(locatorMap.get("acceptPopupbtn")).click();
			}

			String pnrRetrieved = scenarioContext.getContext("PNR_Retrieved");
			String firstNameRetrieved = scenarioContext.getContext("FirstName_Retrieved");
			String lastNameRetrieved = scenarioContext.getContext("LastName_Retrieved");

			log.info("PNR Retrieved is : " + pnrRetrieved);
			log.info("First Name Retrieved is : " + firstNameRetrieved);
			log.info("Last Name Retrieved is : " + lastNameRetrieved);

			waitForElementToBeVisible("bookingReferenceNumber", elementVisibilityMaxTimeout);
			waitAndCheckForElementToBeEnabled(locatorMap.get("bookingReferenceNumber"));
			isTheElementVisible("bookingReferenceNumber", elementVisibilityMaxTimeout);
			enterTextOnTheElement("bookingReferenceNumber", pnrRetrieved);
			enterTextOnTheElement("firstName", firstNameRetrieved);
			enterTextOnTheElement("lastName", lastNameRetrieved);

			clickOnTheElement("continueButton");

		}

		catch (Exception e) {
			logAssert_Fail("Failed to Enter PNR Details");
		}

	}

	public void enterContactDetails(String email, String countryCode, String phoneNumber)
			throws InterruptedException, IOException {
		try {

			scrollElementToVisibility("emailTextBox");
			waitForElementToBeVisible("emailTextBox", elementVisibilityMaxTimeout);
			enterTextOnTheElement("emailTextBox", email);
			enterTextOnTheElement("countryCode", countryCode);
			enterTextOnTheElement("phoneNumber", phoneNumber);

			scrollElementToVisibility("consentCheckbox");
			clickOnTheElement("consentCheckbox");
			clickOnTheElement("continueButtonContactDetails");

		}

		catch (Exception e) {
			logAssert_Fail("Failed to Enter Contact Details");
		}
	}

	public void enterDocumentDetails(String expiryDate, String documentNumber)
			throws InterruptedException, IOException {
		try {

			String decodedDocumentExpiryDate = Base64EncryptionUtil.readDecryptedData(expiryDate);
			String decodedDocumentNumber = generateDocumentNumber();

			waitForElementToBeVisible("expiryDateTextBox", elementVisibilityMaxTimeout);
			enterTextOnTheElement("expiryDateTextBox", decodedDocumentExpiryDate);
			enterTextOnTheElement("documentNumberTextBox", decodedDocumentNumber);
		}

		catch (Exception e) {
			logAssert_Fail("Failed to Enter Document Details");
		}
	}

	public void enterNationality(String nationality) throws InterruptedException, IOException {
		try {
			waitForElementToBeVisible("nationalityTextBox", elementVisibilityMaxTimeout);
			scrollElementToVisibility("nationalityTextBox");
			clickOnTheElement("nationalityTextBox");
			enterTextOnTheElement("nationalityTextBox", nationality);
			String dynamicLocator = "//span[text()='" + nationality + "']";
			waitAndClick(dynamicLocator);

		}

		catch (Exception e) {
			logAssert_Fail("Failed to Enter Contact Nationality");
		}
	}

	public void enterAddressDetails(String city, String street, String zipCode, String countryOfResidence)
			throws InterruptedException, IOException {
		try {
			// Wait to refresh and load the drop down options
			HardWait(3);
			scrollElementToVisibility("residenceCountry");
			enterTextOnTheElement("residenceCountry", countryOfResidence);
			String dynamicLocatorforCountry = "//span[text()='" + countryOfResidence + "']";
			waitAndCheckForElementToBeEnabled(dynamicLocatorforCountry);
			waitAndClick(dynamicLocatorforCountry);
			// Wait to refresh and load the drop down options
			HardWait(3);
			scrollElementToVisibility("addressCity");
			waitForElementToBeVisible("addressCity", elementVisibilityMaxTimeout);
			enterTextOnTheElement("addressCity", city);
			waitAndClickOnTheElement("firstCityOptionDisplayed", elementVisibilityMinTimeout);
			page.keyboard().press("Enter");
			scrollElementToVisibility("addressStreet");
			enterTextOnTheElement("addressStreet", street);
			scrollElementToVisibility("addressZipCode");
			enterTextOnTheElement("addressZipCode", zipCode);
			// Wait to refresh and load the drop down options
			HardWait(3);

		}

		catch (Exception e) {
			logAssert_Fail("Failed to Enter Address Details");
		}
	}

	public void acceptIfBaggagePickUpNeeded() throws InterruptedException, IOException {
		waitforEitherOfElementToAppear("baggagePickUpConfirmButton", "continueButtonRegulatory");
		if (isTheElementVisible("baggagePickUpConfirmButton", elementVisibilityMinTimeout)) {
			clickOnTheElement("baggagePickUpConfirmButton");
		}
	}

	public void clickToConfirmPassengerDetails() throws InterruptedException, IOException {
		waitAndClickOnTheElement("continueButtonRegulatory", elementVisibilityMaxTimeout);
		waitForElementToBeVisible("emailTextBox", elementVisibilityMaxTimeout);
	}

	public void acceptIfEmergencySeatSelected() throws InterruptedException, IOException {
		waitforEitherOfElementToAppear("emergencySeatConsentCheckbox", "continueAndSkipAdditionalServicesButton");
		if (isTheElementVisible("emergencySeatConsentCheckbox", elementVisibilityMinTimeout)) {
			clickOnTheElement("emergencySeatConsentCheckbox");
			clickOnTheElement("emergencySeatConfirmButton");
		}
	}

	public void skipAdditionalServices() throws InterruptedException, IOException {
		waitAndClickOnTheElement("continueAndSkipAdditionalServicesButton", elementVisibilityMaxTimeout);
		waitForElementToBeVisible("continueWithNoDangerousItemsButton", elementVisibilityMaxTimeout);
	}

	public void continueWithNoDangerousItems() throws InterruptedException, IOException {
		waitAndClickOnTheElement("continueWithNoDangerousItemsButton", elementVisibilityMaxTimeout);
	}

	public void changeSeatButton() throws InterruptedException, IOException {
		waitAndClickOnTheElement("changeSeatButton", elementVisibilityMaxTimeout);
	}

	public void acceptHomeBaggageTagIfRequired() throws InterruptedException {
		try {
			waitforEitherOfElementToAppear("continueButton", "boardingPassOptionsDropDown");
			if (isTheElementVisible("homeBaggageTagHeaderDisplayed", elementVisibilityMinTimeout)) {
				log.info("Home Baggage Tag option was available and accepted");
				scrollElementToVisibility("continueButton");
				clickOnTheElement("continueButton");
			}
		} catch (InterruptedException e) {
			logAssert_Fail("Failed to accept Home Baggage Tag due to error : " + e.getMessage());
		}
	}

	public void issueBoardingPass(String issueMode) throws InterruptedException, IOException {
		try {
			String dynamicLocatorForBoardingPassMode = dynamicLocBoardingPass1 + issueMode + dynamicLocBoardingPass2;
			waitAndClickOnTheElement("boardingPassOptionsDropDown", elementVisibilityMaxTimeout);
			scrollElementToVisibility("requestBoardingPassButton");
			waitAndClick(dynamicLocatorForBoardingPassMode);

			clickOnTheElement("requestBoardingPassButton");
		} catch (Exception e) {
			logAssert_Fail("Failed to issue Boarding Pass");
		}
	}

	public void verifyBoardingPassIssueMessage(String issueMode) throws InterruptedException, IOException {
		try {
			waitForElementToBeVisible("successMsgforBoardingPassIssued", elementVisibilityMaxTimeout);

			String actualMsgDisplayed = getTextOnTheElement("successMsgforBoardingPassIssued");
			log.info("The actual message displayed is : " + actualMsgDisplayed);

			if (!actualMsgDisplayed.equals("We successfully issued your boarding pass(es)")) {
				logAssert_Fail("Boarding Pass Issue Successful message displayed incorrectly");
			}

			// Verification based on Issue Mode
			switch (issueMode)

			{
			case "Print": {
				waitForElementToBeVisible("boardingPassDownloadLink", elementVisibilityMinTimeout);
				log.info("Document Download Link Found !");
				break;
			}
			case "Text message": {
				actualMsgDisplayed = getTextOnTheElement("successMsgforBoardingPassIssuedBySMS");
				log.info("The actual message displayed is : " + actualMsgDisplayed);

				if (!actualMsgDisplayed.equals("We sent your boarding pass(es) by text message.")) {
					logAssert_Fail("Boarding Pass Issue Successful message displayed incorrectly by SMS");
				}
				break;
			}
			case "Email": {
				actualMsgDisplayed = getTextOnTheElement("successMsgforBoardingPassIssuedByEmail");
				log.info("The actual message displayed is : " + actualMsgDisplayed);

				if (!actualMsgDisplayed.equals("We sent your boarding pass(es) by e-mail.")) {
					logAssert_Fail("Boarding Pass Issue Successful message displayed incorrectly by SMS");
				}
				break;
			}
			default: {
				log.error("Issue mode for boarding pass not defined !");
				logAssert_Fail("Issue mode for boarding pass not defined !");
				break;
			}
			}
		} catch (Exception e) {
			logAssert_Fail("Failed to display Boarding Pass Issue Successful message");
		}
	}

	public void continueEntryRegulationsForCheckInProcess() throws InterruptedException {
		try {
			waitAndClickOnTheElement("continueEntryRegulations", elementVisibilityMaxTimeout);
			log.info("Continuing after accepting entry regulations during checkin process");
		} catch (InterruptedException e) {
			logAssert_Fail("Failed to continue with Entry Regulations due to error : " + e.getMessage());
		}
	}

	public void continueDocumentCheckforCheckIn() throws InterruptedException {
		try {
			waitAndClickOnTheElement("continueButton", elementVisibilityMaxTimeout);
			log.info("Document Check completed for Checkin process");
		} catch (InterruptedException e) {
			logAssert_Fail("Document check process failed due to error : " + e.getMessage());
		}
	}

	public void startUpgradeProcess(String upgradeClass) throws InterruptedException {
		try {
			waitAndClickOnTheElement("startUpgradeButton", elementVisibilityMaxTimeout);
			log.info("Upgradation process has started");
			waitForElementToBeVisible("buyNowButton", elementVisibilityMaxTimeout);
			String dynamicUpgradeClassLocator = dynamicLocUpgradeClass1 + upgradeClass + dynamicLocUpgradeClass2;
			waitAndClick(dynamicUpgradeClassLocator);
			waitAndClickOnTheElement("buyNowButton", elementVisibilityMaxTimeout);
			// If a pop up appears to confirm to continue performing Upgrade Process
			if (isTheElementVisible("performUpgradeButton", elementVisibilityMinTimeout)) {
				waitAndClickOnTheElement("performUpgradeButton", elementVisibilityMinTimeout);
			}
			log.info("Upgradation package has been bought");
		} catch (InterruptedException e) {
			logAssert_Fail("Thread was interrupted: " + e.getMessage());
		} catch (Exception e) {
			try {
				logAssert_Fail("Failed to perform Upgrade Travel Class during Checkin Process");
			} catch (InterruptedException e1) {
				logAssert_Fail("Thread was interrupted: " + e1.getMessage());
			}
		}
	}

	public void verifyUpgradationProcess(String origin, String destination, String upgradeOption)
			throws InterruptedException, IOException {
		waitForElementToBeVisible("upgradeCompletedMessage", elementVisibilityMaxTimeout);
		assertTextOnTheElement("upgradeCompletedMessage", "Your payment has been processed");
		assertTextOnTheElement("originCodeDisplayed", origin);
		assertTextOnTheElement("destinationCodeDisplayed", destination);
		assertTextOnTheElement("upgradeClassDisplayed", upgradeOption);
	}

	public void continueCheckinAfterUpgradeVerification() throws InterruptedException {
		try {
			waitAndClickOnTheElement("continueButton", elementVisibilityMaxTimeout);
			log.info("Clicking to continue further after upgrade process");
		} catch (InterruptedException e) {
			logAssert_Fail("Interrupted Exception occured: " + e.getMessage());
		}
	}

	public static String generateDocumentNumber() {
		Random random = new Random();

		// Generate the first character as a capital letter
		char firstChar = (char) ('A' + random.nextInt(26));

		// Generate the next 7 characters as random digits
		StringBuilder documentNumber = new StringBuilder();
		documentNumber.append(firstChar);
		for (int i = 0; i < 7; i++) {
			documentNumber.append(random.nextInt(10));
		}

		return documentNumber.toString();
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
