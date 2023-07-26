package oop2_project;

public class CustomerObserver implements Observer{
    private String name;
    
    public CustomerObserver(String name){
        this.name = name;
    }
    @Override
    public void update(Product product){
        System.out.println(name + ", a new product is available: " + product.getName());
        
    }
}