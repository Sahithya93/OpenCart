package com.wipro.opencart.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
	
	WebDriver driver;
	
	@FindBy(how=How.XPATH,using="//div[@id='welcome']/a[1]")
	WebElement firstname;
	
	@FindBy(name="search")
	WebElement searchField;
	
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
	
	public SearchPage searchForproduct(String product){
		
		searchField.clear();
		searchField.click();
		searchField.sendKeys(product);
		searchField.sendKeys(Keys.ENTER);
		return new SearchPage(driver);
	}

}
