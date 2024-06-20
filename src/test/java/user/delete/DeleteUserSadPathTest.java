package user.delete;

import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.UserUtils;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class DeleteUserSadPathTest {
    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String PATH = "/user";

    @Test
    @DisplayName("Status code is 404 when not given the correct path")
    public void statusCode404GivenIncorrectPath() {
        Response response = RestAssured
                .given()
                .when()
                .delete(BASE_URI + PATH);
        assertThat(response.getStatusCode(), is(404));
    }

    @Test
    @DisplayName("Status code is 400 when given empty username")
    public void validStatusCode() {
        Response response = UserUtils.deleteUser(" ");
        assertThat(response.getStatusCode(), is(400));
    }
}
