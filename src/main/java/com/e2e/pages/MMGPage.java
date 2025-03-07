package com.e2e.pages;

import com.e2e.utils.Base64EncryptionUtil;
import com.e2e.utils.DateManipulator;
import com.e2e.utils.StringManipulator;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.io.IOException;
import java.util.HashMap;

public class MMGPage extends BasePage {

	private HashMap<String, String> locatorMap = new HashMap<>();
	private HashMap<String, String> infoMap = new HashMap<>();

	public static String email;
	public static String EMAIL_1 = "testmg@yopmail.com";
	public static String EMAIL_2 = "testlh@yopmail.com";
	public static String lastusedemailid;

	public MMGPage() {

		// Locators

		locatorMap.put("mamKeepGermany", "//button[@title=\"Keep Germany\"]");
		locatorMap.put("neccessaryCookieSelectAll",
				"//span[contains(text(),'Allow all')]//parent::*[contains(@class,'cookieconsent__buttonAcceptAll cookieconsent')]");
		locatorMap.put("cdnMamLogin", "(//span[contains(text(),'Login')])[1]");
		locatorMap.put("register_for_travel_Id_xpath", "//span[text()='Register for Travel ID']");

		locatorMap.put("mmg_email", "//input[@id='id-email-textfield']");
		locatorMap.put("mmg_password", "//input[contains(@name,'password') or contains(@name,'emailLoginStepTwo')]");
		locatorMap.put("mmg_ClickonCountineInenrolmnt", "button[name='createAccount_next']");
		locatorMap.put("userFirstName_css", "input[name='firstName']");
		locatorMap.put("userLastName_css", "input[name='lastName']");
		locatorMap.put("userBirthDay_css", "input[name='birthday']");
		locatorMap.put("userBirthMonth_css", "input[name='birthmonth']");
		locatorMap.put("userBirthYear_css", "input[name='birthyear']");
		locatorMap.put("countryDropdown", "(//span[@class='travelid-form__icon travelid-form__textfieldIcon'])[10]");
		locatorMap.put("selectCountry", "//li[@data-value='DE']");
		locatorMap.put("userStreet_css", "input[id='id-address-textfield']");
		locatorMap.put("userZip_css", "input[name='postalCode']");
		locatorMap.put("userCity_css", "input[name='townCity']");
		locatorMap.put("userAreaCode_css", "input[name='areacode']");
		locatorMap.put("userPhoneNumber_css", "input[name='number']");
		locatorMap.put("userClickonCountinuInEnrolmentPage_css", "button[name='personalInformation_next']");
		locatorMap.put("scn_registration_next", "//button[@name='serviceCard_next']");
		locatorMap.put("userSelectCheckBox_css",
				"//input[@name='allInOnePermission']//parent::div//span[contains(@class,'travelid-icon-ico')]");
		locatorMap.put("mmgEnrollRadioBtn", "//label[contains(@class,'checkboxLarge switch')]");
		locatorMap.put("userClickOnCountinuAndConfirmeButton_css", "button[name='communication_next']");
		locatorMap.put("mamLoginClosePopup", "//button[@aria-label='Close']");
		locatorMap.put("mamLoginAvatar","//span[contains(text(),'Login')]");
		locatorMap.put("acceptPopupBtn","//*[@id='cm-acceptAll']");

		locatorMap.put("enrollmentConfirmationSubHeading", "//h1[contains(@class,'confirmationSubheading')]");
		locatorMap.put("mmgNextButton", "(//*[contains(text(),'Next')])[1]");
		locatorMap.put("scnPin", "//input[@id='id-loginStepTwoPassword-textfield']");
		locatorMap.put("emailLoginSubmitButton", "//span[text()=\"Log in\"]");

		locatorMap.put("mmgAccountButton", "(//*[contains(text(),'Account')])[1]");
		locatorMap.put("mmgProfileButton",
				"(//*[@class=\"mainnavigation__link mainnavigation__secondLevelItem mdc-tab mdc-ripple-upgraded\"])[3]");
		locatorMap.put("mmgEditButtonOffersAndCommunication",
				"(//a[@class=\"permissionstravelidoverview__editLink\"])[1]");
		locatorMap.put("mmgAccountButton","(//*[contains(text(),'Account')])[1]");
		locatorMap.put("mmgLoggedinButton","(//*[contains(text(),'Account')])[1]/following::*[contains(text(),'Logout')][1]");
        locatorMap.put("mmgProfileButton", "(//*[@class=\"mainnavigation__link mainnavigation__secondLevelItem mdc-tab mdc-ripple-upgraded\"])[3]");
        locatorMap.put("airemLoggedinView", "//*[contains(@class,'user-name ')]");
        locatorMap.put("airemLoggedinViewClose","//*[contains(@class,'user-name ')]/preceding::*[contains(@aria-label,'Close')]");
        locatorMap.put("airemLoggedinButton","//*[contains(@class,'logo')]/following::*[contains(@id,'-name') or contains(@id,'-unique-id-name') or contains(@id,'unique-name')]");
        locatorMap.put("mmgEditButtonOffersAndCommunication", "(//a[@class=\"permissionstravelidoverview__editLink\"])[1]");
 
		locatorMap.put("mmgPersonalisedCommunicationCheckBox", "//*[@id=\"id-behaviouralPermission-checkbox\"]");
		locatorMap.put("saveChangesButton", "(//*[text()=\"Save changes\"])[2]");
		locatorMap.put("backToProfileButton", "//*[@class=\"backlink__anchor\"]");
		locatorMap.put("personalInfoEdit", "//*[@class='personalinformationoverview__edit']");
		locatorMap.put("addressTextBox", "//*[@name=\"street\"]");
		locatorMap.put("addressSave", "(//*[text()=\"Save changes\"])[2]");
		locatorMap.put("requestCodeButton", "//span[text()=\"Request code\"]");
		locatorMap.put("confirmButton", "(//span[text()=\"Confirm\"])[4]");
		locatorMap.put("enterVerificationCodeButton", "//input[@id=\"twofa-authentication\"]");
		locatorMap.put("profileEmail", "//input[@name='email']");
		locatorMap.put("emailSaveChanges", "(//*[text()=\"Save changes\"])[3]");
		locatorMap.put("telephoneNumberSaveButton", "(//*[text()=\"Save changes\"])[5]");
		locatorMap.put("logoutButton", "(//span[text()=\"Logout\"])[1]");
		locatorMap.put("ffbLoginSCN", "//input[@id='id-loginStepOne-textfield']");
		locatorMap.put("deleteButton", "//button[@class=\"mamcomicon-ico48    personalinformation__removeNumber\"]");
		locatorMap.put("addTelephoneNumberButton", "//button[@class=\"personalinformation__addNumberButton\"]");
		locatorMap.put("telephoneSaveButton",
				"//button[@class=\"button mdc-button button--filled button--primary personalinformation__numberSubmitButton personalinformation__buttonSave\"]");
		locatorMap.put("accountButton", "//button[@class=\"mainnavigation__menuButton is-hidden-mb1\"]");
		locatorMap.put("areaCodeTelephoneZero", "(//input[@placeholder=\"Area code\"])[2]");
		locatorMap.put("telephoneNumberZero", "(//input[@placeholder=\"Telephone number\"])[2]");
		locatorMap.put("areaCodeTelephoneOne", "(//input[@placeholder=\"Area code\"])[3]");
		locatorMap.put("telephoneNumberOne", "(//input[@placeholder=\"Telephone number\"])[3]");
		locatorMap.put("areaCodeTelephoneTwo", "(//input[@placeholder=\"Area code\"])[4]");
		locatorMap.put("telephoneNumberTwo", "(//input[@placeholder=\"Telephone number\"])[4]");
		locatorMap.put("areaCodeTelephoneThree", "(//input[@placeholder=\"Area code\"])[5]");
		locatorMap.put("telephoneNumberThree", "(//input[@placeholder=\"Telephone number\"])[5]");
		locatorMap.put("areaCodeTelephoneFour", "(//input[@placeholder=\"Area code\"])[6]");
		locatorMap.put("telephoneNumberFour", "(//input[@placeholder=\"Telephone number\"])[6]");
		locatorMap.put("areaCodeTelephoneFifth", "(//input[@placeholder=\"Area code\"])[7]");
		locatorMap.put("telephoneNumberFifth", "(//input[@placeholder=\"Telephone number\"])[7]");
		locatorMap.put("cookiesAcceptAllButton", "//button[@id='cm-acceptAll']");
		locatorMap.put("popUpCloseCrossButtonType1", "//button[@aria-label='Close']");
		locatorMap.put("popUpCloseCrossButtonType2", "//i[@class='icon lh lh-close']");

		// InfoMap

		infoMap.put("mamKeepGermany", "Button to Keep selecting Germany as country");
		infoMap.put("neccessaryCookieSelectAll", "Button to Select All Neccessary Cookies");
		infoMap.put("cdnMamLogin", "Miles and More Login");
		infoMap.put("register_for_travel_Id_xpath", "Link to register a new travel ID");
		infoMap.put("acceptPopupBtn", "Accept Popup Btn");
        infoMap.put("mamLoginAvatar", "Mam Login Avatar");
        infoMap.put("mamLoginClosePopup", "Mam Login Close Popup");


		infoMap.put("mmg_email", "MMG Login Username/Email Text Box for registration");
		infoMap.put("mmg_password", "MMG Login Password Text Box");
		infoMap.put("mmg_ClickonCountineInenrolmnt", "Button to Continue Enrollment");
		infoMap.put("userFirstName_css", "Text field to input User First Name");
		infoMap.put("userLastName_css", "Text field to input User Last Name");
		infoMap.put("userBirthDay_css", "Text field to input User Birthday");
		infoMap.put("userBirthMonth_css", "Text field to input User Birth Month");
		infoMap.put("userBirthYear_css", "Text field to input User Birth Year");
		infoMap.put("countryDropdown", "Dropdown to select the country");
		infoMap.put("selectCountry", "Selected country label");
		infoMap.put("userStreet_css", "Text field to input Street name");
		infoMap.put("userZip_css", "Text field to input Zip number");
		infoMap.put("userCity_css", "Text field to input City Name");
		infoMap.put("userAreaCode_css", "Text field to input Area code");
		infoMap.put("userPhoneNumber_css", "Text field to input Phone number");
		infoMap.put("userClickonCountinuInEnrolmentPage_css", "Button to continue from Enrollment page");
		infoMap.put("scn_registration_next", "Button to continue to Next page");
		infoMap.put("userSelectCheckBox_css", "Checkbox to select all permissions");
		infoMap.put("mmgEnrollRadioBtn", "Radio button to select Enrollment");
		infoMap.put("userClickOnCountinuAndConfirmeButton_css", "Click and Continue button");
		infoMap.put("enrollmentConfirmationSubHeading", "Heading displaying the enrollment confirmation message");
		infoMap.put("accountButton", "account Button");

		

		infoMap.put("mmgAccountButton","MMG Account Button");
		infoMap.put("mmgLoggedinButton","MMG Loggedin Button");
		infoMap.put("airemLoggedinButton","Airem Loggedin Button");
		infoMap.put("airemLoggedinView","Airem Loggedin View");
		infoMap.put("airemLoggedinViewClose","Airem Loggedin View Closed");

		infoMap.put("logoutButton", "logout Button");
		infoMap.put("ffbLoginSCN", "Ffb Login SCN");
		infoMap.put("deleteButton", "delete Button");
		infoMap.put("addTelephoneNumberButton", "add Telephone Number Button");
		infoMap.put("telephoneSaveButton", "telephone Save Button");
		infoMap.put("areaCodeTelephoneZero", "areaCode Telephone Zero");
		infoMap.put("telephoneNumberZero", "telephone Number Zero");
		infoMap.put("areaCodeTelephoneOne", "areaCode Telephone One");
		infoMap.put("telephoneNumberOne", "telephone Number One");
		infoMap.put("telephoneNumberSaveButton", "telephone Number Save Button");
		infoMap.put("areaCodeTelephoneThree", "areaCode Telephone Three");
		infoMap.put("telephoneNumberThree", "telephone Number Three");
		infoMap.put("areaCodeTelephoneTwo", "areaCode Telephone Two");
		infoMap.put("telephoneNumberTwo", "telephone Number Two");
		infoMap.put("areaCodeTelephoneFour", "areaCode Telephone Four");
		infoMap.put("telephoneNumberFour", "telephone Number Four");
		infoMap.put("areaCodeTelephoneFifth", "areaCode Telephone Four");
		infoMap.put("telephoneNumberFifth", "telephone Number Fifth");
		infoMap.put("cookiesAcceptAllButton", "Accept All Cookies Button");
		infoMap.put("popUpCloseCrossButtonType1", "Cross Close Button");
		infoMap.put("popUpCloseCrossButtonType2", "Alternate Cross Close Button");
	}

