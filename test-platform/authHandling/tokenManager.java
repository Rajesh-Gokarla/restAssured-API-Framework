package authHandling;

import config.Config;

public class TokenManager {

    private static String token;

    public static String getToken() {
        if (token == null) {
            token = Config.value("STATIC_TOKEN");
        }
        return token;
    }

    public static void clear() {
        token = null;
    }
}