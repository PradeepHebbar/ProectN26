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
import com.n26.main.wiring.GetProductWiring;

/**
 * Verify the get product API
 * @author Pradeep
 */
public class CreateAndGetProduct  extends BaseTest{
    
    @Test(dataProvider = "testData")
    public void testCreateAndGetProduct(Hashtable<String, String> testData) throws IOException, WebServicesException {
        ProductAgent createProductAgent = new ProductAgent();
        CreateProductWiring createProductWiring = createProductAgent.createProduct(testData);
        Assert.assertEquals(createProductWiring.getStatusCode(),201,"Status code should have been 201 for create product");
        BaseClass.log("steppass","Product created sucessfully");
        
        //Verify the get product by calling get request with valid product Id
        GetProductWiring getProductWiring = createProductAgent.getProduct(createProductWiring.getProductId());
        Assert.assertEquals(getProductWiring.getStatusCode(),200,"Status code should have been 200 for get product");
        Assert.assertEquals(getProductWiring.getProductName(),testData.get("Name"));
        BaseClass.log("stepinfo","Product name "+testData.get("Name")+" validated sucessfully from the get product response");
        Assert.assertTrue(getProductWiring.getProductId()!=null, "Product ID cannot be null");
        BaseClass.log("stepinfo","Product Id has been validated sucessfully from the get product response");
        BaseClass.log("steppass","Get product by product Id validated sucessfully");

        //Verify the get product by calling get request with in valid product Id
        getProductWiring = createProductAgent.getProduct("randomId");
        Assert.assertEquals(getProductWiring.getStatusCode(),404,"Status code should have been 404 for invalid product ID");
    }

}
