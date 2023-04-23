package group;

import org.testng.annotations.Test;

public class Payment_Address {

	@Test(groups = "pay")
	public void Shipping() {
		System.out.println("Shipping");
	}
	
	@Test(groups = "pay")
	public void Buyer() {
		System.out.println("Buyer");
	}
	
}
