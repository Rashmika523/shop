package com.dev.pos.dto;

public class ItemDetailDTO {

    private String detailCode;
    private int qty;
    private double discount;
    private double amount;

    public ItemDetailDTO() {
    }

    public ItemDetailDTO(String detailCode, int qty, double discount, double amount) {
        this.detailCode = detailCode;
        this.qty = qty;
        this.discount = discount;
        this.amount = amount;
    }

    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
