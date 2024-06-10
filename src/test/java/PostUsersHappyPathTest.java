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

public class PostUsersHappyPathTest {

    private static Response response;
    private static JSONArray responseBody;

    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String PATH = "/user";
    private static final String API_KEY = TestConfig.getAPIKey();


    @BeforeAll
    public static void beforeAll() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("id", 12345); // Example ID
        requestBody.put("username", "newUser");
        requestBody.put("firstName", "John");
        requestBody.put("lastName", "Doe");
        requestBody.put("email", "john.doe@example.com");
        requestBody.put("password", "password123");
        requestBody.put("phone", "123-456-7890");
        requestBody.put("userStatus", 1);

        RequestSpecification requestSpec = Utils.postRequestSpecForUser(BASE_URI, PATH, API_KEY, requestBody);

        response = RestAssured.given(requestSpec)
                .when()
                .post()
                .thenReturn();
    }

    @Test
    @DisplayName("User can create a new account")
    public void validateResponseBody() {
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));
    }
}
