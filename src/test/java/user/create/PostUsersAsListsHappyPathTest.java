package user.create;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.User;
import user.UserBody;
import user.UserUtils;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class PostUsersAsListsHappyPathTest {
    private static Response response;
    private static User[] users;

    @BeforeAll
    public static void beforeAll() {
        UserBody[] requestBody = {
                new UserBody(
                    10, "theUser", "John", "James",
                    "john@email.com", "12345", "12345", 1
                ),
                new UserBody(
                    11, "theUser123", "John", "James",
                    "john@email.com", "12345678", "12345678", 1
                )
        };

        response = UserUtils.postUserList(requestBody);

        users = response.as(User[].class);
    }

    @Test
    @DisplayName("Can create list of users with given input array")
    public void validateResponseBody() {
        assertThat(response.getStatusCode(), is(200));
    }

    @Test
    @DisplayName("Response returns 2 users")
    public void validateCorrectNumberOfUsersCreated() {
        assertThat(users.length, is(2));
    }

    @Test
    @DisplayName("Response returns correct user1")
    public void validateCorrectUser1() {
        assertThat(users[0].getId(), is(10));
        assertThat(users[0].getUsername(), is("theUser"));
        assertThat(users[0].getFirstName(), is("John"));
        assertThat(users[0].getLastName(), is("James"));
        assertThat(users[0].getEmail(), is("john@email.com"));
        assertThat(users[0].getPhone(), is("12345"));
        assertThat(users[0].getUserStatus(), is(1));
    }

    @Test
    @DisplayName("Response returns correct user2")
    public void validateCorrectUser2() {
        assertThat(users[1].getId(), is(11));
        assertThat(users[1].getUsername(), is("theUser123"));
        assertThat(users[1].getFirstName(), is("John"));
        assertThat(users[1].getLastName(), is("James"));
        assertThat(users[1].getEmail(), is("john@email.com"));
        assertThat(users[1].getPhone(), is("12345678"));
        assertThat(users[1].getUserStatus(), is(1));
    }
}
