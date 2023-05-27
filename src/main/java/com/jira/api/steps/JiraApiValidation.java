package com.jira.api.steps;

import com.jira.api.assertion.Assertion;
import com.jira.api.constant.FilePathConstant;
import com.jira.api.constant.SpecificationConstant;
import com.jira.api.constant.StatusCodeConstant;
import com.jira.api.message.VerificationMessage;
import com.jira.api.pojo.build.BuildAddCommentRequestPayload;
import com.jira.api.pojo.build.BuildCreateIssueRequestPayload;
import com.jira.api.pojo.build.BuildLoginRequestPayload;
import com.jira.api.pojo.build.CreateModifyCommentRequestPayload;
import com.jira.api.pojo.dto.CreateIssueResponseData;
import com.jira.api.pojo.dto.loginResponseData.LoginResponseData;
import com.jira.api.testdata.Endpoint;
import com.jira.api.testdata.TestData;
import com.jira.api.utils.ApiActions;
import com.jira.api.utils.JsonSchemaValidator;
import com.jira.api.utils.PropertyParser;
import com.relevantcodes.extentreports.ExtentTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class JiraApiValidation {

    ExtentTest report;

    PropertyParser getTestData = new PropertyParser(FilePathConstant.TEST_DATA_FILEPATH);

    public JiraApiValidation(ExtentTest report) {
        this.report = report;
    }

    ApiActions apiActions = new ApiActions();

    public String login() {

        String headerKey = SpecificationConstant.CONNECTION_KEY;
        String headerValue = SpecificationConstant.CONNECTION_VALUE;
        String contentType = SpecificationConstant.CONTENT_TYPE_VALUE;
        String jsonSchemaMessage = VerificationMessage.VERIFY_JSON_SCHEMA;
        String tokenNameMessage = VerificationMessage.VERIFY_TOKEN_NAME;
        String tokenValueMessage = VerificationMessage.TOKEN_IS_NOT_EMPTY;
        String expectedTokenName = getTestData.getPropertyValue(TestData.TOKEN_NAME);

        Response response = apiActions.post(headerKey, headerValue,
                contentType,
                BuildLoginRequestPayload.setUpLoginCredentials(),
                Endpoint.LOGIN_ENDPOINT
        );

        boolean responseSchema = JsonSchemaValidator.validateJsonSchema(response, FilePathConstant.LOGIN_SCHEMA_FILEPATH);
        LoginResponseData loginResponse = response.as(LoginResponseData.class);
        String name = loginResponse.getSession().getName();
        String value = loginResponse.getSession().getValue();
        String token = name + "=" + value;
        int statusCode = response.getStatusCode();
        String actualTokenName = loginResponse.getSession().getName();
        boolean tokenIsNotEmpty = !loginResponse.getSession().getValue().isEmpty();


        Assertion.verifyStatusCode(statusCode, StatusCodeConstant.STATUS_C0DE_200, VerificationMessage.VERIFY_STATUS_CODE, report);
        Assertion.verifyBooleanValue(responseSchema, true, jsonSchemaMessage, report);
        Assertion.verifyString(actualTokenName, expectedTokenName, tokenNameMessage, report);
        Assertion.verifyBooleanValue(tokenIsNotEmpty, true, tokenValueMessage, report);

        return token;
    }

    public String createNewIssue(String token) {
        String headerKey = SpecificationConstant.CONNECTION_KEY;
        String headerValue = SpecificationConstant.CONNECTION_VALUE;
        String cookieKey = SpecificationConstant.COOKIE_KEY;
        String contentType = SpecificationConstant.CONTENT_TYPE_VALUE;

        Response response = apiActions.post(headerKey, headerValue,
                cookieKey, token,
                contentType,
                BuildCreateIssueRequestPayload.createIssueRequestPayload(),
                Endpoint.CREATE_ISSUE_ENDPOINT
        );

        CreateIssueResponseData createIssueResponse = response.as(CreateIssueResponseData.class);
        return createIssueResponse.getKey();
    }


    public int addCommentToTheIssue(String token, String issueKey) {
        String key = getTestData.getPropertyValue(TestData.ISSUE_KEY);
        String contentTypeKey = SpecificationConstant.CONTENT_TYPE_KEY;
        String contentTypeValue = SpecificationConstant.CONTENT_TYPE_VALUE;
        String cookieKey = SpecificationConstant.COOKIE_KEY;

        Response response = apiActions.addComment(key, issueKey,
                contentTypeKey, contentTypeValue,
                cookieKey, token,
                BuildAddCommentRequestPayload.addCommentPayload(),
                Endpoint.ADD_COMMENT_ENDPOINT
        );

        JsonPath jsonPath = new JsonPath(response.asString());
        int commentId = jsonPath.getInt("id");

        int actualStatusCode = response.getStatusCode();
        int expectedStatusCode = StatusCodeConstant.STATUS_C0DE_201;
        boolean responseSchema = JsonSchemaValidator.validateJsonSchema(response, FilePathConstant.ADD_COMMENT_SCHEMA_FILEPATH);

        Assertion.verifyStatusCode(actualStatusCode, expectedStatusCode, VerificationMessage.VERIFY_STATUS_CODE, report);
        Assertion.verifyBooleanValue(responseSchema, true, VerificationMessage.VERIFY_JSON_SCHEMA, report);

        return commentId;

    }

    public void getCreatedIssue(String token, String issueKey) {
        String key = getTestData.getPropertyValue(TestData.ISSUE_KEY);
        String cookieKey = SpecificationConstant.COOKIE_KEY;
        String endpoint = Endpoint.GET_ISSUE_ENDPOINT;

        Response response = apiActions.get(key, issueKey,
                cookieKey, token,
                endpoint
        );

        boolean getIssueSchema = JsonSchemaValidator.validateJsonSchema(response, FilePathConstant.GET_ISSUE_SCHEMA_FILEPATH);
        int actualStatusCode = response.getStatusCode();
        int expectedStatusCode = StatusCodeConstant.STATUS_C0DE_200;

        Assertion.verifyStatusCode(actualStatusCode, expectedStatusCode, VerificationMessage.VERIFY_STATUS_CODE, report);
        Assertion.verifyBooleanValue(getIssueSchema, true, VerificationMessage.VERIFY_JSON_SCHEMA, report);

    }


    public void modifyComment(String token, String issueKey, int commentId) {
        String key = getTestData.getPropertyValue(TestData.ISSUE_KEY);
        String commentKey = getTestData.getPropertyValue(TestData.COMMENT_KEY);
        String cookieKey = SpecificationConstant.COOKIE_KEY;
        String endpoint = Endpoint.UPDATE_COMMENT_ENDPOINT;


        Response response = apiActions.put(key, issueKey,
                commentKey, commentId,
                cookieKey, token,
                CreateModifyCommentRequestPayload.modifyCommentRequestData(),
                endpoint
        );

        boolean responseSchema = JsonSchemaValidator.validateJsonSchema(response, FilePathConstant.UPDATE_COMMENT_SCHEMA_FILEPATH);
        int actualStatusCode = response.getStatusCode();

        Assertion.verifyStatusCode(actualStatusCode, StatusCodeConstant.STATUS_C0DE_200, VerificationMessage.VERIFY_STATUS_CODE, report);
        Assertion.verifyBooleanValue(responseSchema, true, VerificationMessage.VERIFY_JSON_SCHEMA, report);

    }

    public void getComment(String token, String issueKey, int commentId) {
        String key = getTestData.getPropertyValue(TestData.ISSUE_KEY);
        String commentKey = getTestData.getPropertyValue(TestData.COMMENT_KEY);
        String cookieKey = SpecificationConstant.COOKIE_KEY;
        String endpoint = Endpoint.UPDATE_COMMENT_ENDPOINT;

        Response response = apiActions.get(key, issueKey,
                commentKey, commentId,
                cookieKey, token,
                endpoint
        );

        boolean responseSchema = JsonSchemaValidator.validateJsonSchema(response, FilePathConstant.ADD_COMMENT_SCHEMA_FILEPATH);
        int actualStatusCode = response.getStatusCode();

        Assertion.verifyStatusCode(actualStatusCode, StatusCodeConstant.STATUS_C0DE_200, VerificationMessage.VERIFY_STATUS_CODE, report);
        Assertion.verifyBooleanValue(responseSchema, true, VerificationMessage.VERIFY_JSON_SCHEMA, report);
    }

    public void deleteComment(String token, String issueKey, int commentId) {
        String key = getTestData.getPropertyValue(TestData.ISSUE_KEY);
        String commentKey = getTestData.getPropertyValue(TestData.COMMENT_KEY);
        String cookieKey = SpecificationConstant.COOKIE_KEY;
        String endpoint = Endpoint.UPDATE_COMMENT_ENDPOINT;

        Response response = apiActions.delete(key, issueKey,
                commentKey, commentId,
                cookieKey, token,
                endpoint
        );

        int actualStatusCode = response.getStatusCode();

        Assertion.verifyStatusCode(actualStatusCode, StatusCodeConstant.STATUS_C0DE_204, VerificationMessage.VERIFY_STATUS_CODE, report);
    }

    public void deleteIssue(String token, String issueKey) {
        String key = getTestData.getPropertyValue(TestData.ISSUE_KEY);
        String cookieKey = SpecificationConstant.COOKIE_KEY;
        String endpoint = Endpoint.GET_ISSUE_ENDPOINT;

        Response response = apiActions.delete(key, issueKey,
                cookieKey, token,
                endpoint
        );


        int actualStatusCode = response.getStatusCode();
        int expectedStatusCode = StatusCodeConstant.STATUS_C0DE_204;

        Assertion.verifyStatusCode(actualStatusCode, expectedStatusCode, VerificationMessage.VERIFY_STATUS_CODE, report);


    }

}
