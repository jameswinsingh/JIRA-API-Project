package com.jira.api.steps;

import com.jira.api.assertion.Assertion;
import com.jira.api.constant.FilePathConstant;
import com.jira.api.constant.SpecificationConstant;
import com.jira.api.constant.StatusCodeConstant;
import com.jira.api.message.InfoMessage;
import com.jira.api.message.VerificationMessage;
import com.jira.api.pojo.build.*;
import com.jira.api.pojo.dto.CreateIssueResponseData;
import com.jira.api.pojo.dto.loginResponseData.LoginResponseData;
import com.jira.api.testdata.Endpoint;
import com.jira.api.testdata.TestData;
import com.jira.api.utils.ApiActions;
import com.jira.api.utils.JsonSchemaValidator;
import com.jira.api.utils.ReadJsonDataUtil;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * A Class contains JIRA API tests.
 */
public class JiraIssueApiValidation {

    ExtentTest report;

    public JiraIssueApiValidation(ExtentTest report) {
        this.report = report;
    }

    ApiActions apiActions = new ApiActions();

    /**
     * A method to verify login API.
     */
    public String verifyLogin() {

        String token = null;
        try {
            report.log(LogStatus.INFO, InfoMessage.LOGIN_JIRA);

            String headerKey = SpecificationConstant.CONNECTION_KEY;
            String headerValue = SpecificationConstant.CONNECTION_VALUE;
            String contentType = SpecificationConstant.CONTENT_TYPE_VALUE;
            String jsonSchemaMessage = VerificationMessage.VERIFY_JSON_SCHEMA;
            String tokenNameMessage = VerificationMessage.VERIFY_TOKEN_NAME;
            String tokenValueMessage = VerificationMessage.TOKEN_IS_NOT_EMPTY;
            String expectedTokenName = (String) ReadJsonDataUtil.readJsonData(FilePathConstant.TEST_DATA_JSON_FILEPATH, TestData.TOKEN_NAME);

            Response response = apiActions.post(headerKey, headerValue,
                    contentType,
                    BuildLoginRequestPayload.setUpLoginCredentials(),
                    Endpoint.LOGIN_ENDPOINT
            );

            boolean responseSchema = JsonSchemaValidator.validateJsonSchema(response, FilePathConstant.LOGIN_SCHEMA_FILEPATH);
            LoginResponseData loginResponse = response.as(LoginResponseData.class);
            String name = loginResponse.getSession().getName();
            String value = loginResponse.getSession().getValue();
            token = name + "=" + value;

            int statusCode = response.getStatusCode();
            String actualTokenName = loginResponse.getSession().getName();
            boolean tokenIsNotEmpty = !loginResponse.getSession().getValue().isEmpty();

            Assertion.verifyStatusCode(statusCode, StatusCodeConstant.STATUS_C0DE_200, VerificationMessage.VERIFY_STATUS_CODE, report);
            Assertion.verifyBooleanValue(responseSchema, true, jsonSchemaMessage, report);
            Assertion.verifyString(actualTokenName, expectedTokenName, tokenNameMessage, report);
            Assertion.verifyBooleanValue(tokenIsNotEmpty, true, tokenValueMessage, report);
        } catch (Exception exception) {
            report.log(LogStatus.FAIL, exception.fillInStackTrace());
        }
        return token;
    }

    /**
     * A method to verify create issue API.
     *
     * @param token contains the token.
     * @return issue key
     */
    public String verifyCreateIssueApi(String token) {

        CreateIssueResponseData createIssueResponse = null;

        try {

            report.log(LogStatus.INFO, InfoMessage.CREATE_NEW_ISSUE);

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

            createIssueResponse = response.as(CreateIssueResponseData.class);

            int statusCode = response.getStatusCode();
            boolean responseSchema = JsonSchemaValidator.validateJsonSchema(response, FilePathConstant.CREATE_ISSUE_SCHEMA_FILEPATH);


            Assertion.verifyStatusCode(statusCode, StatusCodeConstant.STATUS_C0DE_201, VerificationMessage.VERIFY_STATUS_CODE, report);
            Assertion.verifyBooleanValue(responseSchema, true, VerificationMessage.VERIFY_JSON_SCHEMA, report);
        } catch (Exception exception) {
            report.log(LogStatus.FAIL, exception.fillInStackTrace());
        }
        return createIssueResponse.getKey();
    }


