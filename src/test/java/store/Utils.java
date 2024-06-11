package store;

import config.TestConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class Utils {
    private static final String CREATE_ORDER = "/store/order";
    private static final String DELETE_ORDER = "/store/order/{id}";

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

    public static RequestSpecification getDeleteOrderRequestSpec(String id) {
        return new RequestSpecBuilder()
                .setBaseUri(TestConfig.getBaseUri())
                .setBasePath(DELETE_ORDER)
                .addPathParams(Map.of("id", id))
                .addHeaders(HEADERS)
                .build();
    }
}
