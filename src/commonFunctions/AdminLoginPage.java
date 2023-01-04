package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLoginPage {
//define repository
	@FindBy(name="txtUsername")
	WebElement EnterUsername;
	@FindBy(name="txtPassword")
	WebElement EnterPassword;
	@FindBy(name="Submit")
	WebElement ClickLoginbtn;
	//method for login
	public void verifyLogin(String username,String password)
	{
		EnterUsername.sendKeys(username);
		EnterPassword.sendKeys(password);
		ClickLoginbtn.click();
		
		}
	
	
}
