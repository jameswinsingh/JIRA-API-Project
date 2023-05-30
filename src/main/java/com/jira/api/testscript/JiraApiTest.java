package com.jira.api.testscript;

import com.jira.api.base.Base;
import com.jira.api.steps.JiraFilterApiValidation;
import com.jira.api.steps.JiraIssueApiValidation;
import org.testng.annotations.Test;

/**
 * A class contains the JIRA API tests.
 */
public class JiraApiTest extends Base {

    JiraIssueApiValidation jiraApi;
    String token;
    String issueKey;
    int commentId;
    int filterId;

    /**
     * A method to verify the login API.
     */
    @Test
    public void verifyLoginApiTest() {
        jiraApi = new JiraIssueApiValidation(report);
        token = jiraApi.verifyLogin();

    }

    /**
     * A method to verify create issue, get issue & delete issue APIs .
     */
    @Test(priority = 1)
    public void verifyIssueApiTest() {
        jiraApi = new JiraIssueApiValidation(report);
        issueKey = jiraApi.verifyCreateIssueApi(token);
        jiraApi.verifyGetIssueApi(token, issueKey);
        jiraApi.verifyDeleteIssueApi(token, issueKey);

    }

    /**
     * A method to verify create issue, add comment, update comment, get comment, delete comment & delete issue APIs .
     */
    @Test(priority = 2)
    public void verifyCommentApiTest() {
        jiraApi = new JiraIssueApiValidation(report);
        issueKey = jiraApi.verifyCreateIssueApi(token);
        commentId = jiraApi.verifyAddCommentApi(token, issueKey);
        jiraApi.verifyModifyCommentApi(token, issueKey, commentId);
        jiraApi.verifyGetCommentApi(token, issueKey, commentId);
        jiraApi.verifyDeleteCommentApi(token, issueKey, commentId);
        jiraApi.verifyDeleteIssueApi(token, issueKey);

    }

    /**
     * A method to verify create filter, get filter & delete filter APIs.
     */
    @Test(priority = 3)
    public void verifyFilterApiTest() {
        JiraFilterApiValidation filterApiValidation = new JiraFilterApiValidation(report);
        filterId = filterApiValidation.verifyCreateFilterApi(token);
        filterApiValidation.verifyGetFilterApi(filterId, token);
        filterApiValidation.verifyDeleteFilterApi(filterId, token);

    }

    /**
     * A method to verify negative scenarios.
     */
    @Test(priority = 4)
    public void verifyNegativeTest() {
        jiraApi = new JiraIssueApiValidation(report);
        jiraApi.verifyInvalidLogin();
        jiraApi.verifyCreateIssueApiWithInvalidData(token);
        jiraApi.verifyAddCommentApiWithInvalidData(token, issueKey);
        jiraApi.verifyModifyCommentApiWithInvalidData(token, issueKey);

    }
}
