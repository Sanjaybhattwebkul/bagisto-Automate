package bagisto.automationFramework;

import java.util.*;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.*;  
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// driver.findElement(By.cssSelector("[routerLink*='cart']")).click();
	@FindBy(xpath = "//div[@id='mini-cart']")
	WebElement cart;

	@FindBy(css = "a[class*='navbar-brand']")
	WebElement logo;

	@FindBy(xpath = "//a[@class='close']")
	WebElement flashMessage;

	@FindBy(css = "a[class*='remove-decoration']")
	WebElement viewCart;

	@FindBy(linkText = "View Shopping Cart")
	WebElement viewShoppingCart;

	@FindBy(xpath = "//span[contains(@aria-label,'November ')]")
	List<WebElement> totalDates;
	
	@FindBy(xpath="//div[contains(@class,'open')]/div/div/div/select")
				   //div[@class='flatpickr-calendar hasWeeks animate arrowTop arrowLeft open']/div/div/div/select
	WebElement monthsDropdown;

	public void clickOnCartIcon() {
		cart.click();
	}

	public void gotoCartPage() {
		viewShoppingCart.click();
	}

	public void waitForElementToAppear(By findBy) {
		WebDriverWait waite = new WebDriverWait(driver, Duration.ofSeconds(5));
		waite.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait waite = new WebDriverWait(driver, Duration.ofSeconds(5));
		waite.until(ExpectedConditions.visibilityOf(findBy));
	}

	public static void scrollDown(WebDriver driver, int to) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + to + ")");
	}

	public static void scrollUp(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-900)");
	}

	public void waiteForElementToDisAppear() throws InterruptedException {
		Thread.sleep(2000);
		// WebDriverWait waite = new WebDriverWait(driver,Duration.ofSeconds(5));
		// waite.until(ExpectedConditions.invisibilityOf(element));
	}

	public void goToHomePage() {
		logo.click();

	}

	public void closeFlashMessage() {
		flashMessage.click();
	}

	public static String removeComma(String str) {
		return str.replaceAll("[^a-zA-Z0-9.]", ""); // remove , from price
	}

	public String getSubTotalOfCartSummary(WebElement cartSummarySubTotal) {
		return cartSummarySubTotal.getText();
	}

	public void handleCalendarDate(String date,String currentMonth) throws InterruptedException {
		monthsDropdown.click();
		Select selectMonth = new Select(monthsDropdown); 	// select the current moth from calendar			
		selectMonth.selectByVisibleText(currentMonth); 	
		
		// Select the given date
		int count = totalDates.size(); // count total dates on calendar
		for (int i = 0; i < count; i++) {
			String text = totalDates.get(i).getText(); // get text of dateBox
			if (text.equalsIgnoreCase(date)) {
				totalDates.get(i).click(); // click on dateBox
				System.out.println(text);
				break;
			}
		}
	}

	/*
	 * Get current date
	 */
	public String getDate(String type) {
		SimpleDateFormat formatter = new SimpleDateFormat(type);
		return formatter.format(new Date());

	}
	
	/*
	 * Get After Date
	 */
	public String getAfterDate(String dateBefore) {
		SimpleDateFormat beforeDate = new SimpleDateFormat(dateBefore);
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(beforeDate.parse(dateBefore));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.add(Calendar.DAY_OF_MONTH, 3); // date after 3 days
		String dateAfter = beforeDate.format(cal.getTime());
		System.out.println(dateAfter + " is the date after adding 3 days.");
		return dateAfter;
		// TODO get day from date and return

	}
	
	/**
	 * 
	 * This method will set any parameter string to the system's clipboard.
	 * 
	 */

	public static void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

	}
	
	/**
	 * 
	 * Upload the image from local system
	 * 
	 */
	public static void uploadFile(String fileLocation) {

		try {

			// Setting ClipBoard with file location
			setClipboardData(fileLocation);
			// native key strokes for CTRL, V and ENTER keys
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

		} catch (Exception exp) {
			exp.printStackTrace();

		}

	}

}
