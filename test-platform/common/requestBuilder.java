package common;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class requestBuilder {

    public static RequestSpecification buildRequest(String baseUri, Map<String, String> headers, Object body) {

        RequestSpecification request = RestAssured
                .given()
                .baseUri(baseUri)
                .headers(headers);

        if (body != null) {
            request.body(body);
        }

        return request;
    }
}