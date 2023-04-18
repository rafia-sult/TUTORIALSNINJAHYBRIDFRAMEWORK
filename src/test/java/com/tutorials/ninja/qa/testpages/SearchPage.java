package com.tutorials.ninja.qa.testpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	public WebDriver driver;
	
	@FindBy(name = "search")
	private WebElement searchBox;
	
	@FindBy(name = "search")
	private WebElement clickSearchBox;
	
	@FindBy(css =".fa.fa-search")
	private WebElement submitButton;
	
	@FindBy(linkText ="iMac")
	private WebElement existingProduct;
	
	@FindBy(xpath ="//p[contains(text(),'There is no product that matches the search criteria.')]")
	private WebElement nonExistingProduct;
	
	@FindBy(linkText ="Apple Cinema 30\"")
	private WebElement appleCinemaProductName;
	
	@FindBy(linkText ="MacBook Air")
	private WebElement multipleProduct;

	
	
	
	
	
	
	
//  -------------------------------------------------------------------------
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
//  --------------------------------------------------------------------------	
	

	
	public void typeInSearchBox(String search) {
		searchBox.sendKeys(search);
	}
	

	public void clickOnSearchBox() {
		clickSearchBox.click();
	}
	
	public void clickOnSubmitButton() {
		submitButton.click();
	}
	
	public String existingProductDisplayedOrNot() {
		String displayStatus = existingProduct.getText();
		return displayStatus;
	}
	
	public String nonExistingProductDisplayedOrNot() {
		String displayStatus = nonExistingProduct.getText();
		return displayStatus;
	}
	
	public String appleCinemaProductNameDisplayedOrNot() {
		String displayStatus = appleCinemaProductName.getText();
		return displayStatus;
	}
	
	public String multipleProductDisplayedOrNot() {
		String displayStatus = multipleProduct.getText();
		return displayStatus;
	}


	
	
	
	
	
	
	
	
}
