package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_;
import io.cucumber.java.en.When;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static DriverFactory.DriverFactory.getDriver;


@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC01_ extends BaseTest {


    @Test(priority = 1)
    @When("")
    public void signupPage() throws IOException, InterruptedException {
        new P01_(getDriver())
        ;

    }


}
