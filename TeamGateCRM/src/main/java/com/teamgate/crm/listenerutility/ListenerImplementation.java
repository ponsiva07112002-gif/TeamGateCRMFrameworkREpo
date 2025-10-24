package com.teamgate.crm.listenerutility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.teamgate.crm.baseclass.BaseClass;
import com.teamgate.crm.generic.webdriverutility.UtilityClassObject;

/**
 * This class is used to Take the Screenshot and to generate Report
 * 
 * @author Ponselvi
 */
public class ListenerImplementation implements ITestListener, ISuiteListener {

	public ExtentSparkReporter spark;
	public static ExtentReports report;
	public static ExtentTest test;

	/**
	 * This method is used for report configuration
	 */
	public void onStart(ISuite suite) {
		System.out.println("Report configuration");
		//UtilityClassObject.getTest().log(Status.INFO, "Report configuration");
		//test.log(Status.INFO, "Report configuration");
		/* Spark Report configuration */
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		spark = new ExtentSparkReporter("./AdvanceReport/report_" + time + ".html");
		spark.config().setDocumentTitle("CRM Test Suite Result");
		spark.config().setReportName("CRM Result");
		spark.config().setTheme(Theme.DARK);

		/* Add environment info and create test */
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "Chrome");
	}

	/**
	 * This method is used to take report back-up
	 */
	public void onFinish(ISuite suite) {
		report.flush();
		System.out.println("Report Backup");
		//test.log(Status.INFO, "Report Backup");
	}

	/**
	 * This method is used to create the test
	 */
	public void onTestStart(ITestResult result) {

		//test.log(Status.INFO, result.getMethod().getMethodName() + "===START===");
		System.out.println(result.getMethod().getMethodName() + "===START===");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
	}

	/**
	 * This method is used to take screenshot when the test is passed
	 */
	public void onTestSuccess(ITestResult result) {
		// String testname = result.getMethod().getMethodName();
		// TakesScreenshot screenshot = (TakesScreenshot) BaseClass.sdriver;
		// String filepath = screenshot.getScreenshotAs(OutputType.BASE64);
		// String time = new Date().toString().replace(" ", "_").replace(":", "_");
		// test.addScreenCaptureFromBase64String(filepath, testname+"_"+time);
		test.log(Status.PASS, "===>" + result.getMethod().getMethodName() + ">===COMPLETED===");
	}

	/**
	 * This method is used to take screenshot when the test is failed
	 */
	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		TakesScreenshot screenshot = (TakesScreenshot) BaseClass.sdriver;
		String filepath = screenshot.getScreenshotAs(OutputType.BASE64);
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		UtilityClassObject.getTest().addScreenCaptureFromBase64String(filepath, testname + "_" + time);
		// test.addScreenCaptureFromBase64String(filepath, testname+"_"+time);
		test.log(Status.FAIL, result.getMethod().getMethodName() + "===FAILED===");
	}
}
