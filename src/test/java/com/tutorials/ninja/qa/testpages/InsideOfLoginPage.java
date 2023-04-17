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

	@FindBy(linkText = "Logout")
	private WebElement logoutButton;

	public InsideOfLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String editYourAccountInfoDisplayedOrNot() {
		String displayStatus = validateLoginText.getText();
		return displayStatus;
	}

	public void clickOnPhonesAndPDAsButton() {
		phonesAndPDAsButton.click();
	}

	public void clickOnTabletsButton() {
		tabletsButton.click();
	}
	
	public void clickOnLogoutButton() {
		logoutButton.click();
	}

}
