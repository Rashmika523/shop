package com.dev.pos.dto;

public class ItemDetailDTO {

    private int detailCode;
    private int qty;
    private double discount;
    private double amount;

    public ItemDetailDTO() {
    }

    public ItemDetailDTO(int detailCode, int qty, double discount, double amount) {
        this.detailCode = detailCode;
        this.qty = qty;
        this.discount = discount;
        this.amount = amount;
    }

    public int getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(int detailCode) {
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
