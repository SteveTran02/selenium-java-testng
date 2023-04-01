package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Wait_P5_Explicit_Wait {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
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

	
	public void TC_01_Alert_Is_Present () {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		explicitWait = new WebDriverWait(driver, 30);
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
		
		// alert = driver.switchTo().alert();
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		alert.accept();
		
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='You clicked an alert successfully ']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='You clicked an alert successfully ']")).isDisplayed());
		
	}

	
	public void TC_02_Wait_For_Attribute_Contains_Value () {
		driver.get("http://live.techpanda.org/");
		explicitWait = new WebDriverWait(driver, 30);
		
		// Wait cho Search Textbox chứa giá trị là 1 đoạn placeholder
		explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("input#search"), "placeholder", "Search entire store here..."));

		// Verify
		Assert.assertTrue(driver.findElement(By.cssSelector("input#search")).getAttribute("placeholder").contains("Search entire store"));
		
	}

	
	public void TC_03_Wait_For_Element_Clickable () {
		driver.get("https://login.mailchimp.com/signup/");
		
		explicitWait = new WebDriverWait(driver, 30);
		
		// Wait cho Sign Up button được ready trước khi click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#create-account-enabled")));
		
		// Click Sign Up button
		driver.findElement(By.cssSelector("button#create-account-enabled")).click();

	}

	
	public void TC_04_Wait_For_Element_Selected () {
		driver.get("https://automationfc.github.io/multiple-fields/");
		explicitWait = new WebDriverWait(driver, 30);
		
		// Wait cho 29 textbox đc load ra
		explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("input.form-checkbox"), 29));
		List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("input.form-checkbox"));
		
		// Click all checkbox
		for (WebElement checkbox : allCheckboxes) {
			checkbox.click();
		}
		
		// Verify click all checkbox
		for (WebElement checkbox : allCheckboxes) {
			// Wait cho element đã được selected
			explicitWait.until(ExpectedConditions.elementToBeSelected(checkbox));
			Assert.assertTrue(checkbox.isSelected());
		}
	}
	
	
	public void TC_05_Wait_For_Frame_Availiable () {
		
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		explicitWait = new WebDriverWait(driver, 30);
		
		// Switch to Login Frame
		driver = explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("login_page")));
		
		// input Customer ID textbox
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.form-control.text-muted")));
		driver.findElement(By.cssSelector("input.form-control.text-muted")).sendKeys("soundOfHope");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='CONTINUE']")));
		driver.findElement(By.cssSelector("a.login-btn")).click();
		
		// Switch to Login Frame
		driver.switchTo().defaultContent();
				
		
		// input Password textbox
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#keyboard")));
		driver.findElement(By.cssSelector("input#keyboard")).sendKeys("soundOfHope");
		
	}
	
	
	public void TC_06_Wait_For_Get_Text () {
		
		driver.get("http://live.techpanda.org/");
		explicitWait = new WebDriverWait(driver, 30);
		
		// Click acount link
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='footer-container']//a[@title='My Account']")));
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		
		// click button 
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#send2")));
		driver.findElement(By.cssSelector("button#send2")).click();
		
		// Verify email address error message
		explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#advice-required-entry-email"), "This is a required field."));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(), "This is a required field.");
		
		// Verify password textbox error message
		explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#advice-required-entry-pass"), "This is a required field."));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(), "This is a required field.");
	}
	
	
	public void TC_07_Wait_URL_Title () {
		
		driver.get("http://live.techpanda.org/");
		explicitWait = new WebDriverWait(driver, 30);
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Mobile']")));
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		// Get URL
		explicitWait.until(ExpectedConditions.urlContains("live.techpanda.org/index.php/mobile"));
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/mobile.html");
		
		
		// Get Title
		explicitWait.until(ExpectedConditions.titleIs("Mobile"));
		Assert.assertEquals(driver.getTitle(), "Mobile");
		
	}
	
	
	@Test
	public void TC_08_Timeout_Les_Than_5_Seconds() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait = new WebDriverWait(driver, 3);
		
		// Click Start button
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Start']")));
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		// Case 1: Wait cho loading biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		
		// Case 2: Wait cho Hello World! xuất hiện 
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		// Verify text message
		explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"), "Hello World!"));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

	
	@Test
	public void TC_09_Timeout_Equal_5_Seconds() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait = new WebDriverWait(driver, 5);
		
		// Click Start button
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Start']")));
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		// Case 1: Wait cho loading biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		
		// Case 2: Wait cho Hello World! xuất hiện 
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		// Verify text message
		explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"), "Hello World!"));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

	
	@Test
	public void TC_10_Timeout_More_Than_5_Seconds() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait = new WebDriverWait(driver, 100);
		
		// Click Start button
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Start']")));
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		// Case 1: Wait cho loading biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		
		// Case 2: Wait cho Hello World! xuất hiện 
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		// Verify text message
		explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"), "Hello World!"));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
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
