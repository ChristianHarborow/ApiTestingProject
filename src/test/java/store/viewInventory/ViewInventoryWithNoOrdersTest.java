package store.viewInventory;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.Utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ViewInventoryWithNoOrdersTest {
    private static Response response;

    @BeforeAll
    public static void beforeAll() {
        assertThat(Utils.deleteDefaultOrders(), is(true));

        response = Utils.getInventory();
    }

    @AfterAll
    public static void afterAll() {
        assertThat(Utils.restoreDefaultOrders(), is(true));
    }

    @Test
    @DisplayName("Check that sending a get inventory request when there are no orders present returns a 200 status code")
    void checkGetInventoryWithNoOrders_Returns200Code() {
        assertThat(response.statusCode(), is(200));
    }

    @Test
    @DisplayName("Check that sending a get inventory request when there are no orders present returns empty json body")
    void checkGetInventoryWithNoOrders_ReturnsNoOrdersFound() {
        assertThat(response.body().asString(), is("{}"));
    }
}
