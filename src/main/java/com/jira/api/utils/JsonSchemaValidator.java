package com.jira.api.utils;

import io.restassured.response.Response;

import java.io.File;

public class JsonSchemaValidator {
    public static boolean validateJsonSchema(Response response, String schemaPath) {
        try {
            response.then().assertThat().body(io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema(new File(schemaPath)));
            return true;
        } catch (AssertionError assertionError) {
            return false;
        }
    }
}
