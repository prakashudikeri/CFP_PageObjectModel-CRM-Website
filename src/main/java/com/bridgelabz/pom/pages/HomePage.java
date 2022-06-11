package com.bridgelabz.pom.pages;

import com.bridgelabz.pom.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;

public class HomePage extends TestBase {

    @FindBy(xpath = "//td[contains(text(), 'User: PRAKASH UDIKERI')]" )
    @CacheLookup                        //Page Refresh - StaleMemoryException throw
    WebElement userNameLabel;

    @FindBy(xpath = "//a[contains(text(), 'Contacts')]")
    @CacheLookup
    WebElement contactsLink;

    @FindBy(xpath = "//a[contains(text(), 'New Contact')]")
    @CacheLookup
    WebElement newContactLink;

    @FindBy(xpath = "//[contains(text(), 'Deals')]")
    WebElement dealsLink;

    @FindBy(xpath = "//[contains(text(), 'Tasks')]")
    WebElement tasksLink;

    //Initializing the page objects - constructor of HomePage
    public HomePage(){
        PageFactory.initElements(driver, this);
    }

    public String verifyHomePageTitle(){
        return driver.getTitle();
    }

    public boolean verifyCorrectUserName(){
        return userNameLabel.isDisplayed();
    }

    public ContactsPage clickOnContactsLink(){
        contactsLink.click();
        return new ContactsPage();
    }

    public DealsPage clickOnDealsLink(){
        dealsLink.click();
        return new DealsPage();
    }

    public TasksPage clickOnTaskLink(){
        tasksLink.click();
        return new TasksPage();
    }

    public void clickOnNewContactLink(){
        Actions action = new Actions(driver);
        action.moveToElement(contactsLink).build().perform();
        newContactLink.click();
    }


}

//Page Object Model (POM) Design With Selenium - Part -3 - 39:13 - framseset, user name
