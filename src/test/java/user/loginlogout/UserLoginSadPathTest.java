package user.loginlogout;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.UserUtils;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class UserLoginSadPathTest {
    private static Response response;

    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    @BeforeAll
    public static void beforeAll() {
        response = UserUtils.loginUser(USERNAME, PASSWORD);
    }

    @Test
    @DisplayName("Logging in user with empty username and password")
    void correctResponseCode_requestDataWithEmptyUsernameAndPassword(){
        assertThat(response.statusCode(), is(400));
    }
}
