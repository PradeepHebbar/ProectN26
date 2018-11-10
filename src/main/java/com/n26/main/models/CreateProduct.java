package com.n26.main.models;

import java.util.Hashtable;

import com.n26.main.utilities.GenerateRandom;

/**
 * 
 * Pojo class to store and generate data to create a product
 * @author Pradeep
 */
public class CreateProduct {
    
    private String name;
    private String type;
    private float price;
    private float shipping;
    private String upc;
    private String description;
    private String manufacturer;
    private String model;
    private String url;
    private String image;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public float getShipping() {
        return shipping;
    }
    public void setShipping(float shipping) {
        this.shipping = shipping;
    }
    public String getUpc() {
        return upc;
    }
    public void setUpc(String upc) {
        this.upc = upc;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    
    //This method will set all required values with random data
    public void prePopulateData(){
       setName(GenerateRandom.randomString(5));
       setType(GenerateRandom.randomString(5));
       setPrice(GenerateRandom.randomNumber(3));
       setShipping(GenerateRandom.randomNumber(4));
       setUpc(GenerateRandom.randomString(10));
       setDescription(GenerateRandom.randomString(25));
       setManufacturer(GenerateRandom.randomString(15));
       setModel(GenerateRandom.randomString(5));
       setUrl(GenerateRandom.randomString(35));
       setImage(GenerateRandom.randomString(35));
    }
    
    //This methos will set the value based on the input provided
    public void populateDataWithExcel(Hashtable<String, String> testData){
        setName(testData.get("Name"));
        setType(testData.get("Type"));
        setPrice(Float.valueOf(testData.get("Price")));
        setShipping(Float.valueOf(testData.get("Shipping")));
        setUpc(testData.get("Upc"));
        setDescription(testData.get("Description"));
        setManufacturer(testData.get("Manufacturer"));
        setModel(testData.get("Model"));
        setUrl(testData.get("Url"));
        setImage(testData.get("Image"));
     }
    
    @Override
    public String toString() {
        return "{\"name\":" +"\"" +name +"\"" + ", \"type\":" +"\"" + type +"\"" + ", \"price\":" + price + ", \"shipping\":" + shipping
                + ", \"upc\":" +"\"" + upc +"\"" + ", \"description\":" +"\"" + description +"\"" + ", \"manufacturer\":" + "\"" +manufacturer +"\"" + ", \"model\":"
                +"\"" + model +"\"" + ", \"url\":" +"\"" + url +"\"" + ", \"image\":" +"\"" + image +"\"" + "}";
    }

}
