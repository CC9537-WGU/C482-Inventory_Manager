package model;

/**
 *
 * @author Clint Chancellor - Inventory Management Program for WGU C482 Course.
 */
public abstract class Part {
    
    // Private members
    private int id, stock, min, max;
    private String name;
    double price;
    
    // Constructor
    public Part(int _id, String _name, double _price, int _stock, int _min, int _max ) {
        this.id = _id;
        this.name = _name;
        this.price = _price;
        this.stock = _stock;
        this.min = _min;
        this.max = _max;
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
}
