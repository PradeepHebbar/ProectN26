package com.n26.main.core;

import javax.xml.ws.WebServiceException;

import com.jayway.restassured.response.Response;

/**
 * This is the base wiring class, each wiring class has toextend this call
 * This class will be having all the required tags in ResponseTag enum
 * This call will be having all commom menthods
 * @author Pradeep
 */
public class BaseWiring {
    
    protected Response response;
    
    public enum ResponseTags {
        Name("name"),
        ID("id"),
        TYPE("type");
        
        public final String responseKey;

        private ResponseTags(String tag) {
            this.responseKey = tag;
        }
    }
    
    /**
     * Construcot to initilize the response object
     * @param response
     */
    public BaseWiring(Response response){
        this.response = response;
    }

    
    /**
     * 
     * @return status code from the response
     */
    public int getStatusCode(){
        try{
            return response.getStatusCode();    
        }catch(Exception e){
            throw new WebServiceException("Failed to get the status code from the API response: "+e);
        }
    }
    
}
