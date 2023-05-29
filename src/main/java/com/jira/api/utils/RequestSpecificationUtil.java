package com.jira.api.utils;


import com.jira.api.constant.BaseConfiguration;
import com.jira.api.constant.FilePathConstant;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class RequestSpecificationUtil {

    public static RequestSpecification specification;

    /**
     * A method to set up base uri & to log request, response body.
     */
    public RequestSpecification requestSpecification() {

        try {
            if (specification == null) {
                PrintStream log = new PrintStream(new FileOutputStream(FilePathConstant.LOGGER_FILE_PATH));
                specification = new RequestSpecBuilder().setBaseUri(BaseConfiguration.URL)
                        .addFilter(RequestLoggingFilter.logRequestTo(log))
                        .addFilter(ResponseLoggingFilter.logResponseTo(log))
                        .build();

            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.getLocalizedMessage();
        }
        return specification;
    }
}
