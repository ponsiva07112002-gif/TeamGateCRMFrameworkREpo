package com.teamgate.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This class is for LeadsPage which contains webelements and getter methods 
 * @author Ponselvi
 */
public class LeadsPage {
	WebDriver driver;
	public LeadsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//a[text()='New']")
	private WebElement newLink;
	
	public WebElement getNewLink() {
		return newLink;
	}
	
}
