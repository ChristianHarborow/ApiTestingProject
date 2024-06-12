package store.viewInventory;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.Order;
import store.Utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ViewInventoryWithExistingOrdersTest {
    private static Response response;

    @BeforeAll
    public static void beforeAll() {
        response = RestAssured
                .given(Utils.getInventoryRequestSpec())
                .when()
                    .get()
                .thenReturn();
    }

    private int getInventoryValueAsInt(String key) {
        return Integer.parseInt(response.jsonPath().getString(key));
    }

    @Test
    @DisplayName("Check that sending a get inventory request when there are orders present returns a 200 status code")
    void checkGetInventoryWithExistingOrders_Returns200Code() {
        assertThat(response.statusCode(), is(200));
    }

    @Test
    @DisplayName("Check that sending a get inventory request when there are orders present returns the correct order object")
    void checkGetInventoryWithExistingOrders_ReturnsCorrectInventoryValues() {
        System.out.println(response.body().asString());
        assertThat(getInventoryValueAsInt("approved"), is(50));
        assertThat(getInventoryValueAsInt("placed"), is(100));
        assertThat(getInventoryValueAsInt("delivered"), is(50));
    }
}
