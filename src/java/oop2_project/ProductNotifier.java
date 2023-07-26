 package oop2_project;

import java.util.ArrayList;
import java.util.List;

public class ProductNotifier implements Notify {
    private List<Observer> observers = new ArrayList<>();
    
    public void addObserver(Observer observer){
        observers.add(observer);
    }
    
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }
    
    public void notifyObservers(Product product){
        for (Observer observer : observers){
            observer.update(product);
        }
    }
    
    public void addNewProduct(Product product){
        notifyObservers(product);
    }

}
