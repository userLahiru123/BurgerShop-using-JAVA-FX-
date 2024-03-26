package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class SearchOrderDetailsController {
    public Label lblCustomerID;
    public Label lblName;
    public Label lblQTY;
    public Label lblTotal;
    public Label lblOrderStatus;
    public TextField txtOrderID;
    public Button btnSearch;
    public Button btnClear;
    public Button btnBack;

    ShopController controller = ShopController.getInstance();

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String orderId = txtOrderID.getText();
        if (controller.isExitOrder(orderId)) {
            lblCustomerID.setText(controller.getCustomerId(orderId));
            lblName.setText(controller.getCustomer(orderId));
            lblOrderStatus.setText(controller.getOrderStatus(orderId));
            lblQTY.setText(String.valueOf(controller.getOrderQTY(orderId)));
            lblTotal.setText(String.format("%.2f", controller.getOrderValue(orderId)));
        } else {
            JOptionPane.showMessageDialog(null, "Please enter valid Order ID...");
            txtOrderID.setText("");
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtOrderID.setText("");
        lblCustomerID.setText("");
        lblName.setText("");
        lblOrderStatus.setText("");
        lblQTY.setText("");
        lblTotal.setText("");
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        try {
            Parent fxmlLoader = new FXMLLoader(getClass().getResource("../view/SearchBestCustomerForm.fxml")).load();
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
