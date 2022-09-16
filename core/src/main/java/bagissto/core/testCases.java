package bagissto.core;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


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
	
	

}
