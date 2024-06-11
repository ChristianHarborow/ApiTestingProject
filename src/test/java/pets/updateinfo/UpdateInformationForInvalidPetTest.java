package pets.updateinfo;

import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateInformationForInvalidPetTest {
    private static Response response;


    @BeforeAll
    public static void beforeAll(){
        String petJson = "{\n" +
                "  \"id\": 123,\n" +
                "  \"name\": \"UpdatedPetName\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Dogs\"\n" +
                "  },\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";
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

        MatcherAssert.assertThat(response.getStatusCode(), equalTo(404));


    }
}
