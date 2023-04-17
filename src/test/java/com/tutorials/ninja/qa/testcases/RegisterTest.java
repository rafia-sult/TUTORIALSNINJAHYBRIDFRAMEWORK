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
import com.tutorials.ninja.qa.testpages.LandingPage;
import com.tutorials.ninja.qa.utils.Utilities;

public class RegisterTest extends TestBase {
	
	public RegisterTest() throws Exception {
		super();
	}

	public static ChromeOptions options;
	public static WebDriver driver;
	public static SoftAssert softassert = new SoftAssert();

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(configProp.getProperty("browserName"));
		LandingPage landingpage = new LandingPage(driver);
		landingpage.clickOnMyAccountLink();
		landingpage.clickOnRegisterLink();
//		driver.findElement(By.linkText("My Account")).click();
//		driver.findElement(By.linkText("Register")).click();


	}
	
	@Test (priority =1)
	public void verifyRegisterAnAccountWithMandatoryFields() {
		driver.findElement(By.id("input-firstname")).sendKeys("John");
		driver.findElement(By.id("input-lastname")).sendKeys("Smith");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("0000000000");
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium@123");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		String actualMessageForRegister = (driver.findElement(By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]")).getText());
		String expectedMessageForRegister = "Your Account Has Been Created!";
		softassert.assertTrue(actualMessageForRegister.contains(expectedMessageForRegister),"Validation for registering account does not match");
		softassert.assertAll();
	
	}
	
	@Test (priority =2)
	public void verifyRegisterAnAccountByProvidingAllFields() {
		driver.findElement(By.id("input-firstname")).sendKeys("John");
		driver.findElement(By.id("input-lastname")).sendKeys("Smith");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("0000000000");
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium@123");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		String actualMessageForRegister = (driver.findElement(By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]")).getText());
		String expectedMessageForRegister = "Your Account Has Been Created!";
		softassert.assertTrue(actualMessageForRegister.contains(expectedMessageForRegister),"Validation for registering account does not match");
		softassert.assertAll();
	
	}

	
	@Test (priority = 3)
	public void verifyRegisterWithOnlyFirstName() {
		driver.findElement(By.id("input-firstname")).sendKeys("John");		
		driver.findElement(By.name("newsletter")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		String actualMessageForLastName = (driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div[contains(text(),'Last Name must be between 1 and 32 characters!')]")).getText());
		String expectedMessageForLastName = "Last Name must be between 1 and 32 characters!";
		softassert.assertTrue(actualMessageForLastName.contains(expectedMessageForLastName),"Error message for missing last name does not exits");
		softassert.assertAll();
	}
	
	@Test (priority =4)
	public void verifyRegisterWithOnlyFirstAndLastName() {
		driver.findElement(By.id("input-firstname")).sendKeys("John");
		driver.findElement(By.id("input-lastname")).sendKeys("Smith");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		String actualMessageForEmail = (driver.findElement(By.xpath("//div[contains(text(),'E-Mail Address does not appear to be valid!')]")).getText());
		String expectedMessageForEmail = "E-Mail Address does not appear to be valid!";
		softassert.assertTrue(actualMessageForEmail.contains(expectedMessageForEmail),"Error message for missing email does not exits");
		softassert.assertAll();
	}
	
	
	@Test (priority =5)
	public void verifyRegisterUptoEmail() {
		driver.findElement(By.id("input-firstname")).sendKeys("John");
		driver.findElement(By.id("input-lastname")).sendKeys("Smith");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());	
		driver.findElement(By.name("newsletter")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		String actualMessageForTelephone = (driver.findElement(By.xpath("//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")).getText());
		String expectedMessageForTelephone = "Telephone must be between 3 and 32 characters!";
		softassert.assertTrue(actualMessageForTelephone.contains(expectedMessageForTelephone), "Error message for missing telephone does not exits");
		softassert.assertAll();
	}
	
	@Test (priority =6)
	public void verifyRegisterUptoTelePhoneNumber() {
		driver.findElement(By.id("input-firstname")).sendKeys("John");
		driver.findElement(By.id("input-lastname")).sendKeys("Smith");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("0000000000");
		driver.findElement(By.name("newsletter")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		String actualMessageForPassword = (driver.findElement(By.xpath("//div[contains(text(),'Password must be between 4 and 20 characters!')]")).getText());
		String expectedMessageForPassword = "Password must be between 4 and 20 characters!";
		softassert.assertTrue(actualMessageForPassword.contains(expectedMessageForPassword), "Error message for missing password does not exits");
		softassert.assertAll();
	}
	
	@Test (priority =7)
	public void verifyRegisterUptoPassword() {
		driver.findElement(By.id("input-firstname")).sendKeys("John");
		driver.findElement(By.id("input-lastname")).sendKeys("Smith");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("0000000000");
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
		driver.findElement(By.name("newsletter")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		String actualMessageForConfirmPassword = (driver.findElement(By.xpath("//div[contains(text(),'Password confirmation does not match password!')]")).getText());
		String expectedMessageConfirmForPassword = "Password confirmation does not match password!";
		softassert.assertTrue(actualMessageForConfirmPassword.contains(expectedMessageConfirmForPassword), "Error message for missing confirmation password does not exits");
		softassert.assertAll();
	}
	

	@Test (priority =8)
	public void verifyRegisterWithoutCheckingPrivacyPolicy() {
		driver.findElement(By.id("input-firstname")).sendKeys("John");
		driver.findElement(By.id("input-lastname")).sendKeys("Smith");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("0000000000");
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium@123");
		driver.findElement(By.name("newsletter")).click();
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		String actualMessageForUncheckedPrivacyButton = (driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible")).getText());
		String expectedMessageForUncheckedPrivacyButton = "Warning: You must agree to the Privacy Policy!";
		softassert.assertTrue(actualMessageForUncheckedPrivacyButton.contains(expectedMessageForUncheckedPrivacyButton), "Error message for unchecked privacy policy button does not exits");
		softassert.assertAll();

	}
	

	@Test (priority =9)
	public void verifyRegisterByEnteringDifferentPasswordForThePasswordFields() {
		driver.findElement(By.id("input-firstname")).sendKeys("John");
		driver.findElement(By.id("input-lastname")).sendKeys("Smith");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("0000000000");
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium@12");
		driver.findElement(By.name("newsletter")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		String actualMessageForConfirmPassword = (driver.findElement(By.xpath("//div[contains(text(),'Password confirmation does not match password!')]")).getText());
		String expectedMessageConfirmForPassword = "Password confirmation does not match password!";
		softassert.assertTrue(actualMessageForConfirmPassword.contains(expectedMessageConfirmForPassword), "Error message for missing confirmation password does not exits");
		softassert.assertAll();
	}


	@Test (priority =10)
	public void verifyRegisterSubscribedYes() {
		driver.findElement(By.id("input-firstname")).sendKeys("John");
		driver.findElement(By.id("input-lastname")).sendKeys("Smith");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("0000000000");
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium@123");
		driver.findElement(By.xpath("//label[@class='radio-inline']/child::input")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		String actualMessage = (driver.findElement(By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]")).getText());
		String expectedMessage = "Your Account Has Been Created!";
		softassert.assertTrue(actualMessage.contains(expectedMessage),"Validation for registering account does not match");
		softassert.assertAll();
	}
	

	@Test (priority =11)
	public void verifyRegisterSubscribedNo() {
		driver.findElement(By.id("input-firstname")).sendKeys("John");
		driver.findElement(By.id("input-lastname")).sendKeys("Smith");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("0000000000");
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium@123");
		driver.findElement(By.name("newsletter")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		String actualMessage = (driver.findElement(By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]")).getText());
		String expectedMessage = "Your Account Has Been Created!";
		softassert.assertTrue(actualMessage.contains(expectedMessage),"Validation for registering account does not match");
		softassert.assertAll();
	}
	
	
	@Test (priority =12)
	public void verifyRegisterUsingExistingEmail() {
		driver.findElement(By.id("input-firstname")).sendKeys("John");
		driver.findElement(By.id("input-lastname")).sendKeys("Smith");
		driver.findElement(By.id("input-email")).sendKeys(testDataProp.getProperty("existingEmail"));
		driver.findElement(By.id("input-telephone")).sendKeys("0000000000");
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium@123");
		driver.findElement(By.name("newsletter")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		String actualMessageForExistingEmail = (driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible")).getText());
		String expectedMessageForExistingEmial = "Warning: E-Mail Address is already registered!";
		softassert.assertTrue(actualMessageForExistingEmail.contains(expectedMessageForExistingEmial),"Error message for existing email does not match");
		softassert.assertAll();
	}
	
	

	@Test (priority =13)
	public void verifyRegisterUsingMoreThan32NumbersForTelePhoneNumber() {
		driver.findElement(By.id("input-firstname")).sendKeys("John");
		driver.findElement(By.id("input-lastname")).sendKeys("Smith");
		driver.findElement(By.id("input-email")).sendKeys("johnsmith20@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("0000000000000000000000000000000000");
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium@123");
		driver.findElement(By.name("newsletter")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		String actualMessageForTelephone = (driver.findElement(By.xpath("//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")).getText());
		String expectedMessageForTelephone = "Telephone must be between 3 and 32 characters!";
		softassert.assertTrue(actualMessageForTelephone.contains(expectedMessageForTelephone), "Error message for missing telephone does not exits");
		softassert.assertAll();
	}
	

	@Test (priority =14)
	public void verifyRegisterAnAccountWithoutFilling() {
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();	

		
		String actualMessageForPrivacyPolicy = (driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible")).getText());
		String expectedMessageForPrivacyPolicy = "Warning: You must agree to the Privacy Policy!";
		softassert.assertTrue(actualMessageForPrivacyPolicy.contains(expectedMessageForPrivacyPolicy), "Error message for privacy policy does not exits");
		softassert.assertAll();
		
		String actualMessageForFirstName = (driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div[@class='text-danger']")).getText());
		String expectedMessageForFirstName = "First Name must be between 1 and 32 characters!";
		softassert.assertTrue(actualMessageForFirstName.contains(expectedMessageForFirstName), "Error message for missing first name does not match");
		
		String actualMessageForLastName = (driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div[contains(text(),'Last Name must be between 1 and 32 characters!')]")).getText());
		String expectedMessageForLastName = "Last Name must be between 1 and 32 characters!";
		softassert.assertTrue(actualMessageForLastName.contains(expectedMessageForLastName),"Error message for missing last name does not match");
		
		String actualMessageForEmail = (driver.findElement(By.xpath("//div[contains(text(),'E-Mail Address does not appear to be valid!')]")).getText());
		String expectedMessageForEmail = "E-Mail Address does not appear to be valid!";
		softassert.assertTrue(actualMessageForEmail.contains(expectedMessageForEmail),"Error message for missing email does not match");
		
		String actualMessageForTelephone = (driver.findElement(By.xpath("//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")).getText());
		String expectedMessageForTelephone = "Telephone must be between 3 and 32 characters!";
		softassert.assertTrue(actualMessageForTelephone.contains(expectedMessageForTelephone), "Error message for missing telephone does not exits");
		
		String actualMessageForPassword = (driver.findElement(By.xpath("//div[contains(text(),'Password must be between 4 and 20 characters!')]")).getText());
		String expectedMessageForPassword = "Password must be between 4 and 20 characters!";
		softassert.assertTrue(actualMessageForPassword.contains(expectedMessageForPassword), "Error message for missing password does not exits");
		softassert.assertAll();
		
		
}
	
	@Test(priority = 15)
	public void verifyRegisterUsingMoreThan32CharactersForFirstName() {
		driver.findElement(By.id("input-firstname")).sendKeys("Johnqwetyweyioptwotyuewtiwoiweoiutp");
		driver.findElement(By.id("input-lastname")).sendKeys("Smith");
		driver.findElement(By.id("input-email")).sendKeys("johnsmith20@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("0000000000");
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium@123");
		driver.findElement(By.name("newsletter")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();	
		String actualMessageForFirstName = (driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div[@class='text-danger']")).getText());
		String expectedMessageForFirstName = "First Name must be between 1 and 32 characters!";
		softassert.assertTrue(actualMessageForFirstName.contains(expectedMessageForFirstName), "Error message for missing first name does not match");
		softassert.assertAll();
	}
	
	
	@Test(priority = 16)
	public void verifyRegisterUsingMoreThan32CharactersForLastName() {
		driver.findElement(By.id("input-firstname")).sendKeys("John");
		driver.findElement(By.id("input-lastname")).sendKeys("Smithrtaieerrtytuefhkadsjfiuoehfas");
		driver.findElement(By.id("input-email")).sendKeys("johnsmith20@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("0000000000");
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium@123");
		driver.findElement(By.name("newsletter")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();	
		String actualMessageForLastName = (driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div[contains(text(),'Last Name must be between 1 and 32 characters!')]")).getText());
		String expectedMessageForLastName = "Last Name must be between 1 and 32 characters!";
		softassert.assertTrue(actualMessageForLastName.contains(expectedMessageForLastName),"Error message for missing last name does not match");
		softassert.assertAll();
	}
	
	@Test(priority = 17)
	public void verifyRegisterUsingMoreThan20CharactersForPassword() {
		driver.findElement(By.id("input-firstname")).sendKeys("John");
		driver.findElement(By.id("input-lastname")).sendKeys("Smith");
		driver.findElement(By.id("input-email")).sendKeys("johnsmith20@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("0000000000");
		driver.findElement(By.id("input-password")).sendKeys("Seleniumwe1332455asdfghjkrtyughj");
		driver.findElement(By.id("input-confirm")).sendKeys("Seleniumwe1332455asdfghjkrtyughj");
		driver.findElement(By.name("newsletter")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();	
		String actualMessageForPassword = (driver.findElement(By.xpath("//div[contains(text(),'Password must be between 4 and 20 characters!')]")).getText());
		String expectedMessageForPassword = "Password must be between 4 and 20 characters!";
		softassert.assertTrue(actualMessageForPassword.contains(expectedMessageForPassword), "Error message for missing password does not exits");
		softassert.assertAll();
	}
	
	@Test(priority = 18)
	public void verifyRegisterUsingLessThan4CharactersForPassword() {
		driver.findElement(By.id("input-firstname")).sendKeys("John");
		driver.findElement(By.id("input-lastname")).sendKeys("Smithrtaiuefhkadsjfiuoehfas");
		driver.findElement(By.id("input-email")).sendKeys("johnsmith20@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("0000000000");
		driver.findElement(By.id("input-password")).sendKeys("123");
		driver.findElement(By.id("input-confirm")).sendKeys("123");
		driver.findElement(By.name("newsletter")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();	
		String actualMessageForPassword = (driver.findElement(By.xpath("//div[contains(text(),'Password must be between 4 and 20 characters!')]")).getText());
		String expectedMessageForPassword = "Password must be between 4 and 20 characters!";
		softassert.assertTrue(actualMessageForPassword.contains(expectedMessageForPassword), "Error message for missing password does not exits");
		softassert.assertAll();
	}
	
	@Test (priority =19)
	public void verifyRegisterUsingLessThan3NumbersForTelePhoneNumber() {
		driver.findElement(By.id("input-firstname")).sendKeys("John");
		driver.findElement(By.id("input-lastname")).sendKeys("Smith");
		driver.findElement(By.id("input-email")).sendKeys("johnsmith20@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("00");
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium@123");
		driver.findElement(By.name("newsletter")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		String actualMessageForTelephone = (driver.findElement(By.xpath("//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")).getText());
		String expectedMessageForTelephone = "Telephone must be between 3 and 32 characters!";
		softassert.assertTrue(actualMessageForTelephone.contains(expectedMessageForTelephone), "Error message for missing telephone does not exits");
		softassert.assertAll();
	}
	
	@Test(priority = 20)
	public void verifyOpeningOfLoginLinkInANewTab() throws Exception {

		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).moveToElement(driver.findElement(By.linkText("Register"))).click().perform();

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));

	}

	@Test(priority = 21)
	public void verifyOpeningOfLoginLinkInANewWindow() throws Exception {
//		WebElement registerLink = driver.findElement(By.linkText("Register"));


		Actions action = new Actions(driver);
		action.keyDown(Keys.SHIFT).click(driver.findElement(By.linkText("Register"))).keyUp(Keys.SHIFT).click().perform();
	}

	

	
	
	
	
	
	@AfterMethod
	public void tearDown() {
	//	driver.quit();
	}
	
	

}
