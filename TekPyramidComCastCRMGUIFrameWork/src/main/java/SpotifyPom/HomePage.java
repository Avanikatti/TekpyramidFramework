package SpotifyPom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "(//input[@data-encore-id='formInput'])[1]")
	private WebElement Searchfield;
	
	@FindBy(xpath = "//a[@class='RHD86oLu2vEXXZm6']")
	private WebElement SingerBTN;
	
	@FindBy(xpath="//button[@data-testid='user-widget-link']")
	private WebElement UserIcon;
	@FindBy(xpath="//button[@data-testid='user-widget-dropdown-logout']/descendant::span")
	private WebElement logout;
	
	

	public WebElement getUserIcon() {
		return UserIcon;
	}

	public WebElement getLogout() {
		return logout;
	}

	public WebElement getSearchfield() {
		return Searchfield;
	}

	public WebElement getSingerBTN() {
		return SingerBTN;
	}
}
