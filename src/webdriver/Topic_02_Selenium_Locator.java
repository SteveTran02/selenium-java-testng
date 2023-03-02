package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
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
		
		// Opening Register Page
		driver.get("https://demo.nopcommerce.com/register");
		
		/* Code HTML of FirstName textbox
		   <input type="text" data-val="true" data-val-required="First name is required." 
		       id="FirstName" name="FirstName">
		*/
		
		/* HTML
		 * 1. TagName HTML: input
		 * 2. Attribute name: type, data-val, data-val-required ...
		 * 3. Attribute value:text, true,...
		 * */
	}

	@Test
	public void TC_01_ID() {
		/* 1. thao tác đầu tiên trên element thì tìm được element đó: findElement
		   2. Find theo cái gì: ID/Class/ Name/ CSS/ XPath...
		   3. Find tìm thấy Element rồi thì Action lên Element đó: sendKey/ Click...
		*/
		
		//tìm id firstname
		//gán giá trị automation
		driver.findElement(By.id("FirstName")).sendKeys("Automation");
	}

	@Test
	public void TC_02_Class() {
		// Mở màn hình Search
		driver.get("https://demo.nopcommerce.com/search");
		// Nhập text vào Search textbox
		driver.findElement(By.className("search-text")).sendKeys("Asus");
	}

	@Test
	public void TC_03_Name () {
		// Click vào Advance Search textbox
		driver.findElement(By.name("advs")).click();
	}
 
	@Test
	public void TC_04_TagName () {
		// tìm ra bao nhiêu thẻ input trên màn hình hiện tại
		System.out.println(driver.findElements(By.tagName("input")).size());
		
	}
	
	@Test
	public void TC_05_LinkText () {
		// Click vào link Address - tuyệt đối
		driver.findElement(By.linkText("Addresses")).click();
		
	}
	
	@Test
	public void TC_06_PartialLinkText () {
		// Click vào link Apply for vendor - link tương đối
		driver.findElement(By.partialLinkText("vendor account"));
	}
	
	@Test
	public void TC_07_CSS () {
		//mở lại trang register
		driver.get("https://demo.nopcommerce.com/register");
		//1
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Steve");
		//2
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Tran");
		//3
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("mitrantiennam@gmail.com");
	}
	
	@Test
	public void TC_08_XPath () {
		//mở lại trang register
		driver.get("https://demo.nopcommerce.com/register");
		//1
		driver.findElement(By.xpath("//input[@data-val-required='First name is required.']")).sendKeys("Pure");
		//2
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Insight");
		//3
		driver.findElement(By.xpath("//label[text()='Email:']/following-sibling::input")).sendKeys("pureinsight@gmail.com");
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
