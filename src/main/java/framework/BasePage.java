package framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;
import java.util.Properties;

public class BasePage {

	private static final Logger log = Logger.getLogger(BasePage.class);

	public WebDriver driver;
	public WebDriverWait wait;
	
	public String appURL;
	public Properties prop = new Properties();

	public BasePage(WebDriver driver) {       // parameterised constrctr
		log.info("BasePage class invoked");

		this.driver = driver;
		this.wait = new WebDriverWait(driver, 20);

		BrowserUtil.driver = this.driver;
		BrowserUtil.wait = this.wait;
		
		
        try (InputStream input = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/resource/config.properties")) {

            // load a properties file
            prop.load(input);
            appURL = prop.getProperty("url");
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        PageFactory.initElements(driver, this);
        
		log.info("BasePage(WebDriver driver) is completed");
	}

	public BasePage() { // default constrctr
		//log.info("there is nothing here yet");
        //to do later
	}
	
	//App page or home page...as the user of the application, you will search something from home page
		//menu page		
}