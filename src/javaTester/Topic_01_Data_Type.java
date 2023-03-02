package javaTester;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_01_Data_Type {

	public static void main(String[] args) {
		
	// 1. Primitive variables
		//Integer
			byte bNumber = 127; 
			short sNumber = 30000;
			int iNumber = 2142523490;
			long lNumber = 24242424L;
			
		//Floating
			//float
			float studentPoint = 9.5f;
			//double
			double employeeSalary = 35.1d;
			
		//Character
			char name = ' ';
		//Boolean
			boolean voucherPurchased = true;
			voucherPurchased = false;
			
			
	// 2. Reference variables
		//Class
			FirefoxDriver driver = new FirefoxDriver();
					
		//Interface
			WebElement firstNameTextbox;
			
		//String
			String firstName = "Steve";
		//Object
			Object people;
		//Array
			String[] studentName = {"Steve", "Derek", "David"};
		//Collection: List/ Set/ Queue
			List<WebElement> checkboxes = (List<WebElement>) driver.findElement(By.cssSelector(""));
		//Map
			Map<String, Integer> student = new HashMap<String, Integer>();
			
		
	}

}
