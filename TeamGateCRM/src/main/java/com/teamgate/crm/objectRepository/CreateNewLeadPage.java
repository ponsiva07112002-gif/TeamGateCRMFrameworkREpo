package com.teamgate.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This class is for CreateNewLeadPage which contains webelements and getter methods 
 * @author Ponselvi
 */
public class CreateNewLeadPage {
	WebDriver driver;
	public CreateNewLeadPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//input[@placeholder='Lead Name *']")
	private WebElement leadNameTextField;
	
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement saveButton;
	public WebElement getLeadNameTextField() {
		return leadNameTextField;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	
	
}
