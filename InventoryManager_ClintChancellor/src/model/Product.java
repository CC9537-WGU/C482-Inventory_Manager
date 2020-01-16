/**
 *
 * @author Clint Chancellor - Inventory Management Program for WGU C482 Course.
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    
    // Private Members
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id, stock, min, max;
    private String name;
    double price;
    
    // Class Variables
    static int productCount = 10000;
       
    // Constructor - excludes id and autoincrements to next id.
    public Product(String _name, double _price, int _stock, int _min, int _max, ObservableList<Part> _associatedParts) {
        this.id = getNextProductId();
        this.name = _name;
        this.price = _price;
        this.stock = _stock;
        this.min = _min;
        this.max = _max;
        this.associatedParts = _associatedParts;
        productCount++;
    }
    
    // Overloaded Constructor - excludes associtated parts
    public Product(String _name, double _price, int _stock, int _min, int _max) {
        this.id = getNextProductId();
        this.name = _name;
        this.price = _price;
        this.stock = _stock;
        this.min = _min;
        this.max = _max;
        productCount++;
    }
    
    // Setters for all private members
    public void setId(int _id) {
        this.id = _id;
    }
    
    public void setName(String _name) {
        this.name = _name;
    }
    
    public void setPrice(double _price) {
        this.price = _price;
    }
    
    public void setStock(int _stock) {
        this.stock = _stock;
    }
    
    public void setMin(int _min) {
        this.min = _min;
    }
    
    public void setMax(int _max) {
        this.max = _max;
    }
    
    // Getters for all private members
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getStock() {
        return stock;
    }
    
    public int getMin() {
        return min;
    }
    
    public int getMax() {
        return max;
    }
    
    // Public Methods
    public void addAssociatedPart(Part _selectedAssociatedPart) {
        associatedParts.add(_selectedAssociatedPart);
    }
    
    public boolean deleteAssociatedPart(Part _selectedAssociatedPart) {
        return associatedParts.remove(_selectedAssociatedPart);
    }
    
    public void setAssociatedParts(ObservableList<Part> _associatedParts) {
        this.associatedParts = _associatedParts;
    }
    
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
    
    // Class Methods
    public static int getNextProductId() {
        return productCount;
    }
    
}
