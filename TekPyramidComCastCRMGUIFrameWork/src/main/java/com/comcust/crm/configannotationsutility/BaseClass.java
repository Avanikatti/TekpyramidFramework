package com.comcust.crm.configannotationsutility;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.comcust.crm.generic.ObjectrepositoryUtility.Homepage;
import com.comcust.crm.generic.ObjectrepositoryUtility.LoginPage;
import com.comcust.crm.generic.WebDriverUtility.JavaUtility;
import com.comcust.crm.generic.WebDriverUtility.WebDriverUtility;
import com.comcust.crm.generic.databaseUtility.DatabaseUtility;
import com.comcust.crm.generic.fileUtility.ExcelUtility;
import com.comcust.crm.generic.fileUtility.PropertiesFileUtility;

public class BaseClass {
 public DatabaseUtility dlib=new DatabaseUtility();
 public PropertiesFileUtility plib=new PropertiesFileUtility();
 public ExcelUtility elib=new ExcelUtility();
 public WebDriverUtility wlib=new WebDriverUtility();
 public JavaUtility jlib=new JavaUtility();
 public WebDriver driver;
 public static WebDriver sdriver=null;//listener purpose only
	@BeforeSuite(alwaysRun = true)
	public void beforeSuit() throws SQLException
	{
		dlib.togetDBConnection();
	}
	@AfterSuite(alwaysRun = true)
	public void afterSuit() throws SQLException
	{
		dlib.closeDBconnection();
	}
	//@Parameters("BROWSER")//for Cross Browser purpose only
	@BeforeClass(alwaysRun = true)
	//public void beforeClass(String browser) throws IOException//Pass browser argument in method only while passing parameters in cross browser testing
	public void beforeClass() throws IOException
	{
		//String BROWSER=browser;//for Cross Browser purpose only
		String BROWSER = plib.togetDataFromPropertiesFile("browser");
		//launching browser
		if(BROWSER.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("firefox"))
		{
			
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{  //default browser
			driver=new ChromeDriver();
		}
		
		//sdriver=driver;//listener purpose only
		UtilityClassObject.setDriver(driver);//instead of reinitializing driver to sdriver you can use UtilityclassObject
	}
	@AfterClass(alwaysRun = true)
	public void afterClass()
	{
		//closing browser
		driver.quit();
	}
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() throws IOException
	{
		String URL = plib.togetDataFromPropertiesFile("url");
		String USERNAME=plib.togetDataFromPropertiesFile("username");
		String PASSWORD=plib.togetDataFromPropertiesFile("password");
		wlib.toWaitPagetoLoad(driver,20 );
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);
	}
	@AfterMethod(alwaysRun = true)
	public void afterMethod()
	{
		Homepage hp=new Homepage(driver);
		WebElement UserIcon=hp.getUserIcon();
		wlib.toclickOnWebElement(driver, UserIcon);
		hp.getSignOutBtn().click();
	}
}
