package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Wait_P1_Element_Status {
	WebDriver driver;
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

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		
		// Cho việc tìm element ( findElement/ findElements ) -> chung chung
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Cho trạng thái của element -> cụ thể
		explicitWait = new WebDriverWait(driver, 30);
		
	}

	
	public void TC_01_Visible_Displayed() {
		// Điều kiện 1: Element có trên UI và có trong DOM
		driver.get("https://www.facebook.com/");
		
		// Chờ Email textbox đc hiện thị trước khi sendkeys vào nó
		// Chờ trong khoảng time là 30s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));

		driver.findElement(By.cssSelector("input#email")).sendKeys("soundOfHope@gmail.com");
		
	}

	
	public void TC_02_Invisible_Undisplayed_Case_1 () {
		// Điều kiện 2: Element không có trên UI (k nhìn thấy) nhưng có trong DOM
		driver.get("https://www.facebook.com/");

		
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		
		// Confirmation email textbox is undisplayed
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
		
		driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("soundOfHope@gmail.com");
		
		// Confirmation email textbox is displayed
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
		
		driver.findElement(By.cssSelector("input[name='reg_email_confirmation__']")).sendKeys("soundOfHope@gmail.com");
			
		
	}
	
	
	public void TC_02_Invisible_Undisplayed_Case_2 () {
		// Điều kiện 3: Element không có trên UI (k nhìn thấy) và cũng không có trong DOM
		driver.get("https://www.facebook.com/");

		
	
		// Confirmation email textbox is undisplayed
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
		
	}

	
	
	public void TC_03_Presence_Case_1 () {
		// Điều kiện 1: Element có trên UI và có trong DOM
		driver.get("https://www.facebook.com/");
		
		
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#email")));
		
		
	}
	
	
	public void TC_03_Presence_Case_2 () {
		driver.get("https://www.facebook.com/");
		
		// Điều kiện 2: Element không có trên UI (k nhìn thấy) nhưng có trong DOM
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
		
	}

	
	@Test
	public void TC_04_Staleness() {
		// Apply cho cả: có trong HTML và sau đó apply điều kiện 3
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
		
		// B1: element phải có trong DOM
		WebElement confirmationEmailTextbox = driver.findElement(By.cssSelector("input[name='reg_email_confirmation__']"));
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		
		// B2: Wait cho confirm email staleness chạy nhanh
		explicitWait.until(ExpectedConditions.stalenessOf(confirmationEmailTextbox));

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
