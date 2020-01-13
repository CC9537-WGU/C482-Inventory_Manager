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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.InHouse;
import model.Outsourced;

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
    Part modifyPart;
    int partToModifyIndex;
    
    @FXML
    private RadioButton rbInHouse;
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
    private Label swapInhouseOutsourced;
    @FXML
    private TextField tbInhouseOutsourced;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tbId.setText(Integer.toString(modifyPart.getId()));
        tbName.setText(modifyPart.getName());
        tbInv.setText(Integer.toString(modifyPart.getStock()));
        tbPriceCost.setText(Double.toString(modifyPart.getPrice()));
        tbMax.setText(Integer.toString(modifyPart.getMax()));
        tbMin.setText(Integer.toString(modifyPart.getMin()));
        
        if(modifyPart instanceof InHouse) {
            ActionEvent e = new ActionEvent();
            onAction_InHouse(e);
            rbInHouse.setSelected(true);
            tbInhouseOutsourced.setText(Integer.toString(((InHouse) modifyPart).getMachineId()));
        }
        
        if(modifyPart instanceof Outsourced) {
            ActionEvent e = new ActionEvent();
            onAction_Outsourced(e);
            rbOutSourced.setSelected(true);
            tbInhouseOutsourced.setText(((Outsourced) modifyPart).getCompanyName());
        }
    }    
    
    ModifyPartController(Inventory _imInventory, Part _partToModify, int _partToModifyIndex) {
        imInventory = _imInventory;
        modifyPart = _partToModify;
        partToModifyIndex = _partToModifyIndex;
    }

    @FXML
    private void onAction_InHouse(ActionEvent event) {
        swapInhouseOutsourced.setText("Machine ID");
    }

    @FXML
    private void onAction_Outsourced(ActionEvent event) {
        swapInhouseOutsourced.setText("Company Name");
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
    private void onActionSave(ActionEvent event) throws IOException {
        int modifyPartId = Integer.valueOf(tbId.getText());
        
        modifyPart.setName(tbName.getText());
        modifyPart.setStock(Integer.valueOf(tbInv.getText()));
        modifyPart.setPrice(Double.valueOf(tbPriceCost.getText()));
        modifyPart.setMax(Integer.valueOf(tbMax.getText()));
        modifyPart.setMin(Integer.valueOf(tbMin.getText()));
        
        if (rbInHouse.isSelected()) {
            ((InHouse) modifyPart).setMachineId(Integer.valueOf(tbInhouseOutsourced.getText()));
        }
        
        if (rbOutSourced.isSelected()) {
            String newPartCompanyName = tbInhouseOutsourced.getText();
            ((Outsourced) modifyPart).setCompanyName(tbInhouseOutsourced.getText());
        }
        
        imInventory.updatePart(partToModifyIndex, modifyPart);
        
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
    
}
