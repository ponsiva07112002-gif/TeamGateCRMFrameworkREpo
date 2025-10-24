package com.teamgate.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * This class is for DealDetailsPage which contains webelements and getter methods 
 * @author Ponselvi
 */
public class DealDetailsPage {
	WebDriver driver;
	public DealDetailsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//div[@class='mainTitle']")
	private WebElement dealInfo;
	
	public WebElement getDealInfo() {
		return dealInfo;
	}
	
}
