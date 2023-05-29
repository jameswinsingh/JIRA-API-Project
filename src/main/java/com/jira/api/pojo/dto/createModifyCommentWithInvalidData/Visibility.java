package com.jira.api.pojo.dto.createModifyCommentWithInvalidData;

/**
 * A Class contains serialization and deserialization methods .
 */
public class Visibility {
    private String type;
    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
