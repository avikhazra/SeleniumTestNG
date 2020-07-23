
package testScripts;

import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import framework.BaseTest;
import framework.ExcelUtil;
import pageObjects.HomePage;

public class EcomTest2 extends BaseTest{
	private static final Logger log = Logger.getLogger(EcomTest2.class);
	public HomePage hp;
	@DataProvider(name="TD_Ecom")
	public Iterator<Object[]> getTestData(Method method) {
		
		String testName = method.getName().split("_")[1]; // As   I donot want to use same Test case name(which is there in EcomTest.java). 
		//So I add new testCase Name+_"Test1" as "Test1" is the Sheet name of below excel. So I Split it by "_" and took ArrayIndex: 1 value.
		List<HashMap<String,String>> dataMap = new ArrayList<HashMap<String, String>>();
		dataMap = ExcelUtil.loadDataIntoMap(System.getProperty("user.dir")+"/src/test/java/resources/testdatapertest1.xlsx", testName);
		Collection<Object[]> dp = new ArrayList<Object[]>();
		for (HashMap<String, String> map : dataMap) {
			dp.add(new Object[] { map });
		}
		return dp.iterator();
	}
	@BeforeTest
	public void BeforeTheTest() {
		hp = new HomePage(driver); 
	}
	@Test(dataProvider = "TD_Ecom")
	public void SelectProductToCart_test1(HashMap<String, String> data) throws InterruptedException {
		// All new function has description
		 hp.SearchProduct(data.get("SearchString"));
		 hp.Select1stProdduct(); // It will select the always first product

		
	}
	@AfterTest
	public void AfterTheest() {
		driver.close();
		driver.quit();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	private HashMap<String, String> data;
//
//	@Factory(dataProvider = "TD_Ecom")
//	public void Test_Ecom(HashMap<String, String> data) {
//		log.info("Factory method for Test_Ecom invoked and data about to be initialized");
//		this.data = data;
//	}
//
//	@Parameters({ "URL" })
//	@Test
//	public void verifyLaunchEcomApp(String url) {
//
//	}
//
//	@Test(dependsOnMethods = { "verifyLaunchEcomApp" })
//	public void verifyNavigationToACategoryUsingMenu() {
//
//	}
//
//	@Test(dependsOnMethods = { "verifyNavigationToACategoryUsingMenu" })
//	public void verifyMouseOverParticularDressShowsQuickView() {
//
//	}
//
//	@Test(dependsOnMethods = { "verifyMouseOverParticularDressShowsQuickView" })
//	public void verifyProductConfigurationAndAddingToCart() {//
//	}
//
//	@Test(dependsOnMethods = { "verifyProductConfigurationAndAddingToCart" })
//	public void verifyNavigationToCheckoutPage() {
//
//
//	}

//	@Test(dependsOnMethods = { "verifyNavigationToCheckoutPage" })
//	public void verifyAccountCreation() {
//
//
//	}
//
//	@Test(dependsOnMethods = { "verifyAccountCreation" })
//	public void verifyOrder() {
//	}

}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


