package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {
	public static WebDriver driver;
	public static Properties config;
	
	
	@BeforeTest
	public static void setup() throws Throwable
	{
		config = new Properties();
		config.load(new FileInputStream("D:\\11oclockSelenium\\DataDriven_Framework\\PropertyFile\\Environment.properties"));
		if(config.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver= new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(config.getProperty("Browser").equalsIgnoreCase("Firefox"))
		{
			driver = new FirefoxDriver();

		}
		else
		{
			System.out.println("Browser key value is not matching");
		}	
	}
	@AfterTest
	public static void teardown()
	{
		driver.close();
	}

}