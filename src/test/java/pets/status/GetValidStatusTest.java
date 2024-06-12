package pets.status;

import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetValidStatusTest {

    private static Response response;

    @BeforeAll
    public static void setup() {
        
        response = RestAssured
                .given()
                .baseUri(TestConfig.getBaseUri())
                .header("x-api-key", TestConfig.getAPIKey())
                .queryParam("status", "pending")
                .when()
                .get("/pet/findByStatus")
                .then()
                .extract()
                .response();
    }

    @Test
    public void testGetPetsByStatus() {
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(200));
        response.then().body("status", everyItem(equalTo("pending")));

        List<Map<String, Object>> pets = response.jsonPath().getList("$");
        MatcherAssert.assertThat(pets.size(), greaterThan(0));
    }


}
