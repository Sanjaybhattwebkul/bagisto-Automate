package bagissto.core;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


//import org.openqa.selenium.firefox.FirefoxDriver;

public class index extends testCases{

	public static void main(String[] args) throws InterruptedException {
		String[] cartProducts = {"Sunglasses","Men's Polo T-shirt"}; // products for add to cart.	
		String[] miniCartLocators = {"//div[@id='mini-cart']","//span[@class='card-total-price fw6']",".modal-footer h5:nth-child(2)"}; // products for add to cart.	
		String[] loginCredentials= {"tom@example.com","tom123"};
		String FlashMessage = "//a[@class='close']";
		String[] loginLocators = {"div[class*='welcome-content']","//input[@name='email']","//input[@name='password']"};
		configBrowserExe("/chromedriver"); //geckodriver		
		WebDriver driver = new ChromeDriver(); //new FirefoxDriver();		
		openBrowser(driver,"http://192.168.15.237/sanjay-bagisto/public/");		
		Thread.sleep(2000);
		addToCart(driver,cartProducts,FlashMessage); // add-Product-to-cart
		customerLogin(driver,loginLocators,loginCredentials); // customer Login	
		veryfyMiniCartPrice(driver,miniCartLocators);		
		//VerifyShoppingCart(driver,cartProducts);
		verifyOrdersFilter(driver,FlashMessage); //TODO FIX
		createSimpleProduct(driver,loginLocators);
		
	}

}
