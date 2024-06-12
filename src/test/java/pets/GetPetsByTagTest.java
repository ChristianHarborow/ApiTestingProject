package pets;

import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.Pet;

import java.util.List;
import java.util.Map;

public class GetPetsByTagTest {

    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String KEY = TestConfig.getAPIKey();

    private static Response response;
    private static Pet pet;

    @BeforeAll
    public static void beforeAll(){
        response = RestAssured
                .given()
                .baseUri(BASE_URI)
                .headers(Map.of(
                        "api_key",KEY
                ))
                .basePath("/pet/findByTags") //?tags=tag1&tags=tag2
                //.queryParam("tags", new String[]{"tag1, tag2"})
                .when()
                .get("?tags=tag1&tags=tag2")
                .thenReturn();
        System.out.println(response.getBody().toString());
        Pet[] result = response.as(Pet[].class);
        System.out.println(result.toString());
        pet = result[0];
    }

    @Test
    @DisplayName("correct response code when searching by tags")
    void correctResponseCode_requestDataForPetsByTag(){
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(200));
    }

    @Test
    @DisplayName("Correct data for incorrect pet Lion 2")
    void incorrectData_requestDataForExistingUser(){
        System.out.println(pet.toString());
        MatcherAssert.assertThat(pet.getId(), Matchers.is(8));
        MatcherAssert.assertThat(pet.getName(), Matchers.is("Lion 2"));
        MatcherAssert.assertThat(pet.getTags().get(0).getName(), Matchers.is("tag2"));
        MatcherAssert.assertThat(pet.getCategory().getName(), Matchers.is("Lions"));
        MatcherAssert.assertThat(pet.getStatus(), Matchers.is("available"));
        MatcherAssert.assertThat(pet.getPhotoUrls(), Matchers.is(List.of("url1", "url2")));
    }

    @Test
    @DisplayName("Correct data for pet Dog 1")
    void correctData_requestDataForExistingUser(){
        System.out.println(pet.toString());
        MatcherAssert.assertThat(pet.getId(), Matchers.is(4));
        MatcherAssert.assertThat(pet.getName(), Matchers.is("Dog 1"));
        MatcherAssert.assertThat(pet.getTags(), Matchers.is(List.of("tag1", "tag2")));
        MatcherAssert.assertThat(pet.getCategory(), Matchers.is("Dogs"));
        MatcherAssert.assertThat(pet.getStatus(), Matchers.is("available"));
        MatcherAssert.assertThat(pet.getPhotoUrls(), Matchers.is(List.of("url1", "url2")));
    }
}
