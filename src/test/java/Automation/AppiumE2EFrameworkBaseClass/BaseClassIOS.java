package Automation.AppiumE2EFrameworkBaseClass;

import java.io.FileInputStream;
import java.io.IOException;

import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import Automation.IOSAppiumE2EFramework.HomePage;
import Utils.AppiumUtils;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseClassIOS  extends AppiumUtils{
	
	public IOSDriver driver;
	public AppiumDriverLocalService service;
	public HomePage homePage;
	
	@BeforeClass(alwaysRun=true)
	public void ConfigureAppium() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Automation\\AppiumE2EFramewor\\Resources\\data.properties");
		prop.load(fis);
		String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		
		service = startAppiumServer(ipAddress,Integer.parseInt(port));

		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName("iPhone 13 Pro");
		//options.setApp("D:\\Eclipse\\Appium\\Appium\\src\\test\\java\\Resources\\General-Store.app");
		options.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\Resources\\TestApp 3.app");
		options.setWdaLaunchTimeout(Duration.ofSeconds(20));

		driver = new IOSDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		homePage = new HomePage(driver);
	}
	
	@AfterClass(alwaysRun=true)
	public void quit()
	{
		driver.quit();
		service.stop();
	}
}
