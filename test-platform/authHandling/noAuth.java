package authHandling;

import io.restassured.specification.RequestSpecification;

public class NoAuth implements AuthStrategy {
    public void apply(RequestSpecification request) {}
}