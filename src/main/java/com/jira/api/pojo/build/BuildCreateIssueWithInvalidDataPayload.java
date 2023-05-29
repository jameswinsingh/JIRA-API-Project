package com.jira.api.pojo.build;


import com.jira.api.pojo.dto.createIssueWithInvalidData.CreateIssueRequestData;
import com.jira.api.pojo.dto.createIssueWithInvalidData.FieldsData;
import com.jira.api.pojo.dto.createIssueWithInvalidData.IssueTypeData;
import com.jira.api.pojo.dto.createIssueWithInvalidData.ProjectData;

public class BuildCreateIssueWithInvalidDataPayload {

    public static CreateIssueRequestData createIssueRequestPayload() {

        ProjectData projectData = new ProjectData();
        projectData.setKey("RA");

        IssueTypeData issueType = new IssueTypeData();
        issueType.setName("Buggggg");

        FieldsData fields = new FieldsData();
        fields.setProject(projectData);
        fields.setSummary("Start JIRA rest API automation");
        fields.setDescription("Automate this issue");
        fields.setIssuetype(issueType);


        CreateIssueRequestData requestPayload = new CreateIssueRequestData();
        requestPayload.setFields(fields);
        return requestPayload;

    }

}
