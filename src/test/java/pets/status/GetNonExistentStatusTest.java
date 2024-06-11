package pets.status;

import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetNonExistentStatusTest {

    private static Response response;

    @BeforeAll
    public static void setup() {

        response = RestAssured
                .given()
                .baseUri(TestConfig.getBaseUri())
                .header("x-api-key", TestConfig.getAPIKey())
                .queryParam("status", "ended")
                .when()
                .get("/pet/findByStatus")
                .then()
                .extract()
                .response();
    }

    @Test
    public void testGetPetsByStatus() {
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(400));


    }



}
