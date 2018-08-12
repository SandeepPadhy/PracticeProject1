package seleniumEasy;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import lib.BaseTest;
import lib.Javascript;
import lib.LocalDriverFactory;
import lib.LogFactory;

public class InputForms extends BaseTest {
	//	private static Logger log = LogManager.getLogger(InputForms.class.getName());
	WebDriver driver;
	InputFormsPageFactory pf;
	protected SoftAssert sa ;
	String str1 = "Simple Form Demo";
	String str2 = "Checkbox Demo";
	String str3 = "Radio Buttons Demo";
	String str4 = "Select Dropdown List";
	String str5 = "Input Form Submit";
	String str6 = "Ajax Form Submit";
	String str7 = "JQuery Select dropdown";
	String URL = "http://www.seleniumeasy.com/";
	String msg = "Hi, This is Sandeep";
	String a="10";
	String b= "22.22";
	
	@BeforeClass
	public void preBoot() throws IOException {
		driver= fetchDriver();
		System.out.println("Driver fetched");
		 pf = new InputFormsPageFactory(driver);
		sa= new SoftAssert();
	}
	
	@BeforeMethod (alwaysRun=true)
	public void loadSeleniumEasyDemoSite() throws Exception{
		try {
		System.out.println("Hitting Selenium Easy |");
		driver.get(URL);
		Reporter.log("Navigation to Selenium Easy was successful |");
		pf.navigateToDemoSite();
		Reporter.log("Navigated to Demo Site Successfully |");
		}
catch(Exception e) {
	e.printStackTrace();
}
	}

	@Test(priority=1,enabled=true , alwaysRun=true,groups= {"input"})
	public void validateSimpleFormDemo() {
		System.out.println("p1");
		pf.clickDropdown();
		pf.selectFromDropdown(str1);
		Reporter.log("Selected "+str1+" from the dropdown list succesfully |");
		//validating SingleInput
		pf.enterMessage(msg);
		Reporter.log("Entered "+msg+" into the testbox succesfully |");
		pf.clickShowMsg();
		Reporter.log("Clicked Show Message button succesfully |");
		assertEquals(msg, pf.getMsg());
		Reporter.log("Message is Verified |");
		//Validating MultipleInput
		pf.sum1(a);
		Reporter.log("Entered the sum1 value Succesffully | ");
		pf.sum2(b);
		Reporter.log("Entered the sum2 value Succesffully |");
		pf.clickTotal();
		Reporter.log("Clicked the Total button Succesffully |");
		assertEquals(Integer.toString((int)(Double.parseDouble(a)+Double.parseDouble(b)) ),pf.getTotal());
		Reporter.log("Total is verified");
	}
	
	@Test(priority=2,enabled=true,alwaysRun=true,groups= {"input"})
	public void validateCheckboxDemo() {
		System.out.println("p2");
		pf.clickDropdown();
		pf.selectFromDropdown(str2);
		//validating single checkbox
		pf.clickCheckbox();
		assertEquals("Success - Check box is checked", pf.fetchSuccessMsg());
		pf.clickMultiCheckBox("Option 3");
		pf.clickCheckBtn();
		pf.clickCheckBtn();
	}
	@Test(priority=3,enabled=true,alwaysRun=true,groups= {"input"})
	public void validateRadioButtonDemo() {
		System.out.println("p3");
		pf.clickDropdown();
		pf.selectFromDropdown(str3);
		//Radio button validation
		assertEquals( pf.clickGetCheckedValueBtn(),"Radio button is Not checked");
		pf.selectMaleFemale("Male");
		assertEquals( pf.clickGetCheckedValueBtn(),"Radio button 'Male' is checked");
	}
	
	@Test(priority=4 , enabled =true,alwaysRun=true , groups= {"input"})
	public void validateDropDownDemo() throws InterruptedException {
		System.out.println("p4");
		pf.clickDropdown();
		pf.selectFromDropdown(str4);
		String day=pf.singleSelectDropDownByVisbibleText("Wednesday");
		assertEquals("Day selected :- Wednesday", day);
		//MultiSelect
		String[] str = {"Florida","New York","Ohio"} ;
		pf.multiSelectDropDown(str);
		Thread.sleep(5000);		
		sa.assertEquals( pf.clickFirstSelected(),"First selected option is : Florida");
		Thread.sleep(5000);
//		assertEquals( pf.clickAllSelected(),"Options selected are : Florida,New York,Ohio");
//		pf.multiDeselect();
		Thread.sleep(5000);
//		assertEquals("First selected option is : undefined", pf.clickFirstSelected());
//		assertEquals("Options selected are : ", pf.clickAllSelected());
		}	


}
