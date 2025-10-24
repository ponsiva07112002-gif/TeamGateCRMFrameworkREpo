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
import com.teamgate.crm.objectRepository.LeadsPage;

/**
 * This class is for CreateNewLeadTest
 * 
 * @author Ponselvi
 */
@Listeners(com.teamgate.crm.listenerutility.ListenerImplementation.class)
public class CreateNewLeadTest extends BaseClass {
	/**
	 * This method is for CreateNewLeadTest
	 * 
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	@Test(groups = "SmokeTest")
	public void createNewLeadTest() throws EncryptedDocumentException, IOException {
		UtilityClassObject.getTest().log(Status.INFO, "Reading Data from Excel utility");
		/* Reading Data From Excel utility */
		ExcelUtility eutil = new ExcelUtility();

		UtilityClassObject.getTest().log(Status.INFO, "Reading Data from Java utility");
		/* Reading Data From Java utility */
		JavaUtility jutil = new JavaUtility();
		String leadName = eutil.getDataFromExcel("Lead", 1, 2) + jutil.getRandomNumber();

		/* Creating New Lead */
		DealsPage dp = new DealsPage(driver);
		dp.getLeadsLink().click();
		LeadsPage lp = new LeadsPage(driver);
		lp.getNewLink().click();
		CreateNewLeadPage cnlp = new CreateNewLeadPage(driver);
		cnlp.getLeadNameTextField().sendKeys(leadName);
		cnlp.getSaveButton().click();
		UtilityClassObject.getTest().log(Status.PASS, "Lead is created successfully");

		/* verification */
		String leadInfo = driver.findElement(By.xpath("//a[text()='" + leadName + "']")).getText();
		Assert.assertEquals(leadInfo, leadName, "Lead is not verified");
		UtilityClassObject.getTest().log(Status.PASS, "Lead is verified successfully");
		Reporter.log("Lead is verified successfully", true);

	}
}
