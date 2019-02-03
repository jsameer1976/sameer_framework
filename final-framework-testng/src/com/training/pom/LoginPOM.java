package com.training.pom;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPOM {
	private WebDriver driver; 
	
	public LoginPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="user_login")
	private WebElement userName;
	
	@FindBy(className="sign-in")
	private WebElement signInLink;
	
	@FindBy(id="user_pass")
	private WebElement password;
	
	@FindBy(name="login")
	private WebElement loginBtn; 
	
	@FindBy(xpath="//*[@id=\"header\"]/div[2]/div/div/div")
	private WebElement user;
	
	@FindBy(xpath="//a[contains(text(),'Log Out')]")
	private List<WebElement> logout;
	
	public void sendUserName(String userName) {
		this.signInLink.click();
		
		this.userName.clear();
		this.userName.sendKeys(userName);
		
	}
	
	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	
	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}

	public void clickMyProfile() {
		//Actions action= new Actions(driver);
		//action.moveToElement(user, user.getLocation().getX(), user.getLocation().getY()+1).click().build().perform();
		//user.click();
	}

	public void logout() {

		logout.get(1).click();;
	}
}
