package com.test.kerja.sqa.sqawebjavabddtestng.runtestngspecial;

import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
features = {"src/test/resources/features/1.0_Login.feature","src/test/resources/features/1.1_Admin.feature"},
glue = {"com.test.kerja.sqa.sqawebjavabddtestng.runtestngspecial"})
public class MainRunner extends AbstractTestNGCucumberTests {
	
	@Test
	public void test() {
		
	}
}
