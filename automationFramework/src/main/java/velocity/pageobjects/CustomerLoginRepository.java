package velocity.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import abstraction.AbstractComponent;

public class CustomerLoginRepository extends AbstractComponent {
	
	WebDriver driver;
	
	public CustomerLoginRepository(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.cssSelector("div[class*='welcome-content']")).click();
	@FindBy(css="div[class*='welcome-content']")
	WebElement loginPoPup;
	
	//driver.findElement(By.xpath("//a[@class='theme-btn fs14 fw6'][1]")).click();
	@FindBy(xpath="//a[@class='theme-btn fs14 fw6'][1]")
	WebElement loginPopButton;
	
	@FindBy(name="email")
	WebElement userEmail;
	
	@FindBy(id="password")
	WebElement passwordElement;	
	
	@FindBy(css="input[type='submit']")
	WebElement loginButton;
	
	@Test
	public void goToLoginPage() {
		//closeFlashMessage();
		loginPoPup.click();
		loginPopButton.click();
	}
	
	//@Test(dependsOnMethods= {"goToLoginPage"})
	/*public LandingPage customerLogin(HashMap<String, String> input) throws InterruptedException {
		userEmail.sendKeys(input.get("email"));
		passwordElement.sendKeys(input.get("password"));
		loginButton.click();
		return new LandingPage(driver);
	}
	*/
	public MiniCarRepository customerLogin(String email, String Password) throws InterruptedException {
		userEmail.sendKeys(email);
		passwordElement.sendKeys(Password);
		loginButton.click();
		return new MiniCarRepository(driver);
	}
	
}
