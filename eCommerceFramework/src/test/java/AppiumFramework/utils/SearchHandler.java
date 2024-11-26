package AppiumFramework.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class SearchHandler {

    private AndroidDriver driver;
    private ContextSwitcher contextSwitcher;

    public SearchHandler(AndroidDriver driver) {
        this.driver = driver;
        this.contextSwitcher = new ContextSwitcher(driver);
    }

    public void searchInWebView(String webViewContext, String searchText) throws InterruptedException {
        // Switch to WebView context
        contextSwitcher.switchToWebView(webViewContext);

        // Perform search
        driver.findElement(By.name("q")).sendKeys(searchText);
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        // Press back key to return to previous page
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        // Switch back to native app context
        contextSwitcher.switchToNativeApp();
    }
}
