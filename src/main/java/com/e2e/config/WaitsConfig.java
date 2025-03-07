package com.e2e.config;

public class WaitsConfig {

	// Timeouts
	public static final int PAGE_LOAD_TIMEOUT_SECONDS = 60;
	public static final int ELEMENT_WAIT_TIMEOUT_SECONDS = 120;
	public static final int ELEMENT_MINWAIT_TIMEOUT_SECONDS = 5;
	public static final int ELEMENT_POLLING_WAIT_SECONDS = 3;
	public static final int SCRIPT_EXECUTION_TIMEOUT_SECONDS = 30;

	public static final int MAXIMUM_RETRY_FOR_VISIBILITY = 5;
	// Number of retries to look for Shadow Host with 1 second wait each try
	public static final int MAXIMUM_RETRY_FOR_SHADOWDOM = 1;

	// Other configurations...

}
