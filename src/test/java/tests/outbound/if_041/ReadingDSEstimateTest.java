package tests.outbound.if_041;

import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;
import reports.MyExtentReport;

import java.io.IOException;

public class ReadingDSEstimateTest {

    @Test(enabled = true, groups = {"if-041", "consumption", "sanity"}, description = "Outbound-Invoke IF-041[ReadingDSEstimate]")
    public void test_if_041_reading_dsestimate() throws IOException {

    // This is a sample test as part of Reporting
        MyExtentReport.getTest().log(Status.INFO, "This is a sample test in Outbound");

    }
}
