package com.n26.main.utilities;

import org.json.JSONException;
import org.json.JSONObject;


public class GenerateData {
    
    public static JSONObject cereateProductData(String name,String type, float price, float shipping, String upc, String description,
            String manufacturer, String model, String url, String image){
        try {
            JSONObject obj = new JSONObject();
            obj.put("name", name);
            obj.put("type", type);
            obj.put("price", price);
            obj.put("shipping", shipping);
            obj.put("upc", upc);
            obj.put("description", description);
            obj.put("manufacturer", manufacturer);
            obj.put("model", model);
            obj.put("url", url);
            obj.put("image", image);
            
            return obj;
        } catch (JSONException e) {
            throw new AssertionError("Failed to generate payload for create product", e);
        }
    }
    
    public static JSONObject editProductData(String name,String type, String price, String shipping, String upc, String description,
            String manufacturer, String model, String url, String image){
        try {
            JSONObject obj = new JSONObject();
            obj.put("name", name);
            obj.put("type", type);
            obj.put("price", Float.valueOf(price));
            obj.put("shipping", Float.valueOf(shipping));
            obj.put("upc", upc);
            obj.put("description", description);
            obj.put("manufacturer", manufacturer);
            obj.put("model", model);
            obj.put("url", url);
            obj.put("image", image);
            
            return obj;
        } catch (JSONException e) {
            throw new AssertionError("Failed to generate payload for edit product", e);
        }
        
       
    }

}
