package com.comcust.crm.generic.ObjectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrganizationPage {

	public CreateOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//input[@name='accountname']")
	private WebElement organizationName;
	
	@FindBy(xpath = "//textarea[@name='bill_street']")
	private WebElement street;
	
	@FindBy(xpath = "//input[@id='bill_city']")
	private WebElement city;
	
	@FindBy(xpath = "//input[@name='bill_state']")
	private WebElement state;
	
	@FindBy(xpath = "//input[@name='bill_code']")
	private WebElement pincode;
	
	@FindBy(xpath = "//input[@name='bill_country']")
	private WebElement country;
	
	@FindBy(xpath = "//b[text()='Copy Billing address']/preceding-sibling::input")
	private WebElement copyAddress;
	
	@FindBy(xpath = "//select[@name='industry']")
	private WebElement industry;
	
	@FindBy(xpath = "//select[@name='accounttype']")
	private WebElement type;
	
	@FindBy(xpath = "//input[@name='phone']")
	private WebElement phone;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	
	public WebElement getOrganizationName() {
		return organizationName;
	}

	public WebElement getStreet() {
		return street;
	}

	public WebElement getCity() {
		return city;
	}

	public WebElement getState() {
		return state;
	}

	public WebElement getPincode() {
		return pincode;
	}

	public WebElement getCountry() {
		return country;
	}

	public WebElement getCopyAddress() {
		return copyAddress;
	}

	public WebElement getIndustry() {
		return industry;
	}

	public WebElement getType() {
		return type;
	}

	public WebElement getPhone() {
		return phone;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	
}
