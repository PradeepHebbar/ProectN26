
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
import com.n26.main.wiring.DeleteProductWiring;
import com.n26.main.wiring.GetProductWiring;
/**
 * 
 * Verify the delete API by deleting the created product
 * @author Pradeep
 */
public class CreateAndDeleteProduct  extends BaseTest{
    
    @Test(dataProvider = "testData")
    public void testCreateAndDeleteProduct(Hashtable<String, String> testData) throws IOException, WebServicesException {
        ProductAgent createProductAgent = new ProductAgent();
        CreateProductWiring createProductWiring = createProductAgent.createProduct(testData);
        Assert.assertEquals(createProductWiring.getStatusCode(),201,"Status code should have been 201 for create product");
        BaseClass.log("steppass","Product created sucessfully");
        
        //Verify the crated product by product Id  before deleting it
        GetProductWiring getProductWiring = createProductAgent.getProduct(createProductWiring.getProductId());
        Assert.assertEquals(getProductWiring.getStatusCode(),200,"Status code should have been 200, since product has been created");
        
        //Verify some details of the product
        Assert.assertEquals(getProductWiring.getProductName(),testData.get("Name"));
        BaseClass.log("stepinfo","Product name "+testData.get("Name")+" validated sucessfully from the get product response");
        Assert.assertEquals(getProductWiring.getProductType(),testData.get("Type"));
        BaseClass.log("stepinfo","Product Type "+testData.get("Type")+" validated sucessfully from the get product response");
        String productId = getProductWiring.getProductId();
        Assert.assertTrue(productId!=null, "Product ID cannot be null");
        BaseClass.log("stepinfo","Product Id has been validated sucessfully from the get product response");
       
        //Delete the product by product Id
        DeleteProductWiring deleteProductWiring = createProductAgent.deleteProduct(productId);
        Assert.assertEquals(deleteProductWiring.getStatusCode(),200);
        BaseClass.log("steppass","Product deleted sucessfully");
        
        //Try to get the product ehich is already deleted
        getProductWiring = createProductAgent.getProduct(productId);
        Assert.assertEquals(getProductWiring.getStatusCode(),404,"Status code should have been 404 for already deleted product");
    }

}
