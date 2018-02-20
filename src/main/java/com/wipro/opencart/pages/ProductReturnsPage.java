package com.wipro.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ProductReturnsPage {
	
	WebDriver driver;
	
	@FindBy(how=How.ID,using="return-reason-id3")
	WebElement returnReason;
	
	@FindBy(how=How.NAME,using="captcha")
	WebElement captcha;
	
	@FindBy(how=How.XPATH,using="//input[@value='Continue']")
	WebElement ctnBtn;
	
	@FindBy(how=How.LINK_TEXT,using="Continue")
	WebElement ack_CtnBtn;
	
	public ProductReturnsPage(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ProductReturnsPage selectReason(){
		
		returnReason.click();
		return this;
	}
	
	public ProductReturnsPage clickOnContinueBtn(){
		
		ctnBtn.click();
		return this;
	}
	
	public HomePage continueToHomePage(){
		
		ack_CtnBtn.click();
		return new HomePage(driver);
	}

}
