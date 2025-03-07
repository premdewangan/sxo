package com.e2e.pages;

import static com.e2e.utils.Base64EncryptionUtil.decryption;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.e2e.utils.Base64EncryptionUtil;
import com.e2e.utils.PopUpHandler;

public class FifteenBelowPage extends BasePage {

	private HashMap<String, String> locatorMap = new HashMap<>();
	private HashMap<String, String> infoMap = new HashMap<>();

	public PopUpHandler PopupHandler = new PopUpHandler();

	public FifteenBelowPage() {
		// Locators
		locatorMap.put("fifteenBelowEmailID", "//*[@id=\"email\"]");
		locatorMap.put("fifteenBelowPassword", "//*[@id=\"password\"]");
		locatorMap.put("signInButton", "//button[text()='Sign in']");
		locatorMap.put("lxTstTenant", "//*[contains(text(),'LX-TST')]");
		locatorMap.put("lxUatTenant", "//*[contains(text(),'LX-UAT')]");
		locatorMap.put("transactionalHistoryIcon", "//span[text()='Transactional History']");
		locatorMap.put("historicalReportingIcon", "//span[text()='Historical Reporting']");
		locatorMap.put("searchBox", "//*[@id=\"searchBox\"]");
		locatorMap.put("searchTransactionalHistoryButton", "//*[@id=\"searchTransactionalHistory\"]");

		locatorMap.put("expandEmailDetailsButton", "//button[@class='expand-toggle']");
		locatorMap.put("viewAndPrintButton", "//button[text()='View and Print']");
		locatorMap.put("activateEmailButton", "//a[@class='btn']");

		locatorMap.put("emailContentFrame", "#htmlPreview");

		// Info messages for all locators
		infoMap.put("fifteenBelowEmailID", "Text Box to enter Email to Perform 15 below Login");
		infoMap.put("fifteenBelowPassword", "Text Box to enter Password to Perform 15 below Login");
		infoMap.put("signInButton", "Button to select Sign In");
		infoMap.put("lxTstTenant", "Button to select LX-TST Tenant");
		infoMap.put("lxUatTenant", "Button to select LX-UAT Tenant");
		infoMap.put("transactionalHistoryIcon", "Icon to select Transactional History");
		infoMap.put("historicalReportingIcon", "Icon to select Historical Reporting");
		infoMap.put("searchBox", "Search Box to select the Inbox of Email");
		infoMap.put("searchTransactionalHistoryButton", "Button to Search Transactional History");

		infoMap.put("expandEmailDetailsButton", "Toggle button to expand to view details of Email");
		infoMap.put("viewAndPrintButton", "Button to View and Print Email");
		infoMap.put("activateEmailButton", "Button to Activate Email User ID");

		infoMap.put("emailContentFrame", "iFrame that shows the content of the Email selected");

	}

	public void naviagteToFifteenBelowMail(String url) throws IOException, InterruptedException {
		try {
			page.navigate(url);
		} catch (Exception e) {
			logAssert_Fail("Failed to Navigate to 15 Below URL");
		}

	}

	public void loginToFifteenBelow() throws InterruptedException, IOException {
		try {

			String decodedEmail = Base64EncryptionUtil.readDecryptedData("fifteenBelowUserID");
			String decodedPassword = Base64EncryptionUtil.readDecryptedData("fifteenBelowPassword");

			waitAndClickOnTheElement("fifteenBelowEmailID", elementVisibilityMaxTimeout);
			enterTextOnTheElement("fifteenBelowEmailID", decodedEmail);
			enterTextOnTheElement("fifteenBelowPassword", decodedPassword);

			clickOnTheElement("signInButton");
			takeExtraScreenshot();
		} catch (Exception e) {
			logAssert_Fail("Failed to Login to 15Below Portal");
		}
	}

	public void selectTenant(String tenantName) throws IOException, InterruptedException {
		try {
			if (tenantName.contentEquals("LX-TST")) {
				waitAndClickOnTheElement("lxTstTenant", elementVisibilityMaxTimeout);
			} else {
				waitAndClickOnTheElement("lxUatTenant", elementVisibilityMaxTimeout);
			}
		} catch (Exception e) {
			logAssert_Fail("Failed to select Tenant");
		}
	}

	public void selectMenu(String menuOption) throws IOException, InterruptedException {
		try {
			if (menuOption.contentEquals("Transactional History")) {
				waitAndClickOnTheElement("historicalReportingIcon", elementVisibilityMaxTimeout);
				waitAndClickOnTheElement("transactionalHistoryIcon", elementVisibilityMaxTimeout);
			} else {
				waitAndClickOnTheElement("historicalReportingIcon", elementVisibilityMaxTimeout);
			}
		} catch (Exception e) {
			logAssert_Fail("Failed to select Menu");
		}
	}

	public void searchEmailInbox(String emailInput) throws IOException, InterruptedException {
		try {
			waitAndClickOnTheElement("searchBox", elementVisibilityMaxTimeout);
			enterTextOnTheElement("searchBox", emailInput);
			waitAndClickOnTheElement("searchTransactionalHistoryButton", elementVisibilityMaxTimeout);
		} catch (Exception e) {
			logAssert_Fail("Failed to search inbox");
		}
	}

	public void viewEmailContent() throws IOException, InterruptedException {
		try {
			waitAndClickOnTheElement("expandEmailDetailsButton", elementVisibilityMaxTimeout);
			waitAndClickOnTheElement("viewAndPrintButton", elementVisibilityMaxTimeout);
		} catch (Exception e) {
			logAssert_Fail("Failed to view email content");
		}
	}

	public void activateEmailID() throws IOException, InterruptedException {
		try {

			FrameLocator frameLocator = page.frameLocator(locatorMap.get("emailContentFrame"));
			String url = frameLocator.locator(locatorMap.get("activateEmailButton")).getAttribute("href");
			log.info(url);
			page.navigate(url);

		} catch (Exception e) {
			logAssert_Fail("Failed to activate email ID");
		}
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
