package com.dev.pos.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

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

    public void btnCompleOrder(ActionEvent actionEvent) {
    }

    public void btnBackToHome(ActionEvent actionEvent) {
    }

    public void btnAddNewCustomer(ActionEvent actionEvent) {
    }

    public void btnAddNewProduct(ActionEvent actionEvent) {
    }
}
