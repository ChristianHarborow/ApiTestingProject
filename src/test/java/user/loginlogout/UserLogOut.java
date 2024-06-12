package user.loginlogout;

import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserLogOut {

    private static Response response;

    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String PATH = "/user/logout";

    @BeforeAll
    public static void beforeAll() {
        response = RestAssured
                .given()
                .baseUri(BASE_URI)
                .basePath(PATH)
                .when()
                .get()
                .thenReturn();
    }

    @Test
    @DisplayName("correct response code when user logs out")
    void correctResponseCode_userLogOut(){
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(200));
    }

    @Test
    @DisplayName("Message: User logged")
    void confirmationMessage_UserLogOut(){
        MatcherAssert.assertThat(response.asString(), Matchers.is("User logged out"));
    }
}
