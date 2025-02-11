package com.jira.api.pojo.dto;

/**
 * A Class contains serialization and deserialization methods .
 */
public class CreateIssueResponseData {

    private String id;
    private String key;
    private String self;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }
}
