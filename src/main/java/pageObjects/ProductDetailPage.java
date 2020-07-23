
package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import framework.BasePage;
import framework.BrowserUtil;

public class ProductDetailPage extends BasePage{
	private static final Logger log1 = Logger.getLogger(ProductDetailPage.class);
	public ProductDetailPage(WebDriver driver) {
		super(driver);		
	}
private static final Logger log = Logger.getLogger(HomePage.class);
@FindBy(xpath= "(//a[contains(@class,'products-block-image content_img')]//img)[1]")
private WebElement Select1StProduct;


@FindBy(xpath="//p[@id='add_to_cart']/button[@name='Submit']/span")
private WebElement AddtoCart;
@FindBy(xpath= "//span[@class='cross']")
private List<WebElement> proceedToCheckoutBox;

@FindBy(xpath = "//span[normalize-space(text()) = 'Proceed to checkout']")
private WebElement proceedToCheckout;
@FindBy(xpath="//a[contains(@class,'button btn')]/span[1]")
private List<WebElement> proceedToCheckoutButton;



		public  void Select1stProdduct() throws InterruptedException {
			BrowserUtil.click(BrowserUtil.ElementWaitToClickable(Select1StProduct));
			BrowserUtil.PageReadyStateCheck(4000);
			BrowserUtil.click(AddtoCart);
			if(proceedToCheckoutBox!=null) {
				BrowserUtil.click(BrowserUtil.ElementWaitToClickable(proceedToCheckout));
				Assert.assertTrue(true);
			}else {
				Assert.assertTrue(false);
			}
			BrowserUtil.PageReadyStateCheck(4000);
		if(proceedToCheckoutButton!=null) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
			   

		
		}
		
		
	
	
}
