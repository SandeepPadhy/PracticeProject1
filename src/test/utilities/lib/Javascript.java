package lib;

import java.util.function.Predicate;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Javascript {
	
	private JavascriptExecutor js;
	
	public Javascript (WebDriver driver) {
		this.js= (JavascriptExecutor) driver;
	}
	/**
	 * Performs click on the WebElement
	 * @param ele
	 */
public void click(WebElement ele) {
	this.js.executeScript("arguments[0].click;", ele);
}
/**
 * Scrolls to Element view
 * @param ele
 */
public void scrollToView(WebElement ele) {
	this.js.executeScript("arguments[0].scrollIntoView(true);", ele);
}
/**
 * Fetches the Page Title
 * @return
 */
public String getPageTitle() {
	return this.js.executeScript("return document.title;").toString();
}
/**
 * Fetches the Page URL
 * @return
 */
public String getPageURL() {
	return this.js.executeScript("return document.URL;").toString();
}
/**
 * Fetches the Page Domain
 * @return
 */
public String getPageDomain() {
	return this.js.executeScript("return document.domain;").toString();
}
/**
 * Scrolls to X and Y coordinates
 * @param x
 * @param y
 */
public void getPageDomain(int x , int y) {
	this.js.executeScript("window.scrollBy(" + x + "," + y + ")" );
}

public void scrollToBottom() {
	js.executeScript("window.scrollBy(0,1500)");
}
//We can handle hidden elements by using javaScript executor
//(JavascriptExecutor(driver)).executeScript("document.getElementsByClassName(ElementLocator).click();");

//JS.executeScript("document.getElementById(‘User').value=test.com'");

//public static void waitForPageLoad(WebDriver wdriver,String title) {
//    WebDriverWait wait = new WebDriverWait(wdriver, 60);
//
//// ((JavascriptExecutor) wdriver).executeScript("return document.readyState").equals("complete");
//
//    wait.until(ExpectedConditions.titleIs(title));
//}


}
