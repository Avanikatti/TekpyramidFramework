package com.comcast.crmOrganizationTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.comcust.crm.configannotationsutility.BaseClass;
import com.comcust.crm.generic.ObjectrepositoryUtility.CreateOrganizationPage;
import com.comcust.crm.generic.ObjectrepositoryUtility.Homepage;
import com.comcust.crm.generic.ObjectrepositoryUtility.OrganizationInformationPage;
import com.comcust.crm.generic.ObjectrepositoryUtility.OrganizationPage;
/**
 @author Basamma
 contains createOrganization, createOrganizationWithPhoneNumber and CreateOrganizationWithTypeandIndustry testscipts
 */

@Listeners(com.comcust.crm.configannotationsutility.ListenerImplementatioon.class)
public class CreateOrganizationTest extends BaseClass {

	@Test(groups= "smokeTest")
	public void CreateOrganizationTest() throws Throwable, IOException 
	{
	 	int random_number = jlib.togetRandomNumber(100000);
	 	//READING DATA FROM EXCEL FILe
	 	String ORGNAME =elib.togetDataFromExcelFile("sheet1", 1, 2)+random_number;
	 	String STREET = elib.togetDataFromExcelFile("sheet1", 1, 3);
	 	String CITY= elib.togetDataFromExcelFile("sheet1", 1, 4);
	 	String STATE= elib.togetDataFromExcelFile("sheet1", 1, 5);
	 	String  COUNTRY= elib.togetDataFromExcelFile("sheet1", 1, 6);
	 	String  PINCODE= elib.togetDataFromExcelFile("sheet1", 1, 7);
	 	
	 	//2)Navigating to Organization Module
		//creating an object for home page
		Homepage hp=new Homepage(driver);
						hp.getOrgnization().click();
						//3)click on Create Organozation  button
					OrganizationPage op=new OrganizationPage(driver);
					op.getCreateOrg().click();
					//4)Enter All the details and create new organization
					CreateOrganizationPage cop=new CreateOrganizationPage(driver);
				cop.getOrganizationName().sendKeys(ORGNAME);
					cop.getStreet().sendKeys(STREET);
					cop.getCity().sendKeys(CITY);
					cop.getState().sendKeys(STATE);
					cop.getPincode().sendKeys(PINCODE);
					cop.getCountry().sendKeys(COUNTRY);
					cop.getCopyAddress().click();
					cop.getSaveBtn().click();
					
				//5)verify organization name in header of the msg
					OrganizationInformationPage oip=new OrganizationInformationPage(driver);
					String actualHeadOrgName = oip.getHeaderInform().getText();
					boolean status=actualHeadOrgName.contains(ORGNAME);
					Assert.assertTrue(status);		
					//verifying organization name info 
					String verify_Orgz = oip.getOrganizationName().getText().trim();
					SoftAssert soft=new SoftAssert();
					soft.assertEquals(actualHeadOrgName, ORGNAME);
	}

	@Test(groups= {"smokeTest","regressionTest"})
	public void CreateOrgWithPhoneNumber() throws EncryptedDocumentException, IOException
	{
					//Random number
				 	int random_number = jlib.togetRandomNumber(100000);
				 	
				 	//READING DATA FROM EXCEL FILE
				 	String ORGNAME =elib.togetDataFromExcelFile("sheet1", 3, 2)+random_number;
				 	String STREET = elib.togetDataFromExcelFile("sheet1", 3, 3);
				 	String CITY= elib.togetDataFromExcelFile("sheet1", 3, 4);
				 	String STATE= elib.togetDataFromExcelFile("sheet1", 3, 5);
				 	String  COUNTRY= elib.togetDataFromExcelFile("sheet1", 3, 6);
				 	String  PINCODE= elib.togetDataFromExcelFile("sheet1", 3, 7);
				 	String  PhoneNumber=elib.togetDataFromExcelFile("sheet1", 3, 10);
			        //2)Navigating to Organization Module
				     Homepage hp=new Homepage(driver);
					hp.getOrgnization().click();		
					//3)click on Create Organozation  button
					OrganizationPage op=new OrganizationPage(driver);
					op.getCreateOrg().click();
					//4)Enter All the details and create new organization
					CreateOrganizationPage cop=new CreateOrganizationPage(driver);
					cop.getOrganizationName().sendKeys(ORGNAME);
					cop.getPhone().sendKeys(PhoneNumber);
					cop.getStreet().sendKeys(STREET);
					cop.getCity().sendKeys(CITY);
					cop.getState().sendKeys(STATE);
					cop.getPincode().sendKeys(PINCODE);
					cop.getCountry().sendKeys(COUNTRY);
					cop.getCopyAddress().click();
					cop.getSaveBtn().click();	
				//5)verify PhoneNumber
					OrganizationInformationPage oip=new OrganizationInformationPage(driver);
					String actual_phno =oip.getPhone().getText().trim();
					Assert.assertEquals(actual_phno, PhoneNumber);		
								
		}
	
	@Test(groups= {"smokeTest","regressionTest"})
	public void organizationWithType() throws EncryptedDocumentException, IOException
	{
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
				Assert.assertEquals(actualIndustry, INDUSTRY);
			//6)verifying type
				String actual_Type =oip.getType().getText().trim();
				Assert.assertEquals(actual_Type, TYPE);
	 	
	}
}
