package com.jira.api.utils;

import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


/**
 * This class contains Listeners implementation.
 */
public class Listeners implements ITestListener, ISuiteListener {


    @Override
    public void onFinish(ITestContext Result) {
        System.out.println("*************************************************->TEST END<-**************************************************");

    }

    @Override
    public void onStart(ITestContext Result) {
        System.out.println("*************************************************->TEST STARTS FOR " + Result.getName() + "<-**************************************************");
    }

    @Override
    public void onTestFailure(ITestResult Result) {
        System.out.println("THE NAME OF THE TEST FAIL IS :" + Result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult Result) {
        System.out.println("THE NAME OF THE TEST SKIPPED IS :" + Result.getName());
    }

    @Override
    public void onTestStart(ITestResult Result) {
        System.out.println(Result.getName() + " test case started");
    }

    @Override
    public void onTestSuccess(ITestResult Result) {
        System.out.println("THE NAME OF THE TEST PASSED IS :" + Result.getName());
    }
}
