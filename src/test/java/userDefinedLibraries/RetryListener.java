package userDefinedLibraries;

/**
 * This class is defined in order to setup different Webdrivers.
 * 
 * @author BINARYBEASTS
 * @since 2020/11/27
 * 
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class RetryListener implements IAnnotationTransformer {

	
	@SuppressWarnings("rawtypes")
	public void transform(ITestAnnotation testannotation, Class testClass, Constructor testConstructor,
			Method testMethod) {
		IRetryAnalyzer retry = testannotation.getRetryAnalyzer();
		if (retry == null) {
			testannotation.setRetryAnalyzer(RetryFailedTestCases.class);
		}
	}
}