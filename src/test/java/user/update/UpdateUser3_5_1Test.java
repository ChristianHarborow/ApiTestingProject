package user.update;

import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.User;
import user.UserUtils;

public class UpdateUser3_5_1Test {
    private static Response response;
    private static User user;

    private static UserUtils userUtils = new UserUtils();

    @BeforeAll
    public static void beforeAll(){
        response = userUtils.getUser("theUser");
        user = response.as(User.class);

        user.setPhone("11112");

        response = userUtils.updateUser("theUser",user);
        user = response.as(User.class);
    }

    @Test
    @DisplayName("Sending a put request to update an existing user with valid data, the status code of the response should be 200")
    void validUpdateUser_TestResponseStatusCode(){
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(200));
    }

    @Test
    @DisplayName("Sending a put request to update an existing user with valid data, updates the data correctly")
    void validUpdateUser_UpdatesUser(){
        MatcherAssert.assertThat(user.getPhone(), Matchers.is("11112"));
    }
}
