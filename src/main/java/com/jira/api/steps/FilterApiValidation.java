package com.jira.api.steps;

import com.jira.api.assertion.Assertion;
import com.jira.api.constant.FilePathConstant;
import com.jira.api.constant.SpecificationConstant;
import com.jira.api.constant.StatusCodeConstant;
import com.jira.api.message.VerificationMessage;
import com.jira.api.pojo.build.BuildCreateFilterPayload;
import com.jira.api.testdata.Endpoint;
import com.jira.api.testdata.TestData;
import com.jira.api.utils.ApiActions;
import com.jira.api.utils.JsonSchemaValidator;
import com.jira.api.utils.PropertyParser;
import com.relevantcodes.extentreports.ExtentTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FilterApiValidation {

    ExtentTest report;

    public FilterApiValidation(ExtentTest report) {
        this.report = report;
    }
    ApiActions apiActions = new ApiActions();

    public int verifyCreateFilterApi(String token) {

        String cookieKey = SpecificationConstant.COOKIE_KEY;
        String contentTypeKey = SpecificationConstant.CONTENT_TYPE_KEY;
        String contentTypeValue = SpecificationConstant.CONTENT_TYPE_VALUE;
        String endpoint = Endpoint.CREATE_FILTER_ENDPOINT;
        String jsonSchemaMessage = VerificationMessage.VERIFY_JSON_SCHEMA;

        Response response = apiActions.post(cookieKey, token,
                contentTypeKey, contentTypeValue,
                BuildCreateFilterPayload.setCreateFilterRequestData(),
                endpoint
        );
        JsonPath jsonPath = new JsonPath(response.asString());

        int statusCode = response.getStatusCode();
        boolean responseSchema = JsonSchemaValidator.validateJsonSchema(response, FilePathConstant.LOGIN_SCHEMA_FILEPATH);

        Assertion.verifyStatusCode(statusCode, StatusCodeConstant.STATUS_C0DE_200, VerificationMessage.VERIFY_STATUS_CODE, report);
        Assertion.verifyBooleanValue(responseSchema, true, jsonSchemaMessage, report);

        return jsonPath.getInt("id");

    }

    public void verifyGetFilterApi(int filterId, String token) {
        String cookieKey = SpecificationConstant.COOKIE_KEY;
        String filterIdKey = TestData.FILTER_KEY;
        String endpoint = Endpoint.DELETE_FILTER_ENDPOINT;

        apiActions.getFilter(filterIdKey, filterId,
                cookieKey, token,
                endpoint
        );
    }

    public void verifyDeleteFilter(int filterId, String token) {

        String cookieKey = SpecificationConstant.COOKIE_KEY;
        String filterIdKey = TestData.FILTER_KEY;
        String endpoint = Endpoint.DELETE_FILTER_ENDPOINT;

        apiActions.deleteFilter(
                filterIdKey, filterId,
                cookieKey, token,
                endpoint
        );
    }
}
