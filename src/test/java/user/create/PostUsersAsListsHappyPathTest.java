package user.create;

import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.User;
import user.Utils;

public class PostUsersAsListsHappyPathTest {
    private static Response response;
    private static User[] users;

    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String PATH = "/user/createWithList";

    @BeforeAll
    public static void beforeAll() {
        JSONObject user1 = new JSONObject();
        user1.put("id", 10);
        user1.put("username", "theUser");
        user1.put("firstName", "John");
        user1.put("lastName", "James");
        user1.put("email", "john@email.com");
        user1.put("password", "12345");
        user1.put("phone", "12345");
        user1.put("userStatus", 1);

        JSONObject user2 = new JSONObject();
        user2.put("id", 11);
        user2.put("username", "theUser123");
        user2.put("firstName", "John");
        user2.put("lastName", "James");
        user2.put("email", "john@email.com");
        user2.put("password", "12345678");
        user2.put("phone", "12345678");
        user2.put("userStatus", 1);

        JSONObject[] requestBody = {user1, user2};

        RequestSpecification requestSpec = Utils.postRequestSpecForUserList(BASE_URI, PATH, requestBody);

        response = RestAssured.given(requestSpec)
                .when()
                .post()
                .thenReturn();

        users = response.as(User[].class);
    }

    @Test
    @DisplayName("Can create list of users with given input array")
    public void validateResponseBody() {
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));
    }

    @Test
    @DisplayName("Response returns 2 users")
    public void validateCorrectNumberOfUsersCreated() {
        MatcherAssert.assertThat(users.length, Matchers.is(2));
    }

    @Test
    @DisplayName("Response returns correct user1")
    public void validateCorrectUser1() {
        MatcherAssert.assertThat(users[0].getId(), Matchers.is(10));
        MatcherAssert.assertThat(users[0].getUsername(), Matchers.is("theUser"));
        MatcherAssert.assertThat(users[0].getFirstName(), Matchers.is("John"));
        MatcherAssert.assertThat(users[0].getLastName(), Matchers.is("James"));
        MatcherAssert.assertThat(users[0].getEmail(), Matchers.is("john@email.com"));
        MatcherAssert.assertThat(users[0].getPhone(), Matchers.is("12345"));
        MatcherAssert.assertThat(users[0].getUserStatus(), Matchers.is(1));
    }

    @Test
    @DisplayName("Response returns correct user2")
    public void validateCorrectUser2() {
        MatcherAssert.assertThat(users[1].getId(), Matchers.is(11));
        MatcherAssert.assertThat(users[1].getUsername(), Matchers.is("theUser123"));
        MatcherAssert.assertThat(users[1].getFirstName(), Matchers.is("John"));
        MatcherAssert.assertThat(users[1].getLastName(), Matchers.is("James"));
        MatcherAssert.assertThat(users[1].getEmail(), Matchers.is("john@email.com"));
        MatcherAssert.assertThat(users[1].getPhone(), Matchers.is("12345678"));
        MatcherAssert.assertThat(users[1].getUserStatus(), Matchers.is(1));
    }
}
