package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class AddEmpPage {
	WebDriver driver;
	public AddEmpPage(WebDriver driver)
	{
		this.driver = driver;
			}
	//define repository
	@FindBy(xpath="//b[normalize-space()='PIM']")
	WebElement clickPim;
	@FindBy(name="btnAdd")
	WebElement clickAddbtn;
	@FindBy(name="firstName")
	WebElement EnterFname;
	@FindBy(name="middleName")
	WebElement EnterMname;
	@FindBy(name="lastName")
	WebElement EnterLname;
	@FindBy(name="employeeId")
    WebElement beforeEid;
	@FindBy(id="btnSave")
    WebElement clickSavebtn;
	@FindBy(name="personal[txtEmployeeId]")
	//@FindBy(xpath="//input[@id='personal_txtEmployeeId']")
    WebElement afterEid;
	

//method for adding employee
public boolean verifyEmp(String FirstName,String MiddleName,String LastName) 
{
	Actions ac =  new Actions(driver);
	ac.moveToElement(this.clickPim).click().perform();
	ac.moveToElement(this.clickAddbtn).click().perform();
    this.EnterFname.sendKeys(FirstName);
    this.EnterMname.sendKeys(MiddleName);
    this.EnterLname.sendKeys(LastName);
    
	String ExpectedEID = this.beforeEid.getAttribute("value");
	this.clickSavebtn.click();
	String ActualEID= this.afterEid.getAttribute("value");
	if(ExpectedEID.equals(ActualEID))
	{
		Reporter.log("Emplyoyee added succefully"+ExpectedEID+"      "+ActualEID,true);
		return true;
		
	}
	else
	{
		Reporter.log("Emplyoyee  not added succefully"+ExpectedEID+"      "+ActualEID,true);
		return false;
	}
	
    }

}
