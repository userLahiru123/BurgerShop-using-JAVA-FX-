package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Burger;

import javax.swing.*;
import java.io.IOException;

public class SearchCustomerController {
    public TextField txtCustomerID;
    public Button btnSearch;
    public Button btnClear;

    public Button btnBack;
    public Label lblName;
    public TableColumn colOrderID;
    public TableColumn colQuantity;
    public TableColumn colTotal;
    public TableView tblOrderDetails;

    ShopController controller = ShopController.getInstance();

    String customerId;

    public void btnSearchOnAction(ActionEvent actionEvent) {
        customerId = txtCustomerID.getText();

        if (controller.isExitCustomer(customerId)) {
            lblName.setText(controller.getCustomerName(customerId));

            colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderId"));
            colQuantity.setCellValueFactory(new PropertyValueFactory<>("orderQTY"));
            colTotal.setCellValueFactory(new PropertyValueFactory<>("orderValue"));
            loadTable();

        }else{
            JOptionPane.showMessageDialog(null, "Please enter valid Customer ID...");
            txtCustomerID.setText("");
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtCustomerID.setText("");
        lblName.setText("");
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

    public void loadTable(){
        ObservableList<Burger> customerList;
        customerList = controller.searchCustomer(customerId);
        tblOrderDetails.setItems(customerList);
    }
}
