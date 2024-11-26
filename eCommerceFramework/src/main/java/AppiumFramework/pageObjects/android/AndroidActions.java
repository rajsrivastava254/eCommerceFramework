package AppiumFramework.pageObjects.android;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;


public class AndroidActions {
	AndroidDriver driver;
	
	public AndroidActions(AndroidDriver driver) {
		this.driver = driver; 
	}

	public void longPressAction(WebElement ele) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 3000));
		Thread.sleep(2000);
	}

//	public void ScrollDownAction() {
//		boolean canScrollMore;
//		do {
//
//			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript(
//				    "mobile: scrollGesture", ImmutableMap.<String, Object>builder()
//				        .put("left", 100)
//				        .put("top", 100)
//				        .put("width", 200)
//				        .put("height", 200)
//				        .put("direction", "down")
//				        .put("percent", 1.0)
//				        .build()
//				);
//		} while (canScrollMore);
//	}
	
	public void scrollToText(String text) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
	}

	public void SwipeAction(WebElement FirstId, String direction) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) FirstId).getId(), "duration", 3000, "direction", direction, "percent", 0.75));

	}

	public void DragDropAction(WebElement Drag, int endX, int endY) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) Drag).getId(), "endX", endX, "endY", endY));
	}
	public double getFormattedAmount(String amountString) {
		amountString = amountString.replace("$", "");
		return Double.parseDouble(amountString);
	}
}
