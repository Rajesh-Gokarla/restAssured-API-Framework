package authHandling;

import io.restassured.specification.RequestSpecification;

public interface AuthStrategy {
    void apply(RequestSpecification request);
}