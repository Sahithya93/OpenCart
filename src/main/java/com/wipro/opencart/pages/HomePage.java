package com.wipro.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(how=How.LINK_TEXT,using="create an account")
	WebElement createAccount;//This is Common Action
	
	@FindBy(how=How.XPATH,using="//div[@id='slideshow0']/a")
	WebElement galaxyTab_adv;
	
	@FindBy(how=How.LINK_TEXT, using="MacBook")
	WebElement macBook_lnk;
	
	@FindBy(how=How.LINK_TEXT,using="Logout")
	WebElement logout;
	
	public HomePage(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}
	
	//Method to click on 'Create an account' link to navigate to Registration page
	public RegistrationPage clickOnCreateLink(){
		
		createAccount.click();
		return new RegistrationPage(driver);
		
	}
	
	//Method to click on 'Samsung Galaxy Tab 10.1' advertisement
	public ProductPage clickOnGalaxyAdvTab(){
		
		
		galaxyTab_adv.click();
		return new ProductPage(driver);
	}
	
	public ProductPage clickOnProduct(){
		
		macBook_lnk.click();
		return new ProductPage(driver);
	}
	
	public AccountLogoutPage logout(){
		
		logout.click();
		return new AccountLogoutPage(driver);
	}

}
