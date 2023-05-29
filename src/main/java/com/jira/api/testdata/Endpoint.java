package com.jira.api.testdata;

/**
 * A class contains the endpoints.
 */
public class Endpoint {
    public static final String LOGIN_ENDPOINT = "/rest/auth/1/session";
    public static final String CREATE_ISSUE_ENDPOINT = "/rest/api/2/issue";
    public static final String ADD_COMMENT_ENDPOINT = "/rest/api/2/issue/{key}/comment";
    public static final String GET_ISSUE_ENDPOINT = "/rest/api/2/issue/{key}";
    public static final String UPDATE_COMMENT_ENDPOINT = "/rest/api/2/issue/{key}/comment/{comment}";
    public static final String GET_COMMENT_ENDPOINT = "/rest/api/2/issue/{key}/comment/{comment}";
    public static final String CREATE_FILTER_ENDPOINT = "/rest/api/2/filter";
    public static final String DELETE_FILTER_ENDPOINT = "/rest/api/2/filter/{id}";
}

