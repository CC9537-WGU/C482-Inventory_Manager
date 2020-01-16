/**
 *
 * @author Clint Chancellor - Inventory Management Program for WGU C482 Course.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

public class AddProductController implements Initializable {
    
    private Stage stage;
    private Parent parent;
    private Scene scene;
    Inventory imInventory;
    Product newProduct;
    boolean partSearched;
    
    private ObservableList<Part> partInventory = FXCollections.observableArrayList();
    private ObservableList<Part> partInventorySearch = FXCollections.observableArrayList();
    private ObservableList<Part> productParts = FXCollections.observableArrayList();


@FXML
    private TextField tbProductId;

    @FXML
    private TextField tbProductName;

    @FXML
    private TextField tbProductInv;

    @FXML
    private TextField tbProductPriceCost;

    @FXML
    private TextField tbProductMax;

    @FXML
    private TextField tbProductMin;

    @FXML
    private TextField tbSeachBox;

    @FXML
    private Button btnSearch;

    @FXML
    private TableView<Part> tvPartsTable;

    @FXML
    private TableColumn<Part, Integer> tvcPartsId;

    @FXML
    private TableColumn<Part, String> tvcPartsName;

    @FXML
    private TableColumn<Part, Integer> tvcPartsInv;

    @FXML
    private TableColumn<Part, Double> tvcPartsCost;

    @FXML
    private Button btnAddPart;

    @FXML
    private TableView<Part> tvProductPartsTable;

    @FXML
    private TableColumn<Part, Integer> tvcProductsPartsId;

    @FXML
    private TableColumn<Part, String> tvcProductsPartsName;

    @FXML
    private TableColumn<Part, Integer> tvcProductsPartsInv;

    @FXML
    private TableColumn<Part, Double> tvcProductsPartsPrice;

    @FXML
    private Button btnDeletePartFromProduct;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    AddProductController(Inventory _imInventory) {
        imInventory = _imInventory;
        partInventory = imInventory.getAllParts();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tbProductId.setText(Integer.toString(newProduct.getNextProductId()));
        
        //Table and columns for Parts
        tvcPartsId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tvcPartsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tvcPartsInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tvcPartsCost.setCellValueFactory(new PropertyValueFactory<>("price"));

        tvPartsTable.setItems(partInventory);
        tvPartsTable.refresh();
        
        //Table and columns for PartsInProduct
        tvcProductsPartsId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tvcProductsPartsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tvcProductsPartsInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tvcProductsPartsPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tvProductPartsTable.setItems(productParts);
        tvProductPartsTable.refresh();
    }    

    @FXML
    private void onActionPartSearch(ActionEvent event) {
        if (!partSearched) {
            partSearched = true;
            partInventorySearch = imInventory.lookupPart(tbSeachBox.getText());
            tvPartsTable.setItems(partInventorySearch);
            tvPartsTable.refresh();
            btnSearch.setText("Clear");
        } else {
            partSearched = false;
            tbSeachBox.setText("");
            btnSearch.setText("Search");
            tvPartsTable.setItems(partInventory);
            tvPartsTable.refresh();
        }
    }

    @FXML
    private void onActionAddPart(ActionEvent event) {
        Part partToAddToProduct = tvPartsTable.getSelectionModel().getSelectedItem();
        productParts.add(partToAddToProduct);
    }

    @FXML
    private void onActionDeletePartFromProduct(ActionEvent event) {
        int partToDeleteFromProduct = tvProductPartsTable.getSelectionModel().getSelectedIndex();
        productParts.remove(partToDeleteFromProduct);
    }

    @FXML
    private void onActionSave(ActionEvent event) throws IOException {
        String newProductName = tbProductName.getText();
        int newProductStock = Integer.valueOf(tbProductInv.getText());
        double newProductPrice = Double.valueOf(tbProductPriceCost.getText());
        int newProductMax = Integer.valueOf(tbProductMax.getText());
        int newProductMin = Integer.valueOf(tbProductMin.getText());
        
        newProduct = new Product(newProductName, newProductPrice, newProductStock, newProductMin, newProductMax);
        tvProductPartsTable.getItems().forEach(part -> {newProduct.addAssociatedPart(part);});
        imInventory.addProduct(newProduct);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainScreen.fxml"));
        controller.MainScreenController mainScreenController = new controller.MainScreenController(imInventory);
        loader.setController(mainScreenController);
        parent = loader.load();
        scene = new Scene(parent);
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void onActionCancel(ActionEvent event) throws IOException {
        Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel? The product will not be added.", ButtonType.YES, ButtonType.NO);
        confirmDelete.setTitle("Confirm Cancel");
        confirmDelete.showAndWait().filter(response -> response == ButtonType.YES).ifPresent(response -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainScreen.fxml"));
                controller.MainScreenController mainScreenController = new controller.MainScreenController(imInventory);
                loader.setController(mainScreenController);
                parent = loader.load();
                scene = new Scene(parent);
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
}
