

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class InvocationCountThreadPlSzEnable {

	@Test(invocationCount = 5)
	public void createAccount()
	{
		Reporter.log("creating account", true);
	}
	@Test(invocationCount = 5,threadPoolSize = 5)
	public void updateAccount()
	{
		Reporter.log("updating account", true);
	}
	@Test(enabled=false)
	public void deleteAccount()
	{
		Reporter.log("deleting account", true);
	}
}
