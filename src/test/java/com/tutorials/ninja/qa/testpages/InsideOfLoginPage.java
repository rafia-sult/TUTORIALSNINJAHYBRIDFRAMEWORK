package com.tutorials.ninja.qa.testpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InsideOfLoginPage {

	public WebDriver driver;

	@FindBy(linkText = "Edit your account information")
	private WebElement validateLoginText;

	@FindBy(linkText = "Phones & PDAs")
	private WebElement phonesAndPDAsButton;

	@FindBy(linkText = "Tablets")
	private WebElement tabletsButton;

	@FindBy(linkText = "Software")
	private WebElement softwareButton;

	@FindBy(linkText = "Logout")
	private WebElement logoutButton;

	@FindBy(linkText = "Change your password")
	private WebElement changeYourPassword;

	@FindBy(id = "input-password")
	private WebElement changePassword;

	@FindBy(id = "input-confirm")
	private WebElement confirmChangePassword;

	@FindBy(css = ".btn.btn-primary")
	private WebElement continueButton;

	@FindBy(css = ".alert.alert-success.alert-dismissible")
	private WebElement updatingPasswordAlert;

	public InsideOfLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String editYourAccountInfoDisplayedOrNot() {
		String displayStatus = validateLoginText.getText();
		return displayStatus;
	}

	public String updatingPasswordAlertDisplayedOrNot() {
		String displayStatus = updatingPasswordAlert.getText();
		return displayStatus;
	}

	public WebElement clickOnPhonesAndPDAsButton() {
		phonesAndPDAsButton.click();
		return phonesAndPDAsButton;
	}

	public WebElement clickOnTabletsButton() {
		tabletsButton.click();
		return tabletsButton;
	}

	public WebElement clickOnSoftwareButton() {
		softwareButton.click();
		return softwareButton;
	}

	public void clickOnLogoutButton() {
		logoutButton.click();
	}

	public void clickOnChangeYourPassword() {
		changeYourPassword.click();
	}

	public WebElement clickOnPassword(String changePasswordBox) {
		changePassword.sendKeys(changePasswordBox);
		return changePassword;
	}

	public WebElement clickOnPasswordConfirm(String confirmPasswordBox) {
		confirmChangePassword.sendKeys(confirmPasswordBox);
		return confirmChangePassword;
	}

	public void clickOnContinueButton() {
		continueButton.click();
	}

}
