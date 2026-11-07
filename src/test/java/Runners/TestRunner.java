package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions", "hooks"},
        dryRun = true,
        plugin = {"pretty"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
