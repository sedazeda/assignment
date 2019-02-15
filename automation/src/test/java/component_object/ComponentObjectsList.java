package component_object;

import common.CustomWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.logging.Logger;

import static base.Base.MAIN_PAGE_URL;


public class ComponentObjectsList {

    private WebDriver driver;

    public ComponentObjectsList(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    static Logger log;

    @FindBy(id = "add")
    private WebElement addNewComputer;

    @FindBy(xpath = "//*[@id=\"main\"]/div[1]/strong")
    private WebElement done;

    @FindBy(id = "searchbox")
    private WebElement filterField;

    @FindBy(id = "searchsubmit")
    private WebElement filterButton;

    @FindBy(xpath = "//*[@id=\"main\"]/table/tbody")
    private WebElement computerTable;

    @FindBy(xpath = "//*[@id=\"main\"]/div[2]/em")
    private WebElement nothingFound;

    public WebElement selectComputer(String computerName) {
        List<WebElement> computerList = computerTable.findElements(By.tagName("tr"));
        System.out.println("debug");
        WebElement selectedLine = computerList.stream().filter(element -> element.findElements(By.tagName("td")).get(0).findElement(By.tagName("a")).getText().equals(computerName)).findFirst().orElse(null);
        return selectedLine;
    }

    public void clickComputerName(WebElement computer) {
        computer.findElements(By.tagName("td")).get(0).findElement(By.tagName("a")).click();
        ;
    }

    public boolean isComputerExists(String computerName) {
        WebElement selectedComputer = this.selectComputer(computerName);
        String selectedComputerName = null;
        if (selectedComputer != null) {
            selectedComputerName = selectedComputer.findElements(By.tagName("td")).get(0).findElement(By.tagName("a")).getText();
        }

        return selectedComputerName == null ? false : selectedComputerName.equals(computerName);
    }

    public void setFilterName(String name) {
        filterField.clear();
        filterField.sendKeys(name);
    }

    public void clickFilterButton() {
        filterButton.click();
    }

    public void clickAddButton() {
        CustomWait.waitUntilElementVisible(driver, addNewComputer);
        addNewComputer.click();
    }

    public boolean isHomePage() {
        return driver.getCurrentUrl().equals(MAIN_PAGE_URL);
    }

    public boolean signOfSuccess() {
        return done.isDisplayed();
    }

    public boolean nothingFound() {
        return this.nothingFound.isDisplayed();
    }

}
