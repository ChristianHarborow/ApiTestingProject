package pets;

import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.Category;
import pojos.Pet;

import java.util.ArrayList;
import java.util.Map;

public class AddNewPetTest {

    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String KEY = TestConfig.getAPIKey();

    private static Response response;
    private static Pet pet;
    private static Category category;

    @BeforeAll
    public static void beforeAll(){
        pet = new Pet();
        category = new Category();
        category.setName("Monkeys");
        category.setId(44);

        pet.setId(5555);
        pet.setCategory(category);
        pet.setName("Spider Monkey");
        pet.setStatus("Available");
        pet.setPhotoUrls(new ArrayList<String>());

        JSONObject petObj = new JSONObject();
        petObj.put("id", 5555);
        petObj.put("name", "Spider Monkey");
        petObj.put("status", "Available");


        RequestSpecification reqSpec = new RequestSpecBuilder().setBody(petObj).build();

        response = RestAssured
                .given(reqSpec)
                .baseUri(BASE_URI)
                .headers(Map.of(
                        "api_key", KEY,
                        "Content-Type", "application/json"
                ))
                .basePath("/pet")
                .when()
                .post()
                .thenReturn();
        System.out.println(response);
        var result = response.as(JSONObject.class);
        System.out.println(result);
    }

    @Test
    @DisplayName("correct response code when requesting data for an existing user")
    void correctResponseCode_requestANewPet(){
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(200));
    }
}
