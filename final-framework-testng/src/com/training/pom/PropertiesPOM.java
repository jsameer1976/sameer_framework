package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PropertiesPOM {

	private WebDriver driver;

	public PropertiesPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//li[@id='menu-posts-property']")
	private WebElement PropertyLink;

	@FindBy(xpath = "//a[contains(text(),'Log Out')]")
	private List<WebElement> LogoutLinks;

	@FindBy(xpath = "//h2[contains(text(),'Add New Feature')]")
	private WebElement AddNewFeaturePageTitle;

	@FindBy(xpath = "//li[contains(text(),'Properties')]/..//a")
	private List<WebElement> PropertiesLinkOptions;

	@FindBy(xpath = "//h1[contains(text(),'Add Property')]")
	private WebElement AddNewPropertyTitle;

	@FindBy(xpath = "//input[@id='tag-name']")
	private WebElement PropertyName;

	@FindBy(xpath = "//input[@id='tag-slug']")
	private WebElement Slug;

	@FindBy(xpath = "//textarea[@id='tag-description']")
	private WebElement PropertyDescription;

	@FindBy(xpath = "//input[@id='submit']")
	private WebElement AddFeatureButton;

	@FindBy(xpath = "//input[@id='title']")
	private WebElement PropertyTitle;

	@FindBy(xpath = "//input[@id='publish']")
	private WebElement PublishButton;

	@FindBy(xpath = "//textarea[@id='content']")
	private WebElement PropertyBody;

	@FindBy(xpath = "//a[contains(text(),'Howdy')]")
	private WebElement AdminLogo;

	@FindBy(xpath = "//input[@id='tag-search-input']")
	private WebElement SearchProperty;

	@FindBy(xpath = "//input[@id='search-submit']")
	private WebElement SearchSubmitButton;
		
	@FindBy(xpath = "//a[contains(text(),'Real Estate')]")
	private WebElement RealEstateIcon;
	
	@FindBy(xpath = "//input[@title='Search input']")
	private WebElement SearchForFeatureProperty;
	
	@FindBy(xpath = "//div[@class='proloading']")
	private List<WebElement> SearchLoading;

	@FindBy(xpath = "//div[@class='proclose']")
	private List<WebElement> CloseSearch;
	
	public void clickRealEstateIcon() {
		RealEstateIcon.click();
	}
	
	public void searchRealEstateProperty(String keyword) {
		SearchForFeatureProperty.sendKeys(keyword);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.attributeContains(SearchLoading.get(0), "style", "none"));
	}
	
	public void enterPropertyTitle(String title) {
		PropertyTitle.sendKeys(title);
	}

	public void enterPropertyContent(String desc) {
		PropertyBody.sendKeys(desc);
	}

	public void enterPropertyDescription(String desc) {
		PropertyDescription.sendKeys(desc);
	}

	public void enterPropertyName(String name) {
		PropertyName.sendKeys(name);
	}

	public void enterSlug(String slug) {
		Slug.sendKeys(slug);
	}

	public boolean isAddNewFeaturePageDisplay() {
		return AddNewFeaturePageTitle.isDisplayed();
	}

	public void clickAddFeatureButton() {
		AddFeatureButton.click();
	}

	public void clickPublishButton() {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].click();", PublishButton);
		PublishButton.click();
	}

	public void clickPropertiesLink() {
		PropertyLink.click();
	}

	public boolean isPropertiesLinkOptionsDisplayed(String propertyLinkOption) {
		for (WebElement option : PropertiesLinkOptions) {
			if (option.getText().contains(propertyLinkOption)) {
				return true;
			}
		}
		return false;
	}

	public void clickPropertiesLinkOption(String propertyLinkOption) {
		for (WebElement option : PropertiesLinkOptions) {
			if (option.getText().contains(propertyLinkOption)) {
				option.click();
				break;
			}
		}
	}

	public boolean isAddNewPropertyPageDisplay() {
		return AddNewPropertyTitle.isDisplayed();
	}

	public void clickSearchSubmit() {
		SearchSubmitButton.click();
	}

	public void enterSearchPropertyAdded(String searchText) {
		SearchProperty.sendKeys(searchText);
	}
	
	public void clickOnLogout() {
		Actions action=new Actions(driver);
		action.moveToElement(AdminLogo).build().perform();
		LogoutLinks.get(0).click();
	}
	
	public void clickSearchResult(String keyword) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//a[@class='asl_res_url'] [contains(text(),'" + keyword + "')]")).click();
	}

	public boolean isSearchResultDisplayed(String keyword) {
		return driver.findElement(By.xpath("//a[@class='asl_res_url'] [contains(text(),'" + keyword + "')]")).isDisplayed();
	}
	
	public boolean isPropertyDisplayed(String propertyName) {
		return driver.findElement(By.xpath("//h3[contains(text(),'" + propertyName + "')]")).isDisplayed();
	}
	
	
}
