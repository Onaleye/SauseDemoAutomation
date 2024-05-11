package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * @author Hitendra: ExtentManager class is used for Extent Report
 *
 */
public class ExtentManager {

    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    public static void setExtent() {
        //htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/ExtentReport/"+"MyReport_"+BaseClass.getCurrentTime()+".html");
        htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/ExtentReport/"+"MyReport.html");
        htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");///Users/seunonaleye/IdeaProjects/CreditCheckDecisioning/test-output/ExtentReport
        htmlReporter.config().setDocumentTitle("Automation Test Report");
        htmlReporter.config().setReportName("OrangeHRM Test Automation Report");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        extent.setSystemInfo("HostName", "MyHost");
        extent.setSystemInfo("ProjectName", "CreditCheckDecisioning");
        extent.setSystemInfo("Tester", "Olayinka Onaleye");
        extent.setSystemInfo("OS", "MACOS");
        extent.setSystemInfo("Browser", "Chrome");
    }
    public static void endReport() {
        extent.flush();
    }
}
