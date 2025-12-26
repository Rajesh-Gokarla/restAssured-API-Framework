package businessLogic;

import authHandling.AuthFactory;
import common.*;
import config.Config;
import endPoints.Operation;
import endPoints.Service;
import io.restassured.response.Response;

import java.util.Map;

public class CifService {

    public static Response createCif(String payload, ContentType contentType) {

        var req = RequestBuilder.build(
                Config.baseUrl(Service.USER_SERVICE.name()),
                Map.of(
                        "Content-Type", contentType.value(),
                        "Accept", contentType.value()
                ),
                payload
        );

        AuthFactory.getAuth().apply(req);
        return ApiActions.post(req, Config.endpoint(Operation.CREATE_CIF));
    }
}