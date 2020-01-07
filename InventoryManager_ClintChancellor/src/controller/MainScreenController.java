/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

/**
 * FXML Controller class
 *
 * @author Clint
 */
public class MainScreenController implements Initializable {
    
    private Stage stage;
    private Parent parent;
    private Scene scene;
    private boolean partSearched, productSearched = false;
    
    private ObservableList<Part> partInventory = FXCollections.observableArrayList();
    private ObservableList<Product> productInventory = FXCollections.observableArrayList();
    private ObservableList<Part> partInventorySearch = FXCollections.observableArrayList();
    private ObservableList<Product> productInventorySearch = FXCollections.observableArrayList();
    
    Inventory imInventory;
    

    @FXML
    private AnchorPane im_main;
    @FXML
    private Button btnExit;
    @FXML
    private TextField tbPartSearch;
    @FXML
    private Button btnPartSearch;
    @FXML
    private TableView<Part> tvPartsTable;
    @FXML
    private TableColumn<Part, Integer> tvcPartId;
    @FXML
    private TableColumn<Part, String> tvcPartName;
    @FXML
    private TableColumn<Part, Integer> tvcPartStock;
    @FXML
    private TableColumn<Part, Double> tvcPartPrice;
    @FXML
    private Button btnPartAdd;
    @FXML
    private Button btnPartModify;
    @FXML
    private Button btnPartDelete;
    @FXML
    private TextField tbProductSearch;
    @FXML
    private Button btnProductSearch;
    @FXML
    private TableView<Product> tvProductsTable;
    @FXML
    private TableColumn<Part, Integer> tvcProductId;
    @FXML
    private TableColumn<Part, String> tvcProductName;
    @FXML
    private TableColumn<Part, Integer> tvcProductStock;
    @FXML
    private TableColumn<Part, Double> tvcProductPrice;
    @FXML
    private Button btnProductAdd;
    @FXML
    private Button btnProductModify;
    @FXML
    private Button btnProductDelete;

    public MainScreenController(Inventory _imInventory) {
        System.out.println("In MainScreenController");
        imInventory = _imInventory;
        partInventory = imInventory.getAllParts();
        productInventory = imInventory.getAllProducts();

    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("In initialize");
        
        //Table and columns for Parts
        tvcPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tvcPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tvcPartStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tvcPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        tvPartsTable.setItems(partInventory);
        tvPartsTable.refresh();
        
        //Table and columns for Products
        tvcProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tvcProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tvcProductStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tvcProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        tvProductsTable.setItems(productInventory);
        tvProductsTable.refresh();
        
    }    

    @FXML
    private void onActionExit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void onActionPartSearch(ActionEvent event) {
        if (!partSearched) {
            partSearched = true;
            //ToDo: Implement Search Functionality
            System.out.println("ToDo: Implement Search Functionality");
            btnPartSearch.setText("Clear");
        } else
        {
            partSearched = false;
            tbPartSearch.setText("");
            btnPartSearch.setText("Search");
        }
        
    }

    @FXML
    private void onActionPartAdd(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddPart.fxml"));
        controller.AddPartController addPartController = new controller.AddPartController(imInventory);
        loader.setController(addPartController);
        parent = loader.load();
        scene = new Scene(parent);
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void onActionPartModify(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModifyPart.fxml"));
        controller.ModifyPartController modifyPartController = new controller.ModifyPartController(imInventory);
        loader.setController(modifyPartController);
        parent = loader.load();
        scene = new Scene(parent);
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void onActionPartDelete(ActionEvent event) {
        Alert confirmDelete = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete this part?", ButtonType.YES, ButtonType.NO);
        confirmDelete.setTitle("Confirm Part Deletion");
        confirmDelete.showAndWait().filter(response -> response == ButtonType.YES).ifPresent(response -> {
            Part selectedPart = (Part) tvPartsTable.getSelectionModel().getSelectedItem();
            //ToDo: Implement DeletePart
            System.out.println("ToDo: Implement DeletePart");
        });
    }

    @FXML
    private void onActionProductSearch(ActionEvent event) {
        if (!productSearched) {
            productSearched = true;
            //ToDo: Implement Search Functionality
            System.out.println("ToDo: Implement Search Functionality");
            btnProductSearch.setText("Clear");
        } else
        {
            productSearched = false;
            tbProductSearch.setText("");
            btnProductSearch.setText("Search");
        }
    }

    @FXML
    private void onActionProductAdd(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddProduct.fxml"));
        controller.AddProductController addProductController = new controller.AddProductController(imInventory);
        loader.setController(addProductController);
        parent = loader.load();
        scene = new Scene(parent);
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void onActionProductModify(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModifyProduct.fxml"));
        controller.ModifyProductController modifyProductController = new controller.ModifyProductController(imInventory);
        loader.setController(modifyProductController);
        parent = loader.load();
        scene = new Scene(parent);
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void onActionProductDelete(ActionEvent event) {
        Alert confirmDelete = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete this product?", ButtonType.YES, ButtonType.NO);
        confirmDelete.setTitle("Confirm Product Deletion");
        confirmDelete.showAndWait().filter(response -> response == ButtonType.YES).ifPresent(response -> {
            Product selectedProduct = (Product) tvProductsTable.getSelectionModel().getSelectedItem();
            //ToDo: Implement DeletePart
            System.out.println("ToDo: Implement DeletePart");
        });
    }
    
}
