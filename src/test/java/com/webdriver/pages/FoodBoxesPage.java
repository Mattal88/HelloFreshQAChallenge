package com.webdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by matshaik on 4/7/2017.
 */
public class FoodBoxesPage {

    public  ResourceBundle localizedResourceBundle;

    private WebElement classicBoxOrPlanDetailButton;
    private WebElement veggieBoxOrPlanDetailButton;
    private WebElement familyBoxOrPlanDetailButton;

    private WebDriver driver;

    private WebDriverWait FoodBoxesPageWait;
    private static final int FOOD_BOXES_PAGE_OBJECT_TIME_OUT_PERIOD = 5;
    private static final int FOOD_BOXES_PAGE_OBJECT_POLL_PERIOD = 100;

    public FoodBoxesPage(WebDriver driver,Locale locale) {
        this.driver = driver;
        localizedResourceBundle = ResourceBundle.getBundle("LocatorBundle",locale);
        PageFactory.initElements(driver, this);
        FoodBoxesPageWait = new WebDriverWait(driver,FOOD_BOXES_PAGE_OBJECT_TIME_OUT_PERIOD,FOOD_BOXES_PAGE_OBJECT_POLL_PERIOD);
        FoodBoxesPageWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(localizedResourceBundle.getString("id.classicboxorplandetailbutton"))));
        classicBoxOrPlanDetailButton = driver.findElement(By.id(localizedResourceBundle.getString("id.classicboxorplandetailbutton")));
        veggieBoxOrPlanDetailButton = driver.findElement(By.id(localizedResourceBundle.getString("id.veggieboxorplandetailbutton")));
        familyBoxOrPlanDetailButton = driver.findElement(By.id(localizedResourceBundle.getString("id.familyboxorplandetailbutton")));
    }

    public ClassicFoodBoxSelectionOrderPage selectClassic(){
        FoodBoxesPageWait.until(ExpectedConditions.elementToBeClickable(classicBoxOrPlanDetailButton));
        classicBoxOrPlanDetailButton.click();
        return new ClassicFoodBoxSelectionOrderPage(driver);
    }

    //Similarly for Veggie and Family boxes
}
