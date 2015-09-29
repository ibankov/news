package news.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends Page {

	By containerLocator = By.id("j_idt28");
	By usernameLocator = By.xpath(".//*[@id='j_idt28:username']");
	By passwordLocator = By.xpath(".//*[@id='j_idt28:password']");
	By loginButtonLocator = By.xpath(".//*[@id='j_idt28:j_idt37']");

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public LoginPage typeUsername(String username) {

		WebElement container = driver.findElement(containerLocator);
		WebElement usernameField = container.findElement(usernameLocator);

		usernameField.sendKeys(Keys.CONTROL + "a");
		usernameField.sendKeys(Keys.DELETE);
		usernameField.sendKeys(username);

		return this;

	}

	public LoginPage typePassword(String password) {

		WebElement container = driver.findElement(containerLocator);
		WebElement passwordField = container.findElement(passwordLocator);

		passwordField.sendKeys(Keys.CONTROL + "a");
		passwordField.sendKeys(Keys.DELETE);
		passwordField.sendKeys(password);

		return this;

	}

	public HomePage submitLogin() {

		WebElement container = driver.findElement(containerLocator);

		container.findElement(loginButtonLocator).click();

		return new HomePage(driver);

	}

	public HomePage loginAs(String username, String password) {

		typeUsername(username);
		typePassword(password);
		return submitLogin();
	}

}
