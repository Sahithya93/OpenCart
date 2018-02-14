package com.wipro.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class YourOrderPage {
	
	WebDriver driver;
	
	@FindBy(how=How.TAG_NAME,using="h1")
	WebElement heading;
	
	@FindBy(how=How.LINK_TEXT,using="Order History")
	WebElement historyLnk;
	
	
	
	public YourOrderPage(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getPageHeader(){
		
		return heading.getText();
	}
	
	public OrderHistoryPage navigateToOrderHistoryPage(){
		
		historyLnk.click();
		return new OrderHistoryPage(driver);
	}
	
	
	

}
