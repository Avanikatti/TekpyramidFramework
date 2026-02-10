package com.comcust.crm.generic.ObjectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage {
	
	public CreateContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='lastname']")
	private WebElement lastname;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement OrgNameLookUp;
	
	@FindBy(xpath = "//input[@name='support_start_date']")
	private WebElement startDate;
	
	@FindBy(xpath = "//input[@name='support_end_date']")
	private WebElement endDate;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBTN;

	public WebElement getLastname() {
		return lastname;
	}

	public WebElement getOrgNameLookUp() {
		return OrgNameLookUp;
	}

	public WebElement getStartDate() {
		return startDate;
	}

	public WebElement getEndDate() {
		return endDate;
	}

	public WebElement getSaveBTN() {
		return saveBTN;
	}
	
	
}
