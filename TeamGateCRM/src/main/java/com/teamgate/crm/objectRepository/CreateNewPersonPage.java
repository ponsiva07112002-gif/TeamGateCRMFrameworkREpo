package com.teamgate.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This class is for CreateNewPersonPage which contains webelements and getter methods 
 * @author Ponselvi
 */
public class CreateNewPersonPage {
	WebDriver driver;
	public CreateNewPersonPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//input[@placeholder='Person Name *']")
	private WebElement personNameTextField;
	
	@FindBy(xpath = "//button[@id='form-submit']")
	private WebElement saveButton;
	
	@FindBy(xpath = "//input[@placeholder='Phone']")
	private WebElement phoneTextField;
	
	public WebElement getPersonNameTextField() {
		return personNameTextField;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getPhoneTextField() {
		return phoneTextField;
	}
	
	
}
