package suite;


import base.Base;
import common.CommonMethods;
import common.DataProviderInitializer;
import component_object.ComponentObjectsAdd;
import component_object.ComponentObjectsEdit;
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

public class EditTestSuite extends Base {

    ComponentObjectsList componentObjectsList;
    ComponentObjectsAdd componentObjectsAdd;
    ComponentObjectsEdit componentObjectsEdit;


    @DataProvider(name = "validDataForEdit")
    public Object[][] validEditInfo() {
        return DataProviderInitializer.validDataForEdit();
    }

    @DataProvider(name = "invalidDataForEdit")
    public Object[][] invalidEditInfo() {
        return DataProviderInitializer.invalidDataForEdit();
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

    @Test(dataProvider = "invalidDataForEdit", suiteName = "CRUD Operation Test Suite",
            description = "Verify to do not save with the information that comes from invalid data set ",
            testName = "Update Information for previously added computer with invalid data")
    @Feature("Edit computer")
    @Severity(SeverityLevel.CRITICAL)
    public void editComputerOperationInvalid(String computerName, String introducedDate, String discontinuedDate, String company, String status) {

        Computer addedComputer = createComputer();
        searchComputer(addedComputer.getName());
        pickComputer(addedComputer.getName());
        fillInformationwithInValidData(computerName, introducedDate, discontinuedDate, company, status);
        submitComputerInformationforInvalidData();

    }

    @Test(dataProvider = "validDataForEdit", suiteName = "CRUD Operation Test Suite",
            description = "Verify to save changes with the information that comes from valid data set",
            testName = "Update Information for previously added computer with valid data")
    @Feature("Edit computer")
    @Severity(SeverityLevel.CRITICAL)
    public void editComputerOperationValid(String computerName, String introducedDate, String discontinuedDate, String company, String status) {

        Computer addedComputer = createComputer();
        searchComputer(addedComputer.getName());
        pickComputer(addedComputer.getName());
        fillInformationwithValidData(computerName, introducedDate, discontinuedDate, company, status);
        submitComputerInformationforValidData();
        searchComputer(computerName);
        checkComputerExist(computerName);

    }

    @Step("Search computer")
    public void searchComputer(String computerName) {
        componentObjectsList.setFilterName(computerName);
        componentObjectsList.clickFilterButton();
        assertTrue(componentObjectsList.isComputerExists(computerName));

    }

    @Step("Check computer")
    public void checkComputerExist(String computerName) {
        assertTrue(componentObjectsList.isComputerExists(computerName));
    }

    @Step("Pick computer")
    public void pickComputer(String computerName) {

        WebElement computerToEdit = componentObjectsList.selectComputer(computerName);
        if (computerToEdit != null)
            componentObjectsList.clickComputerName(computerToEdit);
        else
            fail("Computer not found");

    }

    @Step("Fill Informations as invalid")
    public void fillInformationwithInValidData(String computerName, String introducedDate, String discontinuedDate, String company, String status) {

        componentObjectsEdit.fillName(computerName);
        componentObjectsEdit.fillIntroducedDate(introducedDate);
        componentObjectsEdit.fillDiscontinuedDate(discontinuedDate);
        if (company != null || !company.equals(""))
            componentObjectsAdd.selectCompany(company);

    }

    @Step("Fill Informations as valid")
    public void fillInformationwithValidData(String computerName, String introducedDate, String discontinuedDate, String company, String status) {

        componentObjectsEdit.fillName(computerName);
        componentObjectsEdit.fillIntroducedDate(introducedDate);
        componentObjectsEdit.fillDiscontinuedDate(discontinuedDate);
        if (company != null || !company.equals(""))
            componentObjectsAdd.selectCompany(company);
    }

    @Step("Submit Save Computer Button for Valid Data")
    public void submitComputerInformationforValidData() {
        componentObjectsEdit.saveThisComputerBtnClick();
        assertTrue(componentObjectsList.signOfSuccess());

    }

    @Step("Submit Save Computer Button for invalid Data")
    public void submitComputerInformationforInvalidData() {
        componentObjectsEdit.saveThisComputerBtnClick();
        assertTrue(componentObjectsEdit.saveThisBtnAvailable());
    }

    public void clickDeleteComputerButton() {
        componentObjectsEdit.deleteBtnClick();
        assertTrue(componentObjectsList.signOfSuccess());

    }

    @AfterMethod
    public void afterMethod() {
        super.afterMethod();

    }

    private Computer createComputer() {
        componentObjectsList.clickAddButton();
        Computer computer = CommonMethods.getValidComputerData();
        componentObjectsAdd.fillName(computer.getName());
        componentObjectsAdd.fillIntroducedDate(computer.getDate());
        componentObjectsAdd.fillDiscontinuedDate(computer.getDiscontinuedDate());
        if (computer.getCompany() != null || !computer.getCompany().equals(""))
            componentObjectsAdd.selectCompany(computer.getCompany());
        componentObjectsAdd.createThisComputerBtnClick();
        return computer;
    }

}
