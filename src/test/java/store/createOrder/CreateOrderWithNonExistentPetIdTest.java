package store.createOrder;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.Utils;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CreateOrderWithNonExistentPetIdTest {
    private static Response response;
    private static Map<String, Object> body = Map.of(
            "id", 50,
            "petId", 99,
            "quantity", 1,
            "shipDate", "2024-07-01T10:00:00.000+00:00",
            "status", "approved",
            "complete", true
    );

    @BeforeAll
    public static void beforeAll() {
        response = RestAssured
                .given(Utils.getPostOrderRequestSpec(body))
                .when()
                    .post()
                .thenReturn();
    }

    @AfterAll
    public static void afterAll() {
        RestAssured
            .given(Utils.getDeleteOrderRequestSpec("50"))
            .when()
                .delete()
            .thenReturn();
    }

    @Test
    @DisplayName("Check that posting a new order that uses a non existent pet id returns a 405 status code")
    void checkPostingNewOrderNonExistentPetId_Returns405Code() {
        assertThat(response.statusCode(), is(405));
    }

    @Test
    @DisplayName("Check that posting a new order that uses a non existent pet id returns the message \"Invalid input\"")
    void checkPostingNewOrderNonExistentPetId_ReturnsErrorMessage() {
        assertThat(response.body().asString(), is("Invalid input"));
    }
}