	public void handle_Privacy_Settings_Page() throws InterruptedException {
		page.isVisible(locatorMap.get("mamKeepGermany"));
		log.info("Keep germany popup found");
		page.click(locatorMap.get("mamKeepGermany"));
		log.info("Keep germany popup clicked");
		page.isVisible(locatorMap.get("neccessaryCookieSelectAll"));
		log.info("Cookie popup found");
		page.click(locatorMap.get("neccessaryCookieSelectAll"));
		log.info("Cookie popup clicked");
		page.click(locatorMap.get("cdnMamLogin"));
	}

	public void clicks(String locator) throws InterruptedException {
		try {
			waitAndClick(locatorMap.get(locator));
		} catch (Exception e) {
			logAssert_Fail("Fails to click " + infoMap.get(locator) + ". Locator is: " + locatorMap.get(locator));
		}
	}

	public void verifyMmgAccountButtonVisibility() throws InterruptedException {
		try {
			isTheElementVisible("mmgAccountButton", elementVisibilityMaxTimeout);

		} catch (Exception e) {
			log.info("Fails to Verify SCN " + infoMap.get(locatorMap.get("mmgAccountButton")) + ". Locator is: "
					+ locatorMap.get("mmgAccountButton"));
		}

	}
	
	public void verifyMmgLoggedinButtonVisibility() throws InterruptedException {
		try {
			isTheElementVisible("mmgLoggedinButton", elementVisibilityMaxTimeout);

		} catch (Exception e) {
			log.info("Fails to Verify Loggedin " + infoMap.get(locatorMap.get("mmgLoggedinButton")) + ". Locator is: "
					+ locatorMap.get("mmgLoggedinButton"));
		}

	}

