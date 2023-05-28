package com.jira.api.runner;

import com.jira.api.constant.FilePathConstant;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the runner method which has the configuration of suite file.
 */
public class TestngRunner {

    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        List<String> suiteFilesList = new ArrayList<>();
        suiteFilesList.add(FilePathConstant.TESTNG_FILE_PATH);
        testNG.setTestSuites(suiteFilesList);
        testNG.run();
    }
}
