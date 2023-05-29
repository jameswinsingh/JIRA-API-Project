package com.jira.api.pojo.build;

import com.jira.api.pojo.dto.loginResponseData.InvalidLoginRequestData;

/**
 * A Class contains the setup of login request payload with invalid data.
 */
public class BuildInvalidLoginRequestPayload {

    public static InvalidLoginRequestData setUpLoginCredentials()
    {
        InvalidLoginRequestData loginRequestBody = new InvalidLoginRequestData();
        loginRequestBody.setUsername("jamesrio617");
        loginRequestBody.setPassword("Restasssured@1234");
        return loginRequestBody;
    }

}
