package seleniumEasy;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import lib.Javascript;
import lib.LogFactory;
import lib.WrapperTest;

public class AlertnModalPageFactory {
	private WebDriver driver;
	private Actions act;
	private Javascript js;
	private WrapperTest test ;
	private LogFactory log = new LogFactory(InputFormsPageFactory.class.getName());

	@FindBy(xpath = "//a[@class='dropdown-toggle' and contains(text(),'Alert')]")
	private WebElement alertnModalTab;

	@FindBy(linkText = "Window Popup Modal")
	private WebElement windowPopupModal;

	@FindBy(xpath = "//h2[text()='Window popup Modal Example for Automation']")
	private WebElement pageHeader;

	@FindBy(how = How.XPATH, using = "//a[@title='Follow @seleniumeasy' and @class='btn btn-primary ']")
	private WebElement follow1;
	
	@FindBy(linkText="Javascript Alerts")
	private WebElement jsAlert;
	
	@FindBy(xpath="//div[text()='Java Script Alert Box']//following-sibling::div/button[@class='btn btn-default']")
	private WebElement click1;
	
	@FindBy(xpath="//div[text()='Java Script Confirm Box']//following-sibling::div/button[@class='btn btn-default btn-lg']")
	private WebElement click2;
	
	@FindBy(xpath="//div[text()='Java Script Alert Box']//following-sibling::div/button[@class='btn btn-default btn-lg']")
	private WebElement click3;

	@FindBy(linkText="File Download")
	private WebElement filedownload;
	
	@FindBy(how=How.TAG_NAME , using="textarea")
	private WebElement textarea;
	
	@FindBy(id="create")
	private WebElement generate;
	
	public AlertnModalPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		test = new WrapperTest(driver);
	}

	public void clickAlertnModal() {
		if (alertnModalTab.isDisplayed()) {
			alertnModalTab.click();
		}
	}

	public String navigateToWindowPopOption() {
		if (windowPopupModal.isEnabled()) {
			windowPopupModal.click();
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			if (pageHeader.getText().equals("Window popup Modal Example for Automation")) {
				log.info("Navigated to: " + driver.getTitle());
			} else {
				log.error("could not Navigate to Windom Popup page");
			}
		} else {
			log.error("Window PopUP Modal option is unavailable");
		}
		return driver.getTitle();
	}

	public void clickFollowBtn() {
		try {
		if (follow1.isEnabled()) {
			follow1.click();
			log.info("Follow button is clicked");
		}else {
			test.isElementClickable(follow1, 10);
			if (follow1.isEnabled()) {
				follow1.click();
				log.info("Follow button is clicked");
			}
		}
		}catch(Exception e) {
			log.error(e.getMessage());
		}
	}
	public String getmainWindow() {
		System.out.println(driver.getWindowHandle());
		return driver.getWindowHandle();
	}
	public void backTomainWindow(String main) throws InterruptedException {
		Set<String> windows= driver.getWindowHandles();
		Iterator it= windows.iterator();
		while(it.hasNext()) {
			String pops=it.next().toString();
			if (pops.equals(main))
			{
				Thread.sleep(2000);
				driver.switchTo().window(pops);
				log.info("Back to: "+ driver.getTitle());
			}
		}
	}
	public void navigateToWindowPopups(String main,String title) throws InterruptedException {
		Set<String> windows= driver.getWindowHandles();
		for (String pops:windows) {
			if (pops!=main)
			{
				Thread.sleep(2000);
				driver.switchTo().window(pops);
				if (driver.getTitle().contains(title)) {	
					log.info("Closing: "+ title);
					driver.close();
				}
			}
		}
	}
	
	public boolean navigateToJsAlerts() {
		try {
			test.isElementClickable(jsAlert, 10);
			if(jsAlert.isEnabled()) {
				jsAlert.click();
				log.info("Clicked on Javascript Alerts option");
				if(click1.isDisplayed()) {
					return true;
				}
			}
		}catch (Exception e) {
			log.error(e.getMessage());
		}
		return false;
	}
	
	public boolean alert1() {
		try {
			if(click1.isDisplayed()) {
				click1.click();
				test.isAlertPresent(10);
				Alert a1 = driver.switchTo().alert();
				log.info(a1.getText());
				a1.accept();
				return true;
			}
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		return false;
	}
	
	public boolean alert2() {
		try {
			if(click2.isDisplayed()) {
				click2.click();
				test.isAlertPresent(10);
				Alert a1 = driver.switchTo().alert();
				log.info(a1.getText());
				a1.accept();
				click2.click();
				test.isAlertPresent(10);
				a1 = driver.switchTo().alert();
				log.info(a1.getText());
				a1.dismiss();
				return true;
			}
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		return false;
	}
	
	public boolean alert3() {
		try {
			if(click3.isDisplayed()) {
				click3.click();
				test.isAlertPresent(10);
				Alert a1 = driver.switchTo().alert();
				log.info(a1.getText());
				a1.sendKeys("Hello!! This is Sandeep");
				a1.accept();
				return true;
			}
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		return false;
	}
	

	public boolean navigateTofileDownloads() {
		try {
			test.isElementClickable(filedownload, 10);
			if(filedownload.isEnabled()) {
				filedownload.click();
				log.info("Clicked on filedownload option");
				if(textarea.isDisplayed()) {
					return true;
				}
			}
		}catch (Exception e) {
			log.error(e.getMessage());
		}
		return false;
	}
	
	public void doDownload() {
		try {
			test.isElementClickable(textarea, 10);
			textarea.clear();
			textarea.sendKeys("This is Sandeep");
			log.info("Text is sent");
			test.isElementClickable(generate, 10);
			generate.click();
			log.info("Generate button is clicked");
			Thread.sleep(5000);
			log.info("Download wait is done");
		}catch (Exception e) {
			log.error(e.getStackTrace().toString());
		}
	}
}
