package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Handle_Dropdown_List_Custom {
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
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Method_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		// 1 - Click vào 1 thẻ bất kỳ để làm sao cho nó đổ ra hết các item của dropdown
		driver.findElement(By.cssSelector("span#speed-button")).click();
		
		// 2 - Chờ cho tất cả các item load ra thành công
			/*	
			 * Locator phải lấy để đại diện cho tất cả các item
			 * Lấy đền thẻ chứa text
			 */
				/* Note:
				 * Wait linh động # Wait cứng
				 * presence (in DOM) # visible
				 */
			// Đưa hết thẻ element vào 1 list
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div[role='option']")));
		List<WebElement> speedDropdownitems = driver.findElements(By.cssSelector("ul#speed-menu div[role='option']"));
		
		
		// 3 - Tìm item xem đúng cái đang cần hay không
			// 3.1 - Nếu nó nằm trong khoảng nhìn thấy của user k cần phải scroll xuống
			// 3.2 - Nếu nó không nằm trong khoảng nhìn thấy của user thì cần phải scroll xuống để tìm item đó
		// 4 - Kiểm tra cái text của item đúng với cái mình mong muốn		
		// 5 - Click vào item đó
		for (WebElement tempItem : speedDropdownitems) {
			String itemText = tempItem.getText();
			System.out.println(itemText);
			if(itemText.equals("Fast")) {
				tempItem.click();
				break;
			}		
		}
		
		
		
	}

	// @Test
	public void TC_02_JQuery_Method_2() {
				// Step 1: Launch JQuery website
				driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
				// click on the Select a speed drop down to make all the options visible
				driver.findElement(By.cssSelector("span#speed-button")).click();
				
				// Step 2: Apply explicitWait for appear all items
				explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div[role='option']")));
			
				// Step 3: Store all the options as WebElements in a List
				List<WebElement> speedDropownItems = driver.findElements(By.cssSelector("ul#speed-menu div[role='option']"));
				// Step 4: Iterate the list using a for loop and click the desired option	
				// Step 5: Instead of for loop, enhanced for loop can only be used in the following way
				String optionItem = "Faster";
				for(int i=0; i<speedDropownItems.size(); i++) {
					if(speedDropownItems.get(i).getText().equals(optionItem)) {
						speedDropownItems.get(i).click();
						break;
					}
				}
	}

	//@Test
	public void TC_03_Using_Function() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Faster");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(), "Faster");
		
		
		
		selectItemInDropdown("span#salutation-button", "ul#salutation-menu div[role='option']", "Dr.");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button span.ui-selectmenu-text")).getText(), "Dr.");

	}
	
	//@Test
	public void TC_04_ReactJS() {
		
//		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
//		driver.findElement(By.cssSelector("i.dropdown")).click();
//		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.item")));
//		List<WebElement> allElementItems = driver.findElements(By.cssSelector("div.item"));
//		String option = "Stevie Feliciano";
//		for (WebElement tempItem : allElementItems) {
//			String itemText = tempItem.getText();
//			if(itemText.equals(option)) {
//				tempItem.click();
//				break;
//			}
//		}
		
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInDropdown("i.dropdown", "div.item", "Stevie Feliciano");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Stevie Feliciano");
		
		
		selectItemInDropdown("i.dropdown", "div.item", "Jenny Hess");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
	
		
	}
	
	//@Test
	public void TC_05_VueJS() {

		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		
		
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
		
	}

	
	@Test
	public void TC_06_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		enterAndSelectItemInDropdown("input.search", "span.text", "Argentina");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Argentina");
		
		enterAndSelectItemInDropdown("input.search", "span.text", "Bangladesh");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Bangladesh");
	}
	
	
	
	public void selectItemInDropdown(String parentCss, String allItemCss, String ExpectedTextItem) {
		// Step 1: Launch JQuery website
		// click on the Select a speed drop down to make all the options visible
		driver.findElement(By.cssSelector(parentCss)).click();
		sleepInSecond(1);
		// Step 2: Apply explicitWait for appear all items
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
	
		// Step 3: Store all the options as WebElements in a List
		List<WebElement> speedDropownItems = driver.findElements(By.cssSelector(allItemCss));
		// Step 4: Iterate the list using a for loop and click the desired option	
		// Step 5: Instead of for loop, enhanced for loop can only be used in the following way
		String optionItem = ExpectedTextItem;
		for(int i=0; i<speedDropownItems.size(); i++) {
			if(speedDropownItems.get(i).getText().trim().equals(optionItem)) {
				speedDropownItems.get(i).click();
				break;
			}
		}
	}

	public void enterAndSelectItemInDropdown(String textboxCss, String allItemCss, String ExpectedTextItem) {
		// Step 1: Launch JQuery website
		// click on the textbox, clear data and sendkeys text
		driver.findElement(By.cssSelector(textboxCss)).clear();
		driver.findElement(By.cssSelector(textboxCss)).sendKeys(ExpectedTextItem);;
		sleepInSecond(1);
		
		// Step 2: Apply explicitWait for appear all items
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
	
		// Step 3: Store all the options as WebElements in a List
		List<WebElement> speedDropownItems = driver.findElements(By.cssSelector(allItemCss));
		// Step 4: Iterate the list using a for loop and click the desired option	
		// Step 5: Instead of for loop, enhanced for loop can only be used in the following way
		String optionItem = ExpectedTextItem;
		for(int i=0; i<speedDropownItems.size(); i++) {
			if(speedDropownItems.get(i).getText().trim().equals(optionItem)) {
				speedDropownItems.get(i).click();
				break;
			}
		}
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);;
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
