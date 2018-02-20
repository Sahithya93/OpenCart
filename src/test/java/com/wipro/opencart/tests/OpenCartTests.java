package com.wipro.opencart.tests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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
import com.wipro.opencart.pages.AccountLoginPage;
import com.wipro.opencart.pages.AccountLogoutPage;
import com.wipro.opencart.pages.CheckOutPage;
import com.wipro.opencart.pages.CommonActions;
import com.wipro.opencart.pages.MyAccountPage;
import com.wipro.opencart.pages.OrderHistoryPage;
import com.wipro.opencart.pages.PhonesAndPDAPage;
import com.wipro.opencart.pages.ProductPage;
import com.wipro.opencart.pages.HomePage;
import com.wipro.opencart.pages.MyWishlistPage;
import com.wipro.opencart.pages.ProductReturnsPage;
import com.wipro.opencart.pages.RegistrationPage;
import com.wipro.opencart.pages.SearchPage;
import com.wipro.opencart.pages.ShoppingCartPage;
import com.wipro.opencart.pages.YourOrderPage;
import com.wipro.opencart.utilities.CaptureScreenshot;
import com.wipro.opencart.utilities.ExcelData;
import com.wipro.opencart.utilities.ExcelWriteData;
import com.wipro.opencart.utilities.WriteData;

import basesetup.LaunchBrowser;

public class OpenCartTests {
	
	WebDriver driver;
	RegistrationPage registrationPage;
	HomePage homePage;
	ProductPage galaxyProductPage;
	MyWishlistPage myWishlistPage;
	ProductPage productPage;
	ShoppingCartPage shoppingCartPage;
	CheckOutPage checkOutPage;
	YourOrderPage yourOrderPage;
	OrderHistoryPage orderHistoryPage;
	ProductReturnsPage productReturnsPage;
	AccountLoginPage accountLoginPage;
	MyAccountPage myAccountPage;
	SearchPage searchPage;
	PhonesAndPDAPage phonesAndPDAPage;
	ShoppingCartPage shoppingCart;
	
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
	public void launchBrowser(String Browser) throws Exception{
		
		//Method invokes extent report with the given name
		
		driver = LaunchBrowser.OpenBrowser(Browser);
		System.out.println("Registration and Add to cart: Browser Launched");
		
	}
	
	//Test to create a new account for the user - [Used Apache POI to read details from Excel]
	@Test(priority=1, dataProvider="User Details")
	public void registration(String firstname,String lastname,String emailAddress,String telephoneNum,String address1,String cityName,String postcodeNum,String country,String zone,String pwd,String confirm_pwd) throws Exception{
		
		extentTest = extentReports.startTest("Registration and Add to Cart");
		homePage = new HomePage(driver);
		
		//Calling method to click on 'Create Account' link
		registrationPage = homePage.clickOnCreateLink();
		
		//Calling method to fill user details in Registration page and verify account is created
		registrationPage.fillDetailsAndRegister(firstname,lastname,emailAddress,telephoneNum,address1,cityName,postcodeNum,country,zone,pwd,confirm_pwd);
		try{
		Assert.assertEquals("Your Account Has Been Created!", driver.getTitle(),"Titles Not Matched: New Account Not Created");
		extentTest.log(LogStatus.PASS, "Registration Successful");
		System.out.println("Your Account Has Been Created!");
		}catch(Exception e){
			extentTest.log(LogStatus.INFO, "Registration is not successful");
		}
		
	}
	
	//Test To add reviews on the product
	@Test(priority=2, dataProvider="ReviewInputValues")
	public void addReviewOnViewedProduct(String name,String reviewComments,String rating) throws Exception{
		
		//Calling method to navigate to Home Page after registration
		homePage=registrationPage.navigateToHome();//should be done from common actions class
		//Calling method to click on Galaxy tab 10.1 advertisement and verify user is redirected to respective product page
		galaxyProductPage=homePage.clickOnGalaxyAdvTab();
		Assert.assertEquals("Samsung Galaxy Tab 10.1", galaxyProductPage.heading.getText(), "User is in " +"'"+driver.getTitle()+"'"+" page");
		//extentTest.log(LogStatus.PASS,"User is in "+"'"+"Samsung Galaxy Tab 10.1"+"'"+" page");
		
		//Calling method to add reviews on the product
		galaxyProductPage.reviewOnProduct(name, reviewComments, rating);
		
		//Handling the error toast if displayed, when adding reviews
		/*try{
			if(galaxyProductPage.warningToast.getText().equalsIgnoreCase("Warning: Review Text must be between 25 and 1000 characters!")){
			extentTest.log(LogStatus.INFO,"Warning: Review Text must be between 25 and 1000 characters!");}
		}catch(org.openqa.selenium.NoSuchElementException e){
			Thread.sleep(10000);
			if(galaxyProductPage.successToast.getText().equalsIgnoreCase("Thank you for your review. It has been submitted to the webmaster for approval.")){
			extentTest.log(LogStatus.INFO,"Thank you for your review. It has been submitted to the webmaster for approval.");
			System.out.println("Added Reviews to the Product");
			extentTest.log(LogStatus.PASS, "Success:Reviews Added");
			}
		}*/
		
		
	}
	
	//Test to add product to the Wishlist
	@Test(priority=3)
	public void addToWishlist() throws Exception{
		
		
		//Calling method to click on 'Add to Wishlist' link and verify success toast is displayed
		galaxyProductPage.clickOnAddToWishlist();
		Thread.sleep(1500);
		Assert.assertTrue(galaxyProductPage.getSuccessMessage().contains("Success"), "Product is not added to Wishlist");
		//extentTest.log(LogStatus.PASS,"Success: You have added Samsung Galaxy Tab 10.1 to your wish list!");
		System.out.println("Success: You have added Samsung Galaxy Tab 10.1 to your wish list!");
		
		//Calling method to close the success toast
		galaxyProductPage.closeSuccesstoast();
		
		//Calling method to click on 'Wishlist' link and check user is redirected to 'My Wishlist' page
		myWishlistPage = galaxyProductPage.clickOnWishlist();
		
		Assert.assertTrue(myWishlistPage.getTitle().equals("My Wish List"), "User is not redirected to wishlist page");
		//extentTest.log(LogStatus.INFO,"User is redirected to My Wishlist Page");
		
		
		//Verifying count in 'Wishlist' link is equal to number of products in the page
		Assert.assertEquals(myWishlistPage.valueInWishlistLink(), myWishlistPage.numOfProductsInTable(), "Value shown in wishlist link is different from number of records in the table");
		//extentTest.log(LogStatus.INFO,"Product added: Value shown in wishlist link is equal to number of records in the table");
		//extentTest.log(LogStatus.PASS, "Success: Product added to Wishlist and Verified");
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
		
		System.out.println("Add to cart: Success message is displayed");
		Thread.sleep(3000);
		
		//Calling method to close the success toast
		myWishlistPage.closeSuccessToast();
		Thread.sleep(3000);
		//Verifying the success toast is closed or not
		try{
		Assert.assertTrue(myWishlistPage.isSuccessToastDisplayed());
		}catch(org.openqa.selenium.NoSuchElementException e){
			//extentTest.log(LogStatus.PASS,"Add to cart: Success Message is closed");
		}
		
		//Calling method to remove product from the list and click on continue
		myWishlistPage.removeProductFromWishlistAndContinue();
		
		System.out.println("Product is removed from the Wishlist");
		//extentTest.log(LogStatus.PASS,"Success: Product added to Cart and removed automatically from Wishlist");
		
		//Calling method to logout from the account and verify logout message
		AccountLogoutPage accountLogoutPage =myWishlistPage.logout();
		Assert.assertTrue(accountLogoutPage.getLogoutMessage().equals("Account Logout"), "Account Logout message is not displayed");
		//extentTest.log(LogStatus.PASS,"Account Logout message is displayed and the user is signed out from the account");
		System.out.println("Account Logout message is displayed and the user is signed out from the account");
	}
	