	public void verifyAiremLoggedinButtonVisibility() throws InterruptedException {
		try {
			waitForElementToBeVisible("airemLoggedinButton", elementVisibilityMaxTimeout);
		clickOnTheElement("airemLoggedinButton");
		} catch (Exception e) {
			log.info("Fails to Verify Loggedin " + infoMap.get(locatorMap.get("airemLoggedinButton")) + ". Locator is: "
					+ locatorMap.get("airemLoggedinButton"));
		}

	}
	
	

	public boolean elementVisibility(String object) {
		try {
			page.isVisible(object);
		} catch (Exception e) {
			throw e;
		}
		return false;
	}

	public void verifyUsingText(String locator) throws InterruptedException {
		try {
			getText(locatorMap.get(locator));
		} catch (Exception e) {
			logAssert_Fail("Fails to gettext " + infoMap.get(locator) + ". Locator is: " + locatorMap.get(locator));
		}
	}

	public void verifyAndEnterText(String locator, String data) throws InterruptedException {
		boolean status = false;
		status = isTheElementVisible(locator, 3000);
		if (status == true) {
			enterText(locatorMap.get(locator), data);
		} else {
			logAssert_Fail(
					"Fails to enter text in " + infoMap.get(locator) + ". Locator is: " + locatorMap.get(locator));
		}
	}



