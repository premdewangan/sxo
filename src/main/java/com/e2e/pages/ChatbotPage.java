package com.e2e.pages;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;

import com.e2e.utils.PropertyReader;
import com.microsoft.playwright.Page;
import com.e2e.utils.Base64EncryptionUtil;
import com.e2e.utils.DateManipulator;
import com.e2e.config.WaitsConfig;

public class ChatbotPage extends BasePage {

	private HashMap<String, String> locatorMap = new HashMap<>();
	private HashMap<String, String> infoMap = new HashMap<>();

	// Constants
	String outBoundDate;
	String newOutboundDate;
	String inboundDate;
	String newInboundDate;

	public ChatbotPage() {
		// Locators
		locatorMap.put("startChat", "//button[@id='webchatGetStartedButton']");
		locatorMap.put("acceptPopupbtn", "//button[@id='cm-acceptAll']");
		locatorMap.put("baggageAllowanceBtnChatbot", "//span[contains(text(),'Baggage allowance')]");
		locatorMap.put("selectYesBtnChatbot", "//span[contains(text(),'Yes')]");
		locatorMap.put("whereToStartTripChatbot", "//div[contains(text(),'Where do you start your trip')]");
		locatorMap.put("whereToStopTripChatbot", "//div[contains(text(),'Where are you travelling to?')]");
		locatorMap.put("textField", "//*[@id='webchatInputMessageInputInTextMode']");
		locatorMap.put("sendTextBtn", "//*[@id='webchatInputMessageSendMessageButton']");
		locatorMap.put("firstClassOption", "//span[contains(text(),'First Class')]");
		locatorMap.put("milesAndMoreOption", "//span[contains(text(),'Miles and More Basic')]");
		locatorMap.put("baggageRegulationChatbot", "//button[contains(text(),'Baggage regulation')]");
		locatorMap.put("chatBotBaggageAllowenceTxt",
				"//h1[contains(text(),'Carry-on baggage rules at Lufthansa')or contains(text(),'Hand baggage')]");
		locatorMap.put("noneOfTheseBtn", "//span[contains(text(),'None of these')]");
		locatorMap.put("chatBotFlightIrregularityBtn",
				"//span[contains(text(),'Compensation for flight irregularities') or contains(text(),'Check compensation entitlement according') or contains(text(),'Flight delay or cancellation')]");
		locatorMap.put("chatBotFlightIrrLink",
				"//a[contains(text(),'https://www.lh.com/xx/en/compensation-in-the-event-of-flight-irregularities') or contains(text(),'https://www.austrian.com/xx/en/help-and-contact#/irregularities/cancellation') or  contains(text(),'https://www.swiss.com/xx/en/customer-support/compensation-check')]");
		locatorMap.put("delayedBaggaeBtnChatbot", "//span[contains(text(),'Delayed baggage')]");
		locatorMap.put("claimCompensationChatbot", "//span[contains(text(),'Claim compensation')]");
		locatorMap.put("requestForCompensationBtn", "//button[contains(text(),'Request compensation')]");
		locatorMap.put("damagedBaggageBtnChatbot", "//span[contains(text(),'Baggage is damaged')]");
		locatorMap.put("chatbotBackToOverViewBtn", "//*[contains(text(),'Back to overview')]");
		locatorMap.put("chatbotEnterLastNameMsg", "//div[contains(text(),'enter your last name')]");
		locatorMap.put("chatBotYourFlightOperatesAsTxt",
				"//div[contains(text(),'Your flight operates as scheduled.')]");
		locatorMap.put("chatBotFlightBookingStatusFirstFlight",
				"(//*[contains(text(),'Waitlist only') or contains(text(),'Confirmed')])[1]");
		locatorMap.put("chatBotCheckRefundBtn", "//*[contains(text(),'Check refund')]");
		locatorMap.put("chatBotRefundText", "//*[contains(text(),'Your refund')]");
		locatorMap.put("chatBotPerformRefundBtn", "//*[contains(text(),'Perform refund')]");
		locatorMap.put("chatBotRefundConfirmation",
				"//*[contains(text(),'I have successfully processed your refund')]");
		locatorMap.put("chatBotFlightBookingStatus",
				"(//*[contains(text(),'Waitlist only') or contains(text(),'Confirmed')])[1]");
		locatorMap.put("chatBotNxtSliderFlightBtn", "//button[@aria-label='next slide / item']");
		locatorMap.put("chatBotFlightStatusCancel", "(//div[contains(text(),'Cancelled')])[1]");
		locatorMap.put("chatBotSearchAltFlight", "//button[contains(@aria-label,'Item 1 of 3: Search alternative')]");
		locatorMap.put("chatbotGlobalContinue", "//button[contains(text(),'Continue')]");
		locatorMap.put("chatBotDetailsBtn", "(//*[contains(text(),'View details')])[1]");
		locatorMap.put("chatBotContinueRebook", "//*[contains(text(),'Continue rebooking')]");
		locatorMap.put("acceptFlightBtnClick",
				"(//*[contains(text(),'Accept proposed flight') or contains(text(),'Rebook now')])");
		locatorMap.put("rebookingConfirmationText",
				"//div[contains(text(),'Your rebooking was successful') or contains(text(),'successfully rebooked your flight') or contains(text(),'rebooking is done')]");
		locatorMap.put("chatBotFlightBookingStatusAfterRebooking",
				"(//*[contains(text(),'Waitlist only') or contains(text(),'Confirmed')])[3]");
		locatorMap.put("yourBookingConfirmationText", "//div[contains(text(),'Your booking code is still the same')]");
		locatorMap.put("verifySelectedFlight", "(//div[contains(text(),'Gate')])[1]");
		locatorMap.put("isThereAnythingText", "//div[contains(text(),'Is there anything else I can help you with?')]");
		locatorMap.put("chatBotFlightDisruptedMsg",
				"//*[contains(text(),'has been affected by a flight disruption.')]");
		locatorMap.put("proposedFlightBtn",
				"(//*[contains(text(),'Show details for proposed flight') or contains(text(),'Continue with proposal')])");
		locatorMap.put("rebookNowBtn", "//span[contains(text(),'Rebook now')]");
		locatorMap.put("chatbotRebookBtn", "//span[contains(text(),'Rebook')]");
		locatorMap.put("chatbotContinueWithAllBtn", "//span[contains(text(),'continue with all') or contains(text(),'Both trips')]");
		locatorMap.put("chatbotInBoundSelectionForRebookBtn", "//button[contains(@aria-label,'Item 2 of 3: Rebook')]");
		locatorMap.put("chatbotBothBoundsSelectionForRebookBtn",
				"//button[contains(@aria-label,'Item 3 of 3: Both trips')]");
		locatorMap.put("chatbotRebookPickDateBtnFirst", "//*[contains(text(),'Pick date')]");
		locatorMap.put("chatbotRebookPickDateBtnSecond", "(//*[contains(text(),'Pick date')])[2]");
		locatorMap.put("selectOutboundNewProposedFlight", "(//div[contains(text(),'Select')])[1]");
		locatorMap.put("selectInboundNewProposedFlight", "(//div[contains(text(),'Select')])[7]");
		locatorMap.put("summerizeRebooking",
				"//div[contains(text(),'After that you can decide if you want to confirm the rebooking')]");
		locatorMap.put("chatbotFlightRebookYesPleaseBtn", "//span[contains(text(),'Yes, please')]");
		locatorMap.put("chatbotClickContinueBtnFirst", "//span[contains(text(),'Continue')]");
		locatorMap.put("chatbotClickContinueBtnSecond", "(//span[contains(text(),'Continue')])[2]");
		locatorMap.put("chatbotPriceOverviewText", "//div[contains(text(),'Price overview')]");
		locatorMap.put("chatbotProceedWithPaymentBtn", "//span[contains(text(),'Yes, proceed with payment')]");
		locatorMap.put("chatbotGoToPaymentBtn", "//button[contains(text(),'Go to payment page')]");
		locatorMap.put("chatbotRebookPaymentPageText", "//h1[contains(text(),'Select your payment method')]");
		locatorMap.put("paymentFopButton", "//article[@id='fop-cc']//input[@name='radios']");
		locatorMap.put("paymentCardType_lst", "//div[contains(@class,'cardtype-select')]");
		locatorMap.put("paymentMasterCard_lst", "//Ul[@aria-label='Card type listbox']//*[@data-value='CA']");
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
		locatorMap.put("paymentCVV_input", "//input[@aria-labelledby='cvv-label']");
		locatorMap.put("paymentCountry", "(//Ul[@aria-label='Country list']//*[@data-value='GB'])[1]");
		locatorMap.put("paymentStreetinput", "//input[@aria-labelledby='street-label']");
		locatorMap.put("paymentZipInput", "//input[@aria-labelledby='zip-label']");
		locatorMap.put("paymentCityInput", "//input[@aria-labelledby='city-label']");
		locatorMap.put("paymentCountrylist", "//div[@aria-labelledby='country-outlined-select-label']");
		locatorMap.put("paymentAcceptCheckBox", "//input[@id='terms-checkbox']");
		locatorMap.put("paymentPayNowBtn", "//span[text()='Pay Now']");
		locatorMap.put("chatbotRebookConfirmationText", "//div[contains(text(),'Confirmation')]");
		locatorMap.put("chatbotRebookInboundDate", "(//div[@class=\"ac-textBlock\"])[18]");
		locatorMap.put("chatbotRebookOutboundDate", "(//div[@class=\"ac-textBlock\"])[05]");
		locatorMap.put("chatbotAdditionalServicesText", "//*[contains(text(),'Unfortunately')]");
		locatorMap.put("chatbotContinueToRebookBtn", "//span[contains(text(),'Continue with rebooking')]");
		locatorMap.put("chatBotFlightBookingStatusThirdFlight",
				"(//*[contains(text(),'Waitlist only') or contains(text(),'Confirmed')])[3]");
		locatorMap.put("chatBotFlightBookingStatusFourthFlight",
				"(//*[contains(text(),'Waitlist only') or contains(text(),'Confirmed')])[4]");
		locatorMap.put("chatbotYesButton", "//span[contains(text(),'Yes')]");
		locatorMap.put("chatbotNoButton", "((//div[contains(@class,'webchat-quick-reply-template-re')])[2]//button)[2]");
		locatorMap.put("chatbotCreateNewReportForDelayedBaggageButton", "//span[contains(text(),'Create new report')]");
		locatorMap.put("chatbotCreateReportForDelayedBaggageURL", "//button[contains(text(),'Create report')]");
		locatorMap.put("chatbotReportDelayedBaggageText", "//h1[contains(text(),'Report a delayed baggage')]");
		locatorMap.put("chatbotNewQuestionButton", "//span[contains(text(),'New question')]");
		locatorMap.put("chatbotCreateNewReportForDamagedBaggageButton", "(//span[contains(text(),'Create new report')])[2]");
		locatorMap.put("chatbotDamagedBaggageYesButton", "(//span[contains(text(),'Yes')])[2]");
		locatorMap.put("chatbotCreateReportForDamagedBaggageURL", "//button[contains(text(),'Report damaged bag')]");
		locatorMap.put("chatbotReportDamagedBaggageText", "//h1[contains(text(),'damaged baggage')]");
		locatorMap.put("regularBaggage","(//span[contains(text(),'Regular baggage')])[1]");
		locatorMap.put("economyLightFare", "//span[contains(text(),'Economy Light')]");
		locatorMap.put("RequestCompensation","(//*[contains(text(),'Request compensation')])[1]");
		locatorMap.put("chatbotOnlineCheckInbtn","//button[contains(@id,'webchatButtonTemplateButton-')]");
		locatorMap.put("chatbotIDontHaveFlightNumber","(//button[contains(@id,'webchatQuickReplyTemplateButton')])[7]");
		locatorMap.put("chatbotTodayBtn","(//button[contains(@id,'webchatQuickReplyTemplateButton')])[8]");
		locatorMap.put("chatbotFlightStatus","(//div[@class='ac-container'])[11]");


		// Info messages for all locators
		infoMap.put("startChat", "Start Chat");
		infoMap.put("acceptPopupbtn", "Accept popup");
		infoMap.put("baggageAllowanceBtnChatbot", "Baggage allowance in chatbot");
		infoMap.put("selectYesBtnChatbot", "select 'Yes' in chatbot");
		infoMap.put("whereToStartTripChatbot", "Text indicating where the trip starts in chatbot");
		infoMap.put("whereToStopTripChatbot", "Text indicating where the trip ends in chatbot");
		infoMap.put("textField", "Text field in chatbot");
		infoMap.put("sendTextBtn", "button to send text in chatbot");
		infoMap.put("firstClassOption", "option for First Class");
		infoMap.put("milesAndMoreOption", "Option for Miles and More Basic");
		infoMap.put("baggageRegulationChatbot", "button for baggage regulation in chatbot");
		infoMap.put("chatBotBaggageAllowenceTxt", "Text indicating baggage allowance rules in chatbot");
		infoMap.put("noneOfTheseBtn", "Button for 'None of these' option in chatbot");
		infoMap.put("chatBotFlightIrregularityBtn", "Button for flight irregularities in chatbot");
		infoMap.put("chatBotFlightIrrLink", "Link for flight irregularities information in chatbot");
		infoMap.put("delayedBaggaeBtnChatbot", "button for Delayed baggage in chatbot");
		infoMap.put("claimCompensationChatbot", "button forClaiming compensation in chatbot");
		infoMap.put("requestForCompensationBtn", "button to request compensation in chatbot");
		infoMap.put("damagedBaggageBtnChatbot", "button for Damaged baggage in chatbot");
		infoMap.put("chatbotBackToOverViewBtn", "back to overview");
		infoMap.put("chatbotEnterLastNameMsg", "Text indicating enter last name");
		infoMap.put("chatBotYourFlightOperatesAsTxt", "Flight operates text");
		infoMap.put("chatBotFlightBookingStatusFirstFlight", "Confirmed flight");
		infoMap.put("chatBotCheckRefundBtn", "check refund");
		infoMap.put("chatBotRefundText", "Your refund");
		infoMap.put("chatBotPerformRefundBtn", "perform refund ");
		infoMap.put("chatBotRefundConfirmation", "Refund confirmation");
		infoMap.put("chatBotFlightBookingStatus", "Confirmed Flight");
		infoMap.put("chatBotNxtSliderFlightBtn", "Next slide");
		infoMap.put("chatBotFlightStatusCancel", "Cancelled flight");
		infoMap.put("chatBotSearchAltFlight", "search alternative flight");
		infoMap.put("chatbotGlobalContinue", "global continue");
		infoMap.put("chatBotDetailsBtn", "view details");
		infoMap.put("chatBotContinueRebook", "continue rebooking");
		infoMap.put("acceptFlightBtnClick", "rebook now");
		infoMap.put("rebookingConfirmationText", "rebook confirmation text");
		infoMap.put("chatBotFlightBookingStatusAfterRebooking", "confirmed flight");
		infoMap.put("yourBookingConfirmationText", "your booking confirmation text");
		infoMap.put("verifySelectedFlight", "verify selected flight ");
		infoMap.put("isThereAnythingText", "is there anything text");
		infoMap.put("chatBotFlightDisruptedMsg", "Disrupted Message");
		infoMap.put("proposedFlightBtn", "Proposed flight button");
		infoMap.put("rebookNowBtn", "Rebook Now button");
		infoMap.put("chatbotRebookBtn", "Rebook button");
		infoMap.put("chatbotContinueWithAllBtn", "Continue with all button");
		infoMap.put("chatbotInBoundSelectionForRebookBtn", "Inbound flight selection button");
		infoMap.put("chatbotBothBoundsSelectionForRebookBtn", "Both Bounds selection for rebook button");
		infoMap.put("chatbotRebookPickDateBtnFirst", "Pick date button");
		infoMap.put("chatbotRebookPickDateBtnSecond", "Pick date button");
		infoMap.put("selectOutboundNewProposedFlight", "select Outbound New Proposed Flight button");
		infoMap.put("selectInboundNewProposedFlight", "select Inbound New Proposed Flight button");
		infoMap.put("summerizeRebooking", "Summerize Rebooking");
		infoMap.put("chatbotFlightRebookYesPleaseBtn", "Rebook Yes Please button");
		infoMap.put("chatbotClickContinueBtnFirst", "Click Continue button");
		infoMap.put("chatbotClickContinueBtnSecond", "Click Continue button");
		infoMap.put("chatbotPriceOverviewText", "Price Overview Text");
		infoMap.put("chatbotProceedWithPaymentBtn", "Proceed With Payment button");
		infoMap.put("chatbotGoToPaymentBtn", "Go To Payment Page button");
		infoMap.put("chatbotRebookPaymentPageText", "Rebook Payment Page Text");
		infoMap.put("paymentFopButton", "Select credit/debit card button");
		infoMap.put("paymentCardType_lst", "payment CardType Dropdown");
		infoMap.put("paymentMasterCard_lst", "Master card selection option");
		infoMap.put("paymentAmex_lst", "Amex card selection option");
		infoMap.put("paymentVisa_lst", "Visa card selection option");
		infoMap.put("paymentDiners_lst", "Diner's card selection option");
		infoMap.put("paymentJCB_lst", "JCB card selection option");
		infoMap.put("paymentUatp_lst", "UATP card selection option");
		infoMap.put("paymentChinaUP_lst", "ChinaUp card selection option");
		infoMap.put("paymentCardNo_input", "Enter Card Number");
		infoMap.put("paymentCardholderName_input", "Enter Card Holder Name");
		infoMap.put("paymentExpiryMonth_input", "Enter Card Expiry Month");
		infoMap.put("paymentExpiryYear_input", "Enter Card Expiry year");
		infoMap.put("paymentCVV_input", "Enter Card CVV");
		infoMap.put("paymentCountry", "Country Dropdown");
		infoMap.put("paymentStreetinput", "Enter Street Name");
		infoMap.put("paymentZipInput", "Enter Zip Code");
		infoMap.put("paymentCityInput", "Enter City Name");
		infoMap.put("paymentCountrylist", "Select Country Dropdown");
		infoMap.put("paymentAcceptCheckBox", "Select Checkbox");
		infoMap.put("paymentPayNowBtn", "Pay Now button");
		infoMap.put("chatbotRebookConfirmationText", "Rebook Confirmation Text");
		infoMap.put("chatbotRebookInboundDate", "Inbound date Text");
		infoMap.put("chatbotRebookOutboundDate", "Outbound date Text");
		infoMap.put("chatbotAdditionalServicesText", "Additional service Text");
		infoMap.put("chatbotContinueToRebookBtn", "Continue With rebooking button");
		infoMap.put("chatBotFlightBookingStatusThirdFlight", "Confirmed Flight after rebook");
		infoMap.put("chatBotFlightBookingStatusFourthFlight", "Confirmed Flight after rebook");
        infoMap.put("chatbotYesButton", "Yes Button");
        infoMap.put("chatbotCreateNewReportForDelayedBaggageButton", "Create New Report For Delayed Baggage Button");
        infoMap.put("chatbotCreateReportForDelayedBaggageURL", "Create Report For Delayed Baggage URL");
        infoMap.put("chatbotReportDelayedBaggageText", "Report Delayed Baggage Text");
        infoMap.put("chatbotNewQuestionButton", "New Question Button");
        infoMap.put("chatbotCreateNewReportForDamagedBaggageButton", "Create New Report For Damaged Baggage Button");
        infoMap.put("chatbotDamagedBaggageYesButton", "Damaged Baggage Yes Button");
        infoMap.put("chatbotCreateReportForDamagedBaggageURL", "Create Report For Damaged Baggage URL");
        infoMap.put("chatbotReportDamagedBaggageText", "Report Damaged Baggage Text");
		infoMap.put("regularBaggage","regular Baggage");
		infoMap.put("economyLightFare","economy Light Fare");
		infoMap.put("RequestCompensation","Request Compensation");
		infoMap.put("chatbotOnlineCheckInbtn","Online check in button");
		infoMap.put("chatbotNoButton","ChatBot No button");
		infoMap.put("chatbotIDontHaveFlightNumber","ChatBot I don't have flight number button");
		infoMap.put("chatbotTodayBtn","ChatBot today button");


	}

