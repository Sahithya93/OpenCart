package com.wipro.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AccountLoginPage {
	
	WebDriver driver;
	
	@FindBy(how=How.NAME,using="email")
	WebElement email_inputField;
	@FindBy(how=How.NAME,using="password")
	WebElement pwd_inputField;
	@FindBy(how=How.XPATH,using="//input[@type='submit']")
	WebElement loginBtn;
	@FindBy(how=How.LINK_TEXT,using="login")
	WebElement login;
	
	public AccountLoginPage(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnLoginLink(){
		login.click();
	}
	
	public String getTitle(){
		
		return driver.getTitle();
	}
	
	public MyAccountPage giveCredentialsAndSignin(String emailAddress, String password){
		
		email_inputField.sendKeys(emailAddress);
		pwd_inputField.sendKeys(password);
		loginBtn.click();
		return new MyAccountPage(driver);
	}

}
