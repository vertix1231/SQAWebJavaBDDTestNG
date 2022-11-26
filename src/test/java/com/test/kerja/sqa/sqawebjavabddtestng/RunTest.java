package com.test.kerja.sqa.sqawebjavabddtestng;

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

@ContextConfiguration(classes = AutomationFrameworkConfiguration.class)
public class RunTest extends AbstractTestNGSpringContextTests {
	private static WebDriver driver;
	private ExtentTest extentTest;
	private static ExtentReports extentReports = new ExtentReports();
	private static ExtentSparkReporter htmlreporter = new ExtentSparkReporter("src/test/resources/reporttest_testkerja.html");
	private static ConstantsParam constantsParam;
	private static LoginPage loginPage;
	private static AdminPage adminPage;

	@Autowired
	ConfigurationProperties configurationProperties;

	@BeforeTest
	public void setup() {
//		loginPage = new LoginPage();
		extentReports.attachReporter(htmlreporter);
		ScenarioTestCases[] tests = ScenarioTestCases.values();
		extentTest = extentReports.createTest(tests[UtilsTest.scenariotestcount].getScenarioTestName());
		UtilsTest.scenariotestcount++;
	}
	
	@Test(priority = 0)
	public void workZero() {
		DriverSingleton.getInstance(configurationProperties.getBrowser());
		driver = DriverSingleton.getDriver();
		driver.get(ConstantsParam.URL_MAIN_WEB);
		extentTest.log(Status.PASS, "Navigation to : " + ConstantsParam.URL_MAIN_WEB);
	}
	
	@Test(priority = 1)
	public void workOne() {
		loginPage = new LoginPage();
		loginPage.goToSignin("Admin", "admin123");
		extentTest.log(Status.PASS, "input user and password to login to web pass");
//		if (Status.PASS != null) {
//			System.out.println("input user and password to login to web pass");
//			extentTest.log(Status.PASS, "input user and password to login to web pass");
//
//		} else {
//			System.out.println("input user and password to login to web fail");
//			try {
//				extentTest.fail("input user and password to login to web fail",
//						MediaEntityBuilder.createScreenCaptureFromPath(failcaptureScreen()).build());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//		}
	}
	
	@Test(priority = 2)
	public void workTwo() {
		adminPage = new AdminPage();
		adminPage.goToAdmin();
		if(Status.PASS != null) {
			extentTest.log(Status.PASS, "Click admin menu");
		}else if(Status.FAIL != null){
			extentTest.log(Status.FAIL, "Click admin menu");
		}
//		if (Status.PASS != null) {
//			System.out.println("input user and password to login to web pass");
//			extentTest.log(Status.PASS, "input user and password to login to web pass");
//
//		} else {
//			System.out.println("input user and password to login to web fail");
//			try {
//				extentTest.fail("input user and password to login to web fail",
//						MediaEntityBuilder.createScreenCaptureFromPath(failcaptureScreen()).build());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//		}
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
