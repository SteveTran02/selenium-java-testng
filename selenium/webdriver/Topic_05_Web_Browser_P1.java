package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_P1 {
	WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		// Tương tác với Browser thì sẽ thông qua biến WebDriver _ driver
		
		// Tương tác với Element thì sẽ thông qua biến WebElement _ element
	}

	@Test
	public void TC_01_() {
		
		// Java Document
// 1. ------------------------------------------------------------------------------------
		driver.close();
		driver.quit();//**
		
// 2. ------------------------------------------------------------------------------------		
		// Có thể lưu nó vào 1 biến để sử dụng cho các step sau -> dùng lại nhiều lần
			// Clean Code
			// Code đúng, chạy được
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
		emailTextbox.clear();//**
		emailTextbox.sendKeys("");//**
		
			// Bad Code
		driver.findElement(By.xpath("//input[@id='username']")).clear();
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("");
		
		// Có thể sử dụng luôn (ko cần tạo biến)
		driver.findElement(By.xpath("@input[@id='button']")).click();//**
		
		
		// findElement's'		
		List<WebElement> checkboxes = driver.findElements(By.xpath("@input[@id='checkbox']")); //**
		
// 3. GET ------------------------------------------------------------------------------------		
		// Mở ra URL 
		driver.get("www.google.com"); //**
		    // Click vào link vietnamese
		driver.findElement(By.xpath("//div[@id='SIvCob']/a[text()='Tiếng Việt']")).click();
		    // lấy ra link hiện tại
		driver.getCurrentUrl(); //*
		    // kiểm tra page hiện tại vs page ban đầu
		Assert.assertEquals(driver.getCurrentUrl(), "www.google.com");
		
		
		// Trả về source code HTML của page hiện tại
		driver.getPageSource();
		Assert.assertTrue(driver.getPageSource().contains("How Search works"));
		
		// Trả về Title của page hiện tại
		driver.getTitle();
		Assert.assertEquals(driver.getTitle(), "Google");
		
		
		
		// Webdriver API - Windows/ Tabs
		    // Lấy ra được ID của Windows/ Tab mà driver đang đứng (Active)
		 String searchWindowID = driver.getWindowHandle();   
		 
		    // Lấy ra ID tất cả Windows/ Tab
		 Set<String> allIDs  = driver.getWindowHandles();
		 
		 // Cookie and cache
		 Options opt = driver.manage();			// kiểu dữ liệu trả về Option interface
		 	// Login thành công lưu lại
		 opt.getCookies();
		 	// testcase khác -> set cookie lại -> k cần phải login nữa
		 opt.logs();
		 
		 
// 4. driver.manage() -> Timeouts ------------------------------------------------------------------------------------				 
		 
		 Timeouts time = opt.timeouts();
		 // Implicit wait and depend on: Find element/ Find elements
		 // Khoảng thời gian chờ Element xuất hiện trong x giây
		 time.implicitlyWait(5, TimeUnit.SECONDS);  //**
		// Khoảng thời gian chờ Page load trong x giây
		 time.pageLoadTimeout(5, TimeUnit.SECONDS);
		 
		// Webdriver API - Javacript Executor (JavacriptExecutor library)
		 	// Khoảng thời gian chờ script được thực thi trong trong x giây
		 time.setScriptTimeout(5, TimeUnit.SECONDS);
	
		 
// 5. driver.manage() -> Windows ------------------------------------------------------------------------------------				 
		 Window win = opt.window();
		 
		 win.fullscreen(); 
		 win.maximize(); //**
		 
		 // Test FUI: Function
		 // Test GUI
		 win.getPosition();
		 win.getSize();
		 
// 6. Navigation ------------------------------------------------------------------------------------				 
		 Navigation nav = driver.navigate();
		 nav.back();
		 nav.forward();
		 nav.refresh();
		 nav.to("www.google.com");
		 
//7. Switch To ------------------------------------------------------------------------------------			 
		 TargetLocator tar = driver.switchTo();
		 
		 // Webdriver API - Alert/ Authentication Alert (Alert library)
		 tar.alert();
		// Webdriver API - Frame/ Iframe (Frame library)
		 tar.frame("");
		// Webdriver API - Window/ Tabs
		 tar.window("");
		 
		
		 
	}

}
