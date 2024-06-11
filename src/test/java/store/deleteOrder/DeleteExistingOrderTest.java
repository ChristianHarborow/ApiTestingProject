package store.deleteOrder;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.Utils;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;

public class DeleteExistingOrderTest {
    private static Response response;
    private static final String ID = "1";

    @BeforeAll
    public static void beforeAll() {
        response = RestAssured
                .given(Utils.getSpecificOrderRequestSpec(ID))
                .when()
                    .delete()
                .thenReturn();
    }

    @AfterAll
    public static void afterAll() {
        Map<String, Object> body = Map.of(
                "id", 1,
                "petId", 1,
                "quantity", 100,
                "shipDate", "2024-07-01T10:00:00.000+00:00",
                "status", "placed",
                "complete", true
        );
        response = RestAssured
                .given(Utils.getPostOrderRequestSpec(body))
                .when()
                    .post()
                .thenReturn();
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
