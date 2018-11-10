package com.n26.main.core;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.n26.main.exceptions.WebServicesException;

public class BaseClass {
    private ExtentHtmlReporter htmlReporter;
    private static ExtentTest test;
    private ExtentReports extent;

    public BaseClass(String TestName) throws IOException, WebServicesException {
        setupExtent(TestName);
    }

    /**
     * Method to log the details in to report
     * 
     * @param logtype
     * @param message
     * @throws WebServicesException
     */
    public static void log(String logtype, String message) throws WebServicesException {

        switch (logtype.toLowerCase()) {
        case "stepinfo":
            test.log(Status.INFO, message);
            System.out.println(message);
            break;
        case "steppass":
            test.log(Status.PASS, message);
            System.out.println(message);
            break;
        case "stepfail":
            test.log(Status.FAIL, message);
            System.out.println(message);
            break;
        default:
            throw new WebServicesException("Invalid log type: " + logtype);
        }

    }

    /**
     * Start the extent report instance
     * @param TestName
     * @throws IOException
     * @throws WebServicesException
     */
    public void setupExtent(String TestName) throws IOException, WebServicesException {
        htmlReporter = new ExtentHtmlReporter("reports\\" + TestName + ".html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    /**
     * Start the extent report test
     * @param TestName
     * @throws IOException
     * @throws WebServicesException
     */
    public void startTest(String TestName) throws IOException, WebServicesException {
        test = extent.createTest(TestName);
        test.assignAuthor("Pradeep");
    }
    
    public void testFail(Throwable throwable) throws IOException, WebServicesException {
        test.log(Status.FAIL, throwable);
    }
    
    /*public void testPass(String TestName) throws IOException, WebServicesException {
        test = extent.createTest(TestName);
        test.assignAuthor("Pradeep");
    }*/

    /**
     * Store the extent report
     */
    public void close() {
        extent.attachReporter(htmlReporter);
        extent.flush();
        htmlReporter.flush();
    }

}
