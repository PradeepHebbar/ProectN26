package com.n26.main.wiring;

import com.jayway.restassured.response.Response;
import com.n26.main.core.BaseWiring;

/**
 * This class can be used to store delete product response and also get the details out of it
 * @author Pradeep
 */
public class DeleteProductWiring extends BaseWiring{
    
    public DeleteProductWiring(Response response){
        super(response);
    }

    /**
     * 
     * @return product name from the response
     */
    public String getProductName(){
        try{
            return response.getBody().jsonPath().get(ResponseTags.Name.responseKey);  
        }catch(Exception e){
            throw new AssertionError("Failed to get the product name from delete product API response: "+e);
        }
    }
    
    /**
     * 
     * @return product Id from the response
     */
    public String getProductId(){
        try{
            return response.getBody().jsonPath().get(ResponseTags.ID.responseKey).toString();  
        }catch(Exception e){
            throw new AssertionError("Failed to get the product ID from delete product API response: "+e);
        }
    }
    
    /**
     * 
     * @return Product Type from the response
     */
    public String getProductType(){
        try{
            return response.getBody().jsonPath().get(ResponseTags.TYPE.responseKey);  
        }catch(Exception e){
            throw new AssertionError("Failed to get the product type from delete product API response: "+e);
        }
    }
}
