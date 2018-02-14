package basesetup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LaunchBrowser {
	static WebDriver driver;
	
	
	public static WebDriver OpenBrowser(String Browser){
	
	if(Browser.equalsIgnoreCase("chrome")){
	System.setProperty("webdriver.chrome.driver", "D:\\Selenium Stuff\\Selenium Jars\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://10.207.182.108:81/opencart/");
	driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}
	return driver;
	}
	/*
	
	
	}*/
}
