package SpotifyPom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	@FindBy (xpath = "//input[@id='username']")
	private WebElement UserName;
	
	public WebElement getUserName() {
		return UserName;
	}

	public WebElement getLoginBTN() {
		return LoginBTN;
	}
	@FindBy(xpath = "//span[text()='Continue']")
	private WebElement LoginBTN;
}
