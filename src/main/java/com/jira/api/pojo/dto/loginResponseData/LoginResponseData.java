package com.jira.api.pojo.dto.loginResponseData;

/**
 * A Class contains serialization and deserialization methods .
 */
public class LoginResponseData {

    public LoginSessionData session;
    public LoginInfo loginInfo;

    public LoginSessionData getSession() {
        return session;
    }

    public void setSession(LoginSessionData session) {
        this.session = session;
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }
}
