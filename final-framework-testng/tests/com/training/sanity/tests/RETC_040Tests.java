package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AdminPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RETC_040Tests {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private AdminPOM adminPage;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver);
		adminPage = new AdminPOM(driver);
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
	public void validRETC_040Test() {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		adminPage.clickPostLink();
		Assert.assertTrue(adminPage.isPostLinkOptionsDisplayed("Add New"));
		Assert.assertTrue(adminPage.isPostLinkOptionsDisplayed("All Posts"));
		Assert.assertTrue(adminPage.isPostLinkOptionsDisplayed("Categories"));
		Assert.assertTrue(adminPage.isPostLinkOptionsDisplayed("Tags"));
		adminPage.clickPostLinkOption("Categories");
		adminPage.enterCategoryName("New Launches");
		adminPage.enterCategoryDescription("New Launches of villas, apartments, flats");
		adminPage.enterSlug("launch");
		adminPage.clickAddCategoryButton();
		adminPage.clickPostLinkOption("Add New");
		Assert.assertTrue(adminPage.isAddNewPostPageDisplay());
		adminPage.enterPostTitle("New Launches");
		adminPage.enterPostDescription("New Launch in Home");
		adminPage.clickCheckListOption("New Category Name");
		adminPage.clickPublishButton();
		String publishPostMessage = adminPage.getPublishPostMessage();
		Assert.assertTrue(publishPostMessage.contains("Post published. View post"));
		System.out.println(publishPostMessage);

	}
}
