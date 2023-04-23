package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Javascript_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	Random rand = new Random();
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	// emailAdress
	String emailAdress = "pureInsight" + String.valueOf(rand.nextInt(9999)) + "@gmail.com";

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
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Tech_Panda() {

		// Step 1: Navigate to Techpanda
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(3);

		// Step 2: get domain and verify
		String domain = (String) executeForBrowser("return document.domain;");
		System.out.println("domain is: " + domain);
		// Bỏ vô Assert tự động ép kiểu Object -> String
		Assert.assertEquals(executeForBrowser("return document.domain;"), "live.techpanda.org");

		// Step 3: get URL and verify
		String url = (String) executeForBrowser("return document.URL;");
		System.out.println("URL is: " + url);
		// Bỏ vô Assert tự động ép kiểu Object -> String
		Assert.assertEquals(executeForBrowser("return document.URL;"), "http://live.techpanda.org/");

		// Step 4: open Mobile page
		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(3);

		// Step 5: Add to cart Samsung galaxy
		hightlightElement("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		sleepInSecond(3);

		// Step 6: verify message is displayed
		hightlightElement("//li[@class='success-msg']");
		// Cach 1
		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
		// Cach 2
		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

		// Step 7: open customer service page, verify title
		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(3);

		Assert.assertEquals(executeForBrowser("return document.title;"), "Customer Service");

		// Step 8: scroll to element Newsletter textbox
		hightlightElement("//input[@id='newsletter']");
		scrollToElementOnTop("//input[@id='newsletter']");
		sleepInSecond(3);

		// Step 9: input invalid data into Newsletter textbox
		hightlightElement("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", emailAdress);
		sleepInSecond(3);

		// Step 10: click Subscribe button
		hightlightElement("//span[text()='Subscribe']");
		clickToElementByJS("//span[text()='Subscribe']");
		sleepInSecond(3);

		// Step 11: verify message is displayed
		hightlightElement("//li[@class='success-msg']");
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
		sleepInSecond(3);

		// Step 12: Navigate to demo.guru99.com
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(3);
		Assert.assertEquals(executeForBrowser("return document.domain;"), "demo.guru99.com");

	}

	// @Test
	public void TC_02_Rode() {

		driver.get("https://warranty.rode.com/");

		// firstname textbox
		getElement("//button[contains(text(),'Register')]").click();
		hightlightElement("//input[@id='firstname']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='firstname']"), "Please fill out this field.");
		sleepInSecond(3);
		getElement("//input[@id='firstname']").sendKeys("Sound");
		sleepInSecond(3);

		// surname textbox
		getElement("//button[contains(text(),'Register')]").click();
		hightlightElement("//input[@id='surname']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='surname']"), "Please fill out this field.");
		sleepInSecond(3);
		getElement("//input[@id='surname']").sendKeys("of Hope");
		sleepInSecond(3);

		// Email textbox
		getElement("//button[contains(text(),'Register')]").click();
		hightlightElement("//form[contains(@action,'register')]//input[@id='email']");
		Assert.assertEquals(getElementValidationMessage("//form[contains(@action,'register')]//input[@id='email']"),
				"Please fill out this field.");
		sleepInSecond(3);
		getElement("//form[contains(@action,'register')]//input[@id='email']").sendKeys(emailAdress);
		sleepInSecond(3);

		// Password textbox
		getElement("//button[contains(text(),'Register')]").click();
		hightlightElement("//form[contains(@action,'register')]//input[@id='password']");
		Assert.assertEquals(getElementValidationMessage("//form[contains(@action,'register')]//input[@id='password']"),
				"Please fill out this field.");
		sleepInSecond(3);
		getElement("//form[contains(@action,'register')]//input[@id='password']").sendKeys("123456789");
		sleepInSecond(3);

		// Comfirm Password textbox
		getElement("//button[contains(text(),'Register')]").click();
		hightlightElement("//form[contains(@action,'register')]//input[@id='password-confirm']");
		Assert.assertEquals(
				getElementValidationMessage("//form[contains(@action,'register')]//input[@id='password-confirm']"),
				"Please fill out this field.");
		sleepInSecond(3);
		getElement("//form[contains(@action,'register')]//input[@id='password-confirm']").sendKeys("123456789");
		sleepInSecond(3);

	}

	// @Test
	public void TC_03_Guru99_Remove_Attribute() {

		driver.get("https://demo.guru99.com/v4/");
		// Login
		getElement("//input[@type='text']").sendKeys("mngr487976");
		getElement("//input[@type='password']").sendKeys("magYqat");
		getElement("//input[@type='submit']").click();
		sleepInSecond(3);

		getElement("//a[text()='New Customer']").click();

		jsExecutor.executeScript("arguments[0].removeAttribute('type');", getElement("//input[@id='dob']"));
		sleepInSecond(2);
		getElement("//input[@id='dob']").sendKeys("10/10/2022");
		sleepInSecond(5);
	}

	@Test
	public void TC_04_Get_HTML5_Validation_Message() {

		driver.get("https://automationfc.github.io/html5/index.html");
		By submitButton = By.xpath("//input[@type='submit']");

		// Name textbox
		driver.findElement(submitButton).click();
		sleepInSecond(2);
		// Verify message
		String actualMessageOfName = (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
				driver.findElement(By.xpath("//input[@type='name']")));
		Assert.assertEquals(actualMessageOfName, "Please fill out this field.");
		// Sendkeys
		driver.findElement(By.xpath("//input[@type='name']")).sendKeys("Sound Of Hope");
		sleepInSecond(2);

		
		
		// password textbox
		driver.findElement(submitButton).click();
		sleepInSecond(2);
		// Verify message
		String actualMessageOfPassword = (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
				driver.findElement(By.xpath("//input[@type='password']")));
		Assert.assertEquals(actualMessageOfPassword, "Please fill out this field.");
		// Sendkeys
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("SoundofHope");
		sleepInSecond(2);

		
		
		// email textbox
		driver.findElement(submitButton).click();
		sleepInSecond(2);
		// Verify message
		String actualMessageOfEmail = (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
				driver.findElement(By.xpath("//input[@type='email']")));
		Assert.assertEquals(actualMessageOfEmail, "Please fill out this field.");
		// Sendkeys
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(emailAdress);
		sleepInSecond(2);

		
		// Address textbox
		driver.findElement(submitButton).click();
		sleepInSecond(2);
		// Verify message
		 String actualMessageOfAddress = (String) jsExecutor.executeScript("return arguments[0].validationMessage;", driver.findElement(By.xpath("//select")));
		 Assert.assertEquals(actualMessageOfAddress, "Please select an item in the list.");
		 // select one option
		new Select(driver.findElement(By.xpath("//select"))).selectByVisibleText("DA NANG");
		
		// Click submit button
		driver.findElement(submitButton).click();
		sleepInSecond(5);


		
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

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 4px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
}
