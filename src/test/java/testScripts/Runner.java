package testScripts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import framework.ExcelUtil;

public class Runner {
	static XmlTest test;
	static XmlSuite suite;
	static XmlClass xmclass;
	static List<XmlTest> tests;
	static List<XmlClass> classes;
	static List<XmlSuite> suites;
	static List<XmlInclude> includedMethods;

	public static void main(String[] args) {

		List<HashMap<String, String>> suiteMap = ExcelUtil
				.loadDataIntoMap(System.getProperty("user.dir")+ "/src/test/java/resources/resources/TestSuite.xlsx");

		suite = new XmlSuite();
		suite.setName("Framework_Training");
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("browser", "chrome");
		parameters.put("implicitWait", "60");
		parameters.put("explicitWait", "60");
		parameters.put("secretKey", "donotspillthesecret!");
		suite.setParameters(parameters);

		Iterator<HashMap<String, String>> suiteMapIterator = suiteMap.iterator();
		tests = new ArrayList<XmlTest>();
		while (suiteMapIterator.hasNext()) {

			HashMap<String, String> item = suiteMapIterator.next();

			if (!item.get("Execute").equalsIgnoreCase("N")) {

				if (!testExists(test, item.get("Test"))) {
					test = new XmlTest(suite);
					test.setName(item.get("Test"));
					classes = new ArrayList<XmlClass>();
				}

				if (!classExists(xmclass, item.get("TestClass"))) {
					xmclass = new XmlClass("testScripts." + item.get("TestClass"));

				}
				
				xmclass.getIncludedMethods().add(new XmlInclude(item.get("TestMethods")));
				classes.add(xmclass);
				test.setXmlClasses(classes);
				tests.add(test);

			}

		}

		suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		
		tng.run();

	}

	private static boolean classExists(XmlClass xmclass, String className) {
		boolean result;
		if (xmclass != null) {
			if (!xmclass.getName().equalsIgnoreCase(className)) {
				result = false;

			} else {
				result = true;
			}

		} else {
			result = false;
		}
		return result;
	}

	private static boolean testExists(XmlTest test, String testName) {
		boolean result;
		if (test != null) {
			if (!test.getName().equalsIgnoreCase(testName)) {
				result = false;

			} else {
				result = true;
			}

		} else {
			result = false;
		}
		return result;
	}

}