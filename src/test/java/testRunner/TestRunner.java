package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "Tests", // Ensure this points to your step definitions package
        plugin = {"pretty",
                "html:target/cucumber.html",
                "json:target/cucumber.json",
                "junit:target/cukes.xml",
                "rerun:target/rerun.txt"}
        //   , tags = "@FeatureOne or @FeatureThree"

)
public class TestRunner extends AbstractTestNGCucumberTests {
}
