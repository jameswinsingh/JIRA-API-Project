package com.jira.api.utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * This class contains the methods of HTTP request.
 */
public class ApiActions {

    RequestSpecificationUtil specSetup = new RequestSpecificationUtil();

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
                .spec(specSetup.requestSpecification())
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
                .spec(specSetup.requestSpecification())
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


    /**
     * @param issueIdKey   contains issue key
     * @param issueIdValue contains issue value
     * @param headerKey    contains content type key
     * @param headerValue  contains content type value
     * @param cookieKey    contains cookie key
     * @param cookieValue  contains token
     * @param requestBody  contains request payload.
     * @param endpoints    contains the endpoint.
     */
    public Response addComment(String issueIdKey, String issueIdValue, String headerKey,
                               String headerValue, String cookieKey, String cookieValue,
                               Object requestBody, String endpoints) {
        return given()
                .spec(specSetup.requestSpecification())
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
     * A method to create filter
     *
     * @param headerKey   contains content type key
     * @param headerValue contains content type value
     * @param cookieKey   contains comment header key
     * @param cookieValue contains token
     * @param endpoints   contains endpoint
     */
    public Response post(String cookieKey, String cookieValue,
                         String headerKey, String headerValue,
                         Object requestBody, String endpoints) {
        return given()
                .spec(specSetup.requestSpecification())
                .header(cookieKey, cookieValue)
                .header(headerKey, headerValue)
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
                .spec(specSetup.requestSpecification())
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
     *
     * @param issueKey        contains issue param key
     * @param issueKeyValue   contains issue key
     * @param commentKey      contains comment param key
     * @param commentKeyValue contains comment id
     * @param cookieKey       contains comment header key
     * @param cookieValue     contains token
     * @param endpoint        contains endpoint
     */
    public Response get(String issueKey, String issueKeyValue, String commentKey, int commentKeyValue,
                        String cookieKey, String cookieValue, String endpoint) {
        return given()
                .spec(specSetup.requestSpecification())
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

    /**
     * A method to getFilter
     *
     * @param cookieKey   contains comment header key
     * @param cookieValue contains token
     * @param endpoint    contains endpoint
     */
    public Response getFilter(String filterKey, int filterValue,
                              String cookieKey, String cookieValue, String endpoint) {
        return given()
                .spec(specSetup.requestSpecification())
                .pathParam(filterKey, filterValue)
                .header(cookieKey, cookieValue)
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .extract()
                .response();
    }

    /**
     * @param issueKey        contains issue param key
     * @param issueKeyValue   contains issue key
     * @param commentKey      contains comment param key
     * @param commentKeyValue contains comment id
     * @param cookieKey       contains comment header key
     * @param cookieValue     contains token
     * @param endpoint        contains endpoint
     */
    public Response put(String issueKey, String issueKeyValue, String commentKey, int commentKeyValue,
                        String cookieKey, String cookieValue, Object payload, String endpoint) {
        return given()
                .spec(specSetup.requestSpecification())
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
                .spec(specSetup.requestSpecification())
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
     *
     * @param issueKey        contains issue param key
     * @param issueKeyValue   contains issue key
     * @param commentKey      contains comment param key
     * @param commentKeyValue contains comment id
     * @param cookieKey       contains comment header key
     * @param cookieValue     contains token
     * @param endpoint        contains the endpoint
     */
    public Response delete(String issueKey, String issueKeyValue, String commentKey, int commentKeyValue,
                           String cookieKey, String cookieValue, String endpoint) {
        return given()
                .spec(specSetup.requestSpecification())
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
     *
     * @param issueKey      contains issue param key
     * @param issueKeyValue contains issue key
     * @param cookieKey     contains comment header key
     * @param cookieValue   contains token
     * @param endpoint      contains the endpoint
     */
    public Response delete(String issueKey, String issueKeyValue, String cookieKey, String cookieValue, String endpoint) {
        return given()
                .spec(specSetup.requestSpecification())
                .pathParam(issueKey, issueKeyValue)
                .header(cookieKey, cookieValue)
                .when()
                .delete(endpoint)
                .then()
                .log().body()
                .extract()
                .response();
    }


    /**
     * A method to delete filter
     *
     * @param filterIdKey   contains filter key
     * @param filterIdValue contains filter value
     * @param cookieKey     contains comment header key
     * @param cookieValue   contains token
     * @param endpoint      contains the endpoint
     */
    public Response deleteFilter(String filterIdKey, int filterIdValue,
                                 String cookieKey, String cookieValue,
                                 String endpoint) {
        return given()
                .spec(specSetup.requestSpecification())
                .pathParam(filterIdKey, filterIdValue)
                .header(cookieKey, cookieValue)
                .when()
                .delete(endpoint)
                .then()
                .log().body()
                .extract()
                .response();
    }
}
