package com.dev.pos.Entity;

public class ItemDetail implements SuperEntity {

    private int orderDetailCode;
    private int order;
    private int qty;
    private double discount;
    private double amount;

    public ItemDetail() {
    }

    public ItemDetail(int orderDetailCode, int order, int qty, double discount, double amount) {
        this.orderDetailCode = orderDetailCode;
        this.order = order;
        this.qty = qty;
        this.discount = discount;
        this.amount = amount;
    }

    public int getOrderDetailCode() {
        return orderDetailCode;
    }

    public void setOrderDetailCode(int orderDetailCode) {
        this.orderDetailCode = orderDetailCode;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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
