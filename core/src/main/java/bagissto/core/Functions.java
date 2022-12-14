package bagissto.core;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Functions {

	public static String removeComma(String str) {		
		return str.replaceAll("[^a-zA-Z0-9.]", ""); // remove , from price				 
	}
	
	public static void scrollDown(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		   js.executeScript("window.scrollBy(0,440)");
	}
	
	public static void scrollUp(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		   js.executeScript("window.scrollBy(0,-900)");
	}
	
	public static void configBrowserExe(String fileName) {
		System.getProperty("webdriver.chrome.driver", "user.dir"+ fileName);
		
	}
	
	public static void updateCart(WebDriver driver, int productQuantity,String[] products) throws InterruptedException {
		
		List<WebElement> productNames = driver.findElements(By.xpath("//span[@class='fs20 fw6 link-color']"));
		Thread.sleep(2000);
		int k =0;		
		for(int j=0; j< productNames.size(); j++) {
			k++;	
			String name = productNames.get(j).getText();
			System.out.println(name);
			List<String> productsForUpdate = Arrays.asList(products); // convert array into array list for easy search
			if(productsForUpdate.contains(name)) {
				updateProductsQuantity(driver,productQuantity,j); // Click on + icon 
			}
			if(k == productNames.size()) {
				break;
			}
			driver.findElement(By.xpath("//div[@class='misc']/button")).click(); // click on update cart button
		}
	}
	
	public static void updateProductsQuantity(WebDriver driver, int productQuantity,int j) throws InterruptedException {
		for(int i=0; i<productQuantity; i++) {			
			driver.findElements(By.cssSelector(".increase i")).get(j).click(); // Click on + icon
		}		
	}
	
	public static void adminLogin(WebDriver driver,String[] loginLocators) {
		driver.findElement(By.xpath(loginLocators[1])).sendKeys("admin@example.com");		
		driver.findElement(By.xpath(loginLocators[2])).sendKeys("admin123");		
		driver.findElement(By.xpath("//button[@class='btn btn-xl btn-primary']")).click();
	}
}
