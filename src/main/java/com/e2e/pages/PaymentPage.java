package com.e2e.pages;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

import com.e2e.utils.Base64EncryptionUtil;
import com.microsoft.playwright.FrameLocator;

public class PaymentPage extends BasePage {

	private HashMap<String, String> locatorMap = new HashMap<>();
	private HashMap<String, String> infoMap = new HashMap<>();

	// Constants
	final String countryLoc1 = "//Ul[@aria-label='Country list']//span[contains(text(),'";
	final String countryLoc2 = "')]";

	final String currencyLoc1 = "//div[contains(@class,'fullwidth mdc-menu-surface')]//span[contains(text(),'";
	final String currencyLoc2 = "')]";

	public PaymentPage() {
		// Locators
		locatorMap.put("paymentAcceptCheckbox", "//input[@id='terms-checkbox']");
		locatorMap.put("paymentContinuetoPaymentBtn", "//span[text()='Continue to payment' or text()='Pay Now']");
		locatorMap.put("upgradeContinueButton", "//button[contains(text(),'Continue')]");
		locatorMap.put("paymentFopButton", "//article[@id='fop-cc']//input[@name='radios']");

		locatorMap.put("savedCard_payment", "//*[@id=\"radio_w0\"]");
		locatorMap.put("savedCard_cvv", "(//input[@aria-labelledby='cvv-label'])[1]");
		locatorMap.put("paymentPayNowButton", "//span[text()='Pay Now']");
		locatorMap.put("confirmationText",
				"//div[text()='Your booking is confirmed!' or contains(text(),'Your new itinerary is confirmed!') or contains(text(),'Your services are confirmed') or contains(text(),'Your booking is on hold!') or contains(text(),'Thank you for your booking')]");
		locatorMap.put("confirmationPNRObj", "//span[text()='Your booking reference is']//..//following-sibling::div");
		locatorMap.put("confirmationFirstName", "(//span[@class='name'])[1]");

		locatorMap.put("paymentCardTypelst", "//div[contains(@class,'cardtype-select')]");
		locatorMap.put("paymentMasterCard_lst", "//ul[@aria-label='Card type list']//*[@data-value='CA']");
		locatorMap.put("paymentAmex_lst", "//Ul[@aria-label='Card type listbox']//*[@data-value='AX']");
		locatorMap.put("paymentVisa_lst", "//ul[@aria-label='Card type list']//*[@data-value='VI']");
		locatorMap.put("paymentDiners_lst", "//Ul[@aria-label='Card type listbox']//*[@data-value='DC']");
		locatorMap.put("paymentJCB_lst", "//Ul[@aria-label='Card type list']//*[@data-value='JC']");
		locatorMap.put("paymentUatp_lst", "//Ul[@aria-label='Card type listbox']//*[@data-value='TP']");
		locatorMap.put("paymentChinaUP_lst", "//Ul[@aria-label='Card type listbox']//*[@data-value='UP']");
		locatorMap.put("paymentCardNo_input", "//span[@id='number-label']");
		locatorMap.put("paymentCardholderName_input", "//input[@aria-labelledby='name-label']");
		locatorMap.put("paymentExpiryMonth_input", "//input[@aria-labelledby='expirydate-month-label']");
		locatorMap.put("paymentExpiryYear_input", "//input[@aria-labelledby='expirydate-year-label']");
		locatorMap.put("paymentCVV_input", "//*[@id='fop-cc']//input[@aria-labelledby='cvv-label']");
		locatorMap.put("paymentCountry", "(//Ul[@aria-label='Country list']//*[@data-value='GB'])[1]");
		locatorMap.put("paymentStreetinput", "//input[@aria-labelledby='street-label']");
		locatorMap.put("paymentZipInput", "//input[@aria-labelledby='zip-label']");
		locatorMap.put("paymentCityInput", "//input[@aria-labelledby='city-label']");
		locatorMap.put("paymentCountrylst", "//div[@aria-labelledby='country-outlined-select-label']");
		/// ////////////
		locatorMap.put("paymentVisa_lst1", "//img[@alt='VISA'][@data-brand='VI']");
		locatorMap.put("paymentMasterCard_lst1", "//img[@alt='Mastercard'][@data-brand='CA']");
		locatorMap.put("paymentAmex_lst1", "//img[@alt='American Express'][@data-brand='AX']");
		locatorMap.put("paymentDiners_lst1", "//img[@alt='Diners Club'][@data-brand='DC']");
		locatorMap.put("paymentDiscover_lst1", "//img[@alt='Discover'][@data-brand='DI']");
		locatorMap.put("paymentJCB_lst1", "//img[@alt='JCB'][@data-brand='JC']");
		locatorMap.put("paymentChinaUP_lst1", "//img[@alt='China Union Pay'][@data-brand='UP']");
		locatorMap.put("paymentUatp_lst1", "//img[@alt='UATP'][@data-brand='TP']");
		locatorMap.put("camSlider", "//*[@id='slider-input']");
		locatorMap.put("radiobuttonCC", "//label[text()='Credit / Debit Card']");

/// ////////
		locatorMap.put("totalFlightPriceCurrency",
				"//div[@class='price-subcontainer']//abbr[contains(@class,'price-currency-code')]");
		locatorMap.put("totalPriceCurrency",
				"//div[contains(@class,'price-information-card-price-wrapper total-price')]//abbr[contains(@class,'price-currency-code')]");

		locatorMap.put("activateCurrencyChangeDropdown", "//div[@aria-labelledby='mcp-outlined-select-label']");

		locatorMap.put("currencyRateLabelDisplayed", "//div[@class='mcp-select-rate-label']");

		locatorMap.put("cumulativeTotalDisplayed", "//div[@id='price-information-wrapper']//*[@id='price-amount']");
		locatorMap.put("cumulativeInitialCurrencyTotalDisplayed", "//*[@class='mcp-price-amount']");

		locatorMap.put("showPriceDetailsLink", "//*[text()='Show price details']");

		locatorMap.put("initialCurrencyCodeInPopup", "//*[@id='price-dialog-originalcurrency']");
		locatorMap.put("initialCurrencyTotalInPopup", "//*[@id='price-dialog-originalamount']");
		locatorMap.put("newCurrencyCodeInPopup", "//*[@id='price-dialog-currency']");
		locatorMap.put("newCurrencyTotalInPopup", "//*[@id='price-dialog-amount']");
		locatorMap.put("closeButtonPopup",
				"//div[text()='Price details']//parent::div[@role='alertdialog']//span[text()='Close']");

		locatorMap.put("finalAmountPaidCurrency",
				"//div[@class='price-subcontainer']//abbr[contains(@class,'price-currency-code')]");
		locatorMap.put("finalAmountPaidValue", "//div[@class='price-subcontainer']//span[@class='price-amount']");

		locatorMap.put("activatePaymentCountryDropDown", "//div[@aria-controls='country-helper-text']");

		locatorMap.put("itenarySummaryHeader", "//h2[text()='Your Itinerary']");

		locatorMap.put("cardOTPIFrame", "//iframe[contains(@title,'Bank Authentication')]");
		locatorMap.put("masterCardOTPInputField", "//input[@name='challengeDataEntry']");
		locatorMap.put("masterCardOTPSubmitButton", "//input[@value='SUBMIT']");

		locatorMap.put("paymentContactDetailsEmail", "//*[@id=\"contact-info-pres-emailItem-0email\"]");
		locatorMap.put("paymentContactDetailsCountry", "//*[@id=\"contact-info-pres-phoneItem-0phoneCountryCode\"]");
		locatorMap.put("paymentContactDetailsCountrySelection", "//*[@id=\"mat-option-0\"]");
		locatorMap.put("paymentContactDetailsPhone", "//*[@id=\"contact-info-pres-phoneItem-0phone\"]");
		locatorMap.put("paymentContactDetailsConfirmButton", "//span[text()='Confirm']");

		// Info messages for all locators -----

		infoMap.put("paymentSlider", "NA");
		infoMap.put("paymentAcceptCheckbox", "Checkbox to Accept Terms and Conditions");
		infoMap.put("paymentContinuetoPaymentBtn", "Button to Continue to Payment");
		infoMap.put("upgradeContinueButton", "Button to Continue to Upgrade");
		infoMap.put("paymentFopButton", "Radio Button to select Credit Card Type Payment");
		infoMap.put("savedCard_payment", "Radio Button to select Saved Credit Card Type Payment");
		infoMap.put("savedCard_cvv", "Input TextBox to enter CVV of Saved Card");
		infoMap.put("paymentPayNowButton", "Pay Now Button");
		infoMap.put("confirmationText", "Label Text displaying confirmation of Payment");
		infoMap.put("confirmationPNRObj", "Label Text showing confirmed PNR number generated");
		infoMap.put("confirmationFirstName", "Name of Passenger Displayed");

		infoMap.put("paymentCardTypelst", "Selection of Card Type");
		infoMap.put("paymentMasterCard_lst", "Master Card Card Type");
		infoMap.put("paymentAmex_lst", "Amex Card Type");
		infoMap.put("paymentVisa_lst", "Visa Card Type");
		infoMap.put("paymentDiners_lst", "Diners Card Type");
		infoMap.put("paymentJCB_lst", "JCB Card Type");
		infoMap.put("paymentUatp_lst", "UATP Card Type");
		infoMap.put("paymentChinaUP_lst", "ChinaUP Card Type");

		infoMap.put("paymentCardNo_input", "Text Box to Input Card Number");
		infoMap.put("paymentCardholderName_input", "Text Box to Input Card Holder Name");
		infoMap.put("paymentExpiryMonth_input", "Text Box to Input Expiry Month");
		infoMap.put("paymentExpiryYear_input", "Text Box to Input Expiry Year");
		infoMap.put("paymentCVV_input", "Text Box to Input CVV");
		infoMap.put("paymentCountry", "Text Box to Input Country");

		infoMap.put("paymentMasterCard_lst", "Master Card Card Type");
		infoMap.put("paymentAmex_lst", "Amex Card Type");
		infoMap.put("paymentVisa_lst", "Visa Card Type");
		infoMap.put("paymentDiners_lst", "Diners Card Type");
		infoMap.put("paymentJCB_lst", "JCB Card Type");
		infoMap.put("paymentUatp_lst", "UATP Card Type");
		infoMap.put("paymentChinaUP_lst", "ChinaUP Card Type");

		infoMap.put("paymentStreetinput", "ChinaUP Card Street");
		infoMap.put("paymentZipInput", "Text Box to Input ZIP Code");
		infoMap.put("paymentCityInput", "Text Box to Input City");
		infoMap.put("paymentCountrylst", "Text Box to Input Country");

		infoMap.put("camSlider", "Slider for CAM Payment");

		infoMap.put("totalFlightPriceCurrency", "Currency Displayed for the Total Price of Flights");
		infoMap.put("totalPriceCurrency", "Currency Displayed for the Cumulative Total Price");

		infoMap.put("activateCurrencyChangeDropdown", "Button to activate dropdown to change currency");

		infoMap.put("currencyRateLabelDisplayed", "The label displayed showing the currency conversion rate");

		infoMap.put("cumulativeTotalDisplayed", "The label displayed showing the Cumulative Total Amount");
		infoMap.put("cumulativeInitialCurrencyTotalDisplayed",
				"The label displayed showing the Cumulative Total Amount in initial currency");

		infoMap.put("showPriceDetailsLink", "Link to Show Price Details");

		infoMap.put("initialCurrencyCodeInPopup", "The Initial Currency code displayed in Price Details Popup");
		infoMap.put("initialCurrencyTotalInPopup", "The Initial Currency total displayed in Price Details Popup");
		infoMap.put("newCurrencyCodeInPopup", "The New Currency code displayed in Price Details Popup");
		infoMap.put("newCurrencyTotalInPopup", "The New Currency total displayed in Price Details Popup");
		infoMap.put("closeButtonPopup", "Close Button in Price Details Popup");

		infoMap.put("finalAmountPaidCurrency", "Currency Code displayed for the Final Amount Displayed after Payment");
		infoMap.put("finalAmountPaidValue", "Total Amount displayed for the Final Amount Displayed after Payment");

		infoMap.put("activatePaymentCountryDropDown", "Activate the drop down to Select Payment Counry");

		infoMap.put("itenarySummaryHeader", "Header of the Page that displays the summary of the Booked itenary");

		infoMap.put("masterCardOTPInputField", "Input field to enter OTP for MasterCard Credit Card");
		infoMap.put("masterCardOTPSubmitButton", "Submit Button to enter OTP for MasterCard Credit Card");
		infoMap.put("cardOTPIFrame", "IFrame to enter OTP details of master card");

		infoMap.put("paymentContactDetailsEmail", "Input field to enter Email during Payment Process");
		infoMap.put("paymentContactDetailsCountry", "Input field to enter Country during Payment Process");
		infoMap.put("paymentContactDetailsCountrySelection", "Select contact details country during Payment Process");
		infoMap.put("paymentContactDetailsPhone", "Input field to enter Phone during Payment Process");
		infoMap.put("paymentContactDetailsConfirmButton", "Confirm Payment Details during Payment Process");
		infoMap.put("paymentDiscover_lst", "Discover Card Type");
	}

