package com.jira.api.pojo.dto.createIssueWithInvalidData;

/**
 * A Class contains serialization and deserialization methods .
 */
public class CreateIssueRequestData {
    private FieldsData fields;

    public FieldsData getFields() {
        return fields;
    }

    public void setFields(FieldsData fields) {
        this.fields = fields;
    }
}
