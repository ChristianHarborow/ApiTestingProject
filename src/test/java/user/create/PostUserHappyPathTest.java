package user.create;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.User;
import user.UserBody;
import user.UserUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostUserHappyPathTest {

    private static Response response;
    private static User user;

    @BeforeAll
    public static void beforeAll() {
        UserBody requestBody = new UserBody(
                10, "theUser", "John", "James",
                "john@email.com", "12345", "12345", 1
        );

        response = UserUtils.postUser(requestBody);

        user = response.as(User.class);
    }

    @Test
    @DisplayName("User can create a new account")
    public void validateResponseBody() {
        assertThat(response.getStatusCode(), is(200));
    }

    @Test
    @DisplayName("Correct data for created user")
    void correctData_requestDataForExistingUser(){
        assertThat(user.getId(), is(10));
        assertThat(user.getUsername(), is("theUser"));
        assertThat(user.getFirstName(), is("John"));
        assertThat(user.getLastName(), is("James"));
        assertThat(user.getEmail(), is("john@email.com"));
        assertThat(user.getPhone(), is("12345"));
        assertThat(user.getUserStatus(), is(1));
    }
}
