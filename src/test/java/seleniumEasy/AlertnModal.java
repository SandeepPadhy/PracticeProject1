package seleniumEasy;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import lib.BaseTest;
import lib.Javascript;
import lib.LocalDriverFactory;
import lib.LogFactory;

public class AlertnModal extends BaseTest{

	String URL = "http://www.seleniumeasy.com/";
	AlertnModalPageFactory pf ;

	@BeforeClass
	public void preBoot() throws IOException {
		driver= fetchDriver();
		System.out.println("Driver fetched");
		 pf = new AlertnModalPageFactory(driver);
		sa= new SoftAssert();
	}
	
	@BeforeMethod
	public void loadSeleniumEasyDemoSite() throws Exception{
		System.out.println("Hitting Selenium Easy");
		driver.get(URL);
		new InputFormsPageFactory(driver).navigateToDemoSite();
	}
	
	@Test(enabled=true,alwaysRun=true , groups= {"alert"})
	public void testWindowPopup() throws InterruptedException {		
		pf.clickAlertnModal();
		String title=pf.navigateToWindowPopOption();
		assertEquals(title, "Selenium Easy - Window Popup Modal Demo");
		String main = pf.getmainWindow();
		pf.clickFollowBtn();
		Thread.sleep(5000);
		pf.navigateToWindowPopups(main , "Facebook");
		pf.backTomainWindow(main);
		pf.navigateToWindowPopups(main , "Twitter");
		pf.backTomainWindow(main);
	}
	@Test(enabled=true,alwaysRun=true , groups= {"alert"})
	public void testJavascriptAlerts() {
		pf.clickAlertnModal();
		assertEquals(true, pf.navigateToJsAlerts());
		assertEquals(true, pf.alert1());
		assertEquals(true, pf.alert2());
		assertEquals(true, pf.alert3());
		
	}
	@Test(enabled=true,alwaysRun=true , groups= {"file"})
	public void testFileDownload() {
		pf.clickAlertnModal();
		assertEquals(true, pf.navigateTofileDownloads());
		pf.doDownload();
	}
	

}
