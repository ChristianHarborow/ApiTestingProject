import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import config.TestConfig;

public class UserLoginSadPath {
    private static Response response;

    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String PATH = "/user/login";
    private static final String KEY = TestConfig.getAPIKey();
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    @BeforeAll
    public static void beforeAll() {
        response = RestAssured
                .given(Utils.getUserRequestSpec(BASE_URI, PATH, KEY, USERNAME, PASSWORD))
                .when()
                .get()
                .thenReturn();

    }

    @Test
    @DisplayName("Logging in user with empty username and password")
    void correctResponseCode_requestDataWithEmptyUsernameAndPassword(){
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(400));
    }
}
