package com.webdriver.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Page Object class for the registration widget
 */
public class RegisterWidgetPage {

    private WebDriver driver;

    //Page Object elements
    @FindBy(className = "modal-title")
    private WebElement registerWidgetHeader;

    @FindBy(id = "gender-input")
    private WebElement genderInput;

    @FindBy(id = "first-name-input")
    private WebElement firstNameInput;

    @FindBy(id = "last-name-input")
    private WebElement lastNameInput;

    @FindBy(id = "email-input")
    private WebElement emailInput;

    @FindBy(id = "password-input")
    private WebElement passwordInput;

    @FindBy(id = "day-input")
    private WebElement dayInput;

    @FindBy(id = "month-input")
    private WebElement monthInput;

    @FindBy(id = "year-input")
    private WebElement yearInput;

    @FindBy(id = "register-button")
    private WebElement registerButton;


    private WebDriverWait RegisterWidgetPageWait;
    private static final int REGISTER_WIDGET_PAGE_OBJECT_TIME_OUT_PERIOD = 5;
    private static final int REGISTER_WIDGET_PAGE_OBJECT_POLL_PERIOD = 100;

    public RegisterWidgetPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        RegisterWidgetPageWait = new WebDriverWait(driver,REGISTER_WIDGET_PAGE_OBJECT_TIME_OUT_PERIOD,REGISTER_WIDGET_PAGE_OBJECT_POLL_PERIOD);
    }
    public void registerUser(RegistrationDetails userDetails){
        //Select the gender from drop down
        selectGender(userDetails.getGender());
        //Enter the First name and last name
        firstNameInput.sendKeys(userDetails.getFirstName());
        lastNameInput.sendKeys(userDetails.getLastName());
        //Enter the email
        emailInput.sendKeys(userDetails.getEmail());
        passwordInput.sendKeys(userDetails.getPassword());
        //Enter Birthday
        dayInput.sendKeys(userDetails.getDay());
        monthInput.sendKeys(userDetails.getMonth());
        yearInput.sendKeys(userDetails.getYear());
        //clickOnRegister();
    }

    public void selectGender(String gender){
        Select dropdown= new Select(genderInput);
        if(gender.equalsIgnoreCase("Male")){
            dropdown.selectByVisibleText("Mr.");
        }else if(gender.equalsIgnoreCase("Female")){
            dropdown.selectByVisibleText("Mrs./Miss/Ms.");
        }else{
            // Selecting the two available genders in dropdown , suggested that you can add "dont declare field" too
            throw new RuntimeException("Invalid Gender given");
        }
    }

    public void clickOnRegister(){
        RegisterWidgetPageWait.withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.elementToBeClickable(registerButton));
        registerButton.click();
    }


    public static class RegistrationDetails{
        private String gender;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String day;
        private String month;
        private String year;

        public RegistrationDetails(String gender, String firstName, String lastName, String email, String password, String day, String month, String year) {
            this.gender = gender;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.day = day;
            this.month = month;
            this.year = year;
        }


        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }
    }
}
