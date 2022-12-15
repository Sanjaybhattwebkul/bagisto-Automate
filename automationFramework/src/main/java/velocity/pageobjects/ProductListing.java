package velocity.pageobjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import helpers.productsActions;

public class ProductListing extends productsActions{
	
	WebDriver driver;
	
	public ProductListing(WebDriver driver) {
		super(driver); // initialize driver to parent class constructor
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".alert-dismissible")  //alert alert-success 
	WebElement flashMessage;
	
	
	@FindBy(css="div[class*='card grid-card']")
	List<WebElement> productCard;
	
	By getProductsBy = By.cssSelector(".card-body");
	By addToCartButton = By.cssSelector(".btn-add-to-cart");	//button[class*='btn-add-to-cart']
	
	/*
	 * Add Only the given product to the cart.
	 */
	public void addProductToCart(String ProductName) throws InterruptedException {
		//System.out.println(ProductName);
		WebElement ProName = getProductCardByProductsName(ProductName);
		ProName.findElement(addToCartButton).click();
		waitForWebElementToAppear(flashMessage); 
		Thread.sleep(1000);		
		scrollUp(driver);		
	}
	
	/*
	 * @Object
	 * Add Multiple products to the cart.
	 */
	public CustomerLogin addProductTo(String type, int number) throws InterruptedException {		
		addProductsTo(type,number);	
		return new CustomerLogin(driver);
	}
	
	public void viewProduct(int i) {
		boolean isOutOfStock = 	productCard.get(i).findElement(addToCartButton).isEnabled();	
		if(i==productCard.size()){
			
			System.out.println("ALL PRODUCTS ARE OUT OF STOCK");
			
		}else if(isOutOfStock) {
			
			productCard.get(i).click();	
			
		}  else {
			i=i+1;
			viewProduct(i);
		}
	}
}
