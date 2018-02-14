package com.wipro.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class OrderHistoryPage {
	
	WebDriver driver;
	
	@FindBy(how=How.XPATH,using="//div[@class='order-list'][1]/div[3]/div[3]/a[1]")
	WebElement viewBtn;
	
	@FindBy(how=How.XPATH,using="//div[@class='order-list'][1]/div[1]")
	WebElement previousOrderId;
	
	@FindBy(how=How.XPATH,using="//table[@class='list'][1]/tbody/tr[1]/td[1]")
	WebElement OrderId_InfoPage;
	
	@FindBy(how=How.LINK_TEXT,using="Logout")
	WebElement logout;
	
	@FindBy(how=How.XPATH,using="//table[@class='list'][3]/tbody/tr[2]/td[6]/a")
	WebElement returnBtn;
	
	public OrderHistoryPage(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getPreviousOrderId(){
		
		return previousOrderId.getText();
	}
	
	public OrderHistoryPage viewOrderDetails(){
		
		viewBtn.click();
		return this;
	}
	
	public String getOrderId_InfoPage(){
		
		return OrderId_InfoPage.getText();
	}
	
	public AccountLogoutPage logout(){
		
		logout.click();
		return new AccountLogoutPage(driver);
	}
	
	public ProductReturnsPage navigateToProductReturnPage(){
		
		returnBtn.click();
		return new ProductReturnsPage(driver);
	}

}
