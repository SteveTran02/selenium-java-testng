package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Action_Part_I {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Actions action;
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
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Hover_To_Element_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");

		action.moveToElement(driver.findElement(By.cssSelector("#age"))).perform();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
				"We ask for your age only for statistical purposes.");
	}

	@Test
	public void TC_02_Myntra() {
		driver.get("http://www.myntra.com/");
		sleepInSecond(9);
		// Hover mouse to Kids tab
		action.moveToElement(driver.findElement(By.xpath("//header[@class='desktop-container']//a[text()='Kids']")))
				.perform();
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		sleepInSecond(3);

		// Verify success message
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Kids Home Bath']")).getText(),
				"Kids Home Bath");

	}

	@Test
	public void TC_03_Fahasha() {
		driver.get("https://www.fahasa.com/");
		sleepInSecond(10);

		// Hover mouse to icon Menu
		action.moveToElement(driver.findElement(By.xpath("//span[@class='icon_menu']"))).perform();
		sleepInSecond(3);
		// Hover mouse to submenu Sach trong nuoc
		action.moveToElement(driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//span[text()='Sách Trong Nước']"))).perform();
		sleepInSecond(3);
		// Click to 
		driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Quản Trị - Lãnh Đạo']")).click();
		sleepInSecond(9);
		// Verify
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Quản Trị - Lãnh Đạo']")).isDisplayed());
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
