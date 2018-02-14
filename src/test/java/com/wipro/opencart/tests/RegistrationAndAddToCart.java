package com.wipro.opencart.tests;

import java.io.File;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.wipro.opencart.pages.AccountLogoutPage;
import com.wipro.opencart.pages.CommonActions;
import com.wipro.opencart.pages.ProductPage;
import com.wipro.opencart.pages.HomePage;
import com.wipro.opencart.pages.MyWishlistPage;
import com.wipro.opencart.pages.RegistrationPage;
import com.wipro.opencart.utilities.CaptureScreenshot;
import com.wipro.opencart.utilities.ExcelData;
import com.wipro.opencart.utilities.WriteData;

import basesetup.LaunchBrowser;

public class RegistrationAndAddToCart {
	
	WebDriver driver;
	RegistrationPage registrationPage;
	HomePage homePage;
	ProductPage galaxyProductPage;
	MyWishlistPage myWishlistPage;
	
	ExtentReports extentReports;
	ExtentTest extentTest;
	@BeforeSuite
	public void reports(){
		
		extentReports = new ExtentReports(System.getProperty("user.dir")+"\\Extent-Reports\\report.html",true);
		//extentReports.loadConfig(new File(System.getProperty("user.dir")+"\\Extent-Reports\\extent_config.xml"));
	}
	
	//Test to Launch browser and access the application
	@Parameters({"Browser"})
	@BeforeClass
	public void launchBrowser(String Browser){
		
		//Method invokes extent report with the given name
		extentTest = extentReports.startTest("Registration and Add to Cart");
		driver = LaunchBrowser.OpenBrowser(Browser);
		extentTest.log(LogStatus.PASS, "Browser Launched and accessed application");
		System.out.println("Registration and Add to cart: Browser Launched");
		
	}
	
	//Test to create a new account for the user - [Used Apache POI to read details from Excel]
	@Test(priority=1, dataProvider="User Details")
	public void registration(String firstname,String lastname,String emailAddress,String telephoneNum,String address1,String cityName,String postcodeNum,String country,String zone,String pwd,String confirm_pwd) throws Exception{
		
		homePage = new HomePage(driver);
		
		//Calling method to click on 'Create Account' link
		registrationPage = homePage.clickOnCreateLink();
		
		//Calling method to fill user details in Registration page and verify account is created
		registrationPage.fillDetailsAndRegister(firstname,lastname,emailAddress,telephoneNum,address1,cityName,postcodeNum,country,zone,pwd,confirm_pwd);
		try{
		Assert.assertEquals("Your Account Has Been Created!", driver.getTitle(),"Titles Not Matched: New Account Not Created");
		extentTest.log(LogStatus.PASS, "Registration: New User Account is created");
		}catch(Exception e){
			extentTest.log(LogStatus.FAIL, "Registration is not successful");
		}
		System.out.println("Your Account Has Been Created!");
	}
	
	//Test To add reviews on the product
	@Test(priority=2, dataProvider="ReviewInputValues")
	public void addReviewOnViewedProduct(String name,String reviewComments,String rating){
		
		//Calling method to navigate to Home Page after registration
		homePage=registrationPage.navigateToHome();//should be done from common actions class
		//Calling method to click on Galaxy tab 10.1 advertisement and verify user is redirected to respective product page
		galaxyProductPage=homePage.clickOnGalaxyAdvTab();
		Assert.assertEquals("Samsung Galaxy Tab 10.1", galaxyProductPage.heading.getText(), "User is in " +"'"+driver.getTitle()+"'"+" page");
		extentTest.log(LogStatus.PASS,"User is in "+"'"+"Samsung Galaxy Tab 10.1"+"'"+" page");
		
		//Calling method to add reviews on the product
		galaxyProductPage.reviewOnProduct(name, reviewComments, rating);
		
		//Handling the error toast if displayed, when adding reviews
		try{
			if(galaxyProductPage.warningToast.getText().equalsIgnoreCase("Warning: Review Text must be between 25 and 1000 characters!")){
			extentTest.log(LogStatus.PASS,"Warning: Review Text must be between 25 and 1000 characters!");}
		}catch(org.openqa.selenium.NoSuchElementException e){
			if(galaxyProductPage.successToast.getText().equalsIgnoreCase("Thank you for your review. It has been submitted to the webmaster for approval.")){
			extentTest.log(LogStatus.PASS,"Thank you for your review. It has been submitted to the webmaster for approval.");
			System.out.println("Added Reviews to the Product");
			}
		}
		
		
	}
	
