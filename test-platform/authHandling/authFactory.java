package authHandling;

import config.config;

public class authFactory {

    public static authStrategy getAuth(authTypes type) {

        switch (type) {

            case BASIC:
                return new basicAuth(
                        config.get("basic.username"),
                        config.get("basic.password")
                );

            case BEARER:
            case OAUTH2:
                return new bearerAuth(tokenManager.getToken());

            case NO_AUTH:
            default:
                return new noAuth();
        }
    }
}