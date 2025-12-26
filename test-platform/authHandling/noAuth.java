package authHandling;

import io.restassured.specification.RequestSpecification;

public class noAuth implements authStrategy {
     
    @Override
    public void apply(RequestSpecification request) {

    }
}