package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Popup_Part_III_Random {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAdress = "steve" + getRandomNumber() + "@gmail.com";
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Random_Popup_In_DOM_Java_Code_Geeks() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(15);
		
		By lePopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
		
		// Xuất hiện Popup
		if(driver.findElement(lePopup).isDisplayed()) {
			driver.findElement(By.cssSelector("div.lepopup-input>input")).sendKeys(emailAdress);
			sleepInSecond(3);
			driver.findElement(By.cssSelector("a[data-label='Get the Books'],[data-label='OK']>span")).click();
			sleepInSecond(9);
			//Verify		
			Assert.assertEquals(driver.findElement(By.cssSelector("div.lepopup-element-html-content>h4")).getText(), "Thank you!");		
			Assert.assertTrue(driver.findElement(By.cssSelector("div.lepopup-element-html-content>p")).getText().contains("Your sign-up request was successful."));
			sleepInSecond(9);
		}
		
		
		
		// Không xuất hiện Popup
		String articleName = "Agile Testing Explained";
		driver.findElement(By.cssSelector("input#search-input")).sendKeys(articleName);
		sleepInSecond(2);
		driver.findElement(By.cssSelector("button#search-submit")).click();
		sleepInSecond(3);
		
		// Verify
		Assert.assertEquals(driver.findElement(By.cssSelector("ul.posts-items>li:first-child div h2")).getText(), articleName);		

	}

	// @Test
	public void TC_02_Random_Popup_Not_In_DOM_VNK_Edu() {
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(20);
		
		By contentBoxBackgroundPopup = By.cssSelector("div#tve-p-scroller");
		
		if (driver.findElement(contentBoxBackgroundPopup).isDisplayed()) {
			// Close popup
			driver.findElement(By.cssSelector("svg.tcb-icon")).click();
			sleepInSecond(3);
		}
		
		driver.findElement(By.cssSelector("button.btn.btn-danger")).click();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.getTitle(), "Lịch khai giảng các khóa học tại VNK EDU | VNK EDU");
	}

	@Test
	public void TC_03_() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(9);
		
		By popup = By.cssSelector("div#sign-up-form");
		// findElement -> sẽ bị fail nếu k tìm thấy element -> ném ra ngoại lệ NoSuchElementException
		// findelements -> k bị fail nếu k tìm thấy element -> trả về 1 list empty
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		if (driver.findElements(popup).size() > 0 && driver.findElements(popup).get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("input#popup-name")).sendKeys("soundOfHope");
			driver.findElement(By.cssSelector("input#popup-email")).sendKeys(emailAdress);
			driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("123456789");
			sleepInSecond(3);
			driver.findElement(By.cssSelector("button#close-popup")).click();
			sleepInSecond(3);
		}
		
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		sleepInSecond(4);
		
		String courseName = "Khóa học Thiết kế và Thi công Hệ thống BMS";
		driver.findElement(By.cssSelector("input#search-courses")).sendKeys(courseName);
		driver.findElement(By.cssSelector("button#search-course-button")).click();
		sleepInSecond(3);
		
		// Verify
		Assert.assertEquals(driver.findElements(By.cssSelector("div.wrap-courses>div")).size(), 1);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.course-content.hachium>h4")).getText(), courseName);
		
		
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
		return new Random().nextInt(9999);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