	public void makeCAMPayment(String paymentMode) throws InterruptedException, IOException {

		try {
			waitAndCheckForElementToBeEnabled(locatorMap.get("camSlider"));
			waitForElementToBeVisible("camSlider", elementVisibilityMaxTimeout);
			moveSlider(locatorMap.get("camSlider"), paymentMode);
			takeExtraScreenshot();
		} catch (Exception e) {
			logAssert_Fail("Failed to make CAM Payment");
		}

	}

	public void clickContinueToPayment() throws InterruptedException {
		takeScreenshot();
		Thread.sleep(5000);
		log.debug("Starting payment process ....");
		// To handle Pop Up in case of Upgrade Option is available at time of payment
		if (isTheElementVisible("upgradeContinueButton", elementVisibilityMinTimeout)) {
			clickOnTheElement("upgradeContinueButton");
			log.info("Clicked on the button to Continue Upgrade option");
		} else {
			log.info("Upgrade option not available during payment");
		}

		waitforEitherOfElementToAppear("paymentPayNowButton", "paymentContinuetoPaymentBtn");
		log.debug("One of the elements appeared to continue payment process");
		if (isTheElementVisible("paymentContinuetoPaymentBtn", elementVisibilityMaxTimeout)) {
			log.debug("Going to click Continue Payment button to continue payment process");
//			int elementVisibilityMaxTimeoutTimeout = '5000';
			waitAndClickOnTheElement("paymentContinuetoPaymentBtn", elementVisibilityMaxTimeout);
//			takeScreenshot();
			log.info("Clicked on continue to payment after Page load completed");

		} else {
			log.debug("Going to click Pay Now button to continue payment process");
			waitAndClickOnTheElement("paymentPayNowButton", elementVisibilityMaxTimeout);
			log.info("Clicked on Pay Now Button");
		}
	}

