package com.webdriver.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.*;

/**
 * Created by matshaik on 4/8/2017.
 */
public class UserAccountDetailsPage {

    public  ResourceBundle localizedResourceBundle;

    private static final String ACCOUNT_TABLE_XPATH_LOCATOR = ".//*[@id='ginger']/section[1]/div/div/div/div/div/section/table";
    private static final String ACCOUNT_ICON_XPATH = ".//*[@id='nav-navigation']/section/div/ul[2]/li/li[1]/a/span/span[1]";
    private static String LOGOUT_XPATH_LOCATOR;
    //Page Object elements
    @FindBy(xpath = ACCOUNT_TABLE_XPATH_LOCATOR)
    private WebElement accountTable;

    private WebDriver driver;

    private Logger logger = Logger.getLogger(HomePage.class);
    private WebDriverWait UserAccountPageWait;
    private static final int USER_ACCOUNT_PAGE_OBJECT_TIME_OUT_PERIOD = 5;
    private static final int USER_ACCOUNT_PAGE_OBJECT_POLL_PERIOD = 100;


    public UserAccountDetailsPage(WebDriver driver,Locale locale) {
        this.driver = driver;
        localizedResourceBundle = ResourceBundle.getBundle("LocatorBundle",locale);
        PageFactory.initElements(driver, this);
        LOGOUT_XPATH_LOCATOR = localizedResourceBundle.getString("logout.xpath");
        UserAccountPageWait = new WebDriverWait(driver,USER_ACCOUNT_PAGE_OBJECT_TIME_OUT_PERIOD,USER_ACCOUNT_PAGE_OBJECT_POLL_PERIOD);
    }

    public Map<String,String> getUserDetails(){
        Map<String,String> userRegisteredDetailsMap = new HashMap<String, String>();
        UserAccountPageWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ACCOUNT_TABLE_XPATH_LOCATOR)));
        List<WebElement> detailsTable = accountTable.findElements(By.tagName("tr"));
        for(WebElement details:detailsTable){
            List<WebElement> mappedElements = details.findElements(By.tagName("td"));
            userRegisteredDetailsMap.put(mappedElements.get(0).getText(),mappedElements.get(1).getText());
        }
        return userRegisteredDetailsMap;
    }

    public void logOut(){
        UserAccountPageWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ACCOUNT_ICON_XPATH)));
        driver.findElement(By.xpath(ACCOUNT_ICON_XPATH)).click();
        UserAccountPageWait.until(ExpectedConditions.elementToBeClickable(By.xpath(LOGOUT_XPATH_LOCATOR)));
        driver.findElement(By.xpath(LOGOUT_XPATH_LOCATOR)).click();
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isLoginPresent());
    }
}
