package poms;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * When using Selenium, it is common to create Page Object Models (POMs). A Page Object Model is a representation
 * of a web page that you are intending to interact with. It serves as a repository of web elements that exist on that
 * web page. Note that it can also contain useful methods for interacting with those elements.
 */
public class LoginPage {

	/*
	 * The very first thing we'll do is define our repository of elements (i.e. locate our elements). This time,
	 * however, we will not manually initialize our elements. We will let Selenium do it for us by using the @FindBy.
	 */
	
	@FindBy(id = "user")
	private WebElement usernameBox;
	@FindBy(id = "pass")
	private WebElement passwordBox;
	@FindBy(id = "submit")
	private WebElement loginButton;
	
	/*
	 * Note that we have to pass the WebDriver that we're using to our Page Object Model class so that Selenium can
	 * use that driver to grab elements from the web page.
	 */
	
	public LoginPage(WebDriver driver) {
		/*
		 * As I said above, we can let Selenium initialize our WebElements. This is actually done via the PageFactory.
		 */
		
		PageFactory.initElements(driver, this);
		
		/*
		 * Let's talk about explicit waits now. Explicit waits are different from implicit waits in that
		 * they allow us to specify conditions that must be met before elements are interacted with.
		 * For instance, we can wait for an element to be visible or clickable before we interact with it.
		 * 
		 * Explicit waits act on a per element basis. This means that your explicit waits ae applied to specific
		 * elements.
		 */
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(usernameBox));
	}
	
	/*
	 * The rest of the methods here are just utility for later use! Have fun!
	 */
	
	public void login(String username, String password) {
		usernameBox.sendKeys(username);
		passwordBox.sendKeys(password);
		loginButton.click();
	}
	
	/*
	 * NOTE: You can also simulate an end user hitting keys on the keyboard using the "Keys" utility.
	 */
	public void loginByHittingEnterInPasswordBox(String username, String password) {
		
		usernameBox.sendKeys(username);
		passwordBox.sendKeys(password, Keys.ENTER);
	}
}

