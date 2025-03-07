package com.e2e.pages;

import java.io.IOException;
import java.util.HashMap;

public class ConfirmationPage extends BasePage {

	private HashMap<String, String> locatorMap = new HashMap<>();
	private HashMap<String, String> infoMap = new HashMap<>();

	public ConfirmationPage() {
		// Locators
		locatorMap.put("printIcon","//*[@class='print-booking-label']");
		locatorMap.put("calenderIcon","//*[@class='print-boking-label']");
		locatorMap.put("continueBookingbtn","(//span[@class=\"mdc-button__label\"])[4]");


		// Info messages for all locators
		infoMap.put("printIcon", "Print icon is available");
		infoMap.put("calenderIcon", "Calender icon is available");
		infoMap.put("continueBookingbtn", "Continue Booking Button is available");

	}
	public void clicksPrintIcon() throws InterruptedException, IOException {
		try {
			scrollElementToVisibility("printIcon");
			waitAndClickOnTheElement("printIcon", elementVisibilityMaxTimeout);
			takeScreenshot();
		} catch (Exception e) {
			logAssert_Fail("Failed to Click on Print Icon");
		}
	}
	public void clickAddCalenderIcon() throws InterruptedException, IOException {
		try {
			scrollElementToVisibility("calenderIcon");
			waitAndClickOnTheElement("calenderIcon", elementVisibilityMaxTimeout);
		} catch (Exception e) {
			logAssert_Fail("Failed to Click on Print Icon");
		}
	}
	public void ContinueBookingButton() throws InterruptedException {
        waitAndCheckForElementVisibility("continueBookingbtn",elementVisibilityMaxTimeout);
		Thread.sleep(5000);
		waitAndClickOnTheElement("continueBookingbtn",1000);
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
