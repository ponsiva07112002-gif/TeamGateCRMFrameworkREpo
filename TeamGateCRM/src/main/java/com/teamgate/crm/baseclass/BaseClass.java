package com.teamgate.crm.baseclass;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.teamgate.crm.generic.databaseutility.DatabaseUtility;
import com.teamgate.crm.generic.fileutility.ExcelUtility;
import com.teamgate.crm.generic.fileutility.PropertyFileUtility;
import com.teamgate.crm.generic.webdriverutility.JavaUtility;
import com.teamgate.crm.generic.webdriverutility.UtilityClassObject;
import com.teamgate.crm.generic.webdriverutility.WebDriverUtility;
import com.teamgate.crm.objectRepository.DealsPage;
import com.teamgate.crm.objectRepository.SigninPage;

/**
 * This BaseClass is for Batch Execution 
 * @author Ponselvi
 */
public class BaseClass {

	/**
	 * Object Creation
	 */
	public DatabaseUtility dbutil = new DatabaseUtility();
	public WebDriverUtility wutil = new WebDriverUtility();
	public ExcelUtility eutil = new ExcelUtility();
	public PropertyFileUtility putil = new PropertyFileUtility();
	public JavaUtility jutil = new JavaUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver = null;
	
	/**
	 * This Configuration Annotation is used for Database Connection and Report Configuration
	 * @throws Throwable
	 */
	@BeforeSuite(groups = { "SmokeTest", "RegressionTest" })
	public void connectToDB() throws Throwable {
		Reporter.log("====Connect To DB, Report Configuration====", true);
		dbutil.getDBConnection("jdbc:mysql://49.249.28.218:3307/projects", "root@%", "root");
	}

	/**
	 * This Configuration Annotation is used for Launching the Browser
	 * @throws IOException
	 */
	@Parameters("BROWSER")
	@BeforeClass(groups = { "SmokeTest", "RegressionTest" })
	public void openBrowser(@Optional("chrome")String Browser) throws IOException {
		Reporter.log("====Open Browser====", true);
		String BROWSER=System.getProperty("browser", Browser);
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		wutil.maximize(driver);
		wutil.waitForPageToLoad(driver);
		sdriver = driver;
		UtilityClassObject.setDriver(driver);
	}

	/**
	 * This Configuration Annotation is used for Login to Application
	 * @throws Throwable 
	 */
	@BeforeMethod(groups = { "SmokeTest", "RegressionTest" })
	public void loginToApplication() throws Throwable {
		
		String URL = putil.getDataFromPropertiesFile("url");
		String EMAILID = putil.getDataFromPropertiesFile("emailid");
		String PASSWORD = putil.getDataFromPropertiesFile("password");
		driver.get(URL);
		SigninPage sp = new SigninPage(driver);
		sp.signinToApp(EMAILID, PASSWORD);
	}

	/**
	 * This Configuration Annotation is used for Logout of Application
	 */
	@AfterMethod(groups = { "SmokeTest", "RegressionTest" })
	public void logout() {
		Reporter.log("====Logout of Application====", true);
		DealsPage dp = new DealsPage(driver);
		dp.toLogout();
	}

	/**
	 * This Configuration Annotation is used for Closing the Browser
	 */
	@AfterClass(groups = { "SmokeTest", "RegressionTest" })
	public void closeBrowser() {
		Reporter.log("====Close Browser====", true);
		driver.quit();
	}

	/**
	 * This Configuration Annotation is used for Closing the Database Connection and Report Backup
	 */
	@AfterSuite(groups = { "SmokeTest", "RegressionTest" })
	public void closeDBConnection() {
		Reporter.log("====Close DB Connection, Report Backup====", true);
		dbutil.closeDBConnection();
	}
}
