package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Button_Radio_Checkbox {
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
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();

		By loginButton = By.cssSelector("button.fhs-btn-login");

		// Verify button is disable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());

		String buttonLoginBackground = driver.findElement(loginButton).getCssValue("background-image");
		Assert.assertTrue(buttonLoginBackground.contains("rgb(224, 224, 224)"));

		driver.findElement(By.cssSelector("#login_username")).sendKeys("stevetran@gmail.com");
		driver.findElement(By.cssSelector("#login_password")).sendKeys("123456789");
		sleepInSecond(2);

		// Verify button is enable
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());

		buttonLoginBackground = driver.findElement(loginButton).getCssValue("background-color");
		Color buttonLoginBackgroundColor = Color.fromString(buttonLoginBackground);
		Assert.assertEquals(buttonLoginBackgroundColor.asHex().toUpperCase(), "#C92127");

	}

	@Test
	public void TC_02_Default_Checkbox_Radio_Single() {
		driver.get("https://automationfc.github.io/multiple-fields/");

		// Click 1 checkbox
		driver.findElement(By.xpath("//label[contains(text(),'Anemia')]/preceding-sibling::input")).click();

		// Click 1 radio button
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).click();

		// Verify checkbox, radio button is selected
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Anemia')]/preceding-sibling::input"))
				.isSelected());
		Assert.assertTrue(
				driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input"))
						.isSelected());

		// Checkbox có thể tự bỏ chọn được
		driver.findElement(By.xpath("//label[contains(text(),'Anemia')]/preceding-sibling::input")).click();
		// Verify checkbox bỏ chọn rồi
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(),'Anemia')]/preceding-sibling::input"))
				.isSelected());

		// Radio không thể tự bỏ chọn được
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).click();
		// Verify radio vẫn được chọn rồi
		Assert.assertTrue(
				driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input"))
						.isSelected());

	}

	@Test
	public void TC_03_Default_Checkbox_Multiple() {
		driver.get("https://automationfc.github.io/multiple-fields/");

		List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("input.form-checkbox"));
		// Click all checkbox
		for (WebElement checkbox : allCheckboxes) {
			checkbox.click();
		}
		// Verify click all checkbox
		for (WebElement checkbox : allCheckboxes) {
			Assert.assertTrue(checkbox.isSelected());
		}
		
		
		// Nếu như gặp 1 checkbox có tên là X mới click
		for (WebElement checkbox : allCheckboxes) {
			if(checkbox.getAttribute("value").equals("Emotional Disorder")) {
				checkbox.click();
			}
		}
		// Verify is selected checkbox
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(),'Emotional Disorder')]/preceding-sibling::input")).isSelected());
	}

	@Test
	public void TC_04_Default_Checkbox_Radio() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		// chọn checkbox
		checkToCheckbox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		sleepInSecond(2);
		// bỏ chọn checkbox
		unCheckToCheckbox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());

		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		sleepInSecond(2);
		checkToCheckbox(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).isSelected());
	}
	
	public void checkToCheckbox(By by) {
		if(!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public void unCheckToCheckbox(By by) {
		if(driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	// Check element enable or disable
	public boolean isElementEnable(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element is enabled: " + by);
			return true;
		} else {
			System.out.println("Element is disabled: " + by);
			return false;
		}
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
