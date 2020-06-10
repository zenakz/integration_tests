package edu.iis.mto.blog.rest.test;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class UserSearchTest {

    private static final String SEARCH_API = "blog/user/find?searchString=";


    @Test
    public void searchForConfirmedUserShouldEndInSuccess() {
        given().accept(ContentType.JSON)
                .header("Content-Type", "application/json;charset=UTF-8")
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .get(SEARCH_API + "John")
                .then()
                .body("size()", is(1));
    }

    @Test
    public void searchForRemovedUserShouldEndInSuccess() {
        given().accept(ContentType.JSON)
                .header("Content-Type", "application/json;charset=UTF-8")
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .get(SEARCH_API + "deleted@domain.com")
                .then()
                .body("size()", is(0));
    }


}
