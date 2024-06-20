package user.loginlogout;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.UserUtils;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class UserLogInHappyPathTest {

    private static Response response;

    private static final String USERNAME = "theUser";
    private static final String PASSWORD = "abc123";

    @BeforeAll
    public static void beforeAll() {
        response = UserUtils.loginUser(USERNAME, PASSWORD);
    }

    @Test
    @DisplayName("correct response code when logging in user")
    void correctResponseCode_UserLogIn(){
        assertThat(response.statusCode(), is(200));
    }

    @Test
    @DisplayName("Message: Logged in user session")
    void successMessage_UserLogIn(){
        assertThat(response.asString(), containsString("Logged in user session:"));
    }
}
