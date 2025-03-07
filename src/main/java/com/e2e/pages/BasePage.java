package com.e2e.pages;

import com.e2e.utils.PropertyReader;
import com.e2e.utils.ScenarioContext;
import com.microsoft.playwright.*;
import com.e2e.utils.ScenarioContext;
import com.e2e.utils.Base64EncryptionUtil;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.microsoft.playwright.options.AriaRole;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.LogManager;
import org.junit.Assert;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

import com.aventstack.extentreports.configuration.Config;
import com.e2e.config.WaitsConfig;
import com.e2e.config.TestDataConfiguration;
import com.e2e.utils.DriverManager;
import com.microsoft.playwright.ElementHandle.WaitForElementStateOptions;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.mongodb.util.Util;
import cucumber.api.Scenario;

import static com.e2e.utils.Base64EncryptionUtil.decryption;
import static org.junit.Assert.fail;

public abstract class BasePage {
	protected Browser browser;
	protected Object clickONTheElement;
	Playwright playwright;
	protected BrowserContext context;
	public static Page page;
	public static Page lh_Page;
	public static Page lx_Page;
	public static Page sn_Page;
	public static Page os_Page;
	public static Scenario sc;
	public static final org.apache.log4j.Logger log = LogManager.getLogger(BasePage.class);
	public static boolean isScreenshotCapturedAfterFailure = true;
	public static boolean isAdditionalScreenshotsCaptured = true;
	public static Page PopupPage;

	public static ScenarioContext scenarioContext = ScenarioContext.Singleton();
	public static ScenarioContext secondaryScenarioContext = ScenarioContext.Singleton();

	// Provide default implementations for HashMap
	public HashMap<String, String> getLocatorMap() {
		return new HashMap<>();
	}

	public HashMap<String, String> getInfoMap() {
		return new HashMap<>();
	}

	// Page Configurations
	protected int elementVisibilityMaxTimeout = WaitsConfig.ELEMENT_WAIT_TIMEOUT_SECONDS;
	protected int pageVisibilityMaxTimeout = WaitsConfig.PAGE_LOAD_TIMEOUT_SECONDS;
	protected int elementVisibilityMinTimeout = WaitsConfig.ELEMENT_MINWAIT_TIMEOUT_SECONDS;
	protected int elementVisibilityPollIntervalTimeout = WaitsConfig.ELEMENT_POLLING_WAIT_SECONDS;
	protected int elementVisibilityMaximumRetry = WaitsConfig.MAXIMUM_RETRY_FOR_VISIBILITY;
	protected int elementShadowDomVisibilityMaximumRetry = WaitsConfig.MAXIMUM_RETRY_FOR_SHADOWDOM;

	public BasePage() {
		this.browser = DriverManager.getBrowser();
		this.context = browser != null ? browser.newContext() : null;
	}

	public void setScenario(Scenario scenario) {
		sc = scenario;
	}

	public void waitAndClick(String object) {
		try {
			waitAndCheckForElementVisibility(object, elementVisibilityMaxTimeout);
			page.isVisible(object);
			page.click(object);
		} catch (Exception e) {
			throw e;
		}
	}

	public String getText(String locator, String text) {
		String data = null;
		try {
			data = String.valueOf(page.locator(locator).getByText(text));
		} catch (Exception e) {
			throw e;
		}
		return data;
	}

