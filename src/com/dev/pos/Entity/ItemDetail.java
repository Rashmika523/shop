package com.dev.pos.Entity;

public class ItemDetail implements SuperEntity {


    private int order;
    private String orderDetailCode;
    private int qty;
    private double discount;
    private double amount;

    public ItemDetail() {
    }

    public ItemDetail(int order, String orderDetailCode, int qty, double discount, double amount) {
        this.order = order;
        this.orderDetailCode = orderDetailCode;
        this.qty = qty;
        this.discount = discount;
        this.amount = amount;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getOrderDetailCode() {
        return orderDetailCode;
    }

    public void setOrderDetailCode(String orderDetailCode) {
        this.orderDetailCode = orderDetailCode;
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
