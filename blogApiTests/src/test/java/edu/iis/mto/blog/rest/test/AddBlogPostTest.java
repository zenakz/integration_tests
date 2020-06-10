package edu.iis.mto.blog.rest.test;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AddBlogPostTest extends FunctionalTests {
    private static final String CONFIRMED_USER_API = "/blog/user/1/post";
    private static final String NEW_USER_API = "/blog/user/2/post";


    @Test
    public void createPostAsConfirmedUserReturnsCreatedStatus() {
        JSONObject jsonObj = new JSONObject().put("entry", "test post");
        given().accept(ContentType.JSON)
                .header("Content-Type", "application/json;charset=UTF-8")
                .body(jsonObj.toString())
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_CREATED)
                .when()
                .post(CONFIRMED_USER_API);

    }

    @Test
    public void createPostAsNewUserReturnsBadRequest() {
        JSONObject jsonObj = new JSONObject().put("entry", "test post");
        given().accept(ContentType.JSON)
                .header("Content-Type", "application/json;charset=UTF-8")
                .body(jsonObj.toString())
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .when()
                .post(NEW_USER_API);

    }
}
