package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_Wait_P3_Implicit_Wait {
	WebDriver driver;
	Random ran;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAdress, firstname, lastname, fullname, password, confirmation;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		driver = new FirefoxDriver();
		ran = new Random();
		driver.manage().window().maximize();

		emailAdress = "steve" + ran.nextInt(9999) + "@gmail.com";
		firstname = "Steve";
		lastname = "Tran";
		fullname = firstname + " " + lastname;
		password = "steve123";
		confirmation = "steve123";
	}

	@Test
	public void TC_01_Timeout_Les_Than_5_Seconds() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		// Set up timeout = 2s, testcase will fail
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

	@Test
	public void TC_02_Timeout_Equal_5_Seconds() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		// Set up timeout = 5s, testcase will pass
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

	@Test
	public void TC_03_Timeout_More_Than_5_Seconds() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		// Set up timeout > 5s, testcase will pass
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}
	
	
	public void TC_04_Create_new_account() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();

		// Input valid data into textbox
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.id("email_address")).sendKeys(emailAdress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(confirmation);
		driver.findElement(By.xpath("//button[@title='Register']")).click();

		// Verify new account
		// Verify success message
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
				"Thank you for registering with Main Website Store.");
		// Verify fullname and email
		String contactInformationText = driver
				.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p"))
				.getText();
		System.out.println(contactInformationText);
		Assert.assertTrue(contactInformationText.contains(fullname));
		Assert.assertTrue(contactInformationText.contains(emailAdress));

		// Logout
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[@class='label' and text()='Account']"))
				.click();
		driver.findElement(By.xpath("//li[@class=' last']/a[@title='Log Out']")).click();
		// Verify homepage
		// Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Login or
		// Create an Account')]")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
