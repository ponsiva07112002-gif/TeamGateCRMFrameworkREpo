package com.teamgate.crm.peoplesTest;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.teamgate.crm.baseclass.BaseClass;
import com.teamgate.crm.generic.fileutility.ExcelUtility;
import com.teamgate.crm.generic.webdriverutility.JavaUtility;
import com.teamgate.crm.generic.webdriverutility.UtilityClassObject;
import com.teamgate.crm.objectRepository.CompaniesPage;
import com.teamgate.crm.objectRepository.CreateNewCompanyPage;
import com.teamgate.crm.objectRepository.CreateNewPersonPage;
import com.teamgate.crm.objectRepository.DealsPage;
import com.teamgate.crm.objectRepository.PeoplesPage;
import com.teamgate.crm.objectRepository.PersonDetailsPage;

/**
 * This class is for LinkPersonToCompanyTest
 * 
 * @author Ponselvi
 */
@Listeners(com.teamgate.crm.listenerutility.ListenerImplementation.class)
public class LinkPersonToCompanyTest extends BaseClass {
	/**
	 * This method is for LinkPersonToCompanyTest
	 * 
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	@Test(groups = "SystemTest")
	public void linkPersonToCompanyTest() throws EncryptedDocumentException, IOException {
		UtilityClassObject.getTest().log(Status.INFO, "Reading Data from Excel utility");
		/* Reading Data From Excel utility */
		ExcelUtility eutil = new ExcelUtility();

		UtilityClassObject.getTest().log(Status.INFO, "Reading Data from Java utility");
		/* Reading Data From Java utility */
		JavaUtility jutil = new JavaUtility();
		String companyName = eutil.getDataFromExcel("People", 4, 2) + jutil.getRandomNumber();
		String personName = eutil.getDataFromExcel("People", 4, 3) + jutil.getRandomNumber();

		/* creating company */
		DealsPage dp = new DealsPage(driver);
		dp.getCompaniesLink().click();
		CompaniesPage cp = new CompaniesPage(driver);
		cp.getNewLink().click();
		CreateNewCompanyPage cncp = new CreateNewCompanyPage(driver);
		cncp.getCompanyNameTextField().sendKeys(companyName);
		cncp.getSaveButton().click();
		UtilityClassObject.getTest().log(Status.PASS, "Company is created successfully");

		/* creating person */
		dp.getPeoplesLink().click();
		PeoplesPage pp = new PeoplesPage(driver);
		pp.getNewLink().click();
		CreateNewPersonPage cnpp = new CreateNewPersonPage(driver);
		cnpp.getPersonNameTextField().sendKeys(personName);
		cnpp.getSaveButton().click();
		UtilityClassObject.getTest().log(Status.PASS, "person is created successfully");

		/* linking person to company */
		driver.findElement(By.xpath("//a[text()='" + personName + "']")).click();
		PersonDetailsPage pdp = new PersonDetailsPage(driver);
		Actions action = new Actions(driver);
		action.scrollByAmount(0, 500);
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// wait.until(ExpectedConditions.visibilityOf(pdp.getAssociatedContactsPlusIcon()));
		pdp.getAssociatedContactsPlusIcon().click();
		pdp.getAssociatedContactsTextField().sendKeys(companyName);
		// driver.findElement(By.xpath("//div[@class='ui-menu-item-wrapper']")).click();
		pdp.getCompany().click();
		UtilityClassObject.getTest().log(Status.PASS, "person is linked to company successfully");

		/* verification */
		driver.findElement(By.xpath("//a[text()='" + companyName + "']")).click();
		String actual = driver.findElement(By.xpath("//a[text()='" + personName + "']")).getText();
		Assert.assertEquals(actual, personName);
		UtilityClassObject.getTest().log(Status.PASS, "person linked to company is verified successfully");
		Reporter.log("person linked to company is verified successfully", true);
	}
}
