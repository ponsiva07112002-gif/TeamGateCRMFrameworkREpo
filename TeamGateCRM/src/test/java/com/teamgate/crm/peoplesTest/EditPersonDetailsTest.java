package com.teamgate.crm.peoplesTest;

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
import com.teamgate.crm.objectRepository.CreateNewPersonPage;
import com.teamgate.crm.objectRepository.DealsPage;
import com.teamgate.crm.objectRepository.EditPersonPage;
import com.teamgate.crm.objectRepository.PeoplesPage;
import com.teamgate.crm.objectRepository.PersonDetailsPage;

/**
 * This class is for EditPersonDetailsTest
 * 
 * @author Ponselvi
 */
@Listeners(com.teamgate.crm.listenerutility.ListenerImplementation.class)
public class EditPersonDetailsTest extends BaseClass {
	/**
	 * This method is for EditPersonDetailsTest
	 * 
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	@Test(groups = "SystemTest")
	public void editPersonDetailsTest() throws EncryptedDocumentException, IOException {
		UtilityClassObject.getTest().log(Status.INFO, "Reading Data from Excel utility");
		/* Reading Data From Excel utility */
		ExcelUtility eutil = new ExcelUtility();

		UtilityClassObject.getTest().log(Status.INFO, "Reading Data from Java utility");
		/* Reading Data From Java utility */
		JavaUtility jutil = new JavaUtility();
		String peopleName = eutil.getDataFromExcel("People", 1, 2) + jutil.getRandomNumber();
		String phoneNumber = eutil.getDataFromExcel("People", 1, 3);

		/* Creating New Person */
		DealsPage dp = new DealsPage(driver);
		dp.getPeoplesLink().click();
		PeoplesPage pp = new PeoplesPage(driver);
		pp.getNewLink().click();
		CreateNewPersonPage cnpp = new CreateNewPersonPage(driver);
		cnpp.getPersonNameTextField().sendKeys(peopleName);
		cnpp.getSaveButton().click();
		UtilityClassObject.getTest().log(Status.PASS, "Contact person is creted successfully");

		/* Edit person Details */
		driver.findElement(By.xpath("//a[text()='" + peopleName + "']")).click();
		PersonDetailsPage pdp = new PersonDetailsPage(driver);
		pdp.getEditSymbol().click();
		EditPersonPage epp = new EditPersonPage(driver);
		epp.getPhoneTextField().sendKeys(phoneNumber);
		epp.getSaveButton().click();
		UtilityClassObject.getTest().log(Status.PASS, "Contact person details are modified successfully");

		/* verification */
		String actualPhoneNumber = pdp.getPhoneNumber().getText();
		boolean status = actualPhoneNumber.contains(phoneNumber);
		Assert.assertEquals(status, true);
		UtilityClassObject.getTest().log(Status.PASS, "modification is verified successfully");
		Reporter.log("modification is verified successfully", true);
	}
}
