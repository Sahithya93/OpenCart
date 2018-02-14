package com.wipro.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
	
	WebDriver driver;
	
	@FindBy(how=How.XPATH,using="//div[@id='welcome']/a[1]")
	WebElement firstname;
	
	public MyAccountPage(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getTitle(){
		
		return driver.getTitle();
	}
	
	public String getSignedInUserName(){
		
		return firstname.getText();
	}

}
