package store.deleteOrder;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.Utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DeleteOrderWithInvalidIdTest {
    private static Response response;
    private static final String ID = "nine";

    @BeforeAll
    public static void beforeAll() {
        response = RestAssured
                .given(Utils.getSpecificOrderRequestSpec(ID))
                .when()
                    .delete()
                .thenReturn();
    }

    @Test
    @DisplayName("Check that sending a delete request with an invalid id returns a 400 status code")
    void checkGetOrderWithInvalidId_Returns400Code() {
        assertThat(response.statusCode(), is(400));
    }

    @Test
    @DisplayName("Check that sending a delete request with an invalid id returns the message \"Invalid ID supplied\"")
    void checkGetOrderWithInvalidId_ReturnsInvalidIDSupplied() {
        assertThat(response.body().asString(), is("Invalid ID supplied"));
    }
}
