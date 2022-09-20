package Utils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;

public class Listeners extends AppiumUtils implements ITestListener {

	ExtentReports extent = ExtentReporterNG.getReporterObject();
	ExtentTest test;
	AppiumDriver driver;
	
	public void onTestStart(ITestResult result) 
	{
		test = extent.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) 
	{
		test.log(Status.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) 
	{
		test.fail(result.getThrowable());
		
		try 
		{
			driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		try 
		{
			test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(), driver), result.getMethod().getMethodName());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
	
	}

	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext context) 
	{
		extent.flush();
	}

	
}
