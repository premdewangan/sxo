package com.e2e.stepdef;

import java.io.IOException;

import com.e2e.config.Configuration;
import com.e2e.pages.BasePage;
import com.e2e.pages.ChatbotPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.e2e.config.Configuration.baggageRegulationUrl;
//import static com.e2e.config.Configuration.lufthansaUrl;

public class CustomerServiceSteps extends BasePage {

	ChatbotPage chatbot = new ChatbotPage();

	@Given("User launches Chatbot url {string}")
	public void user_launches_Chatbot_url(String tenant) throws IOException, InterruptedException {
		page = browser.newPage();
		String url = null;
		String env = System.getProperty("envName", Configuration.ENVIRONMENT);
		// Save information of current tenant under execution
		scenarioContext.setContext("Tenant_Name", tenant);

		// Save information of Dynamic Test Data source file to be used
		scenarioContext.setContext("Static_TestData_FileName", "chatbot");

		// Save information of Dynamic Test Data source file to be used
		secondaryScenarioContext.setContext("Dynamic_TestData_FileName", "chatbot");

		if ("LH".equals(tenant) && "INT".equalsIgnoreCase(env)) {
			url = Configuration.LH_CHATBOT_INT;
		} else if ("LX".equals(tenant) && "INT".equalsIgnoreCase(env)) {
			url = Configuration.LX_CHATBOT_INT;
		} else if ("OS".equals(tenant) && "INT".equalsIgnoreCase(env)) {
			url = Configuration.OS_CHATBOT_INT;
		}
		System.out.println("url is: " + url);
		if (url != null) {
			try {
				// Navigate to the determined URL
				page.navigate(url);
				// Handle pop-ups that appear
				chatbot.clicks("acceptPopupbtn");
				takeExtraScreenshot();
			} catch (Exception e) {
				// Log an error if navigation fails
				System.err.println("Error navigating to the Chatbot URL: " + e.getMessage());
			}
		} else {
			// Log a message if the URL is null
			System.err.println("URL is null. Cannot navigate to a null URL.");
		}
	}

	@When("User clicks on start chat and select the {string}")
	public void user_Click_Start_Chat_and_Select_Topic(String tenant) throws IOException, InterruptedException {
		chatbot.clicks("startChat");
		log.info("Click on StartChat button");
		chatbot.selectTopic(tenant);
	}

	@When("User select the {string}")
	public void user_Select_Topic(String tenant) throws IOException, InterruptedException {
		chatbot.verifyElementVisibility("noneOfTheseBtn");
		log.info("User select the topic");
		chatbot.selectTopic(tenant);
	}

	@When("User clicks on start chat and enter flight status")
	public void user_enter_flight_status() throws InterruptedException {
		chatbot.clicks("startChat");
		chatbot.verifyElementVisibility("noneOfTheseBtn");
		chatbot.verifyAndEnterText("textField", "flight status");
		chatbot.clicks("sendTextBtn");
		chatbot.verifyElementVisibility("chatbotNoButton");
		chatbot.clicks("chatbotNoButton");
		takeScreenshot();
	}

	@And("User enter the {string}")
	public void user_enter_flight_Number(String flightNo) throws InterruptedException {
		chatbot.verifyElementVisibility("chatbotIDontHaveFlightNumber");
		chatbot.verifyAndEnterText("textField", flightNo);
		chatbot.clicks("sendTextBtn");
		chatbot.verifyElementVisibility("chatbotTodayBtn");
		chatbot.clicks("chatbotTodayBtn");
		takeScreenshot();
	}

	@And("User verify the flight status and click on Back to overview")
	public void user_verify_the_flight_status_and_clickOn_backToOverview() throws InterruptedException {
		chatbot.verifyFlightStatus();
		chatbot.clicks("chatbotBackToOverViewBtn");
	}

	@When("User selects the Baggage allowance button")
	public void user_Select_Baggage_Allowance() throws IOException, InterruptedException {
		chatbot.clicks("baggageAllowanceBtnChatbot");
		log.info("Click on baggage Allowance button");
		chatbot.clicks("regularBaggage");
		log.info("Click on regular Baggage button");
	}

	@And("User enters the startTrip {string} and EndTrip {string}")
	public void user_Enter_startTrip_and_EndTrip(String origin, String destination) throws InterruptedException {
		chatbot.verifyElementVisibility("whereToStartTripChatbot");
		log.info("Start Trip Chatbot button is visible");
		chatbot.verifyAndEnterText("textField", origin);
		log.info("Enter Origin button is visible and Entered Origin");
		chatbot.clicks("sendTextBtn");
		log.info("Click on send Text button");
		chatbot.verifyElementVisibility("whereToStopTripChatbot");
		chatbot.verifyAndEnterText("textField", destination);
		log.info("Enter destination button is visible and Entered destination");
		chatbot.clicks("sendTextBtn");
		log.info("Click on send Text button");
	}

