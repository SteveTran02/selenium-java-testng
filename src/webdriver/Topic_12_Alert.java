package webdriver;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String authenFirefox = projectPath + "\\autoIT\\authen_firefox.exe";
	String authenChrome = projectPath + "\\autoIT\\authen_chrome.exe";
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		System.out.println(driver.toString());
		
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(3);
		// Cách 1: có thể switch qua và tương tác luôn
		// alert = driver.switchTo().alert();

		// Cách 2: wait và switch qua luôn
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		// Verify alert title đúng như mong đợi
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		// Accept alert
		alert.accept();
		sleepInSecond(2);
		// Verify message
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),
				"You clicked an alert successfully");

	}

	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

		// Wait and switch to Alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertTrue(alert.getText().equals("I am a JS Confirm"));
		// Cancel alert
		alert.dismiss();
		sleepInSecond(2);
		// Verify message
		Assert.assertTrue(driver.findElement(By.cssSelector("p#result")).getText().equals("You clicked: Cancel"));
	}

	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		String message = "Coding teaches you how thinking";
		alert.sendKeys(message);
		alert.accept();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + message);
	}

	@Test
	public void TC_04_Authentication_Alert_I() {
		// Truyền trực tiếp username and password vào url tự động signin
		// http:// + [username:passowrd@] + the-internet.herokuapp.com/basic_auth
		// driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		
		String userName = "admin";
		String passWord = "admin";
		
		
		driver.get("http://the-internet.herokuapp.com");
		
		String urlBasicAuth =  driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		System.out.println(urlBasicAuth);
		
		driver.get(passUserAndPassToUrl(urlBasicAuth, userName, passWord));
		sleepInSecond(5);
		
		// Verify success message
		Assert.assertTrue(driver.findElement(By.cssSelector("div.example")).isDisplayed());

	}

	@Test
	public void TC_04_Authentication_Alert_II() throws IOException {
		String userName = "admin";
		String passWord = "admin";
		
		if (driver.toString().contains("firefox")) {
			// Runtime.getRuntime().exec: thực thi 1 file .exe trong code Java
			Runtime.getRuntime().exec(new String[] { authenFirefox, userName, passWord });
		} else if (driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] { authenFirefox, userName, passWord });
		}
		
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		sleepInSecond(9);
		
		// Verify success message
		Assert.assertTrue(driver.findElement(By.cssSelector("div.example")).isDisplayed());
		
	}
		
    public String passUserAndPassToUrl(String url, String userName, String passWord) {
		String[] arrayUrl = url.split("//");
		return arrayUrl[0] + "//" + userName + ":" + passWord + "@" + arrayUrl[1];
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
			;
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
