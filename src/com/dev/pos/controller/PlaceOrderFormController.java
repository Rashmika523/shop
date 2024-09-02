package com.dev.pos.controller;

import com.dev.pos.Enum.BoType;
import com.dev.pos.bo.BoFactory;
import com.dev.pos.bo.custom.BatchBo;
import com.dev.pos.bo.custom.CustomerBo;
import com.dev.pos.dto.CustomerDTO;
import com.dev.pos.dto.ProductDetailsJoinDto;
import com.dev.pos.dto.tm.CartTm;
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

    public TableView<CartTm> tblOrder;
    public TableColumn<CartTm, String> colBarcode;
    public TableColumn<CartTm, String> colDescription;
    public TableColumn<CartTm, Double> colSellingPrice;
    public TableColumn<CartTm, Double> colDiscount;
    public TableColumn<CartTm, Double> colShowPrice;
    public TableColumn<CartTm, Integer> colQty;
    public TableColumn<CartTm, Double> colTotal;
    public TableColumn<CartTm, Button> colDelete;
    public Label lblTotal;

    CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);
    BatchBo batchBo = BoFactory.getInstance().getBo(BoType.BATCH);

    public void initialize() {
        colBarcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colShowPrice.setCellValueFactory(new PropertyValueFactory<>("showPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("button"));
    }

    public void btnCompleOrder(ActionEvent actionEvent) {
    }

    public void btnBackToHome(ActionEvent actionEvent) throws IOException {
        setUI("DashboardForm", false);
    }

    public void btnAddNewCustomer(ActionEvent actionEvent) throws IOException {
        setUI("CustomerForm", true);
    }

    public void btnAddNewProduct(ActionEvent actionEvent) throws IOException {
        setUI("ProductMainForm", true);
    }

    private void setUI(String location, boolean state) throws IOException {
        Stage stage = null;
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml")));

        if (state) {
            stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.centerOnScreen();
        } else {
            stage = (Stage) context.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
        }

    }

    public void searchCustomerOnAction(ActionEvent actionEvent) {

        try {
            CustomerDTO dto = customerBo.findCustomer(txtEmail.getText().trim());
            if (dto != null) {
                txtContact.setText(dto.getContact());
                txtName.setText(dto.getName());
                txtSalary.setText(String.valueOf(dto.getSalary()));
                fetchLoyalityCardData(txtEmail.getText().trim());
                txtBarcode.requestFocus();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Customer Not Found...!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void fetchLoyalityCardData(String email) {

        hyprLoyalityDetails.setVisible(true);
        hyprLoyalityDetails.setText("+ New Loyalty");
    }

    public void loadProductOnAction(ActionEvent actionEvent) {
        try {
            ProductDetailsJoinDto joinDto = batchBo.findProductJoinDetail(txtBarcode.getText().trim());

            if (joinDto != null) {
                txtDescription.setText(joinDto.getDescription());
                txtDescount.setText(String.valueOf(0));
                txtSellingPrice.setText(String.valueOf(joinDto.getBatchDTO().getSellingPrice()));
                txtShowPrice.setText(String.valueOf(joinDto.getBatchDTO().getShowPrice()));
                txtBuyingPrice.setText(String.valueOf(joinDto.getBatchDTO().getBuyingPrice()));
                txtQtyOnHand.setText(String.valueOf(joinDto.getBatchDTO().getQtyOnHand()));
                txtQty.requestFocus();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Product Not Found...!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    ObservableList<CartTm> oblist = FXCollections.observableArrayList();

    public void addToCartOnAction(ActionEvent actionEvent) {

        int qty = Integer.parseInt(txtQty.getText());
        double sellingPrice = Double.parseDouble(txtSellingPrice.getText());
        double total = qty * sellingPrice;

        CartTm selectedItem = isExist(txtBarcode.getText().trim());

        if(selectedItem==null){

            Button button = new Button("Remove");
            if (Integer.parseInt(txtQtyOnHand.getText()) > qty) {

                CartTm tm = new CartTm(
                        txtBarcode.getText(),
                        txtDescription.getText(),
                        sellingPrice, Double.parseDouble(txtDescount.getText()),
                        Double.parseDouble(txtShowPrice.getText()),
                        qty,
                        total,
                        button
                );

                oblist.add(tm);

                tblOrder.setItems(oblist);
                clearFileds();
                txtBarcode.requestFocus();
                setTotal();

                button.setOnAction(event -> {
                    oblist.remove(tm);
                    tblOrder.refresh();
                    setTotal();
                });

            } else {
                new Alert(Alert.AlertType.ERROR, "Please Add Valid Quantity").show();
            }
        }else {
            selectedItem.setQty(qty+selectedItem.getQty());
            selectedItem.setTotal(total+selectedItem.getTotal());
            tblOrder.refresh();
            clearFileds();
            txtBarcode.requestFocus();
            setTotal();
        }



    }

    private CartTm isExist(String code) {

        for (CartTm tm : oblist) {
            if (tm.getBarcode().equals(code)) {
                return tm;
            }
        }

        return null;
    }

    private void clearFileds(){
        txtBarcode.clear();
        txtQty.clear();
        txtQtyOnHand.clear();
        txtBuyingPrice.clear();
        txtDescount.clear();
        txtShowPrice.clear();
        txtSellingPrice.clear();
    }

    private void setTotal(){
        double total =0;
        for(CartTm tm : oblist){
            total+=tm.getTotal();
        }
        lblTotal.setText(String.valueOf(total+"/="));
    }
}
