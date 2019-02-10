package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ApartmentPOM {
	private WebDriver driver;

	public ApartmentPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='amount']")
	private WebElement SalePrice;

	@FindBy(xpath = "//input[@name='your-name']")
	private WebElement Name;
	
	
	@FindBy(xpath = "//input[@name='your-name']/..//*[@role='alert']")
	private WebElement NameRequiredErrorMessage;

	@FindBy(xpath = "//input[@name='your-email']")
	private WebElement Email;
	
	@FindBy(xpath = "//input[@name='your-email']/..//*[@role='alert']")
	private WebElement EmailRequiredErrorMessage;

	@FindBy(xpath = "//textarea[@name='your-message']")
	private WebElement Comments;

	@FindBy(xpath = "//input[@name='your-subject']")
	private WebElement Subject;

	@FindBy(xpath = "//input[@value='Send']")
	private WebElement SendButton;

	@FindBy(xpath = "//input[@id='downpayment']")
	private WebElement Downpayment;

	@FindBy(xpath = "//input[@id='years']")
	private WebElement LoanTerms;

	@FindBy(xpath = "//input[@id='interest']")
	private WebElement Interest;

	@FindBy(xpath = "//button[text()='Calculate']")
	private WebElement CalculateButton;

	@FindBy(xpath = "//h4[contains(text(),'Find Your Home')]")
	private WebElement FindYourHomeTitle;

	@FindBy(xpath = "//input[@id='keyword_search']")
	private WebElement Keyword_Search;

	@FindBy(xpath = "//span[text()='Property type']/..")
	private WebElement PropertyType;

	@FindBy(xpath = "//li[text()='Property type']/../li")
	private List<WebElement> PropertyTypeOptions;

	@FindBy(xpath = "//li[text()='Any Regions']/../li")
	private List<WebElement> RegionOptions;


	@FindBy(xpath = "//span[text()='Any Regions']/..")
	private WebElement TaxRegion;
	
	
	 @FindBy(xpath = "//select[@name='tax-region']")
		private WebElement TaxRegion1;

	@FindBy(xpath = "//input[@class='chosen-search-input']")
	private List<WebElement> TaxRegionSearchInput;

	@FindBy(xpath = "//button[text()='Search']")
	private WebElement SearchPropertyButton;

	@FindBy(xpath = "//strong[@class='calc-output']")
	private WebElement CalculatorOutput;

	@FindBy(xpath = "//div[contains(@class,'response-output')]")
	private WebElement ResponseMessage;

	// input[@value='Send']/following-sibling::span
	@FindBy(xpath = "//input[@value='Send']/following-sibling::span")
	private WebElement MessageSentloader;
	
	//span[@class='ajax-loader']
	@FindBy(xpath = "//span[contains(@class,'ajax-loader')]")
	private WebElement ajexLoader;

	public boolean isImagePageDisplayed(String imageName) {
		return driver.findElement(By.xpath("//h3[contains(text(),'" + imageName + " - Overview')]")).isDisplayed();
	}

	public String getResponseMessage() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.attributeToBe(MessageSentloader, "class", "ajax-loader"));
		return ResponseMessage.getText();
	}

	public String getCalculatorOutput() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return CalculatorOutput.getText();
	}

	public void clickSendButton() {
		SendButton.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.attributeToBe(ajexLoader, "class", "ajax-loader"));*/
	}
	
	public boolean isElementHighlighted(String element) {
		WebDriverWait wait=new WebDriverWait(driver, 10);
		
		if(element.equalsIgnoreCase("name"))
		{
			return wait.until(ExpectedConditions.attributeToBe(Name, "aria-invalid", "true"));
			
		}
		
		else {
			return wait.until(ExpectedConditions.attributeToBe(Email, "aria-invalid", "true"));
		}
	}

	public void clickImage(String imageName) {
		driver.findElement(By.xpath("//span[contains(text(),'" + imageName + "')]/../..")).click();
	}

	public void clickCalculateButton() {
		CalculateButton.click();
	}

	public void clickSearchPropertyButton() {
		SearchPropertyButton.click();
	}

	public void selectPropertyType(String propertyName) {
		System.out.println("selected property: " + propertyName);
		PropertyType.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (WebElement element : PropertyTypeOptions) {
			System.out.println("element: " + element.getText());
			if (element.getText().contains(propertyName)) {
				element.click();
				break;
			}

		}
	}

	public void selectTaxRegion(String taxRegion) {
		System.out.println("selected property: " + taxRegion);
		TaxRegion.click();
		
		
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (WebElement element : RegionOptions) {
			System.out.println("element: " + element.getText());
			if (element.getText().contains(taxRegion)) {
				element.click();
				break;
			}

		}
	}

	public boolean isYourHomeDisplayed() {
		return FindYourHomeTitle.isDisplayed();
	}

	public void enterSearch(String keyword) {
		Keyword_Search.sendKeys(keyword);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Keyword_Search.sendKeys(Keys.ENTER);

	}

	public void clickSearchItem(String searchItem) {
		driver.findElement(By.xpath("//span[text()='" + searchItem + "']")).click();
		;
	}

	public void enterSalePrice(String salePrice) {
		SalePrice.sendKeys(salePrice);
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

	public void enterDownpayment(String downpayment) {
		Downpayment.sendKeys(downpayment);
	}

	public void enterLoanTerms(String loanTerms) {
		LoanTerms.sendKeys(loanTerms);
	}

	public void enterInterest(String interest) {
		Interest.sendKeys(interest);
	}
	
	public String getErrorMessage(String requiredField) {
		if(requiredField.equalsIgnoreCase("name"))
		{
			return NameRequiredErrorMessage.getText();
		}
		
		else {
			return EmailRequiredErrorMessage.getText();
		}
	}
	
	public String getErrMessage(String requiredField) {
		WebElement field =null;
		if(requiredField.equalsIgnoreCase("sale Price"))
		{
			field = SalePrice;
		} else if(requiredField.equalsIgnoreCase("loan Term"))
		{
			field = LoanTerms;
		}else if(requiredField.equalsIgnoreCase("interest rate"))
		{
			field = Interest;
		}	
		return field.getAttribute("validationMessage");

	}

}
