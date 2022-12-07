package Shop.Tests;
import java.io.IOException;

import org.testng.annotations.Test;
import baseComponent.*;
import velocity.pageobjects.ProductListing;

public class GuestCheckout extends BaseTest {
	
	//@test(retryAnalyzer = Retry.class)
	@Test
	public void placeOrder() throws IOException, InterruptedException {
		String productName = "Sunglasses";			
		ProductListing ProductListingObj = launcVelocity();
		ProductListingObj.addProductToCart(productName);	
	}

}
