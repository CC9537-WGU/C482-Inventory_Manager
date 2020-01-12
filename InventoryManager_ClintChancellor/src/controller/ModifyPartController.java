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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;

/**
 * FXML Controller class
 *
 * @author Clint
 */
public class ModifyPartController implements Initializable {
    
    private Stage stage;
    private Parent parent;
    private Scene scene;
    
    Inventory imInventory;
    
        @FXML
    private HBox hbMachineId;
    @FXML
    private RadioButton rbInHouse;
    @FXML
    private ToggleGroup rb_inHouse_outSourcedrb_inHouse_outSourced;
    @FXML
    private RadioButton rbOutSourced;
    @FXML
    private TextField tbId;
    @FXML
    private TextField tbName;
    @FXML
    private TextField tbInv;
    @FXML
    private TextField tbPriceCost;
    @FXML
    private TextField tbMax;
    @FXML
    private TextField tbMin;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private HBox hbCompanyName;
    @FXML
    private TextField tbCompanyName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    ModifyPartController(Inventory _imInventory, Part _partToModify) {
        imInventory = _imInventory;
    }

    @FXML
    private void onAction_InHouse(ActionEvent event) {
        hbCompanyName.setVisible(false);
        hbMachineId.setVisible(true);
    }

    @FXML
    private void onAction_Outsourced(ActionEvent event) {
        hbMachineId.setVisible(false);
        hbCompanyName.setVisible(true);
    }

    @FXML
    private void onActionCancel(ActionEvent event) throws IOException {
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
    private void onActionSave(ActionEvent event) {
    }
    
    @FXML
    private void tbMachineId(ActionEvent event) {
    }
    
}
