package model;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 *
 * @author Clint Chancellor - Inventory Management Program for WGU C482 Course.
 */
public class Inventory {
    
    // Private Members
    private final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private final ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    // Public Methods
    public void addPart(Part _newPart) {
        allParts.add(_newPart);
    }
    
    public void addProduct(Product _newProduct) {
        allProducts.add(_newProduct);
    }
    
    public Part lookupPart(int _partId) {
        for (Part part : getAllParts()) {
            if (part.getId() == _partId) {
                return part;
            }
        }
        return null;
    }
    
    public Product lookupProduct(int _productId) {
        for (Product product : getAllProducts()) {
            if (product.getId() == _productId) {
                return product;
            }
        }
        return null;
    }
    
    public ObservableList<Part> lookupPart(String _partName) {
        ObservableList<Part> matchingParts = FXCollections.observableArrayList();
        getAllParts().stream().filter((part) -> (part.getName().contains(_partName))).forEachOrdered((part) -> {
            matchingParts.add(part);
        });
        return matchingParts;
    }
    
    public ObservableList<Product> lookupProduct(String _productName) {
        ObservableList<Product> matchingProducts = FXCollections.observableArrayList();
        getAllProducts().stream().filter((product) -> (product.getName().contains(_productName))).forEachOrdered((product) -> {
            matchingProducts.add(product);
        });
        return matchingProducts;
    }
    
    public void updatePart(int _partId, Part _selectedPart) {
        allParts.set(_partId, _selectedPart);
    }
    
    public void updateProduct(int _productId, Product _selectedProduct) {
        allProducts.set(_productId, _selectedProduct);
    }
    
    public boolean deletePart(Part _selectedPart) {
        return allParts.remove(_selectedPart);
    }
    
    public boolean deleteProduct(Product _selectedProduct) {
        return allProducts.remove(_selectedProduct);
    }
    
    public ObservableList<Part> getAllParts() {
        return allParts;
    }
    
    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }
    
}
