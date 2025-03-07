package com.e2e.utils;

import static com.e2e.utils.Base64EncryptionUtil.decryption;

import java.io.IOException;
import java.util.Base64;

import org.junit.Assert;

import com.e2e.config.TestDataConfiguration;

public class Base64EncryptionUtil {

	static String Data = "";
	public static ScenarioContext scenarioContext = ScenarioContext.Singleton();

	public static void main(String[] args) {
		encryption();
	}

	public static String decryption(String encodeddata) throws IOException {
		byte[] decodedBytes = Base64.getDecoder().decode(encodeddata.getBytes());
		return new String(decodedBytes);
	}

	public static void encryption() {
		byte[] encodedString = Base64.getEncoder().encode(Data.getBytes());
		System.out.println("Encode password:" + new String(encodedString));
	}

	public static String encryptionData(String inputData) {
		byte[] encodedString = Base64.getEncoder().encode(inputData.getBytes());
		System.out.println("Encode password:" + new String(encodedString));
		return new String(encodedString);
	}

	public static String readDecryptedData(String key) {
		String value = null;
		String decrypted_Value = null;
		key = key.trim();

		String testDataFileName = scenarioContext.getContext("Static_TestData_FileName");
		try {

			if (testDataFileName.toLowerCase().contains("mmg")) {
				value = PropertyReader.getValueForKey(key, TestDataConfiguration.MMGStaticTestDataConfigPath);
			}
			if (testDataFileName.toLowerCase().contains("desktop")) {
				value = PropertyReader.getValueForKey(key, TestDataConfiguration.DesktopStaticTestDataConfigPath);
			}

			decrypted_Value = decryption(value);

		} catch (IOException e) {
			Assert.fail("Static test data is missing for" + key);
		}
		return decrypted_Value;

	}
}