package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_P1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	// By chưa đi tìm element ngay
	By emailTextboxby = By.id("username");
	By passwordTextboxby = By.id("password");
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_WebElement() {
		WebElement element = driver.findElement(By.id("small-searchterms"));
		
		// Dùng cho các textbox/ textarea/ dropdown (editable)
		// Xóa data trước khi input text
		element.clear();   //*
		
		
		// Dùng cho các textbox/ textarea/ dropdown (editable)
		// Nhập liệu
		element.sendKeys("");    //**
		
	    // Click vào button/ link/ checkbox/ radio/ image/...
		element.click();   //**
		
		String searchAttribute = element.getAttribute("placeholder");
		// Search store
		
// -----------------------------------------------------------------------------------------------
		
		
		// GUI: font/ size/ color/ location/ position/...
		element.getCssValue("background-color");  //**
			// Vị trí của element so vs web (bên ngoài)
		element.getLocation();
			// Kích thước của element (bên trong)
		element.getSize();
			// Rect = Location + Size
		element.getRect();
		
		
		// Chụp hình khi testcase fail 
		element.getScreenshotAs(OutputType.BASE64);    //**
		element.getScreenshotAs(OutputType.BYTES);
		element.getScreenshotAs(OutputType.FILE);    
		
		
		
		// Cần lấy ra tagname HTML của element đó => truyền vào locator khác
		String emailTextboxTagname = driver.findElement(By.cssSelector("#email")).getTagName();
		driver.findElement(By.xpath("//" + emailTextboxTagname + "[@id='password']"));
		
		
		// Lấy text từ Error message/ success message/ label/ header...
		element.getText();  //**
		
		/* Khi nào dùng getText / getAttribute
		 * * - Khi value cần lấy ở trong -> getAttribute
		 * * - Khi value cần lấy ở ngoài -> getText
		 */

// -----------------------------------------------------------------------------------------------
		
		// Dùng để verify xem 1 element có hiển thị đc hay không
		// Phạm vi: tất cả element
		Assert.assertTrue(element.isDisplayed());  //**
		Assert.assertFalse(element.isDisplayed());
		
		
		// Dùng để verify xem 1 element đc chọn hay chưa
		// Phạm vi: Checkbox/ Radio
		Assert.assertTrue(element.isSelected());  //*
		Assert.assertFalse(element.isSelected());
		
		
		
		// Các element nằm trong thẻ <form>
		// Tương ứng vs hành vi của End User
		element.submit();
		
		
	}

	@Test
	public void TC_02_Register() {
		// opening Register page
		driver.get("");  
		
		driver.findElement(emailTextboxby).sendKeys("");
		
		driver.quit();		
	}
	
	
	@Test
	public void TC_03_Login() {
		// opening Login page
		driver.get("");  
		
		driver.findElement(emailTextboxby).sendKeys("");
		driver.findElement(passwordTextboxby).sendKeys("");
	
	
		driver.quit();		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
