package com.dev.pos.controller;

import com.dev.pos.Enum.BoType;
import com.dev.pos.bo.BoFactory;
import com.dev.pos.bo.custom.ProductBo;
import com.dev.pos.dao.DatabaseAccessCode;
import com.dev.pos.dto.ProductDTO;
import com.dev.pos.dto.tm.ProductTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;

public class ProductMainFormController {

    public AnchorPane context;
    public TextField txtProductCode;
    public TextArea txtProductDescription;
    public Button btnSave;
    public TextField txtSearch;

    public TableView<ProductTm> tblProduct;
    public TableColumn<ProductTm, Integer> colProductId;
    public TableColumn<ProductTm, String> colDescription;
    public TableColumn<ProductTm, Button> colShowMore;
    public TableColumn<ProductTm, Button> colDelete;

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

    ProductBo productBo = BoFactory.getInstance().getBo(BoType.PRODUCT);

    String searchText = "";

    public void initialize() {

        loadProductId();
        colProductId.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colShowMore.setCellValueFactory(new PropertyValueFactory<>("showMorebtn"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("deleteBtn"));

        loadAllProducts(searchText);

    }

    private void loadProductId() {
        try {
            txtProductCode.setText(String.valueOf(productBo.getLastProductId()));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnBackToHome(ActionEvent actionEvent) throws IOException {
        setUI("DashboardForm");
    }

    public void btnAddNewProductOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        try {

            if (btnSave.getText().equalsIgnoreCase("save product")) {

                boolean isSaved = productBo.saveProduct(new ProductDTO(
                        Integer.valueOf(txtProductCode.getText()),
                        txtProductDescription.getText()
                ));

                if(isSaved){
                    new Alert(Alert.AlertType.INFORMATION,"Product has been saved...!").show();
                    loadAllProducts(searchText);
                    loadProductId();
                }
            }

        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }


    }

    public void btnAddNewBatchOnAction(ActionEvent actionEvent) {
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }

    private void loadAllProducts(String searchText) {

        ObservableList<ProductTm> oblist = FXCollections.observableArrayList();

        try {
            for (ProductDTO p : productBo.findAllProducts()) {
                Button showMore = new Button("Show More");
                Button deleteBtn = new Button("Delete");
                ProductTm tm = new ProductTm(
                        p.getCode(),
                        p.getDescription(),
                        showMore,
                        deleteBtn
                );
                oblist.add(tm);
            }

            tblProduct.setItems(oblist);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
