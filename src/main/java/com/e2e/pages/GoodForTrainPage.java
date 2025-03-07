package com.e2e.pages;

import java.io.IOException;
import java.util.HashMap;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import com.e2e.utils.PropertyReader;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class GoodForTrainPage extends BasePage {

	private HashMap<String, String> locatorMap = new HashMap<>();
	private HashMap<String, String> infoMap = new HashMap<>();

	public GoodForTrainPage() {
		// Locators
		locatorMap.put("acceptPopupbtn", "//button[@id='cm-acceptAll']");
		locatorMap.put("goodForTrainHeader", "//maui-headline[contains(text(),'Good for Train')]");
		locatorMap.put("goodForTrainBookingCode", "//input[@name=\"goodForTrainAuthentication.bookingCode\"]");
		locatorMap.put("goodForTrainLastName", "//input[@name=\"goodForTrainAuthentication.lastName\"]");
		locatorMap.put("goodForTrainFirstName", "//input[@name='goodForTrainAuthentication.firstName']");

		locatorMap.put("goodForTrainSubmitBtn", "//button[@type=\"submit\"]");
		locatorMap.put("goodForTrainSelectFlightsHeader", "//maui-headline[contains(text(),'Select Flights')]");
		locatorMap.put("goodForTrainSelectOutboundFlight", "(//maui-checkbox[contains(@name,'chooseFlight')])[1]");
		locatorMap.put("goodForTrainSelectInboundFlight", "(//maui-checkbox[contains(@name,'chooseFlight')])[2]");
		locatorMap.put("goodForTrainNextButton", "//span[contains(text(),'Next')]");
		locatorMap.put("goodForTrainRequestVoucharText", "//maui-headline[contains(text(),'Request voucher')]");
		locatorMap.put("goodForTrainEmail", "//input[@name=\"goodForTrainQuery.voucherRequestEMail\"]");
		locatorMap.put("goodForTrainCondition", "//*[contains(text(),'I have read the')]");
		locatorMap.put("goodForTrainVoucherCodeHeadingText",
				"//maui-headline[contains(text(),'Your personal train voucher code')]");
		locatorMap.put("goodForTrainDownloadTicketBtn", "//a[contains(text(),'Download your ticket')]");
		locatorMap.put("goodForTrainRatingPopupIframeLocaor", "//iframe[@title=\"Usabilla Feedback Form\"]");
		locatorMap.put("goodForTrainRatingPopupCloseBtn", "//button[@ng-click=\"validate()\"]");
		locatorMap.put("goodForTrainVoucherCodeText", "(//p[contains(text(),'Voucher code')])[1]");

		// Info messages for all locators
		infoMap.put("acceptPopupbtn", "Accept popup");
		infoMap.put("goodForTrainHeader", "Good for train text");
		infoMap.put("goodForTrainBookingCode", "Enter PNR");
		infoMap.put("goodForTrainLastName", "Enter Last Name");
		infoMap.put("goodForTrainFirstName", "Enter First Name");
		infoMap.put("goodForTrainSubmitBtn", "Submit Button");
		infoMap.put("goodForTrainSelectFlightsHeader", "Select flight text");
		infoMap.put("goodForTrainSelectOutboundFlight", "Select Outbound Flight");
		infoMap.put("goodForTrainSelectInboundFlight", "Select Inbound Flight");
		infoMap.put("goodForTrainNextButton", "Next Button");
		infoMap.put("goodForTrainRequestVoucharText", "Next Button");
		infoMap.put("goodForTrainEmail", "Enter email id");
		infoMap.put("goodForTrainCondition", "Terms and conditions Text");
		infoMap.put("goodForTrainVoucherCodeHeadingText", "Voucher Code Verification Text");
		infoMap.put("goodForTrainDownloadTicketBtn", "Download Ticket Button");
		infoMap.put("goodForTrainRatingPopupIframeLocaor", "Rating PopUp Iframe Locator");
		infoMap.put("goodForTrainRatingPopupCloseBtn", "Rating PopUp Close btn");
		infoMap.put("goodForTrainVoucherCodeText", "Voucher Code Text");

	}

	public void verifyElementVisibility(String locator) throws InterruptedException {
		try {
			boolean status = false;
			status = isTheElementVisible(locator, elementVisibilityMaxTimeout);
			Assert.assertTrue("Element is not Visible", status);
		} catch (Exception e) {
			logAssert_Fail(infoMap.get(locator) + " is not visible. " + locator);
		}
	}

	public void clicks(String locator) throws InterruptedException {
		try {
			waitAndClick(locatorMap.get(locator));
		} catch (Exception e) {
			logAssert_Fail("Fails to select " + infoMap.get(locator) + ". Locator is: " + locatorMap.get(locator));
		}
	}

	public void verifyAndEnterText(String locator, String data) throws InterruptedException {
		boolean status = false;
		status = isTheElementVisible(locator, elementVisibilityMaxTimeout);

		if (status == true) {
			enterText(locatorMap.get(locator), data);
		} else {
			logAssert_Fail(
					"Fails to enter text in " + infoMap.get(locator) + ". Locator is: " + locatorMap.get(locator));
		}
	}

	public void retrievePNRDetailsFromGoodForTrain(String firstname, String lastname)
			throws InterruptedException, IOException {
		try {
			String bookingCode_value = scenarioContext.getContext("PNR_Retrieved");
			FrameLocator iframeLocator = page.frameLocator(locatorMap.get("goodForTrainRatingPopupIframeLocaor"));

			if (iframeLocator.locator(locatorMap.get("goodForTrainRatingPopupCloseBtn")).isVisible()) {
				log.info("Inside Pop up if conditions");
				iframeLocator.locator(locatorMap.get("goodForTrainRatingPopupCloseBtn")).click();
			}

			verifyElementVisibility("goodForTrainHeader");
			log.info("header found");
			verifyAndEnterText("goodForTrainBookingCode", bookingCode_value);
			verifyAndEnterText("goodForTrainFirstName", firstname);
			verifyAndEnterText("goodForTrainLastName", lastname);
			verifyElementVisibility("goodForTrainSubmitBtn");
			clicks("goodForTrainSubmitBtn");
		}

		catch (Exception e) {
			logAssert_Fail("Failed to Retrieve PNR Details through GoodForTrain URL");
		}

	}

	public void selectFlightsGoodForTrain(String email) throws InterruptedException, IOException {
		try {
			verifyElementVisibility("goodForTrainSelectFlightsHeader");
			verifyElementVisibility("goodForTrainSelectOutboundFlight");
			clicks("goodForTrainSelectOutboundFlight");
			verifyElementVisibility("goodForTrainSelectInboundFlight");
			clicks("goodForTrainSelectInboundFlight");
			verifyElementVisibility("goodForTrainNextButton");
			clicks("goodForTrainNextButton");
		}

		catch (Exception e) {
			logAssert_Fail("Failed to Select flights for good for train");
		}

	}

	public void addEmailGoodForTrain(String email) throws InterruptedException, IOException {
		try {
			verifyElementVisibility("goodForTrainRequestVoucharText");
			verifyAndEnterText("goodForTrainEmail", email);
			verifyElementVisibility("goodForTrainCondition");
			clicks("goodForTrainCondition");
			verifyElementVisibility("goodForTrainSubmitBtn");
			clicks("goodForTrainSubmitBtn");
		}

		catch (Exception e) {
			logAssert_Fail("Failed to add email id");
		}

	}

	public void verifyVoucherAndQR() throws InterruptedException, IOException {
		try {
			verifyElementVisibility("goodForTrainVoucherCodeHeadingText");
			verifyElementVisibility("goodForTrainDownloadTicketBtn");
			verifyElementVisibility("goodForTrainVoucherCodeText");

		} catch (Exception e) {
			logAssert_Fail("Failed to Verify voucher code and qr code");
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
