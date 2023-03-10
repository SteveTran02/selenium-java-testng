package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Handle_Dropdown_List_Default {
	WebDriver driver;
	// Select select;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstname, lastname, companyName, emailAddress, password, day, month, year;
	String countryName, stateName, cityName, addressName, zipCode, phoneNumber;

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

		firstname = "Steven";
		lastname = "Tran";
		companyName = "The Epoch Times";
		emailAddress = "steven" + getRandomNumber() + "@gmail.com";
		password = "steven123";
		day = "10";
		month = "January";
		year = "2000";
		countryName = "United States";
		stateName = "California";
		cityName = "San Diego";
		addressName = "san diego city 1";
		zipCode = "1234";
		phoneNumber = "123456789";
	}

	@Test
	public void TC_01_Register_New_Account() {
		// Step 1
		driver.get("https://demo.nopcommerce.com/");
		sleepInSecond(3);

		// Step 2: Click Register link
		driver.findElement(By.xpath("//div[@class='header']//a[text()='Register']")).click();

//ACTION		
		// Step 3: Input valid data
		driver.findElement(By.cssSelector("#gender-male")).click();
		driver.findElement(By.cssSelector("#FirstName")).sendKeys(firstname);
		driver.findElement(By.cssSelector("#LastName")).sendKeys(lastname);

		// Date of birth
		// Dropdown List
		// Using Select class
		// Day
		/*
		 * Cach 1: select = new
		 * Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		 * select.selectByVisibleText(day);
		 */
		// Cach 2:
		new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))).selectByVisibleText(day);
		// Function verify multiple
		// Assert.assertFalse(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))).isMultiple());
		// Month
		new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))).selectByVisibleText(month);
		// Year
		new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))).selectByVisibleText(year);

		// input email, company name, Newsletter, password and confirm password
		driver.findElement(By.cssSelector("#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("#Company")).sendKeys(companyName);
		driver.findElement(By.cssSelector("#Newsletter")).click();
		driver.findElement(By.cssSelector("#Password")).sendKeys(password);
		driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys(password);

		// Step 4: Click Register button
		driver.findElement(By.cssSelector("#register-button")).click();
		sleepInSecond(3);

//VERIFY
		// Step 5: Verify Success message
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Your registration completed']")).getText(),
				"Your registration completed");

		// Step 6: Click My Account link
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My account']")).click();
		sleepInSecond(3);

		// Login
		driver.findElement(By.cssSelector("#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("#Password")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Log in' ]")).click();
		sleepInSecond(2);

		// Step 7: Verify First name, Last name and Day of birth
		Assert.assertEquals(driver.findElement(By.cssSelector("#FirstName")).getAttribute("value"), firstname);
		Assert.assertEquals(driver.findElement(By.cssSelector("#LastName")).getAttribute("value"), lastname);

		Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")))
				.getFirstSelectedOption().getText(), day);

		Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")))
				.getFirstSelectedOption().getText(), month);

		Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")))
				.getFirstSelectedOption().getText(), year);

		Assert.assertEquals(driver.findElement(By.cssSelector("#Email")).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.cssSelector("#Company")).getAttribute("value"), companyName);

	}

	@Test
	public void TC_02_Add_Address() {
		driver.findElement(By.cssSelector("li.customer-addresses>a")).click();
		driver.findElement(By.cssSelector("div.add-button>button")).click();
		sleepInSecond(2);
		// Input
		driver.findElement(By.cssSelector("#Address_FirstName")).sendKeys(firstname);
		driver.findElement(By.cssSelector("#Address_LastName")).sendKeys(lastname);
		driver.findElement(By.cssSelector("#Address_Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("#Address_Company")).sendKeys(companyName);
		new Select(driver.findElement(By.cssSelector("#Address_CountryId"))).selectByVisibleText(countryName);
		new Select(driver.findElement(By.cssSelector("#Address_StateProvinceId"))).selectByVisibleText(stateName);
		driver.findElement(By.cssSelector("#Address_City")).sendKeys(cityName);
		driver.findElement(By.cssSelector("#Address_Address1")).sendKeys(addressName);
		driver.findElement(By.cssSelector("#Address_ZipPostalCode")).sendKeys(zipCode);
		driver.findElement(By.cssSelector("#Address_PhoneNumber")).sendKeys(phoneNumber);
		// Click Save button
		driver.findElement(By.cssSelector("button.save-address-button")).click();
		sleepInSecond(2);
		
		// Verify
		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.name")).getText(), firstname + " " + lastname);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.email")).getText().contains(emailAddress));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.phone")).getText().contains(phoneNumber));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.company")).getText(), companyName);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.address1")).getText(), addressName);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(cityName));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(stateName));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(zipCode));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.country")).getText(), countryName);
		
		
	}

	@Test
	public void TC_03_() {

	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
			;
		} catch (InterruptedException e) {
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
