package store.createOrder;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.Order;
import store.Utils;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CreateValidOrderTest {
    private static Response response;
    private static Order order;
    private static Map<String, Object> body = Map.of(
            "id", 42,
            "petId", 1,
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

        order = response.as(Order.class);
    }

    @AfterAll
    public static void afterAll() {
        response = RestAssured
                .given(Utils.getSpecificOrderRequestSpec("42"))
                .when()
                    .delete()
                .thenReturn();
        assertThat(response.statusCode(), is(200));
    }

    @Test
    @DisplayName("Check that posting a new valid order returns a 200 status code")
    void checkPostingNewOrder_Returns200Code() {
        assertThat(response.statusCode(), is(200));
    }

    @Test
    @DisplayName("Check that posting a new order returns a order object containing values equal to that of the request body")
    void checkThatPostingNewOrder_ReturnsAnEqualOrderObject() {
        assertThat(order.getId(), is(body.get("id")));
        assertThat(order.getPetId(), is(body.get("petId")));
        assertThat(order.getQuantity(), is(body.get("quantity")));
        assertThat(order.getShipDate(), is(body.get("shipDate")));
        assertThat(order.getStatus(), is(body.get("status")));
        assertThat(order.isComplete(), is(body.get("complete")));
    }
}
