package news.ui;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import news.ui.pages.HomePage;
import news.ui.pages.LoginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ChromeTestNG {
	
	protected static String gridHubUrl;
	protected static String baseUrl;
	protected static DesiredCapabilities capabilities;
	
	private WebDriver browser;
	private HomePage homePage;
	private LoginPage loginPage;
	private By loginLocator = By.id("j_idt7:login");
	
	@Test(description = "Login with valid data", priority = 2)
	public void loginTest() {
		browser.get(baseUrl + "news/index.xhtml");
		homePage = new HomePage(browser);
		browser.findElement(loginLocator).click();
		Assert.assertEquals(browser.getCurrentUrl().split(";")[0], baseUrl + "news/login.xhtml");
		loginPage = new LoginPage(browser);
		loginPage.loginAs("admin1", "admin1");
		Assert.assertEquals(browser.getCurrentUrl(), homePage.getUrl());
		
	}
	
	@Test(description = "Login with invalid data", priority = 1)
	public void invalidLoginTest() {
		
		browser.get(baseUrl + "news/index.xhtml");
		homePage = new HomePage(browser);
		browser.findElement(loginLocator).click();
		Assert.assertEquals(browser.getCurrentUrl().split(";")[0], baseUrl + "news/login.xhtml");
		loginPage = new LoginPage(browser);
		loginPage.loginAs("admin2", "admin2");
		Assert.assertEquals(browser.getCurrentUrl(), homePage.getUrl());
	}
	
	@Test(description = "Failing test", groups = { "failChrome" })
	public void failTest() {
		
		Assert.assertEquals(1, 2);
	}
	
	@Test(description = "Skipped test", dependsOnGroups = { "failChrome" })
	public void skipTest() {
		
		Assert.assertEquals(1, 2);
	}
	
	@BeforeClass(groups = {"localChrome"})
	public void setupBeforeSuite(ITestContext context) {
		baseUrl = "http://localhost:8080/";
		browser = new ChromeDriver();
		
	}
	
	@BeforeClass(alwaysRun = true,groups = {"remoteChrome"})
	public void setupBeforeSuiteRemote(ITestContext context) {
		capabilities = DesiredCapabilities.chrome();
		capabilities.setJavascriptEnabled(true);
		baseUrl = "http://10.10.0.112:8080/";
		URL url = null;
		try {
			url = new URL("http", "10.10.0.126", 4444, "/wd/hub");
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		browser = new RemoteWebDriver(url, capabilities);
		browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public void afterClass() {
		browser.close();
		browser.quit();
	}
	
}
