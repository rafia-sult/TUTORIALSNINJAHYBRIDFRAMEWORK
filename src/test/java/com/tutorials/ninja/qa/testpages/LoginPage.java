package com.tutorials.ninja.qa.testpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public WebDriver driver;

	@FindBy(id = "input-email")
	public WebElement emailIdTextBox;

	@FindBy(id = "input-password")
	private WebElement passwordTextBox;

	@FindBy(css = "input.btn.btn-primary")
	private WebElement loginButton;

	@FindBy(css = ".alert.alert-danger.alert-dismissible")
	private WebElement warningMessage;

	@FindBy(xpath = "//input[@id='input-password']/following-sibling::a")
	private WebElement forgottenPasswordLink;

	@FindBy(xpath = "//p[contains(text(),'Enter the e-mail address associated with your account. Click submit to have a password reset link e-mailed to you.')]")
	private WebElement validateForgetPasswordPage;

	@FindBy(css = ".alert.alert-danger.alert-dismissible")
	private WebElement unsuccessfulLoginAttempMessage;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public WebElement enterEmailId(String emailIdText) {
		emailIdTextBox.sendKeys(emailIdText);
		return emailIdTextBox;
	}

	public WebElement enterPassword(String passwordText) {
		passwordTextBox.sendKeys(passwordText);
		return passwordTextBox;
	}

	public void clickOnLoginButton() {
		loginButton.click();
	}

	public String retrieveWarningMessageInfoDisplayedOrNot() {
		String displayStatus = warningMessage.getText();
		return displayStatus;
	}

	public void clickOnForgottenPasswordLink() {
		forgottenPasswordLink.click();
	}

	public String validateForgottenPasswordPage() {
		String displayStatus = validateForgetPasswordPage.getText();
		return displayStatus;
	}

	public String unsuccessfulLoginAttempMessageDisplayedOrNot() {
		String displayStatus = unsuccessfulLoginAttempMessage.getText();
		return displayStatus;
	}
}
