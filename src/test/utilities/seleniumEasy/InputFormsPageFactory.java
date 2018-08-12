
package seleniumEasy;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import lib.Javascript;
import lib.LogFactory;
import lib.WrapperTest;

public class InputFormsPageFactory {
	private WebDriver driver;
	private Actions act;
	private Javascript js;
	private WrapperTest test = new WrapperTest(driver);
	private LogFactory log=new LogFactory(InputFormsPageFactory.class.getName());
//	private static Logger log = LogManager.getLogger(InputForms.class.getName() );

	@FindBy(linkText = "Demo Website!")
	private WebElement linkToDemoSite;

	@FindBy(xpath = "//a[contains(text(),'Input Forms') and @class='dropdown-toggle']")
	private WebElement InputFormDropDown;

	@FindBy(xpath = "//a[contains(text(),'Input Forms') and @class='dropdown-toggle']//following-sibling::ul/li")
	private List<WebElement> InputFormList;

	@FindBy(id = "user-message")
	private WebElement msg;

	@FindBy(xpath = "//button[text()='Show Message']")
	private WebElement showMessage;

	@FindBy(xpath = "//div[@id='user-message']/span")
	private WebElement response1;

	@FindBy(id = "sum1")
	private WebElement sum1;

	@FindBy(id = "sum2")
	private WebElement sum2;

	@FindBy(xpath = "//button[text()='Get Total']")
	private WebElement getTotal;

	@FindBy(xpath = "//label[text()=' Total a + b = ']//parent::div//span")
	private WebElement total;
	
	@FindBy(id="isAgeSelected")
	private WebElement firstCheckbox;
	
	@FindBy(id="txtAge")
	private WebElement successMsg;
	
	@FindBy(how=How.CLASS_NAME , using="cb1-element")
	private List <WebElement> multiCheckbox;
	
	@FindBy(id="check1")
	private WebElement checkBtn;
	
	@FindBy(name="optradio")
	private List<WebElement> radioMF;
	
	@FindBy(id="buttoncheck")
	private WebElement getValue;
	
	@FindBy(className="radiobutton")
	private WebElement radiotext;
	
	@FindBy(id="select-demo")
	private WebElement singleDrop;

	@FindBy(className="selected-value")
	private WebElement selectedValue;
	
	@FindBy(id="multi-select")
	private WebElement multiSelect;
	
	@FindBy(xpath="//button[@id='printMe']")
	private WebElement selectFirst;
	
	@FindBy(id="printAll")
	private WebElement selectAll;
	
	@FindBy (className="getall-selected")
	private WebElement selectTxt;
	
	String message=null;
	String tot=null;
	
	public InputFormsPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void navigateToDemoSite() {
		try {
		linkToDemoSite.click();
		log.info("Demo site Link is clicked");
		}catch(Exception e){
			log.error(e.getMessage());
		}
	}

	public void clickDropdown() {
		try {
		InputFormDropDown.click();
		log.info("Input Form dropdown is clicked");
		}catch(Exception e){
			log.error(e.getMessage());
		}
	}

	public void selectFromDropdown(String str) {
		try {
			boolean flag=true;
		for (WebElement ele : InputFormList) {
			if (ele.getText().contains(str)) {
				ele.click();
				flag=false;
				log.info(str+" is selected ");
				break;
			} 
		}if(flag)
			log.trace("No Matching String found in the DropDown for: "+str);
		}catch(Exception e){
			log.trace(e.getMessage());
		}
	}

	public void enterMessage(String msg) {
		try {
		this.msg.sendKeys(msg);
		log.info("Message: "+ msg +" passed to the testbox");
	}catch(Exception e){
		log.error(e.getMessage());
	}
	}	

	public void clickShowMsg() {
		try {
		showMessage.click();
		log.info("Show Message button is clicked");
		}catch(Exception e){
			log.error(e.toString());
			
		}
	}

