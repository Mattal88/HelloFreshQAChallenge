package com.webdriver.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Locale;

/**
 *  Page Object class representing the page attributes and services offered by the Home page
 *  <br> This is the page where the user lands when he clicks on the base url or home url of "HelloFresh" website
 */
public class HomePage {

    //Page Object Locators , To be used in test code, We cannot directy use this from Resource Bundles as annotations require constants
    private final String LOGIN_BUTTON_IDENTIFIER = "login-button";
    private final String VIEW_PLANS_BUTTON_IDENTIFIER = "banner-view-plans-button";
    private final String HOME_ICON_LOCATOR= ".//*[@id='nav-navigation']/section/ul/li/a/img";

    //Page Object elements
    @FindBy(id = LOGIN_BUTTON_IDENTIFIER)
    private WebElement loginButton;

    @FindBy(id = VIEW_PLANS_BUTTON_IDENTIFIER)
    private WebElement viewOurPlans;

    @FindBy(className = "mls summary-item-name")
    private WebElement selectedBox;

    @FindBy(className = "sushi-detail-price")
    private WebElement totalPrice;

    private WebDriver driver;

    private Logger logger = Logger.getLogger(HomePage.class);
    private WebDriverWait HomePageWait;
    private static final int HOME_PAGE_OBJECT_TIME_OUT_PERIOD = 25;
    private static final int HOME_PAGE_OBJECT_POLL_PERIOD = 100;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        HomePageWait = new WebDriverWait(driver,HOME_PAGE_OBJECT_TIME_OUT_PERIOD,HOME_PAGE_OBJECT_POLL_PERIOD);
    }

    public LoginWidgetPage clickLoginButton(){
        HomePageWait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        LoginWidgetPage loginWidgetPage = new LoginWidgetPage(driver);
        return loginWidgetPage;
    }

    public void waitHomePageReady(){
        HomePageWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(HOME_ICON_LOCATOR)));
    }

    public boolean isLoginPresent(){
        HomePageWait.until(ExpectedConditions.elementToBeClickable(By.id(VIEW_PLANS_BUTTON_IDENTIFIER)));
        return driver.findElement(By.id(LOGIN_BUTTON_IDENTIFIER)).isDisplayed();
    }

    public FoodBoxesPage goToViewPlans(Locale locale){
        HomePageWait.until(ExpectedConditions.elementToBeClickable(By.id(VIEW_PLANS_BUTTON_IDENTIFIER)));
        viewOurPlans.click();
        return new FoodBoxesPage(driver,locale);
    }

    public void goToHomePage(){
        waitHomePageReady();
        driver.findElement(By.xpath(HOME_ICON_LOCATOR)).click();
        HomePageWait.until(ExpectedConditions.elementToBeClickable(By.id(VIEW_PLANS_BUTTON_IDENTIFIER)));
    }

}
