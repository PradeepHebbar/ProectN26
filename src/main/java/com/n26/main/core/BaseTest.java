package com.n26.main.core;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.n26.main.exceptions.WebServicesException;
import com.n26.main.utilities.GetTestData;

public class BaseTest {
    
    private BaseClass base;
    private GetTestData getTestData;
    private int count = 1;
    
    /**
     * This is to read the config file(resources/config) and test data file(for Data Driven)
     * @return
     * @throws IOException
     * @throws WebServicesException
     */
    @DataProvider(name = "testData")
    public Object[][] data() throws IOException, WebServicesException {
        getTestData = new GetTestData();
        setBase(new BaseClass(this.getClass().getSimpleName()));
        return  getTestData.getExcelData(this.getClass().getSimpleName());
        
    }
    
    @BeforeMethod
    public void startTest() throws IOException, WebServicesException {
        getBase().startTest("Test"+count++);
    }
    
    @AfterMethod
    public void endTest(ITestResult result) throws IOException, WebServicesException {
       if(result.getStatus()==2){
           getBase().testFail(result.getThrowable());
       }
    }
    
    @AfterClass
    public void closeDriver() {
        getBase().close();
    }

    public BaseClass getBase() {
        return base;
    }

    public void setBase(BaseClass base) {
        this.base = base;
    }

}
