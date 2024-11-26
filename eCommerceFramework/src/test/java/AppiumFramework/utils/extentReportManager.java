package AppiumFramework.utils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class extentReportManager {
	
    static ExtentReports extent;
	
    public static ExtentReports setUpExtentReports() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/extent-report.html");
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Test Report");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "Raj");
        return extent;
    }

}