	public void verifyTotalFlightPriceCurrency(String currencyCode) throws InterruptedException, IOException {
		assertTextOnTheElement("totalFlightPriceCurrency", currencyCode);
	}

	public void verifyTotalPriceDisplayedCurrency(String currencyCode) throws InterruptedException, IOException {
		assertTextOnTheElement("totalPriceCurrency", currencyCode);
	}

	public void changeCurrency(String newCurrencyCode) throws InterruptedException, IOException {
		waitAndCheckForElementToBeEnabled(locatorMap.get("paymentPayNowButton"));
		waitAndClickOnTheElement("activateCurrencyChangeDropdown", elementVisibilityMaxTimeout);
		String currencySelectLocator = currencyLoc1 + newCurrencyCode + currencyLoc2;
		waitAndCheckForElementToBeEnabled(currencySelectLocator);
		waitAndClick(currencySelectLocator);
	}

	public void verifyCurrencyRateDisplayed(String initialCurrencyCode, String newCurrencyCode)
			throws InterruptedException, IOException {

		String rateLabelDisplayed = getTextOnTheElement("currencyRateLabelDisplayed");

		if (!rateLabelDisplayed.contains(initialCurrencyCode)) {
			logAssert_Fail("The currency code " + initialCurrencyCode + " was not displayed in the rate label :"
					+ rateLabelDisplayed);
		}

		if (!rateLabelDisplayed.contains(newCurrencyCode)) {
			logAssert_Fail("The currency code " + newCurrencyCode + " was not displayed in the rate label :"
					+ rateLabelDisplayed);
		}

		String exchangeRate = rateLabelDisplayed.split("=")[1].split(newCurrencyCode)[0].trim();
		log.info("The exchange rate displayed is " + exchangeRate);
		// Save the exchange rate displayed for calculations
		scenarioContext.setContext("Exchange_Rate", exchangeRate);
	}

