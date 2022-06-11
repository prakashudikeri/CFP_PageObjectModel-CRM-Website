package com.bridgelabz.pomtest;

import com.bridgelabz.pom.base.TestBase;
import com.bridgelabz.pom.pages.ContactsPage;
import com.bridgelabz.pom.pages.HomePage;
import com.bridgelabz.pom.pages.LoginPage;
import com.bridgelabz.pom.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ContactsPageTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    TestUtil testUtil;
    ContactsPage contactsPage;
    String sheetName = "contacts";

    public ContactsPageTest() {
        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization();
        testUtil = new TestUtil();
        contactsPage = new ContactsPage();
        loginPage = new LoginPage();
        homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password") );
        testUtil.switchToFrame();
        contactsPage = homePage.clickOnContactsLink();
    }

    @Test (priority = 1)
    public void verifyContactsPageLabelTest(){
        Assert.assertTrue(contactsPage.verifyContactsLabel(), "Contact Label is Missing on the Page");
    }

//    @Test (priority = 2)
//    public void selectContactsTest(){
//        contactsPage.selectContactsByName("dhbvsahk dsluhygslkjg");
//    }

    @Test (priority = 2)
    public void selectSingleContactsTest(){
        contactsPage.selectContactsByName("dhbvsahk dsluhygslkjg");
    }

    @Test (priority = 3)
    public void selectMultipleContactsTest(){
        contactsPage.selectContactsByName("dhbvsahk dsluhygslkjg");
        contactsPage.selectContactsByName("abc xyz");
    }

    @DataProvider
    public Object[][] getCRMTestData(){
        Object data[][] = TestUtil.getTestData(sheetName);
        return data;
    }

    @Test (priority = 4, dataProvider = "getCRMTestData")
    public void validateCreateNewContact(String title, String firstName, String lastName, String company){
//    public void validateCreateNewContact(){
        homePage.clickOnNewContactLink();
//        contactsPage.createNewContact("Mr.","Tom", "Peter", "Google");
        contactsPage.createNewContact(title, firstName, lastName, company);
    }

//    @AfterMethod
//    public void tearDown(){
//        driver.quit();
//    }

}