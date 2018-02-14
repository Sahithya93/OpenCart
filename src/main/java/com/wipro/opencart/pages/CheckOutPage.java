package com.wipro.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {
	
	WebDriver driver;
	
	@FindBy(how=How.ID,using="button-payment-address")
	WebElement billingCtnBtn;
	
	@FindBy(how=How.ID,using="button-shipping-address")
	WebElement deliveryDetails_Btn;
	
	@FindBy(how=How.ID,using="button-shipping-method")
	WebElement deliveryMethod_Btn;
	
	@FindBy(how=How.NAME,using="agree")
	WebElement agree_checkBox;
	
	@FindBy(how=How.ID,using="button-payment-method")
	WebElement paymentCtnBtn;
	
	@FindBy(how=How.XPATH, using="//div[@class='checkout-product']/table/tbody/tr/td[1]/a")
	List<WebElement> itemsInCheckout;
	
	@FindBy(how=How.ID, using="button-confirm")
	WebElement confirmBtn;
	
	public CheckOutPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public CheckOutPage continueWithBillingDetails(){
		
		billingCtnBtn.click();
		return this;
	}
	
	public CheckOutPage continueWithDeliveryDetails(){
		
		deliveryDetails_Btn.click();
		return this;
	}
	
	public CheckOutPage continueWithDeliveryMethod(){
		
		deliveryMethod_Btn.click();
		return this;
	}
	
	public CheckOutPage continueWithPaymentMethod(){
		
		agree_checkBox.click();
		paymentCtnBtn.click();
		return this;
	}
	
	public YourOrderPage confirmOrder(){
		
		confirmBtn.click();
		return new YourOrderPage(driver);
	}
	
	public int numOfItems(){
		
		return itemsInCheckout.size();
	}
	
	public List<String> getItemsFromCheckoutPage(){
		
		List<String> items=new ArrayList();
		
		for(WebElement item:itemsInCheckout){
			
			items.add(item.getText());
		}
		return items;
	}

}
