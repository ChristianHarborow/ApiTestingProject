package pets.delete;

import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteAValidPetFromSystemTest {
    private static Response response;
    private static int petId = 1;
    @BeforeAll
    public static void beforeAll(){

        response = RestAssured
                .given()
                .baseUri(TestConfig.getBaseUri())
                .header("api_key", TestConfig.getAPIKey())
                .pathParam("petId", petId)
                .when()
                .delete("/pet/{petId}")
                .then()
                .extract()
                .response();
    }
    @Test
    public void deleteExistingPetById() {
        MatcherAssert.assertThat(response.getStatusCode(), equalTo(200));

    }
}
