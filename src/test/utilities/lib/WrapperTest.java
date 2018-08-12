package lib;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WrapperTest {

	private WebDriver driver;
	private WebElement element;
	private Alert alert;
	private WebDriverWait wait;

	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public WrapperTest(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Basic findElements
	 * 
	 * @param Locator
	 * @return
	 */
	public WebElement findElementByID(String Locator) {
		return this.driver.findElement(By.id(Locator));
	}

	public WebElement findElementByName(String name) {
		return this.driver.findElement(By.name(name));
	}

	public WebElement findElementByClass(String className) {
		return this.driver.findElement(By.className(className));
	}

	public WebElement findElementByTag(String tagName) {
		return this.driver.findElement(By.tagName(tagName));
	}

	public WebElement findElementByXpath(String xpathExpression) {
		return this.driver.findElement(By.xpath(xpathExpression));
	}

	public WebElement findElementByLinkText(String linkText) {
		return this.driver.findElement(By.linkText(linkText));
	}

	public WebElement findElementByPartialLinkText(String partialLinkText) {
		return this.driver.findElement(By.partialLinkText(partialLinkText));
	}

	public WebElement findElementByCss(String Locator) {
		return this.driver.findElement(By.id(Locator));
	}

	/**
	 * Alert methods
	 */

	public void alertSubmit(String text) {
		if (this.driver != null)
			this.driver.switchTo().alert().sendKeys(text);

	}

	public void alertAccept() {
		if (this.driver != null)
			this.driver.switchTo().alert().accept();
	}

	public void alertDismiss() {
		if (this.driver != null)
			this.driver.switchTo().alert().dismiss();
	}

	public String alertText() {
		if (this.driver != null)
			return this.driver.switchTo().alert().getText();
		else
			return null;
	}

	/**
	 * Wait Methods
	 */

	public void implicitWait(int timeunit) {
		this.driver.manage().timeouts().implicitlyWait(timeunit, TimeUnit.SECONDS);
	}

	public void pageloadWait(int timeunit) {
		this.driver.manage().timeouts().pageLoadTimeout(timeunit, TimeUnit.SECONDS);
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 */
	public void isElementPresent(By locator, int timeunit) {
		this.wait = new WebDriverWait(this.driver, timeunit);
		this.element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 */
	public void isElementVisible(By locator, int timeunit) {
		this.wait = new WebDriverWait(this.driver, timeunit);
		this.element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 */
	public void isElementClickable(WebElement ele, int timeunit) {
		this.wait = new WebDriverWait(this.driver, timeunit);
		this.element = wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	/**
	 * An expectation for checking if the given element is selected.
	 */
	public void isElementClickable(By locator, int timeunit) {
		this.wait = new WebDriverWait(this.driver, timeunit);
		this.element = wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * An expectation for checking if the given element is selected.
	 */
	public boolean isElementSelected(WebElement ele, int timeunit) {
		this.wait = new WebDriverWait(this.driver, timeunit);
		return wait.until(ExpectedConditions.elementToBeSelected(ele));
	}

	/**
	 * An expectation for checking if the given element is selected.
	 */
	public boolean isElementSelected(By locator, int timeunit) {
		this.wait = new WebDriverWait(this.driver, timeunit);
		return wait.until(ExpectedConditions.elementToBeSelected(locator));
	}

	public void isAlertPresent ( int timeunit) {
		this.wait = new WebDriverWait(this.driver, timeunit);
		 wait.until(ExpectedConditions.alertIsPresent());
	}
	
	// Form Elements
	
	public void radioSelect(WebElement ele) {
		if(ele.isSelected()) {
			//Do nothing
		}else {
			ele.click();
		}
	}
	
	public void waitForPageLoad(WebDriver wdriver,String title) {
	    WebDriverWait wait = new WebDriverWait(wdriver, 10);

	// ((JavascriptExecutor) wdriver).executeScript("return document.readyState").equals("complete");

	    wait.until(ExpectedConditions.titleIs(title));
	}
	
}
