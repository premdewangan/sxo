package com.e2e.utils;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

	private static ScenarioContext single_instance = null;
	public static HashMap<String, String> scenarioContext = new HashMap<>();

	public static ScenarioContext Singleton() {
		if (single_instance == null) {
			single_instance = new ScenarioContext();
		}
		return single_instance;
	}

	public void setContext(String key, String value) {
		scenarioContext.put(key.toString(), value.toString());
	}

	public String getContext(String key) {
		return scenarioContext.get(key.toString());
	}

	public Boolean isContains(String key) {
		return scenarioContext.containsKey(key.toString());
	}

}
