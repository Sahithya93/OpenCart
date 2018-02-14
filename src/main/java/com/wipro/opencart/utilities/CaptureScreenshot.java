package com.wipro.opencart.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class CaptureScreenshot {
	
	public static String capture(WebDriver driver,String screenshotName) throws Exception{
		
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir")+screenshotName+"\\.png";
		File destination = new File(dest);
		FileUtils.copyFile(source,destination);
		return dest;
	}

}
