package user.get;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.UserUtils;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class GetUserWithEmptyRequestTest {

    private static Response response;

    @BeforeAll
    public static void beforeAll() {
        response = UserUtils.getUser("");
    }

    @Test
    @DisplayName("requesting info for non-existent user returns response with status code 400")
    void requestingNonExistentUser_returns400Status() {
        assertThat(response.statusCode(), is(400));
    }
}