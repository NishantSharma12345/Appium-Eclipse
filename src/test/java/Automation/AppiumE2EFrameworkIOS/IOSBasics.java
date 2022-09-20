package Automation.AppiumE2EFrameworkIOS;

import org.testng.Assert;
import org.testng.annotations.Test;

import Automation.AppiumE2EFrameworkBaseClass.BaseClassIOS;
import Automation.IOSAppiumE2EFramework.AlertViewsPage;

public class IOSBasics extends BaseClassIOS{

	@Test
	public void IOSBasicsTest()
	{
		AlertViewsPage alertViewsPage = homePage.selectAlertViews();
		alertViewsPage.fillTextLabel("hello");
		String actualMessage = alertViewsPage.getConfirmMessage();
	    Assert.assertEquals(actualMessage, "A message should be a short, complete sentence.");
	    alertViewsPage.confirmPopup();
	}
}

















