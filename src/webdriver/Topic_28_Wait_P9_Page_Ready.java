package webdriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_28_Wait_P9_Page_Ready {
	WebDriver driver;
	Actions action;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
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
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);
	
		driver.manage().window().maximize();
	}
	
	
	public void TC_01_API_Orange_HRM() {
		
		driver.get("https://api.orangehrm.com/");
		
		// Wait cho icon loading/ ajax loading/ progress bar/ improgress loading biến mất
		// vì nó biến mất thì page mới loading hết data về thành công
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loader div.spinner")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#content div#project div.pull-left h1")).getText(), "OrangeHRM REST API Documentation");
		
		}

	
	public void TC_02_Demo_Noncommerce() {
		
		driver.get("https://admin-demo.nopcommerce.com");
		
		// Input valid data
		driver.findElement(By.cssSelector("input#Email")).clear();
		driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
		
		driver.findElement(By.cssSelector("input#Password")).clear();
		driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
		
		driver.findElement(By.cssSelector("button.login-button")).click();
		
		// Wait for page ready
		Assert.assertTrue(waitForAjaxBusyLoadingInvisible());
		
		// Logout
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Logout']"))).click();
		
		// Verify login page
		explicitWait.until(ExpectedConditions.titleContains("Your store. Login"));
	}
	
	
	public void TC_03_Demo_Noncommerce_Using_Javascript() {
		
		driver.get("https://admin-demo.nopcommerce.com");
		
		// Input valid data
		driver.findElement(By.cssSelector("input#Email")).clear();
		driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
		
		driver.findElement(By.cssSelector("input#Password")).clear();
		driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
		
		driver.findElement(By.cssSelector("button.login-button")).click();
		
		// Wait for page ready
		Assert.assertTrue(isPageLoadedSuccess());
		
		// Logout
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Logout']"))).click();
		
		// Verify login page
		explicitWait.until(ExpectedConditions.titleContains("Your store. Login"));	
	}
	
	
	@Test
	public void TC_04_Blog_Test_Project() {
		
		driver.get("https://blog.testproject.io/");
		
		// Hover chuột vào Search Textbox để page đc ready
		action.moveToElement(driver.findElement(By.cssSelector("section#search-2 input.search-field"))).perform();
		
		// Verify page ready
		Assert.assertTrue(isPageLoadedSuccess());
		
		
		// Search with keyword is Selenium
		String keyword = "Selenium";
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys(keyword);
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		
		// Wait cho page Search Results visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.main-headers-wrap>h1")));
		
		// Verify page ready
		Assert.assertTrue(isPageLoadedSuccess());
		
		List<WebElement> articleAll = driver.findElements(By.cssSelector("div.post-content>h3>a"));
		for (WebElement article : articleAll) {
			Assert.assertTrue(article.getText().contains(keyword));
		}
	
	}
	
	
	public boolean waitForAjaxBusyLoadingInvisible() {
		return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
	}
	
	// Wait for page loading success script jquery and readystate
	public boolean isPageLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		final JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
