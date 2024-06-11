package user.create;

import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PostUserSadPathTest {

    private static Response response;

    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String PATH = "/user";
    private static final String API_KEY = TestConfig.getAPIKey();

    @BeforeAll
    public static void beforeAll() {
        response = RestAssured.given()
                .header("api_key", API_KEY)
                .header("Content-Type", "application/json")
                .body("{}")
                .post(BASE_URI + PATH);
    }

    @Test
    @DisplayName("Status code 400 when sent an empty request")
    public void validateResponseBody() {
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(400));
    }
}