	public void verifyConvertedTotalAmountCalculation() throws InterruptedException, IOException {

		String oldCurrentAmount = getTextOnTheElement("cumulativeInitialCurrencyTotalDisplayed");
		String exchangeRate = scenarioContext.getContext("Exchange_Rate");
		oldCurrentAmount = oldCurrentAmount.trim();
		exchangeRate = exchangeRate.trim();
		log.info(oldCurrentAmount + " is the old currency total");
		log.info(exchangeRate + " is the exchange rate");

		// Calculate New Currency Total
		double oldCurrentAmountValue = Double.valueOf(oldCurrentAmount.toString());
		double exchangeRateValue = Double.valueOf(exchangeRate.toString());
		double newCurrencyTotal = oldCurrentAmountValue * exchangeRateValue;
		log.info("Calculated New Currency Total is " + newCurrencyTotal);

		// Formating the number as per requirement
		String expectedNewCurrencyTotal;
		String expectedUnroundedNewCurrencyTotal;
		expectedUnroundedNewCurrencyTotal = String.format("%.5f", newCurrencyTotal);
		double expectedNewCurrencyTotalNumber = Double.valueOf(expectedUnroundedNewCurrencyTotal);

		// Format the number
		NumberFormat format = NumberFormat.getInstance(Locale.US);
		expectedNewCurrencyTotal = format.format(expectedNewCurrencyTotalNumber);

		int decimalPosition = expectedNewCurrencyTotal.indexOf(".");

		expectedNewCurrencyTotal = expectedNewCurrencyTotal.substring(0, decimalPosition + 2) + '0';
		assertTextOnTheElement("cumulativeTotalDisplayed", expectedNewCurrencyTotal);

		scenarioContext.setContext("Total_Price_Displayed", expectedNewCurrencyTotal);
		scenarioContext.setContext("Total_Price_Displayed_OldCurrency", oldCurrentAmount);

	}