	public String getMsg() {
		try {
		message=response1.getText();
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return message;
			}
	
	

	public void sum1(String a) {
		try {
		sum1.sendKeys(a);
		log.info("Value: "+ a +" passed to the A textbox");
		}catch(Exception e){
			log.error(e.getMessage());
		}
	}

	public void sum2(String b) {
		try {
		sum2.sendKeys(b);
		log.info("Value: "+ b +" passed to the B textbox");
		}catch(Exception e){
			log.error(e.getMessage());
		}
	}

	public void clickTotal() {
		try {
		getTotal.click();
		log.info("Total is clicked");}
		catch(Exception e){
			log.error(e.getMessage());
		}
	}

	public String getTotal() {
		try {
		tot= total.getText();
		}catch(Exception e){
			log.error(e.getMessage());
		}
			return tot;
	}
	
	public void clickCheckbox() {
		if (firstCheckbox.isSelected()) {
			firstCheckbox.click();
			log.info("Check Box is unchecked");
		}else {
			firstCheckbox.click();
			log.info("Check Box is checked");
		}
	}
	
	public String fetchSuccessMsg() {
		if(successMsg.isDisplayed()) {
			log.info("Message is Displayed");
			return successMsg.getText();
		}
		return null;
	}
	
	public void clickCheckBtn() {
		if(checkBtn.getAttribute("value").equals("Check All")) {
			checkBtn.click();
			log.info("Check All Button is clicked");
		}else if (checkBtn.getAttribute("value").equals("Uncheck All")) {
			checkBtn.click();
			log.info("Uncheck All Button is clicked");
		}
	}
	
	public void clickMultiCheckBox(String option) {
		for (WebElement e : multiCheckbox) {
			if(e.getText().equals(option)) {
				e.click();
				log.info(e.getText()+"  is clicked");
				break;
			}
		}
	}
	
	public void selectMaleFemale(String str) {
		try {
		for(WebElement ele:radioMF) {
			if(ele.getAttribute("value").equals(str)) {
				test.radioSelect(ele);
				log.info("RadioBtn "+ele.getText()+" is selected");
				break;
			}
		}
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		
	}
	
	public String clickGetCheckedValueBtn() {
		try {
		getValue.click();
		log.info("GetCheckedValue Button is clicked");
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		return radiotext.getText();
		
		}
	
	public String singleSelectDropDownByVisbibleText(String str) {
		Select sel = new Select(singleDrop);
		sel.selectByVisibleText(str);
		log.info(selectedValue.getText()+" is selected");
		return selectedValue.getText();
	}
	
	public void multiSelectDropDown(String[] value) {
//		act= new Actions(driver);
//		act.keyDown(Keys.CONTROL).build().perform();
		Select sel = new Select(multiSelect);
		for(String str:value) {
			sel.selectByVisibleText(str);
			log.info(str+" is selected");
		}
		for (WebElement ele :sel.getAllSelectedOptions()) {
			System.out.println(ele.getText()+" is selected: "+ele.getAttribute("selected"));
		}
//		act.keyUp(Keys.CONTROL).build().perform();
	}
	
	public void multiDeselect() {
		Select sel =new Select (multiSelect);
		sel.deselectAll();
		log.info("All option are deselected");
	}
	
	public String clickFirstSelected() {
		try {
			js= new Javascript(driver);
			js.scrollToBottom();
			selectFirst.click();
				log.info("FirstSelected Button is clicked");
				if(selectTxt.isDisplayed())
				System.err.println(selectTxt.getText());
				else
					System.out.println("no data");
			}catch(Exception e) {
				log.error(e.getMessage());
			}
				return selectTxt.getText();
	}
	
	public String clickAllSelected() {
		act= new Actions(driver);
		act.moveToElement(selectAll).click().build().perform();
		selectAll.click();
		log.info("allSelected Button is clicked");
		return selectTxt.getText();
	}
}
