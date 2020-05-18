package edu.iis.mto.blog.rest.test;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserTest extends FunctionalTests {

    private static final String USER_API = "/blog/user";

    @Test
    public void createUserWithProperDataReturnsCreatedStatus() {
        JSONObject jsonObj = new JSONObject().put("email", "tracy1@domain.com");
        RestAssured.given()
                   .accept(ContentType.JSON)
                   .header("Content-Type", "application/json;charset=UTF-8")
                   .body(jsonObj.toString())
                   .expect()
                   .log()
                   .all()
                   .statusCode(HttpStatus.SC_CREATED)
                   .when()
                   .post(USER_API);
    }
}
