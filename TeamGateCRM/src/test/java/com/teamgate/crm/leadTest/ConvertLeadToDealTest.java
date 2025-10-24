package com.teamgate.crm.leadTest;

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
import com.teamgate.crm.objectRepository.CreateNewLeadPage;
import com.teamgate.crm.objectRepository.DealDetailsPage;
import com.teamgate.crm.objectRepository.DealsPage;
import com.teamgate.crm.objectRepository.LeadDetailsPage;
import com.teamgate.crm.objectRepository.LeadsPage;

/**
 * This class is for ConvertLeadToDealTest
 * 
 * @author Ponselvi
 */
@Listeners(com.teamgate.crm.listenerutility.ListenerImplementation.class)
public class ConvertLeadToDealTest extends BaseClass {
	/**
	 * This method is for ConvertLeadToDealTest
	 * 
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	@Test(groups = "IntegrationTest")
	public void convertLeadToDealTest() throws EncryptedDocumentException, IOException {
		UtilityClassObject.getTest().log(Status.INFO, "Reading Data from Excel utility");
		/* Reading Data From Excel utility */
		ExcelUtility eutil = new ExcelUtility();

		UtilityClassObject.getTest().log(Status.INFO, "Reading Data from Java utility");
		/* Reading Data From Java utility */
		JavaUtility jutil = new JavaUtility();
		String leadName = eutil.getDataFromExcel("Lead", 4, 2) + jutil.getRandomNumber();

		/* Creating new Lead */
		DealsPage dp = new DealsPage(driver);
		dp.getLeadsLink().click();
		LeadsPage lp = new LeadsPage(driver);
		lp.getNewLink().click();
		CreateNewLeadPage cnlp = new CreateNewLeadPage(driver);
		cnlp.getLeadNameTextField().sendKeys(leadName);
		cnlp.getSaveButton().click();
		UtilityClassObject.getTest().log(Status.PASS, "Lead is created successfully");

		driver.findElement(By.xpath("//a[text()='" + leadName + "']")).click();
		LeadDetailsPage ldp = new LeadDetailsPage(driver);
		/* Converting Lead to Deal */
		ldp.getConvertSymbol().click();
		ldp.getConvertButton().click();
		ldp.getViewDealButton().click();
		UtilityClassObject.getTest().log(Status.PASS, "Lead is Converted to Deal successfully");

		/* verification */
		DealDetailsPage ddp = new DealDetailsPage(driver);
		String dealName = ddp.getDealInfo().getText();
		boolean status = dealName.contains(leadName);
		Assert.assertEquals(status, true);
		UtilityClassObject.getTest().log(Status.PASS, "New Deal is verified successfully");
		Reporter.log("New Deal is verified successfully", true);
	}
}
