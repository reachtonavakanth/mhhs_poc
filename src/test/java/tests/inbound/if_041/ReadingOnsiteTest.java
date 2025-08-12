package tests.inbound.if_041;

import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import http_helpers.ApiHelpers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payload_builders.inbound.if_041.BuildReadingOnsitePayload;
import reports.MyExtentReport;

import java.io.IOException;


public class ReadingOnsiteTest extends BuildReadingOnsitePayload{

    @Test(enabled = true, groups = {"if-041", "consumption", "sanity"}, description = "Inbound-Invoke IF-041[ReadingOnsite]")
    public void test_if_041_reading_onsite() throws IOException {

        String mpan = "1234567810";
        String gsp ="_N";
        String meterId ="MM234568";
        String endpoint = "IF-041/14000003";

        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(getPayload(mpan, gsp,meterId));
        System.out.println(jsonPayload);
        MyExtentReport.getTest().log(Status.INFO, "IF-041[ReadingOnsite] payload is generated");
        MyExtentReport.getTest().info(jsonPayload);

        System.out.println("API Call begins ..................!!! ");
        ApiHelpers apiHelpers = new ApiHelpers();
        Response if_041Response = apiHelpers.performPost(runnerObj.getString("baseUrl") + endpoint, apiHelpers.getHeaders(), jsonPayload);
        System.out.println(if_041Response.asString());
        MyExtentReport.getTest().log(Status.INFO, "IF-041[ReadingOnsite] api call is invoked");
        Assert.assertEquals(if_041Response.statusCode(), 200, "Expected and Actual status codes are not equal");
        System.out.println("API Call end ..................!!! ");


    }

}
