package com.jira.api.pojo.build;

import com.jira.api.pojo.dto.LoginRequestData;

/**
 * A Class contains the setup of login request payload.
 */
public class BuildLoginRequestPayload {

    public static LoginRequestData setUpLoginCredentials()
    {
        LoginRequestData loginRequestBody = new LoginRequestData();
        loginRequestBody.setUsername("jamesrio617");
        loginRequestBody.setPassword("Restasssured@123");
        return loginRequestBody;
    }

}
