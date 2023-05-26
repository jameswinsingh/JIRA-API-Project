package com.jira.api.constant;

import java.io.File;

/**
 * A class contains of all the file paths.
 */
public class FilePathConstant {

    public static final String USER_DIR_PATH = System.getProperty("user.dir");
    public static final String RESOURCES_PATH = USER_DIR_PATH + File.separator + "src" + File.separator + "main"
            + File.separator + "resources" + File.separator;
    public static final String EXTENT_REPORT_PATH = USER_DIR_PATH + "/test-output/ExtentReport.html";
    public static final String BASE_RESOURCE = RESOURCES_PATH + "baseresources" + File.separator;
    public static final String BASE_RESOURCE_FILE_PATH = BASE_RESOURCE + "BasicResources.properties";
    public static final String TEST_DATA = RESOURCES_PATH + "testdata" + File.separator;
    public static final String TEST_DATA_FILEPATH = TEST_DATA + "data.properties";
    public static final String JSON_SCHEMA = RESOURCES_PATH + "jsonschemafile" + File.separator;
    public static final String LOGIN_SCHEMA_FILEPATH = JSON_SCHEMA + "login_response_json_Schema.json";
    public static final String ADD_COMMENT_SCHEMA_FILEPATH = JSON_SCHEMA + "add_comment_json_schema.json";


}

