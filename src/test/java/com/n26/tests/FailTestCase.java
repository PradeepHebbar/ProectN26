package com.n26.tests;
import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.n26.main.Agent.ProductAgent;
import com.n26.main.core.BaseClass;
import com.n26.main.core.BaseTest;
import com.n26.main.exceptions.WebServicesException;
import com.n26.main.wiring.CreateProductWiring;

/**
 * Create a product and verify the response
 * @author Pradeep
 */
public class FailTestCase extends BaseTest{
    
    @Test(dataProvider = "testData")
    public void testFailTestCase(Hashtable<String, String> testData) throws IOException, WebServicesException {
        
        ProductAgent createProductAgent = new ProductAgent();
        
        //Create a product based on the inputs given in excel
        CreateProductWiring createProductWiring = createProductAgent.createProduct(testData);
        Assert.assertEquals(createProductWiring.getStatusCode(),201,"Status code should have been 201 for create product");
        BaseClass.log("stepinfo","Status code 201 validated sucessfully from the create product response");
        BaseClass.log("steppass","Product created sucessfully");
        
        //Verify the product name, type and product id from the response
        Assert.assertEquals(createProductWiring.getProductName(),testData.get("ProductNameToVerify"),"Product name suppose to be: "+createProductWiring.getProductName());
        BaseClass.log("stepinfo","Product name "+testData.get("Name")+" validated sucessfully from the create product response");
        BaseClass.log("steppass","Product response validated sucessfully");
    }
    
}
