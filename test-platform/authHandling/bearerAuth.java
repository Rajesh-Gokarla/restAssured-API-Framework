package authHandling;

import chaining.TestContext;
import io.restassured.specification.RequestSpecification;

public class BearerAuth implements AuthStrategy {

    @Override
    public void apply(RequestSpecification request) {
        String token = (String) TestContext.get("ACCESS_TOKEN");
        if (token == null) {
            throw new RuntimeException("Access token not found in TestContext");
        }
        request.header("Authorization", "Bearer " + token);
    }
}