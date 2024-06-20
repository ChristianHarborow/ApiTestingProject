package store.deleteOrder;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.OrderBody;
import store.Utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;

public class DeleteExistingOrderTest {
    private static Response response;
    private static final String ID = "1";

    @BeforeAll
    public static void beforeAll() {
        response = Utils.deleteOrder(ID);
    }

    @AfterAll
    public static void afterAll() {
        OrderBody body = new OrderBody(1, 1, 100, "2024-07-01T10:00:00.000+00:00", "placed");
        response = Utils.postOrder(body);
        assertThat(response.statusCode(), is(200));
    }

    @Test
    @DisplayName("Check that sending a delete request with an existing id returns a 200 status code")
    void checkDeleteOrderWithExistingId_Returns200Code() {
        assertThat(response.statusCode(), is(200));
    }

    @Test
    @DisplayName("Check that sending a delete request with an existing id returns an empty body")
    void checkDeleteOrderWithExistingId_ReturnsEmptyBody() {
        assertThat(response.body().asString(), is(emptyString()));
    }
}