	public void lhAcceptPopUp() {
		if (lh_Page.isVisible(locatorMap.get("acceptPopupBtn"))) {
			lh_Page.click("acceptPopupBtn");
			log.info("Accept button clicked");
		} else if (lh_Page.isVisible(locatorMap.get("mamLoginClosePopup"))) {
			lh_Page.click("mamLoginClosePopup");
		} else {
			lh_Page.isVisible(locatorMap.get("mamLoginAvatar"));
		}
		
	}

	public void lxAcceptPopUp() {
		if (lx_Page.isVisible(locatorMap.get("acceptPopupBtn"))) {
			lx_Page.click("acceptPopupBtn");
			log.info("Accept button clicked");
		} else if (lx_Page.isVisible(locatorMap.get("mamLoginClosePopup"))) {
			lx_Page.click("mamLoginClosePopup");
		} else {
			lx_Page.isVisible(locatorMap.get("mamLoginAvatar"));
		}
	}

	public void osAcceptPopUp() {
		if (os_Page.isVisible(locatorMap.get("acceptPopupBtn"))) {
			os_Page.click("acceptPopupBtn");
			log.info("Accept button clicked");
		} else if (os_Page.isVisible(locatorMap.get("mamLoginClosePopup"))) {
			os_Page.click("mamLoginClosePopup");
		} else {
			os_Page.isVisible(locatorMap.get("mamLoginAvatar"));
		}
	}

