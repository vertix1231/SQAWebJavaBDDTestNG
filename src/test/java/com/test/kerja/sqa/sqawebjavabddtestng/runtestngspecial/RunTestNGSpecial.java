package com.test.kerja.sqa.sqawebjavabddtestng.runtestngspecial;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.test.kerja.sqa.sqawebjavabddtestng.config.AutomationFrameworkConfiguration;
import com.test.kerja.sqa.sqawebjavabddtestng.driver.DriverSingleton;
import com.test.kerja.sqa.sqawebjavabddtestng.scenariopage.AdminPage;
import com.test.kerja.sqa.sqawebjavabddtestng.scenariopage.LoginPage;
import com.test.kerja.sqa.sqawebjavabddtestng.utils.ConfigurationProperties;
import com.test.kerja.sqa.sqawebjavabddtestng.utils.ConstantsParam;
import com.test.kerja.sqa.sqawebjavabddtestng.utils.ScenarioTestCases;
import com.test.kerja.sqa.sqawebjavabddtestng.utils.UtilsTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@ContextConfiguration(classes = AutomationFrameworkConfiguration.class)
public class RunTestNGSpecial extends AbstractTestNGSpringContextTests {
	private static WebDriver driver;
	private ExtentTest extentTest;
	private static ExtentReports extentReports = new ExtentReports();
	private static ExtentSparkReporter htmlreporter = new ExtentSparkReporter("src/test/resources/reporttest_testkerja.html");
	private static LoginPage loginPage;
	private static AdminPage adminPage;

	@Autowired
	ConfigurationProperties configurationProperties;

	@BeforeTest
	public void setup() {
		extentReports.attachReporter(htmlreporter);
		ScenarioTestCases[] tests = ScenarioTestCases.values();
		extentTest = extentReports.createTest(tests[UtilsTest.scenariotestcount].getScenarioTestName());
		UtilsTest.scenariotestcount++;
	}
	
	@Test(priority = 0)
	@Given("^Go to Main web Login")
	public void workZero() throws IOException {
		DriverSingleton.getInstance(configurationProperties.getBrowser());
		driver = DriverSingleton.getDriver();
		driver.get(ConstantsParam.URL_MAIN_WEB);
		loginPage = new LoginPage();
		if(loginPage.getTxtLoginHighlight().contains(configurationProperties.getLoginhighlightexpected())) {
			extentTest.log(Status.PASS, "Navigation to : " + ConstantsParam.URL_MAIN_WEB);
		}else{
			extentTest.fail("Navigation to : " + ConstantsParam.URL_MAIN_WEB,
					MediaEntityBuilder.createScreenCaptureFromPath(failcaptureScreen()).build());
		}
	}
	
	@Test(priority = 1)
	@When("^input user and password to login to web")
	public void workOne() throws IOException {
		loginPage = new LoginPage();
		if (loginPage.getTxtLoginHighlight().contains(configurationProperties.getLoginhighlightexpected())) {
			loginPage.goToSignin("Admin", "admin123");
			System.out.println("input user and password to login to web pass");
			extentTest.log(Status.PASS, "input user and password to login to web");

		} else {
			System.out.println("input user and password to login to web fail");
			extentTest.fail("input user and password to login to web",
					MediaEntityBuilder.createScreenCaptureFromPath(failcaptureScreen()).build());

		}
	}
	
	@Test(priority = 2)
	@Then("^enter dashboard page")
	public void workTwo() throws IOException {
		loginPage = new LoginPage();
		if (loginPage.getTvdashboard().contains(configurationProperties.getDashboardexpectedtext())) {
			System.out.println("scenario enter dashboard page pass");
			extentTest.log(Status.PASS, "enter dashboard page pass");
		} else {
			System.out.println("scenario enter dashboard page fail");
			extentTest.fail("succesfully enter dashboard page fail",
					MediaEntityBuilder.createScreenCaptureFromPath(failcaptureScreen()).build());
		}
	}
	
	@Test(priority = 3)
	@Given("^Click admin menu")
	public void workThree() throws IOException {
		adminPage = new AdminPage();
		adminPage.goToAdmin();
		if(adminPage.getTxtAdmiTabHighlight().contains(configurationProperties.getAdmintabadminmenu())) {
			System.out.println("scenario Click admin menu pass");
			extentTest.log(Status.PASS, "Click admin menu");
		}else{
			System.out.println("scenario Click admin menu fail");
			extentTest.fail("Click admin menu",
					MediaEntityBuilder.createScreenCaptureFromPath(failcaptureScreen()).build());
		}	
	}
	
	@AfterTest
	public void closeObject() {
		extentReports.flush();
	}
	

	//CAPTURE PHOTO TESTING
		public static String failcaptureScreen() throws IOException {
			TakesScreenshot screen = (TakesScreenshot) driver;
			File src = screen.getScreenshotAs(OutputType.FILE);
			String dest = "C:\\Users\\ACER\\Desktop\\JCCODING\\sqa\\com.test.kerja.sqa.sqawebjavabddtestng\\src\\test\\resources\\failevidence\\"
					+ getcurrentdateandtime() + ".png";
			File target = new File(dest);
			FileUtils.copyFile(src, target);
			return dest;
		}

		public static String getcurrentdateandtime() {
			String str = null;
			try {
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
				Date date = new Date();
				str = dateFormat.format(date);
				str = str.replace(" ", "-").replaceAll("/", "").replaceAll(":", "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return str;
		}
}