    /**
     * A method to verify add comment API.
     *
     * @param token    contain the login token
     * @param issueKey contains the created issue key
     * @return contains created comment id
     */
    public int verifyAddCommentApi(String token, String issueKey) {

        int commentId = 0;
        try {
            report.log(LogStatus.INFO, InfoMessage.ADD_COMMENT_TO_ISSUE);

            String key = (String) ReadJsonDataUtil.readJsonData(FilePathConstant.TEST_DATA_JSON_FILEPATH, TestData.ISSUE_KEY);
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
            commentId = jsonPath.getInt("id");

            int actualStatusCode = response.getStatusCode();
            int expectedStatusCode = StatusCodeConstant.STATUS_C0DE_201;
            boolean responseSchema = JsonSchemaValidator.validateJsonSchema(response, FilePathConstant.ADD_COMMENT_SCHEMA_FILEPATH);

            Assertion.verifyStatusCode(actualStatusCode, expectedStatusCode, VerificationMessage.VERIFY_STATUS_CODE, report);
            Assertion.verifyBooleanValue(responseSchema, true, VerificationMessage.VERIFY_JSON_SCHEMA, report);

        } catch (Exception exception) {
            report.log(LogStatus.FAIL, exception.fillInStackTrace());
        }
        return commentId;

    }

    /**
     * A method to verify get issue API.
     *
     * @param token    token contain the login token
     * @param issueKey contains the created issue key
     */
    public void verifyGetIssueApi(String token, String issueKey) {

        try {

            report.log(LogStatus.INFO, InfoMessage.GET_ISSUE);

            String key = (String) ReadJsonDataUtil.readJsonData(FilePathConstant.TEST_DATA_JSON_FILEPATH, TestData.ISSUE_KEY);
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

        } catch (Exception exception) {
            report.log(LogStatus.FAIL, exception.fillInStackTrace());
        }
    }

    /**
     * A method to verify modify comment API.
     *
     * @param token     token contain the login token.
     * @param issueKey  contains the created issue key
     * @param commentId contains the comment id
     */

    public void verifyModifyCommentApi(String token, String issueKey, int commentId) {

        try {

            report.log(LogStatus.INFO, InfoMessage.MODIFY_COMMENT);

            String key = (String) ReadJsonDataUtil.readJsonData(FilePathConstant.TEST_DATA_JSON_FILEPATH, TestData.ISSUE_KEY);
            String commentKey = (String) ReadJsonDataUtil.readJsonData(FilePathConstant.TEST_DATA_JSON_FILEPATH, TestData.COMMENT_KEY);

            String cookieKey = SpecificationConstant.COOKIE_KEY;
            String endpoint = Endpoint.UPDATE_COMMENT_ENDPOINT;


            Response response = apiActions.put(key, issueKey,
                    commentKey, commentId,
                    cookieKey, token,
                    CreateModifyCommentRequestPayload.modifyCommentRequestData(),
                    endpoint
            );

            JsonPath jsonPath = new JsonPath(response.asString());
            String actualComment = jsonPath.get("body");

            boolean responseSchema = JsonSchemaValidator.validateJsonSchema(response, FilePathConstant.UPDATE_COMMENT_SCHEMA_FILEPATH);
            int actualStatusCode = response.getStatusCode();

            Assertion.verifyStatusCode(actualStatusCode, StatusCodeConstant.STATUS_C0DE_200, VerificationMessage.VERIFY_STATUS_CODE, report);
            Assertion.verifyBooleanValue(responseSchema, true, VerificationMessage.VERIFY_JSON_SCHEMA, report);
            Assertion.verifyString(actualComment, TestData.RESPONSE_COMMENT, VerificationMessage.UPDATE_COMMENT, report);

        } catch (Exception exception) {
            report.log(LogStatus.FAIL, exception.fillInStackTrace());
        }
    }

    /**
     * A method to verify get comment API.
     *
     * @param token     token contain the login token.
     * @param issueKey  issueKey contains the created issue key.
     * @param commentId contains the comment id.
     */
    public void verifyGetCommentApi(String token, String issueKey, int commentId) {

        try {

            report.log(LogStatus.INFO, InfoMessage.GET_COMMENT);

            String key = (String) ReadJsonDataUtil.readJsonData(FilePathConstant.TEST_DATA_JSON_FILEPATH, TestData.ISSUE_KEY);
            String commentKey = (String) ReadJsonDataUtil.readJsonData(FilePathConstant.TEST_DATA_JSON_FILEPATH, TestData.COMMENT_KEY);
            String cookieKey = SpecificationConstant.COOKIE_KEY;
            String endpoint = Endpoint.GET_COMMENT_ENDPOINT;

            Response response = apiActions.get(key, issueKey,
                    commentKey, commentId,
                    cookieKey, token,
                    endpoint
            );

            JsonPath jsonPath = new JsonPath(response.asString());
            String actualComment = jsonPath.get("body");

            boolean responseSchema = JsonSchemaValidator.validateJsonSchema(response, FilePathConstant.GET_COMMENT_SCHEMA_FILEPATH);
            int actualStatusCode = response.getStatusCode();

            Assertion.verifyStatusCode(actualStatusCode, StatusCodeConstant.STATUS_C0DE_200, VerificationMessage.VERIFY_STATUS_CODE, report);
            Assertion.verifyBooleanValue(responseSchema, true, VerificationMessage.VERIFY_JSON_SCHEMA, report);
            Assertion.verifyString(actualComment, TestData.RESPONSE_COMMENT, VerificationMessage.GET_UPDATE_COMMENT, report);
        } catch (Exception exception) {
            report.log(LogStatus.FAIL, exception.fillInStackTrace());
        }
    }

