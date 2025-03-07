package com.e2e.config;

public class Configuration {
//
public static final String ExecutionEnvnmt = "QA";
// Valid values: QA, STAGE, PROD
public static final String ProjectName = "SXO_CartCraft";
 public static final String ReleaseName = "Regression";
 public static final String Component = "Login";
	public static final String reportPath = "./Reports/";

	// Base URL for the application
	public static final String LH_DESKTOP_PREPROD = "https://stage-www.lufthansa.com/es/en/homepage";
	public static final String LX_DESKTOP_PREPROD = "https://stage-www.swiss.com/ch/en/homepage";
	public static final String OS_DESKTOP_PREPROD = "https://stage-www.austrian.com/es/en/homepage";
	public static final String SN_DESKTOP_PREPROD = "https://stage-www.brusselsairlines.com/fr/en/homepage";
//	public static final String LH_DESKTOP_INT = "https://stage-www.lufthansa.com/es/en/homepage";

	public static final String LH_DESKTOP_INT = "https://qa-www.lufthansa.com/de/en/homepage";
	public static final String LX_DESKTOP_INT = "https://qa-www.swiss.com/de/en/homepage";
	public static final String OS_DESKTOP_INT = "https://qa-www.austrian.com/de/en/homepage";
	public static final String SN_DESKTOP_INT = "https://qa-www.brusselsairlines.com/de/en/homepage";

	public static final String LH_CHATBOT_INT = "https://qa-www.lufthansa.com/digitalassistant/support/webchat.html?e2e=e2eteam4";
	public static final String LX_CHATBOT_INT = "https://qa-www.swiss.com/digitalassistant/support/webchat.html?e2e=e2eteam4";
	public static final String OS_CHATBOT_INT = "https://qa-www.austrian.com/digitalassistant/support/webchat.html?e2e=e2eteam4";

	public static final String LH_MMB_INT = "https://qa-www.lufthansa.com/de/en/homepage";
	public static final String LH_MMB_PREPROD = "https://qa-www.lufthansa.com/de/en/homepage";
//	public static final String SN_MMB_PREPROD = "https://shop-uat.brusselsairlines.com/sn/booking/manage-booking/confirmation"
//	public static final String LH_MMB_PREPROD = "https://shop-uat.lufthansa.com/lh/booking/manage-booking/retrieve";
	public static final String LX_MMB_PREPROD = "https://shop-uat.lufthansa.com/lh/booking/manage-booking/retrieve";
	public static final String OS_MMB_PREPROD = "https://shop-uat.lufthansa.com/lh/booking/manage-booking/retrieve";
	public static final String SN_MMB_PREPROD = "https://shop-uat.lufthansa.com/lh/booking/manage-booking/retrieve";

	public static final String MMG_PREPROD = "https://www-uat1.miles-and-more.com/de/en.html";

	public static final String YOP_MAIL = "https://yopmail.com/";
	public static final String FIFTEEN_BELOW = "https://uat-eu.15below.travel/account/sign-in?ReturnUrl=%2F";

	public static final String LH_ONCE_INT = "https://once-int.lufthansa.com/connect/main-app?APPID=LHG_ONLINE&TENANT=LH&LANGUAGE=en&COUNTRY=DE&MODE=PORTAL";
	public static final String LX_ONCE_INT = "https://once-int.swiss.com/connect/main-app?APPID=LHG_ONLINE&COUNTRY=CH&LANGUAGE=EN&TENANT=LX&MODE=PORTAL";
	public static final String SN_ONCE_INT = "https://once-int.brusselsairlines.com/connect/main-app?APPID=LHG_ONLINE&COUNTRY=BE&LANGUAGE=EN&TENANT=SN&MODE=PORTAL";
	public static final String OS_ONCE_INT = "https://once-int.austrian.com/connect/main-app?APPID=LHG_ONLINE&COUNTRY=AT&LANGUAGE=EN&TENANT=OS&MODE=PORTAL";

	public static final String LH_GOODFORTRAIN_INT = "https://qa-www.lufthansa.com/de/en/good-for-train";
	public static final String LH_GOODFORTRAIN_PREPROD = "https://stage-www.lufthansa.com/de/en/good-for-train";

//	public static final String lufthansaUrl ="https://www.lufthansa.com";

	public static final String baggageRegulationUrl = "https://www.lufthansa.com/xx/en/carry-on-baggage?intcamp=bot";

	// Timeouts
	public static final int PAGE_LOAD_TIMEOUT_SECONDS = 20;
	public static final int ELEMENT_WAIT_TIMEOUT_SECONDS = 20;
	public static final int SCRIPT_EXECUTION_TIMEOUT_SECONDS = 20;

	// Test data file paths
	public static final String TEST_DATA_PATH = "path/to/test/data";

	// Environment-specific configurations (if applicable)
	public static final String ENVIRONMENT = "INT"; // Options: "INT", "PREPROD", "PROD", "AGATE" etc.
//	public static final String ENVIRONMENT = "AGATE";
	// Xray
	public static final String ENABLE_XRAY = "N";
	public static final String XrayConfig = "./src/test/java/com/e2e/xray/xray_config.properties";

	// screenshot
	public static final Boolean TakeScreenshotAfterEachStep = false;
	/*
	 * set value of takeScreenshotAtEnd to 'false' if takeScreenshotAfterEachStep is
	 * set to 'true' otherwise 2 screenshots will get captured at the end step
	 */
	public static final Boolean TakeScreenshotAtEnd = false;

	// cucumber-report config
	public final static String ReportConfigPath = "./src/test/resources/CucumberReportSetup/reportconfig.properties";

	// Other configurations...
    //Agate
	//Amadeus Agate Login Environment
	public static final String LH_PORTAL_AGATE = "https://uat.digital.airline.amadeus.com/1a/agate/shooter/configLHEmbedded::LH";
	public static final String LX_PORTAL_AGATE = "https://uat.digital.airline.amadeus.com/1a/agate/shooter/configLXEmbedded::LX";
	public static final String OS_PORTAL_AGATE = "https://uat.digital.airline.amadeus.com/1a/agate/shooter/configOSEmbedded::OS";
	public static final String SN_PORTAL_AGATE = "https://uat.digital.airline.amadeus.com/1a/agate/shooter/configSNEmbedded::SN";



	public static final String Airline_Tenant = "https://pdt.accounts.amadeus.com/LoginService/authorizeAngular?service=BCM&response_type=code&scope=&nonce=406e69cb-d6fb-49ba-ba18-caa5a31556b6&client_id=LQI4MMU2lWMLyBh6&redirect_uri=https%3A%2F%2Ftest.ui-api.business-configuration-manager.amadeus.com%2Fbcm%2Frs%2Fauth%2Fv1%2Fagate%2Flogin%3Fredirect-url%3Dhttps%3A%2F%2Fuat.digital.airline.amadeus.com%2F1a%2Fagate%2Fshooter%2FconfigLHEmbedded%3A%3ALH#/login";
	public static final String agateUsername = "VTE5MjI5Nw";
	public static final String agatePassword = "Tm92QDIwMjQ";
}
