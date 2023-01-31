package com.test.kerja.sqa.sqawebjavabddtestng;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.test.kerja.sqa.sqawebjavabddtestng.driver.DriverSingleton;
import com.test.kerja.sqa.sqawebjavabddtestng.utils.ConstantsParam;
import com.test.kerja.sqa.sqawebjavabddtestng.utils.ScenarioTestCases;
import com.test.kerja.sqa.sqawebjavabddtestng.utils.UtilsTest;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    public static WebDriver driver;
    public static ExtentTest extentTest;
    public static ExtentReports reports = new ExtentReports("target/extent-reports.html");

    @Before
    public void setUp() {
        DriverSingleton.getInstance(ConstantsParam.CHROME);
        driver = DriverSingleton.getDriver();
        ScenarioTestCases[] tests =  ScenarioTestCases.values();
        extentTest = reports.startTest(tests[UtilsTest.scenariotestcount].getScenarioTestName());
        UtilsTest.scenariotestcount++;
    }

    @After
    public void closeObject() {
        reports.endTest(extentTest);
        reports.flush();
    }

    @AfterStep
    public void getResultStatus(Scenario scenario) throws IOException {
        if(scenario.isFailed()) {
            String screenshotPath = UtilsTest.getScreenshot(driver, scenario.getName().replace(" ", "_"));
            extentTest.log(LogStatus.FAIL, scenario.getName()+"\n"
                    +extentTest.addScreenCapture(screenshotPath));;
        }
    }

    @AfterAll
    public static void closeBrowser() {
        delay(3);
        DriverSingleton.closeObjectInstance();
    }

    public static void delay(int detik) {
        try {
            Thread.sleep(1000*detik);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void scroll(int vertical) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+vertical+")");
    }


}
