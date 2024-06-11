package pets.updateinfo;

import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateInformationForValidPetTest {
    private static Response response;

    @BeforeAll
    public static void beforeAll(){
        Map<String, Object> petJson = Map.of(
                "id", 5,
                "name", "UpdatedPetName",
                "category", Map.of(
                        "id", 5,
                        "name", "Dogs"
                ),
                "photoUrls", new String[]{"string"},
                "tags", new Map[]{
                        Map.of(
                                "id", 0,
                                "name", "string"
                        )
                },
                "status", "available"
        );


        response = RestAssured
                .given()
                .baseUri(TestConfig.getBaseUri())
                .header("api_key", TestConfig.getAPIKey())
                .header("Content-Type", "application/json")
                .body(petJson)
                .when()
                .put("/pet")
                .then()
                .extract()
                .response();
    }

    @Test
    public void updatePetDetails() {
            MatcherAssert.assertThat(response.getStatusCode(), equalTo(200));
            MatcherAssert.assertThat(response.jsonPath().getInt("id"), equalTo(5));
            MatcherAssert.assertThat(response.jsonPath().getString("name"), equalTo("UpdatedPetName"));
            MatcherAssert.assertThat(response.jsonPath().getString("category.name"), equalTo("Dogs"));
            MatcherAssert.assertThat(response.jsonPath().getString("status"), equalTo("available"));
        }
    }
