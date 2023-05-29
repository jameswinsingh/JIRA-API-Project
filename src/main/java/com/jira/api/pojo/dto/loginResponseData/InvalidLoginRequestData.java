package com.jira.api.pojo.dto.loginResponseData;

/**
 * A Class contains serialization and deserialization methods .
 */
public class InvalidLoginRequestData {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
