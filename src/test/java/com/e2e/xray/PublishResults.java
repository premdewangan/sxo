package com.e2e.xray;

import com.e2e.config.Configuration;
import com.e2e.utils.EnvironmentSelector;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.dh.config.PropertiesHandler;
import com.dh.xray.Xray;

public class PublishResults {

	public static Boolean createNewTestExec = PropertiesHandler.isCreateNewTestExecutionEnabled();
	public static String projectKey = PropertiesHandler.getXrayProjectKey();
	public static String jsonReportPath = PropertiesHandler.getReportJsonFolder();
	public static String testPlanID = PropertiesHandler.getXrayTestPlanKey();
	public static String testExecID = PropertiesHandler.getXrayTestExecutionKey();

	public static void publishResultsToTestExecutonID() {
		String enableXray = System.getProperty("enableXray", Configuration.ENABLE_XRAY);
		if (enableXray.equalsIgnoreCase("Y")) {
			if (createNewTestExec.equals(false)) {
				Xray.uploadCucumberResultsToJIRA(projectKey, jsonReportPath + "cucumber.json");
			} else {
				testExecID = createNewTestExecWithResults(testPlanID);
			}
		} else {
			System.out.println("Xray is not enabled");
		}
	}

	public static String createNewTestExecWithResults(String testPanID) {
		String testExecID = null;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // Format the timestamp as a string
        String timestampAsString = convertTimestampToString(timestamp);
		try {
			testExecID = Xray.uploadResults2NewTstExec(testPanID, jsonReportPath, "MigratedTests-Execution "+timestampAsString,
					"Execution Results", EnvironmentSelector.getEnvironment());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("testExecId: "+ testExecID);
		return testExecID;

	}
	
	private static String convertTimestampToString(Timestamp timestamp) {
        // Create a SimpleDateFormat object with the desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Use the format method to convert the timestamp to a string
        return dateFormat.format(new Date(timestamp.getTime()));
    }

}