	public void clicks(String locator) throws InterruptedException {
		try {
			waitAndClick(locatorMap.get(locator));
		} catch (Exception e) {
			logAssert_Fail("Fails to select " + infoMap.get(locator) + ". Locator is: " + locatorMap.get(locator));
		}
	}

	public void selectTopic(String topic) throws InterruptedException {
		String xpath = "//span[contains(text(), '" + topic + "')]";
		try {
			waitAndClick(xpath);
		} catch (Exception e) {
			logAssert_Fail("Unable to select topic " + topic + ". Locator is: " + xpath);
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

	public void verifyElementVisibility(String locator) throws InterruptedException {
		try {
			boolean status = false;
			status = isTheElementVisible(locator, elementVisibilityMaxTimeout);
			Assert.assertTrue("Element is not Visible", status);
		} catch (Exception e) {
			logAssert_Fail(infoMap.get(locator) + " is not visible. " + locator);
		}
	}

	public void handlePopupWindowAndClose(String expected_url) throws InterruptedException {
		try {
			String popupUrl = null;
			Page popup = page.waitForPopup(() -> {
				System.out.println("Popup Window");
			});
			popupUrl = handleWindow(popup);
			if (popupUrl != null && popupUrl.contains(expected_url)) {
				System.out.println("Popup URL is as expected: " + popupUrl);
			} else {
				System.out.println("Popup URL is not as expected: " + popupUrl);
			}
			popup.close();
		} catch (Exception e) {
			logAssert_Fail("Fail to open new URL " + expected_url);
		}
	}

	public void chatbotPopUpPageWaitForElement(String locator, int timeoutSeconds) throws InterruptedException {
		boolean status = false;
		int timeoutMilliseconds = timeoutSeconds * 1000;
		try {
			PopupPage.waitForLoadState();
			PopupPage.waitForSelector(locatorMap.get(locator),
					new Page.WaitForSelectorOptions().setTimeout(timeoutMilliseconds));
			status = PopupPage.locator(locatorMap.get(locator)).isVisible();
			Assert.assertTrue("Element is not Visible", status);

		} catch (Exception e) {
			logAssert_Fail(infoMap.get(locator) + " is not visible. " + locatorMap.get(locator));
		}
	}

	public void chatbotPopupPageClick(String locator) throws InterruptedException {

		try {
			if (PopupPage.locator(locatorMap.get(locator)).isVisible() == true) {
				PopupPage.click(locatorMap.get(locator));
			}
		} catch (Exception e) {
			logAssert_Fail(infoMap.get(locator) + " is not visible. " + locatorMap.get(locator));
		}
	}

	public void chatbotPopUpPageEnterText(String locator, String data) throws InterruptedException {

		try {
			if (PopupPage.locator(locatorMap.get(locator)).isVisible() == true) {
				PopupPage.locator(locatorMap.get(locator)).fill(data);
			}
		} catch (Exception e) {
			logAssert_Fail(
					"Fails to enter text in " + infoMap.get(locator) + ". Locator is: " + locatorMap.get(locator));
		}
	}

	public void validateFlights() throws InterruptedException {
		try {
			verifyElementVisibility("chatBotYourFlightOperatesAsTxt");
			verifyElementVisibility("chatBotFlightBookingStatusFirstFlight");
			newOutboundAndInboundDateforVoluntaryRebook();
		} catch (Exception e) {
			logAssert_Fail("Fail to validate flights");
		}
	}

	public void newOutboundAndInboundDateforVoluntaryRebook() throws InterruptedException {
		try {
			outBoundDate = getText(locatorMap.get("chatbotRebookOutboundDate"));
			log.info("Original Outbound Date:" + outBoundDate);
			outBoundDate = DateManipulator.formatDate(outBoundDate, "dd MMM yyyy", "dd MMMM yyyy");
			newOutboundDate = DateManipulator.addDaystoDate(outBoundDate, "dd MMMM yyyy", 2);
			log.info("New Outbound Date:" + newOutboundDate);
			inboundDate = getText(locatorMap.get("chatbotRebookInboundDate"));
			log.info("Original inbound date: " + inboundDate);
			inboundDate = DateManipulator.formatDate(inboundDate, "dd MMM yyyy", "dd MMMM yyyy");
			newInboundDate = DateManipulator.addDaystoDate(inboundDate, "dd MMMM yyyy", 2);
			log.info("New inbound date: " + newInboundDate);

		} catch (Exception e) {
			logAssert_Fail("Fail to create New Date for Rebook");
		}
	}

	public void click_On_Rebook() throws InterruptedException {
		try {
			verifyElementVisibility("chatbotRebookBtn");
			clicks("chatbotRebookBtn");
		} catch (Exception e) {
			logAssert_Fail("Fail to click on rebook button");
		}
	}


	public void validateFlightsForCheckIn() throws InterruptedException {
		try {
			verifyElementVisibility("chatBotYourFlightOperatesAsTxt");
			verifyElementVisibility("chatBotFlightBookingStatusFirstFlight");
		} catch (Exception e) {
			logAssert_Fail("Fail to validate flights for check in");
		}
	}
	public void click_On_Online_CheckIn() throws InterruptedException {
		try {
			verifyElementVisibility("chatbotOnlineCheckInbtn");
			clicks("chatbotOnlineCheckInbtn");
		} catch (Exception e) {
			logAssert_Fail("Fail to click on online check in button");
		}
	}
	public void verifyFlightStatus() throws InterruptedException {
		try {
			String actualFlight = getText(locatorMap.get("chatbotFlightStatus"));
			String expectedFlight = "FRAJFK";
			Assert.assertEquals(actualFlight,expectedFlight);
		} catch (Exception e) {
			logAssert_Fail("Fail to verify flights");
		}
	}

	public void click_On_ContinueWithAll() throws InterruptedException {
		try {
			verifyElementVisibility("chatbotContinueWithAllBtn");
			clicks("chatbotContinueWithAllBtn");
		} catch (Exception e) {
			logAssert_Fail("Fail to click on rebook button");
		}
	}

	public void selectInboundFlightForVoluntaryRebook() throws InterruptedException {
		try {
			verifyElementVisibility("chatbotInBoundSelectionForRebookBtn");
			clicks("chatbotInBoundSelectionForRebookBtn");
		} catch (Exception e) {
			logAssert_Fail("Fail to select Inbound flight for voluntary rebook");
		}
	}

	public void selectBothBoundsForVoluntaryRebook() throws InterruptedException {
		try {
			verifyElementVisibility("chatbotBothBoundsSelectionForRebookBtn");
			clicks("chatbotBothBoundsSelectionForRebookBtn");
		} catch (Exception e) {
			logAssert_Fail("Fail to select Both bounds for voluntary rebook");
		}
	}

	public void selectInboundDateForVoluntaryRebook() throws InterruptedException {
		try {
			verifyElementVisibility("chatbotRebookPickDateBtn");
			verifyAndEnterText("textField", newInboundDate);
			clicks("sendTextBtn");
		} catch (Exception e) {
			logAssert_Fail("Fail to select Date for Inbound flight for voluntary rebook");
		}
	}
	public void selectOutboundDateForVoluntaryRebook() throws InterruptedException {
		try {
			verifyElementVisibility("chatbotRebookPickDateBtn");
			verifyAndEnterText("textField", newOutboundDate);
			clicks("sendTextBtn");
		} catch (Exception e) {
			logAssert_Fail("Fail to select Date for OutBound flight for voluntary rebook");
		}
	}

	public void selectBothBoundsDateForVoluntaryRebook() throws InterruptedException {
		try {
			verifyElementVisibility("chatbotRebookPickDateBtnFirst");
			verifyAndEnterText("textField", newOutboundDate);
			clicks("sendTextBtn");
			verifyElementVisibility("chatbotRebookPickDateBtnSecond");
			verifyAndEnterText("textField", newInboundDate);
			clicks("sendTextBtn");
		} catch (Exception e) {
			logAssert_Fail("Fail to select Date for Inbound flight for voluntary rebook");
		}
	}

	public void selectSortForCheapestForVoluntaryRebook() throws InterruptedException {
		try {
			verifyElementVisibility("chatbotFlightRebookYesPleaseBtn");
			clicks("chatbotFlightRebookYesPleaseBtn");
		} catch (Exception e) {
			logAssert_Fail("Fail to select Sort for cheapest flight");
		}
	}

	public void selectAlternateFlightForVoluntaryRebook() throws InterruptedException {
		try {
			verifyElementVisibility("selectOutboundNewProposedFlight");
			clicks("selectOutboundNewProposedFlight");
			verifyElementVisibility("chatbotClickContinueBtnFirst");
			clicks("chatbotClickContinueBtnFirst");
			verifyElementVisibility("chatbotFlightRebookYesPleaseBtn");
			clicks("chatbotFlightRebookYesPleaseBtn");
			verifyElementVisibility("selectInboundNewProposedFlight");
			clicks("selectInboundNewProposedFlight");
			verifyElementVisibility("summerizeRebooking");
			verifyElementVisibility("chatbotClickContinueBtnSecond");
			clicks("chatbotClickContinueBtnSecond");
		} catch (Exception e) {
			logAssert_Fail("Fail to select Alternate Flight");
		}
	}
	public void selectAlternateFlightForVoluntaryRebookOB() throws InterruptedException {
		try {
			verifyElementVisibility("selectOutboundNewProposedFlight");
			clicks("selectOutboundNewProposedFlight");
			verifyElementVisibility("chatbotClickContinueBtnFirst");
			clicks("chatbotClickContinueBtnFirst");
			verifyElementVisibility("summerizeRebooking");
			verifyElementVisibility("chatbotClickContinueBtnSecond");
			clicks("chatbotClickContinueBtnSecond");
		} catch (Exception e) {
			logAssert_Fail("Fail to select Alternate Flight");
		}
	}

	public void clickOnConitue() throws InterruptedException {
		try {
			verifyElementVisibility("chatbotClickContinueBtn");
			clicks("chatbotClickContinueBtn");
		} catch (Exception e) {
			logAssert_Fail("Fail to click on continue button");
		}
	}

	public void verifyAlternateFlight() throws InterruptedException {
		try {

			verifyElementVisibility("chatbotContinueToRebookBtn");
			clicks("chatbotContinueToRebookBtn");

			verifyElementVisibility("chatbotPriceOverviewText");
		} catch (Exception e) {
			logAssert_Fail("Fail to verify Alternate flight");
		}
	}

	public void clickOnGoToPaymentPage() throws InterruptedException {
		try {
			verifyElementVisibility("chatbotProceedWithPaymentBtn");
			clicks("chatbotProceedWithPaymentBtn");
			verifyElementVisibility("chatbotGoToPaymentBtn");
			clicks("chatbotGoToPaymentBtn");
		} catch (Exception e) {
			logAssert_Fail("Fail to Click on Go To Payment page button");
		}
	}

	public void completeForChatbotVolunatryRebook(String cardtype, String cardnumber, String cardholdername,
			String expiry, String cvv, String streetname, String city, String postcode, String country)
			throws InterruptedException, IOException {
		try {
			PopupPage = page.waitForPopup(() -> {
				log.info("Popup Window");
			});

			handleWindow(PopupPage);
			chatbotPopUpPageWaitForElement("chatbotRebookPaymentPageText", elementVisibilityMaxTimeout);
			log.info("Payment page is visible");
			popUpPageTakeScreenshot();
			selectCardType(cardtype);
			enterCreditCardDetails(cardnumber, cardholdername, expiry, cvv);
			enterBillingDetails(streetname, city, postcode, country);
			clickPayNow();
			PopupPage.close();

		} catch (Exception e) {
			e.printStackTrace();
			logAssert_Fail("Failed to pay with Credit Card");
		}
	}

	public void selectCardType(String cardtype) throws InterruptedException, IOException {
		log.info("Inside Select card");
		chatbotPopUpPageWaitForElement("paymentFopButton", elementVisibilityMaxTimeout);
		chatbotPopupPageClick("paymentFopButton");
		log.info("paymentFopButton button is clicked");
		chatbotPopUpPageWaitForElement("paymentCardType_lst", elementVisibilityMinTimeout);
		chatbotPopupPageClick("paymentCardType_lst");
		log.info("paymentCardType_lst button is clicked");

		switch (cardtype) {
		case "MasterCard": {
			chatbotPopUpPageWaitForElement("paymentMasterCard_lst", elementVisibilityMinTimeout);
			chatbotPopupPageClick("paymentMasterCard_lst");
			break;
		}
		case "Amex": {
			chatbotPopUpPageWaitForElement("paymentAmex_lst", elementVisibilityMinTimeout);
			chatbotPopupPageClick("paymentAmex_lst");
			break;
		}
		case "Visa": {
			chatbotPopUpPageWaitForElement("paymentVisa_lst", elementVisibilityMinTimeout);
			chatbotPopupPageClick("paymentVisa_lst");
			log.info("Visa selected");
			break;
		}
		case "Diners": {
			chatbotPopUpPageWaitForElement("paymentDiners_lst", elementVisibilityMinTimeout);
			chatbotPopupPageClick("paymentDiners_lst");
			break;
		}
		case "JCB": {
			chatbotPopUpPageWaitForElement("paymentJCB_lst", elementVisibilityMinTimeout);
			chatbotPopupPageClick("paymentJCB_lst");
			break;
		}
		case "Uatp": {
			chatbotPopUpPageWaitForElement("paymentUatp_lst", elementVisibilityMinTimeout);
			chatbotPopupPageClick("paymentUatp_lst");
			break;
		}
		case "ChinaUnionPay": {
			chatbotPopUpPageWaitForElement("paymentChinaUP_lst", elementVisibilityMinTimeout);
			chatbotPopupPageClick("paymentChinaUP_lst");
			break;
		}
		default: {
			logAssert_Fail("Credit Card choice not found !");
		}
		}
	}

	public void enterCreditCardDetails(String cardnumber, String cardholdername, String expiry, String cvv)
			throws InterruptedException, IOException {
		try {
			String cardnumber_value = readDecryptedData(cardnumber);
			String cvv_value = readDecryptedData(cvv);
			chatbotPopUpPageWaitForElement("paymentCardNo_input", elementVisibilityMinTimeout);
			chatbotPopUpPageEnterText("paymentCardNo_input", cardnumber_value);
			log.info("Card no entered");
			chatbotPopUpPageWaitForElement("paymentCardholderName_input", elementVisibilityMinTimeout);
			chatbotPopUpPageEnterText("paymentCardholderName_input", cardholdername);
			log.info("Card holder name entered");
			String month = expiry.split("/")[0];
			String year = expiry.split("/")[1];
			chatbotPopUpPageWaitForElement("paymentExpiryMonth_input", elementVisibilityMinTimeout);
			chatbotPopUpPageEnterText("paymentExpiryMonth_input", month);
			chatbotPopUpPageWaitForElement("paymentExpiryYear_input", elementVisibilityMinTimeout);
			chatbotPopUpPageEnterText("paymentExpiryYear_input", year);
			chatbotPopUpPageWaitForElement("paymentCVV_input", elementVisibilityMinTimeout);
			chatbotPopUpPageEnterText("paymentCVV_input", cvv_value);
			log.info("Card details have been filled");
		} catch (Exception e) {
			logAssert_Fail("Failed to add credit card details in payment page");
		}
	}

	public void enterBillingDetails(String streetname, String city, String postcode, String country)
			throws InterruptedException, IOException {
		try {
			chatbotPopUpPageWaitForElement("paymentStreetinput", elementVisibilityMinTimeout);
			chatbotPopUpPageEnterText("paymentStreetinput", streetname);
			chatbotPopUpPageWaitForElement("paymentZipInput", elementVisibilityMinTimeout);
			chatbotPopUpPageEnterText("paymentZipInput", postcode);
			chatbotPopUpPageWaitForElement("paymentCityInput", elementVisibilityMinTimeout);
			chatbotPopUpPageEnterText("paymentCityInput", city);
			chatbotPopUpPageWaitForElement("paymentCountrylist", elementVisibilityMinTimeout);
			chatbotPopupPageClick("paymentCountrylist");
			chatbotPopUpPageWaitForElement("paymentCountry", elementVisibilityMinTimeout);
			chatbotPopupPageClick("paymentCountry");
			log.info("Address details filled");
			chatbotPopUpPageWaitForElement("paymentAcceptCheckBox", elementVisibilityMinTimeout);
			chatbotPopupPageClick("paymentAcceptCheckBox");
		} catch (Exception e) {
			logAssert_Fail("Failed to add billing details in payment page");
		}
	}

	public void clickPayNow() throws InterruptedException, IOException {
		try {
			chatbotPopUpPageWaitForElement("paymentPayNowBtn", elementVisibilityMinTimeout);
			chatbotPopupPageClick("paymentPayNowBtn");
			chatbotPopUpPageWaitForElement("chatbotRebookConfirmationText", elementVisibilityMaxTimeout);
		} catch (Exception e) {
			logAssert_Fail("Failed to click on pay now button in Payment page");
		}
	}

	public void verifyRebookConfirmed() throws InterruptedException, IOException {
		try {
			verifyElementVisibility("rebookingConfirmationText");
			verifyElementVisibility("chatBotFlightBookingStatusThirdFlight");
			verifyElementVisibility("chatBotFlightBookingStatusFourthFlight");
		} catch (Exception e) {
			logAssert_Fail("Failed to verify Rebook confirmed");
		}
	}
    
    public void clickOnCreateNewReportForDelayedBaggage() throws InterruptedException, IOException {
		try {
    		verifyElementVisibility("chatbotCreateNewReportForDelayedBaggageButton");
    	    clicks("chatbotCreateNewReportForDelayedBaggageButton");
    	    log.info("Clicked On Create New Report");
		} catch (Exception e) {
			logAssert_Fail("Failed to click on create new report For Delayed Baggage");
		}
	}
    
    public void clickonReportDelayedBaggageURL() throws InterruptedException, IOException {
		try {
    	    verifyElementVisibility("chatbotCreateReportForDelayedBaggageURL");
    	    clicks("chatbotCreateReportForDelayedBaggageURL");
    	    log.info("Clicked On Delayed Baggage URL");

		} catch (Exception e) {
			logAssert_Fail("Failed to click on Report Delayed Baggage URL");
		}
	}
    
    public void verifyLinkNavigatedToDelayedBaggagePage() throws InterruptedException, IOException {
		try {
			PopupPage = page.waitForPopup(() -> {
				log.info("Popup Window");
			});
			handleWindow(PopupPage);
			chatbotPopUpPageWaitForElement("chatbotReportDelayedBaggageText", 30);
    	    log.info("Delayed Baggage Page Verified");

			PopupPage.close();
		} catch (Exception e) {
			logAssert_Fail("Failed to Verify Delayed Baggage Page");
		}
	}

	public void verifyLinkNavigatedToCheckInPage() throws InterruptedException, IOException {
		try {
			PopupPage = page.waitForPopup(() -> {
				log.info("Popup Window");
			});
			handleWindow(PopupPage);
			log.info("CheckIn Page Verified");

			PopupPage.close();
		} catch (Exception e) {
			logAssert_Fail("Failed to Verify CheckIn Page");
		}
	}
    
    public void clickOnYesButton() throws InterruptedException, IOException {
		try {
			verifyElementVisibility("chatbotYesButton");
    	    clicks("chatbotYesButton");
    	    log.info("Clicked On yes Button");

		} catch (Exception e) {
			logAssert_Fail("Failed to Click on Yes Button");
		}
	}
    
    public void clickOnNewQuestionButton() throws InterruptedException, IOException {
		try {
			verifyElementVisibility("chatbotNewQuestionButton");
    	    clicks("chatbotNewQuestionButton");
    	    log.info("Clicked On New Question Button");

		} catch (Exception e) {
			logAssert_Fail("Failed to Click on New Question Button");
		}
	}
    
    public void clickOnCreateNewReportForDamagedBaggage() throws InterruptedException, IOException {
		try {
    		verifyElementVisibility("chatbotCreateNewReportForDamagedBaggageButton");
    	    clicks("chatbotCreateNewReportForDamagedBaggageButton");
    	    log.info("Clicked On Create New Report For Damaged Baggage");

		} catch (Exception e) {
			logAssert_Fail("Failed to click on create new report for damaged Baggage");
		}
	}
    
    public void clickonReportDamagedBaggageURL() throws InterruptedException, IOException {
		try {
    	    verifyElementVisibility("chatbotDamagedBaggageYesButton");
    	    clicks("chatbotDamagedBaggageYesButton");
    	    log.info("Clicked On Yes Button");
    	    verifyElementVisibility("chatbotCreateReportForDamagedBaggageURL");
    	    clicks("chatbotCreateReportForDamagedBaggageURL");
    	    log.info("Clicked On Report damaged Baggage URL");

		} catch (Exception e) {
			logAssert_Fail("Failed to click on Report Damaged Baggage URL");
		}
	}
    
    public void verifyLinkNavigatedToDamagedBaggagePage() throws InterruptedException, IOException {
  		try {
  			PopupPage = page.waitForPopup(() -> {
				log.info("Popup Window");
			});
			handleWindow(PopupPage);
  			chatbotPopUpPageWaitForElement("chatbotReportDamagedBaggageText", elementVisibilityMaxTimeout);
    	    log.info("Report Damaged Baggage Page Verified");

  		} catch (Exception e) {
  			logAssert_Fail("Failed to Verify Damaged Baggage Page");
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

	// Common methods that can be used for specific page
	// ...
}
