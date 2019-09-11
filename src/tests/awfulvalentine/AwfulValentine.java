package tests.awfulvalentine;

import java.io.File;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AwfulValentine {
	static WebDriver driver;

	public static void main(String[] args) throws Exception {

		final String InitDriver = "webdriver.chrome.driver";
		final String startDriver = "lib" + File.separator + "chromedriver";

		System.setProperty(InitDriver, startDriver);

		driver = new ChromeDriver();

		driver.get("http://ww12.awful-valentine.com/");

		driver.switchTo().frame(0);

		driver.findElement(By.linkText("Christmas Cards")).click();

		driver.switchTo().frame(0);

		driver.findElement(By.id("e2")).click();

		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());

//driver.switchTo().window(tabs2.get(0));

		driver.switchTo().window(tabs2.get(1));

		{

			try {
				driver.findElement(By.xpath("blahblahblah']")).click();
			} catch (NoSuchElementException e) {
				// e.printStackTrace();
				System.out.print("Error Dude");
			} finally {
				driver.close();
				driver.switchTo().window(tabs2.get(0));
				driver.findElement(By.id("e8")).click();

			}

		}

	}

}
