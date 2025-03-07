package com.e2e.stepdef;

import java.io.IOException;

import org.junit.Assert;

import com.e2e.config.Configuration;
import com.e2e.pages.BasePage;
import com.e2e.pages.PaymentPage;
import com.e2e.utils.Base64EncryptionUtil;
import com.e2e.pages.BaggageSelectionPage;
import com.microsoft.playwright.Locator;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PaymentPageSteps extends BasePage {

	PaymentPage paymentPage = new PaymentPage();

	// Flag Variables
	boolean flagForContinueToPayment = true;

	// Default Values
	String decryptedContactEmail = Base64EncryptionUtil.readDecryptedData("paymentContactDetailsEmail");
	String decryptedContactCountry = Base64EncryptionUtil.readDecryptedData("paymentContactDetailsCountry");
	String decryptedContactPhone = Base64EncryptionUtil.readDecryptedData("paymentContactDetailsPhone");

	@When("User makes {string} payment using CAM")
	public void user_makes_payment_using_cam(String paymentmode) throws InterruptedException, IOException {
		paymentPage.clickContinueToPayment();
		paymentPage.makeCAMPayment(paymentmode);
		// Set flag for Continue To Payment as false to pay the remaining amount
		flagForContinueToPayment = false;
	}

	@Then("User selects the favorite credit card and enter {string} completes payment and create the pnr")
	public void selectAndPayWithSavedCard(String cvv) throws InterruptedException, IOException {
		if (flagForContinueToPayment) {
			paymentPage.clickContinueToPayment();
		}
		paymentPage.selectAndPayWithSavedCard(cvv);
		paymentPage.savePNRDetails();
	}

	@When("User makes payment using creditcard {string},{string},{string},{string},{string},{string},{string},{string} and {string}")
	public void user_enter_payment_deatils(String cardtype,String cardnumber, String cardholdername, String expiry,
			String cvv, String streetname, String city, String postcode, String country)
			throws InterruptedException, IOException {

		if (flagForContinueToPayment) {

			paymentPage.clickContinueToPayment();
			paymentPage.enterContactDetailsForPaymentIfNeeded(decryptedContactEmail, decryptedContactCountry,
					decryptedContactPhone);
		}
		paymentPage.selectAndPayWithCreditCard(cardtype,cardnumber, cardholdername, expiry, cvv, streetname, city,
				postcode, country);
		paymentPage.savePNRDetails();
		flagForContinueToPayment = true;
	}
//	@Then("User make payment using creditcard {string},{string},{string},{string},{string},{string},{string} and {string}")
//	public void user_enter_payment_details(String cardnumber, String cardholdername, String expiry,
//										   String cvv, String streetname, String city, String postcode, String country)
//			throws InterruptedException, IOException {
//
//		if (flagForContinueToPayment) {
//
//			paymentPage.clickContinueToPayment();
//			paymentPage.enterContactDetailsForPaymentIfNeeded(decryptedContactEmail, decryptedContactCountry,
//					decryptedContactPhone);
//		}
//		paymentPage.selectAndPayWithCreditCard(cardnumber, cardholdername, expiry, cvv, streetname, city,
//				postcode, country);
//		paymentPage.savePNRDetails();
//		flagForContinueToPayment = true;
//	}
	@When("User makes payment using creditcard {string},{string},{string},{string},{string},{string},{string},{string} and {string} for additional purchase")
	public void user_enter_payment_details_additionalPurchase(String cardtype,String cardnumber, String cardholdername,
			String expiry, String cvv, String streetname, String city, String postcode, String country)
			throws InterruptedException, IOException {

		paymentPage.clickContinueToPayment();
		paymentPage.selectAndPayWithCreditCard(cardtype,cardnumber, cardholdername, expiry, cvv, streetname, city,
				postcode, country);
	}

	@When("User clicks on continue to payment button")
	public void clickContinueToPayment() throws InterruptedException, IOException {
		paymentPage.clickContinueToPayment();
//		paymentPage.selectAndPayWithCreditCard();
	}

	@Then("User verifies price currency details are in {string}")
	public void verifyPriceDisplayedDetails(String currencyCode) throws InterruptedException, IOException {
		paymentPage.verifyTotalFlightPriceCurrency(currencyCode);
		paymentPage.verifyTotalPriceDisplayedCurrency(currencyCode);
	}

	@Then("User clicks on change currency dropdown box change the currency to {string}")
	public void changeCurrencyFromDropDown(String newCurrencyCode) throws InterruptedException, IOException {
		flagForContinueToPayment = false;
		paymentPage.changeCurrency(newCurrencyCode);
	}

	@Then("User verifies exchange rate of {string} to {string} should be displayed")
	public void verifyExchangeRateDisplayed(String initialCurrencyCode, String newCurrencyCode)
			throws InterruptedException, IOException {
		paymentPage.verifyCurrencyRateDisplayed(initialCurrencyCode, newCurrencyCode);
		paymentPage.verifyConvertedTotalAmountCalculation();
	}

	@Then("User clicks on show price details link under total price")
	public void clickOnShowPriceLink() throws InterruptedException, IOException {
		paymentPage.clickShowPriceDetailsLink();
	}

	@And("User verifies details in price details popup shown for {string} and {string}")
	public void verifyPriceDetailsInPopUp(String initialCurrencyCode, String newCurrencyCode)
			throws InterruptedException, IOException {
		paymentPage.verifyInitialCurrencyDetailsInPopup(initialCurrencyCode);
		paymentPage.verifyCurrencyDetailsInPopup(newCurrencyCode);
	}

	@Then("User closes the price details popup")
	public void closePriceDetailsPopUp() throws InterruptedException, IOException {
		paymentPage.closePriceDetailsPopup();
		flagForContinueToPayment = false;
	}

	@Then("User verifies the price details displayed in currency {string}")
	public void verifyPriceDetails(String expectedCurrencyCode) throws InterruptedException, IOException {
		paymentPage.verifyFinalCurrencyCodeDisplayed(expectedCurrencyCode);
	}
}
