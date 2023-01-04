package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLogoutPage {
	//define Repository
	@FindBy(id="welcome")
	WebElement Clickwelcome;
	@FindBy(linkText = "Logout")
	WebElement Clicklogout;
	//method for logout
	public void verifyLogout()
	{
		Clickwelcome.click();
		Clicklogout.click();
		
		
	}
	
}
