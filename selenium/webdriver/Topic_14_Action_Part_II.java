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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Action_Part_II {
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

		driver = new FirefoxDriver();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Click_And_Hold_Block() {
		
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		// Step 1: click vào source 
		// Step 2: vẫn giữ chuột chưa nhả ra
		// Step 3: Di chuột tới target 
		// Step 4: Nhả chuột trái ra => Execute (perform)
		
		// Action
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol.ui-selectable>li"));
		
		action.clickAndHold(listNumber.get(0)).moveToElement(listNumber.get(3)).release().perform();
		sleepInSecond(2);
		
		// Verify
		List<WebElement> listSelectedNumber = driver.findElements(By.cssSelector("ol.ui-selectable>li.ui-selected"));
		Assert.assertTrue(listSelectedNumber.size()==4);
	}

	@Test
	public void TC_02_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		// Press Control Key for Windows and MacOS
		Keys key = null;
		if (osName.contains("Windows")) {
			key = Keys.CONTROL;
		} else {
			key = Keys.COMMAND;
		}
		
		//List chứa element
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol.ui-selectable>li"));
		

		// Action
		// Nhấn Control xuống
		action.keyDown(key).perform();
		// Click chọn các số random
		action.click(listNumber.get(0))
			  .click(listNumber.get(2))
		      .click(listNumber.get(5))
		      .click(listNumber.get(7))
		      .click(listNumber.get(8))
		      .click(listNumber.get(10));
		// Nhả phím Control ra
		action.keyUp(key).perform();
		
		sleepInSecond(3);
		
		// Verify
		List<WebElement> listSelectedNumber = driver.findElements(By.cssSelector("ol.ui-selectable>li.ui-selected"));
		Assert.assertTrue(listSelectedNumber.size()==6);
		
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
