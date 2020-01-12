package model;

/**
 *
 * @author Clint Chancellor - Inventory Management Program for WGU C482 Course.
 */
public class Outsourced extends Part {
    
    // Private Members
    private String companyName;
    
    // Constructor
    public Outsourced(int _id, String _name, double _price, int _stock, int _min, int _max, String _companyName) {
        super(_id, _name, _price, _stock, _min, _max);
        this.companyName = _companyName;
    }
    
    // Overloaded Constructor
    public Outsourced(String _name, double _price, int _stock, int _min, int _max, String _companyName) {
        super(_name, _price, _stock, _min, _max);
        this.companyName = _companyName;
    }
    
    // Setters for all private members
    public void setCompanyName(String _companyName) {
        this.companyName = _companyName;
    }
    
    // Getters for all private members
    public String getCompanyName() {
        return companyName;
    }
}
