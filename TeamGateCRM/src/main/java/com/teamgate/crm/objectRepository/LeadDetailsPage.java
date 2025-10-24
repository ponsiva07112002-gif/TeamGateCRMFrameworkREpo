package com.teamgate.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This class is for LeadDetailsPage which contains webelements and getter methods 
 * @author Ponselvi
 */
public class LeadDetailsPage {
	WebDriver driver;
	public LeadDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//span[@class='icon icon-loop2']")
	private WebElement convertSymbol;
	
	@FindBy(xpath = "//button[text()='Convert']")
	private WebElement convertButton;
	
	@FindBy(xpath = "//a[text()='View Deal']")
	private WebElement viewDealButton;
	
	@FindBy(xpath = "//span[@class='icon newglyphicon glyphicon-check']")
	private WebElement addActivitySymbol;
	
	@FindBy(xpath = "//input[@placeholder='Subject']")
	private WebElement subjectTextField;
	
	@FindBy(xpath = "//button[@class='btn blue fl record-save-button withSubButton']")
	private WebElement saveButton;
	
	public WebElement getConvertSymbol() {
		return convertSymbol;
	}

	public WebElement getConvertButton() {
		return convertButton;
	}

	public WebElement getAddActivitySymbol() {
		return addActivitySymbol;
	}

	public WebElement getSubjectTextField() {
		return subjectTextField;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getViewDealButton() {
		return viewDealButton;
	}
	
}