	public void enterText(String object, String data) {

		try {
			if (page.locator(object).isVisible() == true) {
				page.locator(object).fill(data);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean waitAndCheckForElementVisibility(String locator, int timeoutSeconds) {
		int timeoutMilliseconds = timeoutSeconds * 100;
		boolean status = false;
		int maxRetryCount = elementVisibilityMaximumRetry;
		int currentRetryCount = 2;

		while (currentRetryCount <= maxRetryCount) {
			try {
				log.debug("Attempting to check visibility of element " + locator + " for " + timeoutSeconds
						+ " seconds. Attempt number: " + currentRetryCount);
				page.setDefaultNavigationTimeout(timeoutMilliseconds);
				page.waitForLoadState();
				page.waitForLoadState(LoadState.DOMCONTENTLOADED);
				page.waitForLoadState(LoadState.LOAD);

//				Locator element = getShadowDomElement(locator);
				Locator element = page.locator(locator);
				if (element == null) {
					log.debug("Going to wait for visibility after getting null element");
					for (int retryAttempt = 1; retryAttempt <= timeoutSeconds; retryAttempt++) {
						HardWait(1);
//						element = getShadowDomElement(locator);
						element = page.locator(locator);
						if (element != null) {
							break;
						}
					}
				} else {
					log.debug("Going to wait for visibility using page function");
					element.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE)
							.setTimeout(timeoutMilliseconds));
					status = element.isVisible();
					log.debug("The status if visibility as page object is :" + status);
				}

				log.info("Visibility status of element with locator " + locator + " is " + status);
				if (status) {
					log.info("Element with selector: " + locator + " has appeared on the page.");
					break;
				}
			} catch (PlaywrightException e) {
				log.debug("Failed to find the element: " + locator);
			} catch (Exception e) {
				log.debug("An unexpected error occurred while waiting for element visibility: " + locator, e);
			}
			currentRetryCount++;
		}
		return status;
	}

	public boolean waitAndCheckForElementToBeEnabled(String locatorID) throws InterruptedException {
		// Default values are taken from Wait.Config
		return waitAndCheckForElementToBeEnabled(locatorID, elementVisibilityMaxTimeout,
				elementVisibilityPollIntervalTimeout);
	}

	public boolean waitAndCheckForElementToBeEnabled(String locatorID, int timeoutSeconds, int pollingIntervalSeconds)
			throws InterruptedException {
		int timeoutMilliseconds = timeoutSeconds * 1000;
		// Pre-Check for Element Visibility
		if (!waitAndCheckForElementVisibility(locatorID, timeoutSeconds)) {
			log.warn("The object with locator " + locatorID + " is not enabled.");
			return false;
		}

//		Locator locator = getShadowDomElement(locatorID);
		Locator locator = page.locator(locatorID);
		long endTime = System.currentTimeMillis() + timeoutMilliseconds;

		while (System.currentTimeMillis() < endTime) {
			try {
				if (locator.isVisible()) {
					Object result = locator.evaluate("element => !element.disabled");
					if (result instanceof Boolean && (Boolean) result) {
						log.debug("The object with locator " + getLocatorMap().get(locatorID)
								+ " is ENABLED after waiting.");
						return true;
					} else {
						log.debug("Waiting for Object with locator " + getLocatorMap().get(locatorID)
								+ " to be Enabled .....");
					}
				} else {
					log.debug("The object with locator " + locatorID + " is not visible.");
				}
			} catch (com.microsoft.playwright.TimeoutError e) {
				log.error("Timeout while waiting enabling for element state for locator " + locatorID, e);
				return false;
			} catch (Exception e) {
				log.error("Error while evaluating element enable state for locator " + locatorID, e);
			}

			try {
				Thread.sleep(pollingIntervalSeconds * 1000); // Wait for the specified interval before checking again
			} catch (InterruptedException e) {
				log.error("Interrupted while waiting for locator " + locatorID + " to be enabled", e);
				Thread.currentThread().interrupt(); // Restore interrupted status
			}
		}

		log.warn("Timeout reached. The object with locator " + locatorID + " is not enabled after " + timeoutSeconds
				+ " seconds.");
		return false;
	}
//	public void closeLoginFlag() {
//
//		try {
//
//			waitForLoadState();
//
//			waitForTimeOutAndPageLoad(wait5Sec);
//
//			Locator shadowHost = page.locator(getLocatorStr("LoginFlagCloseHost"));
//
//			Locator shadowHost2 = shadowHost.locator(getLocatorStr("innerLoginFlagCloseHost"));
//
//			Locator shadowElement = shadowHost2.locator(getLocatorStr("loginflagColse"));
//
//			// Now you can interact with the shadow element
//
//			if (shadowElement.isVisible()) {
//
//				shadowElement.click();
//
//			} else {
//
//				LOGGER.info("Login Modal is not present on home page.");
//
//			}
//
//		} catch (PlaywrightException ex) {
//
//			LOGGER.error("No Login Flag appeared : " + ex.getMessage());
//
//		}
//
//	}

	public String handleWindow(Page popupPage) {
		String popupUrl = null;
		try {
			popupPage.bringToFront();
			popupUrl = popupPage.url();
		} catch (Exception e) {
			throw e;
		}
		return popupUrl;
	}

	public void takeScreenshot() {
		byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
		String testname = sc.getName();
		sc.embed(screenshot, "image/png");
		sc.write(testname);
	}

	// To take additional screenshots apart for the configured one , in case of
	// Debugging , intermediate screenshot etc
	public void takeExtraScreenshot() {
		if (isAdditionalScreenshotsCaptured) {
			takeScreenshot();
		}
	}

	public void popUpPageTakeScreenshot() {
		byte[] screenshot = PopupPage.screenshot(new Page.ScreenshotOptions().setFullPage(true));
		String testname = sc.getName();
		sc.embed(screenshot, "image/png");
		sc.write(testname);
	}

	public void logAssert_Fail(String errMsg) throws InterruptedException {
		log.error(errMsg);
		takeScreenshot();
		isScreenshotCapturedAfterFailure = true;
		sc.write(errMsg);
		Assert.fail(errMsg);
	}

	public void logAssert_Pass(String successMsg) throws InterruptedException {
		sc.write(successMsg);
		Assert.assertTrue(successMsg, true);
	}

	public static void HardWait(int seconds) throws InterruptedException {
		int milliSeconds = seconds * 1000;
		log.info("Waiting for " + seconds + " seconds");
		Thread.sleep(milliSeconds);
	}

	public boolean waitAndClickInElementArray(String locator, int position, int maxTimeOut)
			throws InterruptedException, IOException {
		Boolean isElementVisible = page.locator(locator).nth(position).isVisible();
		log.info("Visibility of element with the locator " + locator + " at the " + position + " position is "
				+ isElementVisible);
		int Counter = 0;

		while (!isElementVisible) {
			HardWait(1);
			log.debug("waiting for object ....");
			isElementVisible = page.locator(locator).nth(position).isVisible();
			log.debug("Current Object Visibilty Status :" + isElementVisible);
			Counter++;
			if (Counter > maxTimeOut || isElementVisible) {
				break;
			}
		}
		if (Counter > maxTimeOut && isElementVisible == false) {
			log.debug("Failed to Get Element . Locator is: " + locator);
		}
		page.locator(locator).nth(position).click();
		return isElementVisible;
	}

	public String getText(String locator) throws InterruptedException, IOException {
		String result;
		Boolean status = waitAndCheckForElementVisibility(locator, elementVisibilityMaxTimeout);
		HardWait(2); // To avoid scenario when element is attached and enabled but text not displayed
		log.info("Object Visibility :" + status);
		result = page.locator(locator).textContent();
		log.info("Output Text is :" + result);
		return result;
	}
////////////////////////
	public void switchToFrame(String frameLocator) {
		page.frameLocator(frameLocator);
	}

	public void switchtoMainFrame() {
		page.mainFrame();
	}

	public static void moveSlider(String sliderLocator, String moveMode) throws InterruptedException, IOException {
		HardWait(3); // To make the page load ready for key press actions
		Locator slider = page.locator(sliderLocator);
		BoundingBox box = slider.boundingBox();

		// for partial movement of slider
		page.mouse().move(box.x + box.width / 2, box.y + box.height / 2);
		page.mouse().down();

		// for complete movement of slider
		if (moveMode.equalsIgnoreCase("Complete")) {
			page.mouse().move(box.x + box.width / 1, box.y + box.height / 1);
		}
		page.mouse().up();
	}

	// Common methods that can be used across different pages

	public void waitForElementToBeVisible(String locatorKey, int timeOut) throws InterruptedException {
		try {
			log.debug("Going to wait for the element to be visible for the key " + locatorKey + " until " + timeOut
					+ "seconds..");
			boolean status = isTheElementVisible(locatorKey, timeOut);
			if (!status) {
				logAssert_Fail("Failed to Wait for Element " + getInfoMap().get(locatorKey) + ". Locator is: "
						+ getLocatorMap().get(locatorKey));
			}
		} catch (Exception e) {
			logAssert_Fail("Exception raised while wait for Element " + getInfoMap().get(locatorKey) + ". Locator is: "
					+ getLocatorMap().get(locatorKey) + ". Exception raised is: " + e);
		}
	}

	public void clickOnTheElement(String locatorKey) throws InterruptedException {
		try {
			waitAndClick(getLocatorMap().get(locatorKey));
		} catch (Exception e) {
			logAssert_Fail("Failed to Click on Element " + getInfoMap().get(locatorKey) + ". Locator is: "
					+ getLocatorMap().get(locatorKey) + ". The exception raised is :" + e);
		}
	}
	public void switchToNewWindow(String buttonLocatorKey) throws InterruptedException {
		// Click the button that opens the new window
		clickOnTheElement(buttonLocatorKey);

		// Wait for the new window (popup) to appear
		Page newWindow = waitForNewWindow();

		// Perform actions in the new window, now that it's switched
		performActionsInNewWindow(newWindow);
	}
	public static Page clickAndSwitchToNewTab(String buttonSelector) {
		// Click the button that opens the new tab
		page.locator(buttonSelector).click();

		// Wait for the new tab to open (only once)
		Page newTab = page.context().waitForPage(() -> {
		});

		// Wait for the new tab to load
		newTab.waitForLoadState();

		// Return the new tab Page object
		page = newTab; // Set the current page to the new tab
		return newTab;
	}
	// Reusable method to click an element (similar to your existing method)
	public void clickONTheElement(String locatorKey) throws InterruptedException {
		try {
			waitAndClick(getLocatorMap().get(locatorKey));
		} catch (Exception e) {
			logAssert_Fail("Failed to Click on Element " + getInfoMap().get(locatorKey) + ". Locator is: "
					+ getLocatorMap().get(locatorKey) + ". The exception raised is :" + e);
		}
	}

	// Wait for the new window (popup) to open after clicking a button
	private Page waitForNewWindow() {
		final Page[] newWindow = new Page[1];

		// Set up an event listener to handle the new popup window
		page.onPopup(popupPage -> {
			newWindow[0] = popupPage;  // Store reference to the new window (popup)
		});

		// Wait until the popup is created and returned
		// Wait for the new window (this will be triggered once the window opens)
		page.waitForTimeout(2000);  // Wait for a short time to make sure the new window is opened

		return newWindow[0]; // Return the new window (popup)
	}

	// Perform actions in the new window (pop-up)
	private void performActionsInNewWindow(Page newWindow) throws InterruptedException {
		// Example action in the new window
		newWindow.waitForLoadState();  // Wait for the page to load completely
		newWindow.click("buttonInNewWindow");  // Example action (click a button in the new window)
		// You can add more actions here if needed
	}

	// Example of waitAndClick() method (you should already have this in your framework)
	private void waitAndClick(Locator locator) throws InterruptedException {
		locator.waitFor();
		locator.click();
	}
	public void enterTextOnTheElement(String locatorKey, String data) throws InterruptedException {
		try {
			enterText(getLocatorMap().get(locatorKey), data);
		} catch (Exception e) {
			logAssert_Fail("Failed to enter Text on Element " + getInfoMap().get(locatorKey) + ". Locator is: "
					+ getLocatorMap().get(locatorKey));
		}
	}

	public String getTextOnTheElement(String locatorKey) throws InterruptedException {
		String displayedText;
		try {
			scrollElementToVisibility(locatorKey);
			displayedText = getText(getLocatorMap().get(locatorKey));
		} catch (Exception e) {
			logAssert_Fail("Failed to Get Text displayed on Element " + getInfoMap().get(locatorKey) + ". Locator is: "
					+ getLocatorMap().get(locatorKey));
			displayedText = null;
		}
		return displayedText;
	}

	public void waitAndClickOnTheElement(String locatorKey, int timeOut) throws InterruptedException {
		waitForElementToBeVisible(locatorKey, timeOut);
		clickOnTheElement(locatorKey);
	}

	public boolean isTheElementVisible(String locatorKey, int timeOut) throws InterruptedException {
		try {
			boolean isElementVisibile = waitAndCheckForElementVisibility(getLocatorMap().get(locatorKey), timeOut);
			return isElementVisibile;
		} catch (Exception e) {
			log.info("Element " + getInfoMap().get(locatorKey) + " is not visible. Locator is: "
					+ getLocatorMap().get(locatorKey));
			return false;
		}

	}

	public void assertTextOnTheElement(String locatorKey, String expectedText) throws InterruptedException {

		waitForElementToBeVisible(locatorKey, elementVisibilityMaxTimeout);
		String actualText = getTextOnTheElement(locatorKey);
		actualText = actualText.trim();
		expectedText = expectedText.trim();
		log.info("The actual text displayed is " + actualText);
		log.info("The expected text is " + expectedText);
		if (!actualText.equals(expectedText)) {
			logAssert_Fail("Text Displayed is incorrect for the Element " + getInfoMap().get(locatorKey)
					+ ". Locator is: " + getLocatorMap().get(locatorKey));
		}

	}

	public static String readDecryptedData(String key) {
		String value = null;
		String decrypted_Value = null;
		key = key.trim();

		String testDataFileName = scenarioContext.getContext("Static_TestData_FileName");
		try {

			if (testDataFileName.toLowerCase().contains("mmg")) {
				value = PropertyReader.getValueForKey(key, TestDataConfiguration.MMGStaticTestDataConfigPath);
			}
			if (testDataFileName.toLowerCase().contains("desktop")) {
				value = PropertyReader.getValueForKey(key, TestDataConfiguration.DesktopStaticTestDataConfigPath);
			}
			if (testDataFileName.toLowerCase().contains("chatbot")) {
				value = PropertyReader.getValueForKey(key, TestDataConfiguration.ChatbotStaticTestDataConfigPath);
			}
			log.info(value);
			decrypted_Value = decryption(value);
			log.info(decrypted_Value);
		} catch (IOException e) {
			Assert.fail("Static test data is missing for" + key);
		}
		return decrypted_Value;

	}

	public static String readDynamicExternalData(String key) {
		String value = null;
		key = key.trim();

		String testDataFileName = secondaryScenarioContext.getContext("Dynamic_TestData_FileName");
		try {

			if (testDataFileName.toLowerCase().contains("chatbot")) {
				value = PropertyReader.getValueForKey(key, TestDataConfiguration.ChatbotDynamicTestDataConfigPath);
			}
			if (testDataFileName.toLowerCase().contains("desktop")) {
				value = PropertyReader.getValueForKey(key, TestDataConfiguration.DesktopDynamicTestDataConfigPath);
			}

			log.info(value);
		} catch (IOException e) {
			Assert.fail("Dynamic test data is missing for" + key);
		}
		return value;

	}

	public void waitforEitherOfElementToAppear(String locatorKey1, String locatorKey2) throws InterruptedException {
		int maxRetryCount = elementVisibilityMaximumRetry;
		int timeoutInSeconds = elementVisibilityMaxTimeout;

		for (int currentRetryCount = 1; currentRetryCount <= maxRetryCount; currentRetryCount++) {
			log.debug("Checking if " + locatorKey1 + " or " + locatorKey2 + " has appeared on page, attempt "
					+ currentRetryCount);
			page.waitForLoadState();
			HardWait(5);
			log.info("Page load completed after wait");

			CompletableFuture<ElementHandle> element1Future = createElementFuture(locatorKey1, timeoutInSeconds);
			CompletableFuture<ElementHandle> element2Future = createElementFuture(locatorKey2, timeoutInSeconds);

			try {
				CompletableFuture<Object> result = CompletableFuture.anyOf(element1Future, element2Future);
				Object appearedElement = result.get(timeoutInSeconds, TimeUnit.SECONDS);

				if (appearedElement != null) {
					if (element1Future.isDone() && !element1Future.isCompletedExceptionally()
							&& element1Future.get() != null) {
						log.info("Element with selector: " + locatorKey1 + " has appeared on the page.");
						return;
					} else if (element2Future.isDone() && !element2Future.isCompletedExceptionally()
							&& element2Future.get() != null) {
						log.info("Element with selector: " + locatorKey2 + " has appeared on the page.");
						return;
					}
				} else {
					log.info("Neither element appeared on the page within the timeout period.");
				}
			} catch (Exception e) {
				log.debug("Error occurred while waiting for elements: " + e.getMessage());
			}
			HardWait(5);
			log.info("Dual Object Visibility check completed...");
		}
	}

	private CompletableFuture<ElementHandle> createElementFuture(String locatorKey, int timeoutInSeconds) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				Locator element = page.locator(getLocatorMap().get(locatorKey));
				element.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE)
						.setTimeout(timeoutInSeconds * 1000));

				if (element.isVisible()) {
					return element.elementHandle();
				} else {
					log.info("Element is not visible for selector: " + locatorKey);
					return null;
				}
			} catch (Exception e) {
				log.info("Element did not appear for selector: " + locatorKey);
				return null;
			}
		});
	}

	public void scrollElementToVisibility(String locatorKey) throws InterruptedException {
		try {
			log.debug("Going to scroll to the element " + locatorKey);
			waitForElementToBeVisible(locatorKey, elementVisibilityMaxTimeout);
			page.locator(getLocatorMap().get(locatorKey)).scrollIntoViewIfNeeded();
		} catch (Exception e) {
			log.debug("Element " + getInfoMap().get(locatorKey) + " could not be scrolled to visibility. Locator is: "
					+ getLocatorMap().get(locatorKey));
		}

	}

	public static void clearTextField(String selector) {
		// Focus the input field
		page.click(selector);
		// Select all text and delete it
		page.press(selector, "Control+A");
		page.press(selector, "Delete");
	}
