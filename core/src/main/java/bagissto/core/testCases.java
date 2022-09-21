package bagissto.core;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



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
	
	public static void customerLogin(WebDriver driver,String Locator,String[] loginCredentials) {
		scrollUp(driver);
		driver.findElement(By.cssSelector(Locator)).click();
		driver.findElement(By.xpath("//a[@class='theme-btn fs14 fw6'][1]")).click();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(loginCredentials[0]);		
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(loginCredentials[1]);
		driver.findElement(By.cssSelector("input[type='submit']")).click();			
	
	}
	
	public static void veryfyMiniCartPrice(WebDriver driver,String xPath) {		
		driver.findElement(By.xpath("//div[@id='mini-cart']")).click();
		List<WebElement> tdText = driver.findElements(By.xpath(xPath));		
		int sum=0;
		for(int i=0; i<tdText.size(); i++) {
			// convert string to integer			
			sum =sum + Integer.parseInt(tdText.get(i).getText().substring(1));
		}
		
		System.out.println(sum);		
           
	}
}
