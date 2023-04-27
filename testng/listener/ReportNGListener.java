package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ReportNGListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		
	}

	// When test case get passed, the method is called
	@Override
	public void onTestSuccess(ITestResult result) {
		
	}

	// When Test case get failed, the method is called
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("The name of testcase faied is " + result.getName());
		System.out.println("Hàm này để chụp hình testcase fail và add vào Report HTML");
	}

	// When Test case get skipped, the method is called
	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	// When test case get started, the method is called
	@Override
	public void onStart(ITestContext context) {
		
	}

	// When test case get finish, the method is called
	@Override
	public void onFinish(ITestContext context) {
		
	}

}
