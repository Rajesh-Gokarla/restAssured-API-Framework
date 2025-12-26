package common;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reports.ExtentTestManager;
import com.aventstack.extentreports.ExtentTest;

public class ApiActions {

    private static final Logger logger =
            LoggerFactory.getLogger(ApiActions.class);

    public static Response get(RequestSpecification request, String endpoint) {
        return execute(request, endpoint, "GET");
    }

    public static Response post(RequestSpecification request, String endpoint) {
        return execute(request, endpoint, "POST");
    }

    public static Response put(RequestSpecification request, String endpoint) {
        return execute(request, endpoint, "PUT");
    }

    public static Response delete(RequestSpecification request, String endpoint) {
        return execute(request, endpoint, "DELETE");
    }

    private static Response execute(
            RequestSpecification request,
            String endpoint,
            String method) {

        logger.info("{} {}", method, endpoint);

        Response response = switch (method) {
            case "GET" -> request.when().get(endpoint);
            case "POST" -> request.when().post(endpoint);
            case "PUT" -> request.when().put(endpoint);
            case "DELETE" -> request.when().delete(endpoint);
            default -> throw new IllegalStateException("Invalid HTTP method");
        };

        logger.info("Status: {}", response.statusCode());

        if (response.statusCode() >= 400) {

            String log = """
                REQUEST:
                %s %s

                RESPONSE:
                Status Code: %d
                Body:
                %s
                """.formatted(
                    method,
                    endpoint,
                    response.statusCode(),
                    safeBody(response)
            );

            ExtentTest scenario = ExtentTestManager.getScenario();
            if (scenario != null) {
                scenario.fail("API Call Failed").info(log);
            } else {
                logger.error("Extent scenario not initialized");
                logger.error(log);
            }

            response.then().log().all();
        }

        return response;
    }

    private static String safeBody(Response response) {
        try {
            return response.getBody().asPrettyString()
                    .replaceAll(
                            "(?i)token\"\\s*:\\s*\".*?\"",
                            "token\":\"***MASKED***\""
                    );
        } catch (Exception e) {
            return "Unable to parse response body";
        }
    }
}