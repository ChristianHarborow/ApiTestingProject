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

public class CreateOrderWithNonExistentPetIdTest {
    private static Response response;
    private static OrderBody body = new OrderBody(50, 99, 1, "2024-07-01T10:00:00.000+00:00");

    @BeforeAll
    public static void beforeAll() {
        response = Utils.postOrder(body);
    }

    @AfterAll
    public static void afterAll() {
        response = Utils.deleteOrder("50");
        assertThat(response.statusCode(), is(200));
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
