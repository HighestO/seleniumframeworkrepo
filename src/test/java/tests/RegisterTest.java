package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.RegistrationPage;
import resources.Base;

public class RegisterTest extends Base {

	public WebDriver driver;

	@BeforeMethod
	public void openBrowser() throws IOException {

		driver = initializeBrowser();
		driver.get(prop.getProperty("url"));
	}

	@Test(dataProvider = "getRegistrationData")
	public void RegisterAccount(String firstname, String lastname, String email, String telephone, String password,
			String expectedResult) throws InterruptedException {

		LandingPage landingpage = new LandingPage(driver);

		landingpage.myAccountDropdown().click();

		landingpage.RegisterOption().click();

		RegistrationPage registrationpage = new RegistrationPage(driver);
		registrationpage.FirstName().sendKeys(firstname);
		Thread.sleep(1000);
		registrationpage.LastName().sendKeys(lastname);
		Thread.sleep(1000);
		registrationpage.Email().sendKeys(email);
		Thread.sleep(1000);
		registrationpage.Telephone().sendKeys(telephone);
		Thread.sleep(1000);
		registrationpage.Password().sendKeys(password);
		Thread.sleep(1000);
		registrationpage.ConfirmPassword().sendKeys(password);
		Thread.sleep(1000);

		registrationpage.PrivacyPolicyOption().click();

		registrationpage.RegisterContinueButton().click();
		Thread.sleep(3000);

		AccountPage accountpage = new AccountPage(driver);

		String actualResult = null;

		try {
			if (accountpage.AccountCreatedSuccessfully().isDisplayed()) {
				actualResult = "Successful";
			}

		} catch (Exception e) {

			actualResult = "Failure";
		}

		Assert.assertEquals(actualResult, expectedResult);
	}

	@DataProvider
	public Object[][] getRegistrationData() {
		Object[][] data = { { "Christabel", "Sekyi", "chris22@gmail.com", "0889876", "12345", "Successful" },
				{ "dummy", "data1", "dummy200@gmail", "786545", "12345", "Failure" } };

		return data;

	}

	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}

}
