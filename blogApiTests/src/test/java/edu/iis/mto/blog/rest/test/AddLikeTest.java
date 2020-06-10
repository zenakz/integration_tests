package edu.iis.mto.blog.rest.test;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class AddLikeTest {
    private static final String CONFIRMED_USER_API = "/blog/user/1/post";
    private static final String SAME_USER_LIKE_API = "/blog/user/1/like/1";
    private static final String CONFIRMED_LIKE_API = "/blog/user/3/like/1";
    private static final String NEW_LIKE_API = "/blog/user/2/like/1";
    private static final String POST_API = "blog/post/1";

    @Test
    public void addLikeAsConfirmedUserResultsInSuccess() {
        createPost();
        given().accept(ContentType.JSON)
                .header("Content-Type", "application/json;charset=UTF-8")
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .post(CONFIRMED_LIKE_API);
    }

    @Test
    public void addLikeAsPostCreatorResultsInBadRequest() {
        given().accept(ContentType.JSON)
                .header("Content-Type", "application/json;charset=UTF-8")
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .when()
                .post(SAME_USER_LIKE_API);
    }

    @Test
    public void addLikeAsNewUserResultsInBadRequest() {
        given().accept(ContentType.JSON)
                .header("Content-Type", "application/json;charset=UTF-8")
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .when()
                .post(NEW_LIKE_API);
    }

    @Test
    public void likingSamePostTwiceShouldNotChangeStatus() {
        given().accept(ContentType.JSON)
                .header("Content-Type", "application/json;charset=UTF-8")
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .post(CONFIRMED_LIKE_API);

        given().accept(ContentType.JSON)
                .header("Content-Type", "application/json;charset=UTF-8")
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .post(CONFIRMED_LIKE_API);

        given().accept(ContentType.JSON)
                .header("Content-Type", "application/json;charset=UTF-8")
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .get(POST_API)
                .then()
                .body("likesCount", is(1));
    }

    void createPost() {
        JSONObject jsonObj = new JSONObject().put("entry", "test post");
        given().accept(ContentType.JSON)
                .header("Content-Type", "application/json;charset=UTF-8")
                .body(jsonObj.toString())
                .when()
                .post(CONFIRMED_USER_API);
    }

}
