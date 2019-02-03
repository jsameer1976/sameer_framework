package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.ExpectedExceptions;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AdminPOM;
import com.training.pom.ApartmentPOM;
import com.training.pom.ContactPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RETC_037Tests {
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
	public void validRETC_037Test() {
		contactPage.clickPlots();
		Assert.assertTrue(apartmentPage.isYourHomeDisplayed());
		apartmentPage.enterSearch("Electronic City");
		apartmentPage.selectPropertyType("Villas");
		apartmentPage.selectTaxRegion("Central Bangalore");
		apartmentPage.clickSearchPropertyButton();

		String pageTitle = contactPage.getPageTitle();
		Assert.assertTrue(pageTitle.contains("Plots"));
		contactPage.enterSearchInput("Nullam hendrerit apartment", 1);
		contactPage.clickSearchResult("Nullam hendrerit Apartments");
		String parentWindow = driver.getWindowHandle();
		switchWindow(parentWindow);
		boolean actual = contactPage.isSearchResultPage("Nullam hendrerit Apartments - Overview");
		Assert.assertTrue(actual);
		driver.switchTo().window(parentWindow);
		contactPage.closeSearch(1);
		contactPage.clickDropUsALine();
		Assert.assertTrue(contactPage.isContactForm());
		contactPage.enterName("selenium");
		contactPage.enterEmail("selenium@gmail.com");
		contactPage.enterSubject("apartment");
		contactPage.enterComments("looking for apartment");
		contactPage.clickSendButton();

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
