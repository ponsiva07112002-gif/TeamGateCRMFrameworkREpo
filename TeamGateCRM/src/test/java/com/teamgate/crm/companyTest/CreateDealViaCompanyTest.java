package com.teamgate.crm.companyTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
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
import com.teamgate.crm.objectRepository.CompanyDetailsPage;
import com.teamgate.crm.objectRepository.CreateNewCompanyPage;
import com.teamgate.crm.objectRepository.CreateNewDealPage;
import com.teamgate.crm.objectRepository.DealsPage;

/**
 * This class is for CreateDealViaCompanyTest
 * 
 * @author Ponselvi
 */
@Listeners(com.teamgate.crm.listenerutility.ListenerImplementation.class)
public class CreateDealViaCompanyTest extends BaseClass {
	/**
	 * This method is for CreateDealViaCompanyTest
	 * 
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	@Test(groups = "SystemTest")
	public void createDealViaCompanyTest() throws EncryptedDocumentException, IOException, InterruptedException {
		UtilityClassObject.getTest().log(Status.INFO, "Reading Data from Excel utility");
		/* Reading Data From Excel utility */
		ExcelUtility eutil = new ExcelUtility();
		String companyName = eutil.getDataFromExcel("Companies", 1, 2) + jutil.getRandomNumber();

		UtilityClassObject.getTest().log(Status.INFO, "Reading Data from Java utility");
		/* Reading Data From Java utility */
		JavaUtility jutil = new JavaUtility();
		String DealName = eutil.getDataFromExcel("Companies", 1, 3) + jutil.getRandomNumber();

		/* Creating New Company */
		DealsPage dp = new DealsPage(driver);
		dp.getCompaniesLink().click();
		CompaniesPage cp = new CompaniesPage(driver);
		cp.getNewLink().click();
		CreateNewCompanyPage cncp = new CreateNewCompanyPage(driver);
		cncp.getCompanyNameTextField().sendKeys(companyName);
		cncp.getSaveButton().click();
		UtilityClassObject.getTest().log(Status.PASS, "New company is successfully created");

		driver.findElement(By.xpath("//a[text()='" + companyName + "']")).click();
		CompanyDetailsPage cdp = new CompanyDetailsPage(driver);
		/* Creating New Deal */
		Thread.sleep(3000);
		cdp.getDealPlusIcon().click();
		CreateNewDealPage cndp = new CreateNewDealPage(driver);
		Thread.sleep(3000);
		cndp.getNameOfDealTextField().sendKeys(DealName);
		cndp.getSaveButton().click();
		UtilityClassObject.getTest().log(Status.PASS, "New Deal is created successfully via company");

		/* verification */
		dp.getDealsLink().click();
		String actual = driver.findElement(By.xpath("//a[text()='" + DealName + "']")).getText();
		Assert.assertEquals(actual, DealName);
		UtilityClassObject.getTest().log(Status.PASS, "Deal is verified successfully");
		Reporter.log("Deal is verified successfully", true);
	}
}
