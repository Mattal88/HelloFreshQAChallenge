package com.testdrivers;

import com.webdriver.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * This Class consists of the tests as mentioned in the challenge , This class extends TestTask1Base
 */
public class TestTask1LoginRegisterAndCheckout extends TestTask1Base {

    Map<String,String> userDetails = new HashMap<String, String>();
    private final String TEST_USER_FIRST_NAME="Mocky";
    private final String TEST_USER_LAST_NAME="Mocker";

    /**
     * Test1 : Test to Register New User
     * <br> Test Steps:
     * <br> i)Given : The user is on the Home Screen , The screen which is shown when the user clicks on the Hello Fresh url , parametrized through maven
     * <br> ii) When : The User Clicks on the login Button
     * <br> iii) The User then click on the register new user link
     * <br> iv) The user fills in his details , The username and password are parametrized through maven , it is assumed a valid email and password in given in maven command line
     * <br> v) Then : After Filling in the details , The user clicks on Register button
     * <br> vi) The user goes to the account details page , and validates his email , first name and the last name
     * <br> vii) User Logs out
     * <br> viii) This completes the first test to register a user
     */
    @Test
    public void testNewRegistration(){
        //Click On the Login Button
        LoginWidgetPage loginWidgetPage = homePage.clickLoginButton();
        //Click on the registration link
        RegisterWidgetPage registerWidgetPage = loginWidgetPage.clickOnRegisterUser();
        //Fill in the registration details
        RegisterWidgetPage.RegistrationDetails registrationDetails = new RegisterWidgetPage.RegistrationDetails("Male",
                TEST_USER_FIRST_NAME,TEST_USER_LAST_NAME,userName,password,"10","05","1995");
        //registerWidgetPage
        registerWidgetPage.registerUser(registrationDetails);
        //Click on register
        registerWidgetPage.clickOnRegister();
        //Check whether user account has appeared
        UserAccountDetailsPage userAccountDetailsPage = goToAccountDetailsPage();
        Map<String,String> registerdUserDetails =userAccountDetailsPage.getUserDetails();
        Assert.assertTrue(registerdUserDetails.get(userAccountDetailsPage.localizedResourceBundle.getObject("emailkey").toString()).equalsIgnoreCase(registrationDetails.getEmail()));
        Assert.assertTrue(registerdUserDetails.get("Name").equalsIgnoreCase(registrationDetails.getFirstName()+" "+registrationDetails.getLastName()));
        Assert.assertTrue(registerdUserDetails.get("Gender").equalsIgnoreCase(registrationDetails.getGender()));
        userAccountDetailsPage.logOut();
    }

    /**
     * Test2: Test to Login with existing user created in Test1, and Add Classic box/plan to cart and validate that box and price are Ok in checkout page
     * <br> Test Steps :
     * <br> i) Given : The User is on the Home Screen
     * <br> ii) When : The User clicks on Login button
     * <br> iii) Enter the username and password for the account created  and Click on Login
     * <br> iv) Click on view boxes
     * <br> v) Select Classic box , persist the box price to compare in checkout page
     * <br> vi) Click on continue to checkout
     * <br> vii) The Checkout page should be displayed to the user
     * <br> viii) Then : Assert the Classic box and Price
     */
    @Test(dependsOnMethods = {"testNewRegistration"})
    public void testLoginAndSelectClassicBoxAndCheckout(){
        //Login with existing user
        LoginWidgetPage loginWidgetPage = homePage.clickLoginButton();
        loginWidgetPage.loginWithValidCredentials(userName,password);
        //Go to food boxes to select classic plan
        HomePage homePage = goToHomePage();
        //Click on go to view plans
        FoodBoxesPage foodBoxesPage = homePage.goToViewPlans(locale);
        //Select Classic box and store the classic box price
        ClassicFoodBoxSelectionOrderPage classicFoodBoxSelectionOrderPage = foodBoxesPage.selectClassic();
        String planPrice = classicFoodBoxSelectionOrderPage.getPriceTagOfPlan();
        //Click on continue to checkout
        CheckoutPage checkoutPage = classicFoodBoxSelectionOrderPage.clickCheckout();
        String plan = checkoutPage.getPlanNameFromCheckout();
        String price = checkoutPage.getTotalPriceCheckout();
        //Assert the Box menu type to be classic and assert the price to be the same as persisted in classic box order
        Assert.assertEquals("Classic",plan.substring(0,"Classic".length()));
        Assert.assertEquals(price,planPrice,"The prices dont match");
    }
}
