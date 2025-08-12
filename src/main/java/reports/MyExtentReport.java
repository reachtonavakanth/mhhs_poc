package reports;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;

public class MyExtentReport {
    static ExtentReports extent;
    final static String filePath = "Report";
    static Map<Integer, ExtentTest> extentTestMap = new HashMap();

    public synchronized static ExtentReports getReporter(String reportName) throws IOException {
        String reportDir = "HTMLReports" + File.separator + filePath+"_"+now().format(ofPattern("yyyy-MM-dd-HH-mm-ss"))+".html" ;
        if (extent == null) {
            extent = new ExtentReports();
            ExtentSparkReporter sparktHtml = new ExtentSparkReporter(reportDir);
            sparktHtml.config().setDocumentTitle("Automation Execution Report");
            sparktHtml.config().setReportName(reportName);
            sparktHtml.config().setTheme(Theme.DARK);
            extent.attachReporter(sparktHtml);
        }
        return extent;
    }

    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized ExtentTest startTest(String reportName, String testName, String desc) throws IOException {
        ExtentTest test = getReporter(reportName).createTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }
}