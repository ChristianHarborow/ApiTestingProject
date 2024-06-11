package store.viewOrder;

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

public class ViewExistingOrderTest {
    private static Response response;
    private static Order order;
    private static final String ID = "40";

    private static final Map<String, Object> EXISTING_ORDER_BODY = Map.of(
            "id", 40,
            "petId", 4,
            "quantity", 23,
            "shipDate", "2024-07-01T10:00:00.000+00:00",
            "status", "approved",
            "complete", true
    );

    @BeforeAll
    public static void beforeAll() {
        response = RestAssured
                .given(Utils.getPostOrderRequestSpec(EXISTING_ORDER_BODY))
                .when()
                .post()
                .thenReturn();
        assertThat(response.statusCode(), is(200));

        response = RestAssured
                .given(Utils.getSpecificOrderRequestSpec(ID))
                .when()
                    .get()
                .thenReturn();

        order = response.as(Order.class);
    }

    @AfterAll
    public static void afterAll() {
        response = RestAssured
                .given(Utils.getSpecificOrderRequestSpec(ID))
                .when()
                .delete()
                .thenReturn();
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
        assertThat(order.getId(), is(40));
        assertThat(order.getPetId(), is(4));
        assertThat(order.getQuantity(), is(23));
        assertThat(order.getShipDate(), is("2024-07-01T10:00:00.000+00:00")); //
        assertThat(order.getStatus(), is("approved"));
        assertThat(order.isComplete(), is(true));
    }
}
