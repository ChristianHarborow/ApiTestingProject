package user.update;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.User;
import user.UserUtils;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class UpdateUser3_5_1Test {
    private static Response response;
    private static User user;

    @BeforeAll
    public static void beforeAll(){
        response = UserUtils.getUser("theUser");
        user = response.as(User.class);

        user.setPhone("11112");

        response = UserUtils.updateUser("theUser", user);
        user = response.as(User.class);
    }

    @Test
    @DisplayName("Sending a put request to update an existing user with valid data, the status code of the response should be 200")
    void validUpdateUser_TestResponseStatusCode(){
        assertThat(response.statusCode(), is(200));
    }

    @Test
    @DisplayName("Sending a put request to update an existing user with valid data, updates the data correctly")
    void validUpdateUser_UpdatesUser(){
        assertThat(user.getPhone(), is("11112"));
    }
}
