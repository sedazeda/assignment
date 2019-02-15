package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Base {

    public final static String MAIN_PAGE_URL = "http://computer-database.herokuapp.com/computers";
    protected static WebDriver driver;


    public void beforeMethodChrome() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();


    }

    public void beforeMethodFirefox() {

        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    public static void takeScreenshot(String filePath) {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File scrFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath);
        try {
            FileUtils.copyFile(scrFile, destFile.getAbsoluteFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void afterMethod() {
        driver.close();
    }

}
