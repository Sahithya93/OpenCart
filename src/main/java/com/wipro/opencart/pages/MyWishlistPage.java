package com.wipro.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class MyWishlistPage {
	
	WebDriver driver;
	
	@FindBy(how=How.XPATH,using="//td[@class='name']/a")
	List<WebElement> products;
	@FindBy(how=How.XPATH,using="//div[@class='links']/a[2]")
	WebElement wishlistValue;
	@FindBy(how=How.XPATH,using="//div[@class='price']")
	WebElement unitPrice;
	@FindBy(how=How.XPATH,using="//td[@class='action']/img")
	WebElement addCart;
	@FindBy(how=How.XPATH,using="//td[@class='action']/a")
	WebElement remove;
	@FindBy(how=How.LINK_TEXT,using="Continue")
	WebElement continueButton;
	
	//Common Actions
	@FindBy(how=How.LINK_TEXT,using="£")
	WebElement pound;
	@FindBy(how=How.LINK_TEXT,using="€")
	WebElement euro;
	@FindBy(how=How.LINK_TEXT,using="$")
	WebElement dollar;
	@FindBy(how=How.XPATH,using="//div[@class='success']")
	WebElement success;
	@FindBy(how=How.XPATH,using="//img[@class='close']")
	WebElement close;
	@FindBy(how=How.LINK_TEXT,using="Logout")
	WebElement logout;
	
	public MyWishlistPage(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getTitle(){
		
		return driver.getTitle();
	}
	
	public int valueInWishlistLink(){
		String text=wishlistValue.getText();
		String value=text.substring(11, 12);
		//System.out.println(value);
		int valueInLink = Integer.parseInt(value);
		return valueInLink;
		
	}
	public int numOfProductsInTable(){
		int size=products.size();
		return size;
	}
	
	public List<String> storeUnitPrices(){
		
		List<String> prices = new ArrayList<String>();
		pound.click();
		prices.add("Pounds: "+unitPrice.getText());
		euro.click();
		prices.add("Euros: "+unitPrice.getText());
		dollar.click();
		prices.add("US Dollar: "+unitPrice.getText());
		return prices;
		
	}
	
	public void addToCart(){
		
		addCart.click();
	}
	
	public boolean isSuccessToastDisplayed(){
		
		return success.isDisplayed();
	}
	
	public void closeSuccessToast(){
		
		close.click();
	}
	public void removeProductFromWishlistAndContinue(){
		
		remove.click();
		continueButton.click();
	}
	
	public AccountLogoutPage logout(){
		
		logout.click();
		return new AccountLogoutPage(driver);
	}
}
