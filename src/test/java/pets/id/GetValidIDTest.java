package pets.id;

import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class GetValidIDTest {

    private static Response response;

    @BeforeAll
    public static void beforeAll(){
        int petId = 3;
        response = RestAssured
                .given()
                .baseUri(TestConfig.getBaseUri())
                .header("x-api-key", TestConfig.getAPIKey())
                .pathParam("petId", petId)
                .when()
                .get("/pet/{petId}")
                .thenReturn();
    }

    @Test
    public void getPetsByInvalidID() {
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(200));
    }


}

