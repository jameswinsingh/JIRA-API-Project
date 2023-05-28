package com.jira.api.base;

import com.jira.api.constant.FilePathConstant;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class Base {

    public static ExtentTest report;
    public static ExtentReports extent;


    @BeforeSuite
    public void beforeSuite() {
        extent = new ExtentReports(FilePathConstant.EXTENT_REPORT_PATH);
    }


    /**
     * A method to get the class name & method name of the current test.
     */
    @BeforeMethod
    public void beforeMethod(Method method) {
        report = extent.startTest((this.getClass().getSimpleName() + "::" + method.getName()), method.getName());
    }

    /**
     * A method to end the extent report.
     */
    @AfterMethod
    public void afterMethod() {
        extent.endTest(report);
    }

    /**
     * A method to clear the existing reports.
     * A method to end the extent report after the test execution is done .
     */
    @AfterSuite
    public void afterClass() {
        extent.flush();
        extent.close();
    }
}
