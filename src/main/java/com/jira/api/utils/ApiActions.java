package com.jira.api.utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * This class contains the methods of HTTP request.
 */
public class ApiActions {

    /**
     * This method contains POST HTTP request
     *
     * @param headerKey   contains header Key
     * @param headerValue contains header value
     * @param contentType contains the type of the file
     * @param endpoints   contains the endpoint
     * @param requestBody contains the request body
     */
    public Response post(String headerKey,
                         String headerValue, String contentType, Object requestBody, String endpoints) {
        return given()
                .header(headerKey, headerValue)
                .contentType(contentType)
                .body(requestBody)
                .when()
                .post(endpoints)
                .then()
                .log().body()
                .extract()
                .response();
    }

    /**
     * This method contains POST HTTP request
     *
     * @param headerKey   contains header Key
     * @param headerValue contains header value
     * @param contentType contains the type of the file
     * @param endpoints   contains the endpoint
     * @param requestBody contains the request body
     */
    public Response post(String headerKey,
                         String headerValue, String cookieKey, String cookieValue, String contentType, Object requestBody, String endpoints) {
        return given()
                .header(headerKey, headerValue)
                .header(cookieKey, cookieValue)
                .contentType(contentType)
                .body(requestBody)
                .when()
                .post(endpoints)
                .then()
                .log().body()
                .extract()
                .response();
    }

    public Response addComment(String issueIdKey, String issueIdValue, String headerKey,
                               String headerValue, String cookieKey, String cookieValue,
                               Object requestBody, String endpoints) {
        return given()
                .pathParam(issueIdKey, issueIdValue)
                .header(headerKey, headerValue)
                .header(cookieKey, cookieValue)
                .body(requestBody)
                .when()
                .post(endpoints)
                .then()
                .log().body()
                .extract()
                .response();
    }


    /**
     * This method contains POST HTTP request without body.
     *
     * @param headerKey   contains header Key
     * @param headerValue contains header value
     * @param contentType contains the type of the file
     * @param endpoints   contains the endpoint
     */
    public Response postWithoutBody(String headerKey, String headerValue, String contentType, String endpoints) {
        return given()
                .header(headerKey, headerValue)
                .contentType(contentType)
                .when()
                .post(endpoints)
                .then()
                .log().body()
                .extract().response();
    }

    /**
     * This method contains GET HTTP request
     *
     * @param categoryKey   contains query parameter key
     * @param categoryValue contains query parameter value
     * @param endpoint      contains the endpoint
     */
    //GET
    public Response getItemsInStoreByCategory(String categoryKey, String categoryValue, String endpoint) {
        return given()
                .queryParam(categoryKey, categoryValue)
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .extract()
                .response();
    }

    /**
     * This method contains GET HTTP request
     *
     * @param endpoint contains the endpoint
     */
    public Response get(String endpoint) {
        return given()
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .extract()
                .response();
    }

    /**
     * This method contains GET HTTP request
     *
     * @param cartId      contains path parameter key
     * @param cartIdValue contains path parameter value
     * @param endpoint    contains the endpoint
     */
    public Response getCartItems(String cartId, String cartIdValue, String endpoint) {
        return given()
                .pathParam(cartId, cartIdValue)
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .extract()
                .response();
    }

    /**
     * This method contains POST HTTP request
     *
     * @param cartId           contains path parameter key
     * @param cartIdValue      contains path parameter value
     * @param itemIdKey        contains path parameter key
     * @param itemIdValue      contains path parameter value
     * @param contentTypeKey   contains header Key
     * @param contentTypeValue contains header value
     * @param endpoint         contains the endpoint
     * @param body             contains the request body
     */
    public Response patch(String contentTypeKey, String contentTypeValue, String cartId, String cartIdValue, String itemIdKey, int itemIdValue, Object body, String endpoint) {
        return given()
                .header(contentTypeKey, contentTypeValue)
                .pathParam(cartId, cartIdValue)
                .pathParam(itemIdKey, itemIdValue)
                .body(body)
                .when()
                .patch(endpoint)
                .then()
                .log().body()
                .extract()
                .response();
    }
}
