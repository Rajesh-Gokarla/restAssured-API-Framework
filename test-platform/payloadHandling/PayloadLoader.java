package payloadHandling;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class PayloadLoader {

    public static String load(String path) {
        try (InputStream is =
                     PayloadLoader.class
                             .getClassLoader()
                             .getResourceAsStream(path)) {

            if (is == null)
                throw new RuntimeException("Payload not found: " + path);

            return new String(is.readAllBytes(), StandardCharsets.UTF_8);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}