package tests.inbound.if_041;

import azure.ServiceBusUtils;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import http_helpers.ApiHelpers;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import payload_builders.inbound.if_041.BuildReadingCosPayload;
import reports.MyExtentReport;
import utils.DbUtil;
import utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ReadingCosTest extends BuildReadingCosPayload {


    @Test(enabled = true, groups = {"if-041", "consumption", "sanity"}, description = "Inbound-Invoke IF-041[ReadingCos] and validate data against ServiceBus queue")
    public void test_if_041_reading_cos() throws IOException, SQLException {
        String testResourceDir = System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "test" + File.separator + "resources" +
                File.separator;

        String servicebusPropertiesFilePath = testResourceDir + "servicebus.properties";
        String testDataFilePath = testResourceDir + "testdata" + File.separator + "inbound" +
                File.separator + "if_041" + File.separator + "readingCos.json";

        FileUtils fileUtils = new FileUtils();
        JSONObject testDataJsonObj = fileUtils.getJsonObject(testDataFilePath, "tc_01");

        String endpoint = "IF-041/14000003";

        FileUtils utils = new FileUtils();

        String serviceBusConnection = """
                Endpoint=%s;SharedAccessKeyName=%s;SharedAccessKey=%s
                """.formatted(
                utils.getPropValue(servicebusPropertiesFilePath).getProperty("Endpoint"),
                utils.getPropValue(servicebusPropertiesFilePath).getProperty("SharedAccessKeyName"),
                utils.getPropValue(servicebusPropertiesFilePath).getProperty("SharedAccessKey")
        );
        String queueName = utils.getPropValue(testResourceDir + "servicebus.properties").getProperty("queueName");

        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                getPayload(testDataJsonObj.getString("mpan"),
                        testDataJsonObj.getString("gsp"),
                        testDataJsonObj.getString("meterId"),
                        testDataJsonObj.getString("cumulativeRegisterReading"),
                        testDataJsonObj.getString("cumulativeRegisterReadingDateTime"),
                        testDataJsonObj.getString("readingMethod"),
                        testDataJsonObj.getString("siteVisitCheckCode")
                ));
        System.out.println(jsonPayload);
        MyExtentReport.getTest().log(Status.INFO, "IF-041[ReadingCos] payload is generated");
//        MyExtentReport.getTest().info(MarkupHelper.createCodeBlock(jsonPayload, CodeLanguage.JSON));
        MyExtentReport.getTest().info(jsonPayload);

        System.out.println("API Call begins ..................!!! ");

        ApiHelpers apiHelpers = new ApiHelpers();
        Response if_041Response = apiHelpers.performPost(runnerObj.getString("baseUrl") + endpoint, apiHelpers.getHeaders(), jsonPayload);
        System.out.println(if_041Response.asString());
        MyExtentReport.getTest().log(Status.INFO, "IF-041[ReadingCos] api call is invoked");
        Assert.assertEquals(if_041Response.statusCode(), 200, "Expected and Actual status codes are not equal");
        System.out.println("API Call end ..................!!! ");

        // validation in queue

        System.out.println("ServiceBus call begins ..................!!! ");

        ServiceBusUtils serviceBusUtils = new ServiceBusUtils();
        serviceBusUtils.getCounts(serviceBusConnection, queueName);
        String message = serviceBusUtils.peekMessage(serviceBusConnection, queueName);
        MyExtentReport.getTest().info("Received data from ServiceBus..................!!! ");
        System.out.println(message);
        MyExtentReport.getTest().info(message);
        System.out.println("ServiceBus data validation begins ..................!!! ");
//        MyExtentReport.getTest().info("ServiceBus data validation begins ..................!!! ");
        JsonNode messageJson = mapper.readTree(message);
        System.out.println("ServiceBus data validation ends ..................!!! ");
//        MyExtentReport.getTest().info("ServiceBus data validation ends ..................!!! ");


        // DB Validation

        System.out.println("Database Connectivity Begins  ..................!!!");
        DbUtil dbUtil = new DbUtil();
        Connection dbConnection = dbUtil.getConnection(testResourceDir + "db.properties");
        PreparedStatement statement = dbConnection.prepareStatement("select * from mpt.register_readings where meterpoint = '1234567891' and reading_datetime = '2025-07-24';");
        ResultSet resultSet = statement.executeQuery();

        System.out.println("Received the data from Database ..................!!!");
        MyExtentReport.getTest().info("Received the data from Database ..................!!!");

        resultSet.next();

        System.out.println("MeterPoint " + resultSet.getString("meterpoint") + ", Meter ID: " + resultSet.getString("meter_id") +
                ", Reading: " + resultSet.getInt("cumulative_register_reading"));
        MyExtentReport.getTest().info("MeterPoint " + resultSet.getString("meterpoint") + ", Meter ID: " + resultSet.getString("meter_id") +
                ", Reading: " + resultSet.getInt("cumulative_register_reading"));

        MyExtentReport.getTest().info("Data Validations Begins !!!");

        Assert.assertEquals(messageJson.get("meterpoint").asText(), testDataJsonObj.getString("mpan"), "Mpan in the payload & service bus is not matching");
        Assert.assertEquals(resultSet.getString("meterpoint"), testDataJsonObj.getString("mpan"), "Mpan in the payload & database is not matching");
        Assert.assertEquals(resultSet.getString("meter_id")+9, testDataJsonObj.getString("meterId"), "MeterID in the payload & database is not matching");

        resultSet.close();
        statement.close();
        dbConnection.close();
    }

}
