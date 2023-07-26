package oop2_project;

public interface Notify {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Product product);
}
