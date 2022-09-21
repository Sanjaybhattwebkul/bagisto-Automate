package bagissto.core;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.testng.Assert;
import dev.failsafe.internal.util.Assert;



public class testCases {

	public static void configBrowserExe(String fileName) {
		System.getProperty("webdriver.chrome.driver", "user.dir"+ fileName);
		
	}
	
	public static void openBrowser(WebDriver driver, String URL) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize(); 
		driver.get(URL);
	}
	
	public static void scrollDown(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		   js.executeScript("window.scrollBy(0,440)");
	}
	
	public static void scrollUp(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		   js.executeScript("window.scrollBy(0,-900)");
	}
	
	public static void addToCart(WebDriver driver, String[] cartProducts,String flashMessage) {		
		int j=0; 
		scrollDown(driver);
		List<WebElement> products = driver.findElements(By.cssSelector("div.product-name")); // products array
	
		for (int i = 1; i<products.size(); i++) {		
			String formattedName =products.get(i).getText();	// get productName from productCard	
			List itemForAddToCart = Arrays.asList(cartProducts); //Convert stringArray to arrayList	
			if (itemForAddToCart.contains(formattedName)) {
				j++;
				//click on add to cart button				
				driver.findElements(By.xpath("//div[@class='add-to-cart-btn pl0']/form/button")).get(i).click();
				driver.findElement(By.xpath(flashMessage)).click();	//close flash message
				if(j == cartProducts.length) {
					break;
				}
			}
		}		
	}
	
	public static void customerLogin(WebDriver driver,String Locator,String[] loginCredentials) throws InterruptedException {
		scrollUp(driver);
		driver.findElement(By.cssSelector(Locator)).click();
		driver.findElement(By.xpath("//a[@class='theme-btn fs14 fw6'][1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(loginCredentials[0]);		
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(loginCredentials[1]);
		driver.findElement(By.cssSelector("input[type='submit']")).click();			
	
	}
	
	public static void veryfyMiniCartPrice(WebDriver driver,String xPath) {		
		driver.findElement(By.xpath("//div[@id='mini-cart']")).click();
		List<WebElement> miniCartmount = driver.findElements(By.xpath(xPath));
		int actualAmount=0;
		for(int i=0; i<miniCartmount.size(); i++) {
			
			//convert string to double
			 double itemAmount = Double.parseDouble(miniCartmount.get(i).getText().substring(1));
			 // convert into int
			 int intsTotalAmount = (int)itemAmount;	
			 System.out.println("Integer - " + intsTotalAmount);
			 actualAmount =actualAmount + intsTotalAmount;
		}
		System.out.println(actualAmount);
		double total = Double.parseDouble(driver.findElement(By.cssSelector(".modal-footer h5:nth-child(2)")).getText().substring(1));
		 // convert into int
		 int grandTotalAmount = (int)total;	
		 System.out.println("Grand Integer - " + grandTotalAmount);
		//System.out.println(ExpectAmount);
		//Assert.assertEquals(ExpectAmount,actualAmount); // check if(givenAmout==sum);
		
		
           
	}
}
