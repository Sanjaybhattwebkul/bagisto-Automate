package velocity.pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import helpers.getProductsPrice;

public class SearchProductRepository extends getProductsPrice{

	WebDriver driver;
	public SearchProductRepository(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="term")
	WebElement searchBox;
	
	@FindBy(xpath="//span[@class='special-price']")
	WebElement productsPrice;
	
	@FindBy(css="div[class*='card grid-card']")
	WebElement productCard;
	
	
	
	public double searchProduct(String productName) {
		searchBox.sendKeys(productName,Keys.ENTER);
		boolean isproductFound = productCard.isDisplayed();
		if(isproductFound) {
			return getActualPrice(productsPrice.getText());
		}
		return (Double) null;
	}
}
