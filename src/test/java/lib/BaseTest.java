package lib;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import seleniumEasy.InputForms;
import seleniumEasy.InputFormsPageFactory;

public class BaseTest {
	protected static WebDriver driver;
	protected InputFormsPageFactory pf;
	protected Javascript js;
	protected Actions act;
	protected SoftAssert sa ;
	protected LogFactory log = new LogFactory(InputForms.class.getName());
	 protected static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>();

	 
  @BeforeSuite(alwaysRun=true)
	public void basicSetup() throws IOException {
		driver = LocalDriverFactory.getDriver();
//		js= new Javascript(driver);
//		act= new Actions(driver);
//		sa= new SoftAssert();
		System.out.println("basicsetup");
	}
  
  public WebDriver fetchDriver() {
	  return driver;
  }

  @AfterSuite (alwaysRun=true)
  public void tearDown() {
	  System.out.println("shutting down");
		driver.quit();
	}

}
