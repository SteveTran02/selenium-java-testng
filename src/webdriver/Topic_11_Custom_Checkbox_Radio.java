package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Custom_Checkbox_Radio {
	WebDriver driver;
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
		
		// Luôn khởi tạo sau biến driver	
		jsExecutor = (JavascriptExecutor) driver;  
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Using_Javascript() {
		
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(3);
		
		// Thẻ Input bị ẩn nhưng vẫn dùng để click
		// Hàm click() của WebElement sẽ không thao tác vào element bị ẩn được
		// => dùng 1 hàm click của Javascript để click (click vào element bị ẩn được)
		// Selenium cung cấp 1 thư viện để có thể nhúng các đoạn code JS vào test script  --> JavascriptExecutor
		// Thẻ <input> dùng để verify được vì hàm isSelected() chỉ work vs <input>
		
		By radioButton = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		// Thao tác chọn 
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(radioButton));
		
		// Verify đã chọn thành công
		Assert.assertTrue(driver.findElement(radioButton).isSelected());
		
	}

	@Test
	public void TC_02_Google_Docs() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInSecond(3);
		By radioButton = By.cssSelector("div[aria-label='Cần Thơ']");
		By checkbox = By.cssSelector("div[aria-label='Quảng Nam']");
		
		
	
		// Thao tác chọn 
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(radioButton));
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkbox));
		sleepInSecond(2);

		// Verify label Can Tho isDisplayed
		// Cach 1:
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Cần Thơ'][aria-checked='true']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Quảng Nam'][aria-checked='true']")).isDisplayed());
		// Cach 2:
		Assert.assertEquals(driver.findElement(radioButton).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(checkbox).getAttribute("aria-checked"), "true");
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
