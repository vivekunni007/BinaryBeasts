package userDefinedLibraries;

/**
 * This class is defined in order to initialize rerun for the failed test cases.
 * 
 * @author BINARYBEASTS
 * @since 2020/11/27
 * 
 */

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTestCases implements IRetryAnalyzer {
	public static int retryCnt = 0;
	
	private int maxRetryCnt = 3;
	// This method will be called everytime a test fails. It will return TRUE if a
	// test fails and need to be retried, else it returns FALSE

	public boolean retry(ITestResult result) {

		if (retryCnt < maxRetryCnt) {
			System.out.println("Retrying " + result.getName() + " again and the count is " + (retryCnt + 1));
			retryCnt++;
			return true;
		}
		return false;
	}
}
