package com.tutorials.ninja.qa.testcases;

import java.util.ArrayList;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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
	public void verifyLoginUsingValidCredentials(String username, String password) {
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
	public void verifyLoginUsingInvalidCredentials() {
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
	public void verifyLoginUsingInvalidEmailandValidPassword() {
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
	public void verifyLoginUsingValidEmailandInvalidPassword() {
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
	public void verifyLoginUsingBlankEmailandValidPassword() {
		loginpage = new LoginPage(driver);
		loginpage.enterPassword(prop.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveWarningMessageInfoDisplayedOrNot();
		String expectedWarningMessage = dataProp.getProperty("warningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
		softassert.assertAll();

	}

	@Test(priority = 6)
	public void verifyLoginUsingValidEmailandBlankPassword() {
		loginpage = new LoginPage(driver);
		loginpage.enterEmailId(prop.getProperty("validUserName"));
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveWarningMessageInfoDisplayedOrNot();
		String expectedWarningMessage = dataProp.getProperty("warningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
		softassert.assertAll();

	}

	@Test(priority = 7)
	public void verifyLoginUsingBlankCredentials() {
		loginpage = new LoginPage(driver);
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveWarningMessageInfoDisplayedOrNot();
		String expectedWarningMessage = dataProp.getProperty("warningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
		softassert.assertAll();

	}

	@Test(priority = 8)
	public void verifyLoginUsingInactiveCredentials() {

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
	public void verifyWorkingOfForgetPasswordLink() {
		loginpage = new LoginPage(driver);
		loginpage.clickOnForgottenPasswordLink();
		String actualWarningMessage = loginpage.validateForgottenPasswordPage();
		String expectedWarningMessage = dataProp.getProperty("validateForgetPasswordPage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message Does Not Match");
		softassert.assertAll();
	}

	@Test(priority = 10)
	public void verifyLoginIntoApplicationAndBrowsingBackWithoutBeingLoggedOut() {
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

	@Test(priority = 11, invocationCount = 8)
	public void verifyNumberOfUnsuccessfulLoginAttempts() {
		loginpage = new LoginPage(driver);
		loginpage.enterEmailId(prop.getProperty("unsuccessfulLoginAttempEmail"));
		loginpage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();
		String actualMessageForLogin = loginpage.unsuccessfulLoginAttempMessageDisplayedOrNot();
		String expectedMessageForLogin = dataProp.getProperty("warningMessageForUnsuccessfulLoginAttempt");
		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin),
				"Warning message does not match");
		softassert.assertAll();

	}

	@Test(priority = 12)
	public void verifyNavigationToDifferentPagesFromLoginPage() {
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
	public void verifyOpeningOfLoginPageInANewTab() {
		action = new Actions(driver);
		action.keyDown(Keys.CONTROL).moveToElement(landingpage.clickOnLoginLink()).click().perform();
		;
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));

	}

	@Test(priority = 14)
	public void verifyOpeningOfLoginPageInANewWindow() {

		action = new Actions(driver);
		action.keyDown(Keys.SHIFT).click(landingpage.clickOnLoginLink()).keyUp(Keys.SHIFT).click().perform();
	}

	@Test(priority = 15)
	public void verifyProperLogoutAfterLogin() {
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
		String actualMessageForLogout = logoutpage.logoutValidationMessageDisplayedOrNot();
		String expectedMessageForLogout = dataProp.getProperty("validateLogout");
		softassert.assertTrue(actualMessageForLogout.contains(expectedMessageForLogout),
				"Logout confirmation text does not match");
		softassert.assertAll();

	}

	@Test(priority = 16)
	public void verifyUpdatingThePassword() {
		loginpage = new LoginPage(driver);
		insideofloginpage = new InsideOfLoginPage(driver);
		loginpage.enterEmailId(dataProp.getProperty("updatePasswordEmail"));
		loginpage.enterPassword(dataProp.getProperty("currentPassword"));
		loginpage.clickOnLoginButton();
		String actualMessageForLogin = insideofloginpage.editYourAccountInfoDisplayedOrNot();
		String expectedMessageForLogin = dataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin),
				"Edit your account information is not displayed");
		insideofloginpage.clickOnChangeYourPassword();
		insideofloginpage.clickOnPassword(dataProp.getProperty("updatePassword"));
		insideofloginpage.clickOnPasswordConfirm(dataProp.getProperty("updatePasswordConfirm"));
		insideofloginpage.clickOnContinueButton();
		String actualMessageForUpdatingPass = insideofloginpage.updatingPasswordAlertDisplayedOrNot();
		String expectedMessageForUpdatingPass = dataProp.getProperty("updatePasswordMessage");
		softassert.assertTrue(actualMessageForUpdatingPass.contains(expectedMessageForUpdatingPass),
				"Password successfully updated message is not displayed");

	}

	@Test(priority = 17)
	public void verifyProperLogoutAfterUpdatingThePassword() {
		loginpage = new LoginPage(driver);
		insideofloginpage = new InsideOfLoginPage(driver);
		logoutpage = new LogoutPage(driver);
		loginpage.enterEmailId(dataProp.getProperty("updatePasswordEmail"));
		loginpage.enterPassword(dataProp.getProperty("currentPassword"));
		loginpage.clickOnLoginButton();
		String actualMessageForLogin = insideofloginpage.editYourAccountInfoDisplayedOrNot();
		String expectedMessageForLogin = dataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin),
				"Edit your account information is not displayed");
		insideofloginpage.clickOnChangeYourPassword();
		insideofloginpage.clickOnPassword(dataProp.getProperty("updatePassword"));
		insideofloginpage.clickOnPasswordConfirm(dataProp.getProperty("updatePasswordConfirm"));
		insideofloginpage.clickOnContinueButton();
		String actualMessageForUpdatingPass = insideofloginpage.updatingPasswordAlertDisplayedOrNot();
		String expectedMessageForUpdatingPass = dataProp.getProperty("updatePasswordMessage");
		softassert.assertTrue(actualMessageForUpdatingPass.contains(expectedMessageForUpdatingPass),
				"Password successfully updated message is not displayed");
		insideofloginpage.clickOnLogoutButton();
		String actualMessageForLogout = logoutpage.logoutValidationMessageDisplayedOrNot();
		String expectedMessageForLogout = dataProp.getProperty("validateLogout");
		softassert.assertTrue(actualMessageForLogout.contains(expectedMessageForLogout),
				"Logout confirmation text does not match");
		softassert.assertAll();

	}

	@Test(priority = 18)
	public void verifyLoginWithNewPasswordAfterUpdatingThePassword() {
		loginpage = new LoginPage(driver);
		insideofloginpage = new InsideOfLoginPage(driver);
		logoutpage = new LogoutPage(driver);
		landingpage = new LandingPage(driver);
		loginpage.enterEmailId(dataProp.getProperty("updatePasswordEmail"));
		loginpage.enterPassword(dataProp.getProperty("currentPassword"));
		loginpage.clickOnLoginButton();
		String actualMessageForLogin = insideofloginpage.editYourAccountInfoDisplayedOrNot();
		String expectedMessageForLogin = dataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin),
				"Edit your account information is not displayed");
		insideofloginpage.clickOnChangeYourPassword();
		insideofloginpage.clickOnPassword(dataProp.getProperty("updatePassword"));
		insideofloginpage.clickOnPasswordConfirm(dataProp.getProperty("updatePasswordConfirm"));
		insideofloginpage.clickOnContinueButton();
		String actualMessageForUpdatingPass = insideofloginpage.updatingPasswordAlertDisplayedOrNot();
		String expectedMessageForUpdatingPass = dataProp.getProperty("updatePasswordMessage");
		softassert.assertTrue(actualMessageForUpdatingPass.contains(expectedMessageForUpdatingPass),
				"Password successfully updated message is not displayed");
		insideofloginpage.clickOnLogoutButton();
		String actualMessageForLogout = logoutpage.logoutValidationMessageDisplayedOrNot();
		String expectedMessageForLogout = dataProp.getProperty("validateLogout");
		softassert.assertTrue(actualMessageForLogout.contains(expectedMessageForLogout),
				"Logout confirmation text does not match");
		landingpage.clickOnMyAccountLink();
		landingpage.clickOnLoginLink();
		loginpage.enterEmailId(dataProp.getProperty("updatePasswordEmail"));
		loginpage.enterPassword(dataProp.getProperty("currentPassword"));
		loginpage.clickOnLoginButton();
		String actualMessageForLogin1 = insideofloginpage.editYourAccountInfoDisplayedOrNot();
		String expectedMessageForLogin1 = dataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessageForLogin1.contains(expectedMessageForLogin1),
				"Edit your account information is not displayed");
		softassert.assertAll();

	}

	@Test(priority = 19)
	public void verifyLoginPressingTheEnterKeyOfKeyboard() {
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

	@Test(priority = 20)
	public void verifyWorkingOfBackspaceKeyOnLoginPage() {
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

	@Test(priority = 21)
	public void verifyNavigationTOcommandInLoginPage() {
		driver.navigate().to(dataProp.getProperty("googleURL"));
	}

	@Test(priority = 22)
	public void verifyNavigationBACKcommandInLoginPage() {
		loginpage = new LoginPage(driver);
		driver.navigate().to(dataProp.getProperty("googleURL"));
		driver.navigate().back();
		loginpage.enterEmailId(prop.getProperty("validUserName"));
	}

	@Test(priority = 23)
	public void verifyNavigationFORWARDcommandOfLoginPage() {
		loginpage = new LoginPage(driver);
		loginpage.enterEmailId(prop.getProperty("validUserName"));
		driver.navigate().back();
		driver.navigate().forward();
	}

	@Test(priority = 24)
	public void verifyNavigationREFRESHcommandInLoginPage() {
		loginpage = new LoginPage(driver);
		loginpage.enterEmailId(prop.getProperty("validUserName"));
		driver.navigate().refresh();

	}

	@Test(priority = 25)
	public void verifyTabKeyOfTheKeyboardWorksOnLoginPage() {
		loginpage = new LoginPage(driver);
		insideofloginpage = new InsideOfLoginPage(driver);
		loginpage.enterEmailId(prop.getProperty("validUserName")).sendKeys(Keys.TAB, prop.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		String actualMessageForLogin = insideofloginpage.editYourAccountInfoDisplayedOrNot();
		String expectedMessageForLogin = dataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin),
				"Edit your account information is not displayed");
		softassert.assertAll();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
