package Androidprograms;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class orientation
{
	public static void main(String[] args) throws Exception
	{
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","emulator-5554");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","4.2.2");
		dc.setCapability("appPackage","com.android.calculator2");
		dc.setCapability("appActivity","com.android.calculator2.Calculator");
		
		//Start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4723\"");
		URL u = new URL("http://0.0.0.0:4723/wd/hub");
	
		//Create object to AndroidDriver to Launch
		AndroidDriver driver;
		while(2>1)
		{
			try
			{
			driver=new AndroidDriver(u,dc);
			break;
		    }		
		catch(Exception e)
		{			 
		}
		}
	//Automation		
		try
		{
		WebDriverWait w = new WebDriverWait(driver,20);
		//w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='9']")));
				
		
		//rotate screen
		String x=driver.getOrientation().name();
		if(x.equalsIgnoreCase("portrait"))
		{
			driver.rotate(ScreenOrientation.LANDSCAPE);
			Thread.sleep(10000);
			driver.rotate(ScreenOrientation.PORTRAIT);
		}
		Thread.sleep(5000);
		driver.lockDevice();
		Thread.sleep(5000);
		if(driver.isDeviceLocked())
		{
			System.out.println("Locked");
			driver.unlockDevice();
		}
		Thread.sleep(10000);
		
		
		//close App
		driver.closeApp();
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}

