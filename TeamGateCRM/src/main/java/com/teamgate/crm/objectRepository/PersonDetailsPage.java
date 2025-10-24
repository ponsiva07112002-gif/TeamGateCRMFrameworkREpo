package com.teamgate.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This class is for PersonDetailsPage which contains webelements and getter methods 
 * @author Ponselvi
 */
public class PersonDetailsPage {
	WebDriver driver;
	public PersonDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//span[text()='Edit']")
	private WebElement editSymbol;
	
	@FindBy(xpath = "//span[@class='rounded_info phone clickable']")
	private WebElement phoneNumber;
	
	@FindBy(xpath = "//span[@class='round_btn blue darkBg mgl10']")
	private WebElement associatedContactsPlusIcon;
	
	@FindBy(xpath = "//div[@class='right_card contact_card parent-white']/descendant::div[@class='search-block']/descendant::input")
	private WebElement associatedContactsTextField;
	
	@FindBy(xpath = "//div[@class='ui-menu-item-wrapper']")
	private WebElement company;
	
	public WebElement getEditSymbol() {
		return editSymbol;
	}

	public WebElement getPhoneNumber() {
		return phoneNumber;
	}

	public WebElement getAssociatedContactsPlusIcon() {
		return associatedContactsPlusIcon;
	}

	public WebElement getAssociatedContactsTextField() {
		return associatedContactsTextField;
	}

	public WebElement getCompany() {
		return company;
	}
	
	
}
