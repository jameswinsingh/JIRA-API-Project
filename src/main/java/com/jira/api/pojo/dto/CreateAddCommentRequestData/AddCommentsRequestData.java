package com.jira.api.pojo.dto.CreateAddCommentRequestData;

/**
 * A Class contains serialization and deserialization methods .
 */
public class AddCommentsRequestData {

    private String body;
    private Visibility visibility;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }
}
