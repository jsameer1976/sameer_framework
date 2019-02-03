package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.ApartmentPOM;
import com.training.pom.ContactPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RETC_008Tests {
	private WebDriver driver;
	private String baseUrl;
	private ContactPOM contactPage;
	private ApartmentPOM apartmentPage;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		contactPage = new ContactPOM(driver);
		apartmentPage = new ApartmentPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("resources//others.properties");
		properties.load(inStream);
		System.out.println(properties);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

	@Test
	public void validRETC_008Test() {
		contactPage.mouseHover("apartments");
		ArrayList<String> locations = contactPage.getLocations("apartments");
		// System.out.println(locations);
		Assert.assertTrue(locations.contains("Central Bangalore"));
		Assert.assertTrue(locations.contains("East Bangalore"));
		Assert.assertTrue(locations.contains("North Bangalore"));
		Assert.assertTrue(locations.contains("South Bangalore"));
		Assert.assertTrue(locations.contains("West Bangalore"));
		contactPage.clickApartments();
		Assert.assertTrue(apartmentPage.isYourHomeDisplayed());
		apartmentPage.clickImage("Donec quis");
		Assert.assertTrue(apartmentPage.isImagePageDisplayed("Donec quis"));
		apartmentPage.enterSalePrice("400000");
		apartmentPage.enterDownpayment("20000");
		apartmentPage.enterLoanTerms("20");
		apartmentPage.enterInterest("7.25");
		apartmentPage.clickCalculateButton();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String calcOutput = apartmentPage.getCalculatorOutput();
		System.out.println(calcOutput);
		Assert.assertTrue(calcOutput.contains("3003.43 Rs"));

	}

	public void switchWindow(String parentWindow) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			if (!window.equalsIgnoreCase(parentWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}
	}
}
