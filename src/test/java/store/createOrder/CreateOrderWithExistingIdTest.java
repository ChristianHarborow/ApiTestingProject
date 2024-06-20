package store.createOrder;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.OrderBody;
import store.Utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CreateOrderWithExistingIdTest {
    private static Response response;
    private static OrderBody NEW_ORDER_BODY = new OrderBody(
            1, 1, 1, "2024-07-01T10:00:00.000+00:00");

    @BeforeAll
    public static void beforeAll() {
        response = Utils.postOrder(NEW_ORDER_BODY);
    }

    @AfterAll
    public static void afterAll() {
        response = Utils.deleteOrder("1");
        assertThat(response.statusCode(), is(200));

        OrderBody body = new OrderBody(
                1, 1, 100, "2024-06-11T14:52:47.694+00:00", "placed");

        response = Utils.postOrder(body);

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
