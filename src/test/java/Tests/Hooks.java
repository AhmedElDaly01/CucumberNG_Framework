package Tests;

import DriverFactory.DriverFactory;
import Utilities.DataUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.setupDriver;

public class Hooks {


    @Before
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


    @After
    public void Quit(Scenario sc) {

        if (sc.isFailed()) {
            saveScreenshotAsFile(sc, "Fail", "Cucumber_Screenshots");
        }
//      else {saveScreenshotAsFile(sc, "PASS", "passed");}

        DriverFactory.Quit();

    }

    private void saveScreenshotAsFile(Scenario sc, String screenshotName, String status) {
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File srcFile = ts.getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("dd-MM-yyyy#HH-mm-ss").format(new Date());
        String scenarioName = sc.getName().replaceAll("[^a-zA-Z0-9]", "_");
        File destFile = new File("test-outputs/" + status + "/" + screenshotName + "_" + scenarioName + "_" + timestamp + ".png");

        try {
            FileUtils.copyFile(srcFile, destFile);
            // Attach the screenshot to the scenario
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            sc.attach(screenshot, "image/png", screenshotName + " - " + scenarioName + " - " + timestamp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void createDirectoriesIfNotExist() {
//        //  new File("src/test/resources/cucumberScreenshots/passed").mkdirs();
//        new File("test-outputs/BugsCucumber").mkdirs();}
}
