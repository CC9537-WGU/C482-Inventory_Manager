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
        int newPartStock = Integer.valueOf(tbInv.getText());
        double newPartPrice = Double.valueOf(tbPriceCost.getText());
        int newPartMax = Integer.valueOf(tbMax.getText());
        int newPartMin = Integer.valueOf(tbMin.getText());

        if (rbInHouse.isSelected()) {
            int newPartMachineId = Integer.valueOf(tbInhouseOutsourced.getText());
            newPart = new InHouse(newPartName, newPartPrice, newPartStock, newPartMin, newPartMax, newPartMachineId);
            imInventory.addPart(newPart);
        }

        if (rbOutSourced.isSelected()) {
            String newPartCompanyName = tbInhouseOutsourced.getText();
            newPart = new Outsourced(newPartName, newPartPrice, newPartStock, newPartMin, newPartMax, newPartCompanyName);
            imInventory.addPart(newPart);
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

}
