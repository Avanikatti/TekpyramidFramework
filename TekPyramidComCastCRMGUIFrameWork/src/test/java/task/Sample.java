package task;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Sample {
	
	
      @Test
	public void createspotify() throws InterruptedException, EncryptedDocumentException, IOException {

		//Webdriverutility wlib = new Webdriverutility();
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://open.spotify.com/");

	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.findElement(By.xpath("//span[text()='Log in']")).click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("kattibasamma@gmail.com");

		Thread.sleep(5000);

		driver.findElement(By.xpath("//span[contains(text(),'Continue')]")).click();

		Thread.sleep(4000);



	WebElement	search=driver.findElement(By.xpath("//input[@data-testid='search-input']"));
    search.sendKeys("Arjith singh");
    search.submit();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@class='e-91000-icon e-91000-baseline MpoH5sdgCUbPL5LCl3Cy']")).click();

		Thread.sleep(2000);

		driver.findElement(By.xpath("(//a[@class='KCkeejQ6OlALo5WqPpnw']/descendant::span)[4]")).click();

		Thread.sleep(2000);

		driver.findElement(By.xpath("(//span[contains(text(),'VijayPrakash')])[3]")).click();

		List<WebElement> songs = driver.findElements(By.xpath("//div[@class='V3hMbl6JubddBUkF2XKw itxBsCHKFwTOvmERT1cg']"));

		

		FileInputStream fis = new FileInputStream("C:\\Users\\Basamma\\OneDrive\\Desktop\\DDT\\SpotifuData.xlsx");

		Workbook wb = WorkbookFactory.create(fis);

		Sheet sh = wb.getSheet("Sheet4");



		for(int i=0;i<Math.min(5, songs.size());i++)

		{

//			for (int i = 0; i < Math.min(5, songs.size()); i++) {

//			    System.out.println(songs.get(i).getText());

//			}

			String song = songs.get(i).getText();

			System.out.println(song);

			Row cel = sh.createRow(i);

			cel.createCell(0).setCellValue(song);

		}

		

		FileOutputStream fos = new FileOutputStream("C:\\Users\\Basamma\\OneDrive\\Desktop\\DDT\\SpotifuData.xlsx");

		wb.write(fos);

		wb.close();

		fis.close();

		fos.close();

		driver.quit();
}
}
