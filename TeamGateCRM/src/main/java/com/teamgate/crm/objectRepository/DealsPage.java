package com.teamgate.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This class is for DealsPage which contains webelements and getter methods 
 * @author Ponselvi
 */
public class DealsPage {
	WebDriver driver;
	public DealsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText = "New")
	private WebElement newLink;
	
	@FindBy(xpath = "//a[@href='/google/leads']")
	private WebElement leadsLink;
	
	@FindBy(xpath = "//a[@href='/google/insights']")
	private WebElement insightsLink;
	
	@FindBy(xpath = "//a[@href='/google/deals']")
	private WebElement dealsLink;
	
	@FindBy(xpath = "//img[@class='rounded-circle']")
	private WebElement profileIcon;
	
	@FindBy(xpath = "//span[text()='Log-out']")
	private WebElement logoutButton;
	
	@FindBy(xpath = "//span[text()='Settings']")
	private WebElement settingsOption;
	
	@FindBy(xpath = "//a[@href='/google/organizer']")
	private WebElement organizerLink;
	
	@FindBy(xpath = "//a[@href='/google/companies']")
	private WebElement companiesLink;
	
	@FindBy(xpath = "//a[@href='/google/board']")
	private WebElement dashBoardLink;
	
	@FindBy(xpath = "//a[@href='/google/people']")
	private WebElement peoplesLink;
	
	public WebElement getOrganizerLink() {
		return organizerLink;
	}
	public WebElement getNewLink() {
		return newLink;
	}
	public WebElement getLeadsLink() {
		return leadsLink;
	}
	public WebElement getInsightsLink() {
		return insightsLink;
	}
	public WebElement getProfileIcon() {
		return profileIcon;
	}
	public WebElement getLogoutButton() {
		return logoutButton;
	}
	public WebElement getCompaniesLink() {
		return companiesLink;
	}
	public WebElement getSettingsOption() {
		return settingsOption;
	}
	public WebElement getDashBoardLink() {
		return dashBoardLink;
	}
	public WebElement getPeoplesLink() {
		return peoplesLink;
	}
	public WebElement getDealsLink() {
		return dealsLink;
	}
	public void toLogout() {
		profileIcon.click();
		logoutButton.click();
	}
}
