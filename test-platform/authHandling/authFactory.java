package authHandling;

import config.Config;

public class AuthFactory {

    public static AuthStrategy getAuth() {
        AuthType type = AuthType.valueOf(
                Config.value("AUTH_TYPE")
        );

        switch (type) {
            case BEARER:
                return new BearerAuth();
            default:
                return new NoAuth();
        }
    }
}