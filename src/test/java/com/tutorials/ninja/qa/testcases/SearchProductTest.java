package com.tutorials.ninja.qa.testcases;

import java.util.ArrayList;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorials.ninja.qa.testbase.TestBase;
import com.tutorials.ninja.qa.testpages.InsideOfLoginPage;
import com.tutorials.ninja.qa.testpages.LandingPage;
import com.tutorials.ninja.qa.testpages.LoginPage;
import com.tutorials.ninja.qa.testpages.LogoutPage;
import com.tutorials.ninja.qa.testpages.RegisterPage;
import com.tutorials.ninja.qa.testpages.RegistrationSuccessPage;
import com.tutorials.ninja.qa.testpages.SearchPage;

public class SearchProductTest extends TestBase {

	public SearchProductTest() throws Exception {
		super();
	}

	public static ChromeOptions options;
	public static WebDriver driver;
	public static SoftAssert softassert = new SoftAssert();
	public static LandingPage landingpage;
	public static SearchPage searchpage;
	public static LoginPage loginpage;
	public static LogoutPage logoutpage;
	public static InsideOfLoginPage insideofloginpage;
	public static Actions action;





	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(configProp.getProperty("browserName"));
		landingpage = new LandingPage(driver);
		landingpage.clickOnSearchButton();
	}
	
	@Test(priority = 1)
	public void verifySearchIsClickable() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnSearchBox();

	}


	
	@Test(priority = 2)
	public void verifySearchWithExistingProductName() {
		searchpage = new SearchPage(driver);
		searchpage.typeInSearchBox(testDataProp.getProperty("existingProduct"));
		searchpage.clickOnSubmitButton();		
		String actualExistingProdName = searchpage.existingProductDisplayedOrNot();
		String expectedExistingProdName = testDataProp.getProperty("existingProduct");
		softassert.assertTrue(actualExistingProdName.contains(expectedExistingProdName), "Search criteria does not match");
		softassert.assertAll();
	}
	
	@Test(priority = 3)
	public void verifySearchWithANonExistingProductName() {
		searchpage = new SearchPage(driver);
		searchpage.typeInSearchBox(testDataProp.getProperty("nonExistingProduct"));
		searchpage.clickOnSubmitButton();		
		String actualNonExistingProdName = searchpage.nonExistingProductDisplayedOrNot();
		String expectedNonExistingProdName = testDataProp.getProperty("noMatchForSearchedProductMessage");
		softassert.assertTrue(actualNonExistingProdName.contains(expectedNonExistingProdName), "Search criteria does not match");
		softassert.assertAll();
	}
	
	@Test(priority = 4)
	public void verifySearchWithoutProvidingAnything() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnSubmitButton();		
		String actualNonExistingProdName = searchpage.nonExistingProductDisplayedOrNot();
		String expectedNonExistingProdName = testDataProp.getProperty("noMatchForSearchedProductMessage");
		softassert.assertTrue(actualNonExistingProdName.contains(expectedNonExistingProdName), "Search criteria does not match");
		softassert.assertAll();
	}
	
	@Test(priority = 5)
	public void verifySearchProductAfterLogin() {
		landingpage = new LandingPage(driver);
		loginpage = new LoginPage(driver);
		searchpage = new SearchPage(driver);
		insideofloginpage = new InsideOfLoginPage(driver);
		landingpage.clickOnMyAccountLink();
		landingpage.clickOnLoginLink();
		loginpage.enterEmailId(configProp.getProperty("validUserName"));
		loginpage.enterPassword(configProp.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		String actualMessageForLogin = insideofloginpage.editYourAccountInfoDisplayedOrNot();
		String expectedMessageForLogin = testDataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin),"Edit your account information is not displayed");
		searchpage.clickOnSearchBox();
		searchpage.typeInSearchBox(testDataProp.getProperty("existingProduct"));
		searchpage.clickOnSubmitButton();		
		String actualExistingProdName = searchpage.existingProductDisplayedOrNot();
		String expectedExistingProdName = testDataProp.getProperty("existingProduct");
		softassert.assertTrue(actualExistingProdName.contains(expectedExistingProdName), "Search criteria does not match");
		softassert.assertAll();
		
	}
	
	@Test(priority = 6)
	public void verifyProperLogoutAfterSearchingForProduct() {
		landingpage = new LandingPage(driver);
		loginpage = new LoginPage(driver);
		searchpage = new SearchPage(driver);
		insideofloginpage = new InsideOfLoginPage(driver);
		logoutpage = new LogoutPage(driver);
		landingpage.clickOnMyAccountLink();
		landingpage.clickOnLoginLink();
		loginpage.enterEmailId(configProp.getProperty("validUserName"));
		loginpage.enterPassword(configProp.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		String actualMessageForLogin = insideofloginpage.editYourAccountInfoDisplayedOrNot();
		String expectedMessageForLogin = testDataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin),"Edit your account information is not displayed");
		searchpage.clickOnSearchBox();
		searchpage.typeInSearchBox(testDataProp.getProperty("existingProduct"));
		searchpage.clickOnSubmitButton();		
		String actualExistingProdName = searchpage.existingProductDisplayedOrNot();
		String expectedExistingProdName = testDataProp.getProperty("existingProduct");
		softassert.assertTrue(actualExistingProdName.contains(expectedExistingProdName), "Search criteria does not match");
		landingpage.clickOnMyAccountLink();
		insideofloginpage.clickOnLogoutButton();		
		String actualMessageForLogout = logoutpage.retrieveLogoutValidationMessage();
		String expectedMessageForLogout = testDataProp.getProperty("validateLogout");
		softassert.assertTrue(actualMessageForLogout.contains(expectedMessageForLogout),"Logout confirmation text does not match");
		softassert.assertAll();
	}
	
	@Test(priority = 7)
	public void verifyCorrectResultsAreDisplayedAfterASearch() {
		searchpage = new SearchPage(driver);
		searchpage.typeInSearchBox(testDataProp.getProperty("existingProduct"));
		searchpage.clickOnSubmitButton();		
		String actualExistingProdName = searchpage.existingProductDisplayedOrNot();
		String expectedExistingProdName = testDataProp.getProperty("existingProduct");
		softassert.assertTrue(actualExistingProdName.contains(expectedExistingProdName), "Search criteria does not match");
		softassert.assertAll();
	}

	@Test(priority = 8)
	public void verifySearchWithSpecialCharacter() {
		searchpage = new SearchPage(driver);
		searchpage.typeInSearchBox(testDataProp.getProperty("searchWithSpecialCharacter"));
		searchpage.clickOnSubmitButton();		
		String actualNonExistingProdName = searchpage.nonExistingProductDisplayedOrNot();
		String expectedNonExistingProdName = testDataProp.getProperty("noMatchForSearchedProductMessage");
		softassert.assertTrue(actualNonExistingProdName.contains(expectedNonExistingProdName), "Search criteria does not match");
		softassert.assertAll();
	}
	
	
	
	@Test(priority = 9)
	public void verifySearchWhenTheSearchTermIsMisspelled() {
		searchpage = new SearchPage(driver);
		searchpage.typeInSearchBox(testDataProp.getProperty("misspelledSearch"));
		searchpage.clickOnSubmitButton();		
		String actualmisspelledProdName = searchpage.appleCinemaProductNameDisplayedOrNot();
		String expectedmisspelledProdName = testDataProp.getProperty("misspelledResult");
		softassert.assertTrue(actualmisspelledProdName.contains(expectedmisspelledProdName), "Search criteria does not match");
		softassert.assertAll();
		
	}
	
	@Test(priority = 10 )
	public void verifySearchByProvidingASearchCriteriaThatResultsInMultipleProducts() {
		searchpage = new SearchPage(driver);
		searchpage.typeInSearchBox(testDataProp.getProperty("multipleProductSearch"));
		searchpage.clickOnSubmitButton();		
		String actualExistingProdName = searchpage.existingProductDisplayedOrNot();
		String expectedExistingProdName = testDataProp.getProperty("existingProduct");
		softassert.assertTrue(actualExistingProdName.contains(expectedExistingProdName), "Search criteria does not match");
		String actualMultipleProdName = searchpage.multipleProductDisplayedOrNot();
		String expectedMultipleProdName = testDataProp.getProperty("multipleProductVarification");
		softassert.assertTrue(actualMultipleProdName.contains(expectedMultipleProdName), "Search criteria does not match");
		softassert.assertAll();
	}
	
	@Test(priority = 11)
	public void verifySearchWithExtraSpacesBetweenLetters() {
		searchpage = new SearchPage(driver);
		searchpage.typeInSearchBox(testDataProp.getProperty("searchWithExtraSpaces"));
		searchpage.clickOnSubmitButton();		
		String actualExistingProdName = searchpage.existingProductDisplayedOrNot();
		String expectedExistingProdName = testDataProp.getProperty("existingProduct");
		softassert.assertTrue(actualExistingProdName.contains(expectedExistingProdName), "Search criteria does not match");
		String actualMultipleProdName = searchpage.multipleProductDisplayedOrNot();
		String expectedMultipleProdName = testDataProp.getProperty("multipleProductVarification");
		softassert.assertTrue(actualMultipleProdName.contains(expectedMultipleProdName), "Search criteria does not match");
		String actualmisspelledProdName = searchpage.appleCinemaProductNameDisplayedOrNot();
		String expectedmisspelledProdName = testDataProp.getProperty("misspelledResult");
		softassert.assertTrue(actualmisspelledProdName.contains(expectedmisspelledProdName), "Search criteria does not match");
		softassert.assertAll();
	}
	
	@Test(priority = 12)
	public void verifySearchWithAllUppercase() {
		searchpage = new SearchPage(driver);
		searchpage.typeInSearchBox(testDataProp.getProperty("searchWithAllUpperCase"));
		searchpage.clickOnSubmitButton();		
		String actualmisspelledProdName = searchpage.appleCinemaProductNameDisplayedOrNot();
		String expectedmisspelledProdName = testDataProp.getProperty("upperCaseSearchResult");
		softassert.assertTrue(actualmisspelledProdName.contains(expectedmisspelledProdName), "Search criteria does not match");
		softassert.assertAll();
	}
	
	@Test(priority =13 )
	public void verifySearchWithAllLowercase() {
		searchpage = new SearchPage(driver);
		searchpage.typeInSearchBox(testDataProp.getProperty("searchWithAllLowerCase"));
		searchpage.clickOnSubmitButton();		
		String actualmisspelledProdName = searchpage.appleCinemaProductNameDisplayedOrNot();
		String expectedmisspelledProdName = testDataProp.getProperty("lowerCaseSearchResult");
		softassert.assertTrue(actualmisspelledProdName.contains(expectedmisspelledProdName), "Search criteria does not match");
		softassert.assertAll();
	}
	
	@Test(priority = 14)
	public void verifySearchWithMixedUppercaseAndlowercase() {
		searchpage = new SearchPage(driver);
		searchpage.typeInSearchBox(testDataProp.getProperty("searchWithMixedUpperCaseAndLowerCase"));
		searchpage.clickOnSubmitButton();		
		String actualmisspelledProdName = searchpage.appleCinemaProductNameDisplayedOrNot();
		String expectedmisspelledProdName = testDataProp.getProperty("mixedCaseSearchResult");
		softassert.assertTrue(actualmisspelledProdName.contains(expectedmisspelledProdName), "Search criteria does not match");
		softassert.assertAll();
	}
	
	@Test(priority = 15)
	public void verifySearchingForProductAfterLogout() {
		
		landingpage = new LandingPage(driver);
		loginpage = new LoginPage(driver);
		searchpage = new SearchPage(driver);
		insideofloginpage = new InsideOfLoginPage(driver);
		logoutpage = new LogoutPage(driver);
		landingpage.clickOnMyAccountLink();
		landingpage.clickOnLoginLink();
		loginpage.enterEmailId(configProp.getProperty("validUserName"));
		loginpage.enterPassword(configProp.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		String actualMessageForLogin = insideofloginpage.editYourAccountInfoDisplayedOrNot();
		String expectedMessageForLogin = testDataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin),"Edit your account information is not displayed");
		insideofloginpage.clickOnLogoutButton();		
		String actualMessageForLogout = logoutpage.retrieveLogoutValidationMessage();
		String expectedMessageForLogout = testDataProp.getProperty("validateLogout");
		softassert.assertTrue(actualMessageForLogout.contains(expectedMessageForLogout),"Logout confirmation text does not match");
		searchpage.clickOnSearchBox();
		searchpage.typeInSearchBox(testDataProp.getProperty("existingProduct"));
		searchpage.clickOnSubmitButton();		
		String actualExistingProdName = searchpage.existingProductDisplayedOrNot();
		String expectedExistingProdName = testDataProp.getProperty("existingProduct");
		softassert.assertTrue(actualExistingProdName.contains(expectedExistingProdName), "Search criteria does not match");
		softassert.assertAll();

	}
	
	@Test(priority = 25)
	public void verifyRegisteringUsingTheTABKeyOfKeyBoard() {

		searchpage = new SearchPage(driver);
//		searchpage.typeInSearchBox(testDataProp.getProperty("existingProduct").sendKeys(Keys.BACK_SPACE));

	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	
	
}
