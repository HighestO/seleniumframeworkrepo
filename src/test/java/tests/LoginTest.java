package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

import pageobjects.LandingPage;
import pageobjects.LoginPage;
import pageobjects.AccountPage;
import resources.Base;

//Base is where i have all the browser to open the application
public class LoginTest extends Base {

	public WebDriver driver;
	Logger log;
	
	//static ExtentReports extentReport;

	@BeforeMethod
	public void openApplication() throws IOException {

		// logs code
		log = LogManager.getLogger(LoginTest.class.getName());

		// calling the browsers
		driver = initializeBrowser();
		log.debug("Browser got Launched");

		driver.get(prop.getProperty("url"));
		log.debug("Navigate to Application URL");
	}

	@Test(dataProvider = "getLoginData") // the name of the data provider
	public void login(String email, String password, String expectedResult) throws IOException {

		// (String email,String password,String expectedstatus)these are called
		// parameters
		// calling the browsers
		// driver = initializeBrowser();
		// driver.get(prop.getProperty("url"));

		LandingPage landingpage = new LandingPage(driver);

		// clicking on the account at the top bar menu
		landingpage.myAccountDropdown().click();
		log.debug("Click on my Account Dropdown");

		// clicking on the login button on the my account drop down
		landingpage.loginOption().click();
		log.debug("Click on Login Option");

		LoginPage loginpage = new LoginPage(driver);
		loginpage.emailAddressField().sendKeys(email);
		log.debug("Email Address got enterred");
		loginpage.passwordField().sendKeys(password);
		log.debug("Password got enttered");

		loginpage.loginButton().click();
		log.debug("Login Button has been Clicked");

		AccountPage accountpage = new AccountPage(driver);

		String actualResult = null;

		try {
			// This verify if we are login or not?
			if (accountpage.editAccountInformation().isDisplayed()) {
				actualResult = "Successful";
				log.debug("User got Logged in");
			}
		} catch (Exception e) {

			actualResult = "Failure";
			log.debug("User did not looged in");
		}
		if (actualResult.equals(expectedResult)) {
			log.info("Login Test got Passed");
		} else {

			log.error("Login Test got Failed");
		}

		// Assert.assertEquals(actualResult, expectedResult);
		// log.info("Login Test got Passed");

	}

	// only use dataprovider if you have multiple sets of data | if the data is
	// single, then use data properties
	@DataProvider
	public Object[][] getLoginData() {

		// data driven //email password status
		Object[][] data = { { "kofirich35@gmail.com", "12345", "Successful" },
				{ "dummy@testdata.com", "12345", "Failure" } };

		return data;
	}

	@AfterMethod
	public void closeBrowser() {

		driver.close();
		log.debug("Browser is close");
		
	}

}
