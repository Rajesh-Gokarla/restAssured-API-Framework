package reports;

public class StepLogger {

    public static void pass(String step) {
        if (ExtentTestManager.getScenario() != null) {
            ExtentTestManager.getScenario().pass(step);
        }
    }

    public static void fail(String step, Throwable error) {
        if (ExtentTestManager.getScenario() != null) {
            ExtentTestManager.getScenario()
                    .fail(step)
                    .fail(error);
        }
    }
}