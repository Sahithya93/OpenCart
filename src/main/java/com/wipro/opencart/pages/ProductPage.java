package com.wipro.opencart.pages;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	
	WebDriver driver;
	
	@FindBy(how=How.TAG_NAME,using="h1")
	public WebElement heading;
	@FindBy(how=How.PARTIAL_LINK_TEXT,using="Reviews")
	WebElement reviewTab;
	@FindBy(how=How.NAME,using="name")
	WebElement name;
	@FindBy(how=How.NAME,using="text")
	WebElement inputText;
	@FindBy(how=How.NAME,using="captcha")
	WebElement inputCaptcha;
	@FindBy(how=How.LINK_TEXT,using="Continue")
	WebElement continueButton;
	@FindBy(how=How.XPATH,using="//div[@class='warning']")
	public static WebElement warningToast;
	@FindBy(how=How.XPATH,using="//div[@class='success']")
	public static WebElement successToast;
	@FindBy(how=How.LINK_TEXT,using="Add to Wish List")
	WebElement addToWishlist;
	@FindBy(how=How.PARTIAL_LINK_TEXT,using="Wish List")
	WebElement Wishlist;
	@FindBy(how=How.XPATH,using="//img[@class='close']")
	WebElement close;
	@FindBy(how=How.ID,using="button-cart")
	WebElement addToCartBtn;
	@FindBy(how=How.XPATH,using="//div[@class='success']")
	WebElement success;
	@FindBy(how=How.LINK_TEXT,using="Shopping Cart")
	WebElement cart_lnk;
	
	public ProductPage(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ProductPage reviewOnProduct(String reviewerName,String reviewComments,String rating) throws Exception{
		
		reviewTab.click();
		System.out.println(rating);
		WebElement ratingValue = driver.findElement(By.xpath("//input[@value='"+rating+"']"));
		String a=rating;
		name.clear();
		name.sendKeys(reviewerName);
		inputText.clear();
		inputText.sendKeys(reviewComments);
		Thread.sleep(4000);
		ratingValue.click();
		//inputCaptcha.clear();
		Thread.sleep(10000);
		//To Enter Captcha Manually
		/*System.out.println("Please enter Captcha:");
		Scanner scanner = new Scanner(System.in);
		String captcha = scanner.nextLine();		
		inputCaptcha.sendKeys(captcha);*/
		
		continueButton.click();
		
		
		return this;
	}
	
	public void clickOnAddToWishlist(){
		
		addToWishlist.click();
	}
	
	public String getSuccessMessage(){
		
		return successToast.getText();
	}
	
	public void closeSuccesstoast(){
		
		close.click();
		
	}
	
	public MyWishlistPage clickOnWishlist(){
		
		Wishlist.click();
		return new MyWishlistPage(driver);
	}
	
	public ProductPage clickOnAddToCart(){
		
		addToCartBtn.click();
		return this;
	}
	
	public boolean isSuccessToastDisplayed() throws Exception{
		
		Thread.sleep(2000);
		return success.isDisplayed();
	}
	
	public ShoppingCartPage clickOnShoppingCart(){
		
		cart_lnk.click();
		return new ShoppingCartPage(driver);
	}

}