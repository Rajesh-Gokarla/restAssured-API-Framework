package authHandling;

import io.restassured.specification.RequestSpecification;

public interface authStrategy {
    void apply(RequestSpecification request);
}