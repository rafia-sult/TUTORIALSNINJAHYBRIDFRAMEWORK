package com.tutorials.ninja.qa.testcases;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorials.ninja.qa.testbase.TestBase;
import com.tutorials.ninja.qa.testdata.SupplyTestDataFromExcel;
import com.tutorials.ninja.qa.utils.Utilities;

public class LoginTest extends TestBase {
	

	public LoginTest() throws Exception {
		super();
	}

	public static ChromeOptions options;
	public static WebDriver driver;
	public static SoftAssert softassert = new SoftAssert();
	


	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(configProp.getProperty("browserName"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
	}

	@Test(priority = 1, dataProvider = "TutorialsNinjaDataProviderSupply", dataProviderClass = SupplyTestDataFromExcel.class)
	public void verifyLoginUsingValidCredentials(String username, String password) {
		driver.findElement(By.id("input-email")).sendKeys(username);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualMessageForLogin = (driver.findElement(By.linkText("Edit your account information")).getText());
		String expectedMessageForLogin = testDataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin),
				"Edit your account information is not displayed");
		softassert.assertAll();

	}

	@Test(priority = 2)
	public void verifyLoginUsingInvalidCredentials() {
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(Utilities.generatePasswordWithTimeStamp());
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = (driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible"))
				.getText());
		String expectedWarningMessage = testDataProp.getProperty("warningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
		softassert.assertAll();

	}

	@Test(priority = 3)
	public void verifyLoginUsingInvalidEmailandValidPassword() {
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(configProp.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = (driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible"))
				.getText());
		String expectedWarningMessage = testDataProp.getProperty("warningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
		softassert.assertAll();
	}

	@Test(priority = 4)
	public void verifyLoginUsingValidEmailandInvalidPassword() {
		driver.findElement(By.id("input-email")).sendKeys(configProp.getProperty("validUserName"));
		driver.findElement(By.id("input-password")).sendKeys(Utilities.generatePasswordWithTimeStamp());
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = (driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible"))
				.getText());
		String expectedWarningMessage = testDataProp.getProperty("warningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
		softassert.assertAll();
	}

	@Test(priority = 5)
	public void verifyLoginUsingBlankEmailandValidPassword() {
		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys(configProp.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = (driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible"))
				.getText());
		String expectedWarningMessage = testDataProp.getProperty("warningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
		softassert.assertAll();

	}

	@Test(priority = 6)
	public void verifyLoginUsingValidEmailandBlankPassword() {
		driver.findElement(By.id("input-email")).sendKeys(configProp.getProperty("validUserName"));
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = (driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible"))
				.getText());
		String expectedWarningMessage = testDataProp.getProperty("warningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
		softassert.assertAll();

	}

	@Test(priority = 7)
	public void verifyLoginUsingBlankCredentials() {
		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = (driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible"))
				.getText());
		String expectedWarningMessage = testDataProp.getProperty("warningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
		softassert.assertAll();
	}

	@Test(priority = 8)
	public void verifyLoginUsingInactiveCredentials() {
		driver.findElement(By.id("input-email")).sendKeys(testDataProp.getProperty("inactiveUserName"));
		driver.findElement(By.id("input-password")).sendKeys(testDataProp.getProperty("inactivePassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = (driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible"))
				.getText());
		String expectedWarningMessage = testDataProp.getProperty("warningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
		softassert.assertAll();
	}


	@Test(priority = 9)
	public void verifyWorkingOfForgetPasswordLink() {
		driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::a")).click();
		String actualWarningMessage = (driver.findElement(By.xpath("//p[contains(text(),'Enter the e-mail address associated with your account. Click submit to have a password reset link e-mailed to you.')]")).getText());
		String expectedWarningMessage = testDataProp.getProperty("validateForgetPasswordPage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
		softassert.assertAll();
	}

	@Test(priority = 10)
	public void verifyLoginIntoApplicationAndBrowsingBackWithoutBeingLoggedOut() {
		driver.findElement(By.id("input-email")).sendKeys(configProp.getProperty("validUserName"));
		driver.findElement(By.id("input-password")).sendKeys(configProp.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualMessageForLogin = (driver.findElement(By.linkText("Edit your account information")).getText());
		String expectedMessageForLogin = testDataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin),
				"Edit your account information is not displayed");
		softassert.assertAll();
		driver.navigate().back();

	}

//	@Test(priority = 11, invocationCount = 6)
//	public void verifyNumberOfUnsuccessfulLoginAttempts() {
//		driver.findElement(By.id("input-email")).sendKeys("johnsmith@gmail.com");
//		driver.findElement(By.id("input-password")).sendKeys("12345");
//		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
//		String actualWarningMessage = (driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible"))
//				.getText());
//		String expectedWarningMessage = testDataProp.getProperty("warningMessageForUnsuccessfulLoginAttempt");
//		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
//		softassert.assertAll();
//
//	}

	@Test(priority = 12)
	public void verifyNavigationToDifferentPagesFromLoginPage() throws Exception {
		driver.findElement(By.id("input-email")).sendKeys(configProp.getProperty("validUserName"));
		driver.findElement(By.id("input-password")).sendKeys(configProp.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualMessageForLogin = (driver.findElement(By.linkText("Edit your account information")).getText());
		String expectedMessageForLogin = testDataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin), "Edit your account is not displayed");
		softassert.assertAll();
		driver.findElement(By.linkText("Tablets")).click();
		driver.navigate().back();
	}

	@Test(priority = 13)
	public void verifyOpeningOfLoginLinkInANewTab() throws Exception {

		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).moveToElement(driver.findElement(By.linkText("Login"))).click().perform();

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));

	}

	@Test(priority = 14)
	public void verifyOpeningOfLoginLinkInANewWindow() throws Exception {
//		WebElement loginLink = driver.findElement(By.linkText("Login"));

		Actions action = new Actions(driver);
		action.keyDown(Keys.SHIFT).click(driver.findElement(By.linkText("Login"))).keyUp(Keys.SHIFT).click().perform();
	}

	@Test(priority = 15)
	public void verifyProperLogoutAfterLogin() {
		driver.findElement(By.id("input-email")).sendKeys(configProp.getProperty("validUserName"));
		driver.findElement(By.id("input-password")).sendKeys(configProp.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualMessageForLogin = (driver.findElement(By.linkText("Edit your account information")).getText());
		String expectedMessageForLogin = testDataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin), "Edit your account information is not displayed");
		driver.findElement(By.linkText("Logout")).click();
		String actualMessageForLogout = (driver.findElement(By.xpath(
				"//p[contains(text(),'You have been logged off your account. It is now safe to leave the computer.')]"))
				.getText());
		String expectedMessageForLogout = testDataProp.getProperty("validateLogout");
		softassert.assertTrue(actualMessageForLogout.contains(expectedMessageForLogout),
				"Logout confirmation text does not match");
		softassert.assertAll();

	}

//	@Test(priority = 16)
//	public void verifyLoginWithNewPasswordRightAfterUpdatingThePassword() {
//		driver.findElement(By.id("input-email")).sendKeys("johnsmith20@gmail.com");
//		driver.findElement(By.id("input-password")).sendKeys("Selenium@278");
//		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
//		String actualMessageForLogin = (driver.findElement(By.linkText("Edit your account information")).getText());
//		String expectedMessageForLogin = testDataProp.getProperty("validateLogin");
//		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin),
//				"Edit your account information is not displayed");
//		driver.findElement(By.linkText("Change your password")).click();
//		driver.findElement(By.id("input-password")).sendKeys("Selenium@11278");
//		driver.findElement(By.id("input-confirm")).sendKeys("Selenium@11278");
//		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
//		String actualMessage1 = (driver.findElement(By.cssSelector(".alert.alert-success.alert-dismissible"))
//				.getText());
//		String expectedMessage1 = "Success: Your password has been successfully updated.";
//		softassert.assertTrue(actualMessage1.contains(expectedMessage1), "My Account is not displayed");
//		driver.findElement(By.linkText("Logout")).click();
//		String actualMessageForLogout = (driver.findElement(By.xpath(
//				"//p[contains(text(),'You have been logged off your account. It is now safe to leave the computer.')]"))
//				.getText());
//		String expectedMessageForLogout = testDataProp.getProperty("validateLogout");
//		softassert.assertTrue(actualMessageForLogout.contains(expectedMessageForLogout),
//				"Logout confirmation text does not match");
//		driver.findElement(By.linkText("My Account")).click();
//		driver.findElement(By.linkText("Login")).click();
//		driver.findElement(By.id("input-email")).sendKeys("johnsmith20@gmail.com");
//		driver.findElement(By.id("input-password")).sendKeys("Selenium@11278");
//		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
//		String actualMessage2 = (driver.findElement(By.linkText("Edit your account information")).getText());
//		String expectedMessage2 = testDataProp.getProperty("validateLogin");
//		softassert.assertTrue(actualMessage2.contains(expectedMessage2), "Edit your account is not displayed");
//		softassert.assertAll();
//
//	}

	@Test(priority = 17)
	public void verifyLoginUsingValidCredentialsPressingTheEnterKey() {
		driver.findElement(By.id("input-email")).sendKeys(configProp.getProperty("validUserName"));
		driver.findElement(By.id("input-password")).sendKeys(configProp.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).sendKeys(Keys.ENTER);
		String actualMessageForLogin = (driver.findElement(By.linkText("Edit your account information")).getText());
		String expectedMessageForLogin = testDataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin), "Edit your account is not displayed");
		softassert.assertAll();
	}

	@Test(priority = 18)
	public void verifyTabKeyofTheKeyboardWorksOnLoginPage() throws Exception {
		driver.findElement(By.id("input-email")).sendKeys(configProp.getProperty("validUserName"));
//		WebElement password1 = driver.findElement(By.id("input-password"));
//		
//		password1.sendKeys(configProp.getProperty("validPassword"));
//		
//		password1.sendKeys(Keys.TAB);

		driver.findElement(By.id("input-password")).sendKeys(configProp.getProperty("validPassword"));
		driver.findElement(By.id("input-password")).sendKeys(Keys.TAB.TAB.ENTER);
		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("input.btn.btn-primary")).sendKeys(Keys.ENTER);
		String actualMessageForLogin = (driver.findElement(By.linkText("Edit your account information")).getText());
		String expectedMessageForLogin = testDataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin), "Edit your account is not displayed");
		softassert.assertAll();
		
	}

	@Test(priority = 19)
	public void verifyCopyAndPasteofThePasswordIsDisabled() throws Exception {
//		WebElement password = driver.findElement(By.id("input-password"));
//		password.sendKeys(Utilities.generatePasswordWithTimeStamp());
//		password.sendKeys(Keys.CONTROL + "a");
//		password.sendKeys(Keys.CONTROL + "c");
//
//		WebElement login = driver.findElement(By.id("input-email"));
//		Thread.sleep(2000);
//		login.sendKeys(Keys.CONTROL + "v");

//		driver.findElement(By.id("input-email")).sendKeys("riftarafia@gmail.com");
//		driver.findElement(By.id("input-password")).sendKeys("rafiasultana");
//		String passwordRetrieve = driver.findElement(By.id("input-password")).getAttribute("value");
//		System.out.println(passwordRetrieve);
		
		
		
	}
	
	@Test(priority = 20)
	public void verifyNavigationToCommandInLoginPage() {
		driver.navigate().to("https://www.google.com/");
		driver.findElement(By.name("q")).sendKeys("Tutorials Ninja");
		driver.findElement(By.name("btnK")).click();

		


		
		
		
		
		
	}
	
	@Test(priority = 21)
	public void verifyNavigationBackCommandInLoginPage() throws Exception {
		driver.navigate().to("https://www.google.com/");

		driver.navigate().back();
		driver.findElement(By.id("input-email")).sendKeys(configProp.getProperty("validUserName"));

	
		
	}

	@Test(priority = 22)
	public void verifyInNavigationForwardCommandOfLoginPage() {
		driver.navigate().to("https://www.google.com/");
		driver.navigate().back();
		driver.navigate().forward();

		
	}
	
	@Test(priority = 21)
	public void verifyNavigationRefreshCommandInLoginPage() {
		driver.findElement(By.id("input-email")).sendKeys(configProp.getProperty("validUserName"));
		driver.navigate().refresh();
		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
