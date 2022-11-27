package com.test.kerja.sqa.sqawebjavabddtestng.utils;

public enum ScenarioTestCases {
	SCT1("Scenario web orangehrm");
	
	private String scenarioTestName;

	ScenarioTestCases(String value){
		this.scenarioTestName  = value;
	}
	
	public String getScenarioTestName() {
		return scenarioTestName;
	}
	
	
	
}
