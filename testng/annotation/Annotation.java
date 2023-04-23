package annotation;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Annotation {
	
	@Test
	public void Login_01_Empty() {
		System.out.println("Login_01_Empty");
	}
	@Test
	public void Login_02_Invalid_Email() {
		System.out.println("Login_02_Invalid_Email");
	}
	@Test
	public void Login_03_Invalid_Password() {
		System.out.println("Login_03_Invalid_Password");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("before Method");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("after Method");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("before Class");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("after Class");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("before Test");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("after Test");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("before Suite");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("after Suite");
	}

}
