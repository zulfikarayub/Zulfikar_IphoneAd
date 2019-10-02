package ali_express;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Iphone {

	static WebDriver driver;

	public static void main(String[] args) {
		String exePath = "src\\Drivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.aliexpress.com");

		try {
			String loginpage = pageSourceValidation();
			String VerifyLoginMessage = "AliExpress - Online Shopping for Popular Electronics, Fashion, Home & Garden, Toys & Sports, Automobiles and More.";
			Assert.assertEquals(VerifyLoginMessage, loginpage);

		} catch (Exception e) {
			// TODO: handle exception
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement popupclose = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(@class, 'ui-window-content')]//a[contains(@class, 'close-layer')]")));
		popupclose.click();
		// div[contains(@class, 'searchbar-operate-box')]//input
		WebElement serachBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-key")));
		serachBar.click();
		serachBar.clear();
		serachBar.sendKeys("Iphone");

		WebElement SearchButton = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("/div[contains(@class, 'searchbar-operate-box')]//input")));
		SearchButton.click();

		driver.quit();
	}

	public static String pageSourceValidation() {
		String pagesource = driver.getTitle();
		System.out.println(pagesource);
		return pagesource;
	}
}
