package ali_express;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
		driver.manage().window().maximize();
		try {
			String loginpage = driver.getTitle();
			String VerifyLoginMessage = "AliExpress - Online Shopping for Popular Electronics, Fashion, Home & Garden, Toys & Sports, Automobiles and More.";
			Assert.assertEquals(VerifyLoginMessage, loginpage);

		} catch (Exception e) {
			// TODO: handle exception
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement popupclose = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(@class, 'ui-window-content')]//a[contains(@class, 'close-layer')]")));
		popupclose.click();

		WebElement serachBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-key")));
		serachBar.click();
		serachBar.clear();
		serachBar.sendKeys("Iphone");
		serachBar.sendKeys(Keys.ENTER);

		// after search page display

		String AfterSearch = driver.getTitle();
		String VerifySearchMessage = "Buy Iphone and get free shipping on AliExpress.com";
		Assert.assertEquals(VerifySearchMessage, AfterSearch);

		WebElement PageNavigation = driver
				.findElement(By.xpath("//div[contains(@class, 'next-pagination-list')]//button[text()='2']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", PageNavigation);
		PageNavigation.click();
		WebElement itemCount = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class, 'list-items')]//li")));
		int count = driver.findElements(By.xpath("//ul[contains(@class, 'list-items')]//li")).size();
		int i = 0;
		// checking in second page if page is not null
		if (count > i) {
			for (i = 1; i < count; i++) {
				WebElement ItemClick = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//ul[contains(@class, 'list-items')]//li[" + i
								+ "]//div[contains(@class,'item-title-wrap')]//a")));
				ItemClick.click();

				// swtiching the control

				for (String handle : driver.getWindowHandles()) {

					driver.switchTo().window(handle);
				}
				WebElement ItemAvailable = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[contains(@class,'product-quantity-info')]//span")));
				String noOfpiece = ItemAvailable.getText();
				Pattern p = Pattern.compile("\\d+");
				Matcher m = p.matcher(noOfpiece);
				String dsd = m.toString();
				int result = Integer.parseInt(dsd);

				if (result != 0) {

					System.out.println("This item is available for shipping");
					driver.quit();
				}

				else {
					System.out.println("This item is not available for shipping");
					i++;

				}

			}

		}

//		WebElement SearchButton = wait.until(ExpectedConditions
//				.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'next-pagination-list')]//input")));
//		SearchButton.click();

		driver.quit();
	}

}