	@And("User selects the First class button and Miles and More Basic button")
	public void user_Selects_FirstClass_and_MilesandMore_Button() throws InterruptedException {
		chatbot.clicks("firstClassOption");
		log.info("Click on first Class Option button");
		chatbot.clicks("milesAndMoreOption");
		log.info("Click on miles And More Option button");
	}

	@And("User selects the Economy Light fare button and Miles and More Basic button")
	public void user_Selects_Economy_Light_fare_button_and_MilesandMore_Button() throws InterruptedException {
		chatbot.clicks("economyLightFare");
		log.info("Click on economy Light Fare button");
		chatbot.clicks("milesAndMoreOption");
		log.info("Click on miles And More Option button");
	}

	@Then("User verifies the Baggage allowance information")
	public void verify_Baggage_Allowance_Info() throws InterruptedException {
		chatbot.clicks("baggageRegulationChatbot");
		log.info("Click on baggage Regulation Chatbot button");
		chatbot.handlePopupWindowAndClose(baggageRegulationUrl);
		log.info("handled Popup Window And Close");

	}

	@When("User clicks on start chat and enter claim")
	public void clicks_Start_Chat_and_Enter_Claim() throws InterruptedException {
		chatbot.clicks("startChat");
		log.info("Clicked on Start Chat Button");
		chatbot.verifyElementVisibility("noneOfTheseBtn");
		log.info("Clicked on none Of These Button");
		chatbot.verifyAndEnterText("textField", "Claim");
		chatbot.clicks("sendTextBtn");
		log.info("Clicked on send Text Button");
	}

	@And("User selects Compensation for flight delay or cancellation button")
	public void select_CompensationForFlightDelayorCancellation_Btn() throws InterruptedException {
		chatbot.clicks("chatBotFlightIrregularityBtn");
		log.info("Clicked on flight delay or cancellation button");
	}

	@And("User validates claim compensation link and clicks on the link displayed")
	public void validate_ClaimCompensation_Link_Click_Link() throws InterruptedException {
		chatbot.verifyElementVisibility("RequestCompensation");
		chatbot.clicks("RequestCompensation");
		log.info("Clicked on Request Compensation button");

	}
//
//	@Then("User verifies the link should navigate to lufthansa.com page")
//	public void verifies_Link_navigated_Lufthasa_Page() throws InterruptedException {
//		chatbot.handlePopupWindowAndClose(lufthansaUrl);
//		log.info("link should navigate to lufthansa.com page");
//	}

	@And("User selects delayed baggage button")
	public void select_Delayed_Baggage_Btn() throws InterruptedException {
		chatbot.clicks("delayedBaggaeBtnChatbot");
	}

	@And("User selects claim compensation button")
	public void select_Claim_Compensation_Btn() throws InterruptedException {
		chatbot.clicks("claimCompensationChatbot");
	}

	@And("Validate request compensation link and clicks on the link displayed")
	public void validate_Request_Compensation_Link_and_Clicks_Link_Displayed() throws InterruptedException {
		chatbot.clicks("requestForCompensationBtn");
	}

	@When("User enters {string} and select the option")
	public void enter_BaggageType_and_Select_Option(String baggageType) throws InterruptedException {
		chatbot.verifyAndEnterText("textField", baggageType);
		chatbot.clicks("sendTextBtn");
		chatbot.clicks("damagedBaggageBtnChatbot");
	}

	@When("User enters {string} and {string} in chat window")
	public void user_enters_and_in_chat_window(String bookingCode, String lastname) throws InterruptedException {

		String bookingCode_value = scenarioContext.getContext("PNR_Retrieved");
		String lastname_value = scenarioContext.getContext("LastName_Retrieved");

		chatbot.verifyElementVisibility("chatbotBackToOverViewBtn");
		chatbot.verifyAndEnterText("textField", bookingCode_value);
		chatbot.clicks("sendTextBtn");
		chatbot.verifyElementVisibility("chatbotEnterLastNameMsg");
		chatbot.verifyAndEnterText("textField", lastname_value);
		chatbot.clicks("sendTextBtn");
		takeScreenshot();

	}

	@When("User enter {string} and {string} in chat window")
	public void user_enter_and_in_chat_window(String bookingCode, String lastname) throws InterruptedException {
		String bookingCode_value = readDynamicExternalData(bookingCode);
		String lastname_value = readDynamicExternalData(lastname);
		chatbot.verifyAndEnterText("textField", bookingCode_value);
		chatbot.clicks("sendTextBtn");
		chatbot.verifyElementVisibility("chatbotEnterLastNameMsg");
		chatbot.verifyAndEnterText("textField", lastname_value);
		chatbot.clicks("sendTextBtn");
		takeScreenshot();
	}

