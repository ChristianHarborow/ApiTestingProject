package user.create;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.UserBody;
import user.UserUtils;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class PostUsersAsListSadPathTests {
    private static Response response;

    @BeforeAll
    public static void beforeAll() {
        UserBody[] requestBody = {};
       response = UserUtils.postUserList(requestBody);
    }

    @Test
    @DisplayName("Post an empty list returns 400 bad request")
    public void validStatusCode() {
        assertThat(response.getStatusCode(), is(400));
    }

    @Test
    @DisplayName("Post an empty list returns error message")
    public void errorMessageWhenEmptyBodyPosted() {
        assertThat(response.asString(), is("No User provided. Try again?"));
    }
}
