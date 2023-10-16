package stepdefinition;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class Login extends Base {

	WebDriver driver;
	LandingPage landingpage;
	LoginPage loginpage;
	AccountPage accountpage;

	@Given("^Open my Browser$")
	public void open_my_browser() throws IOException {

		driver = initializeBrowser();

	}

	@And("^Navigate to Login page$")
	public void navigate_to_login_page() throws InterruptedException {

		driver.get(prop.getProperty("url"));
		landingpage = new LandingPage(driver);
		landingpage.myAccountDropdown().click();
		landingpage.loginOption().click();

		Thread.sleep(3000);

	}

	@When("^User enters username as \"([^\"]*)\" and password as \"([^\"]*)\" into the fields$")
	public void user_enters_username_as_something_and_password_as_something_into_the_fields(String email,
			String password) {

		loginpage = new LoginPage(driver);
		loginpage.emailAddressField().sendKeys(email);
		loginpage.passwordField().sendKeys(password);

	}

	@And("^User clicks on Login button$")
	public void user_clicks_on_login_button() {

		loginpage.loginButton().click();

	}

	@Then("^Verify user is able to successfully login$")
	public void verify_user_be_able_to_successfully_login() {

		accountpage = new AccountPage(driver);
		Assert.assertTrue(accountpage.editAccountInformation().isDisplayed());

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
