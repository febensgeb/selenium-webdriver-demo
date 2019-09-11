package tests;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class MercuryToursAutomatedTests {

	static WebDriver driver;

	private static List<String> fromPorts;

	final static String expectedwebUrl = "http://newtours.demoaut.com";

	public static void main(String[] args) throws Exception {

		final String InitDriver = "webdriver.chrome.driver";
		final String startDriver = "lib" + File.separator + "chromedriver";

		System.setProperty(InitDriver, startDriver);

		driver = new ChromeDriver();

		driver.get(expectedwebUrl);

		// verifies that URL = http://newtours.demoaut.com
		String webUrlActual = driver.getCurrentUrl();

		System.out.println("Expected URL: " + expectedwebUrl);
		System.out.println("Actual URL:" + webUrlActual);

		if (webUrlActual.contains(expectedwebUrl)) {
			System.out.println("Checkpoint passed: for Mercury Tours URL");
		} else {
			throw new Exception("Invalid URL");

		}

		loginToMercuryTours("feben06", "Potfree1");

		// Test 1
		List<String> fromPorts = new ArrayList<>();

		fromPorts.add("Frankfurt");
		fromPorts.add("London");
		fromPorts.add("New York");

		for (int i = 0; i <= 2; i++) {
			bookFlights(fromPorts.get(i));
		}

		driver.quit();

		System.out.println("Test Execution Completed");

	}

	public static void loginToMercuryTours(String username, String password) {
		// Login to Mercury Tours
		driver.findElement(By.xpath("//input[@name='userName']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input [@alt = 'Sign-In']")).click();

	}

	public static void bookFlights(String fromPort) throws Exception {

		driver.findElement(By.cssSelector("input[name=findFlights]")).click();

		driver.findElement(By.className("reserveFlights")).click();

		// Find Flights
		driver.findElement(By.xpath("//input[@value ='oneway']")).click();

		Select passangerDropDownMenu = new Select(driver.findElement(By.name("passCount")));

		passangerDropDownMenu.selectByIndex(2);

		Thread.sleep(1000);

		Select fromPortDropDownMenu = new Select(driver.findElement(By.name("fromPort")));
		fromPortDropDownMenu.selectByVisibleText("fromPort");

		
		// List<WebElement> fromPorts =
		// driver.findElements(By.xpath("//select[@name='fromPort']/option"));

//    for(WebElement fromPort: fromPorts) {
		// fromPort.click();
//   	Thread.sleep(500);
// 	System.out.println("available from ports: " + fromPort.getText());
		
		//List<WebElement> links = driver.findElements(By.linkText("Click Me"));
		
		//for(WebElement link: links) {
		////	link.click();
		//}
		

	}

	// Enter flight info and purchase
	Select meanPreferenceDropDown = new Select(driver.findElement(By.className("pass.0.meal")));
	List<WebElement> allMealOptions = meanPreferenceDropDown.getOptions();
	{

		// select all meal options
		for (WebElement meal : allMealOptions) {
			meal.click();

		}

		driver.findElement(By.name("buyFlights")).click();

		driver.findElement(By.xpath("//a[@href= 'mercuryreservation.php']")).click();

		System.out.println("Flight booked for fromPort: " + fromPorts);

	}

}
