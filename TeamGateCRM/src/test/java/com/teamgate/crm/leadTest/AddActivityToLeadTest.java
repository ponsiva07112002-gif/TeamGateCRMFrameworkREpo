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
import com.teamgate.crm.objectRepository.DealsPage;
import com.teamgate.crm.objectRepository.LeadDetailsPage;
import com.teamgate.crm.objectRepository.LeadsPage;

/**
 * This class is for AddActivityToLeadTest
 * 
 * @author Ponselvi
 */
@Listeners(com.teamgate.crm.listenerutility.ListenerImplementation.class)
public class AddActivityToLeadTest extends BaseClass {
	/**
	 * This method is for AddActivityToLeadTest
	 * 
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	@Test(groups = "IntegrationTest")
	public void addActivityToLead() throws EncryptedDocumentException, IOException, InterruptedException {
		UtilityClassObject.getTest().log(Status.INFO, "Reading Data from Excel utility");
		/* Reading Data From Excel utility */
		ExcelUtility eutil = new ExcelUtility();

		UtilityClassObject.getTest().log(Status.INFO, "Reading Data from Java utility");
		/* Reading Data From Java utility */
		JavaUtility jutil = new JavaUtility();
		String leadName = eutil.getDataFromExcel("Lead", 1, 2) + jutil.getRandomNumber();
		String subject = eutil.getDataFromExcel("Lead", 7, 3);

		/* Creating lead */
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

		/* Add Activity to Lead */
		ldp.getAddActivitySymbol().click();
		ldp.getSubjectTextField().sendKeys(subject);
		ldp.getSaveButton().click();
		UtilityClassObject.getTest().log(Status.PASS, "Activity is added to Lead successfully");

		/* verification */
		dp.getDashBoardLink().click();
		String activityFeed = driver.findElement(By.xpath("//a[text()='" + leadName + "']/preceding-sibling::a[text()='" + subject + "']/ancestor::span")).getText();
		boolean status = activityFeed.contains(leadName);
		Assert.assertEquals(status, true);
		Thread.sleep(3000);
		UtilityClassObject.getTest().log(Status.PASS, "Activity is verified in Activity feed successfully");
		Reporter.log("Activity is verified in Activity feed successfully", true);
	}
}
