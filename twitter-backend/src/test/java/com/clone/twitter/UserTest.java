package com.clone.twitter;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserTest {

    @Test
    public void testUserLogin() {
        given().param("email", "bin@gmail.com")
                .param("password", "12345")
                .when()
                .post("/api/login")
                .then()
                .statusCode(200)
                .body("email", equalTo("bin@gmail.com"));
    }


    @Test
    public void testFailUserLogin() {
        given().param("email", "binz@gmail.com")
                .param("password", "12345")
                .when()
                .post("/api/login")
                .then()
                .statusCode(400);
    }

}
