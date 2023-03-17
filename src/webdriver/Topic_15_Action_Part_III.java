package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Action_Part_III {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String dragDropHelperPath = projectPath + "\\dragAndDrop\\drag_and_drop_helper.js";
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		By doubleClickButton = By.xpath("//button[text()='Double click me']");
		// Scroll đến element Double click me
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(doubleClickButton));
		// Action double click button
		action.doubleClick(driver.findElement(doubleClickButton)).perform();
		sleepInSecond(2);
		
		// Verify
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
		
	}

	@Test
	public void TC_02_Right_Click_To_Element() {
				
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		sleepInSecond(2);
		
		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		sleepInSecond(2);
		// Verify element Quit: visible + hover
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
	
		// Click quit
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover"))).perform();
		sleepInSecond(2);
		// Verify
		driver.switchTo().alert().accept();
		sleepInSecond(2);
		// Verify element Quit is not displayed
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
			
		
	}

	@Test
	public void TC_03_Drag_And_Drop_Element_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement smallCircle = driver.findElement(By.xpath("//div[@data-role='draggable']"));
		WebElement bigCircle = driver.findElement(By.xpath("//div[@data-role='droptarget']"));
		
		Assert.assertEquals(bigCircle.getText(), "Drag the small circle here.");
		
		// Action
		action.dragAndDrop(smallCircle, bigCircle).perform();
		sleepInSecond(3);
		
		
		// Verify Text
		Assert.assertEquals(bigCircle.getText(), "You did great!");
		
		
		// Verify background color
		String bigCircleRGB = bigCircle.getCssValue("background-color");
		System.out.println(bigCircleRGB);
		
		String bigCircleHexa = Color.fromString(bigCircleRGB).asHex();	
		System.out.println(bigCircleHexa);
		
		bigCircleHexa = bigCircleHexa.toUpperCase();	
		System.out.println(bigCircleHexa);
		
		Assert.assertEquals(bigCircleHexa, "#03A9F4");
	}
	
	@Test
	public void TC_04_Drag_And_Drop_Element_HTML5() throws IOException {
		
		String jsHelper = getContentFile(dragDropHelperPath);
		
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		String sourceCss = "div#column-a";
		String targetCss = "div#column-b";
		
		jsHelper = jsHelper + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		
		// Drag A to B
		jsExecutor.executeScript(jsHelper);
		sleepInSecond(3);		
		// Verify
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='A']")).isDisplayed());

		
		// Drag B to A
		jsExecutor.executeScript(jsHelper);
		sleepInSecond(3);		
		// Verify
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());

		
	}

	
	@Test
	public void TC_05_Drag_And_Drop_Element_HTML5_Xpath() throws IOException, AWTException {

		driver.get("https://automationfc.github.io/drag-drop-html5/");
			
		// Drag A to B
		dragAndDropHTML5ByXpath("//div[@id='column-a']", "//div[@id='column-b']");
		sleepInSecond(3);		
		// Verify
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='A']")).isDisplayed());

		
		// Drag B to A
		dragAndDropHTML5ByXpath("//div[@id='column-b']", "//div[@id='column-a']");
		sleepInSecond(3);		
		// Verify
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());

		
	}
	

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
	
	
	public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