	@When("User validates the outbound and inbound confirmed flight details and click on check refund button")
	public void user_validates_the_outbound_and_inbound_confirmed_flight_details_and_click_on_check_refund_button()
			throws InterruptedException {
		String chatBotCheckRefundBtn = "chatBotCheckRefundBtn";
		chatbot.verifyElementVisibility("chatBotYourFlightOperatesAsTxt");
		chatbot.verifyElementVisibility("chatBotFlightBookingStatusFirstFlight");
		chatbot.verifyElementVisibility(chatBotCheckRefundBtn);
		chatbot.clicks(chatBotCheckRefundBtn);
	}

	@When("User verifies the refund amount and clicks on perform refund")
	public void user_verifies_the_refund_amount_and_clicks_on_perform_refund() throws InterruptedException {
		String chatBotPerformRefundBtn = "chatBotPerformRefundBtn";
		chatbot.verifyElementVisibility("chatBotRefundText");
		chatbot.verifyElementVisibility(chatBotPerformRefundBtn);
		chatbot.clicks(chatBotPerformRefundBtn);
	}

	@Then("User verifies the refund successful message")
	public void user_verifies_the_refund_successful_message() throws InterruptedException {
		chatbot.verifyElementVisibility("chatBotRefundConfirmation");
	}

	@When("User validate the outbound and inbound flight details and verifies whether the Inbound is cancelled")
	public void user_validates_outbound_and_inbound_flight_details_and_verifies_whether_inbound_is_cancelled()
			throws InterruptedException {
		String chatBotFlightStatusCancel = "chatBotFlightStatusCancel";
		chatbot.verifyElementVisibility(chatBotFlightStatusCancel);
		chatbot.clicks(chatBotFlightStatusCancel);
	}

	@When("User selects Search alternative flight and click on continue.")
	public void user_selects_search_alternative_flight_and_click_on_continue() throws InterruptedException {
		String chatBotSearchAltFlight = "chatBotSearchAltFlight";
		String chatbotGlobalContinue = "chatbotGlobalContinue";
		chatbot.verifyElementVisibility(chatBotSearchAltFlight);
		chatbot.clicks(chatBotSearchAltFlight);
		chatbot.verifyElementVisibility(chatbotGlobalContinue);
		chatbot.clicks(chatbotGlobalContinue);
	}

	@When("User clicks on details button on the alternatives card shown")
	public void clicks_details_button_alternatives_shown() throws InterruptedException {
		String chatBotDetailsBtn = "chatBotDetailsBtn";
		String chatBotContinueRebook = "chatBotContinueRebook";
		chatbot.verifyElementVisibility(chatBotDetailsBtn);
		chatbot.clicks(chatBotDetailsBtn);
		chatbot.verifyElementVisibility(chatBotContinueRebook);
		chatbot.clicks(chatBotContinueRebook);
	}

	@When("User verifies the details for the alternative you selected and click on continue rebooking")
	public void verfies_selected_alternate_Flight() throws InterruptedException {
		chatbot.verifyElementVisibility("verifySelectedFlight");
	}

	@When("User clicks on rebook now button")
	public void rebookProposedFlight() throws InterruptedException {
		String acceptFlightBtnClick = "acceptFlightBtnClick";
		chatbot.verifyElementVisibility(acceptFlightBtnClick);
		chatbot.clicks(acceptFlightBtnClick);
	}

	@Then("User verify the rebook confirmation message")
	public void user_verify_the_rebook_confirmation_message() throws InterruptedException {
		chatbot.verifyElementVisibility("rebookingConfirmationText");
	}

	@When("User validate the outbound and inbound cancelled flight details and clicks on check refund button")
	public void validateCancelledFlight() throws InterruptedException {
		String chatBotCheckRefundBtn = "chatBotCheckRefundBtn";
		chatbot.verifyElementVisibility("chatBotFlightDisruptedMsg");
		chatbot.verifyElementVisibility(chatBotCheckRefundBtn);
		chatbot.clicks(chatBotCheckRefundBtn);
	}

	@When("User selects continue with Proposal button")
	public void user_selects_continue_with_proposal_button() throws InterruptedException {
		String proposedFlightBtn = "proposedFlightBtn";
		chatbot.verifyElementVisibility(proposedFlightBtn);
		chatbot.clicks(proposedFlightBtn);
	}

	@When("User validate the outbound and inbound confirmed flight details and click on rebook button")
	public void validateFlightsandClickonRebook() throws IOException, InterruptedException {
		chatbot.validateFlights();
		log.info("Flight is validate");
		chatbot.click_On_Rebook();
		log.info("Click on Rebook button");
		chatbot.click_On_ContinueWithAll();
		log.info("Click on continue button");
	}

