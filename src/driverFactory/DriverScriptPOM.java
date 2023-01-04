package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.AddEmpPage;
import config.AppUtil1;
import utilities.ExcelFileUtil;

public class DriverScriptPOM extends AppUtil1 {
String inputpath ="D:\\11oclockSelenium\\DataDriven_Framework\\TestInput\\Employee.xlsx";
String outputpath ="D:\\11oclockSelenium\\DataDriven_Framework\\TestOutput\\POMResults.xlsx";

@Test
public void starttest() throws Throwable
{
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	int rc= xl.rowCount("EmpData");
	Reporter.log ("no of rows are::"+rc,true);
	
	for (int i=1;i<=rc;i++)
	{
		String fname = xl.getCellData("EmpData", i, 0);
		String mname = xl.getCellData("EmpData",i, 1);
		String lname = xl.getCellData("EmpData",i, 2);
		
		AddEmpPage emp = PageFactory.initElements(driver, AddEmpPage.class);
		boolean res = emp.verifyEmp(fname, mname, lname);
		if(res)
		{
			xl.setCellData("EmpData", i, 3,"pass",outputpath);
			
		}
		else
		{
			xl.setCellData("EmpData", i, 3,"fail",outputpath);
			
		}
	}
}
}



