package com.e2e.reportsfreemaker;

import java.io.File;

import com.e2e.config.Configuration;
import org.apache.log4j.Logger;
import com.e2e.runner.TestRunner;
//import com.e2e.utils.Configuration;
//import com.genz.xray.ObjTestCoverage;
//import com.genz.xray.ObjTestExecution;

import com.github.mkolisnyk.cucumber.reporting.types.consolidated.ConsolidatedItemInfo;

/**
 * Generate predefined reports with predefined values. It a convenience class
 * with predefined values.
 * <ul>
 * <li>Overview</li>
 * <li>Detailed</li>
 * <li>Combined report of Overview and Detailed</li>
 * </ul>
 */
public class ReportGenerator {
	private final static Logger LOGGER = Logger.getLogger(ReportGenerator.class);
	private String OUTPUT_DIRECTORY = Configuration.reportPath;

	/*
	 * This template is not compatible with PDF output. If you want to use PDF, use
	 * the default template(or pass nothing to the report generator)
	 */
	private static final String TEMPLATE_PATH = "./custom_templates/templates.json";

	/**
	 *
	 * @param sourceFile       location of cucumber.json file
	 * @param reportFolderName for appending oe name to the report folder.
	 * @throws Exception
	 */

	public void generateReport(String sourceFile, String reportFolderName)
			throws Exception {

		if (reportFolderName != null && !reportFolderName.isEmpty()) {
			OUTPUT_DIRECTORY = OUTPUT_DIRECTORY + reportFolderName + "/";
		}

		Report overviewReport = new OverviewReport(OUTPUT_DIRECTORY, "CucumberResultsOverview_" + reportFolderName,
				TEMPLATE_PATH, sourceFile);

		String overviewReportHtml = overviewReport.generateReport();

		Report report = new DetailedReport(OUTPUT_DIRECTORY, "CucumberDetailedResults_" + reportFolderName,
				TEMPLATE_PATH, sourceFile, "images/resources/");

		String detailedReportHtml = report.generateReport();

		ConsolidatedItemInfo itemInfo1 = new ConsolidatedItemInfo("Report Overview", overviewReportHtml);
		ConsolidatedItemInfo itemInfo2 = new ConsolidatedItemInfo("Details", detailedReportHtml);
		ConsolidatedItemInfo[] itemInfos = { itemInfo1, itemInfo2 };
		Report overviewDetailedReport = new OverviewDetailedCombinedReport(OUTPUT_DIRECTORY,
				"OverviewDetailedReport_" + reportFolderName, TEMPLATE_PATH, sourceFile, itemInfos);

		String overviewDetailedReportHtml = overviewDetailedReport.generateReport();
		LOGGER.info(overviewDetailedReportHtml);

		File fileOverviewReportHtml = new File(overviewReportHtml);
		fileOverviewReportHtml.delete();
		File fieldetailedReportHtml = new File(detailedReportHtml);
		fieldetailedReportHtml.delete();

	}

	public void addTestCoverageToReport(String reportPathName) {
		System.out.print(reportPathName);
		String t = "./Reports/LH_Thu_Dec_12_17_14_22_CET_2019/OverviewDetailedReport_LH_Thu_Dec_12_17_14_22_CET_2019-.html";

	}
}
