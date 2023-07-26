package oop2_project;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSingleton {
    private static DatabaseSingleton instance;
    private List<Product> products = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private ProductNotifier productNotifier = new ProductNotifier();
    
    private DatabaseSingleton(){
        
    }
    
    public static DatabaseSingleton getInstance(){
        if(instance == null){
            instance = new DatabaseSingleton();
        }
        return instance;
    }
    
    public void addProduct(Product product){
        products.add(product);
        productNotifier.addNewProduct(product);    
    }
    
    public void addCustomer(Customer customer){
        customers.add(customer);
    }
    public List<Product> getProducts(){
        return products;
    }
    public List<Customer> getCustomers() {
        return customers;
    }

    public ProductNotifier getProductNotifier() {
        return productNotifier;
    }
}
