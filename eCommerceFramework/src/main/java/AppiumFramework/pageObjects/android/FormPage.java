package AppiumFramework.pageObjects.android;

import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions{
	
	AndroidDriver driver;
	WebDriverWait wait;
	
	public FormPage(AndroidDriver driver) {
		super(driver); //This ensures that any necessary initialization in the parent class is also performed.
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
	
	//@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']") 
	private WebElement femaleOption;
	
	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']") 
	private WebElement maleOption;
	
	@AndroidFindBy(id = "android:id/text1")
	private WebElement selectCountry;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement button;
	
	public void setNameField(String name) {
		nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.androidsample.generalstore:id/nameField")));
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}
	
	public void setGender(String gender) {
		if(gender.contains("Female")) {
			femaleOption.click();
		}
		else {
			maleOption.click();
		}
	}
	public void setCountrySelection(String countryName) {
		selectCountry.click();
		scrollToText(countryName);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
	}
	public ProductCatalogue submitForm() {
		button.click();
		return new ProductCatalogue(driver); //return here for not creating object everytime and if driver is not pass then next page is not initiated
	}
}