	public void clickShowPriceDetailsLink() throws InterruptedException, IOException {
		waitAndClickOnTheElement("showPriceDetailsLink", elementVisibilityMaxTimeout);
	}

	public void closePriceDetailsPopup() throws InterruptedException, IOException {
		waitAndClickOnTheElement("closeButtonPopup", elementVisibilityMaxTimeout);
	}

	public void verifyCurrencyDetailsInPopup(String currencyCode) throws InterruptedException, IOException {
		String totalPriceDisplayed = scenarioContext.getContext("Total_Price_Displayed");
		assertTextOnTheElement("newCurrencyTotalInPopup", totalPriceDisplayed);
		assertTextOnTheElement("newCurrencyCodeInPopup", currencyCode);
	}

	public void verifyInitialCurrencyDetailsInPopup(String currencyCode) throws InterruptedException, IOException {
		String totalPriceDisplayed = scenarioContext.getContext("Total_Price_Displayed_OldCurrency");
		assertTextOnTheElement("initialCurrencyTotalInPopup", totalPriceDisplayed);
		assertTextOnTheElement("initialCurrencyCodeInPopup", currencyCode);
	}

	public void selectAndPayWithSavedCard(String cvv) throws InterruptedException, IOException {
		try {

			waitAndClickOnTheElement("savedCard_payment", elementVisibilityMaxTimeout);
			waitForElementToBeVisible("savedCard_cvv", elementVisibilityMaxTimeout);
			cvv = Base64EncryptionUtil.readDecryptedData(cvv);
			enterTextOnTheElement("savedCard_cvv", cvv);
			waitAndClickOnTheElement("paymentAcceptCheckbox", elementVisibilityMaxTimeout);
			clickPayNow();
			takeExtraScreenshot();
		} catch (Exception e) {
			logAssert_Fail("Failed to pay with Saved Card");
		}

	}