/////////////////Shadow Element//////////////open///////////
	/**

	 * This method returns a locator that matches the given text exactly.

	 *

	 * @param text The text to match.

	 * @return The locator object.

	 */

	public Locator getByText(String text) {

		return page.getByText(text, new Page.GetByTextOptions().setExact(true));

	}

	/**

	 * This method returns a locator that matches the given label.

	 *

	 * @param label The label to match.

	 * @return The locator object.

	 */

	public Locator getByLabel(String label) {

		return page.getByLabel(label);

	}

	/**

	 * This method returns a locator that matches the given placeholder.

	 *

	 * @param placeholder The placeholder to match.

	 * @return The locator object.

	 */

	public Locator getByPlaceholder(String placeholder) {

		return page.getByPlaceholder(placeholder);

	}

	/**

	 * This method returns a locator that matches the given testId.

	 *

	 * @param testId The placeholder to match.

	 * @return The locator object.

	 */

	public Locator getByTestID(String testId) {

		return page.getByTestId(testId);

	}

	/**

	 * This method returns a locator that matches the given tag and text.

	 *

	 * @param tag  The tag to match.

	 * @param text The text to match.

	 * @return The locator object.

	 */

	public Locator getElementByTagAndText(String tag, String text) {

		return page.locator(tag, new Page.LocatorOptions().setHasText(text));

	}

	/**

	 * Performs a getByRole action on the page based on the provided role and text.

	 * <p>

	 * This method takes a role and text as input, and uses a switch case to

	 * determine

	 * which type of getByRole action to perform. The method returns a Locator

	 * object

	 * that matches the specified role and text, or null if no match is found.

	 * <p>

	 * Supported roles:

	 * - "button": Finds a button element with the specified text.

	 * - "dropdown": Finds a dropdown option with the specified text.

	 * - "link": Finds a link element with the specified text.

	 *

	 * @param role The AriaRole to search for (e.g. "button", "dropdown", "link").

	 * @param text The text to search for within the specified role.

	 * @return A Locator object that matches the specified role and text, or null if

	 *         no match is found.

	 */

	public Locator performGetByRole(String role, String text) {

		// Define the locator options

		Page.GetByRoleOptions options = new Page.GetByRoleOptions().setExact(true);

		// Use a switch case to perform the getByRole action

		Locator locator = null;

		switch (role) {

			case "button":

				locator = page.getByRole(AriaRole.BUTTON, options.setName(text));

				break;

			case "dropdown": // use this to select item from maui-list dropdown

				locator = page.getByRole(AriaRole.OPTION, options.setName(text)).getByRole(AriaRole.LISTITEM);

				break;

			case "checkbox": // use this to select item from maui-list dropdown

				locator = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(text));

				break;

			case "link":

				locator = page.getByRole(AriaRole.LINK, options.setName(text));

				break;

			default:

				System.out.println("Unsupported AriaRole: " + role);

		}

		return locator;

	}
	public Locator getByTitle(String title){
		return page.getByTitle(title);
	}
