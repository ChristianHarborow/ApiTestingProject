import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import config.TestConfig;

public class UserLogInHappyPathTest {

    private static Response response;

    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String PATH = "/user/login";
    private static final String KEY = TestConfig.getAPIKey();
    private static final String USERNAME = "theUser";
    private static final String PASSWORD = "abc123";

    @BeforeAll
    public static void beforeAll() {
        response = RestAssured
                .given(Utils.getUser(BASE_URI, PATH, KEY, USERNAME, PASSWORD))
                .when()
                .get()
                .thenReturn();
    }

    @Test
    @DisplayName("correct response code when logging in user")
    void correctResponseCode_requestDataForExistingUser(){
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(200));
    }
}
