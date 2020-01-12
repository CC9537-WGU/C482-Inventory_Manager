package model;

/**
 *
 * @author Clint Chancellor - Inventory Management Program for WGU C482 Course.
 */
public class InHouse extends Part {
    
    // Private Members
    private int machineId;
    
    // Constructor
    public InHouse(int _id, String _name, double _price, int _stock, int _min, int _max, int _machineId) {
        super(_id, _name, _price, _stock, _min, _max);
        this.machineId = _machineId;
    }
    
    // Overloaded Constructor
    public InHouse(String _name, double _price, int _stock, int _min, int _max, int _machineId) {
        super(_name, _price, _stock, _min, _max);
        this.machineId = _machineId;
    }
    
    // Setters for all private members
    public void setMachineId(int _machineId) {
        this.machineId = _machineId;
    }
    
    // Getters for all private members
    public int getMachineId() {
        return machineId;
    }
}
