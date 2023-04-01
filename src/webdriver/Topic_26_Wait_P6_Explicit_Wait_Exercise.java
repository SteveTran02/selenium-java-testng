package webdriver;

import java.io.File;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_Wait_P6_Explicit_Wait_Exercise {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	
	// Declare photo name
	String caoHungPhoto = "caohung.jpg";
	String daiNamPhoto = "dainam.jpg";
	String taiPeiPhoto = "taipei.jpg";
	
	// Declare photo path
	String caoHungPhotoPath = projectPath + File.separator + "uploadFiles" + File.separator + caoHungPhoto;
	String daiNamPhotoPath = projectPath + File.separator + "uploadFiles" + File.separator + daiNamPhoto;
	String taiPeiPhotoPath = projectPath + File.separator + "uploadFiles" + File.separator + taiPeiPhoto;
	
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
	}

	public void TC_01_Telerik () {
		
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		// Wait cho ngày cần click là clickable
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='10']/parent::td")));
		
		// Wait text visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")));
		
		// in ra Selected Dates
		WebElement todayText = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));
		System.out.println(todayText.getText());
		Assert.assertEquals(todayText.getText(), "No Selected Dates to display.");
		
		// click vào ngày hiện tại
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='10']/parent::td"))).click();

		
		// Wait Loading icon biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']/parent::div[not(@style='display:none;')]")));
		
		// wait cho ngày cần chọn là selected
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='10']/parent::td[@class='rcSelected']")));
		
		// Wait text visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")));
				
		todayText = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));
		System.out.println(todayText.getText());
		Assert.assertEquals(todayText.getText(), "Monday, April 10, 2023");
		
	}
	
	@Test
	public void TC_02_Go_File() {
		
		driver.get("https://gofile.io/welcome");
		
		// Đang ở trang Home
		// Wait cho Loading icon ở trang Main biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#mainContent>div>div div.spinner-border")));
		
		// Wait cho Upload Files button clickable + click button
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-secondary btn-lg']"))).click();
		
		// Đang ở trang uploadFiles
		// Wait cho loading icon ở trang UpLoadFiles biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#mainContent>div>div>div.spinner-border")));
		
		// Upload 3 file 1 time
		By uploadFile = By.xpath("//input[@type='file']");
		driver.findElement(uploadFile).sendKeys(caoHungPhotoPath + "\n" + daiNamPhotoPath + "\n" + taiPeiPhotoPath);
		
		// Wait cho tất cả progess bar biến mất -> upload tất cả file thành công
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress-bar"))));
		
		// Wait cho success message hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'mainUploadSuccess')]//div[text()='Your files have been successfully uploaded']")));
		
		// Wait + click vào link
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'mainUploadSuccessLink')]//a[@class='ajaxLink']"))).click();
	
		// Wait cho table chứa các file đc upload visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#filesContentTableContent")));
		
		// Verify download button	
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + caoHungPhoto + "']/parent::a/parent::div/following-sibling::div//span[text()='Download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + daiNamPhoto + "']/parent::a/parent::div/following-sibling::div//span[text()='Download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + taiPeiPhoto + "']/parent::a/parent::div/following-sibling::div//span[text()='Download']")).isDisplayed());

		// Verify play button
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + caoHungPhoto + "']/parent::a/parent::div/following-sibling::div//span[text()='Play']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + daiNamPhoto + "']/parent::a/parent::div/following-sibling::div//span[text()='Play']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + taiPeiPhoto + "']/parent::a/parent::div/following-sibling::div//span[text()='Play']")).isDisplayed());

		
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
