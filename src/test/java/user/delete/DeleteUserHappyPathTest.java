package user.delete;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.UserUtils;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class DeleteUserHappyPathTest {

    private static Response response;

    @BeforeAll
    public static void beforeAll() {
        response = UserUtils.deleteUser("user1");
    }

    @Test
    @DisplayName("Status code is 200 when user is deleted")
    public void validStatusCode() {
        assertThat(response.getStatusCode(), is(200));
    }
}
