package com.teamgate.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.teamgate.crm.generic.webdriverutility.WebDriverUtility;
/**
 * This class is for SigninPage which contains webelements and getter methods 
 * @author Ponselvi
 */
public class SigninPage {
	WebDriver driver;
	public SigninPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText = "Log in")
	private WebElement loginEle;
	
	@FindBy(id = "LoginForm_username")
	private WebElement emailIdTextField;
	
	@FindBy(id = "LoginForm_password")
	private WebElement passwordTextField;
	
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement signinButton;
	public WebElement getLoginEle() {
		return loginEle;
	}

	public WebElement getEmailIdTextField() {
		return emailIdTextField;
	}

	public WebElement getPasswordTextField() {
		return passwordTextField;
	}

	public WebElement getSigninButton() {
		return signinButton;
	}
	
	public void signinToApp(String emailId, String password) throws Throwable {
		loginEle.click();
		WebDriverUtility wutil = new WebDriverUtility();
		wutil.switchToTabOnTitle(driver, "Teamgate - Guest");
		emailIdTextField.sendKeys(emailId);
		passwordTextField.sendKeys(password);
		signinButton.click();
	}

}
