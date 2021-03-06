/**
 *
 * @author Clint Chancellor - Inventory Management Program for WGU C482 Course.
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

public class Inventory_Manager extends Application {
    
    // Main starting fuction
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
    
    // Add some test data
    private void addTestData(Inventory _imInventory) {
        //Cylinder Block, Cylinder Head, Cam Shaft, Piston, Carb
        //Create InHouse Data
        Part rcb = new InHouse("Red Cylinder Block", 949.99, 10, 5, 100, 101);
        Part rch = new InHouse("Red Cylinder Head", 249.99, 7, 3, 80, 102);
        Part rcs = new InHouse("Red Cam Shaft", 649.99, 8, 9, 24, 103);
        Part rp = new InHouse("Red Piston", 749.99, 9, 4, 55, 104);
        Part rc = new InHouse("Red Carb", 549.99, 6, 6, 70, 105);
        //Add parts to Inventory
        _imInventory.addPart(rcb);
        _imInventory.addPart(rch);
        _imInventory.addPart(rcs);
        _imInventory.addPart(rp);
        _imInventory.addPart(rc);
        
        //Create Outsourced Data
        Part gcb = new Outsourced("Green Cylinder Block", 949.99, 10, 5, 100, "Cylindar Blocks R Us");
        Part gch = new Outsourced("Green Cylinder Head", 249.99, 7, 3, 80, "Cylindar Heads R Us");
        Part gcs = new Outsourced("Green Cam Shaft", 649.99, 8, 9, 24, "Cam Shafts R Us");
        Part gp = new Outsourced("Green Piston", 749.99, 9, 4, 55, "Pistons R Us");
        Part gc = new Outsourced("Green Carb", 549.99, 6, 6, 70, "Carbs R Us");
        //Add parts to Inventory
        _imInventory.addPart(gcb);
        _imInventory.addPart(gch);
        _imInventory.addPart(gcs);
        _imInventory.addPart(gp);
        _imInventory.addPart(gc);
        
        //Create Products
        Product ge = new Product("Green Engine", 2499.99, 20, 5, 100);
        ge.addAssociatedPart(gcb);
        ge.addAssociatedPart(gch);
        ge.addAssociatedPart(gcs);
        ge.addAssociatedPart(gp);
        ge.addAssociatedPart(gc);
        
        Product re = new Product("Red Engine", 2299.99, 14, 5, 100);
        re.addAssociatedPart(rcb);
        re.addAssociatedPart(rch);
        re.addAssociatedPart(rcs);
        re.addAssociatedPart(rp);
        re.addAssociatedPart(rc);
        
        Product gre = new Product("Green Red Engine", 2399.99, 8, 5, 100);
        gre.addAssociatedPart(gcb);
        gre.addAssociatedPart(rch);
        gre.addAssociatedPart(gcs);
        gre.addAssociatedPart(rp);
        gre.addAssociatedPart(gc);

        Product rge = new Product("Red Green Engine", 2349.99, 6, 5, 100);
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

    // Main Function
    public static void main(String[] args) {
        launch(args);
    }
    
}
