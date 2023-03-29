package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_Wait_P2_FindElement {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	List<WebElement> elements;

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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://www.facebook.com/reg/");

	}

	
	public void TC_01_FindElement() {
		// 3 cases
		// case 1: nếu tìm đc nhưng chỉ có 1 element đc tìm thấy
		// FindElement sẽ apply timeout của implicitlyWait
		// Vì nó vào là tìm thấy element ngay nên k cần chờ hết timeout là 10s
		driver.findElement(By.cssSelector("input[name='firstname']"));

		// case 2: tìm thấy nhiều hơn 1 element
		// Sẽ luôn lấy element đầu tiên để sử dụng
		// vì vào là tìm đc element ngay nên k cần chờ hết timeout 10s
		driver.findElement(By.cssSelector("input[type='text']")).sendKeys("soundOfHope");
		;

		// case 3: không tìm thấy element nào hết
		// ban đầu k tìm thấy
		// sau 0,5s tìm lại
		// nếu k tìm thấy chờ 0,5s sau tìm lại
		// hết timeout k tìm thấy
		// throw 1 exception: NoSuchElement - không có element nào hết
		driver.findElement(By.xpath("//div[text()=\"What's your name?\"]"));

	}

	@Test
	public void TC_02_FindElements() {

		// case 1: nếu tìm đc nhưng chỉ có 1 element đc tìm thấy
		// FindElements sẽ apply timeout của implicitlyWait
		// Vì nó vào là tìm thấy element ngay nên k cần chờ hết timeout là 10s
		elements = driver.findElements(By.cssSelector("input[name='firstname']"));

		// case 2: tìm thấy nhiều hơn 1 element
		// nó sẽ lấy ra hết element đc tìm thấy
		elements = driver.findElements(By.cssSelector("input[type='text']"));
		System.out.println("case 2: tìm thấy nhiều hơn 1 element: " + elements.size());

		// case 3: không tìm thấy element nào hết
		// ban đầu k tìm thấy
		// sau 0,5s tìm lại
		// nếu k tìm thấy chờ 0,5s sau tìm lại
		// hết timeout k tìm thấy
		// đưa ra empty list
		elements = driver.findElements(By.xpath("//div[text()=\"What's your name?\"]"));
		System.out.println("case 3: không tìm thấy element nào hết: " + elements.size());
		Assert.assertTrue(elements.size()==0);

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

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