	//Test to add product to the Wishlist
	@Test(priority=3)
	public void addToWishlist() throws Exception{
		
		
		//Calling method to click on 'Add to Wishlist' link and verify success toast is displayed
		galaxyProductPage.clickOnAddToWishlist();
		Thread.sleep(1500);
		Assert.assertTrue(galaxyProductPage.getSuccessMessage().contains("Success"), "Product is not added to Wishlist");
		extentTest.log(LogStatus.PASS,"Success: You have added Samsung Galaxy Tab 10.1 to your wish list!");
		System.out.println("Success: You have added Samsung Galaxy Tab 10.1 to your wish list!");
		
		//Calling method to close the success toast
		galaxyProductPage.closeSuccesstoast();
		
		//Calling method to click on 'Wishlist' link and check user is redirected to 'My Wishlist' page
		myWishlistPage = galaxyProductPage.clickOnWishlist();
		
		Assert.assertTrue(myWishlistPage.getTitle().equals("My Wish List"), "User is not redirected to wishlist page");
		extentTest.log(LogStatus.PASS,"User is redirected to My Wishlist Page");
		
		
		//Verifying count in 'Wishlist' link is equal to number of products in the page
		Assert.assertEquals(myWishlistPage.valueInWishlistLink(), myWishlistPage.numOfProductsInTable(), "Value shown in wishlist link is different from number of records in the table");
		extentTest.log(LogStatus.PASS,"Product added: Value shown in wishlist link is equal to number of records in the table");
		
		}
	
	//Test to add product to the cart
	@Test(priority=4)
	public void addToCart() throws Exception{
		
		//Calling method to get the unit prices of product and write to text file
		for(String price: myWishlistPage.storeUnitPrices()){
			
			/*WriteData is the library class created to write data to text file
			 * Created object of WriteData class and passed file name to create in specified location
			 */
			WriteData writeData = new WriteData("unitprices");
			writeData.writeTextToFile(price);
		}		
		
		//Calling method to add product to cart and verifying the success toast
		myWishlistPage.addToCart();
		Thread.sleep(1500);
		Assert.assertTrue(myWishlistPage.isSuccessToastDisplayed(), "Success message is not displayed");
		extentTest.log(LogStatus.PASS,"Add to cart: Success message is displayed");
		System.out.println("Add to cart: Success message is displayed");
		Thread.sleep(3000);
		
		//Calling method to close the success toast
		myWishlistPage.closeSuccessToast();
		Thread.sleep(3000);
		//Verifying the success toast is closed or not
		try{
		Assert.assertTrue(myWishlistPage.isSuccessToastDisplayed());
		}catch(org.openqa.selenium.NoSuchElementException e){
			extentTest.log(LogStatus.PASS,"Add to cart: Success Message is closed");
		}
		
		//Calling method to remove product from the list and click on continue
		myWishlistPage.removeProductFromWishlistAndContinue();
		extentTest.log(LogStatus.PASS, "Product is removed from the Wishlist");
		System.out.println("Product is removed from the Wishlist");
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws Exception{
		
		if(result.getStatus()==ITestResult.FAILURE){
			
			//String capturedPath = CaptureScreenshot.capture(driver, "Failed");
			extentTest.log(LogStatus.FAIL, "Failed");
		}
	}
	
	//Logout from the account
	@AfterClass
	public void logout(){
		
		//Calling method to logout from the account and verify logout message
		AccountLogoutPage accountLogoutPage =myWishlistPage.logout();
		Assert.assertTrue(accountLogoutPage.getLogoutMessage().equals("Account Logout"), "Account Logout message is not displayed");
		extentTest.log(LogStatus.PASS,"Account Logout message is displayed and the user is signed out from the account");
		System.out.println("Account Logout message is displayed and the user is signed out from the account");
		//Close the report
		extentReports.endTest(extentTest);
		extentReports.flush();
		extentReports.close();
	}
	
	//Dataprovider - Sending inputs to add reviews to product
	@DataProvider(name = "ReviewInputValues")
	public Object[][] inputDataValues() throws Exception{
		
		ExcelData excelData = new ExcelData("D:/Test Data/OpenCart.xlsx",0);
		
		int rowsCount = excelData.numOfRows();
		Object[][] data = new Object[rowsCount][3];
		for(int rows=0;rows<rowsCount;rows++){
			for(int cells =0;cells<=2;cells++){
				
				data[rows][cells] =excelData.getData(rows+1, cells);
				
			}
			
		}
		
		return data;
		
		
	}
	
	//Data provider - Sending user details for registration
	@DataProvider(name="User Details")
	public Object[][] userDetails() throws Exception{
		
		ExcelData excelData = new ExcelData("D:/Test Data/OpenCart.xlsx",1);
		Object[][] data = new Object[1][11];
		for(int cells=0;cells<11;cells++){
			
			data[0][cells] = excelData.getData(1, cells);
		}
		return data;
	}

}
