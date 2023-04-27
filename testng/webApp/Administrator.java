package webApp;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Administrator {

	// CRUD: Create Read Update Delete
	@Test(groups = "web")
	public void Admin_01_Create_New_Author() {
		
	}

	@Test(groups = "web", dependsOnMethods = "Admin_01_Create_New_Author")
	public void Admin_02_View_Author() {
		
	}

	@Test(groups = "web", dependsOnMethods = {"Admin_01_Create_New_Author","Admin_02_View_Author"})
	public void Admin_03_Edit_Author() {
		Assert.assertTrue(false);
	}

	@Test(groups = "web", dependsOnMethods = "Admin_03_Edit_Author")
	public void Admin_04_Delete_Author() {

	}
}