	public void selectAndPayWithCreditCard(String cardtype,String cardnumber, String cardholdername, String expiry,
			String cvv, String streetname, String city, String postcode, String country)
			throws InterruptedException, IOException {
		try {
			log.debug("Going to select card type for payment process");
//			selectCardType1(cardtype);
			clickOnTheElement("radiobuttonCC");
			waitAndClickOnTheElement("radiobuttonCC",1);

			enterCreditCardDetails(cardnumber, cardholdername, expiry, cvv);
			enterBillingDetails(streetname, city, postcode, country);

			clickPayNow();

		} catch (Exception e) {
			logAssert_Fail("Failed to pay with Credit Card");
		}
	}

	public void clickPayNow(String cardtype) throws InterruptedException, IOException {
		try {
			scrollElementToVisibility("paymentPayNowButton");
			waitAndClickOnTheElement("paymentPayNowButton", elementVisibilityMaxTimeout);
			// Handle extra authentication code in case of Master Card
			if (cardtype.equalsIgnoreCase("MasterCard")) {
				String decryptedOTP = Base64EncryptionUtil.readDecryptedData("masterCardOTP");
				FrameLocator frameLocator = page.frameLocator(locatorMap.get("cardOTPIFrame"));
				frameLocator.locator(locatorMap.get("masterCardOTPInputField")).waitFor();
				frameLocator.locator(locatorMap.get("masterCardOTPInputField")).fill(decryptedOTP);
				frameLocator.locator(locatorMap.get("masterCardOTPSubmitButton")).click();
			}
		} catch (Exception e) {
			logAssert_Fail("Failed to Proceed with Payment");
		}
	}

	public void clickPayNow() throws InterruptedException, IOException {
		try {
			scrollElementToVisibility("paymentPayNowButton");
			waitAndClickOnTheElement("paymentPayNowButton", elementVisibilityMaxTimeout);
		} catch (Exception e) {
			logAssert_Fail("Failed to Proceed with Payment");
		}
	}

	public void savePNRDetails() throws InterruptedException, IOException {
		page.waitForTimeout(20000);
		waitForElementToBeVisible("confirmationText", elementVisibilityMaxTimeout);
		savePNRDetailsDisplayed();
//		takeExtraScreenshot();
		takeScreenshot();

	}

	public void savePNRDetailsDisplayed() throws InterruptedException, IOException {
		String confirmText = getTextOnTheElement("confirmationPNRObj");
		log.info("PNR is  :" + confirmText);
		log.info("Booking Completed");
//		takeExtraScreenshot();

		scenarioContext.setContext("PNR_Retrieved", confirmText);

		String confirmationFirstName = getTextOnTheElement("confirmationFirstName");

		String splitFirstName = confirmationFirstName.split(" ")[0];
		String splitLastName = confirmationFirstName.split(" ")[1];

		log.info("First Name in confirmation page :" + splitFirstName);
		log.info("Last Name in confirmation page :" + splitLastName);

		scenarioContext.setContext("FirstName_Retrieved", splitFirstName);

		scenarioContext.setContext("LastName_Retrieved", splitLastName);

	}

