package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    private static ExtentReports extent;

    private ExtentManager() {
        // prevent instantiation
    }

    public static synchronized ExtentReports getExtent() {

        if (extent == null) {

            String projectRoot = System.getProperty("user.dir");

            // ================= TIMESTAMPED REPORT FOLDER =================
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")
                    .format(new Date());

            String reportDirPath =
                    projectRoot + "/reports/" + timestamp;

            File reportDir = new File(reportDirPath);
            reportDir.mkdirs();

            // ================= HTML REPORT =================
            ExtentSparkReporter htmlReporter =
                    new ExtentSparkReporter(
                            reportDirPath + "/extent-report.html"
                    );

            htmlReporter.config().setReportName("API Automation Report");
            htmlReporter.config().setDocumentTitle("BDD API Test Results");

            // ================= JSON REPORT =================
            JsonFormatter jsonReporter =
                    new JsonFormatter(
                            reportDirPath + "/extent-report.json"
                    );

            // ================= EXTENT =================
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter, jsonReporter);

            // Optional system info (recommended)
            extent.setSystemInfo("Framework", "API BDD Automation");
            extent.setSystemInfo("Runner", "Cucumber + TestNG");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java", System.getProperty("java.version"));
        }

        return extent;
    }
}