package com.qa.automation.tests;

import com.qa.automation.pages.LoginPage;
import com.qa.automation.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {
        driver.get("https://www.saucedemo.com");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        
        String expectedURL = "https://www.saucedemo.com/inventory.html";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL, 
            "Login failed - URL does not match!");
    }

    @Test
    public void lockedOutUserTest() {
        driver.get("https://www.saucedemo.com");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");

        String expectedError = "Epic sadface: Sorry, this user has been locked out.";
        String actualError = loginPage.getErrorMessage();
        Assert.assertEquals(actualError, expectedError, 
            "Error message does not match!");
    }

    @Test
    public void invalidLoginTest() {
        driver.get("https://www.saucedemo.com");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "wrongpassword");

        String expectedError = "Epic sadface: Username and password do not match any user in this service";
        String actualError = loginPage.getErrorMessage();
        Assert.assertEquals(actualError, expectedError, 
            "Error message does not match!");
    }
}