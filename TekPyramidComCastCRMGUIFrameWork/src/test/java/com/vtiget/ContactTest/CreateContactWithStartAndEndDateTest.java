package com.vtiget.ContactTest;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcust.crm.generic.ObjectrepositoryUtility.ContactInformationPage;
import com.comcust.crm.generic.ObjectrepositoryUtility.ContactPage;
import com.comcust.crm.generic.ObjectrepositoryUtility.CreateContactPage;
import com.comcust.crm.generic.ObjectrepositoryUtility.Homepage;
import com.comcust.crm.generic.ObjectrepositoryUtility.LoginPage;
import com.comcust.crm.generic.WebDriverUtility.JavaUtility;
import com.comcust.crm.generic.WebDriverUtility.WebDriverUtility;
import com.comcust.crm.generic.fileUtility.ExcelUtility;
import com.comcust.crm.generic.fileUtility.PropertiesFileUtility;

public class CreateContactWithStartAndEndDateTest {

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
	     
		//READING DATA FROM EXCEL FILE
	 	String LASTNAME=elib.togetDataFromExcelFile("sheet3", 3, 2);
	 	String  DAYS = elib.togetDataFromExcelFile("sheet3", 3, 4);
	 	
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
						String START_DATE = jlib.togetCurrentDate();
						String END_DATE = jlib.togetRequiredDate(DAYS);
						ccp.getStartDate().sendKeys(START_DATE);
						 WebElement enddate = ccp.getEndDate();
						 enddate.clear();
						 enddate.sendKeys(END_DATE);
						Thread.sleep(5000);
						ccp.getSaveBTN().click();
				//5)verifying startdate
						ContactInformationPage cip=new ContactInformationPage(driver);
						String actual_startDate = cip.getStartDate().getText().trim();
						if(actual_startDate.equals(START_DATE))
						{
						System.out.println("Contact created with "+START_DATE+"===PASS");
						}
						else
						{
				  		  System.out.println("Contact not created with "+START_DATE+"===FAIL");
						}
			//verifying end date
						String actual_endDate = cip.getEndDate().getText().trim();
						if(actual_endDate.trim().equals(END_DATE))
						{
							System.out.println("Contact created with "+END_DATE+"===PASS");
						}
						else
						{	
							System.out.println("Contact not created with "+END_DATE+"===FAIL");
						}
			        // logout
					     WebElement Org_Name = hp.getUserIcon();
					     wlib.toclickOnWebElement(driver, Org_Name);
					     WebElement signOut =hp.getSignOutBtn();
					     signOut.click();
				       driver.close();

	}

}
