package bagissto.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class index extends testCases{

	public static void main(String[] args) throws InterruptedException {
		configBrowserExe("/chromedriver");
		WebDriver driver = new ChromeDriver();
		openBrowser(driver,"http://192.168.15.237/bagisto-demo/public/");
		scrollDown(driver);
		
	}

}
