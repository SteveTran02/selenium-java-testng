package group;

import org.testng.annotations.Test;

public class Payment_Type {

	@Test(groups = "pay")
	public void Visa() {
		System.out.println("Visa");
	}
	
	@Test(groups = "pay")
	public void DigitalWallet() {
		System.out.println("DigitalWallet");
		
	}
	
	@Test(groups = "pay")
	public void InternetBanking() {
		System.out.println("InternetBanking");
	}
	
	
}
