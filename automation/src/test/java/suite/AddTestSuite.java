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
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.fail;
import static org.testng.AssertJUnit.assertTrue;

public class AddTestSuite extends Base {

    ComponentObjectsList componentObjectsList;
    ComponentObjectsAdd componentObjectsAdd;
    ComponentObjectsEdit componentObjectsEdit;
    List<String> addedComputerName;

    @DataProvider(name = "validComputerInfo")
    public Object[][] validComputerInfo() {

        return DataProviderInitializer.createValidDataForAdd();
    }

    @DataProvider(name = "invalidComputerInfo")
    public Object[][] invalidComputerInfo() {

        return DataProviderInitializer.createInvalidDataForAdd();
    }

    @BeforeMethod
    public void beforeMethod() {
        CommonMethods.readDataFromFile();
        super.beforeMethodChrome();
        // super.beforeMethodFirefox();
        driver.get(MAIN_PAGE_URL);
        componentObjectsList = new ComponentObjectsList(driver);
        componentObjectsAdd = new ComponentObjectsAdd(driver);
        componentObjectsEdit = new ComponentObjectsEdit(driver);
        addedComputerName = new ArrayList<>();

    }

    @Test(dataProvider = "validComputerInfo", suiteName = "CRUD Operation Test Suite",
            testName = "Add computer with valid data.",
            description = "Verify to adding a new computer"
    )
    @Feature("Adding computer")
    @Severity(SeverityLevel.CRITICAL)
    public void addComputerOperation(String computerName, String introducedDate, String discontinuedDate, String company, String status) {
        /**contains;
         Try to save  Computer Name
         Try to save  dates as formatted
         Try to change company name
         **/
        clickAddComputerButton();
        fillInformationwithValidData(computerName, introducedDate, discontinuedDate, company, status);
        submitComputerInformationforValidData();
        searchComputer(computerName);
        addedComputerName.add(computerName);

    }


    @Test(dataProvider = "invalidComputerInfo", suiteName = "CRUD Operation Test Suite",
            description = "Verify to do not save if the data is invalid",
            testName = "Add computer with invalid data.")
    @Feature("Adding computer")
    @Severity(SeverityLevel.MINOR)
    public void addComputerOperationInvalid(String computerName, String introducedDate, String discontinuedDate, String company, String status) {
        /**contains;
         Try to save empty Computer Name
         Try to save invalid date
         Try to save without mandatory field (computer name)

         **/

        clickAddComputerButton();
        fillInformationwithInValidData(computerName, introducedDate, discontinuedDate, company, status);
        submitComputerInformationforInvalidData();

    }

    @Step("Click Add Computer Button")
    public void clickAddComputerButton() {
        componentObjectsList.clickAddButton();
        assertTrue(componentObjectsAdd.createThisBtnAvailable());
    }

    @Step("Fill Informations as invalid")
    public void fillInformationwithInValidData(String computerName, String introducedDate, String discontinuedDate, String company, String status) {

        componentObjectsAdd.fillName(computerName);
        componentObjectsAdd.fillIntroducedDate(introducedDate);
        componentObjectsAdd.fillDiscontinuedDate(discontinuedDate);
        if (company != null && !company.equals(""))
            componentObjectsAdd.selectCompany(company);

    }

    @Step("Fill Informations as valid")
    public void fillInformationwithValidData(String computerName, String introducedDate, String discontinuedDate, String company, String status) {

        componentObjectsAdd.fillName(computerName);
        componentObjectsAdd.fillIntroducedDate(introducedDate);
        componentObjectsAdd.fillDiscontinuedDate(discontinuedDate);
        if (company != null || !company.equals(""))
            componentObjectsAdd.selectCompany(company);

    }

    @Step("Submit Create Computer Button for Valid Data")
    public void submitComputerInformationforValidData() {
        componentObjectsAdd.createThisComputerBtnClick();
        assertTrue(componentObjectsList.isHomePage());
        assertTrue(componentObjectsList.signOfSuccess());


    }

    @Step("Search computer")
    public void searchComputer(String computerName) {
        componentObjectsList.setFilterName(computerName);
        componentObjectsList.clickFilterButton();
        assertTrue(componentObjectsList.isComputerExists(computerName));

    }


    @Step("Submit Create Computer Button for Invalid Data")
    public void submitComputerInformationforInvalidData() {
        componentObjectsAdd.createThisComputerBtnClick();
        assertTrue(componentObjectsAdd.createThisBtnAvailable());

    }


    @Step("Pick computer")
    public void pickComputer(String computerName) {

        WebElement computerToEdit = componentObjectsList.selectComputer(computerName);
        if (computerToEdit != null)
            componentObjectsList.clickComputerName(computerToEdit);
        else
            fail("Computer not found");

    }


    public void deleteData(String computerName) {
        componentObjectsList.setFilterName(computerName);
        componentObjectsList.clickFilterButton();
        pickComputer(computerName);
        componentObjectsEdit.deleteBtnClick();

    }


    @AfterMethod
    public void afterMethod() {
        addedComputerName.stream().forEach(s -> deleteData(s));

        super.afterMethod();

    }

}
