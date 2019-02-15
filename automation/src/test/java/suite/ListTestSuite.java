package suite;


import base.Base;
import common.CommonMethods;
import common.DataProviderInitializer;
import component_object.ComponentObjectsAdd;
import component_object.ComponentObjectsList;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import model.Computer;
import org.openqa.selenium.WebElement;

import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import static org.testng.Assert.fail;

public class ListTestSuite extends Base {

    ComponentObjectsList componentObjectsList;
    ComponentObjectsAdd componentObjectsAdd;
    Computer computer;

    @DataProvider(name = "validComputerInfo")
    public Object[][] validComputerInfo() {
        return DataProviderInitializer.createValidDataForAdd();
    }


    @BeforeMethod
    public void beforeMethod() {
        CommonMethods.readDataFromFile();
        super.beforeMethodChrome();
        // super.beforeMethodFirefox();
        driver.get(MAIN_PAGE_URL);
        componentObjectsList = new ComponentObjectsList(driver);
        componentObjectsAdd = new ComponentObjectsAdd(driver);

    }

    @Test(dataProvider = "validComputerInfo",suiteName = "CRUD Operation Test Suite",
            description = "Verify to list a computer",
            testName = "Add computer with valid data.")
    @Feature("List computer")
    @Severity(SeverityLevel.CRITICAL)
    public void listComputer(String computerName, String introducedDate, String discontinuedDate, String company, String status) {
        /**contains;
         Try to search computer
         **/
        computer = createComputer(computerName, introducedDate, discontinuedDate, company, status);
        searchComputer(computer.getName());
        assertTrue(componentObjectsList.isComputerExists(computer.getName()));

    }

    @Step("Search computer")
    public void searchComputer(String computerName) {
        componentObjectsList.setFilterName(computerName);
        componentObjectsList.clickFilterButton();

    }

    @Step("Pick computer")
    public void pickComputer() {

        WebElement computerToEdit = componentObjectsList.selectComputer(computer.getName());
        if (computerToEdit != null)
            componentObjectsList.clickComputerName(computerToEdit);
        else
            fail("Computer not found");

    }

    private Computer createComputer(String computerName, String introducedDate, String discontinuedDate, String company, String status) {

        componentObjectsList.clickAddButton();
        componentObjectsAdd.fillName(computerName);
        componentObjectsAdd.fillIntroducedDate(introducedDate);
        componentObjectsAdd.fillDiscontinuedDate(discontinuedDate);
        if (company != null || !company.equals(""))
            componentObjectsAdd.selectCompany(company);
        componentObjectsAdd.createThisComputerBtnClick();
        return new Computer(computerName, introducedDate, discontinuedDate, company, status);
    }

    @AfterMethod
    public void afterMethod() {

        super.afterMethod();

    }

}
