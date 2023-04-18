package com.tutorials.ninja.qa.testpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	public WebDriver driver;

	// Objects
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountLink;

	@FindBy(linkText = "Login")
	private WebElement loginLink;

	@FindBy(linkText = "Register")
	private WebElement registerLink;

	@FindBy(name = "search")
	private WebElement searchButton;
	


	// create a constructor
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// action
	public void clickOnMyAccountLink() {
		myAccountLink.click();
	}

	public WebElement clickOnLoginLink() {
		loginLink.click();
		return loginLink;
	}

	
	public WebElement clickOnRegisterLink() {
		registerLink.click();
		return registerLink;
	}

	public void clickOnSearchButton() {
		searchButton.click();
	}
	


}
