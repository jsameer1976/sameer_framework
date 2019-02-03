package com.training.pom;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactPOM {
	private WebDriver driver;

	public ContactPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h4[contains(text(),'Contact Form')]")
	private WebElement ContactForm;

	@FindBy(xpath = "//input[@name='name']")
	private WebElement Name;

	@FindBy(xpath = "//input[@name='email']")
	private WebElement Email;

	@FindBy(xpath = "//textarea[@name='id:comments']")
	private WebElement Comments;

	@FindBy(xpath = "//input[@name='subject']")
	private WebElement Subject;

	@FindBy(xpath = "//input[@value='Send']")
	private WebElement SendButton;

	@FindBy(xpath = "//a[contains(text(),'Villas')]")
	private WebElement Villas;

	@FindBy(xpath = "//a[contains(text(),'Apartments')]")
	private WebElement Apartments;

	@FindBy(xpath = "//a[contains(text(),'New Launch')]")
	private WebElement New_Launch;

	@FindBy(xpath = "//a[contains(text(),'Plots')]")
	private WebElement Plots;

	@FindBy(xpath = "//a[contains(text(),'Blog')]")
	private WebElement Blog;

	@FindBy(xpath = "//input[@title='Search input']")
	private List<WebElement> SearchInput;

	@FindBy(xpath = "//div[@class='proloading']")
	private List<WebElement> SearchLoading;

	@FindBy(xpath = "//div[@class='proclose']")
	private List<WebElement> CloseSearch;

	@FindBy(xpath = "//a[contains(text(),'Drop Us a Line')]")
	private WebElement DropUsALine;

	/*
	 * @FindBy(xpath = "//div[@id='titlebar']//h1[@class='page-title']") private
	 * WebElement PageTitle;
	 */

	@FindBy(xpath = "//div[@id='titlebar']")
	private WebElement PageTitle;

	public void mouseHover(String propertyType) {
		Actions action = new Actions(driver);
		if (propertyType.equalsIgnoreCase("apartments")) {
			action.moveToElement(Apartments).build().perform();
		}
	}

	public ArrayList<String> getLocations(String propertyType) {
		String attr = null;
		if (propertyType.equalsIgnoreCase("apartments")) {
			attr = Apartments.getAttribute("data-menu_item");
		}
		ArrayList<String> locations = new ArrayList();
		List<WebElement> elements = driver
				.findElements(By.xpath("//div[contains(@class,'" + attr + "')]//a[@class='wpmm-subcategory']"));
		for (WebElement element : elements) {
			locations.add(element.getText().trim());
		}
		return locations;

	}

	public void closeSearch(int searchNumber) {
		CloseSearch.get(searchNumber).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickSearchResult(String keyword) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//h3//a[contains(text(),'" + keyword + "')]")).click();
	}

	public boolean isSearchResultPage(String keyword) {
		return driver.findElement(By.xpath("//h3[contains(text(),'" + keyword + "')]")).isDisplayed();
	}

	public void enterName(String name) {
		Name.sendKeys(name);
	}

	public void enterEmail(String email) {
		Email.sendKeys(email);
	}

	public void enterComments(String comments) {
		Comments.sendKeys(comments);
	}

	public void enterSubject(String subject) {
		Subject.sendKeys(subject);
	}

	public void enterSearchInput(String keyword, int searchNumber) {
		SearchInput.get(searchNumber).sendKeys(keyword);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.attributeContains(SearchLoading.get(searchNumber), "style", "none"));
	}

	public String getPageTitle() {
		return PageTitle.getText();
	}

	public boolean isContactForm() {
		return ContactForm.isDisplayed();
	}

	public void clickDropUsALine() {
		DropUsALine.click();
	}

	public void clickSendButton() {
		SendButton.click();
	}

	public void clickVillas() {
		Villas.click();
	}

	public void clickApartments() {
		Apartments.click();
	}

	public void clickNew_Launch() {
		New_Launch.click();
	}

	public void clickPlots() {
		Plots.click();
	}

	public void clickBlog() {
		Blog.click();
	}
}
