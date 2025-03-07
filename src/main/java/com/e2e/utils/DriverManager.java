package com.e2e.utils;

import com.microsoft.playwright.*;

public class DriverManager {

	private static Browser browser;

	public static void setUp() {
	    // Get headless and browser type properties
	    String headless = System.getProperty("headless", "false");
	    Boolean enableHeadless = headless.equalsIgnoreCase("true");

	    String browserType = System.getProperty("browser", "chromium"); // Default to chromium

	    Playwright playwright = Playwright.create();

	    // Configure and launch the selected browser
	    switch (browserType.toLowerCase()) {
	        case "firefox":
	            browser = playwright.firefox()
	                    .launch(new BrowserType.LaunchOptions().setHeadless(enableHeadless).setTimeout(60000));
	            break;
	        case "webkit":
	            browser = playwright.webkit()
	                    .launch(new BrowserType.LaunchOptions().setHeadless(enableHeadless).setTimeout(60000));
	            break;
	        case "chromium":
	        default:
	            browser = playwright.chromium()
	                    .launch(new BrowserType.LaunchOptions().setHeadless(enableHeadless).setTimeout(60000));
	            break;
	    }

	    // Use the browser object for further actions
	}


	public static void tearDown() {
		if (browser != null) {
			browser.contexts().clear();
			browser.close();
			browser = null;
		}
	}

	public static Browser getBrowser() {
		return browser;
	}

}
