package task;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.comcust.crm.generic.fileUtility.ExcelUtility;

import SpotifyPom.HomePage;
import SpotifyPom.LoginPage;
import SpotifyPom.SongsPage;
import SpotifyPom.SpotipyExcel;

public class TaskSpotify {
	@Test
	public void spotify() throws InterruptedException, EncryptedDocumentException, IOException
	{
	ChromeOptions options = new ChromeOptions();
	options.addArguments("user-data-dir=C:/selenium/profile");
	
	SpotipyExcel elib=new SpotipyExcel();
	String singer1 = elib.togetDataFromExcelFile("sheet1", 1, 0);
	String singer2 = elib.togetDataFromExcelFile("sheet1", 2, 0);
	String singer=singer1;
	for(int j=2;j<4;j++)
    {
	WebDriver driver=new ChromeDriver(options);
	driver.manage().window().maximize();
	driver.get("https://accounts.spotify.com/en/login?continue=https%3A%2F%2Fopen.spotify.com%2F");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
			LoginPage lp=new LoginPage(driver);
		   lp.getUserName() .sendKeys("kattibasamma@gmail.com");
		    lp.getLoginBTN().click();
		    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(70));
		     wait.until(ExpectedConditions.urlContains("https://open.spotify.com"));
		    HomePage hp=new HomePage(driver);
		     hp.getSearchfield().sendKeys(singer);
		     hp.getSearchfield().submit();
		     hp.getSingerBTN().click();
		     singer=singer2;
		     SongsPage sp=new SongsPage(driver);
		     List<WebElement> suggettionsongs = sp.getSongs();
		     	for(int i=1;i<=5;i++)
		     	{
		     		String songNAME=suggettionsongs.get(i).getText();
		     		elib.setDatBackToExcel("Sheet"+j+"", i, 0, songNAME );
		     	}
		     List<WebElement> dates= sp.getDate();
		     dates.size();
		     	for(int i=1;i<=5;i++)
		     	{
		     		String noUsers = dates.get(i).getText();
		     		elib.setDatBackToExcel("Sheet"+j+"", i, 1,noUsers);
		     	}
		     hp.getUserIcon().click();
		     Thread.sleep(3000);
		     hp.getLogout().click();
		     driver.close();
		     driver.quit();
		     Thread.sleep(5000);
			}
	
			}
		}
