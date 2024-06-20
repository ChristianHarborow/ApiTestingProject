package user.update;

import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.User;
import user.UserUtils;

public class UpdateUser3_5_2Test {

    private static Response response;
    private static User user;

    @BeforeAll
    public static void beforeAll(){
        user = UserUtils.getUser("theUser").as(User.class);
        user.setPhone(null);
        response = UserUtils.updateUser("theUser",user);

        user = response.as(User.class);
    }

    @Test
    @DisplayName("Given data for a user with missing phone number, attempting to update theUser should return a 400 response")
    void GivenInvalidData_UpdateUserReturns400Code(){
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(400));
    }

    @Test
    @DisplayName("Given data for a user with missing phone number, attempting to update theUser should not work")
    void GivenInvalidData_UpdateUserDoesntUpdate(){
        MatcherAssert.assertThat(user.getPhone(), Matchers.notNullValue());
    }
}
