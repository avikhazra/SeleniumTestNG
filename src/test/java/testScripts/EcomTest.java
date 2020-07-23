package testScripts;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

import framework.BaseTest;
import framework.ExcelUtil;
import pageObjects.HomePage;

public class EcomTest extends BaseTest{
	
	public HomePage hp;
	//WebDriver driver;
		
	@DataProvider(name="DP")
	public Iterator<Object[]> getTestData(Method method) {
		
		String testName = method.getName(); 
		List<HashMap<String,String>> dataMap = new ArrayList<HashMap<String, String>>();
		
		switch (testName) {
		case "test1":
			dataMap = ExcelUtil.loadDataIntoMap(System.getProperty("user.dir")+"/src/test/java/resources/testdatapertest1.xlsx", testName);
			break;

		case "test2":
			dataMap = ExcelUtil.loadDataIntoMap(System.getProperty("user.dir")+"/src/test/java/resources/testdatapertest2.xlsx", testName);			
			break;
			
		default:
			break;
		}
		System.out.println(testName);

		Collection<Object[]> dp = new ArrayList<Object[]>();
		for (HashMap<String, String> map : dataMap) {
			dp.add(new Object[] { map });
		}
		return dp.iterator();
	}
	
	@Test(dataProvider = "DP")
	public void test1(HashMap<String, String> data) {
		System.out.println(data);
		hp = new HomePage(driver); //sending driver to homepage
		List<String> searchResults = hp.searchProduct(data.get("SearchString"));
		for(String str:searchResults) {
			Assert.assertTrue(str.contains(data.get("SearchString")), "Actual Value: " + str);
		}
		
		System.out.println("one");		
	}	
	
	@Test(dataProvider = "DP")
	public void test2(HashMap<String, String> data) {
		Assert.assertEquals(data.get("Phone"), hp.getShopPhoneNumber());		
		System.out.println(data);
		System.out.println("two");
		
	}
}