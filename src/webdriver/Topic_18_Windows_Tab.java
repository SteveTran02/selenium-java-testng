package webdriver;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Windows_Tab {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAdress = "soundOfHope" + getRandomNumber() + "@gmail.com";
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
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Github() {
		// Driver đang ở trang Github
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Trả về ID của driver đang đứng tại đó _ Github
		String githubID = driver.getWindowHandle();
		System.out.println("Github ID = " + githubID);
		System.out.println("Page title_Github = " + driver.getTitle());

		// Click vào google link -> theo business nó sẽ mở trang google
		// Driver vẫn đang ở trang Github
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		System.out.println("Page title_Github = " + driver.getTitle());

		switchToWindowsByID(githubID);
		// Driver đang ở tại Google
		System.out.println("Page title_Google = " + driver.getTitle());

		// Quay về Github
		// Trả về ID của driver đang đứng - tại Google
		String googleID = driver.getWindowHandle();
		System.out.println("Google ID = " + googleID);
		switchToWindowsByID(googleID);
		sleepInSecond(2);
		System.out.println("Page title-Github = " + driver.getTitle());

	}

	// @Test
	public void TC_02_Github_Greater_Two_Windows_Tab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		String githubID = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);

		// Switch to Google page
		switchToWindowsByTitle("Google");
		sleepInSecond(2);
		System.out.println("Google title = " + driver.getTitle());

		driver.findElement(By.cssSelector("input.gLFyf")).sendKeys("Shen Yun Creation");
		driver.findElement(By.cssSelector("input.gLFyf")).sendKeys(Keys.ENTER);
		sleepInSecond(2);
		// Switch to Google result page_ Shen Yun Creation
		switchToWindowsByTitle("Shen Yun Creation - Google Search");
		driver.findElement(By.xpath("//h3[text()='Shen Yun Zuo Pin']")).click();
		sleepInSecond(2);

		// Switch to Github
		switchToWindowsByTitle("Selenium WebDriver");
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(2);

		// Switch to Tiki.vn
		switchToWindowsByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		driver.findElement(By.cssSelector("input.FormSearchStyle__InputRevamp-sc-1idbenb-5"))
				.sendKeys("muôn kiếp nhân sinh");
		driver.findElement(By.cssSelector("button.FormSearchStyle__ButtonRevamp-sc-1idbenb-6")).click();
		sleepInSecond(3);
		
		// Switch to Github
		switchToWindowsByTitle("Selenium WebDriver");
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
		sleepInSecond(2);

		// Switch to Lazada.vn
		switchToWindowsByTitle("Lazada - Mua Sắm Hàng Chất Giá Tốt Online");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("input.search-box__input--O34g")).sendKeys("muôn kiếp nhân sinh");
		sleepInSecond(2);
		
		
		// Switch to Github
		closeAllWindowWithoutParentID(githubID);
		
	}

	//@Test
	public void TC_03_Techpanda() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		sleepInSecond(4);
		
		// Add to compare Sony Xperia
		driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		sleepInSecond(3);
		// Verify message is displayed	
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");
		
		// Add to compare Samsung Galaxy
		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		sleepInSecond(3);
		// Verify message is displayed	
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
			
		// Click Compare button
		driver.findElement(By.xpath("//span[text()='Compare']")).click();
		
		// Switch to Compare page
		String mobilePageID = driver.getWindowHandle();
		switchToWindowsByID(mobilePageID);
		sleepInSecond(5);
		// Verify title of compare page
		String comparisonPageTitle = driver.getTitle();
		System.out.println("Comparison page title = " + driver.getTitle());
		Assert.assertEquals(comparisonPageTitle, "Products Comparison List - Magento Commerce");
		String ComparisonPageID = driver.getWindowHandle();

		// Close tab
		driver.close();
		// Switch to Mobile page
		//switchToWindowsByID(ComparisonPageID);
		driver.switchTo().window(mobilePageID);
		sleepInSecond(5);
		// Click Clear all compare
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		sleepInSecond(3);
		
		// Accept alert
		driver.switchTo().alert().accept();
		sleepInSecond(4);
		
		// Verify message is displayed	
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The comparison list was cleared.");
				
	}

	
	//@Test
	public void TC_04_Cambridge_Dictionary() {
		driver.get("https://dictionary.cambridge.org/us");
		sleepInSecond(5);
		String homePage = driver.getWindowHandle();
		driver.findElement(By.xpath("//span[@class='tb' and text()='Log in']")).click();
		sleepInSecond(4);
		
		// Switch to Login page
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			if(!id.equals(homePage)) {
				driver.switchTo().window(id);
				sleepInSecond(2);
			}
		}
		// Input valid data
		driver.findElement(By.xpath("//h2[text()='Log in with your email account']/following-sibling::div/input[@name='username']")).sendKeys(emailAdress);
		driver.findElement(By.xpath("//h2[text()='Log in with your email account']/following-sibling::div[@class='gigya-composite-control gigya-composite-control-password']/input")).sendKeys("123456789");
		sleepInSecond(3);
		driver.close();
		sleepInSecond(3);
		// Switch to Home page
		driver.switchTo().window(homePage);
		sleepInSecond(5);
		// Search new word
		driver.findElement(By.cssSelector("input#searchword")).sendKeys("Hope");
		driver.findElement(By.cssSelector("input#searchword")).sendKeys(Keys.ENTER);
		// driver.findElement(By.xpath("//button[@title='Search']")).click();
		sleepInSecond(4);
		
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='ti fs fs12 lmb-0 hw superentry']")).getText(),"Meaning of hope in English");
		
		
	}

	
	// Chỉ handle cho 2 tab/windows
	public void switchToWindowsByID(String pageID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			if (!id.equals(pageID)) {
				driver.switchTo().window(id);
				sleepInSecond(1);
			}
		}
	}

	// Handle cho cả 2 tab/windows trở lên
	public void switchToWindowsByTitle(String pageTitle) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			driver.switchTo().window(id);
			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(pageTitle)) {
				break;
			}
		}
	}

	// Close tất cả tab/windows ngoại trừ ParentID
	public void closeAllWindowWithoutParentID(String parentID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {			
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
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
