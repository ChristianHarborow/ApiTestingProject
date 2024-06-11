package store.viewOrder;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.Order;
import store.Utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ViewExistingOrderTest {
    private static Response response;
    private static Order order;
    private static final String ID = "1";

    @BeforeAll
    public static void beforeAll() {
        response = RestAssured
                .given(Utils.getSpecificOrderRequestSpec(ID))
                .when()
                    .get()
                .thenReturn();

        order = response.as(Order.class);
    }

    @Test
    @DisplayName("Check that sending a get request with an existing id returns a 200 status code")
    void checkGetOrderWithExistingId_Returns200Code() {
        assertThat(response.statusCode(), is(200));
    }

    @Test
    @DisplayName("Check that sending a get request with an existing id returns the correct order object")
    void checkGetOrderWithExistingId_ReturnsTheCorrectOrderObject() {
        assertThat(order.getId(), is(1));
        assertThat(order.getPetId(), is(1));
        assertThat(order.getQuantity(), is(1));
        assertThat(order.getShipDate(), is("2024-07-01T10:00:00.000+00:00"));
        assertThat(order.getStatus(), is("approved"));
        assertThat(order.isComplete(), is(true));
    }
}
