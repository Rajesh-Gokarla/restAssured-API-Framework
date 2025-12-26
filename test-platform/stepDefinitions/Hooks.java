package stepDefinitions;

import io.cucumber.java.*;
import reports.ExtentManager;
import reports.ExtentTestManager;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {

        String featureName =
                scenario.getUri().getPath()
                        .substring(scenario.getUri().getPath().lastIndexOf("/") + 1);

        // Create feature if not exists
        if (ExtentTestManager.getFeature() == null ||
                !ExtentTestManager.getFeature().getModel().getName().equals(featureName)) {

            ExtentTestManager.setFeature(
                    ExtentManager.getExtent()
                            .createTest(featureName)
            );
        }

        // Create scenario under feature
        ExtentTestManager.setScenario(
                ExtentTestManager.getFeature()
                        .createNode(scenario.getName())
        );
    }

    @After
    public void afterScenario(Scenario scenario) {

        if (scenario.isFailed()) {
            ExtentTestManager.getScenario().fail("Scenario Failed");
        }

        ExtentManager.getExtent().flush();
    }

    @AfterAll
    public static void afterAll() {
        var report = new java.io.File(
                System.getProperty("user.dir") +
                        "/reports/extent-report.html");

        System.out.println("\n================ EXTENT REPORT ================");
        System.out.println(report.toURI());
        System.out.println("===============================================\n");
    }
}