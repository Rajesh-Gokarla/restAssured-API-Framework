package authHandling;

import io.restassured.specification.RequestSpecification;

public class bearerAuth implements authStrategy {

    private final String token;

    public bearerAuth(String token) {
        this.token = token;
    }

    @Override
    public void apply(RequestSpecification request) {
        request.header("Authorization", "Bearer " + token);
    }
}