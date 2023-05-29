package com.jira.api.pojo.build;

import com.jira.api.pojo.dto.createIssueRequestData.CreateIssueRequestData;
import com.jira.api.pojo.dto.createIssueRequestData.FieldsData;
import com.jira.api.pojo.dto.createIssueRequestData.IssueTypeData;
import com.jira.api.pojo.dto.createIssueRequestData.ProjectData;

/**
 * A Class contains the setup of create issue request payload.
 */
public class BuildCreateIssueRequestPayload {

    public static CreateIssueRequestData createIssueRequestPayload() {

        ProjectData projectData = new ProjectData();
        projectData.setKey("RA");

        IssueTypeData issueType = new IssueTypeData();
        issueType.setName("Bug");

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
