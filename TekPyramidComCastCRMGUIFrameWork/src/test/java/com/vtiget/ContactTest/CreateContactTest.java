package com.vtiget.ContactTest;

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

import com.comcust.crm.generic.ObjectrepositoryUtility.ContactInformationPage;
import com.comcust.crm.generic.ObjectrepositoryUtility.ContactPage;
import com.comcust.crm.generic.ObjectrepositoryUtility.CreateContactPage;
import com.comcust.crm.generic.ObjectrepositoryUtility.Homepage;
import com.comcust.crm.generic.ObjectrepositoryUtility.LoginPage;
import com.comcust.crm.generic.WebDriverUtility.JavaUtility;
import com.comcust.crm.generic.WebDriverUtility.WebDriverUtility;
import com.comcust.crm.generic.fileUtility.ExcelUtility;
import com.comcust.crm.generic.fileUtility.PropertiesFileUtility;

public class CreateContactTest {

	public static void main(String[] args) throws IOException {
		//creating an object for utility classes
				PropertiesFileUtility plib=new PropertiesFileUtility();
				ExcelUtility elib=new ExcelUtility();
				WebDriverUtility wlib=new WebDriverUtility();

			  //reading data from property file 
			     String BROWSER = plib.togetDataFromPropertiesFile("browser");
			     String URL = plib.togetDataFromPropertiesFile("url");
			     String USERNAME = plib.togetDataFromPropertiesFile("username");
			     String PASSWORD = plib.togetDataFromPropertiesFile("password");
			 	
			//READING DATA FROM EXCEL FILE
			 	String LASTNAME=elib.togetDataFromExcelFile("sheet3", 1, 2);
			 	
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
		   //2)click on contact link
					Homepage hp=new Homepage(driver);
					hp.getContact().click();
					
	      //3)click on create new contact
					ContactPage cp=new ContactPage(driver);
				cp.getCreateContact().click();
					
		 //4)enter mandatory fields
				CreateContactPage ccp=new CreateContactPage(driver);
					ccp.getLastname().sendKeys(LASTNAME);
				    ccp.getSaveBTN().click();
					
		//5)verifying cantact name
				    ContactInformationPage cip=new ContactInformationPage(driver);
					String actual_lastname =cip.getHeaderInf().getText().trim();
					if(actual_lastname.contains(LASTNAME))
					{
						System.out.println("Contact created  and header contains "+LASTNAME+"===PASS");
					}
					else
					{
						System.out.println("Contact header not contains "+LASTNAME+"===FAIL");
					}
	     //6)verifying contact name in  contact information
					String actual_lstname = cip.getLastName().getText().trim();
					if(actual_lstname.trim().equals(LASTNAME))
					{
						System.out.println("Contact created  Successfully===PASS");
					}
					else
					{
						System.out.println("Contact not craeted===FAIL");
					}
	        // logout
			       WebElement Org_Name =hp.getUserIcon();
			        wlib.toclickOnWebElement(driver, Org_Name);
			        WebElement signOut =hp.getSignOutBtn();
			        signOut.click();
			         driver.close();		
	}

}
