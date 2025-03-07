package com.e2e.reportsfreemaker;

import java.util.Date;
import java.util.concurrent.TimeUnit;

//import com.genz.xray.ObjTestCoverage;
//import com.genz.xray.ObjTestExecution;
//import com.lh.runner.JunitRunner;
import com.e2e.runner.TestRunner;


/**
 * Entry class for the report generator
 */
public class ReportBuilder {

	public static void generateReport() throws Exception {

		TimeUnit.SECONDS.sleep(5);

		Date currentDate = new Date();
//		te.setExecutionDate(currentDate.toString());
		TestRunner.folderNameReport = "LH_" + currentDate.toString().replace(":", "_").replace(" ", "_");

		ReportGenerator rg = new ReportGenerator();

		rg.generateReport(TestRunner.PATH_TO_CUCUMBER_REPORT,TestRunner.folderNameReport);

	}
}
