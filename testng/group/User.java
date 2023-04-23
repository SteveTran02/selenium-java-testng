package group;

import org.testng.annotations.Test;

public class User {

	@Test(groups = "user")
	public void Login() {
		System.out.println("Login");
	}
	
	@Test(groups = "user")
	public void Register() {
		System.out.println("Register");
	}
	
}
