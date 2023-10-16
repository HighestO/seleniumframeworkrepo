package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

	WebDriver driver;

	public RegistrationPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "input-firstname")
	WebElement FirstName;

	@FindBy(id = "input-lastname")
	WebElement LastName;

	@FindBy(id = "input-email")
	WebElement Email;

	@FindBy(id = "input-telephone")
	WebElement Telephone;

	@FindBy(id = "input-password")
	WebElement Password;

	@FindBy(id = "input-confirm")
	WebElement ConfirmPassword;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement PrivacyPolicyOption;
	
	@FindBy(xpath = "//input[@type='submit']")
	WebElement RegisterContinueButton;

	public WebElement FirstName() {

		return FirstName;
	}

	public WebElement LastName() {

		return LastName;
	}

	public WebElement Email() {

		return Email;
	}

	public WebElement Telephone() {
		
		return Telephone;
	}
	public WebElement Password() {

		return Password;
	}

	public WebElement ConfirmPassword() {

		return ConfirmPassword;
	}

	public WebElement PrivacyPolicyOption() {

		return PrivacyPolicyOption;
	}

	public WebElement RegisterContinueButton() {

		return RegisterContinueButton;
	}
}
