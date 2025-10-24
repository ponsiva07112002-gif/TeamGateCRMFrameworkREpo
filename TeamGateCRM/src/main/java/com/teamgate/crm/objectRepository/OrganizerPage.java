package com.teamgate.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This class is for OrganizerPage which contains webelements and getter methods 
 * @author Ponselvi
 */
public class OrganizerPage {
	WebDriver driver;
	public OrganizerPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//a[text()='New']")
	private WebElement newLink;
	
	@FindBy(xpath = "//input[@name='title']")
	private WebElement titleTextfield;
	
	@FindBy(xpath = "//button[@class='btn blue fl record-save-button withSubButton']")
	private WebElement saveButton;
	
	@FindBy(xpath = "//span[@data-type='contacts']")
	private WebElement plusIcon;
	
	@FindBy(name = "search-contacts")
	private WebElement contactsTextField;
	
	@FindBy(xpath = "//div[@class='ui-menu-item-wrapper']" )
	private WebElement company;
	
	@FindBy(xpath = "//button[text()='Confirm']")
	private WebElement confirmButton;
	
	public WebElement getNewLink() {
		return newLink;
	}

	public WebElement getTitleTextfield() {
		return titleTextfield;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getPlusIcon() {
		return plusIcon;
	}

	public WebElement getContactsTextField() {
		return contactsTextField;
	}

	public WebElement getCompany() {
		return company;
	}

	public WebElement getConfirmButton() {
		return confirmButton;
	}
	
}
