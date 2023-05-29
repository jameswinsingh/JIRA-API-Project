package com.jira.api.pojo.dto.createIssueRequestData;

/**
 * A Class contains serialization and deserialization methods .
 */
public class FieldsData {

    private ProjectData projectData;
    private String summary;
    private String description;
    private IssueTypeData issuetype;


    public ProjectData getProject() {
        return projectData;
    }

    public void setProject(ProjectData projectData) {
        this.projectData = projectData;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IssueTypeData getIssuetype() {
        return issuetype;
    }

    public void setIssuetype(IssueTypeData issuetype) {
        this.issuetype = issuetype;
    }
}
