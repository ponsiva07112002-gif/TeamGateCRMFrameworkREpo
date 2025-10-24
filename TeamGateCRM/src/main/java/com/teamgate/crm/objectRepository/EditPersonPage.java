package com.teamgate.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This class is for EditPersonPage which contains webelements and getter methods 
 * @author Ponselvi
 */
public class EditPersonPage {
	WebDriver driver;
	public EditPersonPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@placeholder='Phone']")
	private WebElement phoneTextField;
	
	@FindBy(xpath = "//button[@id='form-submit']")
	private WebElement saveButton;
	
	public WebElement getPhoneTextField() {
		return phoneTextField;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	
	
}
