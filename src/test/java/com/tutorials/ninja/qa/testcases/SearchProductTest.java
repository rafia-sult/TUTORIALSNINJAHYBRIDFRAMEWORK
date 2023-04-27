package com.tutorials.ninja.qa.testcases;

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
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		landingpage = new LandingPage(driver);
		landingpage.clickOnSearchButton();
	}

	@Test(priority = 1)
	public void verifySearchBoxIsClickable() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnSearchBox();

	}

	@Test(priority = 2)
	public void verifySearchWithExistingProductName() {
		searchpage = new SearchPage(driver);
		searchpage.typeInSearchBox(dataProp.getProperty("existingProduct"));
		searchpage.clickOnSubmitButton();
		String actualExistingProdName = searchpage.existingProductDisplayedOrNot();
		String expectedExistingProdName = dataProp.getProperty("existingProduct");
		softassert.assertTrue(actualExistingProdName.contains(expectedExistingProdName),
				"Search criteria does not match");
		softassert.assertAll();
	}

	@Test(priority = 3)
	public void verifySearchWithANonExistingProductName() {
		searchpage = new SearchPage(driver);
		searchpage.typeInSearchBox(dataProp.getProperty("nonExistingProduct"));
		searchpage.clickOnSubmitButton();
		String actualNonExistingProdName = searchpage.nonExistingProductDisplayedOrNot();
		String expectedNonExistingProdName = dataProp.getProperty("noMatchForSearchedProductMessage");
		softassert.assertTrue(actualNonExistingProdName.contains(expectedNonExistingProdName),
				"Search criteria does not match");
		softassert.assertAll();
	}

	@Test(priority = 4)
	public void verifySearchWithoutProvidingAnything() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnSubmitButton();
		String actualNonExistingProdName = searchpage.nonExistingProductDisplayedOrNot();
		String expectedNonExistingProdName = dataProp.getProperty("noMatchForSearchedProductMessage");
		softassert.assertTrue(actualNonExistingProdName.contains(expectedNonExistingProdName),
				"Search criteria does not match");
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
		loginpage.enterEmailId(prop.getProperty("validUserName"));
		loginpage.enterPassword(prop.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		String actualMessageForLogin = insideofloginpage.editYourAccountInfoDisplayedOrNot();
		String expectedMessageForLogin = dataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin),
				"Edit your account information is not displayed");
		searchpage.clickOnSearchBox();
		searchpage.typeInSearchBox(dataProp.getProperty("existingProduct"));
		searchpage.clickOnSubmitButton();
		String actualExistingProdName = searchpage.existingProductDisplayedOrNot();
		String expectedExistingProdName = dataProp.getProperty("existingProduct");
		softassert.assertTrue(actualExistingProdName.contains(expectedExistingProdName),
				"Search criteria does not match");
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
		loginpage.enterEmailId(prop.getProperty("validUserName"));
		loginpage.enterPassword(prop.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		String actualMessageForLogin = insideofloginpage.editYourAccountInfoDisplayedOrNot();
		String expectedMessageForLogin = dataProp.getProperty("validateLogin");
		softassert.assertTrue(actualMessageForLogin.contains(expectedMessageForLogin),
				"Edit your account information is not displayed");
		searchpage.clickOnSearchBox();
		searchpage.typeInSearchBox(dataProp.getProperty("existingProduct"));
		searchpage.clickOnSubmitButton();
		String actualExistingProdName = searchpage.existingProductDisplayedOrNot();
		String expectedExistingProdName = dataProp.getProperty("existingProduct");
		softassert.assertTrue(actualExistingProdName.contains(expectedExistingProdName),
				"Search criteria does not match");
		landingpage.clickOnMyAccountLink();
		insideofloginpage.clickOnLogoutButton();
		String actualMessageForLogout = logoutpage.logoutValidationMessageDisplayedOrNot();
		String expectedMessageForLogout = dataProp.getProperty("validateLogout");
		softassert.assertTrue(actualMessageForLogout.contains(expectedMessageForLogout),
				"Logout confirmation text does not match");
		softassert.assertAll();
	}

	@Test(priority = 7)
	public void verifyCorrectResultsAreDisplayedAfterASearch() {
		searchpage = new SearchPage(driver);
		searchpage.typeInSearchBox(dataProp.getProperty("existingProduct"));
		searchpage.clickOnSubmitButton();
		String actualExistingProdName = searchpage.existingProductDisplayedOrNot();
		String expectedExistingProdName = dataProp.getProperty("existingProduct");
		softassert.assertTrue(actualExistingProdName.contains(expectedExistingProdName),
				"Search criteria does not match");
		softassert.assertAll();
	}

	@Test(priority = 8)
	public void verifySearchWithSpecialCharacter() {
		searchpage = new SearchPage(driver);
		searchpage.typeInSearchBox(dataProp.getProperty("searchWithSpecialCharacter"));
		searchpage.clickOnSubmitButton();
		String actualNonExistingProdName = searchpage.nonExistingProductDisplayedOrNot();
		String expectedNonExistingProdName = dataProp.getProperty("noMatchForSearchedProductMessage");
		softassert.assertTrue(actualNonExistingProdName.contains(expectedNonExistingProdName),
				"Search criteria does not match");
		softassert.assertAll();
	}

	@Test(priority = 9)
	public void verifySearchWhenTheSearchTermIsMisspelled() {
		searchpage = new SearchPage(driver);
		searchpage.typeInSearchBox(dataProp.getProperty("misspelledSearch"));
		searchpage.clickOnSubmitButton();
		String actualmisspelledProdName = searchpage.appleCinemaProductNameDisplayedOrNot();
		String expectedmisspelledProdName = dataProp.getProperty("misspelledResult");
		softassert.assertTrue(actualmisspelledProdName.contains(expectedmisspelledProdName),
				"Search criteria does not match");
		softassert.assertAll();

	}

	@Test(priority = 10)
	public void verifySearchByProvidingASearchCriteriaThatResultsInMultipleProducts() {
		searchpage = new SearchPage(driver);
		searchpage.typeInSearchBox(dataProp.getProperty("multipleProductSearch"));
		searchpage.clickOnSubmitButton();
		String actualExistingProdName = searchpage.existingProductDisplayedOrNot();
		String expectedExistingProdName = dataProp.getProperty("existingProduct");
		softassert.assertTrue(actualExistingProdName.contains(expectedExistingProdName),
				"Search criteria does not match");
		String actualMultipleProdName = searchpage.multipleProductDisplayedOrNot();
		String expectedMultipleProdName = dataProp.getProperty("multipleProductVarification");
		softassert.assertTrue(actualMultipleProdName.contains(expectedMultipleProdName),
				"Search criteria does not match");
		softassert.assertAll();
	}

	@Test(priority = 11)
	public void verifySearchWithExtraSpacesBetweenLetters() {
		searchpage = new SearchPage(driver);
		searchpage.typeInSearchBox(dataProp.getProperty("searchWithExtraSpaces"));
		searchpage.clickOnSubmitButton();
		String actualExistingProdName = searchpage.existingProductDisplayedOrNot();
		String expectedExistingProdName = dataProp.getProperty("existingProduct");
		softassert.assertTrue(actualExistingProdName.contains(expectedExistingProdName),
				"Search criteria does not match");
		String actualMultipleProdName = searchpage.multipleProductDisplayedOrNot();
		String expectedMultipleProdName = dataProp.getProperty("multipleProductVarification");
		softassert.assertTrue(actualMultipleProdName.contains(expectedMultipleProdName),
				"Search criteria does not match");
		String actualmisspelledProdName = searchpage.appleCinemaProductNameDisplayedOrNot();
		String expectedmisspelledProdName = dataProp.getProperty("misspelledResult");
		softassert.assertTrue(actualmisspelledProdName.contains(expectedmisspelledProdName),
				"Search criteria does not match");
		softassert.assertAll();
	}

	@Test(priority = 12)
	public void verifySearchWithAllUppercase() {
		searchpage = new SearchPage(driver);
		searchpage.typeInSearchBox(dataProp.getProperty("searchWithAllUpperCase"));
		searchpage.clickOnSubmitButton();
		String actualmisspelledProdName = searchpage.appleCinemaProductNameDisplayedOrNot();
		String expectedmisspelledProdName = dataProp.getProperty("upperCaseSearchResult");
		softassert.assertTrue(actualmisspelledProdName.contains(expectedmisspelledProdName),
				"Search criteria does not match");
		softassert.assertAll();
	}

	@Test(priority = 13)
	public void verifySearchWithAllLowercase() {
		searchpage = new SearchPage(driver);
		searchpage.typeInSearchBox(dataProp.getProperty("searchWithAllLowerCase"));
		searchpage.clickOnSubmitButton();
		String actualmisspelledProdName = searchpage.appleCinemaProductNameDisplayedOrNot();
		String expectedmisspelledProdName = dataProp.getProperty("lowerCaseSearchResult");
		softassert.assertTrue(actualmisspelledProdName.contains(expectedmisspelledProdName),
				"Search criteria does not match");
		softassert.assertAll();
	}

	@Test(priority = 14)
	public void verifySearchWithMixedUppercaseAndlowercase() {
		searchpage = new SearchPage(driver);
		searchpage.typeInSearchBox(dataProp.getProperty("searchWithMixedUpperCaseAndLowerCase"));
		searchpage.clickOnSubmitButton();
		String actualmisspelledProdName = searchpage.appleCinemaProductNameDisplayedOrNot();
		String expectedmisspelledProdName = dataProp.getProperty("mixedCaseSearchResult");
		softassert.assertTrue(actualmisspelledProdName.contains(expectedmisspelledProdName),
				"Search criteria does not match");
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
		searchpage.clickOnSearchBox();
		searchpage.typeInSearchBox(dataProp.getProperty("existingProduct"));
		searchpage.clickOnSubmitButton();
		String actualExistingProdName = searchpage.existingProductDisplayedOrNot();
		String expectedExistingProdName = dataProp.getProperty("existingProduct");
		softassert.assertTrue(actualExistingProdName.contains(expectedExistingProdName),
				"Search criteria does not match");
		softassert.assertAll();

	}

	@Test(priority = 16)
	public void verifySearchingForProductUsingTheENTERkey() {

		searchpage = new SearchPage(driver);
		searchpage.typeInSearchBox(dataProp.getProperty("existingProduct")).sendKeys(Keys.ENTER);
		String actualExistingProdName = searchpage.existingProductDisplayedOrNot();
		String expectedExistingProdName = dataProp.getProperty("existingProduct");
		softassert.assertTrue(actualExistingProdName.contains(expectedExistingProdName),
				"Search criteria does not match");
		softassert.assertAll();

	}

	@Test(priority = 17)
	public void verifyWorkingOfTABkeyWhenLookingForProducts() throws Exception {

		searchpage = new SearchPage(driver);
		searchpage.clickOnTabletsButton().sendKeys(Keys.TAB);
		String actualSearchResultForTabletsLink = searchpage.tabletsLinkSearchResultDisplayedOrNot();
		String expectedSearchResultForTabletsLink = dataProp.getProperty("tabletsLinkSearchResultVerify");
		softassert.assertTrue(actualSearchResultForTabletsLink.contains(expectedSearchResultForTabletsLink),
				"Search criteria does not match");
		Thread.sleep(2000);
		searchpage.clickOnSoftwareButton().sendKeys(Keys.TAB);
		String actualSearchResultForSoftwareLink = searchpage.softwareLinkSearchResultDisplayedOrNot();
		String expectedSearchResultForSoftwareLink = dataProp.getProperty("softwareLinkSearchResultVerify");
		softassert.assertTrue(actualSearchResultForSoftwareLink.contains(expectedSearchResultForSoftwareLink),
				"Search criteria does not match");
		Thread.sleep(2000);

		searchpage.clickOnPhonesAndPDAsButton().sendKeys(Keys.TAB);
		String actualSearchResultForPhonesAndPDAsLink = searchpage.phonesAndPDAsLinkSearchResultDisplayedOrNot();
		String expectedSearchResultForPhonesAndPDAsLink = dataProp.getProperty("phonesAndPDAsLinkSearchResultVerify");
		softassert.assertTrue(actualSearchResultForPhonesAndPDAsLink.contains(expectedSearchResultForPhonesAndPDAsLink),
				"Search criteria does not match");
		softassert.assertAll();

	}

	@Test(priority = 18)
	public void verifyClickingOnDesktopsLink() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnDesktopsButton();
		String actualDesktopVerification = searchpage.sublinkShowAllPageDesktopsDisplayedOrNOt();
		String expectedDesktopVerification = dataProp.getProperty("clickingOnDesktopsLinkVerification");
		softassert.assertTrue(actualDesktopVerification.contains(expectedDesktopVerification),
				"Desktops link is not clickable");
		softassert.assertAll();

	}

	@Test(priority = 19)
	public void verifyClickingOnLaptopsAndNotebooksLink() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnLaptopsAndNotebooksButton();
		String actualLaptopsAndNotebookVerification = searchpage.sublinkShowAllPageLaptopsDisplayedOrNot();
		String expectedLaptopsAndNotebookVerification = dataProp.getProperty("clickingOnLaptopsLinkVerification");
		softassert.assertTrue(actualLaptopsAndNotebookVerification.contains(expectedLaptopsAndNotebookVerification),
				"LaptopsAndNotebook link is not clickable");
		softassert.assertAll();

	}

	@Test(priority = 20)
	public void verifyClickingOnComponentsLink() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnComponentsButton();
		String actualComponentsVerification = searchpage.sublinkShowAllPageComponentsDisplayedOrNot();
		String expectedComponentsVerification = dataProp.getProperty("clickingOnComponentsLinkVerification");
		softassert.assertTrue(actualComponentsVerification.contains(expectedComponentsVerification),
				"Components link is not clickable");
		softassert.assertAll();

	}

	@Test(priority = 21)
	public void verifyClickingOnTabletsLink() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnTabletsButton();
		String actualSearchResultForTabletsLink = searchpage.tabletsLinkSearchResultDisplayedOrNot();
		String expectedSearchResultForTabletsLink = dataProp.getProperty("tabletsLinkSearchResultVerify");
		softassert.assertTrue(actualSearchResultForTabletsLink.contains(expectedSearchResultForTabletsLink),
				"Tablets Link is not clickable");
		softassert.assertAll();

	}

	@Test(priority = 22)
	public void verifyClickingOnSoftwareLink() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnSoftwareButton();
		String actualSearchResultForSoftwareLink = searchpage.softwareLinkSearchResultDisplayedOrNot();
		String expectedSearchResultForSoftwareLink = dataProp.getProperty("softwareLinkSearchResultVerify");
		softassert.assertTrue(actualSearchResultForSoftwareLink.contains(expectedSearchResultForSoftwareLink),
				"Software Link is not clickable");
		softassert.assertAll();

	}

	@Test(priority = 23)
	public void verifyClickingOnPhonesAndPDAsLink() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnPhonesAndPDAsButton();
		String actualSearchResultForPhonesAndPDAsLink = searchpage.phonesAndPDAsLinkSearchResultDisplayedOrNot();
		String expectedSearchResultForPhonesAndPDAsLink = dataProp.getProperty("phonesAndPDAsLinkSearchResultVerify");
		softassert.assertTrue(actualSearchResultForPhonesAndPDAsLink.contains(expectedSearchResultForPhonesAndPDAsLink),
				"Phones & PDAs Link is not clickable");
		softassert.assertAll();

	}

	@Test(priority = 24)
	public void verifyClickingOnCamerasLink() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnCamerasButton();
		String actualCamerasLinkVerification = searchpage.camerasLinkDisplayedOrNot();
		String expectedCamerasLinkVerification = dataProp.getProperty("clickingOnCamerasLinkVerification");
		softassert.assertTrue(actualCamerasLinkVerification.contains(expectedCamerasLinkVerification),
				"Cameras link is not clickable");
		softassert.assertAll();

	}

	@Test(priority = 25)
	public void verifyClickingOnMP3PlayersLink() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnMP3PlayersButton();
		String actualMP3playersVerification = searchpage.MP3PlayersSublinkShowAllDisplayedOrNot();
		String expectedMp3playersVerification = dataProp.getProperty("clickingOnMP3playersVerification");
		softassert.assertTrue(actualMP3playersVerification.contains(expectedMp3playersVerification),
				"MP3 Players link is not clickable");
		softassert.assertAll();
	}

	@Test(priority = 26)
	public void verifyClickingOnDesktopsSublinkPC() throws Exception {
		searchpage = new SearchPage(driver);
		searchpage.clickOnDesktopsButton();
		Thread.sleep(2000);
		searchpage.clickOnDesktopSublinkPC();
		String actualPCPageDisplay = searchpage.verifyWithContinueButtonDisplayedOrNot();
		String expectedPCPageDisplay = dataProp.getProperty("continueButtonVerification");
		softassert.assertTrue(actualPCPageDisplay.contains(expectedPCPageDisplay), "Continue Button is not there");
		softassert.assertAll();

	}

	@Test(priority = 27)
	public void verifyClickingOnDesktopsSublinkMac() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnDesktopsButton();
		searchpage.clickOnDesktopSublinkMac();
		String actualSublinkMacDisplay = searchpage.sublinkMacPageDisplayedOrNot();
		String expectedSublinkMacDisplay = dataProp.getProperty("sublinkMacVerification");
		softassert.assertTrue(actualSublinkMacDisplay.contains(expectedSublinkMacDisplay), "Mac page is availaable");
		softassert.assertAll();

	}

	@Test(priority = 28)
	public void verifyClickingOnDesktopsSublinkShowAllDesktops() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnDesktopsButton();
		searchpage.clickOnDesktopSublinkShowAll();
		String actualShowAllDesktVerification = searchpage.showAlldesktopsResultDisplayedOrNot();
		String expectedShowAllDeskVerification = dataProp.getProperty("showAllDesksverification");
		softassert.assertTrue(actualShowAllDesktVerification.contains(expectedShowAllDeskVerification),
				"Desktops sublink show all desktops is not clickable");
		softassert.assertAll();

	}

	@Test(priority = 29)
	public void verifyClickingOnLaptopsAndNotebooksSublinkMacs() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnLaptopsAndNotebooksButton();
		searchpage.clickOnLaptopsSublinkMacs();
		String actualLaptopsMacsDisplay = searchpage.verifyWithContinueButtonDisplayedOrNot();
		String expectedLaptopsMacsDisplay = dataProp.getProperty("continueButtonVerification");
		softassert.assertTrue(actualLaptopsMacsDisplay.contains(expectedLaptopsMacsDisplay),
				"Continue Button is not there");
		softassert.assertAll();

	}

	@Test(priority = 30)
	public void verifyClickingOnLaptopsAndNotebooksSublinkWindows() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnLaptopsAndNotebooksButton();
		searchpage.clickOnLaptopsSublinkWindows();
		String actualLaptopsWindowsDisplay = searchpage.verifyWithContinueButtonDisplayedOrNot();
		String expectedLaptopsWindowsDisplay = dataProp.getProperty("continueButtonVerification");
		softassert.assertTrue(actualLaptopsWindowsDisplay.contains(expectedLaptopsWindowsDisplay),
				"Continue Button is not there");
		softassert.assertAll();

	}

	@Test(priority = 31)
	public void verifyClickingOnLaptopsAndNotebooksSublinkShowAll() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnLaptopsAndNotebooksButton();
		searchpage.clickOnLaptopsSublinkShowAll();
		String actualLaptopsShowAllVerification = searchpage.showAllLaptopsandNotebookDisplayedOrNOt();
		String expectedLaptopsShowAllVerification = dataProp.getProperty("showAllLaptopsAndNotebookVerification");
		softassert.assertTrue(actualLaptopsShowAllVerification.contains(expectedLaptopsShowAllVerification),
				"Laptops & Notebooks is not displayed");
		softassert.assertAll();

	}

	@Test(priority = 32)
	public void verifyClickingOnComponentsSublinkMiceAndTrackballs() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnComponentsButton();
		searchpage.clickOnComponentsSublinkMiceAndTrackballs();
		String actualComponentsMiceNTrackDisplay = searchpage.verifyWithContinueButtonDisplayedOrNot();
		String expectedComponentsMiceNTrackDisplay = dataProp.getProperty("continueButtonVerification");
		softassert.assertTrue(actualComponentsMiceNTrackDisplay.contains(expectedComponentsMiceNTrackDisplay),
				"Continue Button is not displayed");
		softassert.assertAll();

	}

	@Test(priority = 33)
	public void verifyClickingOnComponentsSublinkMonitor() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnComponentsButton();
		searchpage.clickOnComponentsSublinkMonitors();
		String actualComponentsMonitorDisplay = searchpage.appleCinemaProductNameDisplayedOrNOt();
		String expectedComponentsMonitorDisplay = dataProp.getProperty("monitorsComponentVerification");
		softassert.assertTrue(actualComponentsMonitorDisplay.contains(expectedComponentsMonitorDisplay),
				"Apple Cinemra 30\" is not displayed");
		softassert.assertAll();

	}

	@Test(priority = 34)
	public void verifyClickingOnComponentsSublinkPrinters() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnComponentsButton();
		searchpage.clickOnComponentsSublinkPrinters();
		String actualComponentsPrintersDisplay = searchpage.verifyWithContinueButtonDisplayedOrNot();
		String expectedComponentsPrinterDisplay = dataProp.getProperty("continueButtonVerification");
		softassert.assertTrue(actualComponentsPrintersDisplay.contains(expectedComponentsPrinterDisplay),
				"Continue Button is not displayed");
		softassert.assertAll();

	}

	@Test(priority = 35)
	public void verifyClickingOnComponentsSublinkScanners() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnComponentsButton();
		searchpage.clickOnComponentsSublinkScanners();
		String actualPCPageDisplay = searchpage.verifyWithContinueButtonDisplayedOrNot();
		String expectedPCPageDisplay = dataProp.getProperty("continueButtonVerification");
		softassert.assertTrue(actualPCPageDisplay.contains(expectedPCPageDisplay), "Continue Button is not displayed");
		softassert.assertAll();

	}

	@Test(priority = 36)
	public void verifyClickingOnComponentsSublinkWebCameras() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnComponentsButton();
		searchpage.clickOnComponentsSublinkWebCameras();
		String actualWebCamerasDisplay = searchpage.verifyWithContinueButtonDisplayedOrNot();
		String expectedWebCamerasDisplayDisplay = dataProp.getProperty("continueButtonVerification");
		softassert.assertTrue(actualWebCamerasDisplay.contains(expectedWebCamerasDisplayDisplay),
				"Continue Button is not displayed");
		softassert.assertAll();

	}

	@Test(priority = 37)
	public void verifyClickingOnComponentsSublinkShowAllComponents() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnComponentsButton();
		searchpage.clickOnComponentsSublinkShowAll();
		String actualShowAllComponentVerification = searchpage.showAllComponentsDisplayedOrNOt();
		String expectedShowAllComponentVerification = dataProp.getProperty("showAllComponentVerification");
		softassert.assertTrue(actualShowAllComponentVerification.contains(expectedShowAllComponentVerification),
				"Show All Components is not Displayed");
		softassert.assertAll();

	}

	@Test(priority = 38)
	public void verifyClickingOnMP3PlayersSublinkShowAllMP3Players() {
		searchpage = new SearchPage(driver);
		searchpage.clickOnMP3PlayersButton();
		searchpage.clickOnMP3SublinkShowAll();
		String actualDesktopVerification = searchpage.showAllMP3PlayersDisplayedOrNOt();
		String expectedDesktopVerification = dataProp.getProperty("showAllMP3Verification");
		softassert.assertTrue(actualDesktopVerification.contains(expectedDesktopVerification),
				"MP3 Show All page is not displayed");
		softassert.assertAll();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
