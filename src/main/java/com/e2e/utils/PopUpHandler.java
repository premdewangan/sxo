package com.e2e.utils;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.microsoft.playwright.Page;

public class PopUpHandler {

	public static HashMap<String, String> scenarioContext = new HashMap<>();
	public static Page PopupPage;

	public void handlePopup(Page page, int timeOutInSeconds) throws InterruptedException, IOException {
		Page popUp = page.waitForPopup(() -> {
		});
		setPopupPage(popUp, timeOutInSeconds);
	}

	public static void setPopupPage(Page page, int timeOutInSeconds) throws InterruptedException, IOException {
		PopUpHandler.PopupPage = page;
		timeOutInSeconds = timeOutInSeconds * 1000;
		PopupPage.setDefaultTimeout(timeOutInSeconds);
	}

	public Page getPopupPage() throws InterruptedException, IOException {
		PopupPage.bringToFront();
		return PopupPage;
	}

}
