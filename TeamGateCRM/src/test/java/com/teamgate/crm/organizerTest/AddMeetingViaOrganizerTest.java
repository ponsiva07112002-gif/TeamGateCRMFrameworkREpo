package com.teamgate.crm.organizerTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import com.teamgate.crm.objectRepository.DealsPage;
import com.teamgate.crm.objectRepository.OrganizerPage;

/**
 * This class is for AddMeetingViaOrganizerTest
 * 
 * @author Ponselvi
 */
@Listeners(com.teamgate.crm.listenerutility.ListenerImplementation.class)
public class AddMeetingViaOrganizerTest extends BaseClass {
	/**
	 * This method is for AddMeetingViaOrganizerTest
	 * 
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	@Test(groups = "IntegrationTest")
	public void addMeetingViaOrganizerTest() throws EncryptedDocumentException, IOException {
		UtilityClassObject.getTest().log(Status.INFO, "Reading Data from Excel utility");
		/* Reading Data From Excel utility */
		ExcelUtility eutil = new ExcelUtility();

		UtilityClassObject.getTest().log(Status.INFO, "Reading Data from Java utility");
		/* Reading Data From Java utility */
		JavaUtility jutil = new JavaUtility();
		String companyName = eutil.getDataFromExcel("Organizer", 1, 2) + jutil.getRandomNumber();
		String meetingTitle = eutil.getDataFromExcel("Organizer", 1, 3);

		/* Creating New company */
		DealsPage dp = new DealsPage(driver);
		dp.getCompaniesLink().click();
		CompaniesPage cp = new CompaniesPage(driver);
		cp.getNewLink().click();
		CreateNewCompanyPage cncp = new CreateNewCompanyPage(driver);
		cncp.getCompanyNameTextField().sendKeys(companyName);
		cncp.getSaveButton().click();
		UtilityClassObject.getTest().log(Status.PASS, "Company is created successfully");

		/* Add meeting */
		dp.getOrganizerLink().click();
		OrganizerPage op = new OrganizerPage(driver);
		op.getNewLink().click();
		op.getTitleTextfield().sendKeys(meetingTitle);
		// op.getPlusIcon().click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", op.getPlusIcon());
		op.getContactsTextField().sendKeys(companyName);
		op.getCompany().click();
		// op.getConfirmButton().click();
		op.getSaveButton().click();
		op.getConfirmButton().click();
		UtilityClassObject.getTest().log(Status.PASS, "Meeting is added in organizer successfully");

		/* verification */
		dp.getCompaniesLink().click();
		driver.findElement(By.xpath("//a[text()='" + companyName + "']")).click();
		CompanyDetailsPage cdp = new CompanyDetailsPage(driver);
		String actualInfo = cdp.getMeetingTitle().getText();
		Assert.assertEquals(actualInfo, meetingTitle);
		UtilityClassObject.getTest().log(Status.PASS, "Meeting is verified in comapny successfully");
		Reporter.log("Meeting is verified in comapny successfully", true);
	}
}