	public void snAcceptPopUp() {
		if (sn_Page.isVisible(locatorMap.get("acceptPopupBtn"))) {
			sn_Page.click("acceptPopupBtn");
			log.info("Accept button clicked");
		} else if (sn_Page.isVisible(locatorMap.get("mamLoginClosePopup"))) {
			sn_Page.click("mamLoginClosePopup");
		} else {
			sn_Page.isVisible(locatorMap.get("mamLoginAvatar"));
		}
	}
	
	
	public void yopmailAccess() throws InterruptedException {
		try {
			page.navigate("https://yopmail.com/en/");
			log.info("yopmail url is launched");
			if (page.isVisible(locatorMap.get("consent"))) {
				clicks("consent");
			}
			verifyAndEnterText("yopMail", "asc04@yopmail.com");
			clicks("submitMail");
			longWait();
			FrameLocator myFrame = page.frameLocator("//iframe[@id='ifmail']");
			Locator url = myFrame
					.locator("//a[contains(@href,\"pin-password-forgotten/pin-password-forgotten-completion.html\")]");
			String s = url.getAttribute("href");
			log.info("url:" + s);
			page.navigate(s);
		} catch (Exception e) {

		}
	}

	public void longWait() throws InterruptedException {
		log.info("Going to long wait for 5 seconds .....");
		Thread.sleep(5000);
	}

	public void clickMMGRegistrationLink() throws InterruptedException, IOException {
		try {
			// Clicks Registration Link
			clicks("register_for_travel_Id_xpath");
		} catch (Exception e) {
			logAssert_Fail("Failed to Click MMG Registration Link");
		}

	}

	public void generateRandomEmail() throws InterruptedException, IOException {
		try {
			// Generate Random Email
			String randomStr = StringManipulator.getRandomString(3);
			System.out.println(randomStr);
			String email = randomStr + "_" + DateManipulator.convertDateToString(
					DateManipulator.getCurrentDate("yyyyMMddHHmmss"), "yyyyMMddHHmmss") + "@yopmail.com";
			scenarioContext.setContext("emailAddressGenerated", email);
		} catch (Exception e) {
			logAssert_Fail("Failed to generate Random Email");
		}

	}

	public void enterUserCredentialsForMMG(String email, String password) throws InterruptedException, IOException {
		try {
			// Enter Username and Password for MMG
			email = scenarioContext.getContext("emailAddressGenerated");
			HardWait(3);
			waitForElementToBeVisible("mmg_email", elementVisibilityMaxTimeout);
			waitAndCheckForElementToBeEnabled(locatorMap.get("mmg_email"));
			enterTextOnTheElement("mmg_email", email);
			String decryptedDefaultPassword = Base64EncryptionUtil.readDecryptedData(password);
			enterTextOnTheElement("mmg_password", decryptedDefaultPassword);
			scenarioContext.setContext("passwordGenerated", decryptedDefaultPassword);
		} catch (Exception e) {
			logAssert_Fail("Failed to enter user credentials in MMG");
		}

	}

	public void clickToProceedEnrollment() throws InterruptedException, IOException {
		try {
			clicks("mmg_ClickonCountineInenrolmnt");
		} catch (Exception e) {
			logAssert_Fail("Failed to proceed with enrollment in MMG");
		}

	}

