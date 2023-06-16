package Automatic_Template;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class automation_Template_0118_Template {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String downloadFilepath = "";

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chrome+driver");
		}

		downloadFilepath = "C:\\Users\\robos\\Downloads\\";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("download.default_directory", downloadFilepath);
		driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Template_Status_sort() {

		// Go to the link: networktest.wastelinq.com /Network side
		driver.get("https://networktest.wastelinq.com/");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#id_username"))).sendKeys("network_admin");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#id_password"))).sendKeys("notsharable1");
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#btnLogin"))).click();
		// Accept Okay login
		if (explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alertLogin"))).isDisplayed()) {
			driver.findElement(By.cssSelector("div.titleform a[onclick='loginAgain()']")).click();
		}

		// Click on Set-up Tools
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.user_dropdown.jsmenu_user"))).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Set-Up Tools']"))).click();

		// Click on Material Management tab
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3#tab_tools_material_management label"))).click();

		// Click on Template Repository tab
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Template Repository']"))).click();

		// Click on Template Status green arrow
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add New Template']"))).click();

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Template Status:']")));

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select#id_template_status"))).click();

		// Select option Submitted to Finance
		new Select(driver.findElement(By.cssSelector("select#id_template_status"))).selectByVisibleText("Submitted to Finance");

		// Verify dropbox display ‘Submitted to Finance’
		Assert.assertEquals(driver.findElement(By.cssSelector("select#id_template_status")).getAttribute("value"), "Submitted to Finance");
		// Verify automatically assigns to Finance (Haris Siddiqui)
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.cssSelector("//select[@id='id_AssignedTo']/option[text()='Haris Siddiqui']")).isSelected());

	}

	// @Test
	public void TC_02_Verify_Automatic_Approval_dropbox() {

		// Go to the link: networktest.wastelinq.com /Network side
		driver.get("https://networktest.wastelinq.com/");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#id_username"))).sendKeys("network_admin");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#id_password"))).sendKeys("notsharable1");
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#btnLogin"))).click();
		// Accept Okay login
		if (explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alertLogin"))).isDisplayed()) {
			driver.findElement(By.cssSelector("div.titleform a[onclick='loginAgain()']")).click();
		}

		// Click on Set-up Tools
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.user_dropdown.jsmenu_user"))).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Set-Up Tools']"))).click();

		// Click on Material Management tab
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3#tab_tools_material_management label"))).click();

		// Click on Template Repository tab
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Template Repository']"))).click();

		// Click on Template Status green arrow
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add New Template']"))).click();

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Template Status:']")));

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select#id_template_status"))).click();

		// Click on Automatic Approval dropbox
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select#id_template_automatic_approval"))).click();

		// Verify Default value – ‘No’
		Assert.assertTrue(driver.findElement(By.xpath("//select[@id='id_template_automatic_approval']/option[text()='No']")).isSelected());

		// Verify ‘Automatic Approval:’ dropdown display below ‘Assigned To:’ dropbox
		String textActualResult = driver.findElement(By.xpath("//label[text()='Assigned To:']/parent::div/following-sibling::div[1]/label")).getText();
		String textExpectedResult = "Automatic Approval:";

		// Verify Options – ‘No’ and ‘Yes’
		ArrayList<String> arrListOptions = new ArrayList<>();
		List<WebElement> listOptions = driver.findElements(By.cssSelector("select#id_template_automatic_approval option"));
		for (WebElement webElement : listOptions) {
			arrListOptions.add(webElement.getText());
		}
		ArrayList<String> expected = new ArrayList<>();
		expected.add("No");
		expected.add("Yes");

		Assert.assertEquals(arrListOptions, expected);

	}

	// @Test
	public void TC_03_Using_Automatic_Approval_dropbox() {

		// Go to the link: networktest.wastelinq.com /Network side
		driver.get("https://networktest.wastelinq.com/");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#id_username"))).sendKeys("network_admin");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#id_password"))).sendKeys("notsharable1");
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#btnLogin"))).click();
		// Accept Okay login
		if (explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alertLogin"))).isDisplayed()) {
			driver.findElement(By.cssSelector("div.titleform a[onclick='loginAgain()']")).click();
		}

		// Click on Set-up Tools
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.user_dropdown.jsmenu_user"))).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Set-Up Tools']"))).click();

		// Click on Material Management tab
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3#tab_tools_material_management label"))).click();

		// Click on Template Repository tab
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Template Repository']"))).click();

		// Click on Template Status green arrow
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add New Template']"))).click();

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Template Status:']")));

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select#id_template_status"))).click();

		// Click on Automatic Approval dropbox
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select#id_template_automatic_approval"))).click();
		String defaultText = driver.findElement(By.cssSelector("td[id='tsdf-approval-status'][class='tbl-select'] span")).getText();

		// Select Yes option
		new Select(driver.findElement(By.cssSelector("select#id_template_automatic_approval"))).selectByVisibleText("Yes");

		// Verify Update ‘Approval # Status’ to ‘Secondary Review’ on all approval lines
		String AfterText = driver.findElement(By.cssSelector("td[id='tsdf-approval-status'][class='tbl-select'] span")).getText();
		Assert.assertEquals(AfterText, "Secondary Review");
		Assert.assertFalse(AfterText.equals(defaultText));
	}

	@Test
	public void TC_04_Verify_Processing_Detail_Lines() {

		// Go to the link: networktest.wastelinq.com /Network side
		driver.get("https://networktest.wastelinq.com/");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#id_username"))).sendKeys("network_admin");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#id_password"))).sendKeys("notsharable1");
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#btnLogin"))).click();
		// Accept Okay login
		if (explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alertLogin"))).isDisplayed()) {
			driver.findElement(By.cssSelector("div.titleform a[onclick='loginAgain()']")).click();
		}

		// Click on Set-up Tools
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.user_dropdown.jsmenu_user"))).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Set-Up Tools']"))).click();

		// Click on Material Management tab
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3#tab_tools_material_management label"))).click();

		// Click on Template Repository tab
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Template Repository']"))).click();

		// Click on Template Status green arrow
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add New Template']"))).click();

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Template Status:']")));

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select#id_template_status"))).click();

		// Click on Automatic Approval dropbox
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select#id_template_automatic_approval"))).click();
		String defaultText = driver.findElement(By.cssSelector("td[id='tsdf-approval-status'][class='tbl-select'] span")).getText();

		// Select Yes option
		new Select(driver.findElement(By.cssSelector("select#id_template_automatic_approval"))).selectByVisibleText("Yes");

		
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

	// Wait for page loading success script jquery and readystate
	public boolean isPageLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		final JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}
