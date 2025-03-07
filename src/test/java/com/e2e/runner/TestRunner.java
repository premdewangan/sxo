package com.e2e.runner;

import com.e2e.reportsfreemaker.ReportBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.dh.config.PropertiesHandler;
import com.e2e.config.Configuration;
import com.e2e.xray.PublishResults;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
		glue = "com.e2e.stepdef", dryRun = false, monochrome = true,
		tags = ("@Pass"), plugin = {"json:target/cucumber.json",
		"html:target/cucumber-reports"})
//		tags = ("@PNR"), plugin = {"pretty","html:target/cucumber-reports/cucumber.html",
//		"json:target/cucumber-reports/cucumber.json",
//		"junit:target/cucumber-reports/cucumber.xml",
//		"rerun:target/rerun.txt"})

public class TestRunner {

	public static final String PATH_TO_CUCUMBER_REPORT ="target/cucumber.json";
	public static String folderNameReport="";

	@BeforeClass
	public static void setup() {
		PropertiesHandler.setConfigPath(Configuration.XrayConfig);
	}

	@AfterClass
	public static void report() throws Exception {
		 ReportBuilder.generateReport();
		System.out.println("Report is generated");
		PublishResults.publishResultsToTestExecutonID();
	}
}
