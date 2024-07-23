package Listeners;

import Utilities.Utility;
import Utilities.logsUtils;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import static DriverFactory.DriverFactory.getDriver;

public class IInvokedMethodListenerClass implements IInvokedMethodListener {
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        if (testResult.getStatus()==ITestResult.FAILURE) {
            logsUtils.info("Test Case " + testResult.getName() + " fails");
            Utility.takeScreenshot(getDriver(), testResult.getName());
        }

        }
}
