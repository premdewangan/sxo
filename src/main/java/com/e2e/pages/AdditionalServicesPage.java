package com.e2e.pages;

import java.io.IOException;
import java.util.HashMap;

import com.e2e.utils.PopUpHandler;
import com.microsoft.playwright.*;

public class AdditionalServicesPage extends BasePage {

	private HashMap<String, String> locatorMap = new HashMap<>();
	private HashMap<String, String> infoMap = new HashMap<>();

	public PopUpHandler PopupHandler = new PopUpHandler();

	public AdditionalServicesPage() {
		// Locators
		locatorMap.put("confirmationYiluIframe", "//iframe[contains(@src,'yiluhub')]");
		locatorMap.put("airportParkingWidget", "//div[@id='yiluWidget']");
		locatorMap.put("confirmationYiluSelectAllButton", "//span[contains(text()='Select all']");
		locatorMap.put("airportPageText", "//h1[contains(text(),'Book your private')]");
		locatorMap.put("findLoungeHeader", "//div[contains(text(),'Find Lounge')]");
		locatorMap.put("findLoungeTxt", "//div[contains(text(),'Lounges in')]");
		locatorMap.put("insuranceTeaserDisplayed", "//h3[contains(text(),'Insurance')]");
		locatorMap.put("addInsuranceButton", "//span[contains(text(),'Add Insurance')]");
		locatorMap.put("selectInsuranceButton", "//b[contains(text(),'Yes, insure my trip for')]");
		locatorMap.put("insuranceTotalPriceDisplayed", "//refx-discountable-price[contains(@class,'total-price')]");
		locatorMap.put("insuranceConfirmButton", "//span[contains(text(),'Confirm')]//ancestor::button");
		locatorMap.put("insuranceConfirmationText", "//span[contains(text(),'Travel Care')]");

		// Info messages for all locators
		infoMap.put("confirmationYiluIframe", "iFRAME showing the confirmation");
		infoMap.put("airportParkingWidget", "Button to add Airport Parking");
		infoMap.put("confirmationYiluSelectAllButton", "Button to Select All");
		infoMap.put("airportPageText", "Heading for Aiport Page");
		infoMap.put("findLoungeHeader", "Heading to Find Lounge");
		infoMap.put("findLoungeTxt", "Text box to find the lounge");
		infoMap.put("insuranceTeaserDisplayed", "Insurance Teaset details displayed");
		infoMap.put("addInsuranceButton", "Button to add insurance");
		infoMap.put("selectInsuranceButton", "Button to select insurance");
		infoMap.put("insuranceTotalPriceDisplayed", "Total Price Displayed");
		infoMap.put("insuranceConfirmButton", "Button to confirm the insurance selected");
		infoMap.put("insuranceConfirmationText", "Text box displaying confirmation for Insurance Selected");

	}

	public void activateAirportParkingWidget() throws InterruptedException, IOException {
		try {
			switchToFrame(locatorMap.get("confirmationYiluIframe"));
			clickOnTheElement("airportParkingWidget");
		} catch (Exception e) {
			logAssert_Fail("Failed to Activate Airport Parking Widget.Failed to Click on Element "
					+ infoMap.get("airportParkingWidget") + ". Locator is: " + locatorMap.get("airportParkingWidget"));
		}
	}

	public void handleAirportParkingWidget() throws InterruptedException, IOException {
		try {
			PopupHandler.handlePopup(page, elementVisibilityMaxTimeout);
			Page PopupPage = null;
			PopupPage = PopupHandler.getPopupPage();

			if (PopupPage.isVisible(locatorMap.get("confirmationYiluSelectAllButton"))) {
				clickOnTheElement("confirmationYiluSelectAllButton");
			} else {
				logAssert_Fail("Failed to load Select All Button " + infoMap.get("confirmationYiluSelectAllButton")
						+ ". Locator is: " + locatorMap.get("confirmationYiluSelectAllButton"));
			}
		} catch (Exception e) {
			logAssert_Fail("Failed to Handle Airport Parking Widget.Failed to Click on Element "
					+ infoMap.get("confirmationYiluSelectAllButton") + ". Locator is: "
					+ locatorMap.get("confirmationYiluSelectAllButton"));
		}
	}

	public void verifyAirportParkingWidget() throws InterruptedException, IOException {
		try {
			waitForElementToBeVisible("airportPageText", elementVisibilityMaxTimeout);
			logAssert_Pass("Successfully verified Airport Parking Widget");
		}

		catch (Exception e) {
			logAssert_Fail("Failed to Verify Airport Parking Widget");
		}
	}

	public void activateLoungeTeaserWidget() throws InterruptedException, IOException {
		try {
			switchToFrame(locatorMap.get("confirmationYiluIframe"));
			clickOnTheElement("findLoungeHeader");
		} catch (Exception e) {
			logAssert_Fail("Failed to Activate Lounge Teaser Widget.Failed to Click on Element "
					+ infoMap.get("findLoungeHeader") + ". Locator is: " + locatorMap.get("findLoungeHeader"));
		}
	}

	public void handleLoungeTeaserWidget() throws InterruptedException, IOException {
		try {
			PopupHandler.handlePopup(page, elementVisibilityMaxTimeout);
			Page widgetDisplayed = null;
			widgetDisplayed = PopupHandler.getPopupPage();
			if (widgetDisplayed.isVisible(locatorMap.get("confirmationYiluSelectAllButton"))) {
				clickOnTheElement("confirmationYiluSelectAllButton");
			} else {
				logAssert_Fail("Failed to load Select All Button " + infoMap.get("confirmationYiluSelectAllButton")
						+ ". Locator is: " + locatorMap.get("confirmationYiluSelectAllButton"));
			}

			waitForElementToBeVisible("findLoungeTxt", elementVisibilityMaxTimeout);

		} catch (Exception e) {
			logAssert_Fail("Failed to Handle Lounge Teaser Widget.Failed to Click on Element "
					+ infoMap.get("confirmationYiluSelectAllButton") + ". Locator is: "
					+ locatorMap.get("confirmationYiluSelectAllButton"));
		}
	}

	public void verifyLoungeTeaser() throws InterruptedException, IOException {

		try {
			scrollWidget();
			takeExtraScreenshot();

		} catch (Exception e) {
			logAssert_Fail("Failed to Verify Lounge Teaser");
		}
	}

	public void addInsurance() throws InterruptedException, IOException {
		try {
			waitForElementToBeVisible("insuranceTeaserDisplayed", elementVisibilityMaxTimeout);
			clickOnTheElement("addInsuranceButton");
			waitAndClickOnTheElement("selectInsuranceButton", elementVisibilityMaxTimeout);
		} catch (Exception e) {
			logAssert_Fail("Failed to Add Insurance Teaser");
		}

	}

	public void verifyInsurance() throws InterruptedException, IOException {
		try {
			waitAndClickOnTheElement("insuranceConfirmationText", elementVisibilityMaxTimeout);
			takeExtraScreenshot();
			logAssert_Pass("Successfully verified and added Insurance Teaser");
		} catch (Exception e) {
			logAssert_Fail("Failed to Verify Added Insurance Teaser");
		}

	}

	public void scrollWidget() throws InterruptedException, IOException {
		try {

			for (int count = 1; count <= 4; count++) {
				HardWait(2);
				page.mouse().wheel(1, 1000);
			}
		} catch (Exception e) {
			logAssert_Fail("Failed to Scroll Widget");
		}
	}

	public void closePopUp() throws InterruptedException {
		PopupPage.close();

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
