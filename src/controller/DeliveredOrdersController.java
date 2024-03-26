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

public class DeliveredOrdersController implements Initializable {
    public TableView tblDeliveredOrders;
    public TableColumn colOrderID;
    public TableColumn colCustomerID;
    public TableColumn colName;
    public TableColumn colQuantity;
    public TableColumn colTotal;
    public Button btnBack;

    ShopController controller = ShopController.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("orderQTY"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("orderValue"));
        loadTable();
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        try {
            Parent fxmlLoader = new FXMLLoader(getClass().getResource("../view/ViewOrderForm.fxml")).load();
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
        ObservableList<Burger> deliveredList;
        deliveredList = controller.viewOrders(Burger.DELIVERED);
        tblDeliveredOrders.setItems(deliveredList);
    }

}
