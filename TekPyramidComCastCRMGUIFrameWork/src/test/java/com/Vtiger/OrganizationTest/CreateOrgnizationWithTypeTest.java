package com.Vtiger.OrganizationTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.comcust.crm.generic.ObjectrepositoryUtility.CreateOrganizationPage;
import com.comcust.crm.generic.ObjectrepositoryUtility.Homepage;
import com.comcust.crm.generic.ObjectrepositoryUtility.LoginPage;
import com.comcust.crm.generic.ObjectrepositoryUtility.OrganizationInformationPage;
import com.comcust.crm.generic.ObjectrepositoryUtility.OrganizationPage;
import com.comcust.crm.generic.WebDriverUtility.JavaUtility;
import com.comcust.crm.generic.WebDriverUtility.WebDriverUtility;
import com.comcust.crm.generic.fileUtility.ExcelUtility;
import com.comcust.crm.generic.fileUtility.PropertiesFileUtility;

public class CreateOrgnizationWithTypeTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		//creating an object for utility classes
		PropertiesFileUtility plib=new PropertiesFileUtility();
		ExcelUtility elib=new ExcelUtility();
		WebDriverUtility wlib=new WebDriverUtility();
	    JavaUtility jlib=new JavaUtility();

	  //reading data from property file 
	     String BROWSER = plib.togetDataFromPropertiesFile("browser");
	     String URL = plib.togetDataFromPropertiesFile("url");
	     String USERNAME = plib.togetDataFromPropertiesFile("username");
	     String PASSWORD = plib.togetDataFromPropertiesFile("password");
	 	
	 	//Random number
	 	int random_number = jlib.togetRandomNumber(100000);
	 	
	 	//READING DATA FROM EXCEL FILE
	 	String ORGNAME =elib.togetDataFromExcelFile("sheet1", 2, 2)+random_number;
	 	String STREET = elib.togetDataFromExcelFile("sheet1", 2, 3);
	 	String CITY= elib.togetDataFromExcelFile("sheet1", 2, 4);
	 	String STATE= elib.togetDataFromExcelFile("sheet1", 2, 5);
	 	String  COUNTRY= elib.togetDataFromExcelFile("sheet1", 2, 6);
	 	String  PINCODE= elib.togetDataFromExcelFile("sheet1", 2, 7);
	 	String  INDUSTRY=elib.togetDataFromExcelFile("sheet1", 2, 8);
	 	String  TYPE=elib.togetDataFromExcelFile("sheet1", 2, 9);
	 	
			//launching browser
				WebDriver driver=null;
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
				
				wlib.toWaitPagetoLoad(driver, 15);
		//1)login
			LoginPage lp=new LoginPage(driver);
			lp.loginToApp(URL, USERNAME, PASSWORD);
			
		//2)Navigating to Organization Module
			Homepage hp=new Homepage(driver);
			hp.getOrgnization().click();
			
		//3)click on Create Organozation  button
			OrganizationPage op=new OrganizationPage(driver);
			op.getCreateOrg().click();
			
		//4)Enter All the details and create new organization
			CreateOrganizationPage cop=new CreateOrganizationPage(driver);
			cop.getOrganizationName().sendKeys(ORGNAME);
			
			WebElement idustry_Dropdown = cop.getIndustry();
			wlib.toSelectFromDropDown(INDUSTRY, idustry_Dropdown);
		
			WebElement type = cop.getType();
			wlib.toSelectFromDropDown(TYPE, type);
			
			cop.getStreet().sendKeys(STREET);
			cop.getCity().sendKeys(CITY);
			cop.getState().sendKeys(STATE);
			cop.getPincode().sendKeys(PINCODE);
			cop.getCountry().sendKeys(COUNTRY);
			cop.getCopyAddress().click();
			cop.getSaveBtn().click();
			
		//5)vreify  industry
			OrganizationInformationPage oip=new OrganizationInformationPage(driver);
			String actualIndustry = oip.getIndustry().getText().trim();
			if(actualIndustry.equals(INDUSTRY))
			{
				System.out.println("Organization created with "+INDUSTRY+"  ====PASS");
			}
			else
			{
				System.out.println("Organization NOT created with "+INDUSTRY+"  ====Fail");
			}
		//verifying type
			String actual_Type =oip.getType().getText().trim();
		         if(actual_Type.trim().equals(TYPE))
		         {
		        	 System.out.println("Organization created with "+TYPE+"  ====PASS");
		         }
		         else
		         {
		        	 System.out.println("Organization NOT created with "+TYPE+"  ====Fail");
		         }
		     	
		// 6)logout
		         WebElement Org_Name =hp.getUserIcon();
		       wlib.toclickOnWebElement(driver, Org_Name);
		         WebElement signOut =hp.getSignOutBtn();
		         signOut.click();
		         driver.close();

	}

}
