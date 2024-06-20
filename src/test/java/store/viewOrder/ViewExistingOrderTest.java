package store.viewOrder;

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

public class ViewExistingOrderTest {
    private static Response response;
    private static Order order;
    private static final String ID = "40";

    private static final OrderBody EXISTING_ORDER_BODY = new OrderBody(
            40, 4, 23, "2024-07-01T10:00:00.000+00:00");

    @BeforeAll
    public static void beforeAll() {
        response = Utils.postOrder(EXISTING_ORDER_BODY);
        assertThat(response.statusCode(), is(200));

        response = Utils.getOrder(ID);

        order = response.as(Order.class);
    }

    @AfterAll
    public static void afterAll() {
        response = Utils.deleteOrder(ID);
        assertThat(response.statusCode(), is(200));
    }

    @Test
    @DisplayName("Check that sending a get request with an existing id returns a 200 status code")
    void checkGetOrderWithExistingId_Returns200Code() {
        assertThat(response.statusCode(), is(200));
    }

    @Test
    @DisplayName("Check that sending a get request with an existing id returns the correct order object")
    void checkGetOrderWithExistingId_ReturnsTheCorrectOrderObject() {
        assertThat(order.getId(), is(EXISTING_ORDER_BODY.id()));
        assertThat(order.getPetId(), is(EXISTING_ORDER_BODY.petId()));
        assertThat(order.getQuantity(), is(EXISTING_ORDER_BODY.quantity()));
        assertThat(order.getShipDate(), is(EXISTING_ORDER_BODY.shipDate())); //
        assertThat(order.getStatus(), is(EXISTING_ORDER_BODY.status()));
        assertThat(order.isComplete(), is(EXISTING_ORDER_BODY.complete()));
    }
}
