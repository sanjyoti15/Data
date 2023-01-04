package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil{
	String inputpath ="D:\\11oclockSelenium\\DataDriven_Framework\\TestInput\\TestData.xlsx";
	String outputpath ="D:\\11oclockSelenium\\DataDriven_Framework\\TestOutput\\DataDrivenResults.xlsx";
	@Test
	public void startTest() throws Throwable
	{
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count no of rows in login sheet
		int rc = xl.rowCount("Login");
		Reporter.log("No.of rows in sheet::"+rc, true);
		//call login methods

		for( int i=1;i<rc;i++)
		{
			String user = xl.getCellData("Login", i, 0);
			String pass = xl.getCellData("Login", i, 1);
			//call login method
			boolean res = FunctionLibrary.verifyLogin(user, pass);
			//if res is true login successful status pass
			if(res)
			{
				xl.setCellData("Login",i, 2,"Login successful",outputpath);
				xl.setCellData("Login",i, 3, "pass",outputpath);

			}
			else {
				File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screen, new File("./Screenshot/Iterations/"+i+"-"+"Loginpage.png"));

				xl.setCellData("Login",i, 2,"Login fail", outputpath);
				xl.setCellData("Login",i, 3,"fail", outputpath);
			}

		}

	}
}


