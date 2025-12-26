package common;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RequestBuilder {

    public static RequestSpecification build(
            String baseUrl,
            Map<String, String> headers,
            Object body
    ) {

        RequestSpecification request =
                RestAssured.given()
                        .baseUri(baseUrl);

        // Apply headers if provided
        if (headers != null && !headers.isEmpty()) {
            request.headers(headers);
        }

        // Apply body if provided
        if (body != null) {
            request.body(body);
        }

        return request;
    }
}