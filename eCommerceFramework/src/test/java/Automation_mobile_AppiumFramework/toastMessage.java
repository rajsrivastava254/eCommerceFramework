package Automation_mobile_AppiumFramework;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class toastMessage extends BaseCaseFrame {
	
	@Test(groups = {"smoke"},dataProvider = "userData", dataProviderClass = DataProviders.class)
	public void incorrectFillForm(HashMap<String, String> input) throws InterruptedException {
		
		formPage.setNameField("");
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));
		formPage.submitForm(); 
		
		//for toast Message you have to comment the namefield
		String toastMessage = driver.findElement(AppiumBy.xpath("(//android.widget.Toast)[1]")).getText();
		Assert.assertEquals(toastMessage,"Please enter your name");
		
	}
}