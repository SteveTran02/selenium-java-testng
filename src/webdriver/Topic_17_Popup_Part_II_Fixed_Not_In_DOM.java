package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Popup_Part_II_Fixed_Not_In_DOM {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);
		driver = new FirefoxDriver(options);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Fixed_Popup_Not_In_DOM_Tiki() {
		driver.get("https://tiki.vn/");
		
		By loginButton = By.cssSelector("div[data-view-id='header_header_account_container']");
		By loginPopup = By.cssSelector("div.ReactModal__Content");
		
		// Verify Login Popup is not displayed
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		
		// Click Login button
		driver.findElement(loginButton).click();
		sleepInSecond(2);
		
		// Verify Login Popup is displayed
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		// Assert.assertEquals(driver.findElements(loginPopup).size(), 1);
		
		// Login by email
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(2);

		// Verify error message
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Email không được để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Mật khẩu không được để trống']")).isDisplayed());
		sleepInSecond(2);
		// Close
		driver.findElement(By.cssSelector("img.close-img")).click();
		
		// Verify Login Popup is not displayed
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
	}

	//@Test
	public void TC_02_Fixed_Popup_Not_In_DOM_Facebook() {
		driver.get("https://www.facebook.com/");
		
		By createAccountButton = By.xpath("//a[@data-testid='open-registration-form-button']");
		By createAccountPopup = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
		
		// Verify creat account popup is not displayed	
		Assert.assertEquals(driver.findElements(createAccountPopup).size(), 0);
		
		driver.findElement(createAccountButton).click();
		sleepInSecond(2);
		
		// Verify creat account popup is displayed	
		Assert.assertTrue(driver.findElement(createAccountPopup).isDisplayed());
		
		// Input valid data
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("soundOfHope");
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("soundOfHope");
		driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("soundOfHope@gmail.com");
		driver.findElement(By.xpath("//input[@name='reg_passwd__']")).sendKeys("soundOfHope");
		// dropdown list Date of birth
		new Select(driver.findElement(By.xpath("//select[@id='day']"))).selectByVisibleText("9");		
		new Select(driver.findElement(By.xpath("//select[@id='month']"))).selectByVisibleText("May");
		new Select(driver.findElement(By.xpath("//select[@id='year']"))).selectByVisibleText("2000");
		// Checkbox
		driver.findElement(By.xpath("//label[text()='Female']/following-sibling::input")).click();
		sleepInSecond(2);
		
		// Close
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(2);
		
		// Verify creat account popup is not displayed		
		Assert.assertEquals(driver.findElements(createAccountPopup).size(), 0);
		
		
	}



	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
