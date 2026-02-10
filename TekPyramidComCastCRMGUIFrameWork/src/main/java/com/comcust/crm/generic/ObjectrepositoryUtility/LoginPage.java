package com.comcust.crm.generic.ObjectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
  @author Basamma
  contains loginPage Elements
 */
public class LoginPage {

	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy (xpath = "//input[@name='user_name']")
	private WebElement UN;
	
	@FindBy(xpath = "//input[@name='user_password']")
	private WebElement PWD;
	
	@FindBy(id = "submitButton")
	private WebElement LoginBTN;

	public WebElement getUN() {
		return UN;
	}

	public WebElement getPWD() {
		return PWD;
	}

	public WebElement getLoginBTN() {
		return LoginBTN;
	}
	
	public void loginToApp(String url,String username,String password)
	{
		driver.manage().window().maximize();
		driver.get(url);
		UN.sendKeys(username);
		PWD.sendKeys(password);
		LoginBTN.click();
	}
	
	
}
