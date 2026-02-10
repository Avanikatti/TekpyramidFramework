package SpotifyPom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SongsPage {

	public SongsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//div[@data-testid='tracklist-row']/descendant::a[@data-testid='internal-track-link']")
	private List<WebElement> songs;
	
	public List<WebElement> getSongs() {
		return songs;
	}

	public List<WebElement> getUsers() {
		return Users;
	}

	public List<WebElement> getDate() {
		return date;
	}

	@FindBy (xpath = "//div[@data-testid='tracklist-row']/descendant::div[@class='F_VvNCRKZ2cKj1a9']/div")
	private List<WebElement> Users;
	@FindBy (xpath = "//div[@data-testid='tracklist-row']/descendant::a[@data-testid='internal-track-link']/parent::div/parent::div/following-sibling::div/following-sibling::div/child::span[@data-encore-id='text']")
	private List<WebElement> date;

	
	}
	

