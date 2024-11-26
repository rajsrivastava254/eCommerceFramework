package Automation_mobile_AppiumFramework;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import AppiumFramework.pageObjects.android.FormPage;
import AppiumFramework.pageObjects.android.ProductCatalogue;
import AppiumFramework.pageObjects.android.cartProduct;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseCaseFrame{
	

    protected FormPage formPage;
    protected ProductCatalogue productCatalogue;
    protected cartProduct cartPage;

	public AppiumDriverLocalService service;
	public WebDriverWait wait;
	public AndroidDriver driver;
	
    
    @BeforeClass(alwaysRun = true)
	public void ConfigureAppium() throws URISyntaxException, IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//AppiumFramework//resources//data.properties");
		prop.load(fis);
		String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		
		service = AppiumDriverLocalService.buildDefaultService();

		service.start();
		// Appium Code --> AppiumServer --> Mobile
		UiAutomator2Options options = new UiAutomator2Options();

		options.setDeviceName(prop.getProperty("AndroidDevice"));
		options.setChromedriverExecutable("C://Users//User1//Downloads//chromedriver-win64 - Hybrid//chromedriver-win64//chromedriver (2).exe");
		options.setApp(System.getProperty("user.dir")+"//src//main//java//AppiumFramework//resources//General-Store.apk");
		driver = new AndroidDriver(new URI("http://" + ipAddress + ":" + port).toURL(), options);//This converts the URI object to a URL object, which is required by the AndroidDriver constructor.
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		formPage = new FormPage(driver);
	
	}

	@AfterClass(alwaysRun = true)
	public void TearDown() {
		driver.quit();
		service.stop();
	}

}
