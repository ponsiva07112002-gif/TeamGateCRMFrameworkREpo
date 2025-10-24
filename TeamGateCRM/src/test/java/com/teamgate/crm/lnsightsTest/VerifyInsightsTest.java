package com.teamgate.crm.lnsightsTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.teamgate.crm.baseclass.BaseClass;
import com.teamgate.crm.generic.fileutility.ExcelUtility;
import com.teamgate.crm.generic.webdriverutility.UtilityClassObject;
import com.teamgate.crm.objectRepository.DealsPage;

/**
 * This class is for VerifyInsightsTest
 * 
 * @author Ponselvi
 */
@Listeners(com.teamgate.crm.listenerutility.ListenerImplementation.class)
public class VerifyInsightsTest extends BaseClass {
	/**
	 * This method is for VerifyInsightsTest
	 * 
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	@Test(groups = "SmokeTest")
	public void verifyInsightsTest() throws EncryptedDocumentException, IOException {
		UtilityClassObject.getTest().log(Status.INFO, "Reading Data from Excel utility");
		/* Reading Data From Excel utility */
		ExcelUtility eutil = new ExcelUtility();
		String expectedTitle = eutil.getDataFromExcel("Insights", 1, 2);

		/* Navigate to Insights */
		DealsPage dp = new DealsPage(driver);
		dp.getInsightsLink().click();
		UtilityClassObject.getTest().log(Status.PASS, "Navigated to Insights module successfully");

		/* verification */
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, expectedTitle);
		UtilityClassObject.getTest().log(Status.PASS, "Verified Insights module");
		Reporter.log("Verified Insights module", true);
	}
}
