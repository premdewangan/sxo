package com.e2e.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class DockspacePageExporter {

	private static final Logger logger = Logger.getLogger(DockspacePageExporter.class);

	public static void exportTabletoPropertiesFile(String baseURI, String token, String pageID, String outputFile) {
		// Set the base URI for the API
		RestAssured.baseURI = baseURI;

		// Replace "YOUR_ACCESS_TOKEN" with the actual access token
		String accessToken = token;

		// Make the GET request with the access token in the header
		Response response = RestAssured.given().header("Authorization", "Bearer " + accessToken)
				.param("expand", "body.storage").get(pageID);

		// Extract the content value from the response
		String contentValue = response.jsonPath().getString("body.storage.value");

		try {
			convertToProperties(contentValue, outputFile);
		} catch (IOException e) {
			logger.error("Error converting HTML to properties: " + e.getMessage());
		}
	}

	public static void convertToProperties(String inputHtml, String outputFilePath) throws IOException {
		Properties properties = new Properties();
		Document doc = Jsoup.parse(inputHtml);

		Elements dockspaceTableRows = doc.select("table tbody tr");

		for (Element row : dockspaceTableRows) {
			Elements columns = row.select("td");
			if (columns.size() == 2) {
				String key = columns.get(0).select("span").text().replaceAll("\\s", "");
				String value = columns.get(1).select("span").text().replaceAll("\\s", ""); // Remove whitespaces
				properties.setProperty(key, value);
			}
		}

		try (FileWriter fileWriter = new FileWriter(outputFilePath)) {
			properties.store(fileWriter, "Generated from HTML");
			logger.info("Conversion to properties successful. Output file: " + outputFilePath);
		} catch (IOException e) {
			logger.error("Error writing properties to file: " + e.getMessage());
			throw e;
		}
	}
}
