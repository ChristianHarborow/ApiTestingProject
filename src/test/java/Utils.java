import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.util.Map;

public class Utils {

    public static RequestSpecification postRequestSpecForUser(String baseUri, String path, String token, JSONObject user) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addHeaders(Map.of(
                        "api_key", token,
                        "Content-Type", "application/json"
                ))
                .setBody(user)
                .build();
    }

    public static RequestSpecification postRequestSpecForUserList(String baseUri, String path, JSONObject[] users) {

        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addHeaders(Map.of(
                        "Content-Type", "application/json"
                ))
                .setBody(users)
                .build();
    }

    public static RequestSpecification getUserRequestSpec(String baseUri, String path, String username, String password) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addHeaders(Map.of(
                        "Content-Type", "application/json"
                ))
                .addQueryParam("username", username)
                .addQueryParam("password", password)
                .build();
    }

    public static RequestSpecification deleteUserRequestSpec(String baseUri, String path, String username) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addPathParams(Map.of("username", username))
                .build();
    }
}
