package com.dev.pos.controller;

import com.dev.pos.util.security.qr.QRdataGenerator;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.image.BufferedImage;

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

    String uniqueData =null;

    public void initialize(){
        try {
            setQRcode();
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    private void setQRcode() throws WriterException {

        uniqueData = QRdataGenerator.generate(30);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(
                qrCodeWriter.encode(
                        uniqueData,
                        BarcodeFormat.QR_CODE,
                        198,196
                )
        );

        Image image = SwingFXUtils.toFXImage(bufferedImage,null);
        imgQR.setImage(image);
    }

    public void setProductCode(int code,String description){
        txtProductCode.setText(String.valueOf(code));
        txtDescription.setText(description);
    }

}
