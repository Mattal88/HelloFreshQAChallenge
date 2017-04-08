package com.testdrivers;

import com.webdriver.pages.HomePage;
import com.webdriver.pages.UserAccountDetailsPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

/**
 * Base and Utility class for the main test class , provides test setup and tear down
 */
public class TestTask1Base {

    private static final Logger logger = Logger.getLogger(TestTask1Base.class);
    public final static String siteUrl = System.getProperty("siteurl");
    public final static String userName = System.getProperty("username")+"@mockinguser.com";
    public final static String password = System.getProperty("password");
    public final static String accountDetailsUrl = "/account/edit/";
    public final static Locale locale = getLocale(siteUrl);

    //We could also use a DriverFactory
    static WebDriver driver;

    //Base page object for the tests
    public static HomePage homePage;


    /**
     * Before each test class run initializes and instantiates a webdriver instance
     */
    @BeforeClass
    public static void setupClass() {
        driver = new FirefoxDriver();
        Assert.assertNotNull(driver);
        driver.manage().window().setSize(new Dimension(1200, 850));
        driver.navigate().to(siteUrl);

    }

    @BeforeMethod
    public static void setupTest() {
        // Add custom setup if you still need some sanity check of environment before a test executes
        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void tearDownTest() {
    }

    @AfterClass
    public static void tearDownClass() {
        driver.quit();
    }
    /**
     * Method to return locale based on the url , Best practice is to use a guava library to get the locales
     * <br> based on url host. But have implemented a simple logic for the urls mentioned the task question to avoid another maven dependency
     * @param url
     * @return
     */
    public static Locale getLocale(String url) {
        Locale locale = null;
        try {
            URL siteUrl = new URL(url);
            String host = siteUrl.getHost();
            String topLevelDomainName = host.substring(host.lastIndexOf(".co"));
            if (topLevelDomainName.equalsIgnoreCase(".com")) {
                locale = Locale.getDefault();
            } else {
                locale = new Locale("en", "GB");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(locale);
        System.out.println("locale is "+locale.getCountry());
        return locale;
    }

    public UserAccountDetailsPage goToAccountDetailsPage(){
        homePage.waitHomePageReady();
        driver.navigate().to(siteUrl+accountDetailsUrl);
        return new UserAccountDetailsPage(driver,locale);
    }

    public HomePage goToHomePage(){
        homePage.goToHomePage();
        return new HomePage(driver);
    }
}
