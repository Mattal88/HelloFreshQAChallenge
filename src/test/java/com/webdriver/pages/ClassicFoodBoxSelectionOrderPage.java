package com.webdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by matshaik on 4/7/2017.
 */
public class ClassicFoodBoxSelectionOrderPage implements FoodBoxSelectionOrder {

    @FindBy(id = ADD_TO_CART_BUTTON_LOCATOR)
    private WebElement addToCartButton;

    @FindBy(className = PRICE_TAG_LOCATOR)
    private WebElement priceTagText;

    private WebDriverWait ClassicFoodBoxSelectionOrderPageWait;
    private static final int CLASSIC_FOOD_BOXES_PAGE_OBJECT_TIME_OUT_PERIOD = 5;
    private static final int CLASSIC_FOOD_BOXES_PAGE_OBJECT_POLL_PERIOD = 100;

    private WebDriver driver;

    public ClassicFoodBoxSelectionOrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        ClassicFoodBoxSelectionOrderPageWait = new WebDriverWait(driver,CLASSIC_FOOD_BOXES_PAGE_OBJECT_TIME_OUT_PERIOD,CLASSIC_FOOD_BOXES_PAGE_OBJECT_POLL_PERIOD);
    }

    public String getPriceTagOfPlan(){
        ClassicFoodBoxSelectionOrderPageWait.until(ExpectedConditions.visibilityOfElementLocated(By.className(PRICE_TAG_LOCATOR)));
        System.out.println("Price"+priceTagText.getText());
        return priceTagText.getText();
    }

    public CheckoutPage clickCheckout(){
        ClassicFoodBoxSelectionOrderPageWait.until(ExpectedConditions.elementToBeClickable(By.id(ADD_TO_CART_BUTTON_LOCATOR)));
        addToCartButton.click();
        return new CheckoutPage(driver);
    }

}
