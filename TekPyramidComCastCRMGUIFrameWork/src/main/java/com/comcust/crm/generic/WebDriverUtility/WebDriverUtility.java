package com.comcust.crm.generic.WebDriverUtility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	public void toWaitPagetoLoad(WebDriver driver,int time)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}
	public void toWaitPagetoLoadOncondition(WebDriver driver,int time,WebElement wb)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(wb));
	}
	public void toswitchToWindow(WebDriver driver,String expectedurl)
	{
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it=windows.iterator();
		while(it.hasNext())
		{
			String windowId = it.next();
			driver.switchTo().window(windowId);
			String actualUrl = driver.getCurrentUrl();
			if(actualUrl.contains(expectedurl))
			{
				break;
			}
		}
	}
	public void toSelectFromDropDown(WebElement wb,int indix)
	{
		Select sel=new Select(wb);
		sel.selectByIndex(indix);	
	}
	public void toSelectFromDropDown(WebElement wb,String value)
	{
		Select sel=new Select(wb);
		sel.selectByValue(value);
	}
	public void toSelectFromDropDown(String visibletext,WebElement wb)
	{
		Select sel=new Select(wb);
		sel.selectByVisibleText(visibletext);
	}
	public void toMouseHoverOnWebElement(WebDriver driver,WebElement wb)
	{
		Actions act=new Actions(driver);
		act.moveToElement(wb).perform();
	}
	public void toclickOnWebElement(WebDriver driver,WebElement wb)
	{
		Actions act=new Actions(driver);
		act.moveToElement(wb).click().perform();
	}
	public void switchToFrame(WebDriver driver,WebElement wb)
	{
		driver.switchTo().frame(wb);
	}

}