	public void selectCardType(String cardtype) throws InterruptedException, IOException
	{

		waitForElementToBeVisible("paymentPayNowButton", elementVisibilityMaxTimeout);
		waitAndClickOnTheElement("paymentFopButton", elementVisibilityMaxTimeout);
		waitAndClickOnTheElement("paymentFopButton", elementVisibilityMaxTimeout);

		// Card Names
		final String cardOption1 = "Visa";
		final String cardOption2 = "MasterCard";
		final String cardOption3 = "Amex";
		final String cardOption4 = "Diners";
		final String cardOption5 = "Discover";
		final String cardOption6 = "JCB";
		final String cardOption7 = "ChinaUnionPay";
		final String cardOption8 = "Uatp";

		switch (cardtype) {
			case cardOption1: {
				waitAndClickOnTheElement("paymentVisa_lst1", elementVisibilityMinTimeout);
				break;
			}
			case cardOption2: {
				waitAndClickOnTheElement("paymentMasterCard_lst1", elementVisibilityMinTimeout);
				break;
			}
			case cardOption3: {
				waitAndClickOnTheElement("paymentAmex_lst1", elementVisibilityMinTimeout);
				break;
			}
			case cardOption4: {
				waitAndClickOnTheElement("paymentDiners_lst1", elementVisibilityMinTimeout);
				break;
			}
			case cardOption5: {
				waitAndClickOnTheElement("paymentDiscover_lst1", elementVisibilityMinTimeout);
				break;
			}
			case cardOption6: {
				waitAndClickOnTheElement("paymentJCB_lst1", elementVisibilityMinTimeout);
				break;
			}
			case cardOption7: {
				waitAndClickOnTheElement("paymentChinaUP_lst1", elementVisibilityMinTimeout);
				break;
			}
			case cardOption8: {
				waitAndClickOnTheElement("paymentUatp_lst1", elementVisibilityMinTimeout);
				break;
			}
			default: {
				log.info("Credit Card choice not found !");
				logAssert_Fail("Credit Card choice not found !");
			}
		}
	}
	public void selectCardType1(String cardtype) throws InterruptedException, IOException {

		waitForElementToBeVisible("paymentPayNowButton", elementVisibilityMaxTimeout);
		waitAndClickOnTheElement("paymentFopButton", elementVisibilityMaxTimeout);
		waitAndClickOnTheElement("paymentFopButton", elementVisibilityMaxTimeout);
		waitAndClickOnTheElement("paymentCardTypelst", elementVisibilityMaxTimeout);

		// Card Names

		final String cardOption1 = "MasterCard";
		final String cardOption2 = "Amex";
		final String cardOption3 = "Visa";
		final String cardOption4 = "Diners";
		final String cardOption5 = "JCB";
		final String cardOption6 = "Uatp";
		final String cardOption7 = "ChinaUnionPay";

		if (!isTheElementVisible("paymentVisa_lst", elementVisibilityMinTimeout)) {
			log.info("Clicking the Card Option Drop Down again to select Card");
			waitAndClickOnTheElement("paymentCardTypelst", elementVisibilityMaxTimeout);
		}

		switch (cardtype) {
		case cardOption1: {
			waitAndClickOnTheElement("paymentMasterCard_lst", elementVisibilityMinTimeout);
			break;
		}
		case cardOption2: {
			waitAndClickOnTheElement("paymentAmex_lst", elementVisibilityMinTimeout);
			break;
		}
		case cardOption3: {
			waitAndClickOnTheElement("paymentVisa_lst", elementVisibilityMinTimeout);
			break;
		}
		case cardOption4: {
			waitAndClickOnTheElement("paymentDiners_lst", elementVisibilityMinTimeout);
			break;
		}
		case cardOption5: {
			waitAndClickOnTheElement("paymentJCB_lst", elementVisibilityMinTimeout);
			break;
		}
		case cardOption6: {
			waitAndClickOnTheElement("paymentUatp_lst", elementVisibilityMinTimeout);
			break;
		}
		case cardOption7: {
			waitAndClickOnTheElement("paymentChinaUP_lst", elementVisibilityMinTimeout);
			break;
		}
		default: {
			log.info("Credit Card choice not found !");
			logAssert_Fail("Credit Card choice not found !");
		}
		}
	}

