/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Clint
 */
public class AddProductController implements Initializable {
    
    private Stage stage;
    private Parent scene;

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
    private TableView<?> tvPartsTable;
    @FXML
    private Button btnAddPart;
    @FXML
    private TableView<?> tvProductTable;
    @FXML
    private Button btnDeletePart;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionPartSearch(ActionEvent event) {
    }

    @FXML
    private void onActionAddPart(ActionEvent event) {
    }

    @FXML
    private void onActionDeleteProduct(ActionEvent event) {
    }

    @FXML
    private void onActionSave(ActionEvent event) {
    }

    @FXML
    private void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();       
        scene = FXMLLoader.load(getClass().getResource("/view/im_main.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
}
