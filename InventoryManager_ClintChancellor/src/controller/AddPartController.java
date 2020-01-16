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

public class AddPartController implements Initializable {

    private Stage stage;
    private Parent parent;
    private Scene scene;

    Inventory imInventory;
    Part newPart;

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

    AddPartController(Inventory _imInventory) {
        imInventory = _imInventory;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tbId.setText(Integer.toString(newPart.getNextPartId()));
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
        Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel? The part will not be added.", ButtonType.YES, ButtonType.NO);
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
        String newPartName = tbName.getText();

        int newPartStock = 0;
        try {
            newPartStock = Integer.valueOf(tbInv.getText());
        } catch (NumberFormatException numberFormatException) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Stock/Inventory can not contain characters or be blank.", ButtonType.OK);
            alert.setTitle("Error - Part Invalid");
            alert.showAndWait();
            return;
        }

        Double newPartPrice = 0.00;
        try {
            newPartPrice = Double.valueOf(tbPriceCost.getText());
        } catch (NumberFormatException numberFormatException) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Price can not contain characters or be blank.", ButtonType.OK);
            alert.setTitle("Error - Part Invalid");
            alert.showAndWait();
            return;
        }

        int newPartMax = 0;
        try {
            newPartMax = Integer.valueOf(tbMax.getText());
        } catch (NumberFormatException numberFormatException) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Max can not contain characters or be blank.", ButtonType.OK);
            alert.setTitle("Error - Part Invalid");
            alert.showAndWait();
            return;
        }

        int newPartMin = 0;
        try {
            newPartMin = Integer.valueOf(tbMin.getText());
        } catch (NumberFormatException numberFormatException) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Min can not contain characters or be blank.", ButtonType.OK);
            alert.setTitle("Error - Part Invalid");
            alert.showAndWait();
            return;
        }

        if (rbInHouse.isSelected()) {
            try {
                int newPartMachineId = Integer.valueOf(tbInhouseOutsourced.getText());
                newPart = new InHouse(newPartName, newPartPrice, newPartStock, newPartMin, newPartMax, newPartMachineId);
            } catch (NumberFormatException numberFormatException) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Machine ID can not contain characters or be blank.", ButtonType.OK);
                alert.setTitle("Error - Part Invalid");
                alert.showAndWait();
                return;
            }
        }

        if (rbOutSourced.isSelected()) {
            String newPartCompanyName = tbInhouseOutsourced.getText();
            newPart = new Outsourced(newPartName, newPartPrice, newPartStock, newPartMin, newPartMax, newPartCompanyName);
        }

        if (isPartValid(newPart)) {
            imInventory.addPart(newPart);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainScreen.fxml"));
            controller.MainScreenController mainScreenController = new controller.MainScreenController(imInventory);
            loader.setController(mainScreenController);
            parent = loader.load();
            scene = new Scene(parent);
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } else {
            return;
        }

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
