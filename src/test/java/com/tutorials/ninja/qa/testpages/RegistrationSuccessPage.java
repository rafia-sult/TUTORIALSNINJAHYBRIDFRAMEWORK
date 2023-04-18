package com.tutorials.ninja.qa.testpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationSuccessPage {

	public WebDriver driver;
	
	
	@FindBy(css = ".btn.btn-primary")
	private WebElement continueButtonInSuccessPage;
	
	public RegistrationSuccessPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	public void clickOnContinueButton() {
		continueButtonInSuccessPage.click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
