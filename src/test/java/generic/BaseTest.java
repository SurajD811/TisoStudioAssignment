package generic;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeSuite;

import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements IAutoConstant {
	
	protected static WebDriver driver;
	@BeforeSuite
	public void setUp() throws IOException
	{
		Flib f=new Flib();
		String url = f.readPropertyData(PROP_PATH, "URL");	
		WebDriverManager.chromedriver().setup();
	    driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);

	}

	public void failed(String methodName)
	{
		try{
			TakesScreenshot ts=(TakesScreenshot)driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File dest = new File(SCREENSHOT_PATH+methodName+".png");
			Files.copy(src, dest);
		}
		catch (Exception e) {
		}
	}

		@AfterSuite
		public void tearDown()
		{
			driver.quit();
		}

}