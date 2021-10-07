package gluecode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/*
 * This file is called a "Step Definition File". It contains what we refer to as a "Gluecode". Gluecode is
 * the code that is generated for us via Cucumber.
 */
public class Gluecode {
	
	private static WebDriver driver;

	@Given("the user is on the login page")
	public void a_user_is_on_the_home_page() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		// Navigate to the home page
		driver.get("http://localhost:7000/loginpage.html");
	}
	
	@Given("the user is on the employee home page")
	public void a_user_is_on_the_employee_home_page() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		// Navigate to the home page
		driver.get("http://localhost:7000/home.html");
	}
	
	@Given("the user is on the manager home page")
	public void a_user_is_on_the_manager_home_page() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		// Navigate to the home page
		driver.get("http://localhost:7000/managerhome.html");
	}
	
	@Given("the user is on the statistics page")
	public void a_user_is_on_the_statistics_page() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		// Navigate to the home page
		driver.get("http://localhost:7000/statistics.html");
	}

	@When("the user inputs the correct username")
	public void the_user_inputs_the_correct_username() {
		WebElement usernameBox = driver.findElement(By.id("user"));
		usernameBox.sendKeys("user1");
	}

	@When("the user inputs the correct password")
	public void the_user_inputs_the_correct_password() {
		WebElement passwordBox = driver.findElement(By.id("pass"));
		passwordBox.sendKeys("pass1");
	}
	
	@When("the manager user inputs the correct username")
	public void the_manager_user_inputs_the_correct_username() {
		WebElement usernameBox = driver.findElement(By.id("user"));
		usernameBox.sendKeys("user2");
	}

	@When("the manager user inputs the correct password")
	public void the_manager_user_inputs_the_correct_password() {
		WebElement passwordBox = driver.findElement(By.id("pass"));
		passwordBox.sendKeys("pass2");
	}

	@Then("the user is forwarded to the employee home page")
	public void the_user_is_redirected_to_the_employee_page() {
		WebElement loginButton = driver.findElement(By.id("submit"));
		loginButton.click();
		Assert.assertEquals("http://localhost:7000/home.html", driver.getCurrentUrl());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
	
	@Then("the user is forwarded to the manager home page")
	public void the_user_is_redirected_to_the_manager_page() {
		WebElement loginButton = driver.findElement(By.id("submit"));
		loginButton.click();
		Assert.assertEquals("http://localhost:7000/managerhome.html", driver.getCurrentUrl());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
	
	@Then("the user is sent to the manager home page")
	public void the_user_is_sent_to_the_manager_page() {
		Assert.assertEquals("http://localhost:7000/managerhome.html", driver.getCurrentUrl());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
	
	@Then("the user is sent to the employee home page")
	public void the_user_is_sent_to_the_employee_home_page() {
		Assert.assertEquals("http://localhost:7000/home.html", driver.getCurrentUrl());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
	
	@Then("the user is forwarded to the login page")
	public void the_user_is_redirected_to_the_login_page() {
		Assert.assertEquals("http://localhost:7000/loginpage.html", driver.getCurrentUrl());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
	
	@Then("the user is forwarded to the statistics page")
	public void the_user_is_redirected_to_the_statistics_page() {
		Assert.assertEquals("http://localhost:7000/statistics.html", driver.getCurrentUrl());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}

	@When("the user inputs the incorrect password")
	public void the_user_inputs_the_incorrect_password() {
		WebElement passwordBox = driver.findElement(By.id("pass"));
		passwordBox.sendKeys("incorrect");
	}
	
	@When("the user inputs a description")
	public void the_user_inputs_a_description() {
		WebElement passwordBox = driver.findElement(By.id("description"));
		passwordBox.sendKeys("test");
	}
	
	@When("the user inputs an amount")
	public void the_user_inputs_an_amount() {
		WebElement passwordBox = driver.findElement(By.id("amount"));
		passwordBox.sendKeys("0.02");
	}

	@Then("I am redirected to the login page")
	public void the_user_is_still_on_the_home_page_of_swag_labs() {
		Assert.assertEquals("http://localhost:7000/loginpage.html", driver.getCurrentUrl());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
	
	@When("I click statistics")
	public void i_click_statistics() {
		WebElement statisticsButton = driver.findElement(By.id("statistic"));
		statisticsButton.click();
	}
	
	@When("I click manager home")
	public void i_click_manager_home() {
		WebElement homeButton = driver.findElement(By.id("mhome"));
		homeButton.click();
	}
	
	@When("I click submit")
	public void i_click_submit() {
		WebElement submitButton = driver.findElement(By.id("submit"));
		submitButton.click();
	}

	@When("I click logout")
	public void i_click_logout() {
		WebElement logoutButton = driver.findElement(By.id("logout"));
		logoutButton.click();
	}
}
