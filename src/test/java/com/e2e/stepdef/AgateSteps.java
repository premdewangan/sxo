package com.e2e.stepdef;

import com.e2e.pages.BasePage;
import com.e2e.utils.DataEncryption;
import com.e2e.utils.EncryptionUtil;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import com.e2e.config.Configuration;

import java.io.IOException;

public class AgateSteps extends BasePage {
//    static CommonActions commonActions;
    int wait15Sec = 15000;
    int wait60Sec = 60000;
    Browser browser;
    BrowserContext browsercontext;
    Page newpage;
//    public AgateSteps(commonactions) {
//        this.commonActions = commonactions;
//    }
//    private final static Logger LOGGER = Logger.getLogger(AgateSteps.class);
//
//    String SecretKey key =
//    // Retrieve your key here
//
//
//    String  decryptedCC = EncryptionUtil.decrypt(encryptedUsername, key);
//    String decryptedCVV = EncryptionUtil.decrypt(encryptedUsername, key);
//    // Description:Agate Login
    @And("^Login to Agate with airline code(.*?)$")
    public void LoginAgate(String airlineCode)
            throws InterruptedException, IOException {
        try {
            String DecryptedUserName = DataEncryption.decodeBase64(Configuration.agateUsername);
            String DecryptedPassword = DataEncryption.decodeBase64(Configuration.agatePassword);
            enterText("username", DecryptedUserName);
            enterText("organization", "LH");
            clickOnTheElement("next_btn");
            enterText("amadeus_Pwd", DecryptedPassword);
            clickOnTheElement("next_btn_login");
            String tenant="//td[contains(@class,'airlineCode')][text()='"+airlineCode+"']";
//            clickElementwithlocator(tenant, airlineCode);
            page.setDefaultTimeout(45000);
        } catch (Exception e) {
            logAssert_Fail("Unable to login in Agate");
        }

    }

}
//    @When("^User select the (.*?)$ on amadeus Home page")
//    public void user_select_the_on_amadeus_Home_page(String string) {
//
//
//    }


