/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory_manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import model.Inventory;
import model.Part;
import model.Product;
import model.InHouse;
import model.Outsourced;

/**
 *
 * @author Clint
 */
public class Inventory_Manager extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Inventory imInventory = new Inventory();
        addTestData(imInventory);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainScreen.fxml"));
        controller.MainScreenController mainScreenController = new controller.MainScreenController(imInventory);
        loader.setController(mainScreenController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    private void addTestData(Inventory _imInventory) {
        //Cylinder Block, Cylinder Head, Cam Shaft, Piston, Carb
        //Create InHouse Data
        Part rcb = new InHouse(1, "Red Cylinder Block", 949.99, 10, 5, 100, 101);
        Part rch = new InHouse(2, "Red Cylinder Head", 249.99, 7, 3, 80, 102);
        Part rcs = new InHouse(3, "Red Cam Shaft", 649.99, 8, 9, 24, 103);
        Part rp = new InHouse(4, "Red Piston", 749.99, 9, 4, 55, 104);
        Part rc = new InHouse(5, "Red Carb", 549.99, 6, 6, 70, 105);
        //Add parts to Inventory
        _imInventory.addPart(rcb);
        _imInventory.addPart(rch);
        _imInventory.addPart(rcs);
        _imInventory.addPart(rp);
        _imInventory.addPart(rc);
        
        //Create Outsourced Data
        Part gcb = new Outsourced(11, "Green Cylinder Block", 949.99, 10, 5, 100, "Cylindar Blocks R Us");
        Part gch = new Outsourced(12, "Green Cylinder Head", 249.99, 7, 3, 80, "Cylindar Heads R Us");
        Part gcs = new Outsourced(13, "Green Cam Shaft", 649.99, 8, 9, 24, "Cam Shafts R Us");
        Part gp = new Outsourced(14, "Green Piston", 749.99, 9, 4, 55, "Pistons R Us");
        Part gc = new Outsourced(15, "Green Carb", 549.99, 6, 6, 70, "Carbs R Us");
        //Add parts to Inventory
        _imInventory.addPart(gcb);
        _imInventory.addPart(gch);
        _imInventory.addPart(gcs);
        _imInventory.addPart(gp);
        _imInventory.addPart(gc);
        
        //Create Products
        Product ge = new Product(100, "Green Engine", 2499.99, 20, 5, 100);
        ge.addAssociatedPart(gcb);
        ge.addAssociatedPart(gch);
        ge.addAssociatedPart(gcs);
        ge.addAssociatedPart(gp);
        ge.addAssociatedPart(gc);
        
        Product re = new Product(110, "Red Engine", 2299.99, 14, 5, 100);
        re.addAssociatedPart(rcb);
        re.addAssociatedPart(rch);
        re.addAssociatedPart(rcs);
        re.addAssociatedPart(rp);
        re.addAssociatedPart(rc);
        
        Product gre = new Product(120, "Green Red Engine", 2399.99, 8, 5, 100);
        gre.addAssociatedPart(gcb);
        gre.addAssociatedPart(rch);
        gre.addAssociatedPart(gcs);
        gre.addAssociatedPart(rp);
        gre.addAssociatedPart(gc);

        Product rge = new Product(130, "Red Green Engine", 2349.99, 6, 5, 100);
        rge.addAssociatedPart(rcb);
        rge.addAssociatedPart(gch);
        rge.addAssociatedPart(rcs);
        rge.addAssociatedPart(gp);
        rge.addAssociatedPart(rc);
        
        //Add products to Inventory
        _imInventory.addProduct(ge);
        _imInventory.addProduct(re);
        _imInventory.addProduct(gre);
        _imInventory.addProduct(rge);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
