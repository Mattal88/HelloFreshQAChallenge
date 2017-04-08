package com.webdriver.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by matshaik on 4/6/2017.
 */
public class LoginWidgetPage {


    @FindBy(id = "email-input")
    private WebElement emailInput;

    @FindBy(id = "password-input")
    private WebElement passwordInput;

    @FindBy(className = "checkbox m-t-1")
    private WebElement rememberMeCheckBox;

    @FindBy(id = "submit-login-button")
    private WebElement submitLoginButton;

    @FindBy(id = "register-user-link")
    private WebElement registerUser;

    private WebDriverWait LoginWidgetPageWait;
    private static final int LOGIN_WIDGET_PAGE_OBJECT_TIME_OUT_PERIOD = 5;
    private static final int LOGIN_WIDGET_PAGE_OBJECT_POLL_PERIOD = 100;

    private WebDriver driver;

    public LoginWidgetPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        LoginWidgetPageWait = new WebDriverWait(driver,LOGIN_WIDGET_PAGE_OBJECT_TIME_OUT_PERIOD,LOGIN_WIDGET_PAGE_OBJECT_POLL_PERIOD);
    }


    public void loginWithValidCredentials(String userName,String password){
        LoginWidgetPageWait = new WebDriverWait(driver,LOGIN_WIDGET_PAGE_OBJECT_TIME_OUT_PERIOD,LOGIN_WIDGET_PAGE_OBJECT_POLL_PERIOD);
        emailInput.sendKeys(userName);
        passwordInput.sendKeys(password);
        System.out.println("username"+userName+"password"+password+"sksnm");
        LoginWidgetPageWait.until(ExpectedConditions.elementToBeClickable(submitLoginButton));
        submitLoginButton.click();
    }

    public boolean isLoginButtonClickable(){
        return submitLoginButton.isEnabled();
    }

    public void clickLoginButton(){
        if(isLoginButtonClickable()){
         submitLoginButton.click();
        }else{
            throw new RuntimeException("login button is not enabled");
        }
    }

    public RegisterWidgetPage clickOnRegisterUser(){
        LoginWidgetPageWait.until(ExpectedConditions.elementToBeClickable(registerUser));
        registerUser.click();
        return new RegisterWidgetPage(driver);
    }
}
