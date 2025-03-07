package com.e2e.stepdef;

import com.e2e.config.Configuration;
import com.e2e.pages.AirEmPage;
import com.e2e.pages.BasePage;
import com.e2e.pages.LufthansaSearchFlightPage;
import com.e2e.utils.EnvironmentSelector;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.util.List;

public class LufthansaSearchFlightSteps extends BasePage {
    LufthansaSearchFlightPage lufSearchFlight = new LufthansaSearchFlightPage();
//    AirEmPage air_em_page = new AirEmPage();
AirEmPage airEm = new AirEmPage();
    @Given("User launches the Amadeus application with {string}")
    public void userLaunchesTheAmadeusApplicationWith(String Airline_Tenant) throws InterruptedException {
        page = browser.newPage();

        // Save information of current tenant under execution
        scenarioContext.setContext("Tenant_Name", Airline_Tenant);

        // Save information of Static Test Data source file to be used
        scenarioContext.setContext("Static_TestData_FileName", "desktop");

        final String url = EnvironmentSelector.envSelectorForBaseApplication(Airline_Tenant);

        // Save information about the environment currently being executed on
        String currentEnv = System.getProperty("envName", Configuration.ENVIRONMENT);
        scenarioContext.setContext("Current_Env", currentEnv);

        log.info("url is: " + url);
        if (url != null) {
            try {
                // Navigate to the determined URL
                page.navigate(url);

            } catch (Exception e) {
                // Log an error if navigation fails
                log.info("Error navigating to the AirEM URL: " + e.getMessage());
                logAssert_Fail("Error navigating to the AirEM URL");
            }
        } else {
            // Log a message if the URL is null
            log.info("URL is null. Cannot navigate to a null URL.");
            logAssert_Fail("URL is null. Cannot navigate to a null URL.");
        }
        log.info("Browser Launched Successfully");

    }

    @And("User enters the userId {string} and airline tenant{string} and navigate to next page")
    public void userEntersTheUserIdAndAirlineTenantAndNavigateToNextPage(String User_ID, String Organization) throws InterruptedException {
        lufSearchFlight.verifyAndEnterText("user_amadeus", User_ID);
        log.info("UserId entered successfully");

        lufSearchFlight.verifyAndEnterText("airline_tenant", Organization);
        log.info("Airline Organization code entered");

        lufSearchFlight.clicks("next_btn");
        log.info("User clicked on next button & navigated to next page");
    }

    @And("User enters the password {string} and clicks next button")
    public void userEntersThePasswordAndClicksNextButton(String Password) throws InterruptedException {
        lufSearchFlight.verifyAndEnterText("amadeus_pwd", Password);
        log.info("Amadeus password entered successfully");

        lufSearchFlight.clicks("next_btn_login");
        log.info("User clicked on Amadeus next login button");

    }

    @When("User selects the one way trip flight")
    public void userSelectsTheOneWayTripFlight() throws InterruptedException {
        lufSearchFlight.clicks("one_way_trip");
        log.info("User clicked on the one way flight trip");
    }

    @And("User clicks on the search flights submit button")
    public void userClicksOnTheSearchFlightsSubmitButton() throws InterruptedException {
        lufSearchFlight.clicks("submit_button");
        log.info("User clicked on the search flights submit button");
        Thread.sleep(50000);
//        airEm.clicksAiremPagePopUps();
    }


    @And("User searches for the flight as {string} and {string}")
    public void userSearchesForTheFlightAsAnd(String Origin, String Destination) throws IOException, InterruptedException {
        lufSearchFlight.selectOriginCity(Origin);
        lufSearchFlight.selectDestinationCity(Destination);
        log.info("User has selected the origin and destination");
    }

    @And("User selects departure date {string}")
    public void userSelectsDepartureDate(String Outbound_Date) throws InterruptedException {
        lufSearchFlight.selectOutboundDate(Outbound_Date);
        log.info("User selects departure date");
    }

//    @And("User clicks on the Agree button")
//    public void userclickAgree() throws InterruptedException, IOException {
//        lufSearchFlight.userclickAgree();
//        log.info("User clicks on the Agree button");

//    }

    @And("^User clicks on the Agree button$")
    public void userClickAgree() throws IOException, InterruptedException {

        // Wait for any necessary page load or element to be ready
        lufSearchFlight.wait(5000);

        Page newPage = null;

        // Loop through contexts and find the new page
        Iterable<? extends BrowserContext> contexts = null;
        for (BrowserContext context : contexts) {
            List<Page> pages = context.pages();

            // Check if there's more than one page (indicating a new window was opened)
            if (pages.size() > 1) {
                newPage = pages.get(1);  // Get the second page (new window/tab)
                break;  // Exit the loop once the new window is found
            }
        }

        if (newPage != null) {
            // Wait for the new page to load completely
            newPage.waitForLoadState();

            // Handle Privacy settings, if present in the new page
            if (newPage.isVisible("text=Privacy settings")) {
                // Wait for the accept button and click it
                newPage.waitForSelector("#cm-acceptAll").click();

                // Optionally wait for a bit to ensure the action is processed
                newPage.waitForTimeout(2000); // Or use waitForSelector() for a more robust wait condition
            }
        } else {
            // Handle case when new page isn't found
            System.out.println("New page not found after clicking the button.");
        }
    }
}




//    @And("^User clicks on the Agree button$")
//    public void userclickAgree() throws IOException, InterruptedException {
//
//        lufSearchFlight.wait(5000);
//
//
//        // Your code, using the 'button' parameter
////        airEm.clicksAiremPagePopUps();
////        lufSearchFlight.clickONTheElement();
//        lufSearchFlight.userclickAgree();
//        log.info("User clicks on the Agree button");
//    }
//
////    @Given("User clicks on the Agree button")
////    public void userclickAgree() throws InterruptedException, IOException {
////        lufSearchFlight.userclickAgree();
////        log.info("User clicks on the Agree button");
//}
