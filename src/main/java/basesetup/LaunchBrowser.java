package basesetup;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LaunchBrowser {
	static WebDriver driver;
	static DesiredCapabilities capability;
	
	public static WebDriver OpenBrowser(String Browser) throws Exception{
	
	if(Browser.equalsIgnoreCase("chrome")){
		
		capability = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL("http://10.159.34.141:4444/wd/hub"), capability);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.get("http://10.207.182.108:81/opencart");       
        
}
	if(Browser.equalsIgnoreCase("ie")){
		
		capability = DesiredCapabilities.internetExplorer();
        driver = new RemoteWebDriver(new URL("http://10.159.34.141:4444/wd/hub"), capability);
        driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
        driver.get("http://10.207.182.108:81/opencart");       
        
}
	return driver;
	}
}
