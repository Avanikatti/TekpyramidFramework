package github;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class PriorityTest {

	@Test(priority=1)
	public void createAccount()
	{
		//Assert.assertTrue(false);
		Reporter.log("creating account", true);
	}
	@Test(priority=2)
	public void updateAccount()
	{
		Reporter.log("updating account", true);
	}
	@Test(priority=3)
	public void deleteAccount()
	{
		Reporter.log("deleting account", true);
	}
}