/////////////////////////Shadow Element////////////////end/////////
	// --------------------------------------------------------
	// All functions below this section are related to Shadow DOM
	// --------------------------------------------------------

	public Locator getShadowDomElement(String locatorUsed) throws InterruptedException {
		int retries = elementShadowDomVisibilityMaximumRetry; // Number of retries
		while (retries > 0) {
			try {
				page.waitForLoadState(LoadState.DOMCONTENTLOADED);
				page.waitForLoadState(LoadState.LOAD);

				// Try locating the element in the main DOM
				Locator elementInMainDom = page.locator(locatorUsed);
				if (elementInMainDom.count() > 0) {
					log.debug("Element found in Non Shadow DOM Structure");
					elementInMainDom.waitFor(
							new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(1000));

					if (elementInMainDom.isVisible()) {
						log.debug("Element visible in Non Shadow DOM Structure");
						return elementInMainDom; // Element found in the main DOM
					}
				}
			} catch (Exception e) {
				log.debug("Error locating element in main DOM: " + e.getMessage());
			}

			// Start by locating shadow hosts only if the element is not found in the main
			// DOM
			try {
				Locator shadowHosts = page.locator("maui-select, maui-label, maui-option");

				// Check if any shadow hosts are found
				if (shadowHosts.count() > 0) {
					for (int i = 0; i < shadowHosts.count(); i++) {
						Locator shadowHost = shadowHosts.nth(i);

						try {
							// Get the shadow root and locate the element inside
							Locator shadowElement = shadowHost.locator(locatorUsed);

							if (shadowElement.count() > 0) {
								log.debug("Element found in shadow DOM for host: "
										+ shadowHost.evaluate("el => el.outerHTML"));
								return shadowElement; // Element found in the shadow DOM
							}
						} catch (Exception e) {
							log.debug("Error locating element in shadow root for host: " + e.getMessage());
						}
					}
				} else {
					log.debug("No shadow hosts found.");
				}
			} catch (Exception e) {
				log.debug("Error during shadow DOM iteration: " + e.getMessage());
			}

			// Decrease the retry counter and log retry attempt
			retries--;
			if (retries > 0) {
				log.debug("Retrying to locate the element. Attempts remaining: " + retries);
				HardWait(1);
			} else {
				log.debug("Element not found in both DOM and shadow DOMs after retrying.");
			}
		}

		return null; // Element not found after retries
	}

}
