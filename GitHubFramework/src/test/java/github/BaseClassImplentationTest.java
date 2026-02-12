package github;

import org.testng.Reporter;
import org.testng.annotations.Test;
import utility.BaseClass;

public class BaseClassImplentationTest extends BaseClass{
	@Test
	public void createAccount()
	{
		Reporter.log("creating account", true);
	}
	@Test
	public void updateAccount()
	{
		Reporter.log("updating account", true);
	}
	@Test
	public void deleteAccount()
	{
		Reporter.log("deleting account", true);
	}
	
}
