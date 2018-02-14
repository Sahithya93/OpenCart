package com.wipro.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {
	
	WebDriver driver;
	
	@FindBy(how=How.NAME,using="quantity[43::]")
	WebElement quantity_input;
	
	@FindBy(how=How.XPATH, using="//input[@name='quantity[43::]']/following-sibling::input")
	WebElement macQty_updateBtn;
	
	@FindBy(how=How.LINK_TEXT,using="Checkout")
	WebElement checkout_btn;
	
	@FindBy(how=How.XPATH,using="//div[@class='cart-info']/table/tbody/tr/td[2]/a")
	List<WebElement> itemsInCart;
	
	public ShoppingCartPage(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ShoppingCartPage quantity(String quantity){
		
		quantity_input.clear();
		quantity_input.sendKeys(quantity);
		return this;
	}
	
	public ShoppingCartPage clickOnUpdateBtn(){
		
		macQty_updateBtn.click();
		return this;
	}
	
	public CheckOutPage navigateToCheckOutPage(){
		
		checkout_btn.click();
		return new CheckOutPage(driver);
	}
	
	public List<String> getItemsInCart(){
		
		List<String> items = new ArrayList();
		
		for(WebElement item: itemsInCart){
			
			items.add(item.getText());
		}
		return items;
	}
	
	public int numOfItems(){
		
		return itemsInCart.size();
	}

}
