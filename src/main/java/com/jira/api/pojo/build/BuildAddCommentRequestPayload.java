package com.jira.api.pojo.build;

import com.jira.api.pojo.dto.CreateAddCommentRequestData.AddCommentsRequestData;
import com.jira.api.pojo.dto.CreateAddCommentRequestData.Visibility;

/**
 * A Class contains the setup of add comment request payload.
 */
public class BuildAddCommentRequestPayload {

    public static AddCommentsRequestData addCommentPayload() {

        AddCommentsRequestData addCommentsRequestData = new AddCommentsRequestData();

        Visibility visibility = new Visibility();
        visibility.setType("role");
        visibility.setValue("Administrators");

        addCommentsRequestData.setVisibility(visibility);
        addCommentsRequestData.setBody("I have added a new comment to this issue");

        return addCommentsRequestData;
    }

}
