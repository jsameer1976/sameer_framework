//To verify whether application allows admin to add post based on the created category
package com.training.pom;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RETC_039POM {

	 private WebDriver driver; 
		
		public RETC_039POM(WebDriver driver) {
			this.driver = driver; 
			PageFactory.initElements(driver, this);
		}
	
		@FindBy(id="user_login")
		private WebElement userName; 
		
		@FindBy(id="user_pass")
		private WebElement password;
	
		@FindBy(name="login")
		private WebElement loginBtn1; 
		
		//Click on Posts
		@FindBy(id="menu-posts")
		private WebElement Posts;
		
		// Click on Categories
		@FindBy(xpath="/html/body/div[1]/div[1]/div[2]/ul/li[3]/ul/li[4]/a")
		private WebElement Categories;
		
		// Enter Valid Credentials in Name textbox
		
		@FindBy(id="tag-name")
		private WebElement NameTextBox;
		
		// Enter Valid Credentials in Slug textbox
		@FindBy(id="tag-slug")
		private WebElement SlugTextBox;
		
		// Enter Valid Credentials in Description textbox
		
		@FindBy(id="tag-description")
		private WebElement DescTextBox;
		
		
		// Click on Add New Category button
		@FindBy(id="submit")
		private WebElement AddNewCategoryButton;
		
		//Click on All Posts link
		@FindBy(xpath="/html/body/div[1]/div[1]/div[2]/ul/li[3]/ul/li[2]/a")
		private WebElement AllPosts;
		
		//Click on Add New Button
		@FindBy(xpath="/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/a")
		private WebElement AddNew;
		
		//Enter Valid credentials in Enter title here textbox
		@FindBy(id="title")
		private WebElement TitleTextBox;
		
		//Enter valid credentials in body textbox
		@FindBy(xpath = "//textarea[@id='content']")
		private WebElement BodyTextBox;
		
		//Click on Category Added
		@FindBy(id="in-category-181")
		private WebElement NewCategoryAdded;
		
		// Wait for Publish button
		
		@FindBy(xpath="/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/div/div/div[2]/div/div[1]/div/div/div[2]/div[1]/a")
		private WebElement WaitForPublish;
		
		// Click on Publish
		@FindBy(xpath = "//input[@id='publish']")
		private WebElement publish;
		
		// Wait for Post Publish
		@FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/div[2]/p/a")
		private WebElement WaitPostPublish;
		
		// Post publish message
		@FindBy(xpath="/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/div[2]/p")
		private WebElement postPublishMessage;
				
		public void sendUserName(String userName) {
			this.userName.clear();
			this.userName.sendKeys(userName);
			}
			
			public void sendPassword(String password) {
			this.password.clear(); 
			this.password.sendKeys(password); 
			}
			
			public void clickLoginBtn() {
			this.loginBtn1.click(); 
			}
			
			//1. Click on Posts
			
			public void Postsclick() {
				this.Posts.click();
			}
	
			// Click on Categories
			public void Categoriesclick() {
				this.Categories.click();
			}
			public void EnterName(String password) {
				this.NameTextBox.clear(); 
				this.NameTextBox.sendKeys(password); 
				}
			public void EnterSlug(String password) {
				this.SlugTextBox.clear(); 
				this.SlugTextBox.sendKeys(password); 
				}
			public void EnterDesc(String password) {
				this.DescTextBox.clear(); 
				this.DescTextBox.sendKeys(password); 
				}
			
			public void AddNewCategoryClick() {
				  this.AddNewCategoryButton.click(); 
				}
			
			public void AllPostsClick() {
				  this.AllPosts.click(); 
				}
			public void AddNewClick() {
				this.AddNew.click();
			}
			public void EnterTitleTextBox(String password) {
				//this.TitleTextBox.clear(); 
				this.TitleTextBox.sendKeys(password); 
				}
			public void EnterBodyTextBox ( String text) {
				//this.BodyTextBox.clear();
				this.BodyTextBox.sendKeys(text);				
			}
			public void CategoryClick() {
				this.NewCategoryAdded.click();
			}
			
			public void WaitForPublish() {
				WebDriverWait wait= new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.elementToBeClickable(WaitForPublish));
				
			}
			
			public void Publish() throws AWTException {
				this.publish.click();
			}
			
			public void WaitPostPublish () {
				
				WebDriverWait wait1= new WebDriverWait(driver, 30);
				wait1.until(ExpectedConditions.elementToBeClickable(WaitPostPublish)); 
			
			}
			
			public String PostPublish() {
				return this.postPublishMessage.getText();
			}
			
			
}
