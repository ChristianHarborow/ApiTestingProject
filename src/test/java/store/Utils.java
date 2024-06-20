package store;

import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Utils {
    private static final String CREATE_ORDER = "/store/order";
    private static final String SPECIFIC_ORDER = "/store/order/{id}";
    private static final String INVENTORY = "/store/inventory";
    private static final List<Integer> DEFAULT_QUANTITIES = List.of(100, 50, 50);
    private static final List<String> DEFAULT_STATUSES = List.of("placed", "approved", "delivered");

    private static final Map<String, String> HEADERS = Map.of(
            "Content-Type", "application/json",
            "api_key", TestConfig.getAPIKey()
            );

    private static RequestSpecBuilder getCommonBuilder(String basePath) {
        return new RequestSpecBuilder()
                .setBaseUri(TestConfig.getBaseUri())
                .setBasePath(basePath)
                .addHeaders(HEADERS);
    }

    public static RequestSpecification getPostOrderRequestSpec(OrderBody body) {
        return getCommonBuilder(CREATE_ORDER)
                .setBody(body)
                .build();
    }

    public static RequestSpecification getSpecificOrderRequestSpec(String id) {
        return getCommonBuilder(SPECIFIC_ORDER)
                .addPathParams(Map.of("id", id))
                .build();
    }

    public static RequestSpecification getInventoryRequestSpec() {
        return getCommonBuilder(INVENTORY).build();
    }

    public static Response postOrder(OrderBody body) {
        return RestAssured
                .given(Utils.getPostOrderRequestSpec(body))
                .post()
                .thenReturn();
    }

    public static Response deleteOrder(String id) {
        return RestAssured
                .given(Utils.getSpecificOrderRequestSpec(id))
                .delete()
                .thenReturn();
    }

    public static Response getOrder(String id) {
        return RestAssured
                .given(Utils.getSpecificOrderRequestSpec(id))
                .get()
                .thenReturn();
    }

    public static Response getInventory() {
        return RestAssured
                .given(Utils.getInventoryRequestSpec())
                .get()
                .thenReturn();
    }

    public static boolean deleteDefaultOrders() {
        boolean allDeleted = true;
        for (int i = 1; i <= 3; i++) {
            Response response = deleteOrder("" + i);
            allDeleted = allDeleted && response.statusCode() == 200;
        }
        return allDeleted;
    }

    public static boolean restoreDefaultOrders() {
        boolean allRestored = true;
        Date date = new Date();
        for (int i = 0; i < 3; i++) {
            OrderBody body = new OrderBody(
                    i+1, 1, DEFAULT_QUANTITIES.get(i), date.toString(), DEFAULT_STATUSES.get(i));
            Response response = postOrder(body);
            allRestored = allRestored && response.statusCode() == 200;
        }
        return allRestored;
    }
}