	@Test(priority=5, dataProvider = "Authentication")
	public void orderHistory(String firstname, String email, String password) throws Exception{
		
		extentReports.startTest("OrderHistory");
		accountLoginPage = new AccountLoginPage(driver);
		registrationPage = new RegistrationPage(driver);
		
		//Calling methods to Login
		accountLoginPage.clickOnLoginLink();
		myAccountPage=accountLoginPage.giveCredentialsAndSignin(email, password);
		
		//Verify firstname of signed in user is shown in the 'You are signed in as...' text after signed in
		Assert.assertEquals(firstname, myAccountPage.getSignedInUserName(), "User is not signed in or incorrect username is shown");
		System.out.println("Firstname of signed in user is shown in the page");
		
		//Calling method to navigate to Home page
		homePage = registrationPage.navigateToHome();
		
		//Calling method to view details of any product and adding it to the cart
		productPage = homePage.clickOnProduct();
		productPage.clickOnAddToCart();
		Assert.assertTrue(productPage.isSuccessToastDisplayed(), "Success toast is not displayed");
		System.out.println("Success: You have added MacBook to your shopping cart!");
		
		//Calling method to navigate to Shopping Cart page
		shoppingCartPage = productPage.clickOnShoppingCart();
		
		//Writing the items present in Shopping cart to the excel sheet
		ExcelWriteData excelWriteData = new ExcelWriteData("Products");
		excelWriteData.createRows(0);	
		excelWriteData.createCellsAndWriteData(0, "Shopping Cart");//Heading of columns in Excel sheet
		excelWriteData.createCellsAndWriteData(1, "Check Out");//Heading of columns in Excel sheet
		excelWriteData.createCellsAndWriteData(2, "Status");//Heading of columns in Excel sheet
		
		List<String> items=shoppingCartPage.getItemsInCart();//Getting items of page to list
		
		for(int rows=1;rows<=shoppingCartPage.numOfItems();rows++){
			
			excelWriteData.createRows(rows);			
			excelWriteData.createCellsAndWriteData(0, items.get(rows-1));
			
		}
		
		
		//Calling methods to update the quantity of product and navigate to checkout page
		shoppingCartPage.quantity("4");
		shoppingCartPage.clickOnUpdateBtn();
		System.out.println("Increased the Quantity of Product to order in Shopping Cart Page");
		checkOutPage = shoppingCartPage.navigateToCheckOutPage();
		
		//Continuing with steps
		Thread.sleep(1500);
		checkOutPage.continueWithBillingDetails();
		Thread.sleep(1500);
		checkOutPage.continueWithDeliveryDetails();
		Thread.sleep(1500);
		checkOutPage.continueWithDeliveryMethod();
		Thread.sleep(3000);
		checkOutPage.continueWithPaymentMethod();
		Thread.sleep(3000);
		//Writing the items present in CheckOut Page to the excel sheet
		List<String> itemsOfCheckOut= checkOutPage.getItemsFromCheckoutPage();
		
		for(int rows=1;rows<=checkOutPage.numOfItems();rows++){
			
			excelWriteData.createRows(rows);		
			excelWriteData.createCellsAndWriteData(1, itemsOfCheckOut.get(rows-1));
			
		}
		excelWriteData.writeTo("D:/Test Data/Items.xlsx");
		
		//Comparing values of Shopping Cart & CheckOut fields and writing the status in Status field of Excel
		ExcelData excelData = new ExcelData("D:/Test Data/Items.xlsx", 0);
		for(int i =1;i<=excelData.numOfRows();i++){
			if(excelData.getData(i, 0).equals(excelData.getData(i, 1))){
				System.out.println("Product is valid");
				excelWriteData.createRows(i);
				excelWriteData.createCellsAndWriteData(2, "True");
			}
			else{
				System.out.println("Product is NOT valid");
				excelWriteData.createRows(i);
				excelWriteData.createCellsAndWriteData(2, "False");
			}
			excelWriteData.writeTo("D:/Test Data/Items.xlsx");
		}
		
		Thread.sleep(1500);
		//Confirming the Order
		yourOrderPage = checkOutPage.confirmOrder();
		Thread.sleep(3000);
		Assert.assertEquals("Your Order Has Been Processed!", yourOrderPage.getPageHeader());
		System.out.println("Your Order Has Been Processed!");
		
		//Calling methods to navigate to Order History Page and verify the order details
		orderHistoryPage = yourOrderPage.navigateToOrderHistoryPage();
		
		String orderId= orderHistoryPage.getPreviousOrderId();
		orderHistoryPage.viewOrderDetails();
		String orderId_InfoPage=orderHistoryPage.getOrderId_InfoPage();
		try{
			Assert.assertEquals(orderId_InfoPage.substring(0,15),orderId);
			System.out.println("Previous Order is displayed in the Result view");
		}catch(NoSuchElementException e){
			
			orderHistoryPage.logout();
			System.out.println("Signed Out:Previous Order is not displayed");
		}
		
		//Calling method to navigate to product returns page and submit the reason for return
		productReturnsPage = orderHistoryPage.navigateToProductReturnPage();
		productReturnsPage.selectReason();
		Thread.sleep(10000);
		productReturnsPage.clickOnContinueBtn();
		System.out.println("Product Return Processed");
		productReturnsPage.continueToHomePage();
		homePage.logout();
	}
	
	
	@Test(priority=6,dataProvider = "Authentication")
	public void productComparison(String firstname, String email, String password) throws Exception{
		
		/*accountLoginPage = new AccountLoginPage(driver);
		registrationPage = new RegistrationPage(driver);*/
		extentReports.startTest("Product Comparison");
		
		//Calling methods to Login
		accountLoginPage.clickOnLoginLink();
		myAccountPage=accountLoginPage.giveCredentialsAndSignin(email, password);
		
		searchPage = myAccountPage.searchForproduct("Samsung");
		searchPage.selectCategory();
		phonesAndPDAPage = searchPage.navigateToPhonesPage();
		
		phonesAndPDAPage.sortProducts();
		
		productPage = phonesAndPDAPage.compareProducts();
		productPage.clickOnAddToCart();
		shoppingCart = productPage.clickOnShoppingCart();
		checkOutPage = shoppingCart.navigateToCheckOutPage();
		
		//Continuing with steps
		Thread.sleep(1500);
		checkOutPage.continueWithBillingDetails();
		Thread.sleep(1500);
		checkOutPage.continueWithDeliveryDetails();
		Thread.sleep(1500);
		checkOutPage.continueWithDeliveryMethod();
		Thread.sleep(3000);
		checkOutPage.continueWithPaymentMethod();
		Thread.sleep(3000);
		yourOrderPage = checkOutPage.confirmOrder();
		Thread.sleep(3000);
		driver.navigate().back();
		
		orderHistoryPage = yourOrderPage.navigateToOrderHistoryPage();
		orderHistoryPage.subscribenewsletter();
		
	
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws Exception{
		
		if(result.getStatus()==ITestResult.FAILURE){
			
			//String capturedPath = CaptureScreenshot.capture(driver, "Failed");
			extentTest.log(LogStatus.FAIL, result.getName());
		}
		extentReports.endTest(extentTest);
		extentReports.flush();
		extentReports.close();
	}
	
	/*(@DataProvider(name="Authentication")
	public Object[][] credentials() throws Exception{
		
		ExcelData exceldata = new ExcelData("D:/Test Data/OpenCart.xlsx",1);
		int rows = exceldata.numOfRows();
		Object[][] data = new Object[rows][3];
		for(int row=0;row<rows;row++){
			
			data[row][0]=exceldata.getData(row+1, 0);
			data[row][1]= exceldata.getData(row+1, 2);
			data[row][2]= exceldata.getData(row+1, 9);
		}
		return data;
	}*/
	
	//Logout from the account
	@AfterTest
	public void logout(){
		
		
		//Close the report
		/*extentReports.endTest(extentTest);
		extentReports.flush();
		extentReports.close();*/
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
	
	@DataProvider(name="Authentication")
	public Object[][] credentials() throws Exception{
		
		ExcelData exceldata = new ExcelData("D:/Test Data/OpenCart.xlsx",1);
		int rows = exceldata.numOfRows();
		Object[][] data = new Object[rows][3];
		for(int row=0;row<rows;row++){
			
			data[row][0]=exceldata.getData(row+1, 0);
			data[row][1]= exceldata.getData(row+1, 2);
			data[row][2]= exceldata.getData(row+1, 9);
		}
		return data;
	}

}
