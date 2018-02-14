package com.wipro.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

public class RegistrationPage {
	
	WebDriver driver;
	
	@FindBy(how=How.NAME, using="firstname")
	WebElement firstName;
	@FindBy(how=How.NAME, using="lastname")
	WebElement lastName;
	@FindBy(how=How.NAME, using="email")
	WebElement email;
	@FindBy(how=How.NAME, using="telephone")
	WebElement telephone;
	@FindBy(how=How.NAME, using="address_1")
	WebElement address_1;
	@FindBy(how=How.NAME, using="city")
	WebElement city;
	@FindBy(how=How.NAME, using="postcode")
	WebElement postcode;
	@FindBy(how=How.NAME, using="country_id")
	WebElement countryId;
	@FindBy(how=How.NAME, using="zone_id")
	WebElement zoneId;
	@FindBy(how=How.NAME, using="password")
	WebElement password;
	@FindBy(how=How.NAME, using="confirm")
	WebElement confirm;
	@FindBy(how=How.NAME, using="agree")
	WebElement privacyPolicy;
	@FindBy(how=How.XPATH, using="//input[@type='submit']")
	WebElement submit;
	
	@FindBy(how=How.LINK_TEXT, using="Home")
	WebElement Home;//This is Common Action
	
	public RegistrationPage(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Method to Fill Details of new user and Register 
	public void fillDetailsAndRegister(String firstname,String lastname,String emailAddress,String telephoneNum,String address1,String cityName,String postcodeNum,String country,String zone,String pwd,String confirm_pwd){
		
		firstName.sendKeys(firstname);
		lastName.sendKeys(lastname);
		email.sendKeys(emailAddress);
		telephone.sendKeys(telephoneNum);
		address_1.sendKeys(address1);
		city.sendKeys(cityName);
		postcode.sendKeys(postcodeNum);
		
		Select dropdown = new Select(countryId);
		dropdown.selectByVisibleText("India");
		
		Select zoneDropdown = new Select(zoneId);
		zoneDropdown.selectByVisibleText("Andhra Pradesh");
		
		zoneId.sendKeys(zone);
		password.sendKeys(pwd);
		confirm.sendKeys(confirm_pwd);
		privacyPolicy.click();
		submit.click();
		
	}
	
	//Method to navigate to Home Page - (For Reference: This method needs to be written in Common Actions)
	public HomePage navigateToHome(){
		
		Home.click();
		return new HomePage(driver);
	}

}
