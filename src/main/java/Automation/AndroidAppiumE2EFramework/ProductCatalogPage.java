package Automation.AndroidAppiumE2EFramework;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCatalogPage extends AndroidActions{

    AndroidDriver driver;
	
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> addToCart;
    
    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartButton;
    
	public ProductCatalogPage(AndroidDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void addItemToCartByIndex(int index)
	{
		addToCart.get(index).click();
	}
	
	public CartPage goToCartPage() throws InterruptedException
	{
		cartButton.click();
		Thread.sleep(2000);
		return new CartPage(driver);
	}
}
