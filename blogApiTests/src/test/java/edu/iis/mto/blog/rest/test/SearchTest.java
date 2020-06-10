package edu.iis.mto.blog.rest.test;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class SearchTest {

    private static final String CONFIRMED_USER_API = "blog/user/1/post";
    private static final String REMOVED_USER_API = "/blog/user/4/post";

    @Test
    public void searchByConfirmedUserShouldEndInSuccess() {
        given().accept(ContentType.JSON)
                .header("Content-Type", "application/json;charset=UTF-8")
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .get(CONFIRMED_USER_API)
                .then()
                .body("size()", is(1));
    }

    @Test
    public void searchByRemovedUserShouldFail() {
        given().accept(ContentType.JSON)
                .header("Content-Type", "application/json;charset=UTF-8")
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .when()
                .get(REMOVED_USER_API);
    }

    @Test
    public void searchResultShouldHaveProperLikesCount() {
        given().accept(ContentType.JSON)
                .header("Content-Type", "application/json;charset=UTF-8")
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .get(CONFIRMED_USER_API)
                .then()
                .body("get(0).likesCount", is(0));
    }
}
