package com.teamgate.crm.settingsTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.teamgate.crm.baseclass.BaseClass;
import com.teamgate.crm.generic.fileutility.ExcelUtility;
import com.teamgate.crm.generic.webdriverutility.UtilityClassObject;
import com.teamgate.crm.objectRepository.DealsPage;
import com.teamgate.crm.objectRepository.SettingsPage;

import junit.framework.Assert;

/**
 * This class is for AccessSettingsTest
 * 
 * @author Ponselvi
 */
@Listeners(com.teamgate.crm.listenerutility.ListenerImplementation.class)
public class AccessSettingsTest extends BaseClass {
	/**
	 * This method is for AccessSettingsTest
	 * 
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	@Test(groups = "SmokeTest")
	public void accessSettingsTest() throws EncryptedDocumentException, IOException {
		UtilityClassObject.getTest().log(Status.INFO, "Reading Data from Excel utility");
		/* Reading Data From Excel utility */
		ExcelUtility eutil = new ExcelUtility();
		String expectedHeaderMsg = eutil.getDataFromExcel("Settings", 1, 2);

		/* Navigating to settings page */
		DealsPage dp = new DealsPage(driver);
		dp.getProfileIcon().click();
		dp.getSettingsOption().click();
		SettingsPage sp = new SettingsPage(driver);
		UtilityClassObject.getTest().log(Status.PASS, "navigated to settings page successfully");

		/* verification */
		String actHeaderMsg = sp.getHeaderMsg().getText();
		Assert.assertEquals(actHeaderMsg, expectedHeaderMsg);
		UtilityClassObject.getTest().log(Status.PASS, "Settings page is verified successfully");
		Reporter.log("Settings page is verified successfully", true);
	}
}
