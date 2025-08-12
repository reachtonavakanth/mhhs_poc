package listners;

import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.MyExtentReport;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

public class TestListeners implements ITestListener {

    private String reportName;

    @Override
    public void onTestStart(ITestResult result) {
        if(result.getTestContext().getIncludedGroups().length==0){
            reportName = "Test Results";
        }
        else
         reportName = "Test Results - "+String.join(", ", result.getTestContext().getIncludedGroups());

        try {
            MyExtentReport.startTest(reportName, result.getName(), result.getMethod().getDescription())
                    .assignCategory(result.getMethod().getDescription().split("-")[0])
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        MyExtentReport.getTest().log(Status.PASS, "Test Passed !!!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        if (result.getThrowable() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            result.getThrowable().printStackTrace(pw);
        }
//   MyExtentReport.getTest().fail("Test Failed");
        MyExtentReport.getTest().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        MyExtentReport.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        //  ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        //ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        // ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        try {
            MyExtentReport.getReporter(reportName).flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}