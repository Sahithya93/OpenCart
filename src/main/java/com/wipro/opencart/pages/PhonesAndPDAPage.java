package com.wipro.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PhonesAndPDAPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//div[@class='sort']/select")
	WebElement sort;
	
	@FindBy(xpath="//*[@id='content']/div[4]/div[1]/div[1]/div[3]/a[1]")
	public  WebElement addtocompare1;
	
	
	@FindBy(xpath="//*[@id='content']/div[4]/div[2]/div[1]/div[3]/a[1]")
	public  WebElement addtocompare2;
	
	@FindBy(xpath="//*[@id='content']/div[4]/div[3]/div[1]/div[3]/a[1]")
	public  WebElement addtocompare3;

	@FindBy(linkText="product comparison")
	public  WebElement productcomparison;
	
	@FindBy(xpath="//*[@id='content']/table/tbody[1]/tr[1]/td[3]/a[1]")
	public  WebElement firstphone;
	
	public PhonesAndPDAPage(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void sortProducts() throws Exception{
		
		Select select = new Select(sort);
		select.selectByVisibleText("Price (High > Low)");
		Thread.sleep(5000);
	}
	
public ProductPage compareProducts() throws Exception{
		
		addtocompare1.click();
		Thread.sleep(5000);
		addtocompare2.click();
		System.out.println("second click done");
		Thread.sleep(5000);
		addtocompare3.click();
		Thread.sleep(5000);
		System.out.println("third click done");
		productcomparison.click();
		System.out.println("comparison click done");
		firstphone.click();
		
		return new ProductPage(driver);
	}

}
