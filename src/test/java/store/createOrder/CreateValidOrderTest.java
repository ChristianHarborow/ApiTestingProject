package store.createOrder;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.Order;
import store.OrderBody;
import store.Utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CreateValidOrderTest {
    private static Response response;
    private static Order order;
    private static OrderBody body = new OrderBody(42, 1, 1, "2024-07-01T10:00:00.000+00:00");

    @BeforeAll
    public static void beforeAll() {
        response = Utils.postOrder(body);

        order = response.as(Order.class);
    }

    @AfterAll
    public static void afterAll() {
        response = Utils.deleteOrder("42");
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
        assertThat(order.getId(), is(body.id()));
        assertThat(order.getPetId(), is(body.petId()));
        assertThat(order.getQuantity(), is(body.quantity()));
        assertThat(order.getShipDate(), is(body.shipDate()));
        assertThat(order.getStatus(), is(body.status()));
        assertThat(order.isComplete(), is(body.complete()));
    }
}
