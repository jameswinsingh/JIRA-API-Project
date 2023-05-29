package com.jira.api.pojo.build;


import com.jira.api.pojo.dto.createAddCommentRequestInvalidData.AddCommentsRequestData;
import com.jira.api.pojo.dto.createAddCommentRequestInvalidData.Visibility;

public class BuildInvalidAddCommentRequestPayload {


    public static AddCommentsRequestData addCommentPayload() {

        AddCommentsRequestData addCommentsRequestData = new AddCommentsRequestData();

        Visibility visibility = new Visibility();
        visibility.setType("role");
        visibility.setValue("Administrators");

        addCommentsRequestData.setVisibility(visibility);
        addCommentsRequestData.setBody(" ");

        return addCommentsRequestData;
    }


}
