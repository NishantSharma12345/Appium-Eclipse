package Automation.AppiumE2EFrameworkAndroid;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Automation.AppiumE2EFrameworkBaseClass.BaseClassAndroid;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;

public class eCommerceTestCase1 extends BaseClassAndroid{

	@BeforeMethod
	public void preSetup()
	{
		Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
	}
	
	@Test
	public void FillForm_ErrorValidation()
	{
		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Nishant");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
			
		String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		Assert.assertEquals(toastMessage, "Please enter your name");
	}
	
	public void FillForm_PositiveFlow()
	{
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Nishant");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
			
		Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size()<1);
	}
}






















