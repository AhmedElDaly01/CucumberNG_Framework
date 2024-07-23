package Tests;

import DriverFactory.DriverFactory;
import Utilities.DataUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.setupDriver;

public class BaseTest {
    @BeforeTest
    public void setUp() throws IOException {

        setupDriver(DataUtils
                .getDataFromProperties("browserName"));

        getDriver()
                .get(DataUtils
                        .getDataFromProperties("Base_URL"));

        getDriver().manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterTest
    public void Quit() {
        DriverFactory.Quit();
    }
}
