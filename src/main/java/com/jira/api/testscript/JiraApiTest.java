package com.jira.api.testscript;

import com.jira.api.base.Base;
import com.jira.api.steps.Login;
import org.testng.annotations.Test;

public class JiraApiTest extends Base {

    @Test
    public void verifyLogin() {
        Login login = new Login(report);
        login.login();
//        login.createNewIssue();
        login.addCommentToTheIssue();
    }


}
