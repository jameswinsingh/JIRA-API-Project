package com.jira.api.utils;

import io.restassured.http.ContentType;
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
                         String headerValue, String contentType,
                         Object requestBody, String endpoints) {
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
                         String headerValue, String cookieKey, String cookieValue,
                         String contentType, Object requestBody, String endpoints) {
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
     * This method contains GET HTTP request
     *
     * @param issueKey      contains path parameter key
     * @param issueKeyValue contains path parameter value
     * @param endpoint      contains the endpoint
     * @param cookieKey     contains key
     * @param cookieValue   contains token value.
     */
    public Response get(String issueKey, String issueKeyValue, String cookieKey, String cookieValue, String endpoint) {
        return given()
                .pathParam(issueKey, issueKeyValue)
                .header(cookieKey, cookieValue)
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .extract()
                .response();
    }

    /**
     * This method contain the get method to get comment API
     * @param issueKey
     * @param issueKeyValue
     * @param commentKey
     * @param commentKeyValue
     * @param cookieKey
     * @param cookieValue
     * @param endpoint
     * @return
     */
    public Response get(String issueKey, String issueKeyValue, String commentKey, int commentKeyValue,
                        String cookieKey, String cookieValue, String endpoint) {
        return given()
                .pathParam(issueKey, issueKeyValue)
                .pathParam(commentKey, commentKeyValue)
                .header(cookieKey, cookieValue)
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .extract()
                .response();
    }


    public Response put(String issueKey, String issueKeyValue, String commentKey, int commentKeyValue,
                        String cookieKey, String cookieValue, Object payload, String endpoint) {
        return given()
                .pathParam(issueKey, issueKeyValue)
                .pathParam(commentKey, commentKeyValue)
                .header(cookieKey, cookieValue)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put(endpoint)
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

    /**
     * This method contain the delete method to delete comment
     * @param issueKey
     * @param issueKeyValue
     * @param commentKey
     * @param commentKeyValue
     * @param cookieKey
     * @param cookieValue
     * @param endpoint
     * @return
     */
    public Response delete(String issueKey, String issueKeyValue, String commentKey, int commentKeyValue,
                        String cookieKey, String cookieValue, String endpoint) {
        return given()
                .pathParam(issueKey, issueKeyValue)
                .pathParam(commentKey, commentKeyValue)
                .header(cookieKey, cookieValue)
                .contentType(ContentType.JSON)
                .when()
                .delete(endpoint)
                .then()
                .log().body()
                .extract()
                .response();
    }

    /**
     * delete issue
     * @param issueKey
     * @param issueKeyValue
     * @param cookieKey
     * @param cookieValue
     * @param endpoint
     * @return
     */
    public Response delete(String issueKey, String issueKeyValue, String cookieKey, String cookieValue, String endpoint) {
        return given()
                .pathParam(issueKey, issueKeyValue)
                .header(cookieKey, cookieValue)
                .when()
                .delete(endpoint)
                .then()
                .log().body()
                .extract()
                .response();
    }
}
