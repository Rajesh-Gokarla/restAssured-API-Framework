package authHandling;

import io.restassured.specification.RequestSpecification;

public class basicAuth implements authStrategy {

    private final String username;
    private final String password;

    public basicAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void apply(RequestSpecification request) {
        request.auth().preemptive().basic(username, password);
    }
}