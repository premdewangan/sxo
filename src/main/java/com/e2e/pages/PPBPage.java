package com.e2e.pages;

import com.e2e.utils.DateManipulator;
import org.junit.Assert;

import java.io.IOException;
import java.util.HashMap;


	public class PPBPage extends BasePage {
		private HashMap<String, String> locatorMap = new HashMap<>();
		private HashMap<String, String> infoMap = new HashMap<>();

		public PPBPage() {
			// Locators
			locatorMap.put("PartnerPlusBenefitsTeaser","//li[@data-category-code='PARTNERPLUSBENEFIT']\n");
			locatorMap.put("AddMembership_number","//input[@placeholder='Membership number']");
			locatorMap.put("PartnerPlusBenefitsPopup","//div[@class='mat-mdc-dialog-surface mdc-dialog__surface']");
			locatorMap.put("PartnerPlusInput","//input[@aria-label='Membership number']");
			locatorMap.put("SubmitPlusButton","//span[@class='mdc-button__label']//span[text()='Submit']");

			//Info messages for all locators
			infoMap.put("PartnerPlusBenefitsTeaser","Partner Plus Benefits Teaser");
			infoMap.put("AddMembership_number","Add Membership number");
			infoMap.put("PartnerPlusBenefitsPopup","Partner Plus Benefits Popup");
			infoMap.put("PartnerPlusInput","Partner Plus Input Field");
			infoMap.put("SubmitPlusButton","Submit Plus Button");
		}


		public void enterMembershipDetails(String membershipNumberField, String membershipNumberValue) throws InterruptedException {
				try {
					waitForElementToBeVisible("PartnerPlusBenefitsTeaser",elementVisibilityMaxTimeout);
					scrollElementToVisibility("PartnerPlusBenefitsTeaser");
					waitAndClickOnTheElement("PartnerPlusBenefitsTeaser", elementVisibilityMaxTimeout);
					clickOnTheElement("PartnerPlusInput");
					takeScreenshot();
					enterTextOnTheElement("PartnerPlusInput","E11111111111111");
					waitAndClickOnTheElement("SubmitPlusButton", 10);
				} catch (Exception e) {
					logAssert_Fail("Failed to Proceed with PPB");
				}
		}



		public void verifyPopupVisibility(String popupLocator) throws InterruptedException {
			try {
				boolean status = false;
				status = waitAndCheckForElementVisibility("PartnerPlusBenefitsPopup",10);
				Assert.assertTrue("Popup Element is not Visible", status);
			} catch (Exception e) {
				logAssert_Fail(infoMap.get(popupLocator) + " is not visible. " + locatorMap.get(popupLocator));
			}
		}

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

