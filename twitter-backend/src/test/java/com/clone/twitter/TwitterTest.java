package com.clone.twitter;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class TwitterTest {

    @Test
    public void testTweet() {
        given().param("post", "hello world")
                .param("id", 1)
                .when()
                .post("api/tweet")
                .then()
                .statusCode(200)
                .body("postContent", equalTo("hello world"));
    }


    @Test
    public void testGetAllTweets() {
        given().when().get("/api/get_tweets/1").then().
                statusCode(200);
    }

    @Test
    public void testFailGetAllTweets() {
        given().when().get("/api/get_tweets/2").then().
                statusCode(400);
    }




}
