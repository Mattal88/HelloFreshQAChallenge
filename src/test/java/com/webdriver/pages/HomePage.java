package com.webdriver.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *  Page Object class representing the page attributes and services offered by the Home page
 *  <br> This is the page where the user lands when he clicks on the base url or home url of "HelloFresh" website
 */
public class HomePage {
    private WebDriver driver;

    //Page Object elements
    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(id = "banner-view-plans-button")
    private WebElement viewOurPlans;

    @FindBy(id = "classic-box-product-detail-button")
    private WebElement classicBoxDetailButton;

    @FindBy(className = "p-y-1 h2")
    private WebElement planHeader;

    @FindBy(id = "add-to-cart-button")
    private WebElement addToCartButton;

    @FindBy(className = "mls summary-item-name")
    private WebElement selectedBox;

    @FindBy(className = "sushi-detail-price")
    private WebElement totalPrice;

}
