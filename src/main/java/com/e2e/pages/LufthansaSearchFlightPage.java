package com.e2e.pages;

import com.microsoft.playwright.options.LoadState;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

public class LufthansaSearchFlightPage extends BasePage {

    private final HashMap<String, String> locatorMap = new HashMap<>();
    private final HashMap<String, String> infoMap = new HashMap<>();

    public LufthansaSearchFlightPage() {
        // Locators
        locatorMap.put("user_amadeus", "//input[@id='userIdInput']");
        locatorMap.put("airline_tenant", "//input[@id='organizationInput']");
        locatorMap.put("next_btn", "//button[@id='id_confirmButton']");
        locatorMap.put("amadeus_pwd", "//input[@id='passwordInput']");
        locatorMap.put("next_btn_login", "//button[@id='nextButton']");
        locatorMap.put("amadeus_logout", "//button[normalize-space()='Logout']");
        locatorMap.put("one_way_trip", "//span[contains(text(),'One Way')]");
        locatorMap.put("submit_button", "//div[@class='btns']//button[text()='Submit']");
        locatorMap.put("origin_city", "//input[@id='originLocationCode']");
        locatorMap.put("destination_city", "//input[@id='destinationLocationCode']");
		locatorMap.put("departure_date", "(//input[contains(@class,'datepicker')])[1]");
        locatorMap.put("dateNextmonth","(//button[@aria-label='Next month'])");
        locatorMap.put("departureCalender","(//button[@aria-label='Open calendar']//span[@class='mat-mdc-button-touch-target'])");
        locatorMap.put("agree_button","(//button[@id='cm-acceptNone' and @class='button-secondary']\n");
        locatorMap.put("select.privacyText","(//button[@id='cm-acceptNone' and @class='button-secondary']\n");
        locatorMap.put("select.privacyText","(//button[@id='cm-acceptNone' and @class='button-secondary']\n");


        // Info messages for all locators
        infoMap.put("user_amadeus", "User ID");
        infoMap.put("airline_tenant", "Airline Organization");
        infoMap.put("next_btn", "Next Page Button");
        infoMap.put("amadeus_pwd", "Amadeus Password");
        infoMap.put("next_btn_login", "Next Login Button");
        infoMap.put("amadeus_logout", "Amadeus Logout Button");
        infoMap.put("one_way_trip", "One Way Flight Trip");
        infoMap.put("submit_button", "Submit Button");
        infoMap.put("origin_city", "Trip Departure City");
        infoMap.put("destination_city", "Trip Destination City");
		infoMap.put("departure_date", "Flight Departure Date");
        infoMap.put("departure","Clicking the next month");
        infoMap.put("departureCalender","Clicking departure Calender button");
        infoMap.put("agree_button","Click on agree button");
    }

    public void clicks(String locator) throws InterruptedException {
        Thread.sleep(3000);
        try {
            waitAndClick(locatorMap.get(locator));
        } catch (Exception e) {
            logAssert_Fail("Fails to select " + infoMap.get(locator) + ". Locator is: " + locatorMap.get(locator));
        }
    }

    public void verifyAndEnterText(String locator, String data) throws InterruptedException {
        Thread.sleep(3000);
        boolean status = false;
        status = waitAndCheckForElementVisibility(locatorMap.get(locator), 2000);
        if (status) {
            enterText(locatorMap.get(locator), data);
        } else {
            logAssert_Fail("Fails to enter text in " + infoMap.get(locator) + ". Locator is: " + locatorMap.get(locator));
        }
    }


//    public void selectOutboundDate(String locator, String departure_date) throws InterruptedException {
//        boolean status = false;
//        status = waitAndCheckForElementVisibility(locatorMap.get(locator), 2000);
//        if (status) {
//            enterText(locatorMap.get(locator), departure_date);
//        } else {
//            logAssert_Fail("Fails to enter Departure Date in " + infoMap.get(locator) + ". Locator is: " + locatorMap.get(locator));
//        }
        /// ///////
        public void selectOutboundDate(String departure_date) throws InterruptedException {
//            String depDateLocator = "//td[contains(@aria-label,'" + outboundDate + "')]";
            String depDateLocator = "//button[contains(@aria-label,'" + departure_date + "')]";

            // Additional check to ensure Calendar Screen is displayed
            if (!isTheElementVisible("dateNextmonth", elementVisibilityMinTimeout)) {
                log.info("Clicking the next month");
                clickOnTheElement("departureCalender");
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
                    clickOnTheElement("dateNextmonth");
                } catch (Exception e) {
                    logAssert_Fail("Failed to Select Departure Date");
                }
            }
        }
    public void userclickAgree() throws InterruptedException, IOException {
//        Thread.sleep(2000);

//        waitForElementToBeVisible("agree_button",elementVisibilityMaxTimeout);
//        waitAndClickOnTheElement("agree_button",elementVisibilityMaxTimeout);
        HardWait(3);

        page.keyboard().press("Tab");
        page.keyboard().press("Tab");
        page.keyboard().press("Enter");
        log.info("Click on agree button");
    }

//

//    public void acceptPrivacySettings() {
//        // Wait for the load state first
//        page.waitForLoadState(LoadState.LOAD);
//        if (page.isVisible(locatorMap.get("select.privacyText"))) {
//            page.waitForSelector(getLocator("select.privacyAcceptBtn")).click();
//            page.waitForTimeout(3000);
//        }
//    }

    public void selectOriginCity(String origin) throws InterruptedException, IOException {
        Thread.sleep(2000);
        verifyAndEnterText("origin_city", origin);
        selectDropDownValue();
        log.info("Flight origin city selected");
    }

    public void selectDestinationCity(String destination) throws InterruptedException, IOException {
        Thread.sleep(2000);
        verifyAndEnterText("destination_city", destination);
        selectDropDownValue();
        log.info("Flight destination city selected");
    }

    public void selectDropDownValue() throws InterruptedException {
        // Need to wait for the drop down to be displayed completely for KeyPress to
        // occur
		HardWait(3);
		page.keyboard().press("ArrowDown");
		page.keyboard().press("Enter");
		page.keyboard().press("Tab");
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
