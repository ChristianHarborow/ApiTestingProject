import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.User;

import java.util.Map;

public class GetUserHappyPathTest {
    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String KEY = TestConfig.getAPIKey();

    private static Response response;
    private static User user;

    @BeforeAll
    public static void beforeAll(){
        response = RestAssured
                .given()
                    .baseUri(BASE_URI)
                    .headers(Map.of(
                            "api_key",KEY
                    ))
                    .basePath("/user/{username}")
                    .pathParams(Map.of(
                            "username","user1"
                    ))
                .when()
                .get()
                .thenReturn();

        user = response.as(User.class);
    }

    @Test
    @DisplayName("correct response code when requesting data for an existing user")
    void correctResponseCode_requestDataForExistingUser(){
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(200));
    }

    @Test
    @DisplayName("Correct data for user user1")
    void correctData_requestDataForExistingUser(){
        MatcherAssert.assertThat(user.getId(), Matchers.is(1));
        MatcherAssert.assertThat(user.getUsername(), Matchers.is("user1"));
        MatcherAssert.assertThat(user.getFirstName(), Matchers.is("first name 1"));
        MatcherAssert.assertThat(user.getLastName(), Matchers.is("last name 1"));
        MatcherAssert.assertThat(user.getEmail(), Matchers.is("email1@test.com"));
        MatcherAssert.assertThat(user.getPhone(), Matchers.is("123-456-7890"));
        MatcherAssert.assertThat(user.getUserStatus(), Matchers.is(1));
    }
}
