package com.wipro.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SearchPage {
	
	WebDriver driver;
	
	@FindBy(name="category_id")
	WebElement category;
	
	@FindBy(id="sub_category")
	WebElement subCategory;
	
	@FindBy(how=How.LINK_TEXT,using="Phones & PDAs")
	WebElement phones_tab;
	
	
	
	public SearchPage(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selectCategory(){
		
		Select select = new Select(category);
		select.selectByValue("28");
		subCategory.click();
	}
	
	public PhonesAndPDAPage navigateToPhonesPage(){
		
		phones_tab.click();
		return new PhonesAndPDAPage(driver);
	}
	
	

}
