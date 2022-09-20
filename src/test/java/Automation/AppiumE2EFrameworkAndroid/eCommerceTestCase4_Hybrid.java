package Automation.AppiumE2EFrameworkAndroid;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Automation.AndroidAppiumE2EFramework.CartPage;
import Automation.AndroidAppiumE2EFramework.ProductCatalogPage;
import Automation.AppiumE2EFrameworkBaseClass.BaseClassAndroid;
import io.appium.java_client.android.Activity;

public class eCommerceTestCase4_Hybrid extends BaseClassAndroid{

	@Test(dataProvider = "getData", groups = {"Smoke"})
	public void FillForm(String name, String gender, String country) throws InterruptedException
	//public void FillForm(HashMap<String, String> input) throws InterruptedException
	{
		formPage.setNameField(/*input.get("name")*/name);
		formPage.setGender(/*input.get("gender")*/gender);
		formPage.setCountrySelection(/*input.get("country")*/country);
		ProductCatalogPage productCatalogPage = formPage.submitForm();
		productCatalogPage.addItemToCartByIndex(0);
		productCatalogPage.addItemToCartByIndex(0);
		CartPage cartPage = productCatalogPage.goToCartPage();
		
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
		
		double totalSum = cartPage.getProductSum();
		double displayFormattedSum = cartPage.getTotalAmountDisplayed();		
		Assert.assertEquals(totalSum, displayFormattedSum);
		cartPage.acceptTermsConditions();
        cartPage.submitOrder();
	}
	
	/*@BeforeMethod(alwaysRun=true)
	public void preSetup()
	{
		//formPage.setActivity();
		/*Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
		Activity activity = new Activity("io.appium.android.apis","io.appium.android.apis.preference.PreferenceDependencies");
		driver.startActivity(activity);
	}*/
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		//List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir"+"\\src\\test\\java\\Automation\\GetData\\eCommerce.json"));
		return new Object[][] {{"test", "female", "Argentina"}/*,{"test1", "male", "Argentina"}*/};
		//return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}






















