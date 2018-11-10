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
import com.n26.main.wiring.EditProductWiring;

/**
 * Verify the patch API 
 * @author pkuma
 */
public class CreatePatchAndVerifyProduct extends BaseTest{
    
    @Test(dataProvider = "testData")
    public void testCreatePatchAndVerifyProduct(Hashtable<String, String> testData) throws IOException, WebServicesException {
        ProductAgent createProductAgent = new ProductAgent();
        CreateProductWiring createProductWiring = createProductAgent.createProduct(testData);
        Assert.assertEquals(createProductWiring.getStatusCode(),201,"Status code should have been 201 for create product");
        BaseClass.log("steppass","Product created sucessfully");
        
        //Get the product Id and verify the product name from the response
        String productId = createProductWiring.getProductId();
        Assert.assertEquals(createProductWiring.getProductName(),testData.get("Name"));
        BaseClass.log("stepinfo","Product name "+testData.get("Name")+" validated sucessfully from the get product response");
        Assert.assertTrue(productId!=null, "Product ID cannot be null");
        BaseClass.log("stepinfo","Product Id has been validated sucessfully from the create product response");
        
        //Edit the product details by Id with the data provided in excel
        EditProductWiring editProductWiring = createProductAgent.editProduct(productId, testData);
        Assert.assertEquals(editProductWiring.getStatusCode(),200,"Status code should have been 201 for edit product");
        BaseClass.log("stepinfo","Status code 201 validated sucessfully from the edit product response");
        BaseClass.log("steppass","Patch API has been validated sucessfully");
        
        //Validate the edited details from the response
        Assert.assertEquals(editProductWiring.getProductName(),testData.get("NewName"));
        BaseClass.log("stepinfo","Product name "+testData.get("NewName")+" validated sucessfully from the edit product response");
        Assert.assertEquals(editProductWiring.getProductType(),testData.get("NewType"));
        BaseClass.log("stepinfo","Product Type "+testData.get("NewType")+" validated sucessfully from the edit product response");
    }

}