//    // Description:Retrieve PNR using Deeplink url in Agate and navigate to cash upgrade page
//    @And("^Retrieve PNR using Deeplink url in Agate and navigate to MMB page$")
//    public void retrievePNRandNavigatetoMMBPage()
//            throws InterruptedException, IOException {
//        try {
//            commonActions.click("manageBookingRefx");
//            commonActions.enterText("enterUrl", Configurations.retrievePNRinAgateforCashUpgrade);
//            commonActions.click("bodyTab");
//            commonActions.enterText("bodyParams","{\n"
//                    + "  \"recLoc\": \""+commonActions.PNR+"\",\r\n"
//                    + "  \"lastName\": \""+passengerInformation.lastName+"\",\r\n"
//                    + "  \"allowDynamicConfig\": \"true\"\n"
//                    + "}");
//            commonActions.clickAndSwitchTab("Submitofbody");
//            Thread.sleep(wait15Sec);
//            commonActions.handle_Privacy_Settings_Page();
//            Thread.sleep(wait15Sec);
//            commonActions.lateLogin();
//        } catch (Exception e) {
//            System.out.println(e);
//            commonActions.logAssert_Fail("Unable retreive PNR and navigate to cash upgrade page: " + e);
//        }
//    }
//
//    @And("^Retrieve PNR using Deeplink url in Agate and navigate to cash upgrade page$")
//    public void retrievePNRandNavigatetoCashUpgradePage()
//            throws InterruptedException, IOException {
//        try {
//            commonActions.click("manageBookingRefx");
//            commonActions.enterText("enterUrl", Configurations.retrievePNRinAgateforCashUpgrade);
//            commonActions.click("bodyTab");
//            commonActions.enterText("bodyParams","{\n"
//                    + "  \"recLoc\": \""+commonActions.PNR+"\",\r\n"
//                    + "  \"lastName\": \""+passengerInformation.lastName+"\",\r\n"
//                    + "  \"allowDynamicConfig\": \"true\"\n"
//                    + "}");
//            commonActions.clickAndSwitchTab("Submitofbody");
//            Thread.sleep(wait15Sec);
//            commonActions.handle_Privacy_Settings_Page();
//            Thread.sleep(wait15Sec);
//            commonActions.lateLogin();
//        } catch (Exception e) {
//            System.out.println(e);
//            commonActions.logAssert_Fail("Unable retreive PNR and navigate to cash upgrade page: " + e);
//        }
//    }
//
//
//    // Description:Agate Login
//    @And("^search Flights in Agate with (.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(.*?) as variables$")
//    public void searchFlightsinAgate(String triptype, String source, String Destination, String passengers, String outbounddate, String inbounddate,String cabin, String platform)
//            throws InterruptedException, IOException {
//        try {
//            commonActions.click("Booking_availability");
//            commonActions.click(triptype);
//            commonActions.selectValFromDropDown(cabin,"agate_Cabin");
//            commonActions.enterTextSelectOption("origin_Amadeus", source);
//            commonActions.enterTextSelectOption("destination_Amadeus", Destination);
//            commonActions.click("Departure_date_Agate");
//            commonActions.enterText("Departure_date_Agate", outbounddate);
//            if(!triptype.equals("agate_oneWay"))
//            {
//                commonActions.click("Return_date_Agate");
//                commonActions.enterText("Return_date_Agate", inbounddate);
//            }
//            //classandTravellerSelection(passengers);
//            if(!(platform.equals("UAT (LH Booking)") || platform.equals("UAT (LX Booking)") || platform.equals("UAT (OS Booking)") || platform.equals("UAT (SN Booking)")))
//            {
//                commonActions.clickAndThenSelectValFromDropDownList("Agate_platform", platform);
//                String platformValue = commonActions.getTextFromElement("Agate_platform");
//                System.out.println(platformValue);
//                Assert.assertEquals(platformValue, platform);
//            }
//            else
//            {
//                String platformValue = commonActions.getTextFromElement("Agate_platform");
//                System.out.println(platformValue);
//                Assert.assertEquals(platformValue, platform);
//            }
//            String title = this.commonActions.page.title();
//            System.out.println(title);
//            commonActions.clickAndSwitchTab("agateSubmit");
//            String newtitle = this.commonActions.page.title();
//            System.out.println(newtitle);
//            this.commonActions.page.waitForSelector(commonActions.getLocatorStr("cookieAEM"), new Page.WaitForSelectorOptions().setStrict(true).setTimeout(30000));
//            commonActions.handle_Privacy_Settings_Page();
//        } catch (Exception e) {
//            System.out.println(e);
//            commonActions.logAssert_Fail("Unable to login in Agate due to the following reason: " + e);
//        }
//    }
//
//    // Description:Passengers selection in Agate
//
//    public static void classandTravellerSelection(String passenger) throws InterruptedException, IOException {
//        try {
//            int adult = Character.getNumericValue(passenger.split("&")[0].charAt(0));
//            int child = Character.getNumericValue(passenger.split("&")[1].charAt(0));
//            int infant = Character.getNumericValue(passenger.split("&")[2].charAt(0));
//            String adt = Integer.toString(adult);
//            String chd = Integer.toString(child);
//            String inf = Integer.toString(infant);
//            System.out.println(adt);
//            System.out.println(chd);
//            System.out.println(inf);
//            if (adult != 0) {
//                for (int i = 0; i < adult-1; i++) {
//                    Thread.sleep(1500);
//                    commonActions.waitForLoadState();
//                    commonActions.enterText("Adult_Agate", adt);
//                }
//            }
//            if (child != 0) {
//
//                for (int i = 0; i < child; i++) {
//                    Thread.sleep(1500);
//                    commonActions.waitForLoadState();
//                    commonActions.enterText("Child_Agate", chd);
//                }
//            }
//            if (infant != 0) {
//
//                for (int i = 0; i < infant; i++) {
//                    Thread.sleep(1500);
//                    commonActions.waitForLoadState();
//                    commonActions.enterText("Infant_Agate", inf);
//                }
//            }
//            Thread.sleep(4000);
//
//        }
//        catch(Exception e) {
//            commonActions.logAssert_Fail("Unable to select number of passengers");
//        }
//    }
//
//    @And("User enters postData in {string}")
//    public void userEntersPostDataIn(String locator) {
//        CommonActions.fillJsonInputBox(locator);
//    }
//}

