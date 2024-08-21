package com.dev.pos.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class NewBatchFormController {
    public AnchorPane context;
    public ImageView imgQR;
    public TextField txtQTy;
    public TextField txtBuyingPrice;
    public TextField txtShowPrice;
    public TextField txtSellingPrice;
    public RadioButton rdNo;
    public ToggleGroup discount;
    public RadioButton rdYes;
    public TextField txtProductCode;
    public TextArea txtDescription;
    public Button btnSave;

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }


}
