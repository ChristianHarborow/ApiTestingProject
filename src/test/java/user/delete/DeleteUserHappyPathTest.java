package user.delete;

import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.Utils;

public class DeleteUserHappyPathTest {
    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String PATH = "/user/{username}";
    private static final String API_KEY = TestConfig.getAPIKey();

    private static Response response;

    @BeforeAll
    public static void beforeAll() {
        response = RestAssured
                .given(Utils.deleteUserRequestSpec(
                        BASE_URI,
                        PATH,
                        "user1"
                ))
                .when()
                .delete();
    }

    @Test
    @DisplayName("Status code is 200 when user is deleted")
    public void validStatusCode() {
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));
    }
}
