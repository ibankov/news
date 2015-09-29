package news.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends Page {
	
	By logoutLocator = By.xpath(".//*[@id='j_idt7:j_idt8']/ul/li[5]/a/span");

	@FindBy(how = How.TAG_NAME, using = "h1")
	@CacheLookup
	public WebElement header;

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public void logout(){
		driver.findElement(logoutLocator).click();
		
	}

}
