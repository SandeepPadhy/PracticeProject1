package com.goibibo.flights;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import lib.Javascript;
import lib.PropertiesReader;
import lib.WrapperTest;

public class HomePage {

	private static WebDriver driver;
	private PropertiesReader pr = new PropertiesReader();
	private WrapperTest test;
	private Javascript js;
	private Actions act;
	private String origin = "Sao Paulo (GRU)";// "Sydney Airport";
	private String origincode = "Sao Paulo (GRU)";// "Sydney (BWU)";
	private String destination = "";

	@Test(enabled=true,alwaysRun=true , groups= {"08"})
	public void flightBookingPage() {
		try {
			driver.get(pr.getOdlProperties("url"));
			System.out.println("Page Tile is : " + driver.getTitle());
			driver.findElement(By.id(pr.getOdlProperties("fromTextboxID"))).clear();
			driver.findElement(By.id(pr.getOdlProperties("fromTextboxID"))).sendKeys("S");
			Thread.sleep(2000);
			// WebElement ele =
			// driver.findElement(By.xpath("//input[@id='gosuggest_inputSrc']//parent::div/section"));
			// String a = ele.getAttribute("innerHTML");
			// String a=(String)js.executeScript("return arguments[0].innerHTML;", ele);
			// System.out.println(a);
			List<WebElement> li = driver
					.findElements(By.xpath("//input[@id='gosuggest_inputSrc']//parent::div/section//li"));
			for (WebElement temp : li) {
				String x = temp.getText();
				System.out.println(x);
				if (x.contains(origin)) {
					System.out.println("clicking....");
					js.scrollToView(temp);
					act.moveToElement(temp).click().build().perform();
					break;
				}
			}
			String to = driver.findElement(By.id(pr.getOdlProperties("fromTextboxID"))).getAttribute("value");
			assertEquals(origincode, to);
			// Select Class
			Select sel = new Select(test.findElementByID(pr.getOdlProperties("classSelectID")));
			System.out.println(sel.isMultiple());
//			Thread.sleep(2000);
			sel.selectByVisibleText("Business");
//			Thread.sleep(2000);
			sel.selectByValue("W");
//			Thread.sleep(2000);
			sel.selectByIndex(2);
//			Thread.sleep(2000);
			// System.out.println(sel.getFirstSelectedOption());
			// sel.deselectAll();
			List<WebElement> ele1 = sel.getOptions();
			for (WebElement e1 : ele1) {
				System.out.println(e1.getText());
			}

			// Webtables use in Calendar
			String month1 = "September";
			String month2 = "November";
			int day1 = 5;
			int day2 = 9;
//			test.findElementByXpath(pr.getOdlProperties("departCalenderXPATH")).clear();
			test.findElementByXpath(pr.getOdlProperties("departCalenderXPATH")).click();
			dateSelect(month1,day1);
			String departDate=test.findElementByXpath(pr.getOdlProperties("departCalenderXPATH")).getAttribute("value");
			System.out.println(departDate);
			if(departDate.contains(month1.substring(0, 3)) && departDate.contains(Integer.toString(day1))) {
				System.out.println("depart date is correctly selected");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//A recursive function
	public void dateSelect(String month1, int day1) throws FileNotFoundException {
		boolean foo = true;
		List<WebElement> li2 = driver.findElements(By.className("DayPicker-Caption"));
		for (WebElement e2 : li2) {
			if (e2.getText().contains(month1)) {
				foo = false;
				System.out.println("Got the month");
				List<WebElement> dates = driver.findElements(By.xpath(pr.getOdlProperties("valid_date_1_xpath")+month1+pr.getOdlProperties("valid_date_2_xpath")));
						for (WebElement d: dates) {
							if(d.getText().equals(Integer.toString(day1)))
									{
								d.click();
								break;
							}
						}
						break;
			}
//			System.out.println("No");
		}
		if (foo) {
			test.findElementByXpath(pr.getOdlProperties("Calendar_next_xpath")).click();
			test.findElementByXpath(pr.getOdlProperties("Calendar_next_xpath")).click();
			dateSelect(month1, day1); // Recursion
		}
			
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
		this.driver = new FirefoxDriver();
		test = new WrapperTest(driver);
		act = new Actions(driver);
		js = new Javascript(driver);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@AfterClass
	public void afterClass() throws InterruptedException {
//		Thread.sleep(10000);
//		driver.close();
	}

}
