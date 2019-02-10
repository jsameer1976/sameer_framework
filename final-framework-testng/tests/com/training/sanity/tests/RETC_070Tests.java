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
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AdminPOM;
import com.training.pom.LoginPOM;
import com.training.pom.PropertiesPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RETC_070Tests {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private AdminPOM adminPOM;
	
	private PropertiesPOM propertiesPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver);
		adminPOM = new AdminPOM(driver);
		propertiesPOM=new PropertiesPOM(driver);
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
	public void validRETC_070Test() {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		propertiesPOM.clickPropertiesLink();
		Assert.assertTrue(propertiesPOM.isPropertiesLinkOptionsDisplayed("All Properties"));
		Assert.assertTrue(propertiesPOM.isPropertiesLinkOptionsDisplayed("Add New"));
		Assert.assertTrue(propertiesPOM.isPropertiesLinkOptionsDisplayed("Features"));
		Assert.assertTrue(propertiesPOM.isPropertiesLinkOptionsDisplayed("Regions"));
		Assert.assertTrue(propertiesPOM.isPropertiesLinkOptionsDisplayed("Properties Settings"));
		propertiesPOM.clickPropertiesLinkOption("Features");
		Assert.assertTrue(propertiesPOM.isAddNewFeaturePageDisplay());
		propertiesPOM.enterPropertyName("New_Launches1");
		propertiesPOM.enterPropertyDescription("New Launches of villas, apartments, flats");
		propertiesPOM.enterSlug("launch");
		propertiesPOM.clickAddFeatureButton();
		
		
		propertiesPOM.clickPropertiesLinkOption("Add New");
		Assert.assertTrue(propertiesPOM.isAddNewPropertyPageDisplay());
		propertiesPOM.enterPropertyTitle("prestige_Test");
		propertiesPOM.enterPropertyContent("home town");
		adminPOM.clickCheckListOption("Test");
		adminPOM.clickPublishButton();
		String publishPostMessage = adminPOM.getPublishPostMessage();
		Assert.assertTrue(publishPostMessage.contains("Post published. View post"));
		System.out.println(publishPostMessage);
		propertiesPOM.clickOnLogout();
		propertiesPOM.clickRealEstateIcon();
		propertiesPOM.searchRealEstateProperty("prestige_Test");
		Assert.assertTrue(propertiesPOM.isSearchResultDisplayed("prestige_Test"));
		propertiesPOM.clickSearchResult("prestige_Test");
		String parentWindow=driver.getWindowHandle();
		switchWindow(parentWindow);
		propertiesPOM.isPropertyDisplayed("prestige_Test");

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
