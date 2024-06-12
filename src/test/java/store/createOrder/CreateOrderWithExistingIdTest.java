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

public class CreateOrderWithExistingIdTest {
    private static Response response;
    private static final Map<String, Object> NEW_ORDER_BODY = Map.of(
            "id", 1,
            "petId", 1,
            "quantity", 1,
            "shipDate", "2024-07-01T10:00:00.000+00:00",
            "status", "approved",
            "complete", true
    );

    @BeforeAll
    public static void beforeAll() {
        response = RestAssured
                .given(Utils.getPostOrderRequestSpec(NEW_ORDER_BODY))
                .when()
                    .post()
                .thenReturn();
    }

    @AfterAll
    public static void afterAll() {
        response = RestAssured
                .given(Utils.getSpecificOrderRequestSpec("1"))
                .when()
                .delete()
                .thenReturn();
        assertThat(response.statusCode(), is(200));

        Map<String, Object> body = Map.of(
                "id", 1,
                "petId", 1,
                "quantity", 100,
                "shipDate", "2024-06-11T14:52:47.694+00:00",
                "status", "placed",
                "complete", true
        );

        response = RestAssured
                .given(Utils.getPostOrderRequestSpec(body))
                .when()
                .post()
                .thenReturn();

        assertThat(response.statusCode(), is(200));
    }

    @Test
    @DisplayName("Check that posting a new order that uses an existing order's id returns a 405 status code")
    void checkPostingNewOrderWithExistingId_Returns405Code() {
        assertThat(response.statusCode(), is(405));
    }

    @Test
    @DisplayName("Check that posting a new order that uses an existing order's id returns the message \"Invalid input\"")
    void checkPostingNewOrderWithExistingId_ReturnsErrorMessage() {
        assertThat(response.body().asString(), is("Invalid input"));
    }
}
