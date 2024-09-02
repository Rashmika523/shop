package com.dev.pos.controller;

import com.dev.pos.Enum.BoType;
import com.dev.pos.bo.BoFactory;
import com.dev.pos.bo.custom.CustomerBo;
import com.dev.pos.dto.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;

public class PlaceOrderFormController {
    public AnchorPane context;
    public RadioButton rbnManualMode;
    public ToggleGroup qrMode;
    public RadioButton rbnAutoMode;
    public TextField txtEmail;
    public TextField txtName;
    public TextField txtContact;
    public TextField txtSalary;
    public Label lblMemberShip;
    public Hyperlink hyprLoyalityDetails;
    public TextField txtBarcode;
    public TextField txtDescription;
    public TextField txtSellingPrice;
    public TextField txtDescount;
    public TextField txtShowPrice;
    public TextField txtBuyingPrice;
    public TextField txtQtyOnHand;
    public TextField txtQty;
    public TableView tblOrder;
    public TableColumn colBarcode;
    public TableColumn colDescription;
    public TableColumn colSellingPrice;
    public TableColumn colDiscount;
    public TableColumn colShowPrice;
    public TableColumn colQty;
    public TableColumn colTotal;
    public TableColumn colDelete;
    public Label lblTotal;

    CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);

    public void btnCompleOrder(ActionEvent actionEvent) {
    }

    public void btnBackToHome(ActionEvent actionEvent) throws IOException {
        setUI("DashboardForm",false);
    }

    public void btnAddNewCustomer(ActionEvent actionEvent) throws IOException {
        setUI("CustomerForm",true);
    }

    public void btnAddNewProduct(ActionEvent actionEvent) throws IOException {
        setUI("ProductMainForm",true);
    }

    private void setUI(String location,boolean state) throws IOException {
        Stage stage = null;
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml")));

        if(state){
            stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.centerOnScreen();
        }else {
            stage = (Stage) context.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
        }

    }

    public void searchCustomerOnAction(ActionEvent actionEvent) {

        try{
            CustomerDTO dto =customerBo.findCustomer(txtEmail.getText().trim());
            if(dto!=null){
                txtContact.setText(dto.getContact());
                txtName.setText(dto.getName());
                txtSalary.setText(String.valueOf(dto.getSalary()));
                fetchLoyalityCardData(txtEmail.getText().trim());
                txtBarcode.requestFocus();
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Customer Not Found...!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    private void fetchLoyalityCardData(String email){

        hyprLoyalityDetails.setVisible(true);
        hyprLoyalityDetails.setText("+ New Loyalty");
    }
}