	public void enterEnrollmentDetailsInMMG(String First_Name, String Surname, String DateofBirth, String Postaladdress,
			String Telephonenumber) throws InterruptedException, IOException {
		try {
			// Enter Enrollment Details
			HardWait(3);
			enterTextOnTheElement("userFirstName_css", First_Name);
			enterTextOnTheElement("userLastName_css", Surname);

			String[] value = DateofBirth.split("-");
			enterTextOnTheElement("userBirthDay_css", value[0]);
			enterTextOnTheElement("userBirthMonth_css", value[1]);
			enterTextOnTheElement("userBirthYear_css", value[2]);

			clickOnTheElement("countryDropdown");
			clickOnTheElement("selectCountry");

			String Streetname = "WALKING SREET";
			enterTextOnTheElement("userStreet_css", Streetname);
			HardWait(2);
			enterTextOnTheElement("userZip_css", Postaladdress);

			String city = "frankfut";
			enterTextOnTheElement("userCity_css", city);

			String areaCode = "49";
			enterTextOnTheElement("userAreaCode_css", areaCode);
			enterTextOnTheElement("userPhoneNumber_css", Telephonenumber);
			clickOnTheElement("userClickonCountinuInEnrolmentPage_css");
			clickOnTheElement("scn_registration_next");

			if (isTheElementVisible("userSelectCheckBox_css", elementVisibilityMinTimeout)) {
				clickOnTheElement("userSelectCheckBox_css");
			} else {
				clickOnTheElement("mmgEnrollRadioBtn");
			}
			clickOnTheElement("userClickOnCountinuAndConfirmeButton_css");
			waitForElementToBeVisible("enrollmentConfirmationSubHeading", elementVisibilityMaxTimeout);
		} catch (Exception e) {
			logAssert_Fail("Failed to proceed with enrollment details in MMG");
		}

	}

	public void checkBoxHandling(String locator) throws InterruptedException {
		try {// Check if the checkbox is already checked
			boolean isChecked = page.isChecked(locatorMap.get(locator));
			log.info(isChecked);
			if (isChecked) {
				page.dblclick(locatorMap.get(locator));
			} else {
				page.click(locatorMap.get(locator));
			}
		} catch (Exception e) {
			logAssert_Fail(
					"Fails to handle checkbox " + infoMap.get(locator) + ". Locator is: " + locatorMap.get(locator));
		}

	}

	public String getNextEmail(String locator) throws InterruptedException {

		try {
			Locator inputTextField = page.locator(locatorMap.get(locator));
			// Get input text of an element using inputValue();
			lastusedemailid = inputTextField.inputValue();
			log.info("Last used email id ==" + lastusedemailid);

			if (EMAIL_1.equals(lastusedemailid)) {
				email = EMAIL_2;
			} else {
				email = EMAIL_1;
			}

		} catch (Exception e) {
			logAssert_Fail("Fails to handle get next email id " + infoMap.get(locator) + ". Locator is: "
					+ locatorMap.get(locator));
		}

		return email;
	}

	public void enterTelephoneNumber(String areacode, String phoneno) throws InterruptedException {
		clearAllTelephoneNumbers();
		addAndEnterTelephoneNumber(areacode, phoneno);
	}

	private void clearAllTelephoneNumbers() {
		// Retrieve the delete button locator from the map
		String deleteButtonLocator = locatorMap.get("deleteButton");
		// Locate the delete buttons using the locator from the map
		Locator deleteButtons = page.locator(deleteButtonLocator);
		// Count the number of delete buttons
		int buttonCount = deleteButtons.count();
		log.info("Total count of button is :" + buttonCount);

		// If more than one delete button, click all of them
		for (int i = 0; i < buttonCount; i++) {
			deleteButtons.nth(i).click();
		}

	}

	private void addAndEnterTelephoneNumber(String areacode, String phoneno) throws InterruptedException {
		int buttonCount = page.locator(locatorMap.get("deleteButton")).count();

		clicks("addTelephoneNumberButton");

		switch (buttonCount) {
		case 0:
			enterTelephoneDetailZero(areacode, phoneno);
			break;
		case 1:
			enterTelephoneDetailOne(areacode, phoneno);
			break;
		case 2:
			enterTelephoneDetailTwo(areacode, phoneno);
			break;
		case 3:
			enterTelephoneDetailThree(areacode, phoneno);
			break;
		case 4:
			enterTelephoneDetailFour(areacode, phoneno);
			break;
		case 5:
			enterTelephoneDetailFive(areacode, phoneno);
			break;
		default:
			log.error("Unexpected button count: " + buttonCount);
			throw new IllegalStateException("Unexpected button count: " + buttonCount);
		}
	}

