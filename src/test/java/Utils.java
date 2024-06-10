import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;

public class Utils {

    public static RequestSpecification postRequestSpecForUser(String baseUri, String path, String token, JSONObject commentBody) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addHeaders(Map.of(
                        "api_key", token
                ))
                .setContentType(ContentType.JSON)
                .setBody(commentBody)
                .build();
    }
}
