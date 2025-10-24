package com.teamgate.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This class is for CreateNewCompanyPage which contains webelements and getter methods 
 * @author Ponselvi
 */
public class CreateNewCompanyPage {
	WebDriver driver;
	public CreateNewCompanyPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//input[@placeholder='Company Name *']")
	private WebElement companyNameTextField;
	
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement saveButton;
	
	public WebElement getCompanyNameTextField() {
		return companyNameTextField;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
}
