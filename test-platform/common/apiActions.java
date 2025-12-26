package common;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class apiActions {

    public static Response get(RequestSpecification request, String endpoint) {
        return request.when().get(endpoint);
    }

    public static Response post(RequestSpecification request, String endpoint) {
        return request.when().post(endpoint);
    }

    public static Response put(RequestSpecification request, String endpoint) {
        return request.when().put(endpoint);
    }

    public static Response delete(RequestSpecification request, String endpoint) {
        return request.when().delete(endpoint);
    }
}