package com.e2e.reports;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.e2e.utilities.Configurations;

public class ExtentManager {

	private static ExtentReports extent;
	public static String screenshotFolderPath;

	public static ExtentReports getInstance(String reportPath) {
		if (extent == null) {
			// generate report folder
			String fileName = "Report.html";
			String folderName;
			Date d = new Date();

//			if ((Configurations.RunOnBrowserStack).equals("Y")) {
//				folderName = d.toString().replace(":", "_") + "_" + System.getProperty("browser");
//			} else {
//				folderName = d.toString().replace(":", "_") + "_chrome";
//			}

				folderName = d.toString().replace(":", "_") + "_chrome";

			// directory of the report folder
			new File(reportPath + folderName + "/screenshots").mkdirs();

			reportPath = reportPath + folderName + "/";
			screenshotFolderPath = reportPath + "screenshots/";
			// System.out.println(reportPath+fileName);
			createInstance(reportPath + fileName);
		}

		return extent;
	}

	public static ExtentReports createInstance(String fileName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		// htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		// htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle("Reports");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Reports - Automation Testing");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		return extent;
	}
}