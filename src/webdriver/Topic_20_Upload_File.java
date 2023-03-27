package webdriver;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Upload_File {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	// Declare photo name
	String caohungPhoto = "caohung.jpg";
	String dainamPhoto = "dainam.jpg";
	String taipeiPhoto = "taipei.jpg";
	
	// Declare photo path
	String caohungPhotoPath = projectPath + File.separator + "uploadFiles" + File.separator + caohungPhoto;
	String dainamPhotoPath = projectPath + File.separator + "uploadFiles" + File.separator + dainamPhoto;
	String taipeiPhotoPath = projectPath + File.separator + "uploadFiles" + File.separator + taipeiPhoto;
	/* File.separator
	 * The system-dependent default name-separator character. 
	 * This field isinitialized to contain the first character of the value of the systemproperty file.separator. 
	 * On UNIX systems the value of thisfield is '/'; 
	 * on Microsoft Windows systems it is '\\'.
	 */
	
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Upload_File_1_time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		By uploadFile = By.xpath("//input[@type='file']");
		
		// Upload image
		// sendkeys into input[@type='file']
		driver.findElement(uploadFile).sendKeys(caohungPhotoPath);
		sleepInSecond(1);
		driver.findElement(uploadFile).sendKeys(dainamPhotoPath);
		sleepInSecond(1);
		driver.findElement(uploadFile).sendKeys(taipeiPhotoPath);
		sleepInSecond(1);
		
		// Verify các file được load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//table[@class='table table-striped']//p[text()='" + caohungPhoto + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//table[@class='table table-striped']//p[text()='" + dainamPhoto + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//table[@class='table table-striped']//p[text()='" + taipeiPhoto + "']")).isDisplayed());
		

		// Click upload cho từng file
		List<WebElement> startButtonUpLoad = driver.findElements(By.cssSelector("table.table-striped button.btn-primary"));
		for (WebElement start : startButtonUpLoad) {
			start.click();
			sleepInSecond(2);
		}
		

		// Verify các file được upload lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + caohungPhoto + "']")).isDisplayed());
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + dainamPhoto + "']")).isDisplayed());
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + taipeiPhoto + "']")).isDisplayed());
		sleepInSecond(1);
		
		// Verify các image được upload lên là hình thực
		Boolean isCaoHungimage =  (Boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' "
				+ "&& arguments[0].naturalWidth > 0", driver.findElement(By.xpath("//a[@title='" + caohungPhoto + "']/img")));
		Assert.assertTrue(isCaoHungimage);
		sleepInSecond(1);
		
		Boolean isDaiNamimage =  (Boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' "
				+ "&& arguments[0].naturalWidth > 0", driver.findElement(By.xpath("//a[@title='" + dainamPhoto + "']/img")));
		Assert.assertTrue(isDaiNamimage);
		sleepInSecond(1);
		
		Boolean isTaipeiImage =  (Boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' "
				+ "&& arguments[0].naturalWidth > 0", driver.findElement(By.xpath("//a[@title='" + taipeiPhoto + "']/img")));
		Assert.assertTrue(isTaipeiImage);
		sleepInSecond(1);
	}

	@Test
	public void TC_02_Upload_Multiple_File_1_time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		By uploadFile = By.xpath("//input[@type='file']");
		
		// Upload image (Load 3 file image 1 time)
		driver.findElement(uploadFile).sendKeys(caohungPhotoPath + "\n" + dainamPhotoPath + "\n" + taipeiPhotoPath);
		sleepInSecond(5);
		
		// Verify các file được load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + caohungPhoto +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + dainamPhoto +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + taipeiPhoto +"']")).isDisplayed());
		
		
		// Click upload cho từng file
		List<WebElement> startButton = driver.findElements(By.cssSelector("table.table-striped button.btn-primary"));
		for (WebElement start : startButton) {
			start.click();
			sleepInSecond(2);
		}
		
		
		// Verify các file được upload lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + caohungPhoto +"']")).isDisplayed());	
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + dainamPhoto +"']")).isDisplayed());	
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + taipeiPhoto +"']")).isDisplayed());	
		
		// Verify các image được upload lên là hình thực
		// Verify các image được upload lên là hình thực
		Boolean isCaoHungimage =  (Boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' "
				+ "&& arguments[0].naturalWidth > 0", driver.findElement(By.xpath("//a[@title='" + caohungPhoto + "']/img")));
		Assert.assertTrue(isCaoHungimage);
		sleepInSecond(1);
		
		Boolean isDaiNamimage =  (Boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' "
				+ "&& arguments[0].naturalWidth > 0", driver.findElement(By.xpath("//a[@title='" + dainamPhoto + "']/img")));
		Assert.assertTrue(isDaiNamimage);
		sleepInSecond(1);
		
		Boolean isTaipeiImage =  (Boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' "
				+ "&& arguments[0].naturalWidth > 0", driver.findElement(By.xpath("//a[@title='" + taipeiPhoto + "']/img")));
		Assert.assertTrue(isTaipeiImage);
		sleepInSecond(1);
		
		
	}

	@Test
	public void TC_03_() {
		
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
