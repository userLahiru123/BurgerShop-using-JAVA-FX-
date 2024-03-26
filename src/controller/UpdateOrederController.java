package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateOrederController implements Initializable {
    public ComboBox<String> cb;
    public TextField txtOrderID;
    public TextField txtCustomerID;
    public TextField txtName;
    public TextField txtQuantity;
    public Button btnSearch;
    public Button btnUpdate;
    public Button btnClear;
    public Button btnBack;
    public Label lblTotal;
    public Label txtError;

    String orderId;

    ShopController controller = ShopController.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> statusValues = FXCollections.observableArrayList();
        statusValues.add("Cancelled...");
        statusValues.add("Processing...");
        statusValues.add("Delivered...");
        cb.setItems(statusValues);

        btnUpdate.setDisable(true);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {

        orderId = txtOrderID.getText();
        if (controller.isExitOrder(orderId)) {
//            btnSearch.setEnabled(false);
            btnSearch.setDisable(true);
            // search order..............
            txtCustomerID.setText(controller.getCustomerId(orderId));
            txtName.setText(controller.getCustomer(orderId));
            txtQuantity.setText(String.valueOf(controller.getOrderQTY(orderId)));
            lblTotal.setText(String.format("%.2f", controller.getOrderValue(orderId)));
            switch (controller.getOrderStatus(orderId)) {
                case "Cancel":
//                    cb.setSelectedIndex(0);
                    cb.getSelectionModel().select(0);
                    break;
                case "Preparing":
//                    cb.setSelectedIndex(1);
                    cb.getSelectionModel().select(1);
                    break;
                case "Delivered":
//                    cb.setSelectedIndex(2);
                    cb.getSelectionModel().select(2);
                    break;
                default:
                    throw new AssertionError();
            }

            if (controller.getOrderStatus(orderId).equals("Cancel")) {
                txtError.setText("This order has been cancelled.\nSorry, You can not update this order...");
//                cb.setSelectedIndex(0);
                cb.getSelectionModel().select(0);
//                cb.setEnabled(false);
                cb.setDisable(true);
            } else if (controller.getOrderStatus(orderId).equals("Delivered")) {
                txtError.setText("This order has been delivered.\nSorry, You can not update this order...");
//                cb.setSelectedIndex(2);
                cb.getSelectionModel().select(2);
//                cb.setEnabled(false);
                cb.setDisable(true);
            }

            if (controller.getOrderStatus(orderId).equals("Preparing")) {
//                btnUpdate.setEnabled(true);
                btnUpdate.setDisable(false);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter valid Order ID...");
            txtOrderID.setText("");
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
//        btnUpdate.setEnabled(false);
        btnUpdate.setDisable(true);

//        s = cb.getSelectedIndex();
        int s = cb.getSelectionModel().getSelectedIndex();
        int qty = Integer.parseInt(txtQuantity.getText());
        if (qty > 0) {
            // quantity update...............
            controller.updateQTY(orderId, qty);

            // status update..............
            controller.updateStatus(orderId, s);
            lblTotal.setText(String.format("%.2f", controller.getOrderValue(orderId)));
            JOptionPane.showMessageDialog(null, "Your order successfully updated...\n New order Status: " + controller.getOrderStatus(orderId) + "\nNew Total: Rs." + String.format("%.2f", controller.getOrderValue(orderId)));
        } else {
            JOptionPane.showMessageDialog(null, "Please enter valid quantity");
            txtQuantity.setText("");
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtOrderID.setText("");
        txtCustomerID.setText("");
        txtName.setText("");
        txtQuantity.setText("");
        lblTotal.setText("");
        txtError.setText("");
//        btnUpdate.setEnabled(false);
        btnUpdate.setDisable(true);
//        btnSearch.setEnabled(true);
        btnSearch.setDisable(false);
//        cb.setEnabled(true);
        cb.setDisable(false);
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        try {
            Parent fxmlLoader = new FXMLLoader(getClass().getResource("../view/DashboardForm.fxml")).load();
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader));
            stage.setTitle("Burger Shop");
            stage.getIcons().add(new Image("./assets/Hamburger.png"));
            stage.show();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        // close window.............................
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }

}
