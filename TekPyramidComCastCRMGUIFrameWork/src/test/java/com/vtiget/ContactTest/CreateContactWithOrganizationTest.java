package com.vtiget.ContactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jspecify.annotations.Nullable;
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
import com.comcust.crm.generic.ObjectrepositoryUtility.CreateOrganizationPage;
import com.comcust.crm.generic.ObjectrepositoryUtility.Homepage;
import com.comcust.crm.generic.ObjectrepositoryUtility.LoginPage;
import com.comcust.crm.generic.ObjectrepositoryUtility.OrganizationLookupPage;
import com.comcust.crm.generic.ObjectrepositoryUtility.OrganizationPage;
import com.comcust.crm.generic.WebDriverUtility.JavaUtility;
import com.comcust.crm.generic.WebDriverUtility.WebDriverUtility;
import com.comcust.crm.generic.fileUtility.ExcelUtility;
import com.comcust.crm.generic.fileUtility.PropertiesFileUtility;

public class CreateContactWithOrganizationTest {

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
			 	String LASTNAME=elib.togetDataFromExcelFile("sheet3", 2, 2);
			 	String  ORGNAME = elib.togetDataFromExcelFile("sheet3", 2, 3)+random_number;
			 	String STREET= elib.togetDataFromExcelFile("sheet3", 2, 5);
			 
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
					//2)Creating organization becouse it is mandatory to have atleast one organization
						   Homepage hp=new Homepage(driver);
						   hp.getOrgnization().click();
							OrganizationPage op=new OrganizationPage(driver);
							op.getCreateOrg().click();
							CreateOrganizationPage cop=new CreateOrganizationPage(driver);
							cop.getOrganizationName().sendKeys(ORGNAME);
							cop.getStreet().sendKeys(STREET);
							cop.getCopyAddress().click();
							cop.getSaveBtn().click();
							
							Thread.sleep(1000);
					//3)click on contact link
							hp.getContact().click();
							
				   //4)click on create new contact
							ContactPage cp=new ContactPage(driver);
							cp.getCreateContact().click();
							
				  //5)enter mandatory fields
							CreateContactPage ccp=new CreateContactPage(driver);
							ccp.getLastname().sendKeys(LASTNAME);
							ccp.getOrgNameLookUp().click();
							wlib.toswitchToWindow(driver, "Accounts&action");
							OrganizationLookupPage olp=new OrganizationLookupPage(driver);
							olp.getSearchOrg().sendKeys(ORGNAME);
							olp.getSearchBTN().click();
								//dynamic xpath************************
							driver.findElement(By.xpath("//a[text()='"+ORGNAME+"']")).click();
							
							wlib.toswitchToWindow(driver, "Contacts&action");
							
							ccp.getSaveBTN().click();
				//6)verifying organization name
							ContactInformationPage cip=new ContactInformationPage(driver);
							String actual_org =cip.getOrgName() .getText().trim();
							if(actual_org.equals(ORGNAME))
							{
								System.out.println("Contact created with  "+ORGNAME+"===PASS");
							}
							else
							{
								System.out.println("Contact NOT created with "+ORGNAME+"===FAIL");
							}
			        // logout
					         WebElement Org_Name = hp.getUserIcon();
					         wlib.toclickOnWebElement(driver, Org_Name);
					         WebElement signOut = hp.getSignOutBtn();
					         signOut.click();
					         
					         driver.close();

	}

}
