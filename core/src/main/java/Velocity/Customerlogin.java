package Velocity;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Customerlogin extends bagissto.core.testCases{
	
	@Test
	public static void Login() throws InterruptedException {
			
		
		String[] loginCredentials= {"tom@example.com","tom123"};		
		String[] loginLocators = {"div[class*='welcome-content']","//input[@name='email']","//input[@name='password']"};
		configBrowserExe("/chromedriver"); //geckodriver		
		WebDriver driver = new ChromeDriver(); //new FirefoxDriver();		
		openBrowser(driver,"http://192.168.15.237/sanjay-bagisto/public/");		
		Thread.sleep(2000);		
		customerLogin(driver,loginLocators,loginCredentials); // customer Login	
		
	}

}
