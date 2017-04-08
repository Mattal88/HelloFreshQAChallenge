# The QA Automation Challenge for Hello Fresh  Task 1 - Java, Selenium Webdriver and Maven

## Consists Tests for :
<br>i)Register new user
<br>ii)Login with existing user
<br>iii)Add classic box/plan to cart and validate that box and price are ok in checkout page
<br>
for https://www.hellofresh.co.uk and https://www.hellofresh.com

## Instructions
<br> i) maven is used to buold and run the tests , The options to be provided to maven are siteurl , the user name and
password , it is assumed that the user will provide a valid username that is emailid and a password

### ex : mvn package -Dsiteurl=https://www.hellofresh.com -Dusername=testuser@testmocker.com -Dpassword=Hellofresh
<br> to the -Dsiteurl option takes the country website url , https://www.hellofresh.com or https://www.hellofresh.co.uk
<br> maven option -Dusername is used to give a valid email id , Please make sure to give it different on different runs.
<br> maven option -Dpassword is used to give a valid password

<br> The username and password given in options will be used in the tests


## Test classes for this test
<br> The Test class TestTask1LoginRegisterAndCheckout is the main test class consisting of test driven through testng.
<br> webdriver.pages includes Page Object Model classes for the use in tests