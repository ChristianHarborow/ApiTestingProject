import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.User;

public class Scenario3_1_2 {

    private static Response response;
    private static User user;

    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String PATH = "/user";
    private static final String API_KEY = TestConfig.getAPIKey();

    @BeforeAll
    public static void beforeAll() {

        JSONArray requestBody = new JSONArray();
        JSONObject user1 = new JSONObject();
        user1.put("id", 10);
        user1.put("username", "theUser");
        user1.put("firstName", "John");
        user1.put("lastName", "James");
        user1.put("email", "john@email.com");
        user1.put("password", "12345");
        user1.put("phone", "12345");
        user1.put("userStatus", 1);

        requestBody.add(user1);

        RequestSpecification requestSpec = Utils.postRequestSpecForUserSadPath(BASE_URI, PATH, API_KEY, requestBody);

        response = RestAssured.given(requestSpec)
                .when()
                .post()
                .thenReturn();
    }

    @Test
    @DisplayName("User can create a new account")
    public void validateResponseBody() {
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(400));
    }
}
