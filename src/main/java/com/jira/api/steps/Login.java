package com.jira.api.steps;

import com.jira.api.assertion.Assertion;
import com.jira.api.constant.FilePathConstant;
import com.jira.api.constant.SpecificationConstant;
import com.jira.api.constant.StatusCodeConstant;
import com.jira.api.message.VerificationMessage;
import com.jira.api.pojo.build.BuildAddCommentRequestPayload;
import com.jira.api.pojo.build.BuildCreateIssueRequestPayload;
import com.jira.api.pojo.build.BuildLoginRequestPayload;
import com.jira.api.pojo.dto.CreateIssueResponseData;
import com.jira.api.pojo.dto.loginResponseData.LoginResponseData;
import com.jira.api.testdata.Endpoint;
import com.jira.api.testdata.TestData;
import com.jira.api.utils.ApiActions;
import com.jira.api.utils.JsonSchemaValidator;
import com.jira.api.utils.PropertyParser;
import com.relevantcodes.extentreports.ExtentTest;
import io.restassured.response.Response;
import org.testng.Assert;

public class Login {

    ExtentTest report;

    PropertyParser getTestData = new PropertyParser(FilePathConstant.TEST_DATA_FILEPATH);

    public Login(ExtentTest report) {
        this.report = report;
    }

    ApiActions apiActions = new ApiActions();
    String token;
    String issueKeyValue;

    public void login() {

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

        boolean responseSchema = JsonSchemaValidator.validateJsonSchema(response,FilePathConstant.LOGIN_SCHEMA_FILEPATH);
        LoginResponseData loginResponse = response.as(LoginResponseData.class);
        String name = loginResponse.getSession().getName();
        String value = loginResponse.getSession().getValue();
        token = name + "=" + value;
        int statusCode = response.getStatusCode();
        String actualTokenName = loginResponse.getSession().getName();
        boolean tokenIsNotEmpty = !loginResponse.getSession().getValue().isEmpty();


        Assertion.verifyStatusCode(statusCode, StatusCodeConstant.STATUSC0DE_200, VerificationMessage.VERIFY_STATUS_CODE, report);
        Assertion.verifyBooleanValue(responseSchema, true, jsonSchemaMessage, report);
        Assertion.verifyString(actualTokenName, expectedTokenName, tokenNameMessage, report);
        Assertion.verifyBooleanValue(tokenIsNotEmpty, true,tokenValueMessage, report);
    }

    public void createNewIssue() {
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
        issueKeyValue = createIssueResponse.getKey();
    }


    public void addCommentToTheIssue() {
        String issueKey = getTestData.getPropertyValue(TestData.ISSUE_KEY);
        String contentTypeKey = SpecificationConstant.CONTENT_TYPE_KEY;
        String contentTypeValue = SpecificationConstant.CONTENT_TYPE_VALUE;
        String cookieKey = SpecificationConstant.COOKIE_KEY;

        Response response = apiActions.addComment(issueKey, "RA-8",
                contentTypeKey, contentTypeValue,
                cookieKey, token,
                BuildAddCommentRequestPayload.addCommentPayload(),
                Endpoint.ADD_COMMENT_ENDPOINT
        );

        boolean responseSchema = JsonSchemaValidator.validateJsonSchema(response,FilePathConstant.ADD_COMMENT_SCHEMA_FILEPATH);


        Assertion.verifyBooleanValue(responseSchema,true, VerificationMessage.VERIFY_JSON_SCHEMA, report);


    }

}
