package com.jira.api.pojo.dto.loginResponseData;

/**
 * A Class contains serialization and deserialization methods .
 */
public class LoginSessionData {

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
