package reports;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

    private static final ThreadLocal<ExtentTest> feature = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> scenario = new ThreadLocal<>();

    public static void setFeature(ExtentTest extentTest) {
        feature.set(extentTest);
    }

    public static ExtentTest getFeature() {
        return feature.get();
    }

    public static void setScenario(ExtentTest extentTest) {
        scenario.set(extentTest);
    }

    public static ExtentTest getScenario() {
        return scenario.get();
    }
}