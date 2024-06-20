package user;

import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojos.User;

import java.util.Map;

public class UserUtils {
    private static final String POST_USER_PATH = "/user";
    private static final String SPECIFIC_USER_PATH = "/user/{username}";
    private static final String LOGIN_PATH = "/user/login";
    private static final String LOGOUT_PATH = "/user/logout";
    private static final String POST_USER_LIST_PATH = "/user/createWithList";

    private static RequestSpecBuilder getCommonBuilder(String path) {
        return new RequestSpecBuilder()
                .setBaseUri(TestConfig.getBaseUri())
                .setBasePath(path)
                .addHeaders(Map.of(
                        "api_key", TestConfig.getAPIKey(),
                        "Content-Type", "application/json"
                ));
    }

    public static RequestSpecification getPostUserRequestSpec(UserBody user) {
        return getCommonBuilder(POST_USER_PATH)
                .setBody(user)
                .build();
    }

    public static RequestSpecification getPostUserListRequestSpec(UserBody[] users) {
        return getCommonBuilder(POST_USER_LIST_PATH)
                .setBody(users)
                .build();
    }

    public static RequestSpecification getUpdateUserRequestSpec(String username, User user) {
        return getCommonBuilder(SPECIFIC_USER_PATH)
                .addPathParams(Map.of("username", username))
                .setBody(user)
                .build();
    }

    public static RequestSpecification getLoginRequestSpec(String username, String password) {
        return getCommonBuilder(LOGIN_PATH)
                .addQueryParam("username", username)
                .addQueryParam("password", password)
                .build();
    }

    public static RequestSpecification getSpecificUserRequestSpec(String username) {
        return getCommonBuilder(SPECIFIC_USER_PATH)
                .addPathParams(Map.of("username", username))
                .build();
    }

    public static RequestSpecification getLogoutRequestSpec() {
        return getCommonBuilder(LOGOUT_PATH).build();
    }

    public static Response postUser(UserBody body) {
        return RestAssured
                .given(getPostUserRequestSpec(body))
                .post();
    }

    public static Response postUserList(UserBody[] body) {
        return RestAssured
                .given(getPostUserListRequestSpec(body))
                .post();
    }

    public static Response getUser(String username) {
        return RestAssured
                .given(getSpecificUserRequestSpec(username))
                .get();
    }

    public static Response updateUser(String username, User user) {
        return RestAssured
                .given(getUpdateUserRequestSpec(username, user))
                .put();
    }

    public static Response deleteUser(String username) {
        return RestAssured
                .given(getSpecificUserRequestSpec(username))
                .delete();
    }

    public static Response loginUser(String username, String password) {
        return RestAssured
                .given(getLoginRequestSpec(username, password))
                .get();
    }

    public static Response logoutUser() {
        return RestAssured
                .given(getLogoutRequestSpec())
                .get();
    }
}
