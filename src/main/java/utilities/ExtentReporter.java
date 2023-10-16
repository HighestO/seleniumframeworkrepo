package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
	
	static ExtentReports extentReport;
	
	public static ExtentReports getExtentReport() {
		
		String extentReportpath = System.getProperty("user.dir") + "\\reports\\extentsreport.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(extentReportpath);

		// Name of the report
		reporter.config().setReportName("tutorialsninja Automation Report");
		// Title of the report
		reporter.config().setDocumentTitle("Test Results");

		// Create an object for the extent reports
		extentReport = new ExtentReports();
		extentReport.attachReporter(reporter);
		extentReport.setSystemInfo("Operating System", "Windows 10");
		extentReport.setSystemInfo("Tested By", "Richmond Frempong");
		
		
		return extentReport;
		
		
	}

}
