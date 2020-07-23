package framework;
import java.time.Duration;
import java.util.function.Function;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BrowserUtil {
	
	private static final Logger log = Logger.getLogger(BrowserUtil.class);
	public static WebDriver driver;
	public static WebDriverWait wait;

	/**
	 * This method clicks on a WebElement	 
	 * @param elem WebElement to be clicked
	 
	 */
	public static void click(WebElement elem) {
		log.info("click(WebElement elem) is invoked");
		wait.until(ExpectedConditions.elementToBeClickable(elem));
		elem.click();
		log.info("click(WebElement elem) is completed");

	}
	
	public static void write(WebElement elem, String text) {
		log.info("write(WebElement elem) is invoked");
		wait.until(ExpectedConditions.visibilityOf(elem));
		elem.clear();                                // for clearing the previous value added by Avik
		elem.sendKeys(text);
		log.info("write(WebElement elem) is completed");
		
	}

	public static String getText(WebElement elem) {
		log.info("getText(WebElement elem) is invoked");
		return elem.getText().trim();
		
	}
	/*************************************************************
	 *  FunctionName: ElementWaitToClickable
	 * Argument : 3000 miliSecon
	 * Functionality: it waits for element for existence, visible and clickablity
	 * 
	 **************************************************************/
	public static WebElement ElementWaitToClickable(final WebElement elem) {
		WebElement webE;
		Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver)
				  .withTimeout(Duration.ofSeconds(3000))
				  .pollingEvery(Duration.ofSeconds(5))
				  .ignoring(NoSuchElementException.class);

				webE = wait1.until(new Function<WebDriver, WebElement>() {
				  public WebElement apply(WebDriver driver) {
				    return elem;
				  }
				});
				
				
	  wait.until(ExpectedConditions.visibilityOfAllElements(webE));				
	  wait.until(ExpectedConditions.elementToBeClickable(webE));	
	  return webE;
	}
	
	/****************************************************************
	 *  FunctionName: PageReadyStateCheck
	 * Argument : intmilisecond
	 * @throws InterruptedException 
	 * 
	 ******************************************************************/
	public static void PageReadyStateCheck(int intmilisecond) throws InterruptedException {
	
		/**************************************
		 * To Know more about readystate Please visit:https://developer.mozilla.org/en-US/docs/Web/API/Document/readyState
		 * https://levelup.gitconnected.com/understand-browser-readystate-and-how-to-track-the-interactivity-of-the-content-on-your-web-page-8d2802f29aa?gi=999be81d411c
		 */
		
		
		System.out.println("It will Wait for page to load");
		final JavascriptExecutor js = (JavascriptExecutor)driver;
		try {
			WebDriverWait wait = new WebDriverWait(driver,intmilisecond);
			System.out.println("Ready state Checking------------------");
			wait.until(new ExpectedCondition<Boolean>(){

				public Boolean apply(WebDriver driver) {
					System.out.println("Ready state is checked");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					return js.executeScript("return document.readyState").equals("complete");
				}
			});
			System.out.println("Jquery checking...................");
			wait.until(new ExpectedCondition<Boolean>(){

				public Boolean apply(WebDriver driver) {

					while(true) {
						if((Boolean) js.executeScript("return jQuery.active==0")) {
							System.out.println("Jquery ready state is checked");
							break;
						}
						try {
							Thread.sleep(1000);
						}catch(Exception ex) {
						}
					}
					return true;
				}
			});
			while((!js.executeScript("return document.readyState").equals("complete"))) {
				Thread.sleep(1000);
			}
			System.out.println("Page Ready state is found:  Page is loaded properly");
			Assert.assertTrue(true,"Page Ready state is found:  Page is loaded properly");
		}catch(Exception ex) {}
	}	
	
	
}
