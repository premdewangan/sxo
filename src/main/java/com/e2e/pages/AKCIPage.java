package com.e2e.pages;

import org.junit.Assert;

import java.util.HashMap;


public class AKCIPage extends BasePage {
    private HashMap<String, String> locatorMap = new HashMap<>();
    private HashMap<String, String> infoMap = new HashMap<>();

    public AKCIPage() {
        // Locators
        locatorMap.put("AKCITeaser","//div[contains(@class, 'teaser-title') and contains(text(), 'Automated Check-in')]");
        locatorMap.put("AddAKCI","//span[contains(text(), 'Select boarding pass preference')]");
        locatorMap.put("DeliveryDropdown","(//span[contains(@class, 'mat-mdc-select-placeholder')])[1]");
        locatorMap.put("RecievePDF","(//span[@class='service-input-label ng-star-inserted'])[1]");
        locatorMap.put("ConfirmButton","//span[@class='ng-star-inserted' and text()='Confirm your choice']");

        //Info messages for all locators
        infoMap.put("AKCITeaser","AKCI Teaser");
        infoMap.put("AddAKCI","Click on AKCI");
        infoMap.put("DeliveryDropdown","Delivery option Dropdown");
        infoMap.put("RecievePDF","Recieve in PDF");
        infoMap.put("ConfirmButton","Confirm your choice");
    }


    public void addAKCI() throws InterruptedException {
            try {
                page.waitForTimeout(5000);
                takeScreenshot();
                scrollElementToVisibility("AKCITeaser");
                waitAndClickOnTheElement("AddAKCI", elementVisibilityMaxTimeout);
                waitAndClickOnTheElement("DeliveryDropdown", elementVisibilityMaxTimeout);

                clickOnTheElement("RecievePDF");
                takeScreenshot();
                waitAndClickOnTheElement("ConfirmButton", 10);
            } catch (Exception e) {
                logAssert_Fail("Failed to Proceed with AKCI");
            }
    }



//    public void verifyPopupVisibility(String popupLocator) throws InterruptedException {
//        try {
//            boolean status = false;
//            status = waitAndCheckForElementVisibility("PartnerPlusBenefitsPopup",10);
//            Assert.assertTrue("Popup Element is not Visible", status);
//        } catch (Exception e) {
//            logAssert_Fail(infoMap.get(popupLocator) + " is not visible. " + locatorMap.get(popupLocator));
//        }
//    }

    // Additional methods specific to Membership Partner Plus Benefits Page


// Constants
final String locFlightNumber1 = "//maui-flight-number[@carrier='";
final String locFlightNumber2 = "' and @number='";



@Override
public HashMap<String, String> getLocatorMap() {
    return locatorMap;
}

@Override
public HashMap<String, String> getInfoMap() {
    return infoMap;
}

}

