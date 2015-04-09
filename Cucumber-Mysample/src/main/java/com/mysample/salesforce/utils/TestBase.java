package com.mysample.salesforce.utils;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase 
{
	//Initialise properties file
	
	Properties OR = null;
	Properties CONFIG = null;
	WebDriver driver = null;
	WebDriver mozilla = null;
	WebDriver chrome = null;
	WebDriver ie = null;
	static TestBase instance;
	
	Logger APPLICATION_LOGS = Logger.getLogger("devpinoyLogger");
	
	public TestBase()
	{
		if(OR == null )
		{
			//initialise OR
			try
			{
				OR = new Properties();
				FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\mysample\\salesforce\\config\\OR.properties");
				OR.load(fs);
				
				//initialise CONFIG to corresponding environment
				
				CONFIG = new Properties();
				fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\mysample\\salesforce\\config\\"+OR.getProperty("testEnv")+"_config.properties");
				CONFIG.load(fs);
				
				System.out.println(OR.getProperty("loginusername"));
				System.out.println(CONFIG.getProperty("loginURL"));
			}
			catch (Exception e)
			{
				System.out.println("Error on initializing properties files");
			}
		}
		
	}
	public void openBrowser(String browserType)
	{
		if(browserType.equals("Mozilla") && mozilla == null)
		{
			log("****************Opening Firefox browser****************");
			driver = new FirefoxDriver();
			mozilla =  driver;
		}
		else if (browserType.equals("Mozilla") && mozilla != null)
		{
			driver = mozilla;
		}
		else if(browserType.equals("Chrome") && chrome == null)
		{
			log("****************Opening Chrome browser****************");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//chrome//chromedriver.exe");
			driver = new ChromeDriver();
			chrome = driver;
		}
		else if(browserType.equals("Chrome") && chrome != null)
		{
			driver = chrome;
		}
		else if(browserType.equals("IE") && ie == null)
		{
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"");
			//driver = new ChromeDriver();
		}
		//Maximise the browser
		driver.manage().window().maximize();
		//implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public void navigate(String URL)
	{
		log("**************** Navigating to "+CONFIG.getProperty(URL)+"****************");
		driver.navigate().to(CONFIG.getProperty(URL));
	}
	
	//Clicking on any object
	public void click(String ObjectName)
	{
		log("**************** Clicking on  "+ ObjectName +"****************");
		driver.findElement(By.xpath(OR.getProperty(ObjectName))).click();;
	}
	
	public void type(String text, String ObjectName)
	{
		log("**************** Entering  "+text+"****************");
		driver.findElement(By.xpath(OR.getProperty(ObjectName))).sendKeys(text);
	}
	
	public void select(String text, String ObjectName)
	{
		driver.findElement(By.xpath(OR.getProperty(ObjectName))).sendKeys(text);		
	}
	
	public boolean isElementPresent(String objectName)
	{
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		System.out.println(driver.findElements(By.xpath(OR.getProperty(objectName))).size());
		int count = driver.findElements(By.xpath(OR.getProperty(objectName))).size();
		if(count == 0)
			return false;
		else
			return true;
	}
	
	/********************Singleton***********************/
	public static TestBase getInstance()
	{
		if(instance == null)
			instance = new TestBase();
		return instance;
		
	}
	
	public void log(String msg)
	{
		APPLICATION_LOGS.debug(msg);
	}
	
	//**************Application specific methods*******************/

	public boolean isLoggedIn()
	{
		if(isElementPresent("searchTextField"))
			return true;
		else
			return false;
		
	}
	public void doDefaultLogin()
	{
		log("Logging in to " + CONFIG.getProperty("loginURL"));
		navigate("loginURL");
		log("User name is -" + CONFIG.getProperty("defaultuser")+" and Password is - " + CONFIG.getProperty("defaultpassword"));
		type(CONFIG.getProperty("defaultuser"), "loginusername");
		type(CONFIG.getProperty("defaultpassword"), "loginpassword");
		click("loginButton");
	}
}
