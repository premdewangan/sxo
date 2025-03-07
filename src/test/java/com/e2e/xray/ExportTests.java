package com.e2e.xray;

import com.dh.config.PropertiesHandler;
import com.dh.xray.Xray;
import com.e2e.config.Configuration;

public class ExportTests {

	public static void main(String[] args) {
		export();

	}
	
	public static void export() {
		PropertiesHandler.setConfigPath(Configuration.XrayConfig);
		Xray.exportCucumberTestsFromXray();
	}

}
