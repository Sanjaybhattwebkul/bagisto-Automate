package admin.pageobjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstraction.AbstractComponent;

public class SalesRepository extends AbstractComponent {
	
	WebDriver driver;
	public SalesRepository(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tbody/tr[1]/td[9]/div/a")
	 WebElement viewOrder;
	
	@FindBy(css="a[class*='btn btn-lg btn-primary']:nth-child(3)")
	WebElement shipButton;
	
	@FindBy(css="select[name*='shipmen']")	
	WebElement selectSource;
	
	@FindBy(css="div[class='page-action'] :first-child")
	WebElement saveShipMentButton;
	
	@FindBy(xpath="//tbody/tr")
	List<WebElement> orderTable;
	
	@FindBy(className = "next")
	WebElement nextPage;
	
	By status = By.xpath("//td[@data-value='Status']/span");
	
	
	public void createShipping() {
		salesIcon.click();
		//System.out.println("inside fun"+checkOrderStatus().get("status"));
		if(checkOrderStatus().get("status")==1) {
			int column = checkOrderStatus().get("column");			
			driver.findElement(By.xpath("//tbody/tr["+column+"]/td[9]/div/a")).click();
			viewOrder.click();
			shipButton.click();
			scrollDown(driver,950);
			selectOptions(selectSource,"Default");
			saveShipMentButton.click();
		}else {
			System.out.println("No Pending Order");
		}
		
	}
	
	public Map<String, Integer> checkOrderStatus() {
		
		Map<String, Integer> orderStatus = new HashMap<String, Integer>();
		int i;
		for(i=0; i<10; i++) {
			//System.out.println(orderTable.get(i).findElement(status).getText());
			/*if(i==9) {
				nextPage.click();
			}else */
				if(orderTable.get(i).findElement(status).getText() == "Pending")
				{
					System.out.println("true");
					/*orderStatus.put("status", 1);
					orderStatus.put("column", i+1);	
					System.out.println("after loop i="+i+"and"+orderTable.get(i).findElement(status).getText());
					*/
					break;
				}else{
					System.out.println("after loop i= "+i+" and  status"+orderTable.get(i).findElement(status).getText());
					System.out.println("false");
					continue;
				}
		}
		return orderStatus;
	}

}
