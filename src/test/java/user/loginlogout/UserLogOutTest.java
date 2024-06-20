package user.loginlogout;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.UserUtils;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class UserLogOutTest {

    private static Response response;

    @BeforeAll
    public static void beforeAll() {
        response = UserUtils.logoutUser();
    }

    @Test
    @DisplayName("correct response code when user logs out")
    void correctResponseCode_userLogOut(){
        assertThat(response.statusCode(), is(200));
    }

    @Test
    @DisplayName("Message: User logged")
    void confirmationMessage_UserLogOut(){
        assertThat(response.asString(), is("User logged out"));
    }
}
