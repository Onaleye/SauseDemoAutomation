/*package BaseClass;

import Utilities.ExtentManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static Properties prop;

    //Declare ThreadLocal Driver
    public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    @BeforeSuite(groups={"Smoke","Sanity","Regression"})
    public void loadConfigFile(){

        ExtentManager.setExtent();

        DOMConfigurator.configure("log4j.xml");

        prop = new Properties();
        try (InputStream ip = getClass().getResourceAsStream("/Configuration/Config.properties")) {
            if (ip != null) {
                prop.load(ip);
            } else {
                throw new FileNotFoundException("Config.properties not found in the classpath");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WebDriver getDriver() {
        //Get Driver from threadLocalmap
        return driver.get();
    }



    public static void launchApp(String browserName) {
     //   String browserName = prop.getProperty("browser");
        if (browserName.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--ignore-certificate-errors"); // Ignore SSL certificate errors
            driver.set(new ChromeDriver(options));
        } else if (browserName.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
        } else if (browserName.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver.set(new EdgeDriver());
        } else if (browserName.equalsIgnoreCase("Internet Explorer")) {
            WebDriverManager.iedriver().setup();
            driver.set(new InternetExplorerDriver());
        } else if (browserName.equalsIgnoreCase("Safari")) {
            WebDriverManager.safaridriver().setup();
            driver.set(new SafariDriver());
        }

        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("implicitWait")), TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("pageLoadTimeOut")), TimeUnit.SECONDS);
        getDriver().get(prop.getProperty("url"));
    }

    @AfterSuite
    public void afterSuite(){
        ExtentManager.endReport();
    }

    public void exitApp() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

}*/

package BaseClass;

import Utilities.ExtentManager;
import Utilities.EmailSender;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static Properties prop;

    //Declare ThreadLocal Driver
    public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    @BeforeSuite(groups={"Smoke","Sanity","Regression"})
    public void loadConfigFile(){

        ExtentManager.setExtent();

        DOMConfigurator.configure("log4j.xml");

        prop = new Properties();
        try (InputStream ip = getClass().getResourceAsStream("/Configuration/Config.properties")) {
            if (ip != null) {
                prop.load(ip);
            } else {
                throw new FileNotFoundException("Config.properties not found in the classpath");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WebDriver getDriver() {
        //Get Driver from threadLocalmap
        return driver.get();
    }

    public static void launchApp(String browserName) {
        //   String browserName = prop.getProperty("browser");
        if (browserName.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--ignore-certificate-errors"); // Ignore SSL certificate errors
            driver.set(new ChromeDriver(options));
        } else if (browserName.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
        } else if (browserName.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver.set(new EdgeDriver());
        } else if (browserName.equalsIgnoreCase("Internet Explorer")) {
            WebDriverManager.iedriver().setup();
            driver.set(new InternetExplorerDriver());
        } else if (browserName.equalsIgnoreCase("Safari")) {
            WebDriverManager.safaridriver().setup();
            driver.set(new SafariDriver());
        }

        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("implicitWait")), TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("pageLoadTimeOut")), TimeUnit.SECONDS);
        getDriver().get(prop.getProperty("url"));
    }

    @AfterSuite
    public void afterSuite() {
        ExtentManager.endReport();
        sendReportEmail();
    }

    public void exitApp() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

    public static void sendReportEmail() {
        try {
            String recipient = "olayinka.onaleye@mtn.com";
            String subject = "Test Automation Report";
            String body = "Please find the test automation report attached.";
            String reportFilePath = "/Users/seunonaleye/IdeaProjects/SauceDemoOlayinkaOnaleye/test-output/ExtentReport/MyReport.html";


            EmailSender.sendEmail(recipient, subject, body, reportFilePath);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}


