package http_helpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ApiHelpers {


    public Response performPost(String url, Map<String, String> headermap, Object body) {
        return RestAssured.given().when().headers(headermap).body(body).when().post(url).then().extract().response();
    }

    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("subscription-key", "123");
        return headers;
    }

}
