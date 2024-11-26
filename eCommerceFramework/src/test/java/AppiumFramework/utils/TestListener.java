package AppiumFramework.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {

    ExtentTest test;
    ExtentReports extent;
    AndroidDriver driver;

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());

        try {
            driver = (AndroidDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        if (driver != null) {
            try {
                String testCaseName = result.getMethod().getMethodName();
                File source = driver.getScreenshotAs(OutputType.FILE);
                String destinationFile = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
                FileUtils.copyFile(source, new File(destinationFile));
                test.addScreenCaptureFromPath(destinationFile, testCaseName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            test.log(Status.FAIL, "Driver is null, screenshot not captured");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
