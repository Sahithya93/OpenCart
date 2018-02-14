package com.wipro.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CommonActions {
	
	WebDriver driver;
	
	@FindBy(how=How.LINK_TEXT,using="login")
	WebElement login;
	
	public CommonActions(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public AccountLoginPage clickOnLoginLink(){
		login.click();
		return new AccountLoginPage(driver);
	}

}
