import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeleteUserSadPathTest {
    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String PATH = "/user";
    private static final String API_KEY = TestConfig.getAPIKey();

    @Test
    @DisplayName("Status code is 404 when not given the correct path")
    public void statusCode404GivenIncorrectPath() {
        Response response = RestAssured
                .given()
                .when()
                .delete(BASE_URI + PATH);
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(404));
    }

    @Test
    @DisplayName("Status code is 404 when not given the correct path")
    public void validStatusCode() {
        Response response = RestAssured
                .given(Utils.deleteUserRequestSpec(
                        BASE_URI,
                        PATH + "/{username}",
                        ""
                ))
                .when()
                .delete();
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(404));
    }
}