	public void enterCreditCardDetails(String cardnumber, String cardholdername, String expiry, String cvv)
			throws InterruptedException, IOException {

		try {
			String decryptedCardNumber = Base64EncryptionUtil.readDecryptedData(cardnumber);
			waitForElementToBeVisible("paymentCardNo_input", elementVisibilityMaxTimeout);
			enterTextOnTheElement("paymentCardNo_input", decryptedCardNumber);
			enterTextOnTheElement("paymentCardholderName_input", cardholdername);
			String month = expiry.split("/")[0];
			String year = expiry.split("/")[1];
			enterTextOnTheElement("paymentExpiryMonth_input", month);
			enterTextOnTheElement("paymentExpiryYear_input", year);
			String decryptedCVV = Base64EncryptionUtil.readDecryptedData(cvv);
			enterTextOnTheElement("paymentCVV_input", decryptedCVV);
			log.info("Card details have been filled");
		} catch (Exception e) {
			logAssert_Fail("Failed to Proceed with Payment using Card");
		}
	}

	public void enterBillingDetails(String streetname, String city, String postcode, String country)
			throws InterruptedException, IOException {
		try {
			enterTextOnTheElement("paymentStreetinput", streetname);
			enterTextOnTheElement("paymentZipInput", postcode);
			enterTextOnTheElement("paymentCityInput", city);

			String currentCountrySelected = getTextOnTheElement("activatePaymentCountryDropDown");
			if (!currentCountrySelected.contains(country)) {
				clickOnTheElement("activatePaymentCountryDropDown");
				String countryLocator = countryLoc1 + country + countryLoc2;
				waitAndClick(countryLocator);
			}

			log.info("Address details filled");

			waitAndClickOnTheElement("paymentAcceptCheckbox", elementVisibilityMaxTimeout);
		} catch (Exception e) {
			logAssert_Fail("Failed to enter billing details");
		}
	}

	public void verifyFinalAmountDisplayed() throws InterruptedException, IOException {
		String expectedTotalAmount = scenarioContext.getContext("Total_Price_Displayed");
		assertTextOnTheElement("finalAmountPaidValue", expectedTotalAmount);
	}

	public void verifyFinalCurrencyCodeDisplayed(String expectedCurrencyCode) throws InterruptedException, IOException {
		assertTextOnTheElement("finalAmountPaidCurrency", expectedCurrencyCode);
	}
	public void verifyBookingCodeDisplayed(String expectedBookingCode) throws InterruptedException, IOException {
		assertTextOnTheElement("BookingCodeDisplayed", expectedBookingCode);
	}


	public void enterContactDetailsForPaymentIfNeeded(String email, String country, String phone)
			throws InterruptedException {
		if (isTheElementVisible("paymentContactDetailsEmail", elementVisibilityMinTimeout)) {
			enterTextOnTheElement("paymentContactDetailsEmail", email);
			enterTextOnTheElement("paymentContactDetailsCountry", country);
			waitAndClickOnTheElement("paymentContactDetailsCountrySelection", elementVisibilityMinTimeout);
			enterTextOnTheElement("paymentContactDetailsPhone", phone);
			waitAndClickOnTheElement("paymentContactDetailsConfirmButton", elementVisibilityMinTimeout);
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
