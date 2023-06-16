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

public class automation_Template_0118 {
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
	public void TC_01_Verify_Add_New_Template_Button() {
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
		// Click on 'Add New Template' button
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Add New Template']"))).click();

		// Verify
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='WASTELINQ Template #:']")));
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='WASTELINQ Template #:']")).isDisplayed());
	}

	// @Test
	public void TC_02_Verify_Copy_Existing_Template_Button() {
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

		// Click on 'Copy Existing Template' button
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Copy Existing Template']"))).click();

		// Show Start a Network Profile table
		// Verify popup is displayed
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mpop.searchPopUp h2")));

		// Action double click Universal Waste
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@aria-labelledby='Universal Waste_anchor']//i[@class='jstree-icon jstree-ocl']"))).click();
		// Choose Profile and double click
		sleepInSecond(2);
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Automatic *']")));
		action.doubleClick(driver.findElement(By.xpath("//a[text()='Automatic *']//i"))).perform();

		// Verify New Waste Profile page opens and 'Wasteling Profile #:' created
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#pdfPopUp h2")));
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Copy Template']"))).click();

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mpop.newtemplatePopUp")));
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='mpop newtemplatePopUp']//a[text()='OK']"))).click();

	}

	// @Test
	public void TC_03_Verify_Export_To_CSV() {
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

		// Verify Button 'Export to CSV' is present and clickable
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#export span"))).click();

		System.out.println(downloadFilepath);
		Assert.assertTrue(downloadFilepath.contains("Template_Repository"));

	}

	// @Test
	public void TC_04_Verify_Paging_Selector() {
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

		// Verify data Entry length (Show 'dropdown' entries)
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select[name='myTable_length']"))).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("select[name='myTable_length'] option[value='10']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("select[name='myTable_length'] option[value='25']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("select[name='myTable_length'] option[value='50']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("select[name='myTable_length'] option[value='100']")).isDisplayed());
	}

	// @Test
	public void TC_05_Using_Paging_Selector() {
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

		// Choose from drop down menu 100
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select[name='myTable_length']"))).click();
		new Select(driver.findElement(By.cssSelector("select[name='myTable_length']"))).selectByVisibleText("100");
		// Verify
		Assert.assertEquals(driver.findElement(By.cssSelector("select[name='myTable_length']")).getAttribute("value"), "100");
	}

	// @Test
	public void TC_06_Using_Paging_Selector() {
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

		// Choose from drop down menu 100
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select[name='myTable_length']"))).click();
		new Select(driver.findElement(By.cssSelector("select[name='myTable_length']"))).selectByVisibleText("100");
		// Verify
		Assert.assertEquals(driver.findElement(By.cssSelector("select[name='myTable_length']")).getAttribute("value"), "100");
	}

	// @Test
	public void TC_07_Verify_Page_Length() {
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

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select[name='myTable_length']"))).click();

		List<WebElement> allPageLength = driver.findElements(By.cssSelector("select[name='myTable_length'] option"));

		String pageLengthArr[] = new String[allPageLength.size()];
		System.out.println(allPageLength.size()); // = 4
		int i = 0;
		// Assign value into array text
		for (WebElement pageLength : allPageLength) {
			pageLengthArr[i] = pageLength.getText();
			i++;
		}
		// Select option and verify option
		int j = 0; // index new array text
		for (WebElement pageLength : allPageLength) {
			// select option
			explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select[name='myTable_length']"))).click();
			new Select(driver.findElement(By.cssSelector("select[name='myTable_length']"))).selectByVisibleText(pageLengthArr[j]);
			// verify option
			Assert.assertEquals(driver.findElement(By.cssSelector("select[name='myTable_length']")).getAttribute("value"), pageLengthArr[j]);
			j++;
		}
	}

	// @Test
	public void TC_08_Verify_DataTable_Paging() {
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

		// Click on Page number button
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#myTable_paginate")));
		List<WebElement> allPageLength = driver.findElements(By.cssSelector("div#myTable_paginate span>a"));
		for (WebElement webElement : allPageLength) {

			// Click
			jsExecutor.executeScript("arguments[0].scrollIntoView(false);", webElement);
			jsExecutor.executeScript("arguments[0].click();", webElement);
			sleepInSecond(2);
			// Verify 
			Assert.assertTrue(webElement.getAttribute("class").contains("current"));
			sleepInSecond(2);

		}

	}

	// @Test
	public void TC_09_Verify_Search_Field() {
		By searchTextbox = By.cssSelector("div#myTable_filter input[type='search']");
		
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
		
		// Click on Search placeholder
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(searchTextbox));
		// Assert.assertTrue(driver.findElement(By.cssSelector("div#myTable_filter input[type='search']")).isSelected());
		
		// Search field: type free text (ex: 9501Q)
		driver.findElement(searchTextbox).sendKeys("9501Q");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(searchTextbox).getAttribute("value"), "9501Q");
		sleepInSecond(2);
		
		// Clear text search field    
		driver.findElement(searchTextbox).clear();
		sleepInSecond(2);
		// Verify search textbox is empty 
		Assert.assertTrue(driver.findElement(searchTextbox).getAttribute("value").isEmpty());
		
	}
	
	// @Test
	public void TC_10_Verify_Template_Category_dropbox() {
		
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
		
		// Click on Template Category dropbox    
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select#id_TemplateCategory"))).click();
		
		// Verify display all Template Category
		String templateCategoryArr[] = {"All", "Automotive Fluids", "Oils", "Universal Waste"};
		int i = 0;
		List<WebElement> allTemplateCategory = driver.findElements(By.cssSelector("select#id_TemplateCategory option"));
		System.out.println("size = " + allTemplateCategory.size());
		for (WebElement template : allTemplateCategory) {
			template.getText().equals(templateCategoryArr[i]);
			i++;
		}
		
	}
	
	// @Test
	public void TC_11_Using_Template_Category_dropbox() {
		
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
		
		// Click on Template Category dropbox    
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select#id_TemplateCategory"))).click();
		
		// Choose "Universal Waste"   
		new Select(driver.findElement(By.cssSelector("select#id_TemplateCategory"))).selectByVisibleText("Universal Waste");
		sleepInSecond(2);
		// Verify select option
		Assert.assertTrue(driver.findElement(By.cssSelector("select#id_TemplateCategory")).getAttribute("value").equals("Universal Waste"));
		
	}
	
	
	// @Test
	public void TC_12_Verify_Template_Status_dropbox() {
		
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
		
		// Click on Template Category dropbox    
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select#id_TemplateCategory"))).click();
		
		// Click on Template Status dropbox   
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.search-field"))).click();
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.chosen-container-active")));
		// Verify
		String statusName[] = {"All", "In Progress", "Submitted to WAC", "Submitted to Finance", "Approved", "Inactive"};
		int i = 0;
		List<WebElement> allStatus = driver.findElements(By.cssSelector("div.chosen-drop ul li"));	
		for (WebElement status : allStatus) {
			status.getText().equals(statusName[i]);
			i++;
		}	
	}
	
	
	// @Test
	public void TC_13_Using_Template_Status_dropbox() {
		
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
		
		// Click on Template Category dropbox    
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select#id_TemplateCategory"))).click();
		
		// Click on Template Status dropbox   
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.search-field"))).click();
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.chosen-container-active")));
		
		// Choose "In Progress"
		List<WebElement> deleteElement = driver.findElements(By.cssSelector("ul.chosen-choices li a"));
		for (WebElement deleteE : deleteElement) {
			deleteE.click();
		}
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.search-field"))).click();
		
		
		List<WebElement> chosenElement = driver.findElements(By.cssSelector("div.chosen-drop ul li"));
		for (WebElement webElement : chosenElement) {
			if (webElement.getText().equals("In Progress")) {
				webElement.click();
				break;
			}
		}
		
		// Verify
		List<WebElement> templateStatus = driver.findElements(By.cssSelector("div.chosen-drop ul li"));
		
		
	}
	
	// @Test
	public void TC_14_Verify_Automatic_Approval_dropbox() {
		
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
		
		// Click on Automatic Approval dropbox    
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select#id_AutomaticApproval"))).click();
		
		// Verify display 'All', 'Yes', and 'No'
		String text = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='id_TemplateStatus_chosen']/following-sibling::span"))).getText();
		// Verify text
		Assert.assertTrue(text.contains("Automatic Approval:"));
		
		String textArr[] = {"All", "No", "Yes"};
		int i = 0;
		List<WebElement> approvalElements  = driver.findElements(By.cssSelector("select#id_AutomaticApproval option"));
		for (WebElement webElement : approvalElements) {
			webElement.getText().equals(textArr[i]);
			i++;
		}
	}
	
	//@Test
	public void TC_15_Using_Automatic_Approval_dropbox() {
		
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
		
		// Click on Automatic Approval dropbox    
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select#id_AutomaticApproval"))).click();
		
		// Choose "Yes"
		List<WebElement> approvalElements  = driver.findElements(By.cssSelector("select#id_AutomaticApproval option"));
		for (WebElement webElement : approvalElements) {
			if (webElement.getText().equals("Yes")) {
				webElement.click();
				break;
			}
		}
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Search']"))).click();
		
		// Verify display all Yes profile
		List<WebElement> status  = driver.findElements(By.xpath("//tbody/tr/td[6]"));
		for (WebElement webElement : status) {
			webElement.getText().equals("Yes");
		}
	}
	
	// @Test
	public void TC_16_Template_ID_sort() {
		
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
		
		// Click on Template ID green arrow
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//th[text()='Template ID']"))).click();
		
		// Verify User able to sort by Template ID
		
		ArrayList<String> newArr1 = new ArrayList<>();
		
		List<WebElement> Sortings  = driver.findElements(By.cssSelector("tbody tr td.sorting_1"));
		for (WebElement item : Sortings) {
			newArr1.add(item.getText());
		}
		
		List<Integer> newList = new ArrayList<Integer>(newArr1.size()) ;
		for (String myInt : newArr1) {
			newList.add(Integer.valueOf(myInt));
		}
		// Sorting in natural order or ascending order
		List<Integer> sortedListAsc = newList.stream().sorted().collect(Collectors.toList());
		
		// Sorting in descending order
		List<Integer> sortedListDesc = newList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		
	}
	
	
	// @Test
	public void TC_17_Profile_Name_sort() {
		
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
		
		
		// Click on Profile Name green arrow
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//th[text()='Profile Name']"))).click();
		
		// Verify
		ArrayList<String> profileNameList = new ArrayList<>();
		
		List<WebElement> profileElements = driver.findElements(By.cssSelector("tbody tr td.sorting_1 a"));
		for (WebElement webElement : profileElements) {
			profileNameList.add(webElement.getText());
		}
		
		ArrayList<String> sortProfileNameList = new ArrayList<>();
		for (String profileName : profileNameList) {
			sortProfileNameList.add(profileName);
		}
		// sort
		Collections.sort(sortProfileNameList);
		
	}
	
	@Test
	public void TC_18_Template_Status_sort() {
		
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
		By templateStatus = By.xpath("//th[text()='Template Status']");
		
		// Verify
		switch (driver.findElement(templateStatus).getAttribute("class")) {
		case "sorting_asc":
			explicitWait.until(ExpectedConditions.elementToBeClickable(templateStatus)).click();
			sleepInSecond(1);
			Assert.assertTrue(driver.findElement(templateStatus).getAttribute("class").equals("sorting_desc"));
			break;
		case "sorting_desc":
			explicitWait.until(ExpectedConditions.elementToBeClickable(templateStatus)).click();
			sleepInSecond(1);
			Assert.assertTrue(driver.findElement(templateStatus).getAttribute("class").equals("sorting_asc"));
			break;
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