	@When("User validate the outbound and inbound confirmed flight details and click on Online Check-in button.")
	public void validateFlightsandClickonOnlineCheckInButton() throws IOException, InterruptedException {
		chatbot.validateFlightsForCheckIn();
		log.info("Flight is validate");
		chatbot.click_On_Online_CheckIn();
		log.info("Click on Online CheckIn button");
	}

	@When("User verify the Check-in confirmation message")
	public void verify_checkIn_confirmation() throws InterruptedException, IOException {
		chatbot.verifyLinkNavigatedToCheckInPage();
	}

	@When("User selects both bounds for rebook")
	public void selectBothBoundsForVoluntaryRebook() throws InterruptedException {
		chatbot.selectBothBoundsForVoluntaryRebook();
	}

	@When("User click on the pick date and select the date for Both Bounds")
	public void selectDateForBothBoundsRebook() throws InterruptedException {
		chatbot.selectBothBoundsDateForVoluntaryRebook();
	}

	@When("User select sort for cheapest")
	public void selectshortforcheapestflight() throws InterruptedException {
		chatbot.selectSortForCheapestForVoluntaryRebook();
	}

	@When("User select alternative flight for Inbound")
	public void selectAlternativeFlights() throws InterruptedException {
		chatbot.selectAlternateFlightForVoluntaryRebook();
	}

	@When("User click on continue button and verifies alternate flight")
	public void verifyAlternateFlightAndClickOnContinue() throws InterruptedException {
		chatbot.verifyAlternateFlight();
	}

	@When("User click on Go To Payment Page")
	public void userClicksOnGoToPaymentPage() throws InterruptedException {
		chatbot.clickOnGoToPaymentPage();
		log.info("Click on go to payment page");
	}

	@Then("User Completes The Payment for Chatbot Volunatry Rebook {string},{string},{string},{string},{string},{string},{string},{string} and {string}")
	public void userCompletesPaymentForChatbotVolunatryRebook(String cardtype, String cardnumber, String cardholdername,
			String expiry, String cvv, String streetname, String city, String postcode, String country)
			throws InterruptedException, IOException {
		chatbot.completeForChatbotVolunatryRebook(cardtype, cardnumber, cardholdername, expiry, cvv, streetname, city,
				postcode, country);
		log.info("Complete the payment for voluntary Rebook with Credit card");
	}

	@Then("User verify the rebook confirmation message for voluntary rebook")
	public void userVerfiesRebookConfirmation() throws InterruptedException, IOException {
		chatbot.verifyRebookConfirmed();
		log.info("Verified Rebook confirmation message for voluntary rebook");
	}

	@And("User selects create new report For Delayed Baggage")
	public void userSelectCreateNewReportForDelayedBaggage() throws InterruptedException, IOException {
		chatbot.clickOnCreateNewReportForDelayedBaggage();
	}

	@And("Validate report delayed baggage link and clicks on the link")
	public void userValidateAndOpenReportDelayedBaggageURL() throws InterruptedException, IOException {
		chatbot.clickonReportDelayedBaggageURL();
	}

	@Then("User verifies the link should navigate to Report Delayed Baggage Page")
	public void userVerifiesLinkIsNavigatedToReportDelayedBaggagePage() throws InterruptedException, IOException {
		chatbot.verifyLinkNavigatedToDelayedBaggagePage();
	}

	@Then("User click on Yes Button")
	public void userClickonYesButton() throws InterruptedException, IOException {
		chatbot.clickOnYesButton();
	}

	@Then("User Click on New Question")
	public void userClickonNewQuestion() throws InterruptedException, IOException {
		chatbot.clickOnNewQuestionButton();
	}

	@And("User selects create new report For Damaged Baggage")
	public void userSelectCreateNewReportForDamagedBaggage() throws InterruptedException, IOException {
		chatbot.clickOnCreateNewReportForDamagedBaggage();
	}

	@And("Validate report damaged baggage link and clicks on the link")
	public void userValidateAndOpenReportDamagedBaggageURL() throws InterruptedException, IOException {
		chatbot.clickonReportDamagedBaggageURL();
	}

	@Then("User verifies the link should navigate to Report Damaged Baggage Page")
	public void userVerifiesLinkIsNavigatedToReportDamagedBaggagePage() throws InterruptedException, IOException {
		chatbot.verifyLinkNavigatedToDamagedBaggagePage();
	}

	@When("User click on the pick date and select the date for Outbound flight")
	public void UserClickOnThePickDateAndSelectTheDateForOutboundFlight() throws InterruptedException {
		chatbot.selectOutboundDateForVoluntaryRebook();
		log.info("Select OutBound Date");
	}

	@When("User select alternative flight for outbound and then click on continue.")
	public void userSelectAlternativeFlightForOutboundAndThenClickOnContinue() throws InterruptedException {
		chatbot.selectAlternateFlightForVoluntaryRebookOB();
		log.info("User Select Alternative flight and then click continue");

	}

}
