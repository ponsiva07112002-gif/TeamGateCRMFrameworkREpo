package com.teamgate.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This class is for CreateNewDealPage which contains webelements and getter methods 
 * @author Ponselvi
 */
public class CreateNewDealPage {
	WebDriver driver;
	public CreateNewDealPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//input[@placeholder='Name of Deal']")
	private WebElement nameOfDealTextField;
	
	@FindBy(xpath = "//button[@id='form-submit']")
	private WebElement saveButton;
	
	public WebElement getNameOfDealTextField() {
		return nameOfDealTextField;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	
	

}
