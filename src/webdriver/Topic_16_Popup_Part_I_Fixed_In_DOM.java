package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Popup_Part_I_Fixed_In_DOM {
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
		
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);
		driver = new FirefoxDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01_Fixed_Popup_in_DOM_Ngoaingu() {
			driver.get("https://ngoaingu24h.vn/");
			
			WebElement loginPopup = driver.findElement(By.cssSelector("div#modal-login-v1 div.modal-content"));
			WebElement loginButton = driver.findElement(By.cssSelector("button.login_.icon-before"));
			
			Assert.assertFalse(loginPopup.isDisplayed());
			
			loginButton.click();
			sleepInSecond(2);
			
			Assert.assertTrue(loginPopup.isDisplayed());
			
			driver.findElement(By.cssSelector("input#account-input")).sendKeys("soundOfHope");
			driver.findElement(By.cssSelector("input#password-input")).sendKeys("soundOfHope");
			driver.findElement(By.cssSelector("button.btn-login-v1.buttonLoading")).click();
			sleepInSecond(2);
			
			Assert.assertEquals(driver.findElement(By.cssSelector("div.modal-body div.row.error-login-panel")).getText(), "Tài khoản không tồn tại!");		
	
			driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
			
			Assert.assertFalse(loginPopup.isDisplayed());
	}

	@Test
	public void TC_02_Fixed_Popup_in_DOM_KyNa() {
		driver.get("https://skills.kynaenglish.vn/");
		
		WebElement loginButton = driver.findElement(By.cssSelector("a.login-btn"));
		WebElement loginPopup = driver.findElement(By.cssSelector("div#k-popup-account-login"));
		
		// Undisplayed
		Assert.assertFalse(loginPopup.isDisplayed());
		
		loginButton.click();
		sleepInSecond(2);
		
		// Displayed
		Assert.assertTrue(loginPopup.isDisplayed());
		
		// Input data
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("soundOfHope");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("soundOfHope");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(1);
		
		// Verify login form message
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		sleepInSecond(1);
		
		Assert.assertFalse(loginPopup.isDisplayed());
		
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