	private void enterTelephoneDetailZero(String areacode, String phoneno) throws InterruptedException {
		clicks("areaCodeTelephoneZero");
		verifyAndEnterText("areaCodeTelephoneZero", areacode);
		page.keyboard().press("Tab");
		clicks("telephoneNumberZero");
		verifyAndEnterText("telephoneNumberZero", phoneno);
		page.keyboard().press("Tab");

	}

	private void enterTelephoneDetailOne(String areacode, String phoneno) throws InterruptedException {
		clicks("areaCodeTelephoneOne");
		verifyAndEnterText("areaCodeTelephoneOne", areacode);
		page.keyboard().press("Tab");
		clicks("telephoneNumberOne");
		verifyAndEnterText("telephoneNumberOne", phoneno);
		page.keyboard().press("Tab");

	}

	private void enterTelephoneDetailTwo(String areacode, String phoneno) throws InterruptedException {
		clicks("areaCodeTelephoneTwo");
		verifyAndEnterText("areaCodeTelephoneTwo", areacode);
		page.keyboard().press("Tab");
		clicks("telephoneNumberTwo");
		verifyAndEnterText("telephoneNumberTwo", phoneno);
		page.keyboard().press("Tab");

	}

	private void enterTelephoneDetailThree(String areacode, String phoneno) throws InterruptedException {
		clicks("areaCodeTelephoneThree");
		verifyAndEnterText("areaCodeTelephoneThree", areacode);
		page.keyboard().press("Tab");
		clicks("telephoneNumberThree");
		verifyAndEnterText("telephoneNumberThree", phoneno);
		page.keyboard().press("Tab");

	}

	private void enterTelephoneDetailFour(String areacode, String phoneno) throws InterruptedException {
		clicks("areaCodeTelephoneFour");
		verifyAndEnterText("areaCodeTelephoneFour", areacode);
		page.keyboard().press("Tab");
		clicks("telephoneNumberFour");
		verifyAndEnterText("telephoneNumberFour", phoneno);
		page.keyboard().press("Tab");

	}

	private void enterTelephoneDetailFive(String areacode, String phoneno) throws InterruptedException {
		clicks("areaCodeTelephoneFifth");
		verifyAndEnterText("areaCodeTelephoneFifth", areacode);
		page.keyboard().press("Tab");
		clicks("telephoneNumberFifth");
		verifyAndEnterText("telephoneNumberFifth", phoneno);
		page.keyboard().press("Tab");
	}

	public void clearText(String locator) throws InterruptedException {
		try {
			clearTextField(locatorMap.get(locator));
		} catch (Exception e) {
			logAssert_Fail("Fails to Clear text " + infoMap.get(locator) + ". Locator is: " + locatorMap.get(locator));
		}
	}
    
    public void getPageTitle() throws InterruptedException {
		takeScreenshot();
        try {
        	String getPageTitle = page.title();
        	log.info(getPageTitle);
        } catch (Exception e) {
            logAssert_Fail("Fails to Get Page Title");
        }
    }

	public void generateRandomAddress() throws InterruptedException, IOException {
		try {
			// Generate Random Address
			String randomAddressInput = StringManipulator.getRandomString(3);
			log.info(randomAddressInput);
			verifyAndEnterText("addressTextBox", randomAddressInput);
		} catch (Exception e) {
			logAssert_Fail("Failed to generate Random Address");
		}

	}

	public void verifySCN() throws InterruptedException {

		try {
			isTheElementVisible("ffbLoginSCN", elementVisibilityMinTimeout);

		} catch (Exception e) {
			log.info("Fails to Verify SCN " + infoMap.get(locatorMap.get("ffbLoginSCN")) + ". Locator is: "
					+ locatorMap.get("ffbLoginSCN"));
		}

	}

	public void verifyPin() throws InterruptedException {

		try {
			isTheElementVisible("scnPin", elementVisibilityMinTimeout);
			clickOnTheElement("scnPin");
		} catch (Exception e) {
			log.info("Fails to Verify Pin " + infoMap.get(locatorMap.get("scnPin")) + ". Locator is: "
					+ locatorMap.get("scnPin"));
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