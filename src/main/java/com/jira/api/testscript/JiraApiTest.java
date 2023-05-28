package com.jira.api.testscript;

import com.jira.api.base.Base;
import com.jira.api.steps.JiraApiValidation;
import org.testng.annotations.Test;

public class JiraApiTest extends Base {

    JiraApiValidation jiraApi;
    String token;
    String issueKey;
    int commentId;

    @Test
    public void verifyLogin() {
        jiraApi = new JiraApiValidation(report);
        token = jiraApi.verifyLogin();

    }

    @Test(priority = 1)
    public void verifyCreateIssue() {
        jiraApi = new JiraApiValidation(report);
        issueKey = jiraApi.verifyCreateIssueApi(token);
    }

    @Test(priority = 2)
    public void verifyAddComment() {
        jiraApi = new JiraApiValidation(report);
        commentId = jiraApi.verifyAddCommentApi(token, issueKey);
    }

    @Test(priority = 3)
    public void verifyGetIssueApi() {
        jiraApi = new JiraApiValidation(report);
        jiraApi.verifyGetIssueApi(token, issueKey);
    }

    @Test(priority = 4)
    public void verifyUpdateCommentApi() {
        System.out.println(commentId);
        jiraApi = new JiraApiValidation(report);
        jiraApi.verifyModifyCommentApi(token, issueKey, commentId);
    }

    @Test(priority = 5)
    public void verifyGetCommentApi() {
        System.out.println(commentId);
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

}
