package com.jira.api.testscript;

import com.jira.api.base.Base;
import com.jira.api.steps.JiraApiValidation;
import org.testng.annotations.Test;

public class JiraApiTest extends Base {

    JiraApiValidation jiraApi;
    String token;
    String issueKey = "RA-12";
    int commentId;

    @Test
    public void verifyLogin() {
        jiraApi = new JiraApiValidation(report);
        token = jiraApi.login();

    }

    @Test(priority = 1)
    public void verifyCreateIssue() {
        jiraApi = new JiraApiValidation(report);
//        issueKey = jiraApi.createNewIssue(token);
        jiraApi.createNewIssue(token);
    }

    @Test(priority = 2)
    public void verifyAddComment() {
        jiraApi = new JiraApiValidation(report);
        commentId = jiraApi.addCommentToTheIssue(token, issueKey);
    }

    @Test(priority = 3)
    public void verifyGetIssueApi() {
        jiraApi = new JiraApiValidation(report);
        jiraApi.getCreatedIssue(token, issueKey);
    }

    @Test(priority = 4)
    public void verifyUpdateCommentApi() {
        jiraApi = new JiraApiValidation(report);
        jiraApi.modifyComment(token, issueKey, commentId);
    }


}
