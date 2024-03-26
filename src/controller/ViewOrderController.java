package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class ViewOrderController {
    public Button btnViewOrders;
    public Button btnPreparingOrders;
    public Button btnCancelOrders;
    public Button btnExit;
    public Button btnMain;

    public void btnViewOrdersOnAction(ActionEvent actionEvent) {
        try {
            Parent fxmlLoader = new FXMLLoader(getClass().getResource("../view/DeliveredOrdersForm.fxml")).load();
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader));
            stage.setTitle("Burger Shop");
            stage.getIcons().add(new Image("./assets/Hamburger.png"));
            stage.show();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        // close window.............................
        Stage stage = (Stage) btnViewOrders.getScene().getWindow();
        stage.close();
    }

    public void btnPreparingOrdersOnAction(ActionEvent actionEvent) {
        try {
            Parent fxmlLoader = new FXMLLoader(getClass().getResource("../view/ProcessingOrdersForm.fxml")).load();
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader));
            stage.setTitle("Burger Shop");
            stage.getIcons().add(new Image("./assets/Hamburger.png"));
            stage.show();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        // close window.............................
        Stage stage = (Stage) btnPreparingOrders.getScene().getWindow();
        stage.close();
    }

    public void btnCancelOrdersOnAction(ActionEvent actionEvent) {
        try {
            Parent fxmlLoader = new FXMLLoader(getClass().getResource("../view/CancelledOrdersForm.fxml")).load();
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader));
            stage.setTitle("Burger Shop");
            stage.getIcons().add(new Image("./assets/Hamburger.png"));
            stage.show();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        // close window.............................
        Stage stage = (Stage) btnCancelOrders.getScene().getWindow();
        stage.close();
    }

    public void btnExitOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void btnMainOnAction(ActionEvent actionEvent) {
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
        Stage stage = (Stage) btnMain.getScene().getWindow();
        stage.close();
    }
}
