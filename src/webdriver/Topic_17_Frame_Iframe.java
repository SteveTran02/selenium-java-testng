package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Frame_Iframe {
	WebDriver driver;
	Actions action;
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
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Iframe_KynaEnglish() {
		driver.get("https://skills.kynaenglish.vn/");
		By iframeKynaFB = By.cssSelector("div.fanpage iframe");
		By chatIframe = By.cssSelector("div#cs-live-chat iframe");
		// Verify Iframe FB Kyna is displayed 
		Assert.assertTrue(driver.findElement(iframeKynaFB).isDisplayed());
		
		// Switch to iframe Fb Kyna
		driver.switchTo().frame(driver.findElement(iframeKynaFB));
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(), "165K likes");

		// Switch back to Main frame (Parent)
		driver.switchTo().defaultContent();
		sleepInSecond(2);
		// Chat iframe
		driver.switchTo().frame(driver.findElement(chatIframe));
		// Click chat iframe
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div.button_bar")));	
		sleepInSecond(3);
		// Input valid data
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("sound of hope");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("123456789");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("Good evening");
		sleepInSecond(3);
		driver.findElement(By.cssSelector("div.overlay")).click();
		sleepInSecond(3);
		
		// Switch back to Main frame (Parent)
		driver.switchTo().defaultContent();
		
		
		// Search
		String keyWord = "Excel";
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(keyWord);
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepInSecond(5);
		
		// Verify
		List<WebElement> courseNames = driver.findElements(By.cssSelector("div.content h4"));
		
		for (WebElement course : courseNames) {
			System.out.println(course.getText());
			Assert.assertTrue(course.getText().contains(keyWord));
		}
	}

	
	public void TC_02_Iframe() {
		// Trang 1 chứa iframe 2
		driver.switchTo().frame("2");
		// iframe 2 chứa iframe 3
		driver.switchTo().frame("3");
		// iframe 3 về 2
		driver.switchTo().parentFrame();
		// iframe 2 về 1
		driver.switchTo().defaultContent();
	}

	@Test
	public void TC_03_Frame_HDFC_Bank() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		String userName = "soundOfHope";
		String passWord = "soundOfHope";
		
		// Switch to frame
		driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='login_page']")));
		// Handle with user ID textbox
		driver.findElement(By.cssSelector("input.form-control")).sendKeys(userName);
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(9);
		
		// Switch về default content
		driver.switchTo().defaultContent();
		
		// Verify password textbox is displayed
		Assert.assertTrue(driver.findElement(By.cssSelector("input#keyboard")).isDisplayed());
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
