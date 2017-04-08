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
public class CheckoutPage {

    public static final String SELECTED_BOX_LOCATOR=".//*[@id='product-detail']/div/div/div[2]/hf-product-detail-card/div/div";
    public static final String TOTAL_PRICE_LOCATOR="sushi-detail-price";

    @FindBy(xpath = SELECTED_BOX_LOCATOR)
    private WebElement selectedBox;

    @FindBy(className = TOTAL_PRICE_LOCATOR)
    private WebElement totalPrice;

    private WebDriverWait CheckoutPageWait;
    private static final int CHECKOUT_PAGE_OBJECT_TIME_OUT_PERIOD = 5;
    private static final int CHECKOUT_PAGE_OBJECT_POLL_PERIOD = 100;

    private WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        CheckoutPageWait = new WebDriverWait(driver,CHECKOUT_PAGE_OBJECT_TIME_OUT_PERIOD,CHECKOUT_PAGE_OBJECT_POLL_PERIOD);
    }

    public String getPlanNameFromCheckout(){
        CheckoutPageWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SELECTED_BOX_LOCATOR)));
        return selectedBox.getText();
    }

    public String getTotalPriceCheckout(){
        CheckoutPageWait.until(ExpectedConditions.visibilityOfElementLocated(By.className(TOTAL_PRICE_LOCATOR)));
        return totalPrice.getText();
    }

}