    /**
     * A method to verify delete comment API.
     *
     * @param token     token contain the login token.
     * @param issueKey  issueKey contains the created issue key.
     * @param commentId contains the comment id.
     */
    public void verifyDeleteCommentApi(String token, String issueKey, int commentId) {

        try {

            report.log(LogStatus.INFO, InfoMessage.DELETE_COMMENT);

            String key = (String) ReadJsonDataUtil.readJsonData(FilePathConstant.TEST_DATA_JSON_FILEPATH, TestData.ISSUE_KEY);
            String commentKey = (String) ReadJsonDataUtil.readJsonData(FilePathConstant.TEST_DATA_JSON_FILEPATH, TestData.COMMENT_KEY);
            String cookieKey = SpecificationConstant.COOKIE_KEY;
            String endpoint = Endpoint.UPDATE_COMMENT_ENDPOINT;

            Response response = apiActions.delete(key, issueKey,
                    commentKey, commentId,
                    cookieKey, token,
                    endpoint
            );

            int actualStatusCode = response.getStatusCode();

            Assertion.verifyStatusCode(actualStatusCode, StatusCodeConstant.STATUS_C0DE_204, VerificationMessage.VERIFY_STATUS_CODE, report);
        } catch (Exception exception) {
            report.log(LogStatus.FAIL, exception.fillInStackTrace());
        }
    }

    /**
     * A method to verify delete issue API.
     *
     * @param token    token contain the login token.
     * @param issueKey issueKey contains the created issue key.
     */
    public void verifyDeleteIssueApi(String token, String issueKey) {

        try {

            report.log(LogStatus.INFO, InfoMessage.DELETE_ISSUE);

            String key = (String) ReadJsonDataUtil.readJsonData(FilePathConstant.TEST_DATA_JSON_FILEPATH, TestData.ISSUE_KEY);
            String cookieKey = SpecificationConstant.COOKIE_KEY;
            String endpoint = Endpoint.GET_ISSUE_ENDPOINT;

            Response response = apiActions.delete(key, issueKey,
                    cookieKey, token,
                    endpoint
            );

            int actualStatusCode = response.getStatusCode();
            int expectedStatusCode = StatusCodeConstant.STATUS_C0DE_204;

            Assertion.verifyStatusCode(actualStatusCode, expectedStatusCode, VerificationMessage.VERIFY_STATUS_CODE, report);
        } catch (Exception exception) {
            report.log(LogStatus.FAIL, exception.fillInStackTrace());
        }

    }

    /**
     * A method to verify login API with invalid credentials.
     */
    public void verifyInvalidLogin() {

        try {

            report.log(LogStatus.INFO, InfoMessage.INVALID_LOGIN_JIRA);

            String headerKey = SpecificationConstant.CONNECTION_KEY;
            String headerValue = SpecificationConstant.CONNECTION_VALUE;
            String contentType = SpecificationConstant.CONTENT_TYPE_VALUE;
            String jsonSchemaMessage = VerificationMessage.VERIFY_JSON_SCHEMA;


            Response response = apiActions.post(headerKey, headerValue,
                    contentType,
                    BuildInvalidLoginRequestPayload.setUpLoginCredentials(),
                    Endpoint.LOGIN_ENDPOINT
            );

            boolean responseSchema = JsonSchemaValidator.validateJsonSchema(response, FilePathConstant.INVALID_LOGIN_SCHEMA_FILEPATH);
            int statusCode = response.getStatusCode();

            Assertion.verifyStatusCode(statusCode, StatusCodeConstant.STATUS_C0DE_401, VerificationMessage.VERIFY_STATUS_CODE, report);
            Assertion.verifyBooleanValue(responseSchema, true, jsonSchemaMessage, report);
        } catch (Exception exception) {
            report.log(LogStatus.FAIL, exception.fillInStackTrace());
        }

    }

