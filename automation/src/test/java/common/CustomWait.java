package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomWait {
    private static final int TIMEOUT_WAIT_SECONDS = 10;

    public static void waitUntilElementVisible(WebDriver driver, WebElement element) {
        WebElement myDynamicElement = (new WebDriverWait(driver, TIMEOUT_WAIT_SECONDS)).
                until(ExpectedConditions.visibilityOf(element));
    }


    public static void waitUntilElementClickable(WebDriver driver, WebElement element) {
        WebElement myDynamicElement = (new WebDriverWait(driver, TIMEOUT_WAIT_SECONDS)).
                until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitInSeconds(WebDriver webDriver,long waitInSeconds) throws Exception{
        (new WebDriverWait(webDriver,waitInSeconds)).wait();
    }
}
