package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Burger;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchBestCustomerController implements Initializable {
    public Button btnBack;
    public Button btnSearchCustomer;
    public Button btnSearchOrder;
    public TableColumn colCustomerID;
    public TableColumn colName;
    public TableColumn colTotal;
    public TableView tblBestCustomers;

    ShopController controller = ShopController.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("orderValue"));
        loadTable();
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

    public void btnSearchCustomerOnAction(ActionEvent actionEvent) {
        try {
            Parent fxmlLoader = new FXMLLoader(getClass().getResource("../view/SearchCustomerForm.fxml")).load();
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader));
            stage.setTitle("Burger Shop");
            stage.getIcons().add(new Image("./assets/Hamburger.png"));
            stage.show();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        // close window.............................
        Stage stage = (Stage) btnSearchCustomer.getScene().getWindow();
        stage.close();
    }

    public void btnSearchOrderOnAction(ActionEvent actionEvent) {
        try {
            Parent fxmlLoader = new FXMLLoader(getClass().getResource("../view/SearchOrderDetailsForm.fxml")).load();
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader));
            stage.setTitle("Burger Shop");
            stage.getIcons().add(new Image("./assets/Hamburger.png"));
            stage.show();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        // close window.............................
        Stage stage = (Stage) btnSearchOrder.getScene().getWindow();
        stage.close();
    }

    public void loadTable(){
        ObservableList<Burger> sortedlist;
        sortedlist = controller.sort();
        tblBestCustomers.setItems(sortedlist);
    }

}
