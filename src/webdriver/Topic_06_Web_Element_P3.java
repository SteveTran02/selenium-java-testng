package webdriver;



import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_P3 {
	WebDriver driver;
	Random ran;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	// declare global variables
	By emailTextbox = By.id("email");
	By passwordTextbox = By.id("pass");
	By loginButton = By.xpath("//button[@id='send2']");
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();	
		
		emailAdress = "steve" + ran.nextInt(9999) + "@gmail.com";
		firstname = "Steve";
		lastname = "Tran"; 
		fullname = firstname + " " + lastname;
		password = "steve123"; 
		confirmation = "steve123"; 
	}

    @Test
    public void Login_01_Empty_Email_and_Password() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		sleepInSecond(2);
		// Empty username and password textbox, click Login button
		driver.findElement(loginButton).click();
		sleepInSecond(2);

		// Verify username and password messages
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(), "This is a required field.");
	}

	@Test
	public void Login_02_Invalid_Email() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		sleepInSecond(2);
		// Input invalid email and valid password, click Login button
		driver.findElement(emailTextbox).sendKeys("12343464646@243434");
		driver.findElement(passwordTextbox).sendKeys("123456");
		driver.findElement(loginButton).click();
		sleepInSecond(2);

		// Verify username and password messages
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void Login_03_Password_less_than_6_character() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		sleepInSecond(2);
		// Input invalid email and valid password, click Login button
		driver.findElement(emailTextbox).sendKeys("steve@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("123");
		driver.findElement(loginButton).click();
		sleepInSecond(2);

		// Verify username and password messages
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");

	
	}
	
	@Test
	public void Login_04_Incorrect_Email_or_Password() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		sleepInSecond(2);
		
		// Input incorrect Email and valid password
		driver.findElement(emailTextbox).sendKeys(emailAdress);
		driver.findElement(passwordTextbox).sendKeys("123123123");
		driver.findElement(loginButton).click();
		sleepInSecond(2);
		
		// Verify
		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");
		
	}
	
	
	@Test
	public void Login_05_Create_new_account() {
		
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(2);
		
		// Input valid data into textbox
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.id("email_address")).sendKeys(emailAdress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(confirmation);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		sleepInSecond(3);
		
		// Verify new account	
		   // Verify success message
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"Thank you for registering with Main Website Store.");
		   // Verify fullname and email
		String contactInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(contactInformationText);
		Assert.assertTrue(contactInformationText.contains(fullname));
		Assert.assertTrue(contactInformationText.contains(emailAdress));
		
		// Logout
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[@class='label' and text()='Account']")).click();
		driver.findElement(By.xpath("//li[@class=' last']/a[@title='Log Out']")).click();
		// Verify homepage
		// Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Login or Create an Account')]")).isDisplayed());
		
	}
	
	
	@Test
	public void Login_06_Login_valid_info() {
				
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		sleepInSecond(2);
		
		// Input Email and password
		driver.findElement(emailTextbox).sendKeys(emailAdress);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(loginButton).click();
		sleepInSecond(2);

		
		// Verify new account	
		String contactInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(contactInformationText);
		Assert.assertTrue(contactInformationText.contains(fullname));
		Assert.assertTrue(contactInformationText.contains(emailAdress));
		
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
