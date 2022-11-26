package com.test.kerja.sqa.sqawebjavabddtestng.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FireFox implements DriverStrategy {

	public WebDriver setStrategy() {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.geckodriver.driver", "D:/firefox/geckodriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}

}
