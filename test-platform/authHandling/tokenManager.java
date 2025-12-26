package authHandling;

import config.config;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class tokenManager {

    private static String cachedToken;

    public static String getToken() {

        if (cachedToken != null) {
            return cachedToken;
        }

        tokenSource source = tokenSource.valueOf(
            config.get("token.source").toUpperCase()
        );

        switch (source) {

            case STATIC:
                cachedToken = config.get("static.token");
                break;

            case API:
                cachedToken = fetchTokenFromApi();
                break;

            default:
                throw new RuntimeException("Invalid Token Source");
        }

        return cachedToken;
    }

    private static String fetchTokenFromApi() {

        Response response = RestAssured
            .given()
            .baseUri(config.get("base.url"))
                .contentType("application/json")
                .body("""
                        {
                          "username": "%s",
                          "password": "%s"
                        }
                        """.formatted(
                        config.get("auth.username"),
                        config.get("auth.password")
                ))
                .post(config.get("auth.endpoint"));

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Token generation failed");
        }

        return response.jsonPath().getString("token");
    }

    // Optional: force refresh (used on 401 later)
    public static void clearToken() {
        cachedToken = null;
    }
}