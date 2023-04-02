package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Wait_P7_Mix_Implicit_Explicit {
	WebDriver driver;
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
		driver.manage().window().maximize();
	}
	
	public void TC_01_Element_Found() {
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 15);
		driver.get("https://safechat.com/");
		
		// Implicit Wait
		System.out.println("1- Start: " + getDateTimeNow());
		driver.findElement(By.xpath("//input[@name='loginEmail']"));
		System.out.println("1- End: " + getDateTimeNow());	
		
		// Explicit Wait
		System.out.println("2- Start: " + getDateTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='loginEmail']")));
		System.out.println("2- End: " + getDateTimeNow());
	}
	
	public void TC_02_Element_Not_Found_Only_Implicit() {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://safechat.com/");
		
		
		// case 3: không tìm thấy element nào hết
				// ban đầu k tìm thấy
				// sau 0,5s tìm lại
				// nếu k tìm thấy chờ 0,5s sau tìm lại
				// hết timeout k tìm thấy
		System.out.println("1- Start: " + getDateTimeNow());
		driver.findElement(By.xpath("//input[@name='login']"));
		System.out.println("1- End: " + getDateTimeNow());
	}
	
	@Test
	public void TC_03_Element_Not_Found_Implicit_Explicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://safechat.com/");
		
		// Implicit and Explicit
		System.out.println("TC_03- Start: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='automationTesting']")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("TC_03- End: " + getDateTimeNow());
	}

	public void TC_04__Element_Not_Found_Only_Explicit_By() {
		// Nếu như k set implicit thì mặc định = Os
		// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("TC_04__Element_Not_Found_Only_Explicit_By");
		
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://safechat.com/");
		
		// Implicit and Explicit
		System.out.println("TC_04- Start: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='automationTesting']")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("TC_04- End: " + getDateTimeNow());
	}
	
	public void TC_05_Element_Not_Found_Only_Explicit_WebElement() {
		// Nếu như k set implicit thì mặc định = Os
		// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("TC_05_Element_Not_Found_Only_Explicit_WebElement");
		
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://safechat.com/");
		
		// Implicit and Explicit
		System.out.println("TC_05- Start: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='automationTesting']"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("TC_05- End: " + getDateTimeNow());
	}
	
	public void TC_06_Element_Not_Found_Only_Explicit_WebElement() {
		// set implicit = 5s
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("TC_06_Element_Not_Found_Only_Explicit_WebElement");
		
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://safechat.com/");
		
		// Implicit and Explicit
		System.out.println("TC_06- Start: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='automationTesting']"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("TC_06- End: " + getDateTimeNow());
	}
	
	public String getDateTimeNow() {
		java.util.Date date=new java.util.Date();
		return date.toString();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
