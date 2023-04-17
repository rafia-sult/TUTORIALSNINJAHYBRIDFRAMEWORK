package com.tutorials.ninja.qa.testpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	public WebDriver driver;
	
	//Objects 
	@FindBy(linkText = "My Account")
	private WebElement signinLink;
	
	@FindBy(linkText = "Login")
	private WebElement loginLink;
	
	
	//create a constructor 
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	
	//action 

}
