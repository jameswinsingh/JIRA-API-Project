package com.jira.api.steps;

import com.jira.api.assertion.Assertion;
import com.jira.api.constant.FilePathConstant;
import com.jira.api.constant.SpecificationConstant;
import com.jira.api.constant.StatusCodeConstant;
import com.jira.api.message.InfoMessage;
import com.jira.api.message.VerificationMessage;
import com.jira.api.pojo.build.BuildCreateFilterPayload;
import com.jira.api.testdata.Endpoint;
import com.jira.api.testdata.TestData;
import com.jira.api.utils.ApiActions;
import com.jira.api.utils.JsonSchemaValidator;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * A class contains test to create, get, & delete the filter.
 */
public class JiraFilterApiValidation {

    ExtentTest report;

    public JiraFilterApiValidation(ExtentTest report) {
        this.report = report;
    }

    ApiActions apiActions = new ApiActions();

    /**
     * A method to create filter
     *
     * @param token contains the token value
     * @return filter ID
     */
    public int verifyCreateFilterApi(String token) {

        JsonPath jsonPath = null;
        try {

            report.log(LogStatus.INFO, InfoMessage.CREATE_FILTER);

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
            jsonPath = new JsonPath(response.asString());

            int statusCode = response.getStatusCode();
            boolean responseSchema = JsonSchemaValidator.validateJsonSchema(response, FilePathConstant.CREATE_FILTER_SCHEMA_FILEPATH);

            Assertion.verifyStatusCode(statusCode, StatusCodeConstant.STATUS_C0DE_200, VerificationMessage.VERIFY_STATUS_CODE, report);
            Assertion.verifyBooleanValue(responseSchema, true, jsonSchemaMessage, report);
        } catch (Exception exception) {
            report.log(LogStatus.FAIL, exception.fillInStackTrace());
        }
        return jsonPath.getInt("id");

    }

    /**
     * A method to get filter.
     *
     * @param filterId contains filter ID
     * @param token    contains token
     */
    public void verifyGetFilterApi(int filterId, String token) {

        try {

            report.log(LogStatus.INFO, InfoMessage.GET_FILTER);

            String cookieKey = SpecificationConstant.COOKIE_KEY;
            String filterIdKey = TestData.FILTER_KEY;
            String endpoint = Endpoint.DELETE_FILTER_ENDPOINT;
            String jsonSchemaMessage = VerificationMessage.VERIFY_JSON_SCHEMA;

            Response response = apiActions.getFilter(filterIdKey, filterId,
                    cookieKey, token,
                    endpoint
            );

            int statusCode = response.getStatusCode();
            boolean responseSchema = JsonSchemaValidator.validateJsonSchema(response, FilePathConstant.GET_FILTER_SCHEMA_FILEPATH);

            Assertion.verifyStatusCode(statusCode, StatusCodeConstant.STATUS_C0DE_200, VerificationMessage.VERIFY_STATUS_CODE, report);
            Assertion.verifyBooleanValue(responseSchema, true, jsonSchemaMessage, report);

        } catch (Exception exception) {
            report.log(LogStatus.FAIL, exception.fillInStackTrace());
        }

    }

    /**
     * A method to delete the created filter.
     *
     * @param filterId contains filter ID
     * @param token    contains token
     */
    public void verifyDeleteFilterApi(int filterId, String token) {

        try {

            report.log(LogStatus.INFO, InfoMessage.DELETE_FILTER);

            String cookieKey = SpecificationConstant.COOKIE_KEY;
            String filterIdKey = TestData.FILTER_KEY;
            String endpoint = Endpoint.DELETE_FILTER_ENDPOINT;

            Response response = apiActions.deleteFilter(
                    filterIdKey, filterId,
                    cookieKey, token,
                    endpoint
            );

            int statusCode = response.getStatusCode();
            Assertion.verifyStatusCode(statusCode, StatusCodeConstant.STATUS_C0DE_204, VerificationMessage.VERIFY_STATUS_CODE, report);

        } catch (Exception exception) {
            report.log(LogStatus.FAIL, exception.fillInStackTrace());
        }
    }
}
