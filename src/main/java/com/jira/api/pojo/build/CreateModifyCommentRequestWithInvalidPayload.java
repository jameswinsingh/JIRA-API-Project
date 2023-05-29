package com.jira.api.pojo.build;


import com.jira.api.pojo.dto.createModifyCommentWithInvalidData.ModifyCommentsRequestData;
import com.jira.api.pojo.dto.createModifyCommentWithInvalidData.Visibility;

/**
 * A Class contains the setup of modify comment request payload with invalid data.
 */
public class CreateModifyCommentRequestWithInvalidPayload {

    public static ModifyCommentsRequestData modifyCommentRequestData()
    {
        ModifyCommentsRequestData modifyCommentsRequestData = new ModifyCommentsRequestData();

        Visibility visibility = new Visibility();
        visibility.setType("role");
        visibility.setValue("Administrators");

        modifyCommentsRequestData.setVisibility(visibility);
        modifyCommentsRequestData.setBody("This test is to update the comment");
        return modifyCommentsRequestData;
    }
}
