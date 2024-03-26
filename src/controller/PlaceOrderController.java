package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Burger;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class PlaceOrderController implements Initializable {

    public Label lblTotal;
    public Button btnPlaceOrder;
    public Button btnMain;
    public Button btncancel;
    public Label lblStatus;
    public TextField txtCustomerID;
    public TextField txtName;
    public TextField txtBurgerQTY;
    public Label lblOrderID;
    ShopController controller = ShopController.getInstance();
    String orderId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderId = controller.generateOrderId();
        lblOrderID.setText(orderId);
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        String customerId = txtCustomerID.getText();
        if (customerId.isEmpty() || customerId.charAt(0) != '0' || customerId.length() != 10) {
            JOptionPane.showMessageDialog(null, "Please enter valid ID");
            txtCustomerID.setText("");
        } else {
            Pattern p = Pattern.compile("(.)*(\\d)(.)*");
            if(txtName.getText().isEmpty() || p.matcher(txtName.getText()).matches()){
                JOptionPane.showMessageDialog(null, "Please enter valid name");
                txtName.setText("");
            }else{
                boolean isExistCustomer;
                String customerName;
                double billValue;
                isExistCustomer = controller.isExitCustomer(customerId);
                if (isExistCustomer) {
                    customerName = controller.getCustomerName(customerId);
                } else {
                    customerName = txtName.getText();
                }

                Pattern p1 = Pattern.compile("[0-9]+");
                if(txtBurgerQTY.getText().isEmpty() || !p1.matcher(txtBurgerQTY.getText()).matches()){
                    JOptionPane.showMessageDialog(null, "Please enter valid quantity");
                    txtBurgerQTY.setText("");
                }else{
                    // get burger quantity.................
                    int qty = Integer.parseInt(txtBurgerQTY.getText());
                    if (qty > 0) {
                        billValue = qty * Burger.BURGERPRICE;
                        lblTotal.setText(String.format("%.2f", billValue));
                        lblStatus.setText("Pending...");

                        controller.addOrder(orderId, customerName, customerId, qty, billValue);
                        JOptionPane.showMessageDialog(null,"Your order is confirmed.....\n Rs." + String.format("%.2f", billValue));

                        try {
                            Parent fxmlLoader = new FXMLLoader(getClass().getResource("../view/PlaceOrderForm.fxml")).load();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(fxmlLoader));
                            stage.setTitle("Burger Shop");
                            stage.getIcons().add(new Image("./assets/Hamburger.png"));
                            stage.show();
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(null,e.getMessage());
                        }
                        // close window.............................
                        Stage stage = (Stage) btnPlaceOrder.getScene().getWindow();
                        stage.close();
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter valid quantity");
                        txtBurgerQTY.setText("");
                    }
                }
            }
        }
    }

    public void btnMainMenuOnAction(ActionEvent actionEvent) {
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

    public void btncancelOnAction(ActionEvent actionEvent) {
        txtCustomerID.setText("");
        txtBurgerQTY.setText("");
        lblStatus.setText("");
        lblTotal.setText("");
        txtName.setText("");
    }
}
