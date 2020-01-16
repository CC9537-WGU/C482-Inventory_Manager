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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.InHouse;
import model.Outsourced;

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

        if (modifyPart instanceof InHouse) {
            ActionEvent e = new ActionEvent();
            onAction_InHouse(e);
            rbInHouse.setSelected(true);
            tbInhouseOutsourced.setText(Integer.toString(((InHouse) modifyPart).getMachineId()));
        }

        if (modifyPart instanceof Outsourced) {
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
        Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel? The part will not be modified.", ButtonType.YES, ButtonType.NO);
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

    @FXML
    private void onActionSave(ActionEvent event) throws IOException {
        int modifyPartId = Integer.valueOf(tbId.getText());
        String modifyPartName = tbName.getText();
        int modifyPartStock = Integer.valueOf(tbInv.getText());
        double modifyPartPrice = Double.valueOf(tbPriceCost.getText());
        int modifyPartMax = Integer.valueOf(tbMax.getText());
        int modifyPartMin = Integer.valueOf(tbMin.getText());

        modifyPart.setName(modifyPartName);
        modifyPart.setStock(modifyPartStock);
        modifyPart.setPrice(modifyPartPrice);
        modifyPart.setMax(modifyPartMax);
        modifyPart.setMin(modifyPartMin);

        if (rbInHouse.isSelected()) {
            if (modifyPart instanceof Outsourced) // Changed from Outsourced to In-House
            {
                int modifyPartMachineId = 0;
                try {
                    modifyPartMachineId = Integer.valueOf(tbInhouseOutsourced.getText());
                } catch (NumberFormatException numberFormatException) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Machine ID can not contain characters or be blank.", ButtonType.OK);
                    alert.setTitle("Error - Part Invalid");
                    alert.showAndWait();
                    return;
                }
                imInventory.deletePart(modifyPart);
                Part changedPartType = new InHouse(modifyPartId, modifyPartName, modifyPartPrice, modifyPartStock, modifyPartMin, modifyPartMax, modifyPartMachineId);
                if (isPartValid(changedPartType)) {
                    imInventory.addPart(changedPartType);
                } else {
                    return;
                }
            } else { // Was In-house Still In-House
                int modifyPartMachineId = Integer.valueOf(tbInhouseOutsourced.getText());
                ((InHouse) modifyPart).setMachineId(modifyPartMachineId);
                if (isPartValid(modifyPart)) {
                    imInventory.updatePart(partToModifyIndex, modifyPart);
                } else {
                    return;
                }
            }
        }

        if (rbOutSourced.isSelected()) {
            if (modifyPart instanceof InHouse) // Changed from In-House to Outsourced
            {
                String modifyPartCompanyName = tbInhouseOutsourced.getText();
                imInventory.deletePart(modifyPart);
                Part changedPartType = new Outsourced(modifyPartId, modifyPartName, modifyPartPrice, modifyPartStock, modifyPartMin, modifyPartMax, modifyPartCompanyName);
                if (isPartValid(changedPartType)) {
                    imInventory.addPart(changedPartType);
                } else {
                    return;
                }
            } else // Was OutSourced Still Outsourced
            {
                String modifyPartCompanyName = tbInhouseOutsourced.getText();
                ((Outsourced) modifyPart).setCompanyName(modifyPartCompanyName);
                if (isPartValid(modifyPart)) {
                    imInventory.updatePart(partToModifyIndex, modifyPart);
                } else {
                    return;
                }
            }
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainScreen.fxml"));
        controller.MainScreenController mainScreenController = new controller.MainScreenController(imInventory);
        loader.setController(mainScreenController);
        parent = loader.load();
        scene = new Scene(parent);
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private boolean isPartValid(Part _checkPart) {
        String _name = _checkPart.getName();
        Double _price = _checkPart.getPrice();
        int _stock = _checkPart.getStock();
        int _min = _checkPart.getMin();
        int _max = _checkPart.getMax();

        if (_name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Name can't be blank.", ButtonType.OK);
            alert.setTitle("Error - Part Invalid");
            alert.showAndWait();
            return false;
        }

        if ((_stock < _min)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Stock/Inventory can not be less than minimum.", ButtonType.OK);
            alert.setTitle("Error - Part Invalid");
            alert.showAndWait();
            return false;
        }

        if ((_stock > _max)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Stock/Inventory can not be more than maximum.", ButtonType.OK);
            alert.setTitle("Error - Part Invalid");
            alert.showAndWait();
            return false;
        }

        if ("0.0".equals(_price.toString())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Price can not be 0 or blank.", ButtonType.OK);
            alert.setTitle("Error - Part Invalid");
            alert.showAndWait();
            return false;
        }

        if (_min > _max) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Min can not be greater than Max.", ButtonType.OK);
            alert.setTitle("Error - Part Invalid");
            alert.showAndWait();
            return false;
        }
        return true;
    }

}
