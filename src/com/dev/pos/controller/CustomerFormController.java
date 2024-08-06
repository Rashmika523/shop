package com.dev.pos.controller;

import com.dev.pos.dao.DatabaseAccessCode;
import com.dev.pos.dto.CustomerDTO;
import com.dev.pos.dto.tm.CustomerTm;
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

public class CustomerFormController {
    public AnchorPane context;
    public TextField txtEmail;
    public TextField txtName;
    public TextField txtContact;
    public TextField txtSalary;
    public Button btnSave;
    public TextField txtSearch;

    public TableView<CustomerTm> tblCustomer;
    public TableColumn<CustomerTm,Integer> colNo;
    public TableColumn<CustomerTm,String> colEmail;
    public TableColumn<CustomerTm,String> colName;
    public TableColumn<CustomerTm,String> colContact;
    public TableColumn<CustomerTm,Double> colSalary;
    public TableColumn<CustomerTm,Button> colDelete;

    String searchText ="";

    public void initialize(){

        colNo.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("button"));

        loadCustomer(searchText);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            loadCustomer(searchText);
        });
    }


    public void btnBackToHome(ActionEvent actionEvent) throws IOException {

        setUI("LoginForm");
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        CustomerDTO dto= new CustomerDTO(
                txtEmail.getText(),
                txtName.getText(),
                txtContact.getText(),
                Double.valueOf(txtSalary.getText())
        );

        try {

            if (btnSave.getText().equalsIgnoreCase("Save Customer")) {

                CustomerDTO customer = DatabaseAccessCode.findCustomer(txtEmail.getText());

                if(customer!=null){
                    new Alert(Alert.AlertType.INFORMATION,"Customer is already saved...!").show();
                }else {
                    boolean isSaved = DatabaseAccessCode.createCustomer(dto);
                    if (isSaved) {
                        new Alert(Alert.AlertType.INFORMATION, "Customer has been saved...!").show();
                        clearFields();
                        loadCustomer(searchText);
                    } else {
                        new Alert(Alert.AlertType.INFORMATION, "Something went wrong, Try again...!").show();
                    }
                }

            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }


    private void setUI(String location) throws IOException {

        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }

    private void clearFields(){
        txtEmail.clear();
        txtSalary.clear();
        txtContact.clear();
        txtName.clear();
    }

    private void loadCustomer(String searchText){

        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();
        try {
            int counter =1;
            for(CustomerDTO dto : (txtSearch.getLength()>0?DatabaseAccessCode.searchCustomer(searchText):DatabaseAccessCode.findAllCustomer())){
                Button button = new Button("Delete");
                CustomerTm customerTm = new CustomerTm(
                        counter,
                        dto.getEmail(),
                        dto.getName(),
                        dto.getContact(),
                        dto.getSalary(),
                        button
                );
                counter++;
                obList.add(customerTm);
            }
            tblCustomer.setItems(obList);
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }


    }
}
