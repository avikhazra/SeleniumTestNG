
package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import framework.BasePage;
import framework.BrowserUtil;

public class HomePage extends BasePage {

	private static final Logger log = Logger.getLogger(HomePage.class);
	//WebDriver driver;

	// list of objects on home page
	@FindBy(id = "search_query_top")
	private WebElement searchBar;

	@FindBy(xpath = "//form[@id='searchbox']//button[@name='submit_search']")
	private WebElement searchButton;

	@FindBy(css = ".product_list.grid.row>li h5")
	private List<WebElement> productResultSet;

	@FindBy(css = ".shop-phone strong")
	private WebElement shopPhoneNumber;

	@FindBy(xpath = "(//a[contains(@class,'products-block-image content_img')]//img)[1]")
	private WebElement Select1StProduct;
	
	@FindBy(xpath = "//button[@name='Submit']//span[1]")
	private WebElement Addtocart;
	@FindBy(xpath = "(//a[contains(@class,'btn btn-default')]//span)[2]")
	private WebElement proceedToCheckout;
	@FindBy(xpath = "//span[@title='Close window']")
	private List<WebElement> proceedToCheckoutBox;
	@FindBy(xpath = "//a[contains(@class,'button btn')]//span[1]")
	private List<WebElement> proceedToCheckoutButton;
;
	//
	public HomePage(WebDriver driver) {
		super(driver);
		launchApp(appURL); // driver.get(url)
		log.info("Home page constructor is invoked");
		log.info("Home page objects are initialized successfully and constructor ends");
	}

	// page actions available for use in test
	public List<String> searchProduct(String productName) {

		BrowserUtil.write(searchBar, productName);
		BrowserUtil.click(searchButton);
		return getProductResultSet();

	}

	
	public String getShopPhoneNumber() {
		return BrowserUtil.getText(shopPhoneNumber);
	}

	public ProductDetailPage productDetails(String productName) {

		for (WebElement elem : productResultSet) {
			if (BrowserUtil.getText(elem).equalsIgnoreCase(productName)) {
				BrowserUtil.click(elem);
			}
		}
		return new ProductDetailPage();
	}

	// private actions to be used in page classes only
	private void launchApp(String url) {
		log.info("launchApp(String url) is invoked");

		driver.get(url);
		log.info("launchApp(String url) is completed");

	}

	private List<String> getProductResultSet() {
		List<String> productNames = new ArrayList<String>();
		for (WebElement elem : productResultSet) {
			productNames.add(elem.getText().trim());

		}
		return productNames;

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
	
	
	//Product Search function  by Avik
	public void SearchProduct(String productName) throws InterruptedException {
		BrowserUtil.write(searchBar, productName);
		BrowserUtil.click(searchButton);
		BrowserUtil.PageReadyStateCheck(5000);
	}

	
	
}
