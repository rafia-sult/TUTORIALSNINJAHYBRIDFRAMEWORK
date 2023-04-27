package com.tutorials.ninja.qa.testpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {

	public WebDriver driver;

	@FindBy(xpath = "//p[contains(text(),'You have been logged off your account. It is now safe to leave the computer.')]")
	private WebElement logoutValidationMessage;

	public LogoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public String logoutValidationMessageDisplayedOrNot() {
		String displayStatus = logoutValidationMessage.getText();
		return displayStatus;
	}

}
