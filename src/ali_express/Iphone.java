package ali_express;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Iphone {

	public static void main(String[] args) { 
		String exePath = "src\\Drivers\\chromedriver.exe";
		 System.setProperty("webdriver.chrome.driver", exePath);
		 WebDriver driver = new ChromeDriver();
		 driver.get("http://toolsqa.com/automation-practice-form/");
//		 driver.findElement(By.id(id))
		 }
}
