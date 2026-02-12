package utility;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseClass {

	@BeforeSuite
	public void beforeSuite()
	{
		Reporter.log("before suite", true);
	}
	@AfterSuite
	public void afterSuite()
	{
		Reporter.log("afterSuit", true);
	}
	@BeforeTest
	public void bforeTest()
	{
		Reporter.log("before test", true);
	}
	@AfterTest
	public void afterTest()
	{
		Reporter.log("afterTest", true);
	}
	@BeforeClass
	public void beforeClass()
	{
		Reporter.log("beforeClass", true);
	}
	@AfterClass
	public void afterClass()
	{
		Reporter.log("afterClass", true);
	}
	@BeforeMethod
	public void beforeMethod()
	{
		Reporter.log("beforeMethod", true);
	}
	@AfterMethod
	public void afterMethod()
	{
		Reporter.log("afterMethod", true);
	}
	@BeforeSuite
	public void beforeSuite1()
	{
		Reporter.log("Before_Suit1", true);
	}
	
	
}
