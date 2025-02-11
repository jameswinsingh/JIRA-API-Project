package com.jira.api.assertion;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;

/**
 * This class contains assertion methods.
 */
public class Assertion {

    /**
     * @param actual                  contains the actual result
     * @param expected                contains the expected result
     * @param verificationStepMessage contains the verification message
     * @param test                    instance of ExtentTest
     * @return status of the test
     */
    public static boolean verifyString(String actual, String expected, String verificationStepMessage, ExtentTest test) {
        test.log(LogStatus.INFO, verificationStepMessage);
        boolean status = false;
        try {
            Assert.assertEquals(actual, expected);
            status = true;
            test.log(LogStatus.PASS, "Actual Result :: " + actual + " == " + " Expected Result :: " + expected);
        } catch (AssertionError assertionError) {
            test.log(LogStatus.FAIL, "Actual Result :: " + actual + " == " + " Expected Result :: " + expected);
        }
        return status;
    }

    /**
     * @param actual                  contains the actual result
     * @param expected                contains the expected result
     * @param verificationStepMessage contains the verification message
     * @param test                    instance of ExtentTest
     * @return status of the test
     */
    public static boolean verifyStatusCode(int actual, int expected, String verificationStepMessage, ExtentTest test) {
        test.log(LogStatus.INFO, verificationStepMessage);
        boolean status = false;
        try {
            Assert.assertEquals(actual, expected);
            status = true;
            test.log(LogStatus.PASS, "Actual Result :: " + actual + " == " + " Expected Result :: " + expected);
        } catch (AssertionError assertionError) {
            test.log(LogStatus.FAIL, "Actual Result :: " + actual + " == " + " Expected Result :: " + expected);
        }
        return status;
    }

    /**
     * @param actual                  contains the actual result
     * @param expected                contains the expected result
     * @param verificationStepMessage contains the verification message
     * @param test                    instance of ExtentTest
     * @return status of the test
     */
    public static boolean verifyInteger(int actual, int expected, String verificationStepMessage, ExtentTest test) {
        test.log(LogStatus.INFO, verificationStepMessage);
        boolean status = false;
        try {
            Assert.assertEquals(actual, expected);
            status = true;
            test.log(LogStatus.PASS, "Actual Result :: " + actual + " == " + " Expected Result :: " + expected);
        } catch (AssertionError assertionError) {
            test.log(LogStatus.FAIL, "Actual Result :: " + actual + " == " + " Expected Result :: " + expected);
        }
        return status;
    }

    /**
     * @param actual                  contains the actual result
     * @param expected                contains the expected result
     * @param verificationStepMessage contains the verification message
     * @param test                    instance of ExtentTest
     * @return status of the test
     */
    public static boolean verifyBooleanValue(boolean actual, boolean expected, String verificationStepMessage, ExtentTest test) {
        test.log(LogStatus.INFO, verificationStepMessage);
        boolean status = false;
        try {
            Assert.assertEquals(actual, expected);
            status = true;
            test.log(LogStatus.PASS, "Actual Result :: " + actual + " == " + " Expected Result :: " + expected);
        } catch (AssertionError assertionError) {
            test.log(LogStatus.FAIL, "Actual Result :: " + actual + " == " + " Expected Result :: " + expected);
        }
        return status;
    }
}




