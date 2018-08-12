package lib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class LocalDriverFactory {

	// static instance declaration to null
	private static WebDriver driver = null;
	private static String filepath= System.getProperty("user.dir")+"\\src\\test\\resources\\common.properties";
	private static Properties p = new Properties();

	private LocalDriverFactory(String browser) {	
		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//drivers//chromedriver.exe");
			ChromeOptions co = new ChromeOptions();
			// co.addArguments("--headless");
			// co.addArguments("--disable-gpu");
			// we can use above lines to start chrome headless OR we can set the value as
			// true
//			co.setHeadless(false);

			// co.addExtensions(new File (filepath));// to add extensions to the fp

			/**
			 * if chrome binary is not present at its default location we can pass the
			 * desired binary location
			 */
			// co.setBinary(path)

			// to hide message 'Chrome is being controlled by automated test software'
//			co.addArguments("disable-infobars");
			
			//to handle SSL-cert alerts
//			co.setAcceptInsecureCerts(true);
//			co.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
			
			// to start with default fp or anyother chrome fp
//			co.addArguments("user-data-dir=C:\\Users\\Senpai\\AppData\\Local\\Google\\Chrome\\User Data");
			
			//to set chrome preferences
			//https://cs.chromium.org/chromium/src/chrome/common/pref_names.cc
//			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();  
//			 chromePrefs.put("fp.default_content_settings.popups", 0);  
//			 chromePrefs.put("download.default_directory", System.getProperty("user.dir")+"//downloads");   
//			 co.setExperimentalOption("prefs", chromePrefs);  
//			 co.addArguments("--disable-notifications");  
			 
			driver = new ChromeDriver(co);
			
		} else if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//drivers//geckodriver.exe");
//			FirefoxProfile fp = new FirefoxProfile();
//			fp.setPreference("browser.download.folderList", 2);
//			fp.setPreference("browser.download.dir", System.getProperty("user.dir")+"//downloads");
//			fp.setPreference("browser.download.manager.showWhenStarting", false);
//			System.out.println("Path ->" +  System.getProperty("user.dir")+"\\downloads");
//			fp.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;octet/stream");
//			fp.setPreference("browser.helperApps.neverAsk.openFile", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;octet/stream");
//			fp.setPreference("browser.helperApps.alwaysAsk.force", false);
//			fp.setPreference("browser.download.manager.alertOnEXEOpen", false);
//			fp.setPreference("browser.download.manager.focusWhenStarting", false);
//			fp.setPreference("browser.download.manager.useWindow", false);
//			fp.setPreference("browser.download.manager.showAlertOnComplete", false);
//			fp.setPreference("browser.download.manager.closeWhenDone", true);
//			fp.setPreference("pdfjs.disabled", true);
//			fp.setPreference("browser.helperApps.alwaysAsk.force", false);
//			fp.setPreference("browser.download.manager.alertOnEXEOpen", false);
//			fp.setPreference("browser.download.manager.focusWhenStarting", false);
//			fp.setPreference("browser.download.manager.useWindow", false); 	
//			fp.setPreference("browser.download.manager.showAlertOnComplete", false);
//			fp.setPreference("browser.download.manager.closeWhenDone", true);
//			FirefoxOptions fo = new FirefoxOptions();
//			fo.setAcceptInsecureCerts(true);
//			fo.setProfile(fp);
//			this.driver = new FirefoxDriver(fo);
			driver = new FirefoxDriver();
			System.out.println("invoking firefox driver");			
		} else if (browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "//drivers//IEDriver.exe");
			InternetExplorerOptions ieo = new InternetExplorerOptions();
			// DesiredCapablities is now depreceated
			// DesiredCapabilities dc = new DesiredCapabilities();
			// dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
			// true);
			// op.merge(dc);

			ieo.introduceFlakinessByIgnoringSecurityDomains();
			ieo.ignoreZoomSettings();
			driver = new InternetExplorerDriver(ieo);

		} else if (browser.equalsIgnoreCase("Edge")) {
			System.setProperty("wbdriver.edge.driver",
					System.getProperty("user.dir") + "//driver//MicrosoftDriver.exe");
			driver = new EdgeDriver();
		} else {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//drivers//chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	public static WebDriver getDriver() throws IOException {
		if (driver == null) {
			System.out.println("inside LocalDriverFactory");
			InputStream file = new FileInputStream (filepath);
			p.load(file);
			new LocalDriverFactory(p.getProperty("browser"));
		}
		return driver;

	}
	


}
