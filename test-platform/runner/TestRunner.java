package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"features/createUser.feature", 
                //     "features/modifyUser.feature", 
                //     "features/deleteUser.feature", 
                //     "features/feature4.feature", 
                //     "features/feature5.feature"
                },

        glue = "stepDefinitions",
        plugin = {"pretty",
                "reports.ExtentStepListener"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {}