package com.tutorials.ninja.qa.testpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	public WebDriver driver;

	@FindBy(id = "input-firstname")
	private WebElement firstNameTextBox;

	@FindBy(id = "input-lastname")
	private WebElement lastNameTextBox;

	@FindBy(id = "input-email")
	private WebElement emailTextBox;

	@FindBy(id = "input-telephone")
	private WebElement telephoneTextBox;

	@FindBy(id = "input-password")
	private WebElement passwordTextBox;

	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordTextBox;

	@FindBy(name = "newsletter")
	private WebElement subscribeButton;

	@FindBy(name = "agree")
	private WebElement privacyPolicyButton;

	@FindBy(css = ".btn.btn-primary")
	private WebElement continueButton;

	@FindBy(xpath = "//h1[contains(text(),'Your Account Has Been Created!')]")
	private WebElement successfullyRegisteredMessage;

	@FindBy(xpath = "//div[contains(text(),'First Name must be between 1 and 32 characters!')]")
	private WebElement warningMessageForMissingFirstName;

	@FindBy(xpath = "//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")
	private WebElement warningMessageForMissingLastName;

	@FindBy(xpath = "//div[contains(text(),'E-Mail Address does not appear to be valid!')]")
	private WebElement warningMessageForMissingEmail;

	@FindBy(xpath = "//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")
	private WebElement warningMessageForMissingPhoneNumber;

	@FindBy(xpath = "//div[contains(text(),'Password must be between 4 and 20 characters!')]")
	private WebElement warningMessageForMissingPassword;

	@FindBy(xpath = "//div[contains(text(),'Password confirmation does not match password!')]")
	private WebElement warningMessageForMissingConfirmPassword;

	@FindBy(css = ".alert.alert-danger.alert-dismissible")
	private WebElement warningMessageForNotCheckingPrivacyPolicyButton;

	@FindBy(css = ".alert.alert-danger.alert-dismissible")
	private WebElement warningMessageForExistingEmail;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement enterFirstName(String firstName) {
		firstNameTextBox.sendKeys(firstName);
		return firstNameTextBox;
	}

	public WebElement enterLastName(String lastName) {
		lastNameTextBox.sendKeys(lastName);
		return lastNameTextBox;
	}

	public void enterEmail(String email) {
		emailTextBox.sendKeys(email);
	}

	public void enterTelephone(String telephone) {
		telephoneTextBox.sendKeys(telephone);
	}

	public WebElement enterPassword(String password) {
		passwordTextBox.sendKeys(password);
		return passwordTextBox;
	}

	public WebElement enterConfirmPassword(String confirmPassword) {
		confirmPasswordTextBox.sendKeys(confirmPassword);
		return confirmPasswordTextBox;
	}

	public void clickOnSubscribeButton() {
		subscribeButton.click();
	}

	public WebElement clickOnPrivacyPolicyButton() {
		privacyPolicyButton.click();
		return privacyPolicyButton;
	}

	public void clickOnContinueButton() {
		continueButton.click();
	}

	public String successfullyRegisteredMessageIsDisplayedOrNot() {
		String displayStatus = successfullyRegisteredMessage.getText();
		return displayStatus;
	}

	public String warningMessageForFirstNameDisplayedOrNot() {
		String displayStatus = warningMessageForMissingFirstName.getText();
		return displayStatus;
	}

	public String warningMessageForLastNameDisplayedOrNot() {
		String displayStatus = warningMessageForMissingLastName.getText();
		return displayStatus;
	}

	public String warningMessageForEmailDisplayedOrNot() {
		String displayStatus = warningMessageForMissingEmail.getText();
		return displayStatus;
	}

	public String warningMessageForTelephoneDisplayedOrNot() {
		String displayStatus = warningMessageForMissingPhoneNumber.getText();
		return displayStatus;
	}

	public String warningMessageForPasswordDisplayedOrNot() {
		String displayStatus = warningMessageForMissingPassword.getText();
		return displayStatus;
	}

	public String warningMessageForConfirmPasswordDisplayedOrNot() {
		String displayStatus = warningMessageForMissingConfirmPassword.getText();
		return displayStatus;
	}

	public String warningMessageForPrivacyPolicyDisplayedOrNot() {
		String displayStatus = warningMessageForNotCheckingPrivacyPolicyButton.getText();
		return displayStatus;
	}

	public String warningMessageForExistingEmailDisplayedOrNot() {
		String displayStatus = warningMessageForExistingEmail.getText();
		return displayStatus;
	}

}
