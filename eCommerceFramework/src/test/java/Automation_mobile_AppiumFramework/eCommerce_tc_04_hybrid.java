package Automation_mobile_AppiumFramework;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppiumFramework.pageObjects.android.ProductCatalogue;
import AppiumFramework.pageObjects.android.cartProduct;
import AppiumFramework.utils.SearchHandler;

public class eCommerce_tc_04_hybrid extends BaseCaseFrame{
	
	
	@Test(groups = {"smoke"}, dataProvider = "userData", dataProviderClass = DataProviders.class)
	public void FillForm(HashMap<String, String> input) throws InterruptedException {
		
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));
		ProductCatalogue productCatalogue = formPage.submitForm(); //This is done to not create object everytime

		productCatalogue.addingProductToCart(0);
		productCatalogue.addingProductToCart(0);
		cartProduct cartpage = productCatalogue.goToCart();
		
		double totalSum = cartpage.getProductsSum();
		double displayFormattedSum = cartpage.getTotalAmountDisplayed();
		Assert.assertEquals(totalSum, displayFormattedSum);
		cartpage.acceptTermsConditions();
		cartpage.submitOrder();
		
	    SearchHandler searchHandler = new SearchHandler(driver);
	    searchHandler.searchInWebView("WEBVIEW_com.androidsample.generalstore", "Devstringx");
		}
}