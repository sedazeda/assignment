package suite;


import base.Base;
import common.CommonMethods;
import common.DataProviderInitializer;
import component_object.ComponentObjectsAdd;
import component_object.ComponentObjectsEdit;
import component_object.ComponentObjectsList;
import io.qameta.allure.*;
import model.Computer;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

import java.io.IOException;

import static org.testng.Assert.fail;

public class DeleteTestSuite extends Base {

    ComponentObjectsList componentObjectsList;
    ComponentObjectsAdd componentObjectsAdd;
    ComponentObjectsEdit componentObjectsEdit;
    Computer computer;

    @DataProvider(name = "deleteData")
    public Object[][] validComputerInfo() {

        return DataProviderInitializer.createDeleteData();
    }

    @BeforeMethod
    public void beforeMethod() {
        CommonMethods.readDataFromFile();
        super.beforeMethodChrome();
        ;
        driver.get(MAIN_PAGE_URL);

        componentObjectsList = new ComponentObjectsList(driver);
        componentObjectsAdd = new ComponentObjectsAdd(driver);
        componentObjectsEdit = new ComponentObjectsEdit(driver);


    }

    @Test(dataProvider = "deleteData", suiteName = "CRUD Operation Test Suite",
            testName = "Delete computer that is saved previously")
    @Description("Verify to delete existing computer")
    @Feature("Delete computer")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteComputerOperation(String computerName, String introducedDate, String discontinuedDate, String company, String status) {

        computer = createComputer(computerName, introducedDate, discontinuedDate, company, status);
        searchExistComputer(computerName);
        pickComputer();
        clickDeleteComputerButton();
        searchNonexistComputer(computerName);

    }


    @Step("Search computer")
    public void searchExistComputer(String computerName) {
        componentObjectsList.setFilterName(computerName);
        componentObjectsList.clickFilterButton();
        assertTrue(componentObjectsList.isComputerExists(computerName));

    }

    @Step("Search non-existing computer")
    public void searchNonexistComputer(String computerName) {
        componentObjectsList.setFilterName(computerName);
        componentObjectsList.clickFilterButton();
        //Assert.assertEquals(componentObjectsList.isComputerExists(computerName),false);
        assertTrue(componentObjectsList.nothingFound());

    }


    @Step("Pick computer")
    public void pickComputer() {

        WebElement computerToEdit = componentObjectsList.selectComputer(computer.getName());
        if (computerToEdit != null)
            componentObjectsList.clickComputerName(computerToEdit);
        else
            fail("Computer not found");

    }


    @Step("Click Delete Computer Button")
    public void clickDeleteComputerButton() {
        componentObjectsEdit.deleteBtnClick();
        assertTrue(componentObjectsList.signOfSuccess());
    }

    @AfterMethod
    public void afterMethod() {
        super.afterMethod();

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

}
