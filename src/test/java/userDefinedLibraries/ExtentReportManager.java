package userDefinedLibraries;

/**
 * This class is defined in order to generate HTML reports about test cases.
 * 
 * @author BINARYBEASTS
 * @since 2020/11/27
 * 
 */

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportManager {
	public static ExtentReports report;

	public static ExtentReports getReportInstance() {

		if (report == null) {

			// currentTimeMillis() to prevent overwriting of reports for pass and fail
			String reportName = System.currentTimeMillis() + ".html";
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("./src/test/resources/Reports/extent" + reportName);
			report = new ExtentReports();
			report.attachReporter(htmlReporter);
		}

		return report;
	}
}
