package webdriver;

import java.awt.RenderingHints.Key;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Handle_Textbox_Textarea_P1 {
	WebDriver driver;
	Random rand;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstname, lastname, username, numberPassPort, password, confirmPassword, comments, employeeID;
	
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		action = new Actions(driver);
		rand = new Random();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		// Global variables
		firstname = "Dai";
		lastname = "Vi";
		employeeID = String.valueOf(rand.nextInt(9999));
		username = "DaiVi" + employeeID ;
		numberPassPort = "40517-402-96-7202";
		password = "Daivi123@";
		confirmPassword = "Daivi123@";
		comments = "Coding so very \nimportant";		
	}


	@Test
	public void TC_01() {
		// Step 1
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		// Step 2
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(10);		
		
		// Step 3
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		sleepInSecond(9);
		
		// Step 4
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(9);
		
		// Step 5: Input valid data into firstname and lastname, employeeID textbox
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(firstname);
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lastname);
		
		   // Input imployeeID
		WebElement employeeIDTextbox = driver.findElement(By.xpath("//label[@class='oxd-label' and text()='Employee Id']/parent::div/following-sibling::div/input"));
		employeeIDTextbox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		employeeIDTextbox.sendKeys(Keys.DELETE);
		employeeIDTextbox.sendKeys(employeeID);
		sleepInSecond(5);
		
		
		// Click Create Login Details
		driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//span")).click();
		sleepInSecond(5);
		
		
		
		// Input valid data into username, password, confirm password textbox
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(username);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(confirmPassword);
		
		// Step 6: Click Save button
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInSecond(9);	
		
		
		// Step 7: Verify textbox: firstname, lastname, employeeID	
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value"), firstname);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value"), lastname);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		

		// Step 8: Click Immigration tab
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		
		
				
		// Step 9: Click Add at Assigned Immigration Records
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
		
		
		// Step 10: Input data into Numbertextbox/ Comments textarea and click Save button
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(numberPassPort);
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(comments);
		//Click Save button
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInSecond(9);
		
	
		// Step 11: click Pencil icon	
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']")).click();
		sleepInSecond(9);
		
		// Step 12: Verify Number textbox and Comments textarea
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), numberPassPort);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comments);
	
	
		
		// Step 14:
		driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepInSecond(10);
		
		// Step 15:Login
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(10);		
		
		// Step 16: My Info Tab
		driver.findElement(By.xpath("//span[text()='My Info']")).click();
		sleepInSecond(9);	
					
		// Step 17: Verify firstname/ lastname/ employeeID
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value"), firstname);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value"), lastname);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		
	
		// Step 18: Immigration, Pencil icon
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(3);	
		
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']")).click();
		sleepInSecond(3);
		
		// Step 19: Verify Number textbox and Comments textarea
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), numberPassPort);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comments);
	}

	@Test
	public void TC_02() {
		
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
