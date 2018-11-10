package com.n26.main.Agent;

import java.io.IOException;
import java.util.Hashtable;

import javax.xml.ws.WebServiceException;

import org.json.JSONObject;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.n26.main.core.BaseAgent;
import com.n26.main.core.BaseClass;
import com.n26.main.exceptions.WebServicesException;
import com.n26.main.models.CreateProduct;
import com.n26.main.utilities.GenerateData;
import com.n26.main.wiring.CreateProductWiring;
import com.n26.main.wiring.DeleteProductWiring;
import com.n26.main.wiring.EditProductWiring;
import com.n26.main.wiring.GetProductWiring;

/**
 * This class has methods which calls the respective endpoints and store the responce
 * to respective wiring class
 * @author Pradeep
 */
public class ProductAgent extends BaseAgent{
    Response response = null;
    
    /**
     * Constructor to initilize the global data
     * @param configData
     * @throws WebServicesException 
     */
    public ProductAgent() throws WebServicesException{
        try {
            setupProps();
        } catch (IOException | WebServicesException e) {
            BaseClass.log("stepfail","Failed to read congif file");
            throw new WebServiceException("Failed to read congif file",e);
        }
    }
    /**
     * Method to create a product
     * @param testData
     * @return
     * @throws WebServicesException 
     */
    public CreateProductWiring createProduct(Hashtable<String, String> testData) throws WebServicesException{
        CreateProduct createProduct = new CreateProduct();
        createProduct.populateDataWithExcel(testData);
        //Generate json body
        JSONObject bodyData = GenerateData.cereateProductData(createProduct.getName(), createProduct.getType(), createProduct.getPrice(), createProduct.getShipping(), 
                createProduct.getUpc(), createProduct.getDescription(), createProduct.getManufacturer(), createProduct.getModel(), createProduct.getUrl(), createProduct.getImage());
        //Generate URI
        String URI = getGlobalData().getProperty("rootURI").toString()+"/products";
        try {
            BaseClass.log("stepinfo","Calling post request to create an product");
            BaseClass.log("stepinfo","URI is : "+URI);
            BaseClass.log("stepinfo","Body is: "+bodyData.toString());
            response = RestAssured.given().contentType(getGlobalData().getProperty("headerTypec").toString()).body(bodyData.toString())
                    .when().post(URI);
            return new CreateProductWiring(response);
        } catch (Exception e) {
            BaseClass.log("stepfail","Failed to create a product");
            throw new WebServiceException("Failed to create a product",e);
        }
    }
    
    /**
     * Get the product details based on product Id
     * @param productId
     * @return
     * @throws WebServicesException 
     */
    public GetProductWiring getProduct(String productId) throws WebServicesException{
        //Generate URI
        String URI = getGlobalData().getProperty("rootURI").toString()+"/products/"+productId;
        try {
            BaseClass.log("stepinfo","Calling Get request to get the product by product Id: "+productId);
            BaseClass.log("stepinfo","URI is : "+URI);
            response = RestAssured.given().contentType(getGlobalData().getProperty("headerTypec").toString()).when()
                    .get(URI);
            return new GetProductWiring(response);
        } catch (Exception e) {
            BaseClass.log("stepfail","Failed to get the product details by product Id: "+productId);
            throw new WebServiceException("Failed to get the product details by product Id: "+productId+"\n"+ e);
        }
    }
    
    /**
     * Edit product details based on product Id
     * @param productId
     * @param testData
     * @return
     * @throws WebServicesException 
     */
    public EditProductWiring editProduct(String productId, Hashtable<String, String> testData) throws WebServicesException{
        //Generate json body
        JSONObject bodyData = GenerateData.editProductData(testData.get("NewName"), testData.get("NewType"), testData.get("NewPrice"), testData.get("NewShipping"), 
                testData.get("NewUpc"), testData.get("NewDescriptior"), testData.get("NewManufacturer"), testData.get("NewModel"), testData.get("NewUrl"), testData.get("NewImage"));
        //Generate URI
        String URI = getGlobalData().getProperty("rootURI").toString()+"/products/"+productId;
        try {
            BaseClass.log("stepinfo","Calling patch request to edit product details by product Id: "+productId);
            BaseClass.log("stepinfo","URI is: "+URI);
            BaseClass.log("stepinfo","Body is: "+bodyData.toString());
            response = RestAssured.given().contentType(getGlobalData().getProperty("headerTypec").toString()).body(bodyData.toString())
                    .when().patch(URI);
            return new EditProductWiring(response);
        } catch (Exception e) {
            BaseClass.log("stepfail","Failed to edit product details by product Id: "+productId);
            throw new WebServiceException("Failed to edit product details by product Id: "+productId+"\n"+ e);
        }
    }
    
    /**
     * Delete the product by product Id
     * @param productId
     * @return
     * @throws WebServicesException 
     */
    public DeleteProductWiring deleteProduct(String productId) throws WebServicesException{
        String URI = getGlobalData().getProperty("rootURI").toString()+"/products/"+productId;
        try {
            BaseClass.log("stepinfo","Calling Delete request to delete a product by product Id "+productId);
            BaseClass.log("stepinfo","URI is : "+URI);
            response = RestAssured.given().contentType(getGlobalData().getProperty("headerTypec").toString()).when()
                    .delete(URI);
            return new DeleteProductWiring(response);
        } catch (Exception e) {
            BaseClass.log("stepfail","Failed to delete a product by product Id: "+productId);
            throw new WebServiceException("Failed to delete a product by product Id: "+productId+"\n"+ e);
        }
    }

}
