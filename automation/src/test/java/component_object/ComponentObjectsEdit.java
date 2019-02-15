package component_object;

import common.CustomWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.logging.Logger;

import static base.Base.MAIN_PAGE_URL;


public class ComponentObjectsEdit {

    private WebDriver driver;

    public ComponentObjectsEdit(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    static Logger log;

    @FindBy(xpath = "//*[@id=\"main\"]/form[2]/input")
    private WebElement deleteButton;


    @FindBy(xpath = "//*[@id=\"main\"]/form[1]/div/a")
    private WebElement cancelButton;

    @FindBy(xpath = "//*[@id=\"main\"]/form[1]/div/input")
    private WebElement saveComputerButton;

    @FindBy(id = "name")
    private WebElement computerName;

    @FindBy(id = "introduced")
    private WebElement introducedDate;

    @FindBy(id = "discontinued")
    private WebElement discontinuedDate;

    @FindBy(id = "company")
    private WebElement companyName;


    public void fillName(String computerName) {
        this.computerName.clear();
        this.computerName.sendKeys(computerName);
    }

    public void fillIntroducedDate(String introducedDate) {
        this.introducedDate.clear();
        this.introducedDate.sendKeys(introducedDate);
    }

    public void fillDiscontinuedDate(String discontinuedDate) {
        this.discontinuedDate.clear();

        this.discontinuedDate.sendKeys(discontinuedDate);
    }

    public void selectCompany(String companyName) {
        this.companyName.click();
        Select select = new Select(this.companyName);
        try {
            select.selectByVisibleText(companyName);
        } catch (Exception e) {
            System.out.println("Company name is: " + companyName);
        }
    }


    public void cancelBtnClick() {
        cancelButton.click();
    }

    public void deleteBtnClick() {
        deleteButton.click();
    }

    public void saveThisComputerBtnClick() {
        CustomWait.waitUntilElementVisible(driver, saveComputerButton);
        saveComputerButton.click();
    }

    public boolean saveThisBtnAvailable() {
        return saveComputerButton.isDisplayed();
    }


    public void navigateToHome() {
        driver.get(MAIN_PAGE_URL);
    }


}
