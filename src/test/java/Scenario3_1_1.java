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

public class Scenario3_1_1 {

    private static Response response;
    private static User user;

    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String PATH = "/user";
    private static final String API_KEY = TestConfig.getAPIKey();


    @BeforeAll
    public static void beforeAll() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("id", 10);
        requestBody.put("username", "theUser");
        requestBody.put("firstName", "John");
        requestBody.put("lastName", "James");
        requestBody.put("email", "john@email.com");
        requestBody.put("password", "12345");
        requestBody.put("phone", "12345");
        requestBody.put("userStatus", 1);

        RequestSpecification requestSpec = Utils.postRequestSpecForUser(BASE_URI, PATH, API_KEY, requestBody);

        response = RestAssured.given(requestSpec)
                .when()
                .post()
                .thenReturn();

        user = response.as(User.class);
    }

    @Test
    @DisplayName("User can create a new account")
    public void validateResponseBody() {
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));
    }

    @Test
    @DisplayName("Correct data for created user")
    void correctData_requestDataForExistingUser(){
        MatcherAssert.assertThat(user.getId(), Matchers.is(10));
        MatcherAssert.assertThat(user.getUsername(), Matchers.is("theUser"));
        MatcherAssert.assertThat(user.getFirstName(), Matchers.is("John"));
        MatcherAssert.assertThat(user.getLastName(), Matchers.is("James"));
        MatcherAssert.assertThat(user.getEmail(), Matchers.is("john@email.com"));
        MatcherAssert.assertThat(user.getPhone(), Matchers.is("12345"));
        MatcherAssert.assertThat(user.getUserStatus(), Matchers.is(1));
    }
}
