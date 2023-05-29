package com.jira.api.testscript;

import com.jira.api.base.Base;
import com.jira.api.steps.FilterApiValidation;
import com.jira.api.steps.JiraApiValidation;
import org.testng.annotations.Test;

/**
 * A class contains the JIRA API tests.
 */
public class JiraApiTest extends Base {

    JiraApiValidation jiraApi;
    String token;
    String issueKey;
    int commentId;
    int filterId;


    /**
     * A method to verify the login API.
     */
    @Test
    public void verifyLogin() {
        jiraApi = new JiraApiValidation(report);
        token = jiraApi.verifyLogin();

    }

    /**
     * A method to verify the create issue API.
     */
    @Test(priority = 1)
    public void verifyCreateIssue() {
        jiraApi = new JiraApiValidation(report);
        issueKey = jiraApi.verifyCreateIssueApi(token);
    }

    /**
     * A method to verify the get issue API.
     */
    @Test(priority = 2)
    public void verifyGetIssueApi() {
        jiraApi = new JiraApiValidation(report);
        jiraApi.verifyGetIssueApi(token, issueKey);
    }

    /**
     * A method to verify the add comment API.
     */
    @Test(priority = 3)
    public void verifyAddComment() {
        jiraApi = new JiraApiValidation(report);
        commentId = jiraApi.verifyAddCommentApi(token, issueKey);
    }

    /**
     * A method to verify the update comment API.
     */
    @Test(priority = 4)
    public void verifyUpdateCommentApi() {
        System.out.println(commentId);
        jiraApi = new JiraApiValidation(report);
        jiraApi.verifyModifyCommentApi(token, issueKey, commentId);
    }

    /**
     * A method to verify the get comment API.
     */
    @Test(priority = 5)
    public void verifyGetCommentApi() {
        jiraApi = new JiraApiValidation(report);
        jiraApi.verifyGetCommentApi(token, issueKey, commentId);
    }

    @Test(priority = 6, enabled = true)
    public void verifyDeleteCommentApi() {
        jiraApi = new JiraApiValidation(report);
        jiraApi.verifyDeleteCommentApi(token, issueKey, commentId);
    }

    @Test(priority = 7, enabled = true)
    public void verifyDeleteIssueApi() {
        jiraApi = new JiraApiValidation(report);
        jiraApi.verifyDeleteIssueApi(token, issueKey);
    }

    @Test(priority = 8)
    public void verifyInvalidLoginTest() {
        jiraApi = new JiraApiValidation(report);
        jiraApi.verifyInvalidLogin();
    }

    @Test(priority = 9)
    public void verifyCreateIssueWithInvalidDataTest() {
        jiraApi = new JiraApiValidation(report);
        jiraApi.verifyCreateIssueApiWithInvalidData(token);
    }

    @Test(priority = 10)
    public void verifyAddCommentWithInvalidData() {
        jiraApi = new JiraApiValidation(report);
        jiraApi.verifyAddCommentApiWithInvalidData(token, issueKey);
    }

    @Test(priority = 11)
    public void verifyUpdateCommentApiWithInvalidData() {
        jiraApi = new JiraApiValidation(report);
        jiraApi.verifyModifyCommentApiWithInvalidData(token, issueKey, 4658);
    }

    @Test(priority = 12)
    public void verifyCreateFilterApi() {
        FilterApiValidation filterApiValidation = new FilterApiValidation(report);
        filterId = filterApiValidation.verifyCreateFilterApi(token);
    }

    @Test(priority = 13)
    public void verifyGetFilterApi() {
        FilterApiValidation filterApiValidation = new FilterApiValidation(report);
        filterApiValidation.verifyGetFilterApi(filterId, token);
    }

    @Test(priority = 14)
    public void verifyDeleteFilterApi() {
        FilterApiValidation filterApiValidation = new FilterApiValidation(report);
        filterApiValidation.verifyDeleteFilter(filterId, token);
    }

}
