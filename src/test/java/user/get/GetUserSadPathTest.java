package user.get;

import config.TestConfig;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.config.XmlPathConfig;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pojos.User;
import user.UserUtils;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class GetUserSadPathTest {
    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String KEY = TestConfig.getAPIKey();

    private static Response response;
    private static User user;

    private static UserUtils userUtils = new UserUtils();
    private static UserUtils userUtilsMock = Mockito.mock(UserUtils.class);

    @BeforeAll
    public static void beforeAll(){
        Mockito.when(userUtilsMock.getUser("notreal")).thenReturn(new Response() {
            @Override
            public String print() {
                return "";
            }

            @Override
            public String prettyPrint() {
                return "";
            }

            @Override
            public Response peek() {
                return null;
            }

            @Override
            public Response prettyPeek() {
                return null;
            }

            @Override
            public <T> T as(Class<T> aClass) {
                return null;
            }

            @Override
            public <T> T as(Class<T> aClass, ObjectMapperType objectMapperType) {
                return null;
            }

            @Override
            public <T> T as(Class<T> aClass, ObjectMapper objectMapper) {
                return null;
            }

            @Override
            public <T> T as(TypeRef<T> typeRef) {
                return null;
            }

            @Override
            public <T> T as(Type type) {
                return null;
            }

            @Override
            public <T> T as(Type type, ObjectMapperType objectMapperType) {
                return null;
            }

            @Override
            public <T> T as(Type type, ObjectMapper objectMapper) {
                return null;
            }

            @Override
            public JsonPath jsonPath() {
                return null;
            }

            @Override
            public JsonPath jsonPath(JsonPathConfig jsonPathConfig) {
                return null;
            }

            @Override
            public XmlPath xmlPath() {
                return null;
            }

            @Override
            public XmlPath xmlPath(XmlPathConfig xmlPathConfig) {
                return null;
            }

            @Override
            public XmlPath xmlPath(XmlPath.CompatibilityMode compatibilityMode) {
                return null;
            }

            @Override
            public XmlPath htmlPath() {
                return null;
            }

            @Override
            public <T> T path(String s, String... strings) {
                return null;
            }

            @Override
            public String asString() {
                return "";
            }

            @Override
            public String asPrettyString() {
                return "";
            }

            @Override
            public byte[] asByteArray() {
                return new byte[0];
            }

            @Override
            public InputStream asInputStream() {
                return null;
            }

            @Override
            public Response andReturn() {
                return null;
            }

            @Override
            public Response thenReturn() {
                return null;
            }

            @Override
            public ResponseBody body() {
                return null;
            }

            @Override
            public ResponseBody getBody() {
                return null;
            }

            @Override
            public Headers headers() {
                return null;
            }

            @Override
            public Headers getHeaders() {
                return null;
            }

            @Override
            public String header(String s) {
                return "";
            }

            @Override
            public String getHeader(String s) {
                return "";
            }

            @Override
            public Map<String, String> cookies() {
                return Map.of();
            }

            @Override
            public Cookies detailedCookies() {
                return null;
            }

            @Override
            public Map<String, String> getCookies() {
                return Map.of();
            }

            @Override
            public Cookies getDetailedCookies() {
                return null;
            }

            @Override
            public String cookie(String s) {
                return "";
            }

            @Override
            public String getCookie(String s) {
                return "";
            }

            @Override
            public Cookie detailedCookie(String s) {
                return null;
            }

            @Override
            public Cookie getDetailedCookie(String s) {
                return null;
            }

            @Override
            public String contentType() {
                return "";
            }

            @Override
            public String getContentType() {
                return "";
            }

            @Override
            public String statusLine() {
                return "";
            }

            @Override
            public String getStatusLine() {
                return "";
            }

            @Override
            public String sessionId() {
                return "";
            }

            @Override
            public String getSessionId() {
                return "";
            }

            @Override
            public int statusCode() {
                return 404;
            }

            @Override
            public int getStatusCode() {
                return 0;
            }

            @Override
            public long time() {
                return 0;
            }

            @Override
            public long timeIn(TimeUnit timeUnit) {
                return 0;
            }

            @Override
            public long getTime() {
                return 0;
            }

            @Override
            public long getTimeIn(TimeUnit timeUnit) {
                return 0;
            }

            @Override
            public ValidatableResponse then() {
                return null;
            }
        });
        response = userUtils.getUser("notreal");
    }

    @Test
    @DisplayName("requesting info for non-existent user returns response with status code 404")
    void requestingNonExistentUser_returns404Status(){
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(404));
    }
}
