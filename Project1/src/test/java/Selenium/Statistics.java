package Selenium;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import poms.LoginPage;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Statistics {

	/*
	 * Selenium has several core interfaces. One such interface is the WebDriver interface. Please note that
	 * the implementation of the WebDriver interface you choose depends on the browser.
	 */
	
	private static WebDriver driver;
	
	/*
	 * Let's set up our driver:
	 */
	
	@BeforeClass
	public static void setUp() {
		/*
		 * We have to tell the application where to locate the driver we're using:
		 */
		
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		// Navigate to the home page
		driver.get("http://localhost:7000/managerhome.html");
	}
	
	@Test
	public void goToStatistics() {
		/*
		 * In Selenium, if you wish to interact with an element, you need to determine how you will
		 * locate the element on the webpage. Selenium has several built-in locators:
		 * 
		 * tag name
		 * name (attribute)
		 * css selector
		 * link text
		 * partial link text
		 * xpath
		 * classname (class attribute)
		 * id (id attribute)
		 * 
		 * That said, the first thing we are going to do is select our input boxes so that we can simulate typing into
		 * them. We'll also select the "login" button so that we can click it.
		 */
		
		WebElement statisticBox = driver.findElement(By.id("statistic"));
		
		/*
		 * Now that we have the web elements, we need to decide what the interactions with those elements should be.
		 * If we're simulating logging in, then we simply want to type our credentials into the input boxes and click
		 * the login button.
		 */

		//How do we simulate clicking on an element?
		statisticBox.click();
		
		/*
		 * Since this is a test, it would be a good idea to check that we are getting our expected result.
		 * In this case, our expected result is that we are redirected to the dashboard when we input correct
		 * credentials.
		 */
		
		Assert.assertEquals("redirected unsuccessfully","http://localhost:7000/statistics.html", driver.getCurrentUrl());
	}
	
	@Test
	public void leaveStatistics() {
		/*
		 * In Selenium, if you wish to interact with an element, you need to determine how you will
		 * locate the element on the webpage. Selenium has several built-in locators:
		 * 
		 * tag name
		 * name (attribute)
		 * css selector
		 * link text
		 * partial link text
		 * xpath
		 * classname (class attribute)
		 * id (id attribute)
		 * 
		 * That said, the first thing we are going to do is select our input boxes so that we can simulate typing into
		 * them. We'll also select the "login" button so that we can click it.
		 */
		
		WebElement homeBox = driver.findElement(By.id("mhome"));
		
		/*
		 * Now that we have the web elements, we need to decide what the interactions with those elements should be.
		 * If we're simulating logging in, then we simply want to type our credentials into the input boxes and click
		 * the login button.
		 */

		//How do we simulate clicking on an element?
		homeBox.click();
		
		/*
		 * Since this is a test, it would be a good idea to check that we are getting our expected result.
		 * In this case, our expected result is that we are redirected to the dashboard when we input correct
		 * credentials.
		 */
		
		Assert.assertEquals("redirected unsuccessfully","http://localhost:7000/managerhome.html", driver.getCurrentUrl());
	}
}


