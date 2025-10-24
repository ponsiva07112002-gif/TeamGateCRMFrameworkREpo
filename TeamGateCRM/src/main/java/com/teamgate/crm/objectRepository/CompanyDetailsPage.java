package com.teamgate.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This class is for CompanyDetailsPage which contains webelements and getter methods 
 * @author Ponselvi
 */
public class CompanyDetailsPage {
	WebDriver driver;
	public CompanyDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//p[@class='comment']")
	private WebElement meetingTitle;
	
	@FindBy(xpath = "//a[@data-action='createDeal']")
	private WebElement dealPlusIcon;
	
	public WebElement getMeetingTitle() {
		return meetingTitle;
	}

	public WebElement getDealPlusIcon() {
		return dealPlusIcon;
	}
	
	
}
