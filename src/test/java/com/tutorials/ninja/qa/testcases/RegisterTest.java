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
import com.tutorials.ninja.qa.testpages.LogoutPage;
import com.tutorials.ninja.qa.testpages.RegisterPage;
import com.tutorials.ninja.qa.testpages.RegistrationSuccessPage;
import com.tutorials.ninja.qa.utils.Utilities;

public class RegisterTest extends TestBase {

	public RegisterTest() throws Exception {
		super();
	}

	public static ChromeOptions options;
	public static WebDriver driver;
	public static SoftAssert softassert = new SoftAssert();
	public static LandingPage landingpage;
	public static RegisterPage registerpage;
	public static RegistrationSuccessPage registrationsuccesspage;
	public static InsideOfLoginPage insideofloginpage;
	public static LogoutPage logoutpage;
	public static Actions action;

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		landingpage = new LandingPage(driver);
		landingpage.clickOnMyAccountLink();
		landingpage.clickOnRegisterLink();

	}

	@Test(priority = 1)
	public void verifyRegisterAnAccountWithOnlyMandatoryFields() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephone(Utilities.generatePhoneNumber(11));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clickOnPrivacyPolicyButton();
		registerpage.clickOnContinueButton();
		String actualMessageForRegister = registerpage.successfullyRegisteredMessageIsDisplayedOrNot();
		String expectedMessageForRegister = dataProp.getProperty("successfullyRegisteredVerifyMessage");
		softassert.assertTrue(actualMessageForRegister.contains(expectedMessageForRegister),
				"Validation for registering account does not match");
		softassert.assertAll();

	}

	@Test(priority = 2)
	public void verifyRegisterAnAccountByProvidingAllFields() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephone(Utilities.generatePhoneNumber(11));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clickOnSubscribeButton();
		registerpage.clickOnPrivacyPolicyButton();
		registerpage.clickOnContinueButton();
		String actualMessageForRegister = registerpage.successfullyRegisteredMessageIsDisplayedOrNot();
		String expectedMessageForRegister = dataProp.getProperty("successfullyRegisteredVerifyMessage");
		softassert.assertTrue(actualMessageForRegister.contains(expectedMessageForRegister),
				"Validation for registering account does not match");
		softassert.assertAll();

	}

	@Test(priority = 3)
	public void verifyRegisterWithOnlyFirstName() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.clickOnSubscribeButton();
		registerpage.clickOnPrivacyPolicyButton();
		registerpage.clickOnContinueButton();
		String actualMessageForLastName = registerpage.warningMessageForLastNameDisplayedOrNot();
		String expectedMessageForLastName = dataProp.getProperty("lastNameWarningMessage");
		softassert.assertTrue(actualMessageForLastName.contains(expectedMessageForLastName),
				"Error message for missing last name does not exits");
		softassert.assertAll();
	}

	@Test(priority = 4)
	public void verifyRegisterWithOnlyFirstAndLastName() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.clickOnSubscribeButton();
		registerpage.clickOnPrivacyPolicyButton();
		registerpage.clickOnContinueButton();
		String actualMessageForEmail = registerpage.warningMessageForEmailDisplayedOrNot();
		String expectedMessageForEmail = dataProp.getProperty("emailWarningMessage");
		softassert.assertTrue(actualMessageForEmail.contains(expectedMessageForEmail),
				"Error message for missing email does not exits");
		softassert.assertAll();
	}

	@Test(priority = 5)
	public void verifyRegisterUptoEmail() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.clickOnSubscribeButton();
		registerpage.clickOnPrivacyPolicyButton();
		registerpage.clickOnContinueButton();
		String actualMessageForTelephone = registerpage.warningMessageForTelephoneDisplayedOrNot();
		String expectedMessageForTelephone = dataProp.getProperty("telephoneWarningMessage");
		softassert.assertTrue(actualMessageForTelephone.contains(expectedMessageForTelephone),
				"Error message for missing telephone does not exits");
		softassert.assertAll();
	}

	@Test(priority = 6)
	public void verifyRegisterUptoTelePhoneNumber() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephone(Utilities.generatePhoneNumber(11));
		registerpage.clickOnSubscribeButton();
		registerpage.clickOnPrivacyPolicyButton();
		registerpage.clickOnContinueButton();
		String actualMessageForPassword = registerpage.warningMessageForPasswordDisplayedOrNot();
		String expectedMessageForPassword = dataProp.getProperty("passwordWarningMessage");
		softassert.assertTrue(actualMessageForPassword.contains(expectedMessageForPassword),
				"Error message for missing password does not exits");
		softassert.assertAll();
	}

	@Test(priority = 7)
	public void verifyRegisterUptoPassword() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephone(Utilities.generatePhoneNumber(11));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.clickOnSubscribeButton();
		registerpage.clickOnPrivacyPolicyButton();
		registerpage.clickOnContinueButton();
		String actualMessageForConfirmPassword = registerpage.warningMessageForConfirmPasswordDisplayedOrNot();
		String expectedMessageConfirmForPassword = dataProp.getProperty("confirmPasswordWarningMessage");
		softassert.assertTrue(actualMessageForConfirmPassword.contains(expectedMessageConfirmForPassword),
				"Error message for missing confirmation password does not exits");
		softassert.assertAll();
	}

	@Test(priority = 8)
	public void verifyRegisterWithoutCheckingPrivacyPolicy() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephone(Utilities.generatePhoneNumber(11));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clickOnSubscribeButton();
		registerpage.clickOnContinueButton();
		String actualMessageForUncheckedPrivacyButton = registerpage.warningMessageForPrivacyPolicyDisplayedOrNot();
		String expectedMessageForUncheckedPrivacyButton = dataProp
				.getProperty("warningMessageForUncheckedPrivacyPolicyButton");
		softassert.assertTrue(actualMessageForUncheckedPrivacyButton.contains(expectedMessageForUncheckedPrivacyButton),
				"Error message for unchecked privacy policy button does not exits");
		softassert.assertAll();

	}

	@Test(priority = 9)
	public void verifyRegisterByEnteringDifferentPasswordForThePasswordFields() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephone(Utilities.generatePhoneNumber(11));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(dataProp.getProperty("invalidPassword"));
		registerpage.clickOnSubscribeButton();
		registerpage.clickOnPrivacyPolicyButton();
		registerpage.clickOnContinueButton();
		String actualMessageForConfirmPassword = registerpage.warningMessageForConfirmPasswordDisplayedOrNot();
		String expectedMessageConfirmForPassword = dataProp.getProperty("confirmPasswordWarningMessage");
		softassert.assertTrue(actualMessageForConfirmPassword.contains(expectedMessageConfirmForPassword),
				"Error message for missing confirmation password does not exits");
		softassert.assertAll();
	}

	@Test(priority = 10)
	public void verifyRegisterSubscribedYes() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephone(Utilities.generatePhoneNumber(11));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clickOnSubscribeButton();
		registerpage.clickOnPrivacyPolicyButton();
		registerpage.clickOnContinueButton();
		String actualMessageForRegister = registerpage.successfullyRegisteredMessageIsDisplayedOrNot();
		String expectedMessageForRegister = dataProp.getProperty("successfullyRegisteredVerifyMessage");
		softassert.assertTrue(actualMessageForRegister.contains(expectedMessageForRegister),
				"Validation for registering account does not match");
		softassert.assertAll();
	}

	@Test(priority = 11)
	public void verifyRegisterSubscribedNo() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephone(Utilities.generatePhoneNumber(11));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clickOnPrivacyPolicyButton();
		registerpage.clickOnContinueButton();
		String actualMessageForRegister = registerpage.successfullyRegisteredMessageIsDisplayedOrNot();
		String expectedMessageForRegister = dataProp.getProperty("successfullyRegisteredVerifyMessage");
		softassert.assertTrue(actualMessageForRegister.contains(expectedMessageForRegister),
				"Validation for registering account does not match");
		softassert.assertAll();
	}

	@Test(priority = 12)
	public void verifyRegisterUsingExistingEmail() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(dataProp.getProperty("existingEmail"));
		registerpage.enterTelephone(Utilities.generatePhoneNumber(11));
		registerpage.enterPassword(dataProp.getProperty("existingPassword"));
		registerpage.enterConfirmPassword(dataProp.getProperty("existingPassword"));
		registerpage.clickOnPrivacyPolicyButton();
		registerpage.clickOnContinueButton();
		String actualMessageForExistingEmail = registerpage.warningMessageForExistingEmailDisplayedOrNot();
		String expectedMessageForExistingEmial = dataProp.getProperty("warningMessageForExistingEmail");
		softassert.assertTrue(actualMessageForExistingEmail.contains(expectedMessageForExistingEmial),
				"Error message for existing email does not match");
		softassert.assertAll();
	}

	@Test(priority = 13)
	public void verifyRegisterUsingMoreThan32NumbersForTelePhoneNumber() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephone(dataProp.getProperty("moreThan32CharsForPhoneNumber"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clickOnPrivacyPolicyButton();
		registerpage.clickOnContinueButton();
		String actualMessageForTelephone = registerpage.warningMessageForTelephoneDisplayedOrNot();
		String expectedMessageForTelephone = dataProp.getProperty("telephoneWarningMessage");
		softassert.assertTrue(actualMessageForTelephone.contains(expectedMessageForTelephone),
				"Error message for missing telephone does not exits");
		softassert.assertAll();
	}

	@Test(priority = 14)
	public void verifyRegisterAnAccountWithoutFillingAnything() {
		registerpage = new RegisterPage(driver);
		registerpage.clickOnContinueButton();
		String actualMessageForUncheckedPrivacyButton = registerpage.warningMessageForPrivacyPolicyDisplayedOrNot();
		String expectedMessageForUncheckedPrivacyButton = dataProp
				.getProperty("warningMessageForUncheckedPrivacyPolicyButton");
		softassert.assertTrue(actualMessageForUncheckedPrivacyButton.contains(expectedMessageForUncheckedPrivacyButton),
				"Error message for unchecked privacy policy button does not exits");
		String actualMessageForFirstName = registerpage.warningMessageForFirstNameDisplayedOrNot();
		String expectedMessageForFirstName = dataProp.getProperty("firstNameWarningMessage");
		softassert.assertTrue(actualMessageForFirstName.contains(expectedMessageForFirstName),
				"Error message for missing first name does not exits");
		String actualMessageForLastName = registerpage.warningMessageForLastNameDisplayedOrNot();
		String expectedMessageForLastName = dataProp.getProperty("lastNameWarningMessage");
		softassert.assertTrue(actualMessageForLastName.contains(expectedMessageForLastName),
				"Error message for missing last name does not exits");
		String actualMessageForEmail = registerpage.warningMessageForEmailDisplayedOrNot();
		String expectedMessageForEmail = dataProp.getProperty("emailWarningMessage");
		softassert.assertTrue(actualMessageForEmail.contains(expectedMessageForEmail),
				"Error message for missing email does not exits");
		String actualMessageForTelephone = registerpage.warningMessageForTelephoneDisplayedOrNot();
		String expectedMessageForTelephone = dataProp.getProperty("telephoneWarningMessage");
		softassert.assertTrue(actualMessageForTelephone.contains(expectedMessageForTelephone),
				"Error message for missing telephone does not exits");
		String actualMessageForPassword = registerpage.warningMessageForPasswordDisplayedOrNot();
		String expectedMessageForPassword = dataProp.getProperty("passwordWarningMessage");
		softassert.assertTrue(actualMessageForPassword.contains(expectedMessageForPassword),
				"Error message for missing password does not exits");
		softassert.assertAll();

	}

	@Test(priority = 15)
	public void verifyRegisterUsingMoreThan32CharactersForFirstName() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("moreThan32CharsForFirstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephone(Utilities.generatePhoneNumber(11));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clickOnPrivacyPolicyButton();
		registerpage.clickOnContinueButton();
		String actualMessageForFirstName = registerpage.warningMessageForFirstNameDisplayedOrNot();
		String expectedMessageForFirstName = dataProp.getProperty("firstNameWarningMessage");
		softassert.assertTrue(actualMessageForFirstName.contains(expectedMessageForFirstName),
				"Error message for missing first name does not exits");
		softassert.assertAll();

	}

	@Test(priority = 16)
	public void verifyRegisterUsingMoreThan32CharactersForLastName() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("moreThan32CharsForLastName"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephone(Utilities.generatePhoneNumber(11));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clickOnPrivacyPolicyButton();
		registerpage.clickOnContinueButton();
		String actualMessageForLastName = registerpage.warningMessageForLastNameDisplayedOrNot();
		String expectedMessageForLastName = dataProp.getProperty("lastNameWarningMessage");
		softassert.assertTrue(actualMessageForLastName.contains(expectedMessageForLastName),
				"Error message for missing last name does not exits");
		softassert.assertAll();
	}

	@Test(priority = 17)
	public void verifyRegisterUsingMoreThan20CharactersForPassword() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephone(Utilities.generatePhoneNumber(11));
		registerpage.enterPassword(dataProp.getProperty("moreThan20CharsForPassword"));
		registerpage.enterConfirmPassword(dataProp.getProperty("moreThan20CharsForPassword"));
		registerpage.clickOnPrivacyPolicyButton();
		registerpage.clickOnContinueButton();
		String actualMessageForPassword = registerpage.warningMessageForPasswordDisplayedOrNot();
		String expectedMessageForPassword = dataProp.getProperty("passwordWarningMessage");
		softassert.assertTrue(actualMessageForPassword.contains(expectedMessageForPassword),
				"Error message for missing password does not exits");
		softassert.assertAll();

	}

	@Test(priority = 18)
	public void verifyRegisterUsingLessThan4CharactersForPassword() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephone(Utilities.generatePhoneNumber(11));
		registerpage.enterPassword(dataProp.getProperty("lessThan4CharsForPassword"));
		registerpage.enterConfirmPassword(dataProp.getProperty("lessThan4CharsForPassword"));
		registerpage.clickOnPrivacyPolicyButton();
		registerpage.clickOnContinueButton();
		String actualMessageForPassword = registerpage.warningMessageForPasswordDisplayedOrNot();
		String expectedMessageForPassword = dataProp.getProperty("passwordWarningMessage");
		softassert.assertTrue(actualMessageForPassword.contains(expectedMessageForPassword),
				"Error message for missing password does not exits");
		softassert.assertAll();
	}

	@Test(priority = 19)
	public void verifyRegisterUsingLessThan3NumbersForTelePhoneNumber() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephone(dataProp.getProperty("lessThan3CharsForPhoneNumber"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clickOnPrivacyPolicyButton();
		registerpage.clickOnContinueButton();
		String actualMessageForTelephone = registerpage.warningMessageForTelephoneDisplayedOrNot();
		String expectedMessageForTelephone = dataProp.getProperty("telephoneWarningMessage");
		softassert.assertTrue(actualMessageForTelephone.contains(expectedMessageForTelephone),
				"Error message for missing telephone does not exits");
		softassert.assertAll();
	}

	@Test(priority = 20)
	public void verifyOpeningOfRegisterPageInANewTab() {

		action = new Actions(driver);
		action.keyDown(Keys.CONTROL).moveToElement(landingpage.clickOnRegisterLink()).click().perform();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));

	}

	@Test(priority = 21)
	public void verifyOpeningOfRegisterPageInANewWindow() {

		action = new Actions(driver);
		action.keyDown(Keys.SHIFT).click(landingpage.clickOnRegisterLink()).keyUp(Keys.SHIFT).click().perform();

	}

	@Test(priority = 22)
	public void verifyLoginAfterRegistration() {

		registerpage = new RegisterPage(driver);
		registrationsuccesspage = new RegistrationSuccessPage(driver);
		insideofloginpage = new InsideOfLoginPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephone(Utilities.generatePhoneNumber(11));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clickOnSubscribeButton();
		registerpage.clickOnPrivacyPolicyButton();
		registerpage.clickOnContinueButton();
		String actualMessageForRegister = registerpage.successfullyRegisteredMessageIsDisplayedOrNot();
		String expectedMessageForRegister = dataProp.getProperty("successfullyRegisteredVerifyMessage");
		softassert.assertTrue(actualMessageForRegister.contains(expectedMessageForRegister),
				"Validation for registering account does not match");
		registrationsuccesspage.clickOnContinueButton();
		String actualMessageForLogin = insideofloginpage.editYourAccountInfoDisplayedOrNot();
		String expectedMessageForLogin = dataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin),
				"Edit your account information is not displayed");
		softassert.assertAll();
	}

	@Test(priority = 23)
	public void verifyProperLogoutAfterRegistration() {

		registerpage = new RegisterPage(driver);
		registrationsuccesspage = new RegistrationSuccessPage(driver);
		insideofloginpage = new InsideOfLoginPage(driver);
		logoutpage = new LogoutPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephone(Utilities.generatePhoneNumber(11));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clickOnSubscribeButton();
		registerpage.clickOnPrivacyPolicyButton();
		registerpage.clickOnContinueButton();
		String actualMessageForRegister = registerpage.successfullyRegisteredMessageIsDisplayedOrNot();
		String expectedMessageForRegister = dataProp.getProperty("successfullyRegisteredVerifyMessage");
		softassert.assertTrue(actualMessageForRegister.contains(expectedMessageForRegister),
				"Validation for registering account does not match");
		registrationsuccesspage.clickOnContinueButton();
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

	@Test(priority = 24)
	public void verifyENTERKeyOfKeyboardWorksOnRegistrationPage() {

		registerpage = new RegisterPage(driver);
		registrationsuccesspage = new RegistrationSuccessPage(driver);
		insideofloginpage = new InsideOfLoginPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephone(Utilities.generatePhoneNumber(11));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clickOnSubscribeButton();
		registerpage.clickOnPrivacyPolicyButton().sendKeys(Keys.ENTER);
		String actualMessageForRegister = registerpage.successfullyRegisteredMessageIsDisplayedOrNot();
		String expectedMessageForRegister = dataProp.getProperty("successfullyRegisteredVerifyMessage");
		softassert.assertTrue(actualMessageForRegister.contains(expectedMessageForRegister),
				"Validation for registering account does not match");
		softassert.assertAll();
	}

	@Test(priority = 25)
	public void verifyBACKSPACEofKeyboardWorksInRegisterPage() {

		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName")).sendKeys(Keys.BACK_SPACE);

	}

	@Test(priority = 26)
	public void verifyCopyAndPasteWorksOnRegisterPage() {

		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName")).sendKeys(Keys.CONTROL + "a" + "c");
		registerpage.enterLastName(dataProp.getProperty("lastName")).sendKeys(Keys.CONTROL + "v");

	}

	@Test(priority = 27)
	public void verifyNavigateTOcommandInRegisterPage() {

		driver.navigate().to(dataProp.getProperty("googleURL"));

	}

	@Test(priority = 28)
	public void verifyNavigateBACKcommandInRegisterPage() {

		registerpage = new RegisterPage(driver);
		driver.navigate().to(dataProp.getProperty("googleURL"));
		driver.navigate().back();
		registerpage.enterFirstName(dataProp.getProperty("firstName"));

	}

	@Test(priority = 29)
	public void verifyNavigationFORWARDcommandInRegisterPage() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		driver.navigate().back();
		driver.navigate().forward();
	}

	@Test(priority = 30)
	public void verifyNavigationREFRESHcommandInRegisterPage() {
		registerpage = new RegisterPage(driver);
		registrationsuccesspage = new RegistrationSuccessPage(driver);
		insideofloginpage = new InsideOfLoginPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephone(Utilities.generatePhoneNumber(11));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clickOnPrivacyPolicyButton();
		driver.navigate().refresh();
	}

	@Test(priority = 31)
	public void verifyWorkingOfTABkeyOfKeyBoardInRegistrationPage() {
		registerpage = new RegisterPage(driver);
		registrationsuccesspage = new RegistrationSuccessPage(driver);
		insideofloginpage = new InsideOfLoginPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName")).sendKeys(Keys.TAB,
				dataProp.getProperty("lastName"));
		registerpage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephone(Utilities.generatePhoneNumber(11));
		registerpage.enterPassword(prop.getProperty("validPassword")).sendKeys(Keys.TAB,
				prop.getProperty("validPassword"));
		registerpage.clickOnPrivacyPolicyButton();
		registerpage.clickOnContinueButton();
		String actualMessageForRegister = registerpage.successfullyRegisteredMessageIsDisplayedOrNot();
		String expectedMessageForRegister = dataProp.getProperty("successfullyRegisteredVerifyMessage");
		softassert.assertTrue(actualMessageForRegister.contains(expectedMessageForRegister),
				"Validation for registering account does not match");
		softassert.assertAll();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
