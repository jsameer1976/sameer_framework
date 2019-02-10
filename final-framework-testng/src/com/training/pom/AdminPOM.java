package com.training.pom;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminPOM {
	private WebDriver driver;

	public AdminPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[text()='Posts']")
	private WebElement PostLink;

	@FindBy(xpath = "//ul[@id='categorychecklist']//li//label")
	private List<WebElement> CategoryCheckListOptions;

	@FindBy(xpath = "//h1[text()='Posts']/following-sibling::a[text()='Add New']")
	private WebElement AllPostAddNewButton;

	@FindBy(xpath = "//li[text()='Posts']/..//li")
	private List<WebElement> PostLinkOptions;

	@FindBy(xpath = "//h2[text()='Add New Category']")
	private WebElement AddNewCategoryTitle;

	@FindBy(xpath = "//input[@id='tag-name']")
	private WebElement CategoryName;

	@FindBy(xpath = "//input[@id='tag-slug']")
	private WebElement Slug;

	@FindBy(xpath = "//textarea[@id='tag-description']")
	private WebElement CategoryDescription;

	@FindBy(xpath = "//input[@id='submit']")
	private WebElement AddCategoryButton;

	@FindBy(xpath = "//input[@id='title']")
	private WebElement PostTitle;

	@FindBy(xpath = "//input[@id='publish']")
	private WebElement PostPublishButton;
	
	/*@FindBy(xpath = "//div[@id='publishing-action']")
	private WebElement PostPublishButton;*/

	@FindBy(xpath = "//textarea[@id='content']")
	private WebElement PostBody;

	@FindBy(xpath = "//h1[text()='Add New Post']")
	private WebElement AddNewPostPage;

	@FindBy(xpath = "//input[@id='tag-search-input']")
	private WebElement SearchCategory;

	@FindBy(xpath = "//input[@id='search-submit']")
	private WebElement SearchSubmitButton;

	// p[contains(text(),'Post published.')]
	@FindBy(xpath = "//div[@id='message']")
	private WebElement PublishPostMessage;

	public void enterPostTitle(String title) {
		PostTitle.sendKeys(title);
	}

	public void enterPostDescription(String desc) {
		PostBody.sendKeys(desc);
	}

	public void enterCategoryDescription(String desc) {
		CategoryDescription.sendKeys(desc);
	}

	public void enterCategoryName(String name) {
		CategoryName.sendKeys(name);
	}

	public void enterSlug(String slug) {
		Slug.sendKeys(slug);
	}

	public boolean isCategoryPageDisplay() {
		return AddNewCategoryTitle.isDisplayed();
	}

	public void clickAddCategoryButton() {
		AddCategoryButton.click();
	}

	public void clickPublishButton() {
		PostPublishButton.submit();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Alert alert=driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		PostPublishButton.click();
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='save'][@value='Update']")));
	}

	public void clickPostLink() {
		PostLink.click();
	}

	public boolean isPostLinkOptionsDisplayed(String postLinkOption) {
		for (WebElement option : PostLinkOptions) {
			if (option.getText().contains(postLinkOption)) {
				return true;
			}
		}
		return false;
	}

	public void clickPostLinkOption(String postLinkOption) {
		for (WebElement option : PostLinkOptions) {
			if (option.getText().contains(postLinkOption)) {
				option.click();
				break;
			}
		}
	}

	public boolean isAddNewPostPageDisplay() {
		return AddNewPostPage.isDisplayed();
	}

	public void clickSearchSubmit() {
		SearchSubmitButton.click();
	}

	public void enterSearchCategory(String searchText) {
		SearchCategory.sendKeys(searchText);
	}

	public void clickAddNewButton() {
		AllPostAddNewButton.click();
	}

	public String getPublishPostMessage() {
		return PublishPostMessage.getText();
	}

	public void clickCheckListOption(String chkList) {
		for (WebElement option : CategoryCheckListOptions) {
			if (option.getText().contains(chkList)) {
				option.click();
				break;
			}
		}
	}

}
