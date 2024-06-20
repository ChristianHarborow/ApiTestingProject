package user.get;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.User;
import user.UserUtils;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class GetUserHappyPathTest {

    private static Response response;
    private static User user;

    @BeforeAll
    public static void beforeAll(){
        response = UserUtils.getUser("user1");

        user = response.as(User.class);
    }

    @Test
    @DisplayName("correct response code when requesting data for an existing user")
    void correctResponseCode_requestDataForExistingUser(){
        assertThat(response.statusCode(), is(200));
    }

    @Test
    @DisplayName("Correct data for user user1")
    void correctData_requestDataForExistingUser(){
        assertThat(user.getId(), is(1));
        assertThat(user.getUsername(), is("user1"));
        assertThat(user.getFirstName(), is("first name 1"));
        assertThat(user.getLastName(), is("last name 1"));
        assertThat(user.getEmail(), is("email1@test.com"));
        assertThat(user.getPhone(), is("123-456-7890"));
        assertThat(user.getUserStatus(), is(1));
    }
}
