package com.tutorials.ninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorials.ninja.qa.testbase.TestBase;

public class SearchProductTest extends TestBase {

	public SearchProductTest() throws Exception {
		super();
	}

	public static ChromeOptions options;
	public static WebDriver driver;
	public static SoftAssert softassert = new SoftAssert();

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(configProp.getProperty("browserName"));
		driver.findElement(By.name("search")).click();
	}
	
	
	@Test(priority = 1)
	public void verifySearchBoxIsClickable() {
		driver.findElement(By.name("search")).click();
	
	}
	
	@Test(priority = 2)
	public void verifySearchWithExistingProductName() {
		driver.findElement(By.name("search")).sendKeys("iMac");
		driver.findElement(By.cssSelector(".fa.fa-search")).click();
		String actualExistingProdName = (driver.findElement(By.linkText("iMac"))).getText();
		String expectedExistingProdName = "iMac";
		softassert.assertTrue(actualExistingProdName.contains(expectedExistingProdName), "Search criteria does not match");
		softassert.assertAll();
	}
	
	@Test(priority = 3)
	public void verifySearchWithANonExistingProductName() {
		driver.findElement(By.name("search")).sendKeys("fruit");
		driver.findElement(By.cssSelector(".fa.fa-search")).click();
		String actualNonExistingProdName = (driver.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criteria.')]"))).getText();
		String expectedNonExistingProdName = "There is no product that matches the search criteria.";
		softassert.assertTrue(actualNonExistingProdName.contains(expectedNonExistingProdName), "Search criteria does not match");
		softassert.assertAll();
	}
	
	@Test(priority =4 )
	public void verifySearchWithoutProvidingAnything() {
		driver.findElement(By.cssSelector(".fa.fa-search")).click();
		String actualSearchWithoutProd = (driver.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criteria.')]"))).getText();
		String expectedSearchWithoutProd = "There is no product that matches the search criteria.";
		softassert.assertTrue(actualSearchWithoutProd.contains(expectedSearchWithoutProd), "Search criteria does not match");
		softassert.assertAll();
	}
	
	@Test(priority = 5)
	public void verifySearchProductAfterLogin() {
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys(testDataProp.getProperty("validUserName"));
		driver.findElement(By.id("input-password")).sendKeys(testDataProp.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualMessage = (driver.findElement(By.linkText("Edit your account information")).getText());
		String expectedMessage = testDataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessage.contains(expectedMessage),"Edit your account information is not displayed");
		driver.findElement(By.name("search")).sendKeys("iMac");
		driver.findElement(By.cssSelector(".fa.fa-search")).click();
		String actualSearchAfterLogin = (driver.findElement(By.linkText("iMac"))).getText();
		String expectedSearchAfterLogin = "iMac";
		softassert.assertTrue(actualSearchAfterLogin.contains(expectedSearchAfterLogin), "Search criteria does not match");
		softassert.assertAll();
		
	}
	
	@Test(priority = 6)
	public void verifyProperLogoutAfterSearchingForProduct() {
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys(testDataProp.getProperty("validUserName"));
		driver.findElement(By.id("input-password")).sendKeys(testDataProp.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualMessage = (driver.findElement(By.linkText("Edit your account information")).getText());
		String expectedMessage = testDataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessage.contains(expectedMessage),"Edit your account information is not displayed");
		driver.findElement(By.name("search")).sendKeys("iMac");
		driver.findElement(By.cssSelector(".fa.fa-search")).click();
		String actualSearchAfterLogout = (driver.findElement(By.linkText("iMac"))).getText();
		String expectedSearchAfterLogout = "iMac";
		softassert.assertTrue(actualSearchAfterLogout.contains(expectedSearchAfterLogout), "Search criteria does not match");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Logout")).click();
		String actualLogoutConfirmationText = (driver.findElement(By.xpath("//p[contains(text(),'You have been logged off your account. It is now safe to leave the computer.')]")).getText());
		String expectedLogoutConfirmationText = testDataProp.getProperty("validateLogout");
		softassert.assertTrue(actualLogoutConfirmationText.contains(expectedLogoutConfirmationText),"Logout confirmation text does not match");
		softassert.assertAll();
	}
	
	@Test(priority = 7)
	public void verifyCorrectResultsAreDisplayedAfterASearch() {
		driver.findElement(By.name("search")).sendKeys("iMac");
		driver.findElement(By.cssSelector(".fa.fa-search")).click();
		String actualSearchProdDisplay = (driver.findElement(By.linkText("iMac"))).getText();
		String expectedSearchProdDisplay = "iMac";
		softassert.assertTrue(actualSearchProdDisplay.contains(expectedSearchProdDisplay), "Search criteria does not match");
		softassert.assertAll();
		
	}

	@Test(priority =8 )
	public void verifySearchWithSpecialCharacter() {
		driver.findElement(By.name("search")).sendKeys("@pple");
		driver.findElement(By.cssSelector(".fa.fa-search")).click();
		String actualSearchWithSpecialChar = (driver.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criteria.')]"))).getText();
		String expectedSearchWithSpecialChar = "There is no product that matches the search criteria.";
		softassert.assertTrue(actualSearchWithSpecialChar.contains(expectedSearchWithSpecialChar), "Search criteria does not match");
		softassert.assertAll();
	}
	
	
	@Test(priority = 9)
	public void verifySearchWhenTheSearchTermIsMisspelled() {
		driver.findElement(By.name("search")).sendKeys("appl");
		driver.findElement(By.cssSelector(".fa.fa-search")).click();
		String actualSearchMisspelled = (driver.findElement(By.linkText("Apple Cinema 30\""))).getText();
		String expectedSearchMisspelled = "Apple Cinema 30";
		softassert.assertTrue(actualSearchMisspelled.contains(expectedSearchMisspelled), "Search criteria does not match");
		softassert.assertAll();
		
	}
	
	@Test(priority = 10 )
	public void verifySearchByProvidingASearchCriteriaThatResultsInMultpleProducnts() {
		
	}
	
	@Test(priority = 11)
	public void verifySearchWithExtraSpacesBetweenLetters() {
		driver.findElement(By.name("search")).sendKeys("i    m a c");
		driver.findElement(By.cssSelector(".fa.fa-search")).click();
		String actualSearchWithExtraSpace = (driver.findElement(By.linkText("Apple Cinema 30\""))).getText();
		String expectedSearchWithExtraSpace = "Apple Cinema 30";
		softassert.assertTrue(actualSearchWithExtraSpace.contains(expectedSearchWithExtraSpace), "Search criteria does not match");
		softassert.assertAll();
	}
	
	@Test(priority = 12)
	public void verifySearchWithAllUppercase() {
		driver.findElement(By.name("search")).sendKeys("APPLE");
		driver.findElement(By.cssSelector(".fa.fa-search")).click();
		String actualSearchAllUppercase = (driver.findElement(By.linkText("Apple Cinema 30\""))).getText();
		String expectedSearchAllUppercase = "Apple Cinema 30";
		softassert.assertTrue(actualSearchAllUppercase.contains(expectedSearchAllUppercase), "Search criteria does not match");
		softassert.assertAll();
	}
	
	@Test(priority =13 )
	public void verifySearchWithAllLowercase() {
		driver.findElement(By.name("search")).sendKeys("apple");
		driver.findElement(By.cssSelector(".fa.fa-search")).click();
		String actualSearchAllLowercase = (driver.findElement(By.linkText("Apple Cinema 30\""))).getText();
		String expectedSearchAllLowercase = "Apple Cinema 30";
		softassert.assertTrue(actualSearchAllLowercase.contains(expectedSearchAllLowercase), "Search criteria does not match");
		softassert.assertAll();
	}
	
	@Test(priority = 14)
	public void verifySearchWithMixedUppercaseAndlowercase() {
		driver.findElement(By.name("search")).sendKeys("aPPle");
		driver.findElement(By.cssSelector(".fa.fa-search")).click();
		String actualSearchMixedLetter = (driver.findElement(By.linkText("Apple Cinema 30\""))).getText();
		String expectedSearchMixedLetter = "Apple Cinema 30";
		softassert.assertTrue(actualSearchMixedLetter.contains(expectedSearchMixedLetter), "Search criteria does not match");
		softassert.assertAll();
	}
	
	@Test(priority = 15)
	public void verifySearchingForProductAfterLogout() {
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys(testDataProp.getProperty("validUserName"));
		driver.findElement(By.id("input-password")).sendKeys(testDataProp.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualMessage = (driver.findElement(By.linkText("Edit your account information")).getText());
		String expectedMessage = testDataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessage.contains(expectedMessage),"Edit your account information is not displayed");
		driver.findElement(By.linkText("Logout")).click();
		String actualLogoutConfirmationText = (driver.findElement(By.xpath("//p[contains(text(),'You have been logged off your account. It is now safe to leave the computer.')]")).getText());
		String expectedLogoutConfirmationText = testDataProp.getProperty("validateLogout");
		softassert.assertTrue(actualLogoutConfirmationText.contains(expectedLogoutConfirmationText),"Logout confirmation text does not match");
		driver.findElement(By.name("search")).sendKeys("iMac");
		driver.findElement(By.cssSelector(".fa.fa-search")).click();
		String actualSearchAfterLogout = (driver.findElement(By.linkText("iMac"))).getText();
		String expectedSearchAfterLogout = "iMac";
		softassert.assertTrue(actualSearchAfterLogout.contains(expectedSearchAfterLogout), "Search criteria does not match");
		softassert.assertAll();
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	
	
}
