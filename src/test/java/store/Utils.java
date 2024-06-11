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

    public static RequestSpecification getPostOrderRequestSpec(Map<String, Object> body) {
        return new RequestSpecBuilder()
                .setBaseUri(TestConfig.getBaseUri())
                .setBasePath(CREATE_ORDER)
                .addHeaders(HEADERS)
                .setBody(body)
                .build();
    }

    public static RequestSpecification getSpecificOrderRequestSpec(String id) {
        return new RequestSpecBuilder()
                .setBaseUri(TestConfig.getBaseUri())
                .setBasePath(SPECIFIC_ORDER)
                .addPathParams(Map.of("id", id))
                .addHeaders(HEADERS)
                .build();
    }

    public static RequestSpecification getInventoryRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(TestConfig.getBaseUri())
                .setBasePath(INVENTORY)
                .addHeaders(HEADERS)
                .build();
    }

    public static boolean deleteDefaultOrders() {
        boolean allDeleted = true;
        for (int i = 1; i <= 3; i++) {
            Response response = RestAssured
                    .given(Utils.getSpecificOrderRequestSpec("" + i))
                    .delete()
                    .thenReturn();
            allDeleted = allDeleted && response.statusCode() == 200;
        }
        return allDeleted;
    }

    public static boolean restoreDefaultOrders() {
        boolean allRestored = true;
        Date date = new Date();
        for (int i = 0; i < 3; i++) {
            Map<String, Object> body = Map.of(
                    "id", i+1,
                    "petId", 1,
                    "quantity", DEFAULT_QUANTITIES.get(i),
                    "shipDate", date,
                    "status", DEFAULT_STATUSES.get(i),
                    "complete", true
            );
            Response response = RestAssured
                    .given(Utils.getPostOrderRequestSpec(body))
                    .post()
                    .thenReturn();
            allRestored = allRestored && response.statusCode() == 200;
        }
        return allRestored;
    }
}
