package com.jira.api.testscript;

import com.jira.api.base.Base;
import com.jira.api.steps.JiraApiValidation;
import org.testng.annotations.Test;

public class JiraApiTest extends Base {

    JiraApiValidation jiraApi;
    String token;
    String issueKey = "RA-22";
    int commentId;

    @Test
    public void verifyLogin() {
        jiraApi = new JiraApiValidation(report);
        token = jiraApi.login();

    }

//    @Test(priority = 1)
//    public void verifyCreateIssue() {
//        jiraApi = new JiraApiValidation(report);
////        issueKey = jiraApi.createNewIssue(token);
//        jiraApi.createNewIssue(token);
//    }

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
        System.out.println(commentId);
        jiraApi = new JiraApiValidation(report);
        jiraApi.modifyComment(token, issueKey, commentId);
    }

    @Test(priority = 5)
    public void verifyGetCommentApi() {
        System.out.println(commentId);
        jiraApi = new JiraApiValidation(report);
        jiraApi.getComment(token, issueKey, commentId);
    }

    @Test(priority = 6, enabled = false)
    public void verifyDeleteCommentApi() {
        System.out.println(commentId);
        jiraApi = new JiraApiValidation(report);
        jiraApi.deleteComment(token, issueKey, commentId);
    }

    @Test(priority = 7)
    public void verifyDeleteIssueApi() {
        System.out.println(commentId);
        jiraApi = new JiraApiValidation(report);
        jiraApi.deleteIssue(token, issueKey);
    }

}
