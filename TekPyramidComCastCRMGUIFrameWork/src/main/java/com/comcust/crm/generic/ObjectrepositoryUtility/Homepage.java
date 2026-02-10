package com.comcust.crm.generic.ObjectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {

    public Homepage(WebDriver driver)
    {
    	PageFactory.initElements(driver, this);
    }
	
	@FindBy(xpath = "//td[@class='tabUnSelected']/descendant::a[text()='Organizations']")
	private WebElement Orgnization;
	
	@FindBy(xpath = "//td[@class='tabUnSelected']/descendant::a[text()='Contacts']")
	private WebElement Contact;
	
    @FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
    private WebElement userIcon;
    
    @FindBy(xpath = "//a[contains(text(),'Sign Out')]")
    private WebElement SignOutBtn;

	public WebElement getOrgnization() {
		return Orgnization;
	}

	public WebElement getContact() {
		return Contact;
	}

	public WebElement getUserIcon() {
		return userIcon;
	}

	public WebElement getSignOutBtn() {
		return SignOutBtn;
	}
    
    

}