    /**
     * A method to verify create issue API with invalid data.
     *
     * @param token contains the token.
     */
    public void verifyCreateIssueApiWithInvalidData(String token) {

        try {

            report.log(LogStatus.INFO, InfoMessage.CREATE_NEW_ISSUE_WITH_INVALID_DATA);

            String headerKey = SpecificationConstant.CONNECTION_KEY;
            String headerValue = SpecificationConstant.CONNECTION_VALUE;
            String cookieKey = SpecificationConstant.COOKIE_KEY;
            String contentType = SpecificationConstant.CONTENT_TYPE_VALUE;

            Response response = apiActions.post(headerKey, headerValue,
                    cookieKey, token,
                    contentType,
                    BuildCreateIssueWithInvalidDataPayload.createIssueRequestPayload(),
                    Endpoint.CREATE_ISSUE_ENDPOINT
            );

            int statusCode = response.getStatusCode();
            boolean responseSchema = JsonSchemaValidator.validateJsonSchema(response, FilePathConstant.CREATE_ISSUE_INVALID_DATA_SCHEMA_FILEPATH);

            Assertion.verifyStatusCode(statusCode, StatusCodeConstant.STATUS_C0DE_400, VerificationMessage.VERIFY_STATUS_CODE, report);
            Assertion.verifyBooleanValue(responseSchema, true, VerificationMessage.VERIFY_JSON_SCHEMA, report);

        } catch (Exception exception) {
            report.log(LogStatus.FAIL, exception.fillInStackTrace());
        }
    }

    /**
     * A method to verify add comment API with invalid data.
     *
     * @param token    contain the login token
     * @param issueKey contains the created issue key
     */
    public void verifyAddCommentApiWithInvalidData(String token, String issueKey) {

        try {

            report.log(LogStatus.INFO, InfoMessage.ADD_COMMENT_TO_ISSUE_WITH_INVALID_DATA);

            String key = (String) ReadJsonDataUtil.readJsonData(FilePathConstant.TEST_DATA_JSON_FILEPATH, TestData.ISSUE_KEY);
            String contentTypeKey = SpecificationConstant.CONTENT_TYPE_KEY;
            String contentTypeValue = SpecificationConstant.CONTENT_TYPE_VALUE;
            String cookieKey = SpecificationConstant.COOKIE_KEY;

            Response response = apiActions.addComment(key, issueKey,
                    contentTypeKey, contentTypeValue,
                    cookieKey, token,
                    BuildInvalidAddCommentRequestPayload.addCommentPayload(),
                    Endpoint.ADD_COMMENT_ENDPOINT
            );

            int actualStatusCode = response.getStatusCode();
            boolean responseSchema = JsonSchemaValidator.validateJsonSchema(response, FilePathConstant.ADD_COMMENT_INVALID_DATA_SCHEMA_FILEPATH);

            Assertion.verifyStatusCode(actualStatusCode, StatusCodeConstant.STATUS_C0DE_400, VerificationMessage.VERIFY_STATUS_CODE, report);
            Assertion.verifyBooleanValue(responseSchema, true, VerificationMessage.VERIFY_JSON_SCHEMA, report);

        } catch (Exception exception) {
            report.log(LogStatus.FAIL, exception.fillInStackTrace());
        }
    }

    /**
     * A method to verify modify comment API with invalid data.
     *
     * @param token    token contain the login token.
     * @param issueKey contains the created issue key
     */
    public void verifyModifyCommentApiWithInvalidData(String token, String issueKey) {

        try {

            report.log(LogStatus.INFO, InfoMessage.MODIFY_COMMENT_WITH_INVALID_DATA);

            String commentId = (String) ReadJsonDataUtil.readJsonData(FilePathConstant.TEST_DATA_JSON_FILEPATH, TestData.INVALID_COMMENT_ID);
            String key = (String) ReadJsonDataUtil.readJsonData(FilePathConstant.TEST_DATA_JSON_FILEPATH, TestData.ISSUE_KEY);
            String commentKey = (String) ReadJsonDataUtil.readJsonData(FilePathConstant.TEST_DATA_JSON_FILEPATH, TestData.COMMENT_KEY);
            String cookieKey = SpecificationConstant.COOKIE_KEY;
            String endpoint = Endpoint.UPDATE_COMMENT_ENDPOINT;

            Response response = apiActions.put(key, issueKey,
                    commentKey, Integer.parseInt(commentId),
                    cookieKey, token,
                    CreateModifyCommentRequestWithInvalidPayload.modifyCommentRequestData(),
                    endpoint
            );

            boolean responseSchema = JsonSchemaValidator.validateJsonSchema(response, FilePathConstant.MODIFY_COMMENT_INVALID_DATA_SCHEMA_FILEPATH);
            int actualStatusCode = response.getStatusCode();

            Assertion.verifyStatusCode(actualStatusCode, StatusCodeConstant.STATUS_C0DE_404, VerificationMessage.VERIFY_STATUS_CODE, report);
            Assertion.verifyBooleanValue(responseSchema, true, VerificationMessage.VERIFY_JSON_SCHEMA, report);

        } catch (Exception exception) {
            report.log(LogStatus.FAIL, exception.fillInStackTrace());
        }
    }

}
