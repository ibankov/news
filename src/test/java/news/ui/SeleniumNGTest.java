package news.ui;

import java.util.concurrent.TimeUnit;

import news.ui.pages.HomePage;
import news.ui.pages.LoginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SeleniumNGTest {
	protected static String gridHubUrl;
	protected static String baseUrl;
	protected static Capabilities capabilities;

	private WebDriver browser;
	private HomePage homePage;
	private LoginPage loginPage;
	private By loginLocator = By
			.xpath(".//*[@id='j_idt7:j_idt8']/ul/li[6]/a/span");

	@Test(description = "Login with valid data")
	public void loginTest() {
		browser.get(baseUrl + "news/index.xhtml");
		homePage = new HomePage(browser);
		browser.findElement(loginLocator).click();
		loginPage = new LoginPage(browser);
		loginPage.loginAs("admin1", "admin1");
		Assert.assertEquals(browser.getCurrentUrl(), homePage.getUrl());

	}
	@Test(description = "Login with invalid data")
	public void invalidLoginTest() {

		browser.get(baseUrl + "news/index.xhtml");
		homePage = new HomePage(browser);
		browser.findElement(loginLocator).click();
		loginPage = new LoginPage(browser);
		loginPage.loginAs("admin2", "admin2");
		Assert.assertEquals(browser.getCurrentUrl(), loginPage.getUrl());

	}

	@BeforeClass(alwaysRun = true)
	public void setupBeforeSuite(ITestContext context) {
		baseUrl = "http://localhost:8080/";

		try {

			browser = new FirefoxDriver();
			browser.manage().timeouts()
					.implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			throw new IllegalStateException("Can't start Web Driver", e);
		}
	}

	@AfterClass
	public void afterClass() {
		browser.close();
		browser.quit();
	}

}
