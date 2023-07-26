package oop2_project;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int price;
    private String name;
    String description;
    
    public Product( String name,String description, int price){
        this.price = price;
        this.name = name;
        this.description = description;
    }
    //gETTERS and setters
    public int getprice(){
        return price;
    }
    public String getName(){
        return name;
    }

    public String getDescription() {
        return description;
    }
     
}
