package com.comcust.crm.generic.ObjectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {

	public OrganizationInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headerInform;
	
	@FindBy(xpath = "//td[@id='mouseArea_Organization Name']")
	private WebElement organizationName;
	
	@FindBy(xpath = "//td[@id='mouseArea_Industry']")
	private WebElement industry;
	
	@FindBy(xpath = "//td[@id='mouseArea_Type']")
	private WebElement Type;
	
	@FindBy(xpath = "//td[@id='mouseArea_Phone']")
	private WebElement phone;

	public WebElement getHeaderInform() {
		return headerInform;
	}

	public WebElement getOrganizationName() {
		return organizationName;
	}

	public WebElement getIndustry() {
		return industry;
	}

	public WebElement getType() {
		return Type;
	}

	public WebElement getPhone() {
		return phone;
	}
}
