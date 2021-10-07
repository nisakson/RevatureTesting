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
public class Login {

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
		driver.get("http://localhost:7000/loginpage.html");
	}
	
	@Test
	public void loginemployee() {
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
		
		WebElement usernameBox = driver.findElement(By.id("user"));
		WebElement passwordBox = driver.findElement(By.id("pass"));
		WebElement loginButton = driver.findElement(By.id("submit"));
		
		/*
		 * Now that we have the web elements, we need to decide what the interactions with those elements should be.
		 * If we're simulating logging in, then we simply want to type our credentials into the input boxes and click
		 * the login button.
		 */
		
		// How do we simulate typing into an input box?
		//NOTE: If these credentials matter to you, please obscure them.
		usernameBox.sendKeys("user1");
		passwordBox.sendKeys("pass1");
		
		//How do we simulate clicking on an element?
		loginButton.click();
		
		/*
		 * Since this is a test, it would be a good idea to check that we are getting our expected result.
		 * In this case, our expected result is that we are redirected to the dashboard when we input correct
		 * credentials.
		 */
		
		Assert.assertEquals("redirected unsuccessfully","http://localhost:7000/home.html", driver.getCurrentUrl());
	}
	
	@Test
	public void loginmanager() {
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
		driver.get("http://localhost:7000/loginpage.html");
		
		WebElement usernameBox = driver.findElement(By.id("user"));
		WebElement passwordBox = driver.findElement(By.id("pass"));
		WebElement loginButton = driver.findElement(By.id("submit"));
		
		/*
		 * Now that we have the web elements, we need to decide what the interactions with those elements should be.
		 * If we're simulating logging in, then we simply want to type our credentials into the input boxes and click
		 * the login button.
		 */
		
		// How do we simulate typing into an input box?
		//NOTE: If these credentials matter to you, please obscure them.
		usernameBox.sendKeys("user2");
		passwordBox.sendKeys("pass2");
		
		//How do we simulate clicking on an element?
		loginButton.click();
		
		/*
		 * Since this is a test, it would be a good idea to check that we are getting our expected result.
		 * In this case, our expected result is that we are redirected to the dashboard when we input correct
		 * credentials.
		 */
		
		Assert.assertEquals("redirected unsuccessfully","http://localhost:7000/managerhome.html", driver.getCurrentUrl());
	}
	
	@Test
	public void loginfail() {
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
		driver.get("http://localhost:7000/loginpage.html");
		
		WebElement usernameBox = driver.findElement(By.id("user"));
		WebElement passwordBox = driver.findElement(By.id("pass"));
		WebElement loginButton = driver.findElement(By.id("submit"));
		
		/*
		 * Now that we have the web elements, we need to decide what the interactions with those elements should be.
		 * If we're simulating logging in, then we simply want to type our credentials into the input boxes and click
		 * the login button.
		 */
		
		// How do we simulate typing into an input box?
		//NOTE: If these credentials matter to you, please obscure them.
		usernameBox.sendKeys("user1");
		passwordBox.sendKeys("pass2");
		
		//How do we simulate clicking on an element?
		loginButton.click();
		
		/*
		 * Since this is a test, it would be a good idea to check that we are getting our expected result.
		 * In this case, our expected result is that we are redirected to the dashboard when we input correct
		 * credentials.
		 */
		
		Assert.assertEquals("redirected unsuccessfully","http://localhost:7000/loginpage.html", driver.getCurrentUrl());
	}
	
	@AfterClass
	public static void tearDown() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
}

