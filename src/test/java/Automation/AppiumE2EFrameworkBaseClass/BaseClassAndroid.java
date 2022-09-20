package Automation.AppiumE2EFrameworkBaseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import Automation.AndroidAppiumE2EFramework.FormPage;
import Utils.AppiumUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseClassAndroid extends AppiumUtils{
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;
	
	@BeforeClass(alwaysRun=true)
	public void ConfigureAppium() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Automation\\AppiumE2EFramewor\\Resources\\data.properties");
		prop.load(fis);
		
		String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		//String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		
		service = startAppiumServer(ipAddress,Integer.parseInt(port));

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("AndroidDeviceName"));
		//options.setChromedriverExecutable("D:\\Eclipse\\Appium\\chromedriver.exe");
		//options.setApp("D:\\Eclipse\\Appium\\Appium\\src\\test\\java\\Resources\\ApiDemos-debug.apk");
		options.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\Resources\\General-Store.apk");

		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);
	}
	
	@AfterClass(alwaysRun=true)
	public void quit()
	{
		driver.quit();
		service.stop();
	}
}
