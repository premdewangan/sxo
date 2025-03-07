package com.e2e.stepdef;

import com.e2e.config.Configuration;
import com.e2e.pages.BasePage;
import com.e2e.utils.DriverManager;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;

public class GenericHooks extends BasePage {

	@Before
	public void before(Scenario scenario) {
		DriverManager.setUp();
		System.out.println("Scenario name: " + scenario.getName());
		setScenario(scenario);
	}

	@After
	public void after(Scenario scenario) {
		if (Configuration.TakeScreenshotAtEnd && !isScreenshotCapturedAfterFailure) {
			takeScreenshot();
		}
		DriverManager.tearDown();
	}

	@AfterStep
	public void afterStep(Scenario scenario) {
		if (Configuration.TakeScreenshotAfterEachStep && !isScreenshotCapturedAfterFailure) {
			takeScreenshot();
		}
	}
}