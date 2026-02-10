package com.comcust.crm.generic.ObjectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationLookupPage {

	public OrganizationLookupPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//input[@id='search_txt']")
	private WebElement searchOrg;
	
	@FindBy(xpath = "//input[@name='search']")
	private WebElement searchBTN;


	//@FindBy(xpath = "//a[text()='"+ORGNAME+"']")
	//private WebElement orgname;
	public WebElement getSearchOrg() {
		return searchOrg;
	}

	public WebElement getSearchBTN() {
		return searchBTN;
	}
	
}
