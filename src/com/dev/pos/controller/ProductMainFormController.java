package com.dev.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class ProductMainFormController {

    public AnchorPane context;
    public TextField txtProductCode;
    public TextArea txtProductDescription;
    public Button btnSave;
    public TextField txtSearch;

    public TableView tblProduct;
    public TableColumn colProductId;
    public TableColumn colDescription;
    public TableColumn colShowMore;
    public TableColumn colDelete;

    public TextField txtSelectedProductCode;
    public TextArea txtSelectedDescription;

    public TableView tblProductMain;
    public TableColumn colNo;
    public TableColumn colQty;
    public TableColumn colBuyingPrice;
    public TableColumn colDiscount;
    public TableColumn colShowPrice;
    public TableColumn colSellingPrice;
    public TableColumn colMainDelete;

    public void btnBackToHome(ActionEvent actionEvent) {
    }

    public void btnAddNewProductOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnAddNewBatchOnAction(ActionEvent actionEvent) {
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }
}
