import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojos.User;

import java.util.Map;

public class UserUtils {
    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String KEY = TestConfig.getAPIKey();

    public Response getUser(String username){
        return RestAssured
                .given()
                .baseUri(BASE_URI)
                .headers(Map.of(
                        "api_key",KEY
                ))
                .basePath("/user/{username}")
                .pathParams(Map.of(
                        "username",username
                ))
                .when()
                .get()
                .thenReturn();
    }

    public Response updateUser(String username,User newData){
        return RestAssured
                .given()
                    .baseUri(BASE_URI)
                    .headers(Map.of(
                            "api_key",KEY
                    ))
                    .basePath("/user/{username}")
                    .pathParams(Map.of(
                            "username",username
                    ))
                    .contentType(ContentType.JSON)
                    .body(newData)
                .when()
                .put()
                .thenReturn();
    }
}
