package com.tutorials.ninja.qa.testcases;

import java.util.ArrayList;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorials.ninja.qa.testbase.TestBase;
import com.tutorials.ninja.qa.testdata.SupplyTestDataFromExcel;
import com.tutorials.ninja.qa.testpages.InsideOfLoginPage;
import com.tutorials.ninja.qa.testpages.LandingPage;
import com.tutorials.ninja.qa.testpages.LoginPage;
import com.tutorials.ninja.qa.testpages.LogoutPage;
import com.tutorials.ninja.qa.utils.Utilities;

public class LoginTest extends TestBase {

	public LoginTest() throws Exception {
		super();
	}

	public static WebDriver driver;
	public static SoftAssert softassert = new SoftAssert();
	public static LandingPage landingpage;
	public static LoginPage loginpage;
	public static InsideOfLoginPage insideofloginpage;
	public static LogoutPage logoutpage;
	public static Actions action;

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		landingpage = new LandingPage(driver);
		landingpage.clickOnMyAccountLink();
		landingpage.clickOnLoginLink();

	}

	@Test(priority = 1, dataProvider = "TutorialsNinjaDataProviderSupply", dataProviderClass = SupplyTestDataFromExcel.class)
	public void verifyLoginUsingValidCredentials_001(String username, String password) {
		loginpage = new LoginPage(driver);
		insideofloginpage = new InsideOfLoginPage(driver);
		loginpage.enterEmailId(username);
		loginpage.enterPassword(password);
		loginpage.clickOnLoginButton();
		String actualMessageForLogin = insideofloginpage.editYourAccountInfoDisplayedOrNot();
		String expectedMessageForLogin = dataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin),
				"Edit your account information is not displayed");
		softassert.assertAll();

	}

	@Test(priority = 2)
	public void verifyLoginUsingInvalidCredentials_002() {
		loginpage = new LoginPage(driver);
		loginpage.enterEmailId(Utilities.generateEmailWithTimeStamp());
		loginpage.enterPassword(Utilities.generatePassword(14));
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveWarningMessageInfoDisplayedOrNot();
		String expectedWarningMessage = dataProp.getProperty("warningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
		softassert.assertAll();

	}

	@Test(priority = 3)
	public void verifyLoginUsingInvalidEmailandValidPassword_003() {
		loginpage = new LoginPage(driver);
		loginpage.enterEmailId(Utilities.generateEmailWithTimeStamp());
		loginpage.enterPassword(prop.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveWarningMessageInfoDisplayedOrNot();
		String expectedWarningMessage = dataProp.getProperty("warningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
		softassert.assertAll();
	}

	@Test(priority = 4)
	public void verifyLoginUsingValidEmailandInvalidPassword_004() {
		loginpage = new LoginPage(driver);
		loginpage.enterEmailId(prop.getProperty("validUserName"));
		loginpage.enterPassword(Utilities.generatePassword(14));
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveWarningMessageInfoDisplayedOrNot();
		String expectedWarningMessage = dataProp.getProperty("warningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
		softassert.assertAll();
	}

	@Test(priority = 5)
	public void verifyLoginUsingBlankEmailandValidPassword_005() {
		loginpage = new LoginPage(driver);
		loginpage.enterPassword(prop.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveWarningMessageInfoDisplayedOrNot();
		String expectedWarningMessage = dataProp.getProperty("warningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
		softassert.assertAll();

	}

	@Test(priority = 6)
	public void verifyLoginUsingValidEmailandBlankPassword_006() {
		loginpage = new LoginPage(driver);
		loginpage.enterEmailId(prop.getProperty("validUserName"));
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveWarningMessageInfoDisplayedOrNot();
		String expectedWarningMessage = dataProp.getProperty("warningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
		softassert.assertAll();

	}

	@Test(priority = 7)
	public void verifyLoginUsingBlankCredentials_007() {
		loginpage = new LoginPage(driver);
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveWarningMessageInfoDisplayedOrNot();
		String expectedWarningMessage = dataProp.getProperty("warningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
		softassert.assertAll();

	}

	@Test(priority = 8)
	public void verifyLoginUsingInactiveCredentials_008() {

		loginpage = new LoginPage(driver);
		loginpage.enterEmailId(dataProp.getProperty("inactiveUserName"));
		loginpage.enterPassword(dataProp.getProperty("inactivePassword"));
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveWarningMessageInfoDisplayedOrNot();
		String expectedWarningMessage = dataProp.getProperty("warningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
		softassert.assertAll();

	}

	@Test(priority = 9)
	public void verifyWorkingOfForgetPasswordLink_009() {
		loginpage = new LoginPage(driver);
		loginpage.clickOnForgottenPasswordLink();
		String actualWarningMessage = loginpage.validateForgottenPasswordPage();
		String expectedWarningMessage = dataProp.getProperty("validateForgetPasswordPage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
		softassert.assertAll();
	}

	@Test(priority = 10)
	public void verifyLoginIntoApplicationAndBrowsingBackWithoutBeingLoggedOut_010() {
		loginpage = new LoginPage(driver);
		insideofloginpage = new InsideOfLoginPage(driver);
		loginpage.enterEmailId(prop.getProperty("validUserName"));
		loginpage.enterPassword(prop.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		String actualMessageForLogin = insideofloginpage.editYourAccountInfoDisplayedOrNot();
		String expectedMessageForLogin = dataProp.getProperty("validateLogin");
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
//		String expectedWarningMessage = dataProp.getProperty("warningMessageForUnsuccessfulLoginAttempt");
//		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
//		softassert.assertAll();
//
//	}

	@Test(priority = 12)
	public void verifyNavigationToDifferentPagesFromLoginPage_011() {
		loginpage = new LoginPage(driver);
		insideofloginpage = new InsideOfLoginPage(driver);
		loginpage.enterEmailId(prop.getProperty("validUserName"));
		loginpage.enterPassword(prop.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		String actualMessageForLogin = insideofloginpage.editYourAccountInfoDisplayedOrNot();
		String expectedMessageForLogin = dataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin),
				"Edit your account information is not displayed");
		softassert.assertAll();
		insideofloginpage.clickOnPhonesAndPDAsButton();
		driver.navigate().back();
		insideofloginpage.clickOnTabletsButton();
	}

	@Test(priority = 13)
	public void verifyOpeningOfLoginPageInANewTab_012() {
		action = new Actions(driver);
		action.keyDown(Keys.CONTROL).moveToElement(landingpage.clickOnLoginLink()).click().perform();
		;
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));

	}

	@Test(priority = 14)
	public void verifyOpeningOfLoginPageInANewWindow_013() {

		action = new Actions(driver);
		action.keyDown(Keys.SHIFT).click(landingpage.clickOnLoginLink()).keyUp(Keys.SHIFT).click().perform();
	}

	@Test(priority = 15)
	public void verifyProperLogoutAfterLogin_014() {
		loginpage = new LoginPage(driver);
		insideofloginpage = new InsideOfLoginPage(driver);
		logoutpage = new LogoutPage(driver);
		loginpage.enterEmailId(prop.getProperty("validUserName"));
		loginpage.enterPassword(prop.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		String actualMessageForLogin = insideofloginpage.editYourAccountInfoDisplayedOrNot();
		String expectedMessageForLogin = dataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin),
				"Edit your account information is not displayed");
		insideofloginpage.clickOnLogoutButton();
		String actualMessageForLogout = logoutpage.retrieveLogoutValidationMessage();
		String expectedMessageForLogout = dataProp.getProperty("validateLogout");
		softassert.assertTrue(actualMessageForLogout.contains(expectedMessageForLogout),
				"Logout confirmation text does not match");
		softassert.assertAll();

	}

//	@Test(priority = 16)
//	public void verifyLoginWithNewPasswordRightAfterUpdatingThePassword() {
//		loginpage = new LoginPage(driver);
//		insideofloginpage = new InsideOfLoginPage(driver);
//		loginpage.enterEmailId(prop.getProperty("validUserName"));
//		loginpage.enterPassword(prop.getProperty("validPassword"));
//		loginpage.clickOnLoginButton();
//		String actualMessageForLogin = insideofloginpage.editYourAccountInfoDisplayedOrNot();
//		String expectedMessageForLogin = dataProp.getProperty("validateLogin");
//		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin),
//				"Edit your account information is not displayed");
//		
//		driver.findElement(By.linkText("Change your password")).click();
//		driver.findElement(By.id("input-password")).sendKeys(Utilities.generatePassword(14));
//		driver.findElement(By.id("input-confirm")).sendKeys(Utilities.generatePassword(14));
//		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
//		String actualMessage1 = (driver.findElement(By.cssSelector(".alert.alert-success.alert-dismissible"))
//				.getText());
//		String expectedMessage1 = "Success: Your password has been successfully updated.";
//		softassert.assertTrue(actualMessage1.contains(expectedMessage1), "My Account is not displayed");
//		driver.findElement(By.linkText("Logout")).click();
//		String actualMessageForLogout = (driver.findElement(By.xpath(
//				"//p[contains(text(),'You have been logged off your account. It is now safe to leave the computer.')]"))
//				.getText());
//		String expectedMessageForLogout = dataProp.getProperty("validateLogout");
//		softassert.assertTrue(actualMessageForLogout.contains(expectedMessageForLogout),
//				"Logout confirmation text does not match");
//		driver.findElement(By.linkText("My Account")).click();
//		driver.findElement(By.linkText("Login")).click();
//		driver.findElement(By.id("input-email")).sendKeys("johnsmith20@gmail.com");
//		driver.findElement(By.id("input-password")).sendKeys(Utilities.generatePassword(14));
//		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
//		String actualMessage2 = (driver.findElement(By.linkText("Edit your account information")).getText());
//		String expectedMessage2 = dataProp.getProperty("validateLogin");
//		softassert.assertTrue(actualMessage2.contains(expectedMessage2), "Edit your account is not displayed");
//		softassert.assertAll();
//
//	}

	@Test(priority = 16)
	public void verifyLoginPressingTheEnterKeyOfKeyboard_015() {
		loginpage = new LoginPage(driver);
		insideofloginpage = new InsideOfLoginPage(driver);
		loginpage.enterEmailId(prop.getProperty("validUserName"));
		loginpage.enterPassword(prop.getProperty("validPassword")).sendKeys(Keys.ENTER);
		String actualMessageForLogin = insideofloginpage.editYourAccountInfoDisplayedOrNot();
		String expectedMessageForLogin = dataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin),
				"Edit your account information is not displayed");
		softassert.assertAll();

	}

	@Test(priority = 17)
	public void verifyTabKeyofTheKeyboardWorksOnLoginPage_016() {
		loginpage = new LoginPage(driver);
		insideofloginpage = new InsideOfLoginPage(driver);
		loginpage.enterEmailId(prop.getProperty("validUserName"));
		loginpage.enterPassword(prop.getProperty("validPassword")).sendKeys(Keys.TAB.ENTER);
		String actualMessageForLogin = insideofloginpage.editYourAccountInfoDisplayedOrNot();
		String expectedMessageForLogin = dataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin),
				"Edit your account information is not displayed");
		softassert.assertAll();

	}

//	@Test(priority = 19)
//	public void verifyCopyAndPasteOfPasswordIsDisabled() {
//
//		WebElement password = driver.findElement(By.id("input-password"));
//		password.sendKeys(Utilities.generatePasswordWithTimeStamp());
//		password.sendKeys(Keys.CONTROL + "a");
//		password.sendKeys(Keys.CONTROL + "c");
//
//		WebElement login = driver.findElement(By.id("input-email"));
//		Thread.sleep(2000);
//		login.sendKeys(Keys.CONTROL + "v");
//
//		driver.findElement(By.id("input-email")).sendKeys("riftarafia@gmail.com");
//		driver.findElement(By.id("input-password")).sendKeys("rafiasultana");
//		String passwordRetrieve = driver.findElement(By.id("input-password")).getAttribute("value");
//		System.out.println(passwordRetrieve);
//
//	}

	@Test(priority = 18)
	public void verifyNavigationTOcommandInLoginPage_017() {
		driver.navigate().to(dataProp.getProperty("googleURL"));
	}

	@Test(priority = 19)
	public void verifyNavigationBACKcommandInLoginPage_018() {
		loginpage = new LoginPage(driver);
		driver.navigate().to(dataProp.getProperty("googleURL"));
		driver.navigate().back();
		loginpage.enterEmailId(prop.getProperty("validUserName"));
	}

	@Test(priority = 20)
	public void verifyNavigationFORWARDcommandOfLoginPage_019() {
		loginpage = new LoginPage(driver);
		loginpage.enterEmailId(prop.getProperty("validUserName"));
		driver.navigate().back();
		driver.navigate().forward();
	}

	@Test(priority = 21)
	public void verifyNavigationREFRESHcommandInLoginPage_020() {
		loginpage = new LoginPage(driver);
		loginpage.enterEmailId(prop.getProperty("validUserName"));
		driver.navigate().refresh();

	}

	@Test(priority = 22)
	public void verifyWorkingOfBackspaceKeyOnLoginPage_021() {
		loginpage = new LoginPage(driver);
		insideofloginpage = new InsideOfLoginPage(driver);
		loginpage.enterEmailId(prop.getProperty("validUserName"));
		loginpage.enterPassword(prop.getProperty("validPassword")).sendKeys(Keys.BACK_SPACE);
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveWarningMessageInfoDisplayedOrNot();
		String expectedWarningMessage = dataProp.getProperty("warningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
		softassert.assertAll();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
