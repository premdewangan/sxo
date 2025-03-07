package com.e2e.dockspace;

import com.e2e.utils.DockspacePageExporter;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FetchDynamicTestData {

    private static final Logger logger = Logger.getLogger(FetchDynamicTestData.class);

    public static void main(String args[]) {
        // Load properties from file
        Properties configFile = loadPropertiesFromFile("./src/test/java/com/e2e/dockspace/config.properties");

        saveDatatoProperitiesFile(configFile, "fetch_chatbotData", "chatbot_pageID", "chatbot_outputFile");
        saveDatatoProperitiesFile(configFile, "fetch_MMGData", "MMG_pageID", "MMG_outputFile");
        saveDatatoProperitiesFile(configFile, "fetch_desktopData", "Desktop_pageID", "Desktop_outputFile");
        saveDatatoProperitiesFile(configFile, "fetch_mobileData", "Mobile_pageID", "Mobile_outputFile");
    }

    private static Properties loadPropertiesFromFile(String filePath) {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        } catch (IOException e) {
            logger.error("Error loading properties from file: " + e.getMessage());
        }
        return properties;
    }

    private static void saveDatatoProperitiesFile(Properties configFile, String fetchKey, String pageIdKey, String outputFileKey) {
        String fetchDataFlag = configFile.getProperty(fetchKey);

        if ("y".equalsIgnoreCase(fetchDataFlag)) {
            String pageID = configFile.getProperty(pageIdKey);
            String outputFile = configFile.getProperty(outputFileKey);

            // Override properties with system properties
            overrideProperties(configFile);

            DockspacePageExporter.exportTabletoPropertiesFile(configFile.getProperty("baseURI"),
                    configFile.getProperty("accessToken"), pageID, outputFile);
        }
    }

    private static void overrideProperties(Properties properties) {
        System.getProperties().forEach((key, value) -> {
            if (key instanceof String && value instanceof String) {
                properties.setProperty((String) key, (String) value);
            }
        });
    }
}
