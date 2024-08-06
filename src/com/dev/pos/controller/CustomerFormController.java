package com.dev.pos.controller;

import com.dev.pos.dao.DatabaseAccessCode;
import com.dev.pos.dto.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class CustomerFormController {
    public AnchorPane context;
    public TextField txtEmail;
    public TextField txtName;
    public TextField txtContact;
    public TextField txtSalary;
    public Button btnSave;
    public TextField txtSearch;
    public TableView tblCustomer;
    public TableColumn colNo;
    public TableColumn colEmail;
    public TableColumn colName;
    public TableColumn colContact;
    public TableColumn colSalary;
    public TableColumn colDelete;

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

       if(btnSave.getText().equalsIgnoreCase("Save Customer")){

           CustomerDTO customer = DatabaseAccessCode.findCustomer(txtEmail.getText());

           if(customer.getEmail().equalsIgnoreCase(txtEmail.getText())){
               new Alert(Alert.AlertType.INFORMATION,"Customer is already saved...!").show();
           }else {
               boolean isSaved = DatabaseAccessCode.createCustomer(dto);

               if(isSaved){
                   new Alert(Alert.AlertType.INFORMATION,"Customer has been saved...!").show();
                   clearFields();
               }else {
                   new Alert(Alert.AlertType.INFORMATION,"Something went wrong, Try again...!").show();
               }
           }
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
}
