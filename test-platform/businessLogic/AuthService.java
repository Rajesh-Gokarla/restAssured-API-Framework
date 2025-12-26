package businessLogic;

import chaining.TestContext;
import common.ApiActions;
import common.RequestBuilder;
import config.Config;
import endPoints.Service;
import io.restassured.response.Response;

import java.util.Map;

public class AuthService {

    public static void generateToken() {

        var request = RequestBuilder.build(
                Config.baseUrl(Service.AUTH_SERVICE),
                Map.of("Content-Type", "application/json"),
                """
                {
                  "username": "%s",
                  "password": "%s"
                }
                """.formatted(
                        Config.value("AUTH_USERNAME"),
                        Config.value("AUTH_PASSWORD")
                )
        );

        Response response = ApiActions.post(
                request,
                Config.value("AUTH_ENDPOINT")
        );

        if (response.statusCode() != 200) {
            throw new RuntimeException("Auth API failed");
        }

        String token = response.jsonPath()
                .getString(Config.value("TOKEN_JSON_PATH"));

        TestContext.put("ACCESS_TOKEN", token);
    }
}