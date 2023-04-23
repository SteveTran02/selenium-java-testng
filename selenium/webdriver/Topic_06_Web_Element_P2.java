package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_P2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	// declare global variables
	By emailTextbox = By.id("mail");
	By passwordTextbox = By.id("disable_password");
	By ageUnder18Radio = By.xpath("//input[@id='under_18']");
	By radioButtonIsDisable = By.cssSelector("#radio-disabled");
	By educationTextarea = By.xpath("//textarea[@id='edu']");
	By biographyTextarea = By.cssSelector("#bio");
	By user5Name = By.xpath("//h5[text()='Name: User5']");
	By jobRole1 = By.cssSelector("#job1");
	By jobRole2 = By.cssSelector("#job2");
	By jobRole3 = By.cssSelector("#job3");
	By interestDevelopmentCheckbox = By.cssSelector("#development");
	By interestCheckboxIsDisable = By.cssSelector("#check-disbaled");
	By slider1 = By.cssSelector("#slider-1");
	By slider2 = By.cssSelector("#slider-2");
	
	
	
	
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
    public void TC_01_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Email textbox
		if(driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("Automation Testing");
			System.out.println("email Textbox is displayed");
		}else {
			System.out.println("emailTextbox is not displayed");
		};
			
		
		// Education textarea
		if(driver.findElement(educationTextarea).isDisplayed()) {
			driver.findElement(educationTextarea).sendKeys("Automation Testing");
			System.out.println("education Textarea is displayed");
		}else {
			System.out.println("education Textarea is not displayed");
		};	
		
		
		// Age under 18 radio
		if(driver.findElement(ageUnder18Radio).isDisplayed()) {
			driver.findElement(ageUnder18Radio).click();
			System.out.println("age Under 18 Radio is displayed");
		}else {
			System.out.println("age Under 18 Radio is not displayed");
		};
		
		
		// Name user 5
		if(driver.findElement(user5Name).isDisplayed()) {
			System.out.println("user 5 Name is displayed");
		}else {
			System.out.println("user 5 Name is not displayed");
		};
		

		
	}

	@Test
	public void TC_02_Enable_Disabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Email textbox
		if(driver.findElement(emailTextbox).isEnabled()) {
			System.out.println("email Textbox is enabled");
		}else {
			System.out.println("emailTextbox is disabled");
		};
		
		// age Under 18 Radio
		if(driver.findElement(ageUnder18Radio).isEnabled()) {
			System.out.println("age Under 18 Radio is enabled");
		}else {
			System.out.println("age Under 18 Radio is disabled");
		};
		
		// education Textarea
		if(driver.findElement(educationTextarea).isEnabled()) {
			System.out.println("education Textarea is enabled");
		}else {
			System.out.println("education Textarea is disabled");
		};	
		
		// job Role 1
		if(driver.findElement(jobRole1).isEnabled()) {
			System.out.println("job Role 1 is enabled");
		}else {
			System.out.println("job Role 1 is disabled");
		};			
		
		
		 //job Role 2
		if(driver.findElement(jobRole2).isEnabled()) {
			System.out.println("job Role 2 is enabled");
		}else {
			System.out.println("job Role 2 is disabled");
		};			
				
	
		 //interest Development Checkbox
		if(driver.findElement(interestDevelopmentCheckbox).isEnabled()) {
			System.out.println("interest Development Checkbox is enabled");
		}else {
			System.out.println("interest Development Checkbox is disabled");
		};	
		
		 //slider 1
		if(driver.findElement(slider1).isEnabled()) {
				System.out.println("slider 1 is enabled");
		}else {
				System.out.println("slider 1 is disabled");
		};	
		
		 //password Textbox
		if(driver.findElement(passwordTextbox).isEnabled()) {
				System.out.println("password Textbox is enabled");
		}else {
				System.out.println("password Textbox is disabled");
		};		
		
		
		 //Age radio Button Is Disable
		if(driver.findElement(radioButtonIsDisable).isEnabled()) {
				System.out.println("AgeradioButtonIsDisable is enabled");
		}else {
				System.out.println("AgeradioButtonIsDisable is disabled");
		};
		
		// biography Textarea
		if(driver.findElement(biographyTextarea).isEnabled()) {
				System.out.println("biography Textarea is enabled");
		}else {
				System.out.println("biography Textarea is disabled");
		};	
		
		// job Role 3
		if(driver.findElement(jobRole3).isEnabled()) {
			System.out.println("job Role 3 is enabled");
		}else {
			System.out.println("job Role 3 is disabled");
		};	
		
		
		// interest Checkbox Is Disable
		if(driver.findElement(interestCheckboxIsDisable).isEnabled()) {
				System.out.println("interest Checkbox Is Disable is enabled");
		}else {
				System.out.println("interest Checkbox Is Disable is disabled");
		};	
		
		// slider 2
		if(driver.findElement(slider2).isEnabled()) {
				System.out.println("slider 2 is enabled");
		}else {
				System.out.println("slider 2 is disabled");
		};
	
	
	}

	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Verify radio button/ checkbox are deselected
		Assert.assertFalse(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertFalse(driver.findElement(By.id("java")).isSelected());
		
		// Click to radio button/ Checkbox
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(By.id("java")).click();
		sleepInSecond(3);
		
		// Verify radio button/ checkbox are selected
		Assert.assertTrue(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertTrue(driver.findElement(By.id("java")).isSelected());
		
	}
	
	@Test
	public void TC_04_Register_function_at_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.id("email")).sendKeys("stevetran@gmail.com");
		
		By passwordTextbox = By.id("new_password");
		
		
		// Input lowercase character
		driver.findElement(passwordTextbox).sendKeys("abc");
		sleepInSecond(2);
		// Verify  lowercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		
		// Input uppercase character
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("ABC");
		sleepInSecond(2);
		// Verify  lowercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		
		// Input number
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("123");
		sleepInSecond(2);
		// Verify  lowercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		
		// Input special character
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("!@#");
		sleepInSecond(2);
		// Verify  lowercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		
		// Input 8 characters minimum
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("1A!a");
		sleepInSecond(2);
		// Verify  8 characters minimum
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		

		// Input full characters
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("1aA!@#abc");
		sleepInSecond(2);
		// Verify full characters
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
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
