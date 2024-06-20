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

public class CreateOrderWithQuantityLessThanOneTest {
    private static Response response;
    private static OrderBody body = new OrderBody(60, 1, 0, "2024-07-01T10:00:00.000+00:00");

    @BeforeAll
    public static void beforeAll() {
        response = Utils.postOrder(body);
    }

    @AfterAll
    public static void afterAll() {
        response = Utils.deleteOrder("60");
        assertThat(response.statusCode(), is(200));
    }

    @Test
    @DisplayName("Check that posting a new order with a quantity of < 1 returns a 405 status code")
    void checkPostingNewOrderWithQuantityLessThanOne_Returns400Code() {
        assertThat(response.statusCode(), is(405));
    }

    @Test
    @DisplayName("Check that posting a new order with a quantity of < 1 returns the message \"Invalid input\"")
    void checkPostingNewOrderWithQuantityLessThanOne_ReturnsErrorMessage() {
        assertThat(response.body().asString(), is("Invalid input"));
    }
}
