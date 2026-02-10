package com.comcast.crm.ContactTest;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcust.crm.configannotationsutility.BaseClass;
import com.comcust.crm.configannotationsutility.UtilityClassObject;
import com.comcust.crm.generic.ObjectrepositoryUtility.ContactInformationPage;
import com.comcust.crm.generic.ObjectrepositoryUtility.ContactPage;
import com.comcust.crm.generic.ObjectrepositoryUtility.CreateContactPage;
import com.comcust.crm.generic.ObjectrepositoryUtility.CreateOrganizationPage;
import com.comcust.crm.generic.ObjectrepositoryUtility.Homepage;
import com.comcust.crm.generic.ObjectrepositoryUtility.OrganizationLookupPage;
import com.comcust.crm.generic.ObjectrepositoryUtility.OrganizationPage;

/**
  @author Basamma
  contains createContact, createContactWthOrganization, createContactWithStartDateANdEndDate testscipts
 **/
	
@Listeners(com.comcust.crm.configannotationsutility.ListenerImplementatioon.class)
public class CreateContactTest extends BaseClass{
		@Test (groups="smokeTest")
	public void createContactTest() throws EncryptedDocumentException, IOException
		{
			//READING DATA FROM EXCEL FILE
		 	String LASTNAME=elib.togetDataFromExcelFile("sheet3", 2, 2);
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
						String actual_header =cip.getHeaderInf().getText().trim();
						boolean status = actual_header.contains(LASTNAME);
						Assert.assertTrue(status);
						
			 //6)verifying contact name in  contact information
						String actual_lstname = cip.getLastName().getText().trim();
						Assert.assertEquals(actual_lstname,LASTNAME);
						UtilityClassObject.getTest().log(Status.INFO, "contact lastname verified succcessfully");
		}
		
	@Test(groups= {"smokeTest","integrationTest","regressionTest"})
	public void createContactWithOrgTest() throws EncryptedDocumentException, IOException, InterruptedException
	{
	 	//Random number
	 	int random_number = jlib.togetRandomNumber(100000);
		//READING DATA FROM EXCEL FILE
	 	String LASTNAME=elib.togetDataFromExcelFile("sheet3", 2, 2);
	 	String  ORGNAME = elib.togetDataFromExcelFile("sheet3", 2, 3)+random_number;
	 	String STREET= elib.togetDataFromExcelFile("sheet3", 2, 5);
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
					Assert.assertEquals(actual_org, ORGNAME);
			 
	}
	@Test(groups= {"smokeTest","regressionTest"})
	public void createContactWithStartAndEndDateTest() throws EncryptedDocumentException, IOException, InterruptedException
	{
				//READING DATA FROM EXCEL FILE
			 	String LASTNAME=elib.togetDataFromExcelFile("sheet3", 3, 2);
			 	String  DAYS = elib.togetDataFromExcelFile("sheet3", 3, 4);
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
					Assert.assertEquals(actual_startDate, START_DATE);
					
			    //6)verifying end date
					String actual_endDate = cip.getEndDate().getText().trim();
					Assert.assertEquals(actual_endDate, END_DATE);
					
	}
		}
