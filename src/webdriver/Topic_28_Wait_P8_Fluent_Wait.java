package webdriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_28_Wait_P8_Fluent_Wait {
	WebDriver driver;
	FluentWait<WebDriver> fluentWaitDriver;
	FluentWait<WebElement> fluentWaitElement;
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
		
		// Selenium version 2x/3x
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
	}
	
	public void TC_01_Get_Text() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		fluentWaitDriver = new FluentWait<WebDriver>(driver);

	// Set time and exception
		// Tổng thời gian chờ bao lâu
		fluentWaitDriver.withTimeout(Duration.ofSeconds(15))
		// Thời gian tìm lại là bao lâu (polling)
		.pollingEvery(Duration.ofMillis(100))
		// Nếu trong quá trình đi tìm Element mà không thấy sẽ throw ngoại lệ
		// Ignore Exception này trong code
		.ignoring(NoSuchElementException.class);
		
	// Condition
		String helloWorldText = fluentWaitDriver.until(new Function<WebDriver, String>() {
			public String apply(WebDriver driver) {
				String text = driver.findElement(By.cssSelector("div#finish>h4")).getText();
				return text;
			}
		});
		
		Assert.assertEquals(helloWorldText, "Hello World!");	
	}

	public void TC_02_Equals() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		fluentWaitDriver = new FluentWait<WebDriver>(driver);

	
		fluentWaitDriver.withTimeout(Duration.ofSeconds(15))
		.pollingEvery(Duration.ofMillis(100))
		.ignoring(NoSuchElementException.class);
		
		fluentWaitDriver.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				String text = driver.findElement(By.cssSelector("div#finish>h4")).getText();
				return text.equals("Hello World!");
			}
		});
		
	}
	
	public void TC_03_IsDisplayed() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		fluentWaitDriver = new FluentWait<WebDriver>(driver);

	
		fluentWaitDriver.withTimeout(Duration.ofSeconds(15))
		.pollingEvery(Duration.ofMillis(100))
		.ignoring(NoSuchElementException.class);
		
		fluentWaitDriver.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed();
			}
		});
		
	}
	
	@Test
	public void TC_04_Element() {
		
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		fluentWaitElement = new FluentWait<WebElement>(driver.findElement(By.cssSelector("div#javascript_countdown_time")));
		
		// set timeout and ignore
		fluentWaitElement.withTimeout(Duration.ofSeconds(15))
		.pollingEvery(Duration.ofMillis(300))
		.ignoring(NoSuchElementException.class);
		// condition
		boolean status = fluentWaitElement.until(new Function<WebElement, Boolean>() {
			public Boolean apply(WebElement element) {
				String text = element.getText();
				System.out.println(text);
				return text.endsWith("00");
			}
		});
		
		// boolean => Boolean (Wrapper classes)
		
		Assert.assertTrue(status);

	}

	public WebElement findElement(final By locator) {
		FluentWait<WebDriver> fluentWaitDriver = new FluentWait<WebDriver>(driver);
		
		fluentWaitDriver.withTimeout(Duration.ofSeconds(15))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class);
		
		return fluentWaitDriver.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
