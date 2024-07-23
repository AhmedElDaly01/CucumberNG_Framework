package Listeners;

import Utilities.logsUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ITestResultListenerClass implements ITestListener {
    public void onTestStart(ITestResult result) {
        logsUtils.info("Test Case " + result.getName() + " started");
    }

    public void onTestSuccess(ITestResult result) {
        logsUtils.info("Test Case " + result.getName() + " passed");
    }
    public void onTestSkipped(ITestResult result) {
        logsUtils.info("Test Case " + result.getName() + " skipped");
    }

}
