import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.User;

import java.util.Map;

public class GetUserWithEmptyRequestTest {
    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String KEY = TestConfig.getAPIKey();

    private static Response response;

    @BeforeAll
    public static void beforeAll() {
        response = RestAssured
                .given()
                .baseUri(BASE_URI)
                .headers(Map.of(
                        "api_key", KEY
                ))
                .basePath("/user/{username}")
                .pathParams(Map.of(
                        "username", ""
                ))
                .when()
                .get()
                .thenReturn();
    }

    @Test
    @DisplayName("requesting info for non-existent user returns response with status code 400")
    void requestingNonExistentUser_returns400Status() {
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(400));
    }
}