import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.User;

import java.util.Map;

public class GetUserSadPathTest {
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
                        "username","notreal"
                ))
                .when()
                .get()
                .thenReturn();
    }

    @Test
    @DisplayName("requesting info for non-existent user returns response with status code 404")
    void requestingNonExistentUser_returns404Status(){
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(404));
    }
}
