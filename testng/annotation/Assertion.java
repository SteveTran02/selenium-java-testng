package annotation;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Assertion {
	String text = "Selenium WebDriver";
	boolean status = false;
	
	@Test
	public void TC_01_Assert_True() {
		// Using data type return is Boolean
		Assert.assertTrue(status);
	}
	
	@Test
	public void TC_02_Assert_Equals() {
		// Using equals expect result and actual result
		Assert.assertEquals(text, "Selenium Grid");;
	}
	
	
}
