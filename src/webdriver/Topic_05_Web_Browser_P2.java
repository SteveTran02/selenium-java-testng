package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_P2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Verify_Url() {
		driver.get("http://live.techpanda.org/");
		
		// Verify URL Login page
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		// Verify URL Register page
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");

	}

	@Test
	public void TC_02_Verify_Title () {
		driver.get("http://live.techpanda.org/");
		
		// Verify Title Login
		driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		// Verify Title Create an account
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	@Test
	public void TC_03_Navigate_function() {
		// Get homepage
		driver.get("http://live.techpanda.org/");
		// Get Login page
		driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
		// Get Register page
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		// Verify Register page
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		// Back page _ Login page
		Navigation nar = driver.navigate();
		nar.back();
		// Verify Login page
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		// Forward to Register page
		nar.forward();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	@Test
	public void TC_04_Get_Page_Source_Code () {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
		//Verify Login Page contains text: Login or Create an Account
		sleepInSecond(2);
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		// Get Register page
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		//Verify Login Page contains text: Login or Create an Account
		sleepInSecond(2);
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
		
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
