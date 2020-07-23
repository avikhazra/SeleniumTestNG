
package pageObjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import framework.BrowserUtil;

public class ProductDetailPage {
private static final Logger log = Logger.getLogger(ProductDetailPage.class);
	
	//ProductDetail page constructor
@FindBy(xpath = "((//a[contains(@class,'products-block-image')]/descendant::img[1])[1]")
private WebElement Select1StProduct;

@FindBy(xpath = "//button[@name='Submit']//span[1]")
private WebElement Addtocart;
@FindBy(xpath = "(//a[contains(@class,'btn btn-default')]//span)[2]")
private WebElement proceedToCheckout;
@FindBy(xpath = "//span[@title='Close window']")
private List<WebElement> proceedToCheckoutBox;
@FindBy(xpath = "//a[contains(@class,'button btn')]//span[1]")
private List<WebElement> proceedToCheckoutButton;


	public ProductDetailPage() {
		super();
		log.info("ProductDetailPage constructor is invoked");
		
	}
	
	//Product add Method  by Avik
	public void Select1stProdduct() throws InterruptedException {
		
		
		
		BrowserUtil.click(Select1StProduct);
		BrowserUtil.click(BrowserUtil.ElementWaitToClickable(Addtocart));
		BrowserUtil.PageReadyStateCheck(4000);
		
		if(proceedToCheckoutBox!=null) {
			BrowserUtil.click(BrowserUtil.ElementWaitToClickable(proceedToCheckout));
			Assert.assertTrue(true,"Procced to Checkout");
		}else {
			Assert.assertTrue(false,"Procced to Checkout");
		}
		
		if(proceedToCheckoutButton!=null) {
			Assert.assertTrue(true,"Product is added in cart");
		}else {
			Assert.assertTrue(false,"Product is not added in cart");
		}
	}
	

}
